import java.util.*;

class Dock extends Thing{

    Ship ship;

    Dock(Scanner sc){
        super(sc);
    }

    public Ship getShip(){
        return ship;
    }
    public void setShip(Ship s){
        ship = s;
    }

    public String toString () {
        return String.format("%s\n     Ship: %s",
               super.toString(), ship);
    }
}
