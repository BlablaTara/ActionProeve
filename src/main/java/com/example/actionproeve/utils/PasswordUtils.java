package com.example.actionproeve.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    
    public class PasswordHashing {
    public static void main(String[] args) {
        String password1 = "adminpass1";
        String password2 = "adminpass2";
        System.out.println("Hashed password for adminpass1: " + PasswordUtils.hashPassword(password1));
        System.out.println("Hashed password for adminpass2: " + PasswordUtils.hashPassword(password2));
    }
}
}
