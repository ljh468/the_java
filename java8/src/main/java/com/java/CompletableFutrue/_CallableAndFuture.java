package com.java.CompletableFutrue;

import java.util.List;
import java.util.concurrent.*;

public class _CallableAndFuture {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    /**
     * Callable은 결과값을 리턴받을 수 있음
     * 리턴 받은 값이 Future
     */
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Callable<String> jaehoon = () -> {
      Thread.sleep(1000L);
      return "jaehoon";
    };

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "java";
    };

    // 스레드 중 하나가 끝나면 리턴 (invokeAny)
    String future = executorService.invokeAny(List.of(jaehoon, hello, java));
    System.out.println(future);

    //    // 모든 스레드가 끝날때까지 기다림 (invokeAll)
    //    List<Future<String>> futures = executorService.invokeAll(List.of(jaehoon, hello, java));
    //    for (Future<String> future : futures) {
    //      System.out.println(future.get());
    //    }
    //    executorService.shutdown();

    //    // future cancel
    //    Future<String> helloFuture = executorService.submit(hello);
    //    System.out.println("isDone: " + helloFuture.isDone());
    //    System.out.println("Started!");
    //    helloFuture.cancel(true); // 현재 진행중인 작업을 종료 (false는 기다림)
    //
    //    //    String get = helloFuture.get(); // 블로킹 콜
    //    //    System.out.println(get);
    //
    //    // System.out.println("isDone: " + helloFuture.isDone()); // cancel을 하면 무조건 ture가 됨
    System.out.println("Ended!");
    executorService.shutdown();
  }
}
