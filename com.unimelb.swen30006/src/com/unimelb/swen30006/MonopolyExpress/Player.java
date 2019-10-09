/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress;

public class Player {
	private String name;
	private int score;
	private int turn;
	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.turn = 0;
	}
	public void addScore(int score) {
		this.score += score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void newTurn() {
		this.turn += 1;
	}
	
	public int getTurn() {
		return this.turn;
	}
}
