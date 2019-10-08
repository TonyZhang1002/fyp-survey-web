package com.example.fypsurveyweb.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class randomGenerateImageNames {

    private List<String> imageNamesBackend;
    private List<String> imageNamesFrontend;
    private List<Integer> preventDup;

    public void initRandomNames () {

        imageNamesBackend = new LinkedList<>();
        imageNamesFrontend = new LinkedList<>();
        preventDup = new ArrayList<>();

        // Add all the possible image numbers to the list
        for (int m = 0; m < 101; m++) {
            preventDup.add(m);
        }

        // Generate 10 images for 2 kinds of questions, 5 for each
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 5; j++) {
                // Random an index in the possible list
                int randomImgIndex = (int) (Math.random() * (preventDup.size() - 1));
                // Random which kind of images we want
                int randomTag = (int) (Math.random() * 4);
                // Get the random image numbers
                int randomImg = preventDup.get(randomImgIndex);
                // Add to the backend image names list (like "24-2")
                imageNamesBackend.add(randomImg + "-" + randomTag);
                // For different image kinds, add different image names for frontend
                switch (randomTag) {
                    case 0:  imageNamesFrontend.add(randomImg  + "-ori.jpg");  break;
                    case 1:  imageNamesFrontend.add(randomImg  + "-ord.jpg");  break;
                    case 2:  imageNamesFrontend.add(randomImg  + "-gan.png");  break;
                    case 3:  imageNamesFrontend.add(randomImg  + "-res.png");  break;
                }
                // Remove this image from the possible image names list
                preventDup.remove(randomImgIndex);
            }
        }
    }

    // A method to get backend image names
    public List<String> getImageNamesBackend() {
        return imageNamesBackend;
    }

    // A method to get frontend image names
    public List<String> getImageNamesFrontend() {
        return imageNamesFrontend;
    }

    // A method to clear all image names
    public void clearImageNames () {
        imageNamesBackend.clear();
        imageNamesFrontend.clear();
    }


}
