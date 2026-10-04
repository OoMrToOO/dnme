package tv.nova.app.data
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject
object ApiClient {
 fun health(base:String):Boolean=runCatching{(URL("${base.trimEnd('/')}/health").openConnection() as HttpURLConnection).apply{requestMethod="GET";connectTimeout=3000;readTimeout=3000}.responseCode==200}.getOrDefault(false)
 fun m3u(base:String,url:String):String{val c=URL("${base.trimEnd('/')}/api/v1/m3u/parse").openConnection() as HttpURLConnection;c.requestMethod="POST";c.doOutput=true;c.setRequestProperty("Content-Type","application/json");c.outputStream.use{it.write(JSONObject(mapOf("url" to url)).toString().toByteArray())};return c.inputStream.bufferedReader().readText()}
}
