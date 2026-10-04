function decodeXml(s=''){return s.replace(/<!\[CDATA\[([\s\S]*?)\]\]>/g,'$1').replace(/&amp;/g,'&').replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&quot;/g,'"').replace(/&#39;/g,"'");}
function tag(block,name){const m=block.match(new RegExp(`<${name}[^>]*>([\\s\\S]*?)</${name}>`,'i'));return m?decodeXml(m[1].trim()):'';}
function attr(block,name){const m=block.match(new RegExp(`<programme[^>]*\\b${name}=["']([^"']+)["']`,'i'));return m?m[1]:'';}
function parseDate(v){if(!v)return null;const m=v.match(/^(\d{14})(?:\s+([+-]\d{4}))?/);if(!m)return null;const d=m[1];const iso=`${d.slice(0,4)}-${d.slice(4,6)}-${d.slice(6,8)}T${d.slice(8,10)}:${d.slice(10,12)}:${d.slice(12,14)}${m[2]?m[2].slice(0,3)+':'+m[2].slice(3):'Z'}`;const t=Date.parse(iso);return Number.isNaN(t)?null:new Date(t).toISOString();}
function parseXmltv(xml){const out=[];const re=/<programme\b[\s\S]*?<\/programme>/gi;for(const b of xml.match(re)||[])out.push({channelId:attr(b,'channel'),title:tag(b,'title')||'Untitled',description:tag(b,'desc'),category:tag(b,'category'),startAt:parseDate(attr(b,'start')),endAt:parseDate(attr(b,'stop'))});return out;}
module.exports={parseXmltv};
