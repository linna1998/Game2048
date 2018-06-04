package com.game.view;

import javax.swing.*;
import java.io.*;

public class EnterNameWindow{

    private String inputName;
    private int score;


    public EnterNameWindow(int inscore) {
        inputName = JOptionPane.showInputDialog(null, "Please input your name!",
                "Name inputting", JOptionPane.PLAIN_MESSAGE);
        score = inscore;
    }

    /*
     * append the Name and Score of this participant
     *  into file "Scores.txt"
     */
    void writeScore() {
        try {
            String fileName = "Scores.txt";
            String outString = inputName + "\t" + score + "\n";
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(outString);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        EnterNameWindow test = new EnterNameWindow(23);
        test.writeScore();
    }
}
