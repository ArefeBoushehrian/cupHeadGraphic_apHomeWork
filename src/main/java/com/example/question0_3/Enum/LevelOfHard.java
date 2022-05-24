package com.example.question0_3.Enum;

public enum LevelOfHard {
    EASY(10),
    INTERMEDIATE(5),
    HARD(2),
    DEVIL_MODE(2);

    private int health;

    LevelOfHard(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
