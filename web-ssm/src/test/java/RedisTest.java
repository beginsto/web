import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedisTest {

    static String contr = "106.14.133.119";
    public static Jedis getRedis(){
        Jedis jedis = new Jedis(contr);
        jedis.auth("P0o9i8u&...");
        return  jedis;
    }
    public static void main(String[] args){
        try{
            Jedis j = RedisTest.getRedis();
            String output;
            //j.set("context","world");
            //j.set("context","你好sssssss");
            //output = j.get("context");
            //System.out.println(output);
            List<Integer> list = new ArrayList();
            j.lpush("award",""+200);
            j.lpush("award",""+130);
            j.lpush("award",""+66);
            j.lpush("award",""+33);

            j.lpush("award",""+16);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
