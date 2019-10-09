/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress.Board;

import com.unimelb.swen30006.MonopolyExpress.Dice.Die;

public class SquareSet {
	private Die[] squares;
	private int currIndex;
	private String groupName;
	
	public SquareSet(String groupName, int size) {
		squares = new Die[size];
		this.groupName = groupName;
		currIndex = 0;
	}
	
	public boolean place(Die d) {
		if(currIndex < squares.length) {
			this.squares[currIndex] = d;
			currIndex += 1;
			return true;
		}
		return false;
	}
	
	public String getGroupName() {
		return this.groupName;
	}
	
	public String showSquares() {
		String output = this.groupName+" | ";
		
		for(int i = 0; i < squares.length; i++) {
			if(squares[i] != null) {
				output += squares[i].getCurrentFaceName()+" | ";
			}else {
				output += "____ | ";
			}
		}
		return output;
	}
	
	public int countFilled() {
		int cnt = 0;
		for(int i = 0; i < squares.length; i++) {
			if(squares[i] != null) {
				cnt += 1;
			}
		}
		return cnt;
	}
	
	public boolean isAllFilled() {
		return countFilled() == squares.length;
	}
	
	public int getSumValue() {
		int sum = 0;
		for(int i = 0; i < squares.length; i++) {
			if(squares[i] != null) {
				sum += squares[i].getCurrentFaceValue();
			}
		}
		return sum;
	}
}
