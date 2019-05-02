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

    public void upLevel() {
        if (getLevel() < 10)
            level++;
        else
            level = 10;
    }
}
