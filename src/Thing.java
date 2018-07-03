import java.util.*;

public class Thing implements Comparable<Thing>  {

    int index = 0;
    String name;
    int parent = 1;

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
        name = n;
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

    @Override
    public int compareTo(Thing o) {
        boolean comp =
                (o.getIndex() == this.getIndex()) &&
                        (o.getName().equals(this.getName())) &&
                        (o.getParent() == this.getParent());
        return (comp) ? 1 : 0;
    }
}



