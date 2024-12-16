package com.example.citymanagementnew.utils;

public class GenerateNumber {
    public static int generateNumber(int countPerson){
        return (int) (Math.random() * countPerson);
    }
}
