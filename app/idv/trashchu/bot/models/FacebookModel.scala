package idv.trashchu.bot.models

import play.api.libs.json.Json

/**
  * Created by joshchu999 on 2/22/17.
  */
object FacebookModel {

  case class FacebookCoordinate(lat: Double,
                                long: Double)

  case class FacebookPayload(url: Option[String] = None,
                             coordinate: Option[FacebookCoordinate] = None)

  case class FacebookQuickReply(payload: String)

  case class FacebookAttachment(`type`: String,
                                payload: FacebookPayload)

  case class FacebookMessage(mid: String,
                             seq: Long,
                             text: Option[String] = None,
                             quick_reply: Option[FacebookQuickReply] = None,
                             attachments: Option[Seq[FacebookAttachment]] = None)

  case class FacebookDelivery(mids: Seq[String],
                              watermark: Long,
                              seq: Long)

  case class FacebookRead(watermark: Long,
                          seq: Long)

  case class FacebookUser(id: String)

  case class FacebookMessaging(sender: FacebookUser,
                               recipient: FacebookUser,
                               timestamp: Option[Long] = None,
                               message: Option[FacebookMessage] = None,
                               delivery: Option[FacebookDelivery] = None,
                               read: Option[FacebookRead] = None)

  case class FacebookEntry(id: String,
                           time: Long,
                           messaging: Seq[FacebookMessaging])

  case class FacebookWebhookRequestBody(`object`: String,
                                        entry: Seq[FacebookEntry])

  case class MessageMessage2(text: String)

  case class MessageResponseBody(recipient: FacebookUser,
                                 message: MessageMessage2)


  implicit val facebookCoordinateReads = Json.reads[FacebookCoordinate]
  implicit val facebookCoordinateWrites = Json.writes[FacebookCoordinate]
  implicit val facebookPayloadReads = Json.reads[FacebookPayload]
  implicit val facebookPayloadWrites = Json.writes[FacebookPayload]
  implicit val facebookQuickReplyReads = Json.reads[FacebookQuickReply]
  implicit val facebookQuickReplyWrites = Json.writes[FacebookQuickReply]
  implicit val facebookAttachmentReads = Json.reads[FacebookAttachment]
  implicit val facebookAttachmentWrites = Json.writes[FacebookAttachment]
  implicit val facebookMessageReads = Json.reads[FacebookMessage]
  implicit val facebookMessageWrites = Json.writes[FacebookMessage]
  implicit val facebookDeliveryReads = Json.reads[FacebookDelivery]
  implicit val facebookDeliveryWrites = Json.writes[FacebookDelivery]
  implicit val facebookReadReads = Json.reads[FacebookRead]
  implicit val facebookReadWrites = Json.writes[FacebookRead]
  implicit val facebookUserReads = Json.reads[FacebookUser]
  implicit val facebookUserWrites = Json.writes[FacebookUser]
  implicit val facebookMessagingReads = Json.reads[FacebookMessaging]
  implicit val facebookMessagingWrites = Json.writes[FacebookMessaging]
  implicit val facebookEntryReads = Json.reads[FacebookEntry]
  implicit val facebookEntryWrites = Json.writes[FacebookEntry]
  implicit val facebookWebhookRequestBodyReads = Json.reads[FacebookWebhookRequestBody]
  implicit val facebookWebhookRequestBodyWrites = Json.writes[FacebookWebhookRequestBody]

  implicit val messageMessage2Reads = Json.reads[MessageMessage2]
  implicit val messageMessage2Writes = Json.writes[MessageMessage2]
  implicit val messageResponseBodyReads = Json.reads[MessageResponseBody]
  implicit val messageResponseBodyWrites = Json.writes[MessageResponseBody]


}
