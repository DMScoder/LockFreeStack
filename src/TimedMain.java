import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Damian Suski on 2/6/2018.
 */
public class TimedMain extends Thread {

    static Stack stack = new Stack();
    static AtomicInteger threadsDone = new AtomicInteger(0);
    private ArrayList<Stack.Node> nodes;
    static AtomicBoolean beginTest;
    final static int popPercent = 10;
    final static int pushPerccent = 10;
    final static int sizePercent = 80;

    public static void main(String args[]){

        if(args.length!=1){
            System.out.println("Incorrect amount of parameters");
            return;
        }

            //int threadCount = Integer.parseInt(args[0]);
            int threadCount = 1;
            threadsDone = new AtomicInteger(0);
            beginTest = new AtomicBoolean(false);
            stack = new Stack();

            for (int i = 0; i < threadCount; i++) {
                Thread thread = new MAIN();
                thread.start();
            }

            while (threadsDone.get() != threadCount);


    }

    @Override
    public void run() {
        nodes = stack.pregenerateNodes();

        threadsDone.getAndIncrement();

        threadsDone.incrementAndGet();
    }
}
