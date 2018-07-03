import java.util.*;

public class CargoShip extends Ship {

    private double cargoValue;
    private double cargoVolume;
    private double cargoWeight;

    CargoShip(Scanner sc) {
        super(sc);
        cargoWeight = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0;
        cargoVolume = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0;
        cargoValue = (sc.hasNextDouble()) ? sc.nextDouble() : 0.0;
    }

    public double getCargoValue(){
        return cargoValue;
    }
    public void setCargoValue(double v){
        if(v >= 0.0){
            cargoValue = v;
        }
    }

    public double getCargoVolume(){
        return cargoVolume;
    }
    public void setCargoVolume(double v){
        if(v >= 0.0){
            cargoVolume = v;
        }
    }

    public double getCargoWeight(){
        return cargoWeight;
    }
    public void setCargoWeight(double w){
        if(w >= 0.0){
            cargoWeight = w;
        }
    }

    public String toString () {
        return String.format("%s\n     Cargo Value: %.2f\n     Cargo Volume: %.2f\n     Cargo Weight: %.2f\n",
                super.toString(), cargoValue, cargoVolume, cargoWeight);
    }
}
