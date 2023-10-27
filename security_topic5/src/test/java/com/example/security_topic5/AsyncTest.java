package com.example.security_topic5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AsyncTest {

  public Future<String> calculateAsync() throws InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    Executors.newCachedThreadPool().submit(() -> {
      Thread.sleep(500);
      completableFuture.complete("Hello");
      return null;
    });

    return completableFuture;
  }

  @Test
  void test() throws ExecutionException, InterruptedException {
    String name = "hihi";

    CompletableFuture<String> completableFuture
        = CompletableFuture.supplyAsync(() -> {
      if (name == null) {
        throw new RuntimeException("Computation error!");
      }
      return "Hello, " + name;
    }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
    System.out.println(completableFuture.get());
  }
}
