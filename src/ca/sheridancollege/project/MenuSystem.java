package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which creates a text-based mainMenu for a card game
 *
 * @author aidanhollington
 */
public class MenuSystem {

    /**
     * Main menu for the game
     *
     * @author aidanhollington
     * @param players which group of players to read from
     * @param currentPlayer which player has the current turn
     * @param input Scanner object to use
     */
    public String mainMenu(ArrayList<Player> players, int currentPlayer, Scanner input) {

        // stores the users selection
        String option;

        while (true) {
            // ask what the player wants to do
            System.out.print("It is " + players.get(currentPlayer).getPlayerID() + "'s turn. What do you want to do? (viewdeck/askforcard): ");
            option = input.next();

            // line break
            System.out.println();

            // verify input
            switch (option) {
                case "viewdeck":
                    return option;
                case "askforcard":
                    return option;
                default:
                    System.out.println("\nPlease enter a valid player number.");
                    break;
            }

        }
    }

    /**
     * Prints the contents of the selected players deck to the console
     *
     * @author aidanhollington
     * @param players which group of players to read from
     * @param source which player from the group to read from
     */
    public void viewDeck(ArrayList<Player> players, int source) {
        System.out.println(players.get(source).getPlayerID() + "'s cards:");

        for (int i = 0; i < players.get(source).cards.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + players.get(source).cards.get(i).getSuit() + ", " + players.get(source).cards.get(i).getValue());
        }

        // create line break
        System.out.println();

    }

    /**
     * Prompts the current player to request cards of a certain value from
     * another player
     *
     * @author aidanhollington
     * @param players which group of players to read from
     * @param input which scanner object to use
     */
    public int[] askPlayerForCards(ArrayList<Player> players, Scanner input) {

        int[] playerAndValue = new int[2];

        while (true) {
            // ask which other player the player wants to ask
            System.out.print("Who do you want to ask? (1-" + players.size() + "): ");
            playerAndValue[0] = input.nextInt();

            // verify input
            if (playerAndValue[0] > 0 && playerAndValue[0] <= players.size()) {
                break;
            } else {
                System.out.println("\nPlease enter a valid player number.");
            }
        }

        // convert entered player number to index for ArrayList
        playerAndValue[0] = playerAndValue[0] - 1;

        // ask player for a valid card value
        while (true) {
            System.out.print("Which value of card do you want from " + players.get(playerAndValue[0]).getPlayerID() + "? (1-13) ");
            playerAndValue[1] = input.nextInt();

            if (playerAndValue[1] >= 1 && playerAndValue[1] <= 13) {
                break;
            } else {
                System.out.println("\nPlease enter a valid card value.");
            }
        }

        // create line break
        System.out.println();

        return playerAndValue;

    }

    /**
     * Gets number of cards in a specified player's deck
     *
     * @param players which group of players to read from
     * @param source which player from the group to read from
     * @return card count of the player
     */
    public int getCardCountOfPlayer(ArrayList<Player> players, int source) {
        return players.get(source).cards.size();
    }

    /**
     * Prints a formatted list of each player, and the number of cards they have
     *
     * @author aidanhollington
     * @param players which group of players to read from
     */
    public void printPlayerCardCounts(ArrayList<Player> players) {
        // tell players how many cards they have
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getPlayerID() + " has " + getCardCountOfPlayer(players, i) + " cards.");
        }
    }

}
