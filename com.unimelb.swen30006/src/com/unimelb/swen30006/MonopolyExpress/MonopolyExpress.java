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
			
			boolean turnEnds = false;
			do {
				//Roll the dice and show the faces
				
				//Check PoliceDice and place on the board

				
				System.out.println(board.show());
				
				if(board.isAllFilled("Police")) {
					//do something
				}else {
					//Ask the player to pick the number dice
					int index = 0;
					int remainingDice = 0;
					
					do {
						System.out.println("------ Remaining Dice ----");
						//show dice faces
						
						
						System.out.print("["+currentPlayer.getName()+"]Pick a number die (1-"+remainingDice+") or -1 (no pick):");
						index = in.nextInt();
						
						
						
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
			
			
			players.add(currentPlayer);
			board.reset();
		}
		
		in.close();
		
	}

	
	
}
