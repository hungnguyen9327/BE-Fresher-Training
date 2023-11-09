package com.example.pubsubredis.service.queue;

public interface MessagePublisher {
  void publish(final String msg);
}
