package com.java.CompletableFutrue;

public class _Thread {

  public static void main(String[] args) {
    // TODO: 1. 상속을 이용한 쓰레드 생성
    MyThread myThread = new MyThread();
    myThread.start();

    // TODO: 2. 익명 객체를 이용한 쓰레드 생성 (람다 사용가능)
    Thread AnonymousThread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Anonymous: " + Thread.currentThread().getName());
      }
    });
    AnonymousThread.start();

    // TODO: 3. Runnable 인터페이스를 이용한 쓰레드 생성
    MyRunnable myRunnable = new MyRunnable();
    myRunnable.run();


    // 실행되는 쓰레드의 순서가 다를 수 있음
    System.out.println("MainThread: " + Thread.currentThread().getName());
  }

  // Thread 상속
  static class MyThread extends Thread {
    @Override
    public void run() {
      System.out.println("MyThread: " + Thread.currentThread().getName());
    }
  }

  // Runnable 인터페이스
  static class MyRunnable implements Runnable {
    @Override
    public void run() {
      System.out.println("MyRunnable: " + Thread.currentThread().getName());
    }
  }
}