package com.java.CompletableFutrue;

public class _ThreadMethod {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
      synchronized (Thread.class){
        try {
          // 다른 쓰레드에게 CPU 자원을 양보하지만 락은 풀지 않음
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          System.out.println("interrupted.!!");
          // TODO: 종료할지, 다른 작업을 할지 코딩하기 나름

          return; // return으로 끝나게 함
        }
      }
      System.out.println("Anonymous: " + Thread.currentThread().getName());
    });

    // 쓰레드 시작
    thread.start();

    // interrupt(), 해당 쓰레드를 인터럽트함
    // thread.interrupt();

    // 메인 쓰레드
    System.out.println("Main Thread: " + Thread.currentThread().getName());

    // join(), 해당쓰레드가 끝날때까지 기다림
    thread.join();

  }
}