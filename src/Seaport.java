import javax.sound.sampled.Port;
import java.util.*;

public class Seaport extends Thing{

    ArrayList<Dock> docks;
    ArrayList<Ship> queue; //ships waiting to dock
    ArrayList<Ship> ships; //ships at this port
    ArrayList<Person> persons; //people with skills at port

    Seaport(Scanner sc){
        super(sc);
        ships = new ArrayList<>();
        persons = new ArrayList<>();
        queue = new ArrayList<>();
        docks = new ArrayList<>();
    }

    //public ArrayList sortPorts(ArrayList<Dock> dock){
      //  Collections.sort(dock);
   // return dock;
    //}

    ArrayList<Dock> getDocks(){
        return docks;
    }
    public void setDocks(ArrayList<Dock> d){
        this.docks = d;
    }

    public ArrayList<Ship> getQueue(){
        return queue;
    }
    public void setQueue(ArrayList<Ship> q){
        this.queue = q;
    }

    public ArrayList<Ship> getShips(){
        return ships;
    }
    public void setShips(ArrayList<Ship> s){
        this.ships = s;
    }

    public ArrayList<Person> getPersons(){
        return persons;
    }
    public void setPersons(ArrayList<Person> p){

        this.persons = p;
    }

    public String toString () {
        StringBuilder stBuilder = new StringBuilder("\n\nSeaport: " + super.toString());
        for (Dock md: docks) stBuilder.append("\n").append(md);
        String st = stBuilder.toString();
        st += "\n\n --- List of all ships in que:";
        StringBuilder stBuilder1 = new StringBuilder(st);
        for (Ship ms: queue ) stBuilder1.append("\n   > ").append(ms);
        st = stBuilder1.toString();
        st += "\n\n --- List of all ships:";
        for (Ship ms: ships) st += "\n   > " + ms;
            st += "\n\n --- List of all persons:";
        for (Person mp: persons) st += "\n   > " + mp;
        return st;
    } // end method toString
}
