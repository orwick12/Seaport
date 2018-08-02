import java.util.*;

public class Thing  {

    protected boolean busyFlag;
    int index = 0;
    String name;
    int parent = 1;

    public Thing(){

    }

    public Thing(Scanner sc) {
        if (sc != null) {
            name = (sc.hasNext()) ? sc.next() : "<ERROR>";
            index = (sc.hasNextInt()) ? sc.nextInt() : 0;
            parent = (sc.hasNextInt()) ? sc.nextInt() : 0;
        }
    }

    public void setIndex(int i) {
        if (i > 0) {
            index = 1;
        }
    }

    public int getIndex() {
        return index;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setParent(int p) {
        if (p > 0) {
            parent = p;
        }
    }

    public int getParent() {
        return parent;
    }

    public String toString() {
        return name;
    }
}



