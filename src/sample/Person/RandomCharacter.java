package sample.Person;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class RandomCharacter {

    public static String randomNameMale() throws IOException {
        ArrayList<String> names = new ArrayList<String>();
        Stream<String> stream = Files.lines(Paths.get("./Random/MaleNames.txt"));

        stream.forEach(line -> names.add(line));
        return names.get((int) (Math.random() * 2943));
    }

    public static String randomNameFemale() throws IOException {
        ArrayList<String> names = new ArrayList<String>();
        Stream<String> stream = Files.lines(Paths.get("./Random/FemaleNames.txt"));
        stream.forEach(line -> names.add(line));
        return names.get((int) (Math.random() * 4341));
    }

    public static String randomLastName() throws IOException {
        ArrayList<String> last_names = new ArrayList<String>();
        Stream<String> stream = Files.lines(Paths.get("./Random/Last names.txt"));
        stream.forEach(line -> last_names.add(line));
        String surname = last_names.get((int) (Math.random() * 500));
        surname = surname.toLowerCase();
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        return surname;
    }

    public static boolean sex(){
        if(Math.random() > 0.5){
            return true;
        }
        else
            return false;
    }

    public static String age(){
        int age = (int) (Math.random()*2);
        switch (age){
            case 0:
                return "Young";
            case 1:
                return "Adult";
            case 2:
                return "Old";
        }
        return "";
    }

    public static Color randomColor(){
        float r = new Random().nextFloat();
        float g = new Random().nextFloat();
        float b = new Random().nextFloat();
        Color randomColor = new Color(r, g, b, 1);
        return randomColor;
    }

    public static String randomBody(){
        int eyes = (int)(Math.random()*4);
        switch (eyes){
            case 0:
                return "Slim";
            case 1:
                return "Fat";
            case 2:
                return "Muscular";
            case 3:
                return "Fit";
        }
        return "";
    }
}
