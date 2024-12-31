package com.java.CompletableFutrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 예외처리<p>
 * - exceptionally(Function)<p>
 * - handle(BiFunction)<p>
 */
public class _CompletableFuture03_Error {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("#######################################################################");
    // TODO: 비동기 처리에서 에러가 발생했을 때

    // 1. exceptionally(Function) - 에러를 받아서 처리할 수 있음
    boolean throwError1 = true;
    CompletableFuture<String> throwErrorFuture1 = CompletableFuture.supplyAsync(() -> {
      System.out.println("throwErrorFuture1: " + Thread.currentThread().getName());
      if (throwError1) {
        throw new IllegalArgumentException();
      }
      return "Success!";
    }).exceptionally(error -> {
      // error가 발생하면 처리
      System.out.println(error.getMessage());
      return "Error!"; // 디폴트 값을 반환 할 수 있음
    });

    System.out.println(throwErrorFuture1.get());
    System.out.println("#######################################################################");

    // 2. handle(BiFunction) - 정상적인 결과(result)와 에러(error)를 하나의 흐름에서 처리
    boolean throwError2 = false;
    CompletableFuture<String> throwErrorFuture2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("throwErrorFuture2: " + Thread.currentThread().getName());
      if (throwError2) {
        throw new IllegalArgumentException();
      }
      return "Success!";
    }).handle((result, error) -> {
      // 에러가 발생한 경우
      if (error != null) {
        System.out.println("Error occurred: " + error.getMessage());
        return "Processed result: Error!"; // 에러 발생 시 기본값 반환
      }
      // 정상적인 결과가 있는 경우
      return "Processed result: " + result; // 결과를 가공하여 반환
    });
    System.out.println(throwErrorFuture2.get());
    System.out.println("#######################################################################");
  }

}

