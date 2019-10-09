/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress.Dice;
import java.util.ArrayList;
import java.util.Random;

public abstract class Die {

	private Random rand = new Random();
	private DieFace currentFace = null;
	protected ArrayList<DieFace> faces;
	
	public Die() {
		this.rand = new Random();
		this.faces = new ArrayList<DieFace>();
	}
	
	public void roll() {
		int faceIndex = rand.nextInt(6);
		currentFace = faces.get(faceIndex);
	}
	
	
	public String getCurrentFaceName() {
		return currentFace.getName();
	}
	
	public int getCurrentFaceValue() {
		return currentFace.getValue();
	}
}
