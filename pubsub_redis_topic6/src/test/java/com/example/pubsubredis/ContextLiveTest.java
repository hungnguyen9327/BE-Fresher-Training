package com.example.pubsubredis;

import static org.junit.Assert.assertTrue;

import com.example.pubsubredis.service.queue.RedisMessageSubscriber;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import redis.embedded.RedisServerBuilder;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = PubsubRedisTopic6App.class
)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class ContextLiveTest {
  private static redis.embedded.RedisServer redisServer;

  @BeforeAll
  public static void startRedisServer() {
    redisServer = new RedisServerBuilder().port(6379).setting("maxmemory 256M").build();
    redisServer.start();
  }

  @AfterAll
  public static void stopRedisServer() {
    redisServer.stop();
  }
  @Test
  public void test() {
  }
}
