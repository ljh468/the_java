package com.java.CompletableFutrue;

import java.util.concurrent.*;

public class _Callable {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Callable<String> hello = () -> {
      Thread.sleep(5000L);
      return "Hello";
    };

    Future<String> future = executorService.submit(hello);
    System.out.println("#### Start!! ####");

    // 끝났으면 true, 아니면 false
    System.out.println("future.isDone() = " + future.isDone());

    // 진행중인 쓰레드 작업을 취소할 수 있음
    // true는 진행중인 작업을 종료, false는 기다렸다가 작업이 마치면 종료 - cancle을 하면 get()으로 가져올 수 없음, isDone은 true로 바뀜
    // future.cancel(true);

    // 끝나서 결과값이 나올때까지 기다림 (블로킹 콜)
    String result = future.get();
    System.out.println("result = " + result);
    System.out.println("future.isDone() = " + future.isDone());

    System.out.println("#### End!! ####");
  }
}
