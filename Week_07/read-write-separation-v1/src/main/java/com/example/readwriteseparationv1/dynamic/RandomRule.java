package com.example.readwriteseparationv1.dynamic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomRule {
    private final static int SIZE=Integer.parseInt(System.getProperty("sdb_size","2"));
    public static List<String> dbnames= Arrays.asList("second","three");
    public static String rule(){

        Random random=new Random();
        int n=random.nextInt(SIZE);
        return dbnames.get(n);
    }
}
