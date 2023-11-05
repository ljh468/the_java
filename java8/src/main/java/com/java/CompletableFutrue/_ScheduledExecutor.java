package com.java.CompletableFutrue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _ScheduledExecutor {

  public static void main(String[] args) {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    // 1초 이후 실행, 2초 주기로 실행
    executorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
  }

  private static Runnable getRunnable(String message) {
    return () -> {
      System.out.println(message + ": " + Thread.currentThread().getName());
    };
  }
}
