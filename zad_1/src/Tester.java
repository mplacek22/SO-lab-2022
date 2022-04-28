import java.util.ArrayList;
import java.util.Random;

public class Tester {
    public static void main(String[] args) {
        Procesy tester = new Procesy();

        Proces p1 = new Proces(5, 1, 0);
        Proces p2 = new Proces(5,0, 1);
        Proces p3 = new Proces(1, 2, 2);

        ArrayList<Proces> procesy = new ArrayList<>();
        procesy.add(p1);
        procesy.add(p2);
        procesy.add(p3);

        tester.RR(procesy,3);


        tester.getData(procesy,3);

        System.out.println("\nGenerator: ");

        ArrayList<Proces> arr1 = generate(10, 200);

        double fcfs = tester.FCFS(arr1);
        double sjf = tester.SJF(arr1);
        double rr = tester.RR(arr1, 3);

        System.out.println("\nStarvation: ");

        ArrayList<Proces> arr2 = new ArrayList<>();
        arr2.add(new Proces(50, 0, 0));
        arr2.add(new Proces(3, 400, 1));
        arr2.add(new Proces(500, 5, 2));
        arr2.add(new Proces(50000, 0, 3));
        arr2.add(new Proces(2, 10, 4));
        arr2.add(new Proces(1, 1000, 5));
        arr2.add(new Proces(3, 200, 6));

        tester.getData(arr2, 3);

        System.out.println("\nDifferent time slices: ");

        ArrayList<Proces> arr3 = new ArrayList<>();
        arr3.add(new Proces(500, 0, 0));
        arr3.add(new Proces(30, 0, 1));
        arr3.add(new Proces(500, 0, 2));
        arr3.add(new Proces(50, 0, 3));
        arr3.add(new Proces(30, 0, 4));
        arr3.add(new Proces(10, 0, 5));
        arr3.add(new Proces(30, 0, 6));

        tester.RR(arr3, 10);
        tester.RR(arr3, 50);
        tester.RR(arr3, 500);





    }

    public static ArrayList<Proces> generate (int nr, int max_size){
        Random generator = new Random();
        ArrayList<Proces> procesy = new ArrayList<>();
        for (int i = 0; i < nr; i++) {
            Proces proces = new Proces(generator.nextInt(max_size) + 1, generator.nextInt(20), i);
            procesy.add(proces);
        }
        return procesy;
    }
}
