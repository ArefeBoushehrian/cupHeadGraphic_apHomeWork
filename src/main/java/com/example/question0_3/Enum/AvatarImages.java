package com.example.question0_3.Enum;

public enum AvatarImages {
    RED("/frames/images/red.png"),
    BLUE("/frames/images/blue.png"),
    AIRPLANE("/airplane.png");

    private final String fileAddress;

    AvatarImages(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    @Override
    public String toString() {
        return fileAddress;
    }
}
