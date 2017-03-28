package idv.trashchu.bot.controllers

import javax.inject.Inject

import akka.stream.Materializer
import idv.trashchu.bot.adapters.{FacebookAdapter, LineAdapter}
import idv.trashchu.bot.handlers.FortuneBotHandler
import idv.trashchu.bot.streams.MessengerStream
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

/**
  * Created by joshchu999 on 12/14/16.
  */
class FortuneBotController @Inject() (implicit materializer: Materializer,
                                      ec: ExecutionContext,
                                      facebookAdapter: FacebookAdapter,
                                      lineAdapter: LineAdapter,
                                      fortuneBotHandler: FortuneBotHandler) extends Controller with MessengerStream {

  def getFacebookWebhook(`hub.mode`: String, `hub.challenge`: String, `hub.verify_token`: String) = Action {
    Ok(`hub.challenge`)
  }



  def postFacebookWebhook = Action(parse.json) { request =>

    try {
      val accessToken = "EAAFk8x93NhEBAI0lUvbE8ZAnZCj4ZCW3XceJWEOGAe5eh0sfpJB4tmnqUZAILgF9PCokM0c57AZAKEKgNEsZAVRIyZBpUweQ7irLuFwiJmNKaj2qFrU4oylaWuBqbsYeQxsSLI30TAferK6EZAfAg1utVMnTAbY8RfyJMoS6IeFbLwZDZD"
      this.process(request.body, facebookAdapter, fortuneBotHandler, accessToken)
      Ok("")
    }
    catch {
      case e: Exception => InternalServerError(e.getMessage)
    }
  }



  def postLineWebhook = Action(parse.json) { request =>

    try {
      val accessToken = "DTVKMzBzXN0j1T+q1KNdmCuZVQP3Ys5CMpHRzzoDMDD7ZBkbryqnm4lRbyyh9+eJrxpuopu/9KrSAo/80o+aZTXGvk110Ord4rPrIyrvhH+FczE8kc18ZHGzN05lT/m58LSvpai3IB9uRwooP8BDVgdB04t89/1O/w1cDnyilFU="
      this.process(request.body, lineAdapter, fortuneBotHandler, accessToken)
      Ok("")
    }
    catch {
      case e: Exception => InternalServerError(e.getMessage)
    }
  }


}
