package com.unimelb.swen30006.monopoly.square;

import com.unimelb.swen30006.monopoly.Player;
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
 * The behavior is coded based on Figure 25.6
 * 
 * @author 	Yunzhe(Alvin) Jia
 * @version 1.0
 * @since 	2016-07-19
 *
 */
public class IncomeTaxSquare extends Square {
	public static final int MAX_TAX = 200;
	public static final int TAX_PERCENTAGE = 10;
	
	public IncomeTaxSquare(String name, int index) {
		super(name, index);
	}

	@Override
	public void landedOn(Player p) {
		int w = p.getNetWorth();
		if( w > 0) {
			int tax = Math.min(MAX_TAX,w*100/TAX_PERCENTAGE);
			p.reduceCash(tax);
			System.out.println(p.getName()+" pay income tax $"+tax);
		}	
	}

}
