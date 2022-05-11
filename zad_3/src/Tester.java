import java.util.ArrayList;
import java.util.Random;

public class Tester {
    public static void main(String[] args) {
        int frames_nr = 20;
        int pages_nr = 40;
        int calls_length = 10000;

        ArrayList<Page> calls = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < calls_length; i++) {
            calls.add(new Page(r.nextInt(pages_nr), true, i));
        }

        Algorithms alg = new Algorithms(frames_nr, calls);
        System.out.println("FIFO: "+alg.FIFO());
        System.out.println("OPT: "+alg.OPT());
        System.out.println("LRU: "+alg.LRU());
        System.out.println("ALRU:"+alg.ALRU());
        System.out.println("RANDOM: "+alg.RANDOM());
    }
}
