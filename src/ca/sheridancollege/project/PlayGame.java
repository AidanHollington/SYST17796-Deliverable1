/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Plays a selected card game
 *
 * @author aidanhollington
 */
public class PlayGame {

    public static void main(String[] args) {
        // there will be more here once the other classes are more complete!

        // create scanner class
        Scanner input = new Scanner(System.in);

        // temporary array to hold player names
        String[] playerNames;

        // instantiate new instance of GoFish
        GoFish game = new GoFish();

        // ask players to input their names
        System.out.print("How many players would like to play? ");
        int numOfPlayers = input.nextInt();

        // set size of player names array according to number of players
        playerNames = new String[numOfPlayers];

        for (int i = 0; i < playerNames.length; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            playerNames[i] = input.next();
        }

        for (int z = 0; z < playerNames.length; z++) {
            System.out.println(playerNames[z]);
        }
        
        
        game.setUp(playerNames);
        
        game.play();
    }
}
