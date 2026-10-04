package tv.nova.app.player
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
class PlayerActivity:ComponentActivity(){
 private var player:ExoPlayer?=null
 override fun onCreate(s:Bundle?){super.onCreate(s); val url=intent.getStringExtra("url")?:return
  player=ExoPlayer.Builder(this).build().apply{setMediaItem(MediaItem.fromUri(url));prepare();playWhenReady=true}
  setContent{AndroidView(factory={PlayerView(it).apply{this.player=player}},modifier=Modifier.fillMaxSize())}
 }
 override fun onStop(){player?.release();player=null;super.onStop()}
}
