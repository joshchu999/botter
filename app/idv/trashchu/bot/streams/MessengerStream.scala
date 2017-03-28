package idv.trashchu.bot.streams

import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import idv.trashchu.bot.adapters.MessengerAdapter
import idv.trashchu.bot.handlers.BotHandler
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.mvc.Result

/**
  * Created by joshchu999 on 3/20/17.
  */
trait MessengerStream {

  implicit val materializer: Materializer

  def process(requestBody: JsValue, adapter: MessengerAdapter, botHandler: BotHandler, accessToken: String) {

    Source.single(requestBody)
      .mapAsync(1)(adapter.fromRequestBodyToMessage(_))
      .mapConcat(_.toList)
      .map(botHandler.handleMessage(_))
      .map(adapter.fromMessageToResponseBody(_))
      .mapAsync(1)(adapter.sendResponseBody(accessToken, _))
      .runWith(Sink.ignore)
      .recover(errorHandler)
  }

  def errorHandler: PartialFunction[Throwable, Result] = {
    case e: Throwable =>

      Logger.error(s"Exception: ${e.getMessage}", e)
      throw e
  }
}
