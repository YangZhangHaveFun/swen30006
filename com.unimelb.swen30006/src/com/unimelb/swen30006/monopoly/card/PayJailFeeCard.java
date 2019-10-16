package com.unimelb.swen30006.monopoly.card;

import com.unimelb.swen30006.monopoly.Player;
import com.unimelb.swen30006.monopoly.square.Square;

public class PayJailFeeCard extends JailCard {

    @Override
    public void action(Player p, Square location) {
        p.setLocation(location);
    }

    public Square getLegalSquare(Player p, Square jail, Square goToJail){
        if (p.getCash() > 500) {
            p.reduceCash(500);
            System.out.println(p.getName()+" Stays in GoToJail Square!");
            return goToJail;
        }
        else {
            System.out.println(p.getName()+" Goes to jail!");
            return jail;
        }
    }
}
