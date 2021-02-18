/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Plays a game of Go Fish
 *
 * @author aidanhollington
 */
public class PlayGame {

    public static GoFish setUpGame(Scanner input) {
        // Create instance of GoFish
        GoFish game;

        // temporary array to hold player names
        String[] playerNames;

        // number of players
        int numOfPlayers;

        // prompt for number of players
        while (true) {
            System.out.print("How many players would like to play? ");
            numOfPlayers = input.nextInt();

            if (numOfPlayers < 2) {
                System.out.println("\nGo Fish requires at least two players.");
            } else {
                break;
            }
        }

        // set size of player names array according to number of players
        playerNames = new String[numOfPlayers];

        // ask each player for their name
        for (int i = 0; i < playerNames.length; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            playerNames[i] = input.next();
        }

        // Determine the hand size, seven for two or three players, five for 4 or more
        if (numOfPlayers > 3) {
            game = new GoFish(5);
        } else {
            game = new GoFish(7);
        }

        game.setUp(playerNames);

        return game;
    }

    public static void main(String[] args) {
        // there will be more here once the other classes are more complete!

        // create scanner class
        Scanner input = new Scanner(System.in);
        GoFish game = setUpGame(input);

        System.out.println();

        System.out.println(game.toString());

//        game.play();
    }
}
