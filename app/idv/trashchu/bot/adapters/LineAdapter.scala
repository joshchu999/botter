package idv.trashchu.bot.adapters

import javax.inject.Inject

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import idv.trashchu.bot.models.BotModel.{Message, TextMessage}
import idv.trashchu.bot.models.LineModel.{LineWebhookRequestBody, MessageMessage2, MessageResponseBody}
import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by joshchu999 on 2/27/17.
  */
class LineAdapter @Inject()(implicit materializer: Materializer,
                            ec: ExecutionContext,
                            ws: WSClient) extends MessengerAdapter {

  override def fromRequestBodyToMessage(requestBody: JsValue): Future[List[Message]] = {

    Logger.info(s"Request: ${requestBody}")

    Source.apply(requestBody.as[LineWebhookRequestBody].events.toList.reverse)
      .map(event => {
        event.message match {
          case Some(message) =>
            message.`type` match {
              case "text" =>
                TextMessage(event.replyToken, event.timestamp, event.source.userId.getOrElse(""), "", message.text.getOrElse("empty"))
            }
        }
      })
      .runFold(List.empty[Message]) { (list, message) =>
        message :: list
      }
      .transform(_.toList, e => e)
  }

  override def fromMessageToResponseBody(message: Message): JsValue = {
    message match {
      case tm: TextMessage => Json.toJson(MessageResponseBody(tm.replyToken.get, Seq(MessageMessage2("text", tm.text))))
    }
  }

  override def sendResponseBody(accessToken: String, responseBody: JsValue): Future[WSResponse] = {

    Logger.info(s"Response: ${responseBody}")

    ws.url("https://api.line.me/v2/bot/message/reply")
      .withHeaders("Authorization" -> s"Bearer ${accessToken}")
      .post(responseBody)
  }
}
