const crypto=require('crypto');
function md5(s){return crypto.createHash('md5').update(s).digest('hex');}
function norm(url){return url.replace(/\/$/,'');}
async function stalkerFetch(portal,path,{mac,token}={}){
  const headers={'User-Agent':'Mozilla/5.0 (MAG250)','X-User-Agent':'Model: MAG250; Link: WiFi','Cookie':`mac=${encodeURIComponent(mac)}; stb_lang=en; timezone=Europe/Istanbul`};
  if(token) headers.Authorization=`Bearer ${token}`;
  const r=await fetch(`${norm(portal)}/portal.php${path}`,{headers});
  if(!r.ok) throw new Error(`Stalker HTTP ${r.status}`); return r.json();
}
async function handshake({portal,mac,serial}){
  const j=await stalkerFetch(portal,`?type=stb&action=handshake&JsHttpRequest=1-xml`,{mac});
  return {token:j.js?.token||null,random:j.js?.random||null,mac,serial:serial||md5(mac).slice(0,13)};
}
async function profile({portal,mac,token}){return stalkerFetch(portal,'?type=stb&action=get_profile&JsHttpRequest=1-xml',{mac,token});}
async function categories({portal,mac,token,kind='itv'}){return stalkerFetch(portal,`?type=${kind}&action=get_genres&JsHttpRequest=1-xml`,{mac,token});}
async function streams({portal,mac,token,kind='itv'}){return stalkerFetch(portal,`?type=${kind}&action=get_all_channels&JsHttpRequest=1-xml`,{mac,token});}
module.exports={handshake,profile,categories,streams};
