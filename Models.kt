package tv.nova.app.data

data class MediaItem(val id:String,val title:String,val url:String,val group:String="",val logo:String?=null,val type:String="channel")
data class EpgProgram(val channelId:String,val title:String,val start:Long,val end:Long,val description:String="")
