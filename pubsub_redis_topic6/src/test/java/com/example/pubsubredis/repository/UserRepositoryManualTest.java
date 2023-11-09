package com.example.pubsubredis.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.pubsubredis.config.RedisConfiguration;
import com.example.pubsubredis.model.User;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.embedded.RedisServerBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfiguration.class)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringBootTest
public class UserRepositoryManualTest {

  @Autowired
  private UserRepository repo;

  private static redis.embedded.RedisServer redisServer;

  @BeforeClass
  public static void startRedisServer() throws IOException {
    redisServer = new RedisServerBuilder().port(6379).setting("maxmemory 128M").build();
    redisServer.start();
  }

  @AfterClass
  public static void stopRedisServer() throws IOException {
    redisServer.stop();
  }

  @Test
  public void whenSavingStudent_thenAvailableOnRetrieval() throws Exception {
    final User user = new User(
        "U20230911",
        "Hung Nguyen",
        "nguyenhung12c1@gmail.com"
    );
    repo.save(user);
    final User retrievedUser = repo.findById(user.getId()).get();
    assertEquals(user.getId(), retrievedUser.getId());
  }

  @Test
  public void whenUpdatingUser_thenAvailableOnRetrieval() throws Exception {
    final User user = new User(
        "U20230911",
        "Hung Nguyen",
        "nguyenhung12c1@gmail.com"
    );
    repo.save(user);
    user.setName("Hung V Nguyen");
    repo.save(user);
    final User retrievedUser = repo.findById(user.getId()).get();
    assertEquals(user.getName(), retrievedUser.getName());
  }

  @Test
  public void whenDeletingUser_thenNotAvailableOnRetrieval() throws Exception {
    final User user = new User(
        "U20230911",
        "Hung Nguyen",
        "nguyenhung12c1@gmail.com"
    );
    repo.save(user);
    repo.deleteById(user.getId());
    final User retrievedUser = repo.findById(user.getId()).orElse(null);
    assertNull(retrievedUser);
  }
}
