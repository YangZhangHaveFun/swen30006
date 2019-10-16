package com.unimelb.swen30006.monopoly.observer;

import com.unimelb.swen30006.monopoly.Player;
import com.unimelb.swen30006.monopoly.square.PropertySquare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ObserverOwnedSqure implements ObserverBase {
    private String fileName;
    private Player registeredPlayer;

    public ObserverOwnedSqure(String fileName, Player registeredPlayer) {
        this.fileName = fileName;
        this.registeredPlayer = registeredPlayer;
    }

    @Override
    public void propertyWriteEvent() {
        ArrayList<String> list = new ArrayList<>();
        for (PropertySquare square: registeredPlayer.getProperties()) {
            list.add(square.getName());
        }
        writeFile(list);
    }

    // For Owned Squares Logger: Count frequency and write into a file
    public void writeFile(ArrayList<String> list) {
        try {
            FileWriter outStream = new FileWriter(fileName);
            Map<Object, Long> counts = list.stream().collect(Collectors.groupingBy(e -> e,
                    Collectors.counting()));
            for(Map.Entry<Object, Long> entry: counts.entrySet()) {
                outStream.write(entry.getKey()+" x "+entry.getValue()+"\n");
            }
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
