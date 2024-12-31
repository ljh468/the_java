package com.java.CompletableFutrue;

import java.util.List;
import java.util.concurrent.*;

/**
 * Callable은 결과값을 리턴받을 수 있음<p>
 * 리턴 받은 값이 Future<p>
 */
public class _Invoke {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "java";
    };

    Callable<String> jaehoon = () -> {
      Thread.sleep(1000L);
      return "jaehoon";
    };

    System.out.println("#### Start!! ####");

    // TODO: invokeAll() - 모든 스레드를 묶어서 보낼 수 있음 (모두 끝날때까지 기다렸다가 결과를 리스트로 반환)
    List<Future<String>> futures = executorService.invokeAll(List.of(hello, java, jaehoon)); // 쓰레드가 할당되는 순서는 앞에서 부터
    for (Future<String> future : futures) {
      System.out.println(future.get());
    }

    // TODO: invokeAny() - 스레드 중 하나가 끝나면 반환
    String future = executorService.invokeAny(List.of(hello, java, jaehoon));
    System.out.println(future);

    System.out.println("#### End!! ####");
    executorService.shutdown();
  }
}
