package com.java.CompletableFutrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _ExecutorService {
  /**
   * Executors<p>
   * - 고수준 (High-Level) Concurrency 프로그래밍<p>
   * - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리.<p>
   * - 그런 기능을 Executors에게 위임.<p>
   * <br>
   * Executors가 하는 일<p>
   * - 쓰레드 만들기: 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.<p>
   * - 쓰레드 관리: 쓰레드 생명 주기를 관리한다.<p>
   * - 작업 처리 및 실행: 쓰레드로 실행할 작업을 제공할 수 있는 API를 제공한다.<p>
   * <br>
   * 주요 인터페이스<p>
   * - Executor: execute(Runnable)<p>
   * - ExecutorService: Executor 상속 받은 인터페이스로, Callable도 실행할 수 있으며,<p>
   * - Executor를 종료 시키거나, 여러 Callable을 동시에 실행하는 등의 기능을 제공한다.<p>
   * - ScheduledExecutorService: ExecutorService를 상속 받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.<p
   */

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // ExecutorService executorService = Executors.newSingleThreadExecutor();
    // executorService.execute(() -> System.out.println("Thread: " + Thread.currentThread().getName()));

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    // executorService는 작업을 실행하고 다음 작업이 들어올때 까지 대기함
    // 프로세스가 죽지 않음
    executorService.submit(getRunnable("Hello"));
    executorService.submit(getRunnable("World"));
    executorService.submit(getRunnable("Lee"));
    executorService.submit(getRunnable("Jaehoon"));

    // submit을 이용하면 반환값을 받을 수 있지만, Runable이라서 null값을 반환
    Future<?> theJava = executorService.submit(getRunnable("The Java"));
    System.out.println("theJava.get() = " + theJava.get());
    

    // executorService 종료
    // 다음 작업이 들어올때까지 계속 대기함, 그래서 shutdown 해줘야함 (모든 작업을 끝내고 shutdown)
    executorService.shutdown();

    // 바로 shutdown 할 수 도 있음
    // executorService.shutdownNow();
  }

  private static Runnable getRunnable(String message) {
    return () -> System.out.println(message + ": " + Thread.currentThread().getName());
  }
}