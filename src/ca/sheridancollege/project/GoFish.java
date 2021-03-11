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

    // random object
    Random ran = new Random();

    // determines if the game is active
    private boolean gameActive = true;

    // the players of the game
    private ArrayList<Player> players;

    // list of possible suits
    private final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};

    /**
     * Constructor for GoFish
     *
     * @author aidanhollington
     * @param handSize initial hand size
     */
    public GoFish(int handSize) {
        // call super class
        super("GoFish", handSize);
        this.handSize = handSize;

        // create ArrayList to hold each player's information
        this.players = new ArrayList();

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
        // create hand of cards
        // creates a number of cards (stored in handSize), all randomized, for each player
        for (int i = 0; i < this.players.size(); i++) {
            for (int j = 0; j < handSize; j++) {
                this.players.get(i).cards.add(new Card(SUITS[this.ran.nextInt(3)], this.ran.nextInt(13) + 1));
                this.cardsInPool -= 1;
            }
        }
    }

    /**
     * Creates a list of each card in a specified player's hand
     *
     * @author aidanhollington
     * @param playerNum which
     * @return formatted string
     */
    public String toString(int playerNum) {
        String string = this.players.get(playerNum).getPlayerID() + "\n";
        // print each card in the player's hand
        for (int j = 0; j < this.players.get(playerNum).cards.size(); j++) {
            string += this.players.get(playerNum).cards.get(j).getSuit() + " " + this.players.get(playerNum).cards.get(j).getValue() + "\n";
        }

        return string;
    }

    @Override
    /**
     * Plays the game
     *
     * @author aidanhollington
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
                System.out.println(cardsInPool + " card(s) remaining.");
                System.out.print("It is " + this.players.get(currentPlayer).getPlayerID() + "'s turn. Who do you want to ask? (1-" + this.players.size() + "): ");

                selectedPlayer = input.nextInt();

                System.out.println();
                // verify input
                if (selectedPlayer >= 1 && selectedPlayer <= this.players.size()) {
                    break;
                } else {
                    System.out.println("\nPlease enter a valid player number.");
                }
            }

            selectedPlayer = selectedPlayer - 1;

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

            // tell player how many cards they gained
            System.out.println("You gained " + moveCards(currentPlayer, selectedPlayer, selectedValue) + " cards of value " + selectedValue + " from " + this.players.get(selectedPlayer).getPlayerID() + ".");

            if (moveCards(currentPlayer, selectedPlayer, selectedValue) == 0) {
                System.out.println(this.players.get(selectedPlayer).getPlayerID() + " said Go Fish! They now get a random card from the pool.");
                addRandomCard(selectedPlayer);
            }

            if (checkForFourSuitsofSameRank(currentPlayer, selectedValue) == false) {
                currentPlayer++;
            } else {
                System.out.println("You have all four suits, you now get an extra turn!");
            }

            if (this.cardsInPool == 0) {
                this.gameActive = false;
            }

            currentPlayer = currentPlayer % 3;

            for (int z = 0; z < players.size(); z++) {
                System.out.println(toString(z));
            }

        } while (this.gameActive);

        declareWinner();

    }

    /**
     * Adds a random card to the specified player's hand
     *
     * @author aidanhollington
     * @param playerNum which player to add to
     */
    public void addRandomCard(int playerNum) {
        this.players.get(playerNum).cards.add(new Card(SUITS[this.ran.nextInt(3)], this.ran.nextInt(13) + 1));
        this.cardsInPool -= 1;
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
        int counter = 0;
        for (int j = 0; j < this.players.get(source).cards.size(); j++) {
            for (int i = 0; i < this.players.get(source).cards.size(); i++) {
                if (this.players.get(source).cards.get(i).getValue() == value) {

                    this.players.get(destination).cards.add(new Card(this.players.get(source).cards.get(i).getSuit(), this.players.get(source).cards.get(i).getValue()));
                    this.players.get(source).cards.remove(i);
                    cardsMoved++;

                    for (int z = 0; z < players.size(); z++) {
                        System.out.println(toString(z));
                    }

                }

                counter++;
            }
        }
        return cardsMoved;
    }

    /**
     * Check if a player has four suits of the same value
     *
     * @author aidanhollington
     * @param playerNum which player to add to
     * @param value which value to search for
     * @return number of cards found
     */
    public boolean checkForFourSuitsofSameRank(int playerNum, int value) {
        int cardsCounted = 0;
        Card[] cards = new Card[4];

        cards[0] = new Card("Hearts", value);
        cards[1] = new Card("Diamonds", value);
        cards[2] = new Card("Spades", value);
        cards[3] = new Card("Clubs", value);

        // search selected player ID 
        for (int i = 0; i < cards.length; i++) {
            if (this.players.get(playerNum).cards.indexOf(cards[i]) != -1) {
                cardsCounted++;
            }
        }

        if (cardsCounted == 4) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Declares a winner
     *
     * @author aidanhollington
     */
    @Override
    public void declareWinner() {
        System.out.println();

        for (int i = 0; i < this.players.size(); i++) {
            System.out.println(this.players.get(i).getPlayerID() + " has " + this.players.get(i).getSize() + " cards.");
        }

        System.out.println("Player with the most amount of cards wins!");
    }

}
