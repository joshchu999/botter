package idv.trashchu.bot.handlers

import idv.trashchu.bot.models.BotModel.{Message, TextMessage}

/**
  * Created by joshchu999 on 2/27/17.
  */
trait BotHandler {

  def handleMessage(message: Message): Message = {
    message match {
      case tm: TextMessage => this.handleTextMessage(tm)
    }
  }

  protected def handleTextMessage(message: TextMessage): Message

}
