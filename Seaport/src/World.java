import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;


public class World extends Thing {

    ArrayList<Seaport> ports;
    PortTime time;

    //Constructor
    public World(Scanner sc) {
        super(sc);
        ports = new ArrayList<>();
    }

    public ArrayList<Seaport> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<Seaport> p) {
        ports = p;
    }

    public PortTime getTime() {
        return time;
    }

    public void setTime(PortTime t) {
        time = t;
    }

    void process(String st) {

        Scanner sc = new Scanner(st);
        while(sc.hasNext()) {System.out.println("switch");
            String lineOfText = sc.nextLine();
            if (lineOfText.startsWith("//")) {
                System.out.println("skip LIne");
                    continue;
                }
            System.out.println ("Processing >" + st + "<");
            if (!sc.hasNext()) return;
            switch (sc.next()) {

                case "port":
                    System.out.println("start");
                    assignPort(new Seaport(sc));
                    break;
                case "dock":
                    assignDock(new Dock(sc));
                    break;
                case "pship":
                    assignShip(new PassengerShip(sc));

                    break;
                case "cship":
                    assignShip(new CargoShip(sc));
                    break;
                case "person":
                    assignPerson(new Person(sc));
                    break;
                //case "job":
                  //  addJob(sc);
                    //break;
                default:
                    break;
            }
        }
    }
    Ship getShip (int x) {
        for (Seaport msp: ports)
            for (Ship ms: msp.ships)
                if (ms.index == x)
                    return ms;
        return null;
    }
    Dock getDock(int x)
    {
        for (Seaport msp: ports)
            for (Dock ms: msp.docks)
                if (ms.index == x)
                    return ms;
        return null;
    }
    Seaport getSeaport(int x) {
        for (Seaport msp: ports)
            if(msp.index==x)
                return msp;
        return null;
    }

    public void assignShip(Ship ms) {
        Dock md = getDock(ms.parent);
        if (md == null) {
            Seaport p = getSeaport(ms.parent);
            if (p != null) {
                p.ships.add(ms);
                p.queue.add(ms);
            }
        }
        else {
            md.ship = ms;
            getSeaport(md.parent).ships.add(ms);
        }
    }

    public void assignPort(Seaport msp){
        ports.add(msp);
    }
    public void assignPerson(Person mp){
        for(Seaport sp:ports)
        {
            if(sp.getIndex()==mp.getParent())
            {
                sp.persons.add(mp);
            }
        }
    }
    public void assignDock(Dock md){
            for(Seaport sp:ports) {
                if(sp.getIndex()==md.getParent()) {
                    sp.docks.add(md);
                }
            }
        }
    public void assignJob(Job mj, Thing parent){

    }

    public String toString(){
        String out = "\nWorld:\n\n";
        for (Seaport msp : ports) {
            out += msp;
        }
        return out;
    }
}