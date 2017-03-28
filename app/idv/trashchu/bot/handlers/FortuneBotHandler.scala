package idv.trashchu.bot.handlers

import idv.trashchu.bot.models.BotModel.{Message, TextMessage}

/**
  * Created by joshchu999 on 2/27/17.
  */
class FortuneBotHandler extends BotHandler {
  override protected def handleTextMessage(message: TextMessage): Message = TextMessage(message.replyToken, System.currentTimeMillis(), message.recipientID, message.senderID, s"FortuneBot: ${message.text}")
}
