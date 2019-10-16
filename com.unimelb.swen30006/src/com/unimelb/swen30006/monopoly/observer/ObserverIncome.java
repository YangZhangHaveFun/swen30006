package com.unimelb.swen30006.monopoly.observer;

import com.unimelb.swen30006.monopoly.Player;

import java.io.FileWriter;
import java.io.IOException;

public class ObserverIncome implements ObserverBase{
    private String fileName;
    private boolean fileCreated = false;
    private Player registeredPlayer;
    private int currentCash;

    public ObserverIncome(String fileName, Player registeredPlayer, int currentCash) {
        this.fileName = fileName;
        this.registeredPlayer = registeredPlayer;
        this.currentCash = currentCash;
    }

    @Override
    public void propertyWriteEvent() {
        if (this.fileCreated)
            writeFile();
        else
            createAndWriteFile();
    }

    // For Cash Transaction Logger: Create a new file
    private void createAndWriteFile() {
        try {
            FileWriter outStream = new FileWriter(this.fileName);
            outStream.write("Amount, Balance\n");
            outStream.write("init, "+this.registeredPlayer.getCash()+"\n");
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // For Cash Transaction Logger: Append text into a file
    private void writeFile() {
        try {
            FileWriter outStream = new FileWriter(fileName, true);
            outStream.write(this.registeredPlayer.getCash()-this.currentCash+", "+this.registeredPlayer.getCash()+"\n");
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
