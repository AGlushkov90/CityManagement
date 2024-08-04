package com.example.citymanagement.utils;

public class GenerateNumber {
    public static int generateNumber(int countPerson){
        return (int) (Math.random() * countPerson);
    }
}
