import java.util.ArrayList;

public class Process {
    private int id;
    private int size; //pages count
    private ArrayList<Page> calls;
    private int pageFails;

    public Process(int id, int size, int calls) {
        this.id = id;
        this.size = size;
        Generator generator = new Generator(size);
        this.calls = generator.generateCallsList(calls);
        pageFails = 0;
    }

    public void addPF(int x){
        pageFails += x;
    }

    public int getPageFails() {
        return pageFails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Page> getCalls() {
        return calls;
    }

    public void setCalls(ArrayList<Page> calls) {
        this.calls = calls;
    }

}
