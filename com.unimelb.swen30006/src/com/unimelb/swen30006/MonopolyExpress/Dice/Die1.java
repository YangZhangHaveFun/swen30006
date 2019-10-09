/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress.Dice;


public class Die1 extends Die {

	public Die1() {
		faces.add(new DieFace("500",500));
		faces.add(new DieFace("200",200));
		faces.add(new DieFace("Railroad",200));
		faces.add(new DieFace("100",100));
		faces.add(new DieFace("50",50));
		faces.add(new DieFace("50",50));
	}

}
