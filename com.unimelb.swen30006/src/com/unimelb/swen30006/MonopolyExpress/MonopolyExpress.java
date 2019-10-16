/**
 * This class is for Workshop's exercises for SWEN30006 Software Design and Modelling subject at the University of Melbourne
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04
 * 
 */

package com.unimelb.swen30006.MonopolyExpress;
import java.util.ArrayList;
import java.util.Scanner;

import com.unimelb.swen30006.MonopolyExpress.Board.BoardGame;
import com.unimelb.swen30006.MonopolyExpress.Board.SquareSet;
import com.unimelb.swen30006.MonopolyExpress.Dice.*;
import com.unimelb.swen30006.MonopolyExpress.Rules.CompositeRuleStrategy;
import com.unimelb.swen30006.MonopolyExpress.Rules.IRuleStrategy;
import com.unimelb.swen30006.MonopolyExpress.Rules.StrategyFactory;


public class MonopolyExpress {

	public static void main(String[] args) {
		
		BoardGame board = new BoardGame();
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.add(new Player("A"));
		players.add(new Player("B"));
		
		Scanner in = new Scanner(System.in);
		
		boolean hasWinner = false;
		
		while(!hasWinner) {
			Player currentPlayer = players.remove(0);
			System.out.println("====== "+currentPlayer.getName()+"'s turn ====");

			ArrayList<Die> dieArrayList = new ArrayList<>();
			dieArrayList.add(new Die1());
			dieArrayList.add(new Die2());
			dieArrayList.add(new Die5());
			dieArrayList.add(new Die34());
			dieArrayList.add(new Die34());
			dieArrayList.add(new DiePolice());
			dieArrayList.add(new DiePolice());
			dieArrayList.add(new DiePolice());
			dieArrayList.add(new DieUtility());
			dieArrayList.add(new DieUtility());
			boolean turnEnds = false;
			do {
				//Roll the dice and show the faces
				for (Die die: dieArrayList) {
					die.roll();
				}
				//Check PoliceDice and place on the board
				for (Die die: dieArrayList) {
					if (die instanceof DiePolice)
						board.placeDie(die);
				}
				
				System.out.println(board.show());
				
				if(board.isAllFilled("Police")) {
					//do something
					turnEnds = true;
				}else {
					//Ask the player to pick the number dice
					int index = 0;
					int remainingDice = dieArrayList.size();
					
					do {
						System.out.println("------ Remaining Dice ----");
						//show dice faces
						for (Die die: dieArrayList) {
							System.out.println(die.getCurrentFaceName()+ " "+die.getCurrentFaceValue());
						}
						
						System.out.print("["+currentPlayer.getName()+"]Pick a number die (1-"+remainingDice+") or -1 (no pick):");
						index = in.nextInt();
						if (index != -1) {
							board.placeDie(dieArrayList.get(index));
							dieArrayList.remove(index);
						}
					}while(index != -1);
					
					
					System.out.print("["+currentPlayer.getName()+"] Keep rolling? (y/n):");
					String answer = in.next();
					
					if(answer.toLowerCase().equals("n")) {
						turnEnds = true;
					}
				}	
	
			}while(!turnEnds);
			System.out.println("Turn ends");

			//Calculate score
			IRuleStrategy strategy = StrategyFactory.getRuleStrategyInstance("composite", currentPlayer);

			currentPlayer.addScore(strategy.getScore(board));
			currentPlayer.newTurn();
			
			players.add(currentPlayer);
			board.reset();
		}
		
		in.close();
		
	}

	
	
}
