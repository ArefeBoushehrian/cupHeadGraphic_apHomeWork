package com.example.question0_3.controller;

import com.example.question0_3.Enum.AvatarImages;
import com.example.question0_3.model.User;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.Random;

public class ProfileController {
    public static void settingAvatar(Rectangle avatar, User user) {
        AvatarImages avatarModel;

        if (user.getAvatar() == null) avatarModel = randomImage();
        else avatarModel = user.getAvatar();

        Image image = new Image(ProfileController.class.getResource(avatarModel.toString()).toExternalForm());
        ImagePattern avatarImage = new ImagePattern(image);
        avatar.setFill(avatarImage);
        avatar.setHeight(40);
        avatar.setWidth(40);
        user.setAvatar(avatarModel);
    }

    public static void changingAvatar(Rectangle avatar, User user) {
        Image image = new Image(ProfileController.class.getResource(nextImage(user).toString()).toExternalForm());
        ImagePattern avatarImage = new ImagePattern(image);
        avatar.setFill(avatarImage);
        avatar.setHeight(40);
        avatar.setWidth(40);
        user.setAvatar(nextImage(user));
    }

    private static AvatarImages nextImage(User user) {
        int currentAvatar = Arrays.binarySearch(AvatarImages.values(), user.getAvatar());
        int nextAvatar = (currentAvatar + 1) % AvatarImages.values().length;
        return AvatarImages.values()[nextAvatar];
    }

    private static AvatarImages randomImage() {
        int max = AvatarImages.values().length;
        Random random = new Random();
        int randomNumber = random.nextInt(max);
        return AvatarImages.values()[randomNumber];
    }
}
