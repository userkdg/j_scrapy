package cn.kindg.jscrapy;

public class ActivemqCustomerTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (true){
                System.out.println();
            }
        },"消费").start();
        Thread.currentThread().join();

    }
}
