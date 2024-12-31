package com.java.CompletableFutrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * 조합하기<p>
 * - thenCompose(): 두 작업이 서로 이어서 실행하도록 조합<p>
 * - thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행<p>
 * - allOf(): 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행<p>
 * - anyOf(): 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행<p>
 */
public class _CompletableFuture02_Compose {

  // CompletableFuture1
  private static CompletableFuture<String> getWorld(String message) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("World: " + Thread.currentThread().getName());
      return message + " World";
    });
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // TODO: CompletableFuture 2개가 연관이 있는 경우에 조합
    // CompletableFuture1
    CompletableFuture<String> hello1 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello: " + Thread.currentThread().getName());
      return "Hello";
    });

    // thenCompose(): 연관된 두 작업이 서로 이어서 실행하고 조합 (CompletableFuture1 + CompletableFuture2)
    CompletableFuture<String> future1 = hello1.thenCompose(_CompletableFuture02_Compose::getWorld);
    System.out.println("future1.get() = " + future1.get());
    System.out.println("#######################################################################");

    // TODO: CompletableFuture 2개가 연관이 없는 경우에 조합
    CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello: " + Thread.currentThread().getName());
      return "Hello";
    });

    CompletableFuture<String> world2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("World: " + Thread.currentThread().getName());
      return "World";
    });

    // thenCombine(): 연관되지 않은 두 작업이 서로 따로 실행하고 조합
    CompletableFuture<String> future2 = hello2.thenCombine(world2, (h, w) -> h + " " + w);
    System.out.println("future2.get() = " + future2.get());
    System.out.println("#######################################################################");

    // TODO: CompletableFuture가 여러개 인 경우에 조합
    // allOf() - 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
    // anyOf() - 여러 작업중에 가장 빨리 끝난 하나의 결과에 콜백 실행

    // 모든 쓰레드가 끝났을 때 리스트로 결과를 받는 방법 (타입이 다르거나 예외가 생길 수 있는 경우 사용)
    CompletableFuture<Integer> java2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Java: " + Thread.currentThread().getName());
      return 100;
    });

    // List<CompletableFuture> futures = List.of(hello2, world2, java2);
    // CompletableFuture[] futureArray = futures.toArray(new CompletableFuture[0]); // 빈 배열을 넘기면, 내부적으로 컬렉션 크기만큼 새로운 배열을 생성
    CompletableFuture[] futureArray = {hello2, world2, java2};
    CompletableFuture<List<Object>> listCompletableFuture =
        CompletableFuture.allOf(futureArray)
                         .thenApply(v -> Arrays.stream(futureArray)
                                               .map(CompletableFuture::join) // CompletableFuture 작업의 결과를 가져오는 동기적 메서드 (Unchecked Exception)
                                               .collect(Collectors.toList())); // 결과값을 모아서 리스트로 반환
    System.out.println("listCompletableFuture.get() = " + listCompletableFuture.get());

    CompletableFuture<Void> voidCompletableFuture =
        CompletableFuture.anyOf(futureArray)
                         .thenAccept((s) -> {
                           // 먼저 끝난 결과만 처리
                           System.out.println("AnyOf: " + Thread.currentThread().getName());
                           System.out.println("AnyOf(s) = " + s);
                         });
    System.out.println("voidCompletableFuture.get() = " + voidCompletableFuture.get());
    System.out.println("#######################################################################");
  }
}
