public class PortTime {

    private double time;
    public PortTime(){
        time = 0;
    }

    public double getTime(){
        return time;
    }
    public void setTime(int t){
        if(t >= 0){
            time = t;
        }
    }

    public String toString () {
        return String.format("%s\n     Time: %s",
                super.toString(), time);
    }
}
