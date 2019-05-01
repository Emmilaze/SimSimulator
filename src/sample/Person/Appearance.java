package sample.Person;

import javafx.scene.paint.Color;

public class Appearance {
    private Color hairColor;
    private Color eyesColor;
    private Color skinColor;
    private String bodyType;

    public Appearance(Color hairColor, Color eyesColor, Color skinColor, String bodyType) {
        this.hairColor = hairColor;
        this.eyesColor = eyesColor;
        this.skinColor = skinColor;
        this.bodyType = bodyType;
    }
}
