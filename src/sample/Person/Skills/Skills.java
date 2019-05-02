package sample.Person.Skills;

import java.util.HashMap;
import java.util.Map;

public class Skills {

    private Skill painting;
    private Skill dancing;
    private Skill gardening;
    private Skill logic;
    private Skill photography;
    private Skill playingTheGuitar;
    private Skill programming;
    private Skill writing;

    public Skill getPainting() {
        return painting;
    }

    public Skill getDancing() {
        return dancing;
    }

    public Skill getGardening() {
        return gardening;
    }

    public Skill getLogic() {
        return logic;
    }

    public Skill getPhotography() {
        return photography;
    }

    public Skill getPlayingTheGuitar() {
        return playingTheGuitar;
    }

    public Skill getProgramming() {
        return programming;
    }

    public Skill getWriting() {
        return writing;
    }

    public void addSkills() {
        painting = new Skill("Painting", 0);
        dancing = new Skill("Dancing", 0);
        gardening = new Skill("Gardening", 0);
        logic = new Skill("Logic", 0);
        photography = new Skill("Photography", 0);
        playingTheGuitar = new Skill("Playing the guitar", 0);
        programming = new Skill("Programming", 0);
        writing = new Skill("Writing", 0);
    }

    public void printSkills(){
        HashMap<String, Integer> skills = new HashMap<>();
        skills.put(getPainting().getName(), getPainting().getLevel());
        skills.put(getDancing().getName(), getDancing().getLevel());
        skills.put(getWriting().getName(), getWriting().getLevel());
        skills.put(getGardening().getName(), getGardening().getLevel());
        skills.put(getPhotography().getName(), getPhotography().getLevel());
        skills.put(getLogic().getName(), getLogic().getLevel());
        skills.put(getPlayingTheGuitar().getName(), getPlayingTheGuitar().getLevel());
        skills.put(getProgramming().getName(), getProgramming().getLevel());

        for(Map.Entry entry : skills.entrySet()){
            System.out.println(entry.getKey() + ": "
                    + entry.getValue());
        }
    }
}
