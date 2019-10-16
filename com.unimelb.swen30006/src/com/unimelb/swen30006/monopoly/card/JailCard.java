package com.unimelb.swen30006.monopoly.card;
import com.unimelb.swen30006.monopoly.Player;
import com.unimelb.swen30006.monopoly.square.Square;
/**
 * This class is created for Workshop 9 exercises for SWEN30006 Software Design and Modelling at the University of Melbourne
 * @author Patanamon Thongtanunam
 * @version 1.1
 * @since 2019-05
 */
public abstract class JailCard {
	double cardValue;
	String cardDescription;
	public void action(Player p, Square location) {
		p.setLocation(location);
	}
}
