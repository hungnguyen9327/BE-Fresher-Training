package com.example.pubsubredis.service.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {

  public static List<String> messageList = new ArrayList<String>();

  private static final Logger LOGGER = LogManager.getLogger(RedisMessageSubscriber.class);

  @Override
  public void onMessage(final Message message, final byte[] pattern) {
    messageList.add(message.toString());
    LOGGER.info("Message received: " + new String(message.getBody()));
  }
}
