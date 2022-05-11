import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AlgorithmsTest {
    Algorithms algorithms;

    @BeforeEach
    void setUp(){

        ArrayList<Page> calls = new ArrayList<>();
        calls.add(new Page(1, true, 0));
        calls.add(new Page(2, true, 1));
        calls.add(new Page(3, true, 2));
        calls.add(new Page(4, true, 3));
        calls.add(new Page(1, true, 4));
        calls.add(new Page(2, true, 5));
        calls.add(new Page(5, true, 6));
        calls.add(new Page(1, true, 7));
        calls.add(new Page(2, true, 8));
        calls.add(new Page(3, true, 9));
        calls.add(new Page(4, true, 10));
        calls.add(new Page(5, true, 11));


        algorithms = new Algorithms(4, calls);
    }

    @Test
    public void FIFO(){
        assertEquals(10, algorithms.FIFO());
    }

    @Test
    public void OPT(){
        assertEquals(6, algorithms.OPT());
    }

    @Test
    public void LRU(){
        assertEquals(8, algorithms.LRU());
    }

    @Test
    public void ALRU(){
        assertEquals(10, algorithms.ALRU());
    }






}