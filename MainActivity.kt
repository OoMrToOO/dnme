package tv.nova.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tv.nova.app.data.MediaItem
import tv.nova.app.player.PlayerActivity

class MainActivity:ComponentActivity(){
 override fun onCreate(s:Bundle?){super.onCreate(s);setContent{NovaApp{url->startActivity(Intent(this,PlayerActivity::class.java).putExtra("url",url))}}}
}

@Composable fun NovaApp(play:(String)->Unit){
 MaterialTheme(colorScheme=darkColorScheme()){
  var tab by remember{mutableStateOf("HOME")}; var showAdd by remember{mutableStateOf(false)}; var sourceType by remember{mutableStateOf("M3U")}; var sourceValue by remember{mutableStateOf("")}
  val demo=remember{listOf(MediaItem("1","BBC One","https://example.com/bbc.m3u8","News"),MediaItem("2","CNN","https://example.com/cnn.m3u8","News"),MediaItem("3","Featured Movie","https://example.com/movie.m3u8","Movies",type="movie"))}
  Surface(Modifier.fillMaxSize(),Color(0xFF08090D)){Column(Modifier.padding(42.dp)){
   Row(verticalAlignment=Alignment.CenterVertically){Text("NOVA",fontSize=32.sp);Spacer(Modifier.width(50.dp));listOf("HOME","LIVE TV","MOVIES","SERIES","FAVORITES").forEach{t->Text(t,color=if(tab==t)Color.White else Color.Gray,fontSize=16.sp,modifier=Modifier.padding(12.dp).clickable{tab=t}.focusable())};Spacer(Modifier.weight(1f));Button(onClick={showAdd=true}){Text("ADD SOURCE")}}
   Spacer(Modifier.height(32.dp));
   when(tab){"LIVE TV"->MediaSection("LIVE TV",demo.filter{it.type=="channel"},play);"MOVIES"->MediaSection("MOVIES",demo.filter{it.type=="movie"},play);"SERIES"->MediaSection("SERIES",emptyList(),play);"FAVORITES"->MediaSection("FAVORITES",demo.take(2),play);else->{Hero();MediaSection("CONTINUE WATCHING",demo,play);MediaSection("LIVE NOW",demo.filter{it.type=="channel"},play);MediaSection("MOVIES",demo.filter{it.type=="movie"},play)}}
  }}
  if(showAdd) AlertDialog(onDismissRequest={showAdd=false},title={Text("Add source")},text={Column{Row{FilterChip(sourceType=="M3U",{sourceType="M3U"},{Text("M3U")});Spacer(Modifier.width(8.dp));FilterChip(sourceType=="STALKER",{sourceType="STALKER"},{Text("STALKER")})};Spacer(Modifier.height(12.dp));if(sourceType=="M3U"){OutlinedTextField(sourceValue,{sourceValue=it},label={Text("M3U / M3U8 URL")},singleLine=true)}else{OutlinedTextField(sourceValue,{sourceValue=it},label={Text("Portal URL")},singleLine=true);Spacer(Modifier.height(8.dp));Text("MAC address will be requested next.",color=Color.Gray)}}},confirmButton={Button(onClick={showAdd=false}){Text("SAVE")}},dismissButton={TextButton(onClick={showAdd=false}){Text("CANCEL")}})
 }
}
@Composable fun Hero(){Box(Modifier.fillMaxWidth().height(260.dp).background(Color(0xFF20242D)),contentAlignment=Alignment.CenterStart){Column(Modifier.padding(32.dp)){Text("YOUR TV. YOUR LIBRARY.",fontSize=30.sp);Text("Netflix-style discovery with powerful IPTV playback.",color=Color.LightGray);Spacer(Modifier.height(20.dp));Button(onClick={}){Text("EXPLORE")}}}}
@Composable fun MediaSection(title:String,items:List<MediaItem>,play:(String)->Unit){if(items.isEmpty())return;Column{Text(title,fontSize=21.sp);Spacer(Modifier.height(12.dp));LazyRow(horizontalArrangement=Arrangement.spacedBy(14.dp)){items(items){item->Card(onClick={play(item.url)},modifier=Modifier.width(190.dp).height(112.dp).focusable()){Box(Modifier.fillMaxSize(),contentAlignment=Alignment.Center){Text(item.title,maxLines=2)}}}};Spacer(Modifier.height(28.dp))}}
