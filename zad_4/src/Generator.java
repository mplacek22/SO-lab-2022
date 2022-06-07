import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generator {
    private final int maxPageID;

    public Generator(int maxPageID) {
        this.maxPageID = maxPageID;
    }

    public ArrayList<Page> generateCallsList (int calls){
        ArrayList<Page> callsList = new ArrayList<>(calls);
        Random random = new Random();
        for (int i = 0; i < calls; i++) {
            if (i % 5 == 1 && i>5)
                callsList.add(callsList.get(1));
            else if (i % 5 == 2 && i>5)
                callsList.add(callsList.get(2));
            else
                callsList.add(new Page(random.nextInt(maxPageID)));
        }

        return callsList;
    }

}
