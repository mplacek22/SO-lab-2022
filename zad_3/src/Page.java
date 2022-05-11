import java.util.Comparator;

public class Page {
    private int nr;
    private boolean parityBit;
    private int id;

    public Page(int nr, boolean parityBit, int id) {
        this.nr = nr;
        this.parityBit = parityBit;
        this.id = id;
    }

    public Page(Page page){
        this.nr = page.getNr();
        this.parityBit = isParityBit();
        this.id = page.getId();
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public boolean isParityBit() {
        return parityBit;
    }

    public void setParityBit(boolean parityBit) {
        this.parityBit = parityBit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Comparator<Page> id_comparator = new Comparator<Page>() {
        @Override
        public int compare(Page o1, Page o2) {
            return o1.id-o2.id;
        }
    };
}
