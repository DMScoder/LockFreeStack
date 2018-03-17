import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Damian Suski on 2/6/2018.
 */
public class MAIN extends Thread {

    static Stack stack = new Stack();
    static AtomicInteger threadsDone = new AtomicInteger(0);

    public static void main(String args[]){
        int passRate = 0;
        int failRate = 0;

        for(int testNum = 0; testNum < 25; testNum++) {
            int counter = 0;
            int threadCount = 100;

            for (int i = 0; i < threadCount; i++) {
                Thread thread = new MAIN();
                thread.start();
            }

            while (threadsDone.get() != threadCount) ;

            while (stack.pop() != null)
                counter++;

            if(counter != 1000 * threadCount)
                failRate++;
            else
                passRate++;

            threadsDone.set(0);
        }

        System.out.println(passRate+" tests passed");
        System.out.println(failRate+" tests failed");
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++)
            stack.push(i);

        threadsDone.incrementAndGet();
    }
}
