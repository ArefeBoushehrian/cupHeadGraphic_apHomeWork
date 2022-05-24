package com.example.question0_3;

import com.example.question0_3.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static void readFromDatabase() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("database.txt")));
        ArrayList<User> listOfUsers;

        listOfUsers = new Gson().fromJson(json, new TypeToken<List<User>>(){}.getType());
        if (listOfUsers == null) listOfUsers = new ArrayList<>();
        User.setListOfAllUsers(listOfUsers);
    }

    public static void writeOnDataBase() throws IOException {
        FileWriter dataBase = new FileWriter("database.txt");
        dataBase.write(new Gson().toJson(User.getListOfAllUsers()));
        dataBase.close();
    }
}
