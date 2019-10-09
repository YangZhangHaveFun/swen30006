/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress.Dice;

public class DiePolice extends Die{
	public DiePolice() {
		faces.add(new DieFace("Police",0));
		faces.add(new DieFace("<blank>",0));
		faces.add(new DieFace("<blank>",0));
		faces.add(new DieFace("Police",0));
		faces.add(new DieFace("<blank>",0));
		faces.add(new DieFace("Police",0));
	}
}
