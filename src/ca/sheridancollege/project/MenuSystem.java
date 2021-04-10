package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which creates a text-based menu for a card game
 *
 * @author aidanhollington
 */
public class MenuSystem {

    /**
     * Main menu for the game
     *
     * @author aidanhollington
     * @param input Scanner object to use
     */
    public String menu(ArrayList<Player> players, int currentPlayer, int selectedValue, Scanner input) {

        // stores the users selection
        String option;

        while (true) {
            // ask what the player wants to do
            System.out.print("It is " + players.get(currentPlayer).getPlayerID() + "'s turn. What do you want to do? (viewdeck/askforcard): ");
            option = input.next();

            // line break
            System.out.println();

            // verify input
            if (option.equals("viewdeck")) {
                return option;
            } else if (option.equals("askforcard")) {
                return option;
            } else {
                System.out.println("\nPlease enter a valid player number.");
            }

            // check if player has four suits of the same value
            if (checkForFourSuitsofSameRank(currentPlayer, selectedValue) == false) {
                currentPlayer++;
            } else {
                System.out.println("You have all four suits, you now get an extra turn!");
            }

            // make sure the current player index never goes above the max number of players for the session
            currentPlayer = currentPlayer % players.size();

        }
    }

    /**
     * Prints the contents of the selected players deck to the console
     *
     * @author aidanhollington
     * @param source which player to read
     * @param input which scanner object to pass on
     */
    public void viewDeck(ArrayList<Player> players, int source, Scanner input) {
        System.out.println(players.get(source).getPlayerID() + "'s cards:");

        for (int i = 0; i < players.get(source).cards.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + players.get(source).cards.get(i).getSuit() + ", " + players.get(source).cards.get(i).getValue());
        }
    }
    
    
        /**
     * Prompts the current player to request cards of a certain value from
     * another player
     *
     * @author aidanhollington
     * @param input which scanner object to use
     */
    public void askPlayerForCards(ArrayList<Player> players, int currentPlayer, int selectedPlayer, Scanner input) {
        while (true) {
            // ask which other player the player wants to ask
            System.out.print("It is " + players.get(currentPlayer).getPlayerID() + "'s turn. Who do you want to ask? (1-" + players.size() + "): ");
            selectedPlayer = input.nextInt();

            // line break
            System.out.println();

            // verify input
            if (selectedPlayer > 0 && selectedPlayer <= players.size()) {
                break;
            } else {
                System.out.println("\nPlease enter a valid player number.");
            }
        }

        // convert player number to index for ArrayList
        selectedPlayer = selectedPlayer - 1;

        // ask player for a valid card value
        while (true) {
            System.out.print("Which value of card do you want from " + players.get(selectedPlayer).getPlayerID() + "? (1-13) ");
            selectedValue = input.nextInt();

            if (selectedValue >= 1 && selectedValue <= 13) {
                break;
            } else {
                System.out.println("\nPlease enter a valid card value.");
            }
        }

        // move all cards of a selected value from the selected player to the current player
        int cardsMoved = moveCards(selectedPlayer, currentPlayer, selectedValue);

        if (cardsMoved == 0) {
            System.out.println(players.get(selectedPlayer).getPlayerID() + " did not have any cards with a value of " + selectedValue);
        } else {
            // if more than one card was moved, use cards (plural). otherwise, say card.
            if (cardsMoved == 1) {
                System.out.println(players.get(selectedPlayer).getPlayerID() + " took " + cardsMoved + " card with a value " + selectedValue);
            } else if (cardsMoved > 1) {
                System.out.println(players.get(selectedPlayer).getPlayerID() + " took " + cardsMoved + " cards with a value " + selectedValue);
            }

        }
    }
    
        /**
     * Prints a formatted list of each player, and the number of cards they have
     *
     * @author aidanhollington
     */
    public void printPlayerCardCounts(ArrayList<Player> players) {
        // tell players how many cards they have
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getPlayerID() + " has " + players.get(i).cards.size() + " cards.");
        }
    }
    
}
