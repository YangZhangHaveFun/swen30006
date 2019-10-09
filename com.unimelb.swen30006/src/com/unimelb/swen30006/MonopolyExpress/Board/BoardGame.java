/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress.Board;

import java.util.ArrayList;

import com.unimelb.swen30006.MonopolyExpress.Dice.Die;

public class BoardGame {
	private ArrayList<SquareSet> groups;
	public BoardGame() {
		this.groups = new ArrayList<SquareSet>();
		reset();
	}
	
	public void reset() {
		if(this.groups.size() > 0) {
			this.groups.clear();
		}
		groups.add(new SquareSet("Police", 3));
		groups.add(new SquareSet("Utility", 2));
		groups.add(new SquareSet("Railroad", 4));
		groups.add(new SquareSet("50", 2));
		groups.add(new SquareSet("100", 3));
		groups.add(new SquareSet("150", 3));
		groups.add(new SquareSet("200", 3));
		groups.add(new SquareSet("250", 3));
		groups.add(new SquareSet("300", 3));
		groups.add(new SquareSet("400", 3));
		groups.add(new SquareSet("500", 2));
	}
	public void placeDie(Die d) {
		String selectedGroup = d.getCurrentFaceName();
		for(int i = 0; i < groups.size(); i++) {
			if(groups.get(i).getGroupName().equals(selectedGroup)) {
				groups.get(i).place(d);
			}
		}
	}
	
	public String show() {
		String output = "======= BOARD =====\n";
		for(SquareSet ss : groups) {
			output += ss.showSquares()+"\n";
		}
		output += "=============";
		return output;
	}
	
	public ArrayList<SquareSet> getCompleteGroup(){
		ArrayList<SquareSet> complete = new ArrayList<SquareSet>();
		for(SquareSet ss: groups) {
			if(ss.isAllFilled()) {
				complete.add(ss);
			}
		}
		return(complete);
	}
	
	public ArrayList<SquareSet> getInCompleteGroup(){
		ArrayList<SquareSet> incomplete = new ArrayList<SquareSet>();
		for(SquareSet ss: groups) {
			if(!ss.isAllFilled()) {
				incomplete.add(ss);
			}
		}
		return(incomplete);
	}
	
	public boolean isAllFilled(String face) {
		boolean allFilled = false;
		for(SquareSet ss: groups) {
			if(ss.getGroupName().equals(face)) {
				allFilled = ss.isAllFilled();
			}
		}
		
		return allFilled;
	}

}
