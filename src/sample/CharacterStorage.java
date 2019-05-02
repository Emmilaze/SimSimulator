package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sample.Core.Model.Game;
import sample.Person.Hero;
import com.google.gson.Gson;

public class CharacterStorage {

    public static ArrayList<String> fullName = new ArrayList<>();

    public static Hero read_hero(File file, Gson GSON) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
        Hero result = GSON.fromJson(content, Hero.class);
        return result;
    }

    public void deleting() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Hero> heroes = Game.getInstance().heroes;

        for (int i = 0; i < heroes.size(); i++)
            System.out.println(i + heroes.get(i).toString());

        int index = Integer.parseInt(reader.readLine());
        Hero selectedHero = heroes.get(index);
        File file = new File("./Characters/" + selectedHero.toString() + ".txt");

        reader.close();
        file.delete();
    }

    public static ArrayList<String> heroesNames() {
        List<Hero> heroes = Game.getInstance().heroes;

        for (int i = 0; i < heroes.size(); i++)
            fullName.add(heroes.get(i).toString());
        return fullName;
    }


}
