package cc.fbksy.edu.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1");
            }
        }).start();

        Runnable runnable = () -> System.out.println("t2");
        new Thread(runnable).start();

        new Thread(() -> System.out.println("t3")).start();

        new Thread(() -> run("t4")).start();

        new Thread(() -> {
            String str = "t5";
            System.out.println(str);
        }).start();

    }

    private static void run(String str) {
        System.out.println(str);
    }
}
