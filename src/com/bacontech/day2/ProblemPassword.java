package com.bacontech.day2;

public class ProblemPassword {
    public String password;
    public String ruleLetter;
    public int minRule;
    public int maxRule;
    public String original;

    ProblemPassword(String password, String letter, int min, int max, String original) {
        this.password = password;
        this.ruleLetter = letter;
        this.minRule = min;
        this.maxRule = max;
        this.original = original;
    }
}
