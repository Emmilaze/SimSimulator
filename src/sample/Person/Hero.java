package sample.Person;

import javafx.scene.paint.Color;
import sample.Person.Skills.*;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class Hero {

    private String name;
    private String last_name;
    private String age;
    private String sex;

    private Work work;
    private Appearance appearance;
    private HeroNeeds heroNeeds;
    private Calendar calendar;

    private Skills skills;
    private boolean haveJob;
    private boolean married;

    public Hero(String name, String last_name, String age, String sex, Calendar calendar, boolean haveJob) {
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.sex = sex;
        this.calendar = calendar;
        this.haveJob = haveJob;
    }

    public void newWork(String position, int salary, String workingDays) {
        this.work = new Work(position, salary, workingDays);
    }

    public void newAppearance(Color hairColor, Color eyesColor, Color skinColor, String bodyType) {
        this.appearance = new Appearance(hairColor, eyesColor, skinColor, bodyType);
    }

    public void newSkills(){
        this.skills = new Skills();
        skills.addSkills();
    }

    public void newHeroNeeds(int money, int hunger, int bladder, int hygiene, int energy){
        this.heroNeeds = new HeroNeeds(money, hunger, bladder, hygiene, energy);
    }

    @Override
    public String toString() {
        return (name + " " + last_name);
    }

    public HashMap showNeeds(){
        return this.heroNeeds.show();
    }

    public IHeroNeeds needs(){
        return this.heroNeeds;
    }

    public HeroNeeds getHeroNeeds() {
        return heroNeeds;
    }

    public Skills getSkill() {
        return skills;
    }

    public void setSkill(Skills skill) {
        this.skills = skills;
    }

    public void save(Hero hero, FileWriter writer, Gson GSON){
        String json = GSON.toJson(hero);
        try {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Calendar getCalendar() {
        return calendar;
    }

}
