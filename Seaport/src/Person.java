import java.util.*;
class Person extends Thing{

    private String skill;

    Person(Scanner sc) {
        super(sc);
        skill = (sc.hasNext()) ? sc.next() : "<ERROR>";
    }
    public String getSkill(){
        return skill;
    }
    public void setSkill(String s){
        skill = s;
    }

    public String toString () {
        return String.format("%s\n     Skill: %s",
                super.toString(), skill);
    }
}
