package idv.trashchu.bot.models

import play.api.libs.json.Json

/**
  * Created by joshchu999 on 2/22/17.
  */
object LineModel {

  case class LineMessage(id: String,
                         `type`: String,
                         text: Option[String] = None,
                         title: Option[String] = None,
                         address: Option[String] = None,
                         latitude: Option[Double] = None,
                         longitude: Option[Double] = None,
                         packageId: Option[String] = None,
                         stickerId: Option[String] = None)

  case class LinePostback(data: String)

  case class LineBeacon(hwid: String,
                        `type`: String)

  case class LineSource(`type`: String,
                        userId: Option[String] = None,
                        groupId: Option[String] = None,
                        roomId: Option[String] = None)

  case class LineEvent(`type`: String,
                       timestamp: Long,
                       source: LineSource,
                       replyToken : Option[String] = None,
                       message : Option[LineMessage] = None,
                       postback : Option[LinePostback] = None,
                       beacon : Option[LineBeacon] = None)

  case class LineWebhookRequestBody(events: Seq[LineEvent])

  case class MessageMessage2(`type`: String,
                             text: String)

  case class MessageResponseBody(replyToken: String,
                                 messages: Seq[MessageMessage2])




  implicit val lineMessageReads = Json.reads[LineMessage]
  implicit val lineMessageWrites = Json.writes[LineMessage]
  implicit val linePostbackReads = Json.reads[LinePostback]
  implicit val linePostbackWrites = Json.writes[LinePostback]
  implicit val lineBeaconReads = Json.reads[LineBeacon]
  implicit val lineBeaconWrites = Json.writes[LineBeacon]
  implicit val lineSourceReads = Json.reads[LineSource]
  implicit val lineSourceWrites = Json.writes[LineSource]
  implicit val lineEventReads = Json.reads[LineEvent]
  implicit val lineEventWrites = Json.writes[LineEvent]
  implicit val lineWebhookRequestBodyReads = Json.reads[LineWebhookRequestBody]
  implicit val lineWebhookRequestBodyWrites = Json.writes[LineWebhookRequestBody]

  implicit val messageMessage2Reads = Json.reads[MessageMessage2]
  implicit val messageMessage2Writes = Json.writes[MessageMessage2]
  implicit val messageResponseBodyReads = Json.reads[MessageResponseBody]
  implicit val messageResponseBodyWrites = Json.writes[MessageResponseBody]


}
