const {URL}=require('url');
function cleanPortal(portal){return portal.replace(/\/$/,'');}
function headers(mac,token){return {'User-Agent':'Mozilla/5.0 (QtEmbedded; U; Linux; C)','X-User-Agent':'Model: MAG250; Link: WiFi','Cookie':`mac=${mac}; stb_lang=en; timezone=Europe/Istanbul`,...(token?{'Authorization':`Bearer ${token}`}:{})};}
async function call({portal,mac,token,action,params={}}){const u=new URL(`${cleanPortal(portal)}/server/load.php`);u.searchParams.set('type','stb');u.searchParams.set('action',action);for(const [k,v] of Object.entries(params))u.searchParams.set(k,String(v));const r=await fetch(u,{headers:headers(mac,token)});if(!r.ok)throw new Error(`stalker_http_${r.status}`);return r.json();}
async function handshake({portal,mac}){return call({portal,mac,action:'handshake',params:{token:''}});}
async function profile({portal,mac,token}){return call({portal,mac,token,action:'get_profile'});}
async function categories({portal,mac,token,type='itv'}){return call({portal,mac,token,action:'get_genres',params:{type}});}
async function channels({portal,mac,token,genre='*',p=1}){return call({portal,mac,token,action:'get_ordered_list',params:{type:'itv',genre,p,JsHttpRequest:'1-xml'}});}
async function vod({portal,mac,token,genre='*',p=1}){return call({portal,mac,token,action:'get_ordered_list',params:{type:'vod',genre,p,JsHttpRequest:'1-xml'}});}
async function series({portal,mac,token,genre='*',p=1}){return call({portal,mac,token,action:'get_ordered_list',params:{type:'series',genre,p,JsHttpRequest:'1-xml'}});}
async function createLink({portal,mac,token,cmd}){return call({portal,mac,token,action:'create_link',params:{cmd,JsHttpRequest:'1-xml'}});}
module.exports={handshake,profile,categories,channels,vod,series,createLink};
