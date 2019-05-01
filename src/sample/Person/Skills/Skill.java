package sample.Person.Skills;

public class Skill {
    protected String name;
    protected int level;

    public Skill(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void upLevel() {
        if (getLevel() < 10)
            setLevel(getLevel()+1);
        else
            setLevel(10);
    }
}
