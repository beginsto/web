public class LiftOff  implements Runnable{

    protected int countDown = 10000;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){
        System.out.println("线程启动中...");
    }
    public LiftOff(int countDown){
        this.countDown = countDown;
    }
    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff") + ").";
    }

    public void run(){
        while (countDown-- > 0){
            System.out.println(status());
            Thread.yield();
        }
        System.out.println("线程已完结...");
    }
}
