package com.bismark.general;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RateLimiter {

  private  int requestLimit;

  public int ratePerSecond;
  public int timeLimit;

  public RateLimiter(int ratePerSecond, int timeLimit){
    this.ratePerSecond = ratePerSecond;
    this.timeLimit = timeLimit;
    requestLimit = this.ratePerSecond * this.timeLimit;
  }

  public HitCounter hitCounter = new HitCounter();

  public boolean isAllow(long currTime) {
    return hitCounter.hit(currTime);
  }

  public class HitCounter {
    public ConcurrentLinkedQueue<Long> queue;
    public HitCounter() {
      queue = new ConcurrentLinkedQueue<>();
    }
    public boolean hit(long timestamp) {
      while (!queue.isEmpty() && timestamp - queue.peek() >= timeLimit) {
        queue.poll();
      }
      if (queue.size() < requestLimit) {
        queue.add(timestamp);
        return true;
      }
      return false;
    }
  }

  public static void main(String[] args) throws Exception{
    RateLimiter rateLimiter = new RateLimiter(2, 2);
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 1
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 2
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 3
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 4
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 5
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 6
    Thread.sleep(2000);
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 7
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 8
    System.out.println(rateLimiter.isAllow(System.currentTimeMillis())); // 9

  }

}
