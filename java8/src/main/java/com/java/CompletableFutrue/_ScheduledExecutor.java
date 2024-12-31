package com.java.CompletableFutrue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class _ScheduledExecutor {

  public static void main(String[] args) {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    // TODO: 1초뒤에 실행
    executorService.schedule(getRunnable("Hello"), 1, TimeUnit.SECONDS);

    // TODO: 반복해서 실행하려면? 최초 딜레이 2초 뒤 부터 2초간격으로 실행
    ScheduledFuture<?> scheduledTask = executorService.scheduleAtFixedRate(getRunnable("Java"),
                                                                           2,
                                                                           1,
                                                                           TimeUnit.SECONDS);

    // 10초 후 작업 중지
    executorService.schedule(() -> {
      System.out.println("Cancelling the scheduled task...");
      scheduledTask.cancel(true); // 실행 중인 작업도 중단
    }, 8, TimeUnit.SECONDS);

    // 12초 후 executorService 스레드 풀 종료
    executorService.schedule(() -> {
      System.out.println("Shutting down executor...");
      executorService.shutdown();
    }, 12, TimeUnit.SECONDS);
  }

  private static Runnable getRunnable(String message) {
    return () -> System.out.println(message + ": " + Thread.currentThread().getName());
  }
}
