package com.java.CompletableFutrue;

import java.util.concurrent.*;

/**
 * 자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스.
 * - Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많았다.
 * <p>
 * Future로는 하기 어렵던 작업들
 * - Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
 * - 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
 * - 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록
 * <p>
 * 가져오기
 * - 예외 처리용 API를 제공하지 않는다.
 */

/**
 * 콜백 제공하기
 * - thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
 * - thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이)
 * - thenRun(Runnable): 리턴값 받지 다른 작업을 처리하는 콜백
 * - 콜백 자체를 또 다른 쓰레드에서 실행할 수 있다.
 */
public class _CompletableFuture01 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //    CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> {
    //      System.out.println("runFuture: " + Thread.currentThread().getName());
    //    });
    //    runFuture.get();

    //    // thenApply() - 리턴 값이 추가 처리 (결과값 있음)
    //    CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
    //      System.out.println("supplyFuture: " + Thread.currentThread().getName());
    //      return "Hello";
    //    }).thenApply((s) -> { // 콜백 함수를 추가 할 수 있음
    //      System.out.println("thenApply: " + Thread.currentThread().getName());
    //      return s.toUpperCase();
    //    });
    //    String upper = supplyFuture.get();
    //    System.out.println(upper);

    //    // thenAccept() - 리턴 값이 추가 처리 (결과값 없음)
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    CompletableFuture<Void> voidFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("voidFuture: " + Thread.currentThread().getName());
      return "Hello";
    }, executorService).thenAcceptAsync(s -> {
      System.out.println("thenAccept: " + Thread.currentThread().getName());
      System.out.println(s.toUpperCase());
    }, executorService);
    voidFuture.get();

    executorService.shutdown();

  }
}
