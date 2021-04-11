package cc.fbksy.edu.springBoot.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest2 {

    public static void main(String[] args) {
        Lock lockA = new ReentrantLock(false);//公平锁,先到优先获取；
        Lock lockB = new ReentrantLock();//默认非公平

        new DemoThread(lockA,lockB,"A").start();
        new DemoThread(lockB,lockA,"B").start();

        System.out.println("MAIN OVER");

    }

    static class  DemoThread extends Thread{
        private Lock lockA;
        private Lock lockB;

        private String name;

        public DemoThread(Lock lockA,Lock lockB,String name){
            this.lockA = lockA;
            this.lockB = lockB;
            this.name = name;
        }

        @Override
        public void run() {
           for(int index=0;index<100;index++){

               try {
                   lockA.lockInterruptibly();
                   lockB.lockInterruptibly();

                   System.out.println(name+"\tget LOCK");
                   TimeUnit.SECONDS.sleep(3);

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   lockB.unlock();
                   lockA.unlock();
               }
           }
        }
    }
}
