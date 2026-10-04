const {Pool}=require('pg');
const pool=new Pool({connectionString:process.env.DATABASE_URL||'postgresql://nova:nova@localhost:5432/nova'});
module.exports={pool};
