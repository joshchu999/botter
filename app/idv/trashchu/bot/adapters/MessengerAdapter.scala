package idv.trashchu.bot.adapters

import idv.trashchu.bot.models.BotModel.Message
import play.api.libs.json.JsValue
import play.api.libs.ws.WSResponse

import scala.concurrent.Future

/**
  * Created by joshchu999 on 2/27/17.
  */
trait MessengerAdapter {

  def fromRequestBodyToMessage(requestBody: JsValue): Future[List[Message]]

  def fromMessageToResponseBody(message: Message): JsValue

  def sendResponseBody(accessToken: String, responseBody: JsValue): Future[WSResponse]

}
