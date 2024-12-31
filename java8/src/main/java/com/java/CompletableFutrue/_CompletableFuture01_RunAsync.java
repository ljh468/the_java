package com.java.CompletableFutrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스.<p>
 * - Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많았다.<p>
 * <br>
 * Future로는 하기 어렵던 작업들<p>
 * - Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.<p>
 * - 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.<p>
 * - 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기<p>
 * - 예외 처리용 API를 제공하지 않는다.<p>
 * <br>
 * 비동기로 작업 실행하기<p>
 * - 리턴값이 없는 경우: runAsync()<p>
 * - 리턴값이 있는 경우: supplyAsync()<p>
 * - 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다. (기본은 ForkJoinPool.commonPool())<p>
 * <br>
 * 콜백 제공하기<p>
 * - thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백<p>
 * - thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이)<p>
 * - thenRun(Runnable): 리턴값 받지 다른 작업을 처리하는 콜백<p>
 * - 콜백 자체를 또 다른 쓰레드에서 실행할 수 있다.<p>
 */

public class _CompletableFuture01_RunAsync {

  // 비동기로 작업 실행하기, 콜백 제공하기
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // TODO: 1. runAsync() - 리턴 타입이 없는 경우
    CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> {
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e) {
        System.out.println("e = " + e.getMessage());
      }
      System.out.println("runFuture: " + Thread.currentThread().getName());
    });
    // get()을 하지 않으면 main 쓰레드는 해당 Future를 기다리지 않고, 아래의 코드를 실행하고 종료됨
    runFuture.get();
    System.out.println();

    // TODO: 2. supplyAsync() - 리턴 값이 있는 경우
    CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("supplyFuture: " + Thread.currentThread().getName());
      return "Hello";
    });
    // get()의 반환값이 없으면 null을 리턴함
    String upper = supplyFuture.get();
    System.out.println(upper);
    System.out.println();

    // TODO: 2-1. 추가처리 가능
    // .thenApply() - 콜백함수 추가 가능 (리턴값 있음)
    // .thenAccept() - 콜백함수 추가 가능 (리턴값 없음)
    CompletableFuture<Void> voidFuture1 = CompletableFuture.supplyAsync(() -> {
      System.out.println("voidFuture1: " + Thread.currentThread().getName());
      return "Hello";
    }).thenApply((s) -> {
      System.out.println("thenApply: " + Thread.currentThread().getName());
      return s.toUpperCase();
    }).thenAccept((s) -> {
      System.out.println("thenAccept: " + Thread.currentThread().getName());
      System.out.println("thenAccept: " + s.toUpperCase());
    });
    Void voided1 = voidFuture1.get();
    System.out.println("voided1 = " + voided1);
    System.out.println();

    // TODO: 2-2. 원하는 쓰레드풀 사용
    // Executor를 사용하지 않고 기본적으로 ForkJoinPool의 commonPool에서 쓰레드를 가져와 사용하지만 원한다면 ExecutorService를 이용한 쓰레드풀을 이용해서 사용할 수 있음.
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    // 추가처리 가능
    // .thenApplyAsync(Functional, Executor) - 콜백함수 추가 가능 (리턴값 있음)
    // .thenAcceptAsync(Consumer, Executor) - 콜백함수 추가 가능 (리턴값 없음)
    CompletableFuture<Void> voidFuture2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("voidFuture2: " + Thread.currentThread().getName());
      return "Hello";
    }, executorService).thenApplyAsync((s) -> {
      System.out.println("thenApplyAsync: " + Thread.currentThread().getName());
      return s.toUpperCase();
    }, executorService).thenAcceptAsync(s -> {
      System.out.println("thenAcceptAsync: " + Thread.currentThread().getName());
      System.out.println("thenAcceptAsync: " + s.toUpperCase());
    }, executorService);
    Void voided2 = voidFuture2.get();
    System.out.println("voided2 = " + voided2);

    executorService.shutdown();

  }
}
