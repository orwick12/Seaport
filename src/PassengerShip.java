import java.util.*;

public class PassengerShip extends Ship{

    private int numberOfOccupiedRooms;
    private int numberOfPassengers;
    private int numberOfRooms;


    public PassengerShip (Scanner sc) {
        super (sc);
        if (sc.hasNextInt()) numberOfPassengers = sc.nextInt();
        if (sc.hasNextInt()) numberOfRooms = sc.nextInt();
        if (sc.hasNextInt()) numberOfOccupiedRooms = sc.nextInt();
    } // end end Scanner constructor

    public int getOccupiedRooms(){
        return numberOfOccupiedRooms;
    }
    public void setOccupiedRooms(int o){
        if(o >= 0){
            numberOfOccupiedRooms = o;
        }
    }

    public int getPassengers(){
        return numberOfPassengers;
    }
    public void setPassengers(int p){
        if(p >= 0){
            numberOfPassengers = p;
        }
    }

    public int getRooms(){
        return numberOfRooms;
    }
    public void setRooms(int r){
        if(r >= 0){
            numberOfRooms = r;
        }
    }

    public String toString () {
        String st = "Passenger ship: " + super.toString();
        if (jobs.size() == 0)
            return st;
        for (Job mj: jobs) st += "\n       - " + mj;
        return st;    } // end method toString
}
