package sample.Core.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.CharacterStorage;
import sample.Person.Hero;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private static Game instance;
    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public ArrayList<Hero> heroes = new ArrayList<Hero>();

    private Game() {
        try {
            heroes.addAll(readAllCharacters());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Hero heroByName(String name) {
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).toString().equals(name)) {
                return heroes.get(i);
            }
        }

        return null;
    }

    private ArrayList<Hero> readAllCharacters() throws IOException {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();

        ArrayList<Hero> allHeroes = new ArrayList<>();

        File folder = new File("./Characters");
        for (File file : folder.listFiles()) {
            if (file.getName().contains(".txt")) {
                Hero person = CharacterStorage.read_hero(file, GSON);
                allHeroes.add(person);
            }
        }
        return allHeroes;
    }
}