
import java.util.*;

public class Job extends Thing {

    private double duration;
    private ArrayList<String> skills; //skills of a person

    public Job(Scanner n) {
        super(n);
    }
    public void setDuration(int r){
        if (r > 0){
            duration = r;
        }
    }
    public double getDuration(){
        return duration;
    }


    public String toString () {
        String st = "Job.toString:\nskills\n";
        for (String s: skills)
            st += s + "\n";
        return st;
    }
}
