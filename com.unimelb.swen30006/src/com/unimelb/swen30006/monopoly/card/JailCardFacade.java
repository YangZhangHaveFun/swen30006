package com.unimelb.swen30006.monopoly.card;

import com.unimelb.swen30006.monopoly.Player;
import com.unimelb.swen30006.monopoly.square.Square;
import java.util.ArrayList;
import java.util.Random;

public class JailCardFacade {

    static ArrayList<JailCard> jailCards = new ArrayList<>();

    static {
        jailCards.add(new JailExcemptionCard());
        jailCards.add(new GoToJailCard());
        jailCards.add(new GoToJailCard());
        jailCards.add(new PayJailFeeCard());
        jailCards.add(new PayJailFeeCard());
    }

    public void chooseAction(Player player, Square jail, Square goToJail){
        int random = new Random().nextInt(5);

        if (jailCards.get(random) instanceof JailExcemptionCard){
            jailCards.get(random).action(player, goToJail);
            System.out.println("JailExcemptionCard is applied");
            System.out.println(player.getName()+" Stays in GoToJail Square!");
        } else if (jailCards.get(random) instanceof GoToJailCard){
            jailCards.get(random).action(player, jail);
            System.out.println("GoToJailCard is applied");
            System.out.println(player.getName()+" Goes to jail!");
        } else if (jailCards.get(random) instanceof PayJailFeeCard){
            System.out.println("PayJailFeeCard is applied");
            Square square = ((PayJailFeeCard) jailCards.get(random)).getLegalSquare(player, jail, goToJail);
            jailCards.get(random).action(player, square);

        }

    }
}
