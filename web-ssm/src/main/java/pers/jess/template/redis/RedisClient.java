package pers.jess.template.redis;

import redis.clients.jedis.Jedis;

public class RedisClient {
    private static String server = "127.0.0.1";

    public static Jedis getRedis(){

        Jedis jedis = new Jedis(server);
        jedis.auth("N8WzW5bOvLCVmT9gyS3v");
        return  jedis;
    }

    public static void main(String[] args){
        Jedis jedis = RedisClient.getRedis();
        for(int i = 0; i < 300; i++){
            System.out.println(jedis.get("award_version"));
        }
    }
}
