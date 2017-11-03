package org.wenxueliu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App
{
    static final Logger log = LoggerFactory.getLogger(App.class);
    private App() {
        throw new IllegalAccessError("Utility class");
    }

    public static void main( String[] args )
    {
        log.info( "Hello World!" );
        RedisService s = new RedisService();
        s.withoutPipeline();
    }
}
