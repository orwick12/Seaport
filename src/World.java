
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;


public class World extends Thing {

    HashMap<Integer, Seaport> portMap = new HashMap<>();
    HashMap<Integer, Dock> dockMap = new HashMap<>();
    HashMap<Integer, Ship> shipMap = new HashMap<>();
    HashMap<Integer, Person> personMap = new HashMap<>();

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

    /**process method for scanning in data from a file to create the world
     * It will use a switch case to determine what is being scanned in then assign it to the correct parents
     * @param sc
     */
    void process(Scanner sc) {
        if(sc.hasNext()) {
            switch (sc.next().trim()) {
                case "port":
                    Seaport msp = new Seaport(sc);
                    portMap.put(msp.getIndex(), msp);
                    assignPort(msp);
                    break;
                case "dock":
                    Dock md = new Dock(sc);
                    dockMap.put(md.getIndex(),md);
                    assignDock(md, portMap);
                    break;
                case "pship":
                    Ship pship = new PassengerShip(sc);
                    shipMap.put(pship.getIndex(), pship);
                    assignShip(pship, portMap, dockMap);
                    break;
                case "cship":
                    Ship cship = new CargoShip(sc);
                    shipMap.put(cship.getIndex(), cship);
                    assignShip(cship, portMap, dockMap);
                    break;
                case "person":
                    Person person = new Person(sc);
                    personMap.put(person.getIndex(), person);
                    assignPerson(person, portMap);
                    break;
                case "job":
                    //Job job = new Job(shipMap,jp, sc);
                    break;
                default:
                    break;
            }
        }
        //System.out.println("portHash " + portMap.toString() + "\ndockHash " + dockMap.toString() + "\nshipHash " + shipMap.toString());
    }

    /*public void assignJob(Job j, HashMap<Integer, Ship> shipMap){
        Ship s;
        if(shipMap.get(j.getParent()) != null) {
            s = shipMap.get(j.getParent());
            s.jobs.add(j);
            j.setParent(shipMap);

        }
    }*/

    Seaport getSeaport(int x) {
        for (Seaport msp: ports)
            if(msp.index==x)
                return msp;
        return null;
    }
    //method assigning ships to seaport or queue and assigning seaports as parents
    public void assignShip(Ship ms, HashMap<Integer,Seaport> portMap, HashMap<Integer, Dock> dockMap) {
        Seaport msp = portMap.get(ms.getParent());
        Dock md = dockMap.get(ms.getParent());
        if (md == null) {
            ms.setParent(msp.getIndex());
            if (msp.docks != null) {
                msp.ships.add(ms);
                msp.queue.add(ms);
            }
            return;
        }
        md.ship = ms;
        getSeaport (md.parent).ships.add (ms);
    }
    //method for assigning ports to the world
    public void assignPort(Seaport msp){
        ports.add(msp);
    }
    //method for assigning people each seaport and assigning seaports as parents
    public void assignPerson(Person mp, HashMap<Integer,Seaport> portMap) {
        if (portMap.containsKey(mp.getParent())) {
            Seaport msp = portMap.get(mp.getParent());
            msp.persons.add(mp);
        }
    }
    //method for assigning docks to seaports and assigning seaports as parents
    public void assignDock(Dock md, HashMap<Integer, Seaport> portMap){
           Seaport msp = portMap.get(md.getParent());
                if(msp != null){
                    md.setParent(msp.getIndex());
                    msp.docks.add(md);
                }


    }

 // end getDockByIndex
    /**method for searching by name will pull each seaport with each dock, ship, queship, and person
     * search through and return matches
     * @param st
     * @return
     */
    public String searchByName(String st) {
        String searchResult = "";
        for(Seaport sp : ports) {
            if(sp.getName().equalsIgnoreCase(st))
                searchResult += sp.toString() + "\n";

            for(Dock d: sp.getDocks()) {
                if(d.getName().equalsIgnoreCase(st)) {
                    searchResult += d.toString() + "\n";
                }
            }

            for(Ship s: sp.getShips()) {
                if(s.getName().equalsIgnoreCase(st)) {
                    searchResult += s.toString() + "\n";
                }
            }
            for(Ship q : sp.getQueue()) {
                if(q.getName().equalsIgnoreCase(st)) {
                    searchResult += q.toString() + "\n";
                }
            }

            for(Person p : sp.getPersons()) {
                if(p.getName().equalsIgnoreCase(st)) {
                    searchResult += p.toString() + "\n";
                }
            }
        }
        return searchResult;
    }
    //method for searching skill only needs to search through all ports and persons in those ports
    public String searchBySkill(String sk) {
        String result = "";
        for(Seaport sp : ports) {
            for(Person p : sp.getPersons()) {
                if(p.getSkill().equalsIgnoreCase(sk)) {
                    result += p.toString() + "\n";
                }
            }
        }
        return result;
    }
    /**method for searching by index will pull each seaport with each dock, ship, queship, and person
     * search through and return matches.    Copy and pasted from searchByName method and changed String to int
     * same algorithm
     * @param index
     * @return
     */
    public String searchByIndex(int index) {
        String searchResult = "";
        int x = Integer.parseInt(String.valueOf(index));

        if (portMap.containsKey(x))
            searchResult += portMap.get(x);
        else if (dockMap.containsKey(x))
            searchResult += dockMap.get(x);
        else if (shipMap.containsKey(x))
            searchResult += shipMap.get(x);
        else if (personMap.containsKey(x))
            searchResult += personMap.get(x);
        else
            searchResult = "Index Not Found";
        return searchResult;
    }

    public String toString(){
        String out = "\nWorld:\n\n";
        for (Seaport msp : ports) {
            out += msp;
        }
        return out;
    }
}