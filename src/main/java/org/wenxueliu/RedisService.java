package org.wenxueliu;

import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import redis.clients.jedis.Jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisService {

    static final Logger log = LoggerFactory.getLogger(RedisService.class);
    Random rd = new Random();

    String genRandomString(int len) {
        byte[] randbytes = new byte[len];
        rd.nextBytes(randbytes);
        return new String(randbytes);
    }

    HashMap<String, String> genHashMap(int len, int num) {
        HashMap<String, String> map = new HashMap<>(num + 1);
        for (int i = 0; i < num; i++) {
            String value = genRandomString(len);
            String key = value;
            map.put(key, value);
        }
        return map;
    }

    void runHashMap(int len, int num) {
        Jedis jedis = new Jedis("10.9.1.44");
        String ret = jedis.select(len);
        log.info(String.format("select db %d return %s", len, ret));
        HashMap<String, String> map = this.genHashMap(len, num);
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        long begin = System.currentTimeMillis();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            jedis.set(entry.getKey(), entry.getValue());
        }
        long end = System.currentTimeMillis();
        log.info(String.format("insert %d to db %d with db size %d took %d ms", num, len, jedis.dbSize(), end - begin));
        jedis.flushDB();
        log.info(String.format("after flushdb, db size %d", jedis.dbSize()));
        jedis.close();
    }

    void withoutPipeline() {
        runHashMap(10, 10000);
        runHashMap(20, 10000);
        runHashMap(50, 10000);
        runHashMap(100, 10000);

        //runHashMap(10, 1000000);
        //runHashMap(10, 2000000);
        //runHashMap(10, 4000000);
        //runHashMap(10, 6000000);
        //runHashMap(10, 8000000);
        //runHashMap(10, 10000000);
        //runHashMap(10, 20000000);
    }
}
