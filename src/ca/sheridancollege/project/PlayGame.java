package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Plays a game of Go Fish
 *
 * @author aidanhollington
 */
public class PlayGame {

    public static void main(String[] args) {
        // create Scanner class
        Scanner input = new Scanner(System.in);

        // Create instance of GoFish
        GoFish game;

        // temporary array to hold player names
        String[] playerNames;

        // number of players
        String numOfPlayers;

        // prompt for number of players
        while (true) {
            System.out.print("How many players would like to play? ");

            // try to parse user input as int. if an exception is thrown, ask
            // the user to enter a number, else check if the number of players
            // is 2 or more
            try {
                numOfPlayers = input.next();

                if (Integer.parseInt(numOfPlayers) < 2) {
                    System.out.println("\nGo Fish requires at least two players.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\nPlease enter a number.");
            }

        }

        // set size of player names array according to number of players
        playerNames = new String[Integer.parseInt(numOfPlayers)];

        // ask each player for their name
        for (int i = 0; i < playerNames.length; i++) {
            System.out.print("Enter the name of player " + (i + 1) + ": ");
            playerNames[i] = input.next();
        }

        // Determine the hand size, seven for two or three players, five for 4 or more
        if (Integer.parseInt(numOfPlayers) > 3) {
            game = new GoFish(5);
        } else {
            game = new GoFish(7);
        }

        // create the players
        game.setUp(playerNames);

        // deal hands to each player
        game.dealHands();

        // start the game
        game.play(input);

    }
}
