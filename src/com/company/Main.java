package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BasketballPlayerProfile player1 = new BasketballPlayerProfile("Larry Bird", 6.9, 220, 24.3);
        File file = new File("player.txt");
        try {
            Serializer.saveSerialized(player1, String.valueOf(file));
        } catch (IllegalAccessException | FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Deserializer.deserialize(file, BasketballPlayerProfile.class);
        } catch (IOException | IllegalAccessException | InstantiationException exception) {
            exception.printStackTrace();
        }
    }
}
