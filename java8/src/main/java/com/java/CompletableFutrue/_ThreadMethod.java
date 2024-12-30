package com.java.CompletableFutrue;

public class _ThreadMethod {

  public static void main(String[] args) throws InterruptedException {
    Object lock = new Object();

    // 첫 번째 스레드: 락을 획득하고 sleep
    Thread t1 = new Thread(() -> {
      synchronized (lock){
        System.out.println("Thread 1: 락 획득");
        try {
          // 다른 쓰레드에게 CPU 자원을 양보하지만 락은 풀지 않음
          Thread.sleep(3000L);
        } catch (InterruptedException e) {
          System.out.println("interrupted.!!");
          // TODO: 종료할지, 다른 작업을 할지 코딩하기 나름
          return; // return으로 끝나게 할 수 있음
        }
        System.out.println("Thread 1: 락 해제");
      }
      System.out.println("Anonymous: " + Thread.currentThread().getName());
    });

    // 두 번째 스레드: 락을 획득하려고 시도
    Thread t2 = new Thread(() -> {
      long startTime = System.currentTimeMillis();
      synchronized (lock) {
        System.out.println("Thread 2: 락 획득");
        System.out.println("Thread 2: 실행 시간: " + (System.currentTimeMillis() - startTime) + "ms");
      }
    });




    // t1 쓰레드 시작
    t1.start();
    Thread.sleep(1000); // 첫 번째 스레드가 sleep 상태가 되도록 잠시 대기
    // t2 쓰레드 시작
    t2.start();

    // interrupt(), 해당 쓰레드를 인터럽트함
    // thread.interrupt();

    // 메인 쓰레드
    System.out.println("Main Thread: " + Thread.currentThread().getName());

    // join(), 해당쓰레드가 끝날때까지 기다림
    t1.join();
    t2.join();
  }
}