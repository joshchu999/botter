package idv.trashchu.bot.adapters

import javax.inject.Inject

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import idv.trashchu.bot.models.BotModel.{Message, TextMessage}
import idv.trashchu.bot.models.FacebookModel.{FacebookUser, FacebookWebhookRequestBody, MessageMessage2, MessageResponseBody}
import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by joshchu999 on 2/27/17.
  */
class FacebookAdapter @Inject()(implicit materializer: Materializer,
                                ec: ExecutionContext,
                                ws: WSClient) extends MessengerAdapter {

  override def fromRequestBodyToMessage(requestBody: JsValue): Future[List[Message]] = {

    Logger.info(s"Request: ${requestBody}")

    Source.apply(requestBody.as[FacebookWebhookRequestBody].entry.toList.reverse)
      .mapConcat(_.messaging.toList)
      .map(messaging => TextMessage(None, messaging.timestamp.getOrElse(0), messaging.sender.id, messaging.recipient.id, messaging.message.get.text.getOrElse("")))
      .runFold(List.empty[Message]) { (list, message) =>
        message :: list
      }
      .transform(_.toList, e => e)
  }

  override def fromMessageToResponseBody(message: Message): JsValue = {
    message match {
      case tm: TextMessage => Json.toJson(MessageResponseBody(FacebookUser(tm.recipientID), MessageMessage2(tm.text)))
    }
  }

  override def sendResponseBody(accessToken: String, responseBody: JsValue): Future[WSResponse] = {

    Logger.info(s"Response: ${responseBody}")

    ws.url(s"https://graph.facebook.com/v2.6/me/messages?access_token=${accessToken}")
      .post(responseBody)
  }
}
