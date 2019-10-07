package com.example.fypsurveyweb.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class randomGenerateImageNames {

    private List<String> picNumBackend = new ArrayList<>();
    private String[][] picNumFrontend = new String[2][5];
    private List<Integer> preventDup = new ArrayList<>();

    public void initRandomNames () {

        for (int m = 0; m < 101; m++) {
            preventDup.add(m);
        }

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 5; j++) {
                int randomImgIndex = (int) (Math.random() * (preventDup.size() - 1));
                int randomTag = (int) (Math.random() * 4);
                int randomImg = preventDup.get(randomImgIndex);
                //System.out.println(randomImg);
                if (picNumBackend.size() > 9) picNumBackend.clear();
                picNumBackend.add(randomImg + "-" + randomTag);
                switch (randomTag) {
                    case 0:  picNumFrontend[i-1][j-1] = randomImg  + "-ori.jpg";  break;
                    case 1:  picNumFrontend[i-1][j-1] = randomImg  + "-ord.jpg";  break;
                    case 2:  picNumFrontend[i-1][j-1] = randomImg  + "-gan.png";  break;
                    case 3:  picNumFrontend[i-1][j-1] = randomImg  + "-res.png";  break;
                }
                preventDup.remove(randomImgIndex);
            }
        }
    }

    public List<String> getPicNumBackend() {
        return picNumBackend;
    }

    public String[][] getPicNumFrontend() {
        return picNumFrontend;
    }


}
