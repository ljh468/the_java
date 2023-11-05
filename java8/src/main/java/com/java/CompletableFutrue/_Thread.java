package com.java.CompletableFutrue;

public class _Thread {
  public static void main(String[] args) throws InterruptedException {
    //    MyThread myThread = new MyThread();
    //    myThread.start();

    // Runable 구현
    java.lang.Thread thread = new java.lang.Thread(() -> {

      //      // Thread.sleep(); -> 쓰레드를 재움
      //      try {
      //        Thread.sleep(1000L);
      //      } catch (InterruptedException e) {
      //        // 자는 도중에 누군가 깨우면 발생
      //        System.out.println("InterruptedException");
      //      }


      //      // Thread.interrupted(); -> 쓰레드를 깨움
      //      while (true) {
      //        System.out.println("Sub Thread: " + Thread.currentThread().getName());
      //        try {
      //          Thread.sleep(1000L);
      //        } catch (InterruptedException e) {
      //          // 자는 도중에 누군가 깨우면 return (종료)
      //          System.out.println("exit!");
      //          return;
      //        }
      //      }

      // Thread.join(); -> 다른 쓰레드를 기다림
      System.out.println("Sub Thread: " + java.lang.Thread.currentThread().getName());
      try {
        java.lang.Thread.sleep(3000L);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }

    });

    thread.start();

    System.out.println("Main Thread: " + java.lang.Thread.currentThread().getName());
    //    // 3초 정도 있다가 종료
    //    Thread.sleep(3000L);
    //    thread.interrupt();

    // 쓰레드가 끝날때까지 기다림 (3초 후)
    thread.join();
    System.out.println(thread + " is finished");

  }

  // Thread 상속
  //  static class MyThread extends Thread {
  //    @Override
  //    public void run() {
  //      System.out.println("Thread: " + Thread.currentThread().getName());
  //    }
  //  }
}