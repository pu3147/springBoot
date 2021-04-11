package cc.fbksy.edu.springBoot.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairSyncLockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);//公平锁,先到优先获取；
        new DemoThread(lock,"A").start();
        new DemoThread(lock,"B").start();
        new DemoThread(lock,"C").start();
        new DemoThread(lock,"D").start();
        new DemoThread(lock,"E").start();

        System.out.println("MAIN OVER");

    }

    static class  DemoThread extends Thread{
        private Lock lock;
        private String name;

        public DemoThread(Lock lock,String name){
            this.lock = lock;
            this.name = name;
        }

        @Override
        public void run() {
           for(int index=0;index<10;index++){
               lock.lock();
               System.out.println(name+"\tget LOCK");
               try {
                   TimeUnit.SECONDS.sleep(3);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   lock.unlock();
               }
           }
        }
    }
}
