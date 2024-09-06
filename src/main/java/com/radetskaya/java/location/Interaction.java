package com.radetskaya.java.location;
import java.util.Random;

class Interaction {
    Random random = new Random();

    boolean attemptToEat(double probability) {
        return random.nextDouble() * 100 < probability;
    }
}