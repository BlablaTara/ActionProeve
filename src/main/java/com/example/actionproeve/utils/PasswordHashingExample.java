package com.example.actionproeve.utils;


public class PasswordHashingExample {
    public static void main(String[] args) {
        // Your initial data
        String[][] users = {
            {"1", "Mario Mario", "supermario", "adminpass1", "1"},
            {"2", "Luigi Mario", "superluigi", "adminpass2", "1"},
            {"3", "Princess Peach", "princessPEACH<3", "password1", "0"},
            {"4", "Toad Toadsworth", "toadsuperworker", "password2", "0"},
            {"5", "Yoshi Yoshi", "yoshiSmash", "password3", "0"},
            {"6", "Bowser Koopa", "peachsuperfan1", "password4", "0"},
            {"7", "Donkey Kong", "banana4eva", "password5", "0"},
            {"8", "Princess Daisy", "daisycutiepie", "password6", "0"},
            {"9", "Wario Wario", "wariowinner", "password7", "0"},
            {"10", "Waluigi Waluigi", "superstarwaluigi", "password8", "0"}
        };

        // Print hashed passwords
        for (String[] user : users) {
            String id = user[0];
            String name = user[1];
            String username = user[2];
            String plainPassword = user[3];
            String isAdmin = user[4];

            String hashedPassword = PasswordUtils.hashPassword(plainPassword);
            System.out.println(String.format("INSERT INTO employee (id, name, username, password, admin) VALUES ('%s', '%s', '%s', '%s', '%s');",
                id, name, username, hashedPassword, isAdmin));
        }
    }
}
