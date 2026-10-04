const {parseM3U}=require('../lib/m3u');
async function loadM3U(url){const r=await fetch(url,{headers:{'User-Agent':'NOVA-TV/1.0'}});if(!r.ok)throw new Error(`m3u_http_${r.status}`);return parseM3U(await r.text());}
module.exports={loadM3U};
