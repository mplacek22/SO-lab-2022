import java.util.Comparator;

public class Page {
    private int id;
    private boolean parityBit;
    private int lastUse;

    public Page(int nr, boolean parityBit) {
        this.id = nr;
        this.parityBit = parityBit;
    }

    public Page(int nr) {
        this.id = nr;
    }

    public Page(Page page){
        this.id = page.getId();
        this.parityBit = isParityBit();
        this.lastUse = page.getLastUse();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isParityBit() {
        return parityBit;
    }

    public void setParityBit(boolean parityBit) {
        this.parityBit = parityBit;
    }

    public int getLastUse() {
        return lastUse;
    }

    public void setLastUse(int lastUse) {
        this.lastUse = lastUse;
    }

    public static Comparator<Page> idComparator = new Comparator<Page>() {
        @Override
        public int compare(Page o1, Page o2) {
            return o1.lastUse -o2.lastUse;
        }
    };
}
