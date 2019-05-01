package sample.Person;

import java.util.Calendar;
import java.util.HashMap;

public class HeroNeeds implements IHeroNeeds {
    public final static int MONEY_START = 2000;
    public final static int HUNGER_START = 100;
    public final static int BLADDER_START = 100;
    public final static int HYGIENE_START = 100;
    public final static int ENERGY_START = 100;
    public final static int MIN = 0;
    public final static int MAX = 100;

    public int money;

    public int hunger;
    public int bladder;
    public int hygiene;
    public int energy;

    public String hungerStr;
    public String bladderStr;
    public String hygieneStr;
    public String energyStr;

    public HeroNeeds(int money, int hunger, int bladder, int hygiene, int energy) {
        this.money = money;
        this.hunger = hunger;
        this.bladder = bladder;
        this.hygiene = hygiene;
        this.energy = energy;
    }

    public int getMoney() {
        return money;
    }

    public int getHunger() {
        return hunger;
    }

    public int getBladder() {
        return bladder;
    }

    public int getHygiene() {
        return hygiene;
    }

    public int getEnergy() {
        return energy;
    }

    public HashMap show() {
        HashMap<String, Integer> needs = new HashMap<>();
        needs.put("Hunger", hunger);
        needs.put("Bladder", bladder);
        needs.put("Hygiene", hygiene);
        needs.put("Energy", energy);
        return needs;
    }

    @Override
    public void sleeping(Calendar calendar) {
        energy = change(energy, 100);
        hunger = change(hunger, -30);
        bladder = change(bladder, -20);
        hygiene = change(hygiene, -10);
        calendar.add(Calendar.HOUR, 8);
    }

    @Override
    public void eating(Calendar calendar) {
        energy = change(energy, -5);
        hunger = change(hunger, 100);
        bladder = change(bladder, -20);
        hygiene = change(hygiene, -5);
        calendar.add(Calendar.MINUTE, 20);
    }

    @Override
    public void takingShower(Calendar calendar) {
        energy = change(energy, -5);
        hunger = change(hunger, -5);
        bladder = change(bladder, -5);
        hygiene = change(hygiene, 100);
        calendar.add(Calendar.MINUTE, 20);
    }

    @Override
    public void goToPee(Calendar calendar) {
        energy = change(energy, -5);
        hunger = change(hunger, -5);
        bladder = change(bladder, 100);
        hygiene = change(hygiene, -5);
        calendar.add(Calendar.MINUTE, 2);
    }

    public int change(int paramert, int delta) {
        paramert += delta;
        if (paramert > MAX)
            paramert = MAX;
        if (paramert < MIN)
            paramert = MIN;
        return paramert;
    }
}


