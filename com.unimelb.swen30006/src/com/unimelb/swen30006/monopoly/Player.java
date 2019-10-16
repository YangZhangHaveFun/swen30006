package com.unimelb.swen30006.monopoly;
/**
 * This class is modified for Workshop 9 exercises for SWEN30006 Software Design and Modelling at the University of Melbourne
 * @author Patanamon Thongtanunam
 * @version 1.1
 * @since 2019-05
 */
import java.util.ArrayList;
import java.util.List;

import com.unimelb.swen30006.monopoly.observer.ObserverIncome;
import com.unimelb.swen30006.monopoly.observer.ObserverOwnedSqure;
import com.unimelb.swen30006.monopoly.square.PropertySquare;
import com.unimelb.swen30006.monopoly.square.RRSquare;
import com.unimelb.swen30006.monopoly.square.Square;

/**
 * This class is created based on case study of Monopoly of "Applying UML and Patterns, 3rd edition by Craig Larman".
 * For demonstration on subject SWEN30006 at The University of Melbourne.
 * 
 *  New Feature: 
 *  1. add properties attribute based on Figure 36.29
 *  2. add attempPurchase() function based on Figure 36.26
 * @author 	Yunzhe(Alvin) Jia
 * @version 3.0
 * @since 	2016-07-19
 *
 */
public class Player{
	public static final int INIT_CASH = 1000;
	
	private String name;
	private Square location;
	private Board board;
	private int cash;
	private int netWorth;
	private ObserverIncome observerIncome;
	private ObserverOwnedSqure observerOwnedSqure;
	
	private List<PropertySquare> properties = new ArrayList<PropertySquare>();
	private int RRCount = 0;
	
	public Player(String name, Board board) {
		this.name = name;
		this.board = board;
		location = board.getStartSquare();
		cash = INIT_CASH;
		netWorth = 0;
		observerIncome = new ObserverIncome("CashTransaction_"+name, this, INIT_CASH);
		observerIncome.propertyWriteEvent();
		observerOwnedSqure = new ObserverOwnedSqure("Property_"+name,this);
	}
	
	/**
	 * simulation run for each turn, output player, dice total and destination square
	 */
	public void takeTurn(){
		//roll dice
		Cup.roll();
		int fvTot = Cup.getTotal();
		
		location = board.getSquare(location, fvTot);
		System.out.println(name+": dice total = "+fvTot+" move to "+location.getName());
		location.landedOn(this);
	}
	
	public void attempPurchase(PropertySquare property){
		int price = property.getPrice();
		if (cash >= price){
			property.setOwner(this);
			reduceCash(price);
			properties.add(property);
			if (property instanceof RRSquare){
				RRCount++;
			}
			observerOwnedSqure.propertyWriteEvent();
			System.out.println(getName()+" buy "+property.getName()+" for $"+price);
		}
	}
	
	public Square getLocation(){
		return location;
	}
	
	public void setLocation(Square location){
		this.location = location;
	}
	
	public String getName(){
		return name;
	}
	
	public int getCash(){
		return cash;
	}
	
	public int getNetWorth(){
		return netWorth;
	}
	
	public void addCash(int c){
		cash+=c;
		observerIncome.propertyWriteEvent();
	}
	
	public void reduceCash(int c){
		cash-=c;
		observerIncome.propertyWriteEvent();
	}
	
	public void addNetWorth(int c){
		netWorth+=c;
	}
	
	public void reduceNetWorth(int c){
		netWorth-=c;
	}
	
	public int getRRCount(){
		return RRCount;
	}

	public List<PropertySquare> getProperties() {
		return properties;
	}
}
