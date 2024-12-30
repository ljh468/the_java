package com.java.CompletableFutrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _CompletableFuture02 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //   // CompletableFuture 2개가 연관이 있는 경우
    //   // 1
    //   CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
    //    System.out.println("Hello: " + Thread.currentThread().getName());
    //    return "Hello";
    //   });
    //
    //   // 3
    //   CompletableFuture<String> future = hello.thenCompose(_CompletableFuture02::getWorld);
    //   System.out.println(future.get());
    //
    //  }
    //
    //  // 2
    //  private static CompletableFuture<String> getWorld(String message) {
    //   return CompletableFuture.supplyAsync(() -> {
    //    System.out.println("World: " + Thread.currentThread().getName());
    //    return message + " World";
    //   });
    //  }

    //    // CompletableFuture 2개가 연관이 없는 경우
    //    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
    //      System.out.println("Hello: " + Thread.currentThread().getName());
    //      return "Hello";
    //    });
    //
    //    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
    //      System.out.println("World: " + Thread.currentThread().getName());
    //      return "World";
    //    });
    //
    //    //    CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
    //    //    System.out.println(future.get());
    //
    //    // 모든 쓰레드가 끝났을 때 결과를 받는 방법 (타입이 다르거나 예외가 생길 수 있다)
    //    List<CompletableFuture<String>> futures = List.of(hello, world);
    //    CompletableFuture[] futureArray = futures.toArray(new CompletableFuture[futures.size()]);
    //    CompletableFuture<List<String>> results = CompletableFuture.allOf(futureArray)
    //                                                               .thenApply(v -> futures.stream()
    //                                                                                      .map(CompletableFuture::join)
    //                                                                                      .toList());
    //    results.get().forEach(System.out::println);

    //    // 비동기 처리에서 에러가 발생하면?
    //    boolean throwError = true;
    //    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
    //      if (throwError) {
    //        throw new IllegalArgumentException();
    //      }
    //      System.out.println("Hello: " + Thread.currentThread().getName());
    //      return "Hello";
    //    }).exceptionally(ex -> {
    //      // error가 발생하면 처리
    //      System.out.println(ex);
    //      return "Error!";
    //    });
    //
    //    System.out.println(hello.get());

    // 정상, 에러 모두 사용가능
    boolean throwError = false;
    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      if (throwError) {
        throw new IllegalArgumentException();
      }
      System.out.println("Hello: " + Thread.currentThread().getName());
      return "Hello";
    }).handle((result, ex) -> {
      if (ex != null) {
        System.out.println(ex);
        return "ERROR!";
      }
      return result;
    });
    System.out.println(hello.get());
  }
}
