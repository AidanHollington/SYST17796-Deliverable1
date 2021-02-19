package ca.sheridancollege.project;

import ca.sheridancollege.project.Game;
import ca.sheridancollege.project.Player;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class which models a game of Go Fish
 *
 * @author aidanhollington
 */
public class GoFish extends Game {

    // instance variables
    private int numOfPlayers;
    private int handSize;
    private int cardsInPool = 52;
    private int numberOfTurns = 0;

    // determines if the game is active
    private boolean gameActive = true;

    // the players of the game
    private ArrayList<Player> players;

    // list of possible suits
    private final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};

    // constructor
    public GoFish(int handSize) {
        // call super class
        super("GoFish", handSize);
        this.handSize = handSize;

        // create ArrayList to hold each player's information
        this.players = new ArrayList();

    }

    /**
     * Returns the starting hand size for each player
     *
     * @return starting hand size
     */
    public int getHandSize() {
        return this.handSize;
    }

    /**
     * Creates a player object for each player, using their names
     *
     * @author aidanhollington
     * @param playerNames String array containing names of each player
     */
    public void setUp(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i], this.handSize));
        }
    }

    /**
     * Deals a full randomized hand to each player
     *
     * @author aidanhollington
     */
    public void dealHands() {
        // create Random class
        Random ran = new Random();

        // create hand of cards
        // creates a number of cards (stored in handSize), all randomized, for each player
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < handSize; j++) {
                this.players.get(i).cards.add(new Card(SUITS[ran.nextInt(3)], ran.nextInt(13) + 1));
                this.cardsInPool -= 1;
            }
        }
    }

    /**
     * Creates a list of each card in a specified player's hand
     *
     * @author aidanhollington
     * @param playerID
     * @return formatted string
     */
    public String toString(int playerID) {
        String string = this.players.get(playerID).getPlayerID() + "\n";
        // print each card in the player's hand
        for (int j = 0; j < this.players.get(playerID).cards.size(); j++) {
            string += this.players.get(playerID).cards.get(j).getSuit() + " " + this.players.get(playerID).cards.get(j).getValue() + "\n";
        }

        return string;
    }

    @Override
    /**
     * Plays the game
     *
     * @param input which Scanner object to use
     */
    public void play(Scanner input) {
        String playerString = "";

        int i = 0;

        int currentPlayer = 0;

        int selectedPlayer;
        int selectedValue;

        // create string representation of each player's name
        for (i = 1; i < this.players.size(); i++) {
            playerString += this.players.get(i - 1).getPlayerID() + " (player " + i + ")" + ", ";
        }
        playerString += this.players.get(i - 1).getPlayerID() + " (player " + (i) + ")";

        do {

            // ask player for a valid player number
            while (true) {
                // print player's names
                System.out.println("Players: " + playerString);
                System.out.print("It is " + this.players.get(currentPlayer).getPlayerID() + "'s turn. Who do you want to ask? (1-" + this.players.size() + "): ");

                selectedPlayer = input.nextInt() - 1;

                // verify input
                if (selectedPlayer >= 1 && selectedPlayer <= this.players.size()) {
                    break;
                } else {
                    System.out.println("\nPlease enter a valid player number.");
                }
            }

            // ask player for a valid card value
            while (true) {
                System.out.print("Which value of card do you want from " + this.players.get(selectedPlayer).getPlayerID() + "? (1-13) ");
                selectedValue = input.nextInt();

                if (selectedValue >= 1 && selectedValue <= 13) {
                    break;
                } else {
                    System.out.println("\nPlease enter a valid card value.");
                }
            }

            System.out.println("You gained " + moveCards(currentPlayer, selectedPlayer, selectedValue) + " cards of value " + selectedValue + " from " + this.players.get(selectedPlayer).getPlayerID() + ".");

        } while (this.gameActive);
    }

    /**
     * Prompt for and add a card to a specified player's hand
     *
     * @author aidanhollington
     * @param playerNum which player to add to
     * @param input which Scanner object to use
     */
    public void addCard(int playerNum, Scanner input) {

        String suit;
        int value;

        // ask for and verify desired suit
        // loop until a valid input is entered
        while (true) {
            System.out.print("Enter a suit (Hearts, Diamonds, Spades, Clubs): ");
            suit = input.next();

            if ((suit.equals(SUITS[0])) || (suit.equals(SUITS[1])) || (suit.equals(SUITS[2])) || (suit.equals(SUITS[3]))) {
                break;
            } else {
                System.out.println("Please enter a valid suit.");
            }

        }

        // ask for and verify desired value
        // loop until a valid input is entered
        while (true) {
            System.out.print("Enter a value (1-13): ");
            value = input.nextInt();

            if (value >= 1 && value <= 13) {
                break;
            } else {
                System.out.println("Please enter a valid value.");
            }
        }

        // add new Card object to selected player
        this.players.get(playerNum).cards.add(new Card(suit, value));

        // remove one card from the pool
        this.cardsInPool -= 1;

    }

    /**
     * Remove a specified card by it's suit and value
     *
     * @author aidanhollington
     * @param playerID which player to remove from
     * @param suit which suit to search for
     * @param value which value to search for
     */
    public void removeCards(int playerID, String suit, int value) {
        
        // create new card object with specified suit and value
        Card c = new Card(suit, value);

        // search selected player ID 
        int index = this.players.get(playerID).cards.indexOf(c);

        this.players.get(playerID).cards.remove(index);
    }

    /**
     * Move all cards of a specific suit from one player to another
     *
     * @author aidanhollington
     * @param source ID of player to move from
     * @param destination ID of player to move to
     * @param suit what suit should be moved
     * @return number of cards moved
     */
    public int moveCards(int source, int destination, String suit) {
        int cardsMoved = 0;

        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(source).cards.get(i).getSuit().equals(suit)) {
                this.players.get(destination).cards.add(this.players.get(source).cards.remove(i));
                cardsMoved++;
            }
        }
        return cardsMoved;
    }

    /**
     * Move all cards of a specific value from one player to another
     *
     * @author aidanhollington
     * @param source ID of player to move from
     * @param destination ID of player to move to
     * @param value what value should be moved
     * @return number of cards moved
     */
    public int moveCards(int source, int destination, int value) {
        int cardsMoved = 0;

        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(source).cards.get(i).getValue() == value) {
                this.players.get(destination).cards.add(this.players.get(source).cards.remove(i));
                cardsMoved++;
            }
        }
        return cardsMoved;
    }

    /**
     * Declares a winner
     *
     * @author aidanhollington
     */
    @Override
    public void declareWinner() {

    }

}
