package com.unimelb.swen30006.monopoly.square;

import com.unimelb.swen30006.monopoly.Player;
import com.unimelb.swen30006.monopoly.card.JailCardFacade;
/**
 * This class is modified for Workshop 9 exercises for SWEN30006 Software Design and Modelling at the University of Melbourne
 * @author Patanamon
 * @version 1.1
 * @since 2019-05
 */
/**
 * This class is created based on case study of Monopoly of "Applying UML and Patterns, 3rd edition by Craig Larman".
 * For demonstration on subject SWEN30006 at The University of Melbourne 
 * 
 * The behavior is coded based on Figure 25.7
 * 
 * @author 	Yunzhe(Alvin) Jia
 * @version 1.0
 * @since 	2016-07-19
 *
 */
public class GoToJailSquare extends Square {
	private Square jail;
	
	public GoToJailSquare(String name, int index) {
		super(name, index);
	}
	
	public void setJail(Square jail){
		this.jail = jail;
	}

	@Override
	public void landedOn(Player p) {
		new JailCardFacade().chooseAction(p, jail, this);
	}

}
