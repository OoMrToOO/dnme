package tv.nova.app.data
object M3uParser {
 private val attr=Regex("(\\w[\\w-]*)=(\\\"[^\\\"]*\\\"|'[^']*'|[^\\s,]*)")
 fun parse(text:String):List<MediaItem>{
  val lines=text.removePrefix("\uFEFF").lines(); val out=mutableListOf<MediaItem>(); var pending:Map<String,String>?=null; var title=""
  for(raw in lines){ val l=raw.trim(); if(l.isEmpty()) continue
   if(l.startsWith("#EXTINF:")){ val comma=l.indexOf(','); val head=if(comma>=0)l.substring(0,comma) else l; title=if(comma>=0)l.substring(comma+1).trim() else "Unknown"; pending=attr.findAll(head).associate{it.groupValues[1] to it.groupValues[2].trim('\"', '\'')} }
   else if(!l.startsWith('#') && pending!=null){ val a=pending!!; out+=MediaItem("m3u-${out.size}",title.ifBlank{a["tvg-name"]?:"Unknown"},l,a["group-title"]?:("Uncategorized"),a["tvg-logo"],if((a["type"]?:("")).contains("movie",true))"movie" else "channel"); pending=null }
  }; return out
 }
}
