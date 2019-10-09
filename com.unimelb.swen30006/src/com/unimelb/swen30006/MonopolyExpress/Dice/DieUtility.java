/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress.Dice;

public class DieUtility extends Die {
	public DieUtility() {
		faces.add(new DieFace("150",150));
		faces.add(new DieFace("150",150));
		faces.add(new DieFace("100",100));
		faces.add(new DieFace("100",100));
		faces.add(new DieFace("100",100));
		faces.add(new DieFace("Utility",10));
	}
}
