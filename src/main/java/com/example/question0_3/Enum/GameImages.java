package com.example.question0_3.Enum;

public enum GameImages {
    BULLET("/frames/images/bullet.png"),
    EGG("/frames/images/egg.png");

    private final String fileAddress;

    GameImages(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    @Override
    public String toString() {
        return fileAddress;
    }
}
