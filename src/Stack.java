import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Damian Suski on 2/6/2018.
 *
 */
public class Stack<T> {

    AtomicReference <Node> head;
    AtomicInteger numOps;
    AtomicInteger size;

    public Stack(){
        head = new AtomicReference<>();
        numOps = new AtomicInteger(0);
        size = new AtomicInteger(0);
    }

    public boolean push(T x){
        Node n = new Node(x);

        while(true){

            Node oldTop = head.get();
            n.next = oldTop;

            if(head.compareAndSet(oldTop,n)){
                size.incrementAndGet();
                numOps.incrementAndGet();
                return true;
            }
        }
    }

    public T pop(){
        while(true){
            if(head.get() == null)
                return null;

            Node oldHead = head.get();
            Node newHead = head.get().next;

            if(head.compareAndSet(oldHead,newHead)){
                size.incrementAndGet();
                numOps.incrementAndGet();
                return oldHead.val;
            }
        }
    }

    public int getSize(){
        return size.get();
    }

    public int getNumOps(){
        return numOps.get();
    }

    public ArrayList<Node> pregenerateNodes(){
        ArrayList<Node> nodes = new ArrayList<>();

        for(int i=0;i<1000;i++){
            nodes.add(new Node((T) new Integer(0)));
        }

        return nodes;
    }

    class Node{
        T val;
        Node next;

        Node(T val){
            this.val = val;
        }
    }
}
