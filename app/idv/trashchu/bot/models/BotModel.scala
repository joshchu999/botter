package idv.trashchu.bot.models

/**
  * Created by joshchu999 on 2/27/17.
  */
object BotModel {

  sealed trait Message
  case class TextMessage(replyToken: Option[String], timestamp: Long, senderID: String, recipientID: String, text: String) extends Message
}
