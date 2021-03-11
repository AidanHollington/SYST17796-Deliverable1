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
     * Returns number of cards in a player's hand
     *
     * @author aidanhollington
     * @param player which player to select
     * @return number of cards in hand of selected player
     */
    public int getNumOfCards(int player) {
        return this.players.get(player).cards.size();
    }

    /**
     * Prints a formatted list of each player, and the number of cards they have
     *
     * @author aidanhollington
     */
    public void printPlayerCardCounts() {
        // tell players how many cards they have
        for (int i = 0; i < this.players.size(); i++) {
            System.out.println(this.players.get(i).getPlayerID() + " has " + getNumOfCards(i) + " cards.");
        }
    }

    @Override
    /**
     * Plays the game
     *
     * @author aidanhollington
     * @param input which Scanner object to use
     */
    public void play(Scanner input) {
        // string representation of each player's name
        String playerString = "";

        int i = 0;

        // current player
        int currentPlayer = 0;

        // player selected by current player
        int selectedPlayer;
        
        // which value the current player selected
        int selectedValue;

        // create string representation of each player's name
        for (i = 1; i < this.players.size(); i++) {
            playerString += this.players.get(i - 1).getPlayerID() + " (player " + i + ")" + ", ";
        }

        playerString += this.players.get(i - 1).getPlayerID() + " (player " + (i) + ")";

        // main game loop
        do {
            printPlayerCardCounts();

            if (this.cardsInPool == 0) {
                this.gameActive = false;
            }

            // ask player for a valid player number
            while (true) {

                // print how many cards are remaining in the pool
                System.out.println(cardsInPool + " card(s) remaining in pool.");

                // ask which other player the player wants to ask
                System.out.print("It is " + this.players.get(currentPlayer).getPlayerID() + "'s turn. Who do you want to ask? (1-" + this.players.size() + "): ");
                selectedPlayer = input.nextInt();

                // line break
                System.out.println();

                // verify input
                if (selectedPlayer >= 1 && selectedPlayer <= this.players.size()) {
                    break;
                } else {
                    System.out.println("\nPlease enter a valid player number.");
                }
            }

            // convert player number to index for ArrayList
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

            // check if player has four suits of the same value
            if (checkForFourSuitsofSameRank(currentPlayer, selectedValue) == false) {
                currentPlayer++;
            } else {
                System.out.println("You have all four suits, you now get an extra turn!");
            }

            currentPlayer = currentPlayer % 3;

        } while (this.gameActive);

        // if game is over, declare winner
        declareWinner();

    }

    /**
     * Adds a random card to the specified player's hand
     *
     * @author aidanhollington
     * @param playerNum which player to add to
     */
    public void takeCardFromPool(int playerNum) {
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
    public void moveCards(int source, int destination, int value) {
        // how many card checks were performed
        int iterations = 0;

        // buffer to hold moved cards
        ArrayList<Card> cardBuffer = new ArrayList<Card>();

        // take card(s) from source player and place them in buffer
        do {
            for (int i = 0; i < this.players.get(source).cards.size(); i++) {
                if (this.players.get(source).cards.get(i).getValue() == value) {
                    cardBuffer.add(this.players.get(source).cards.get(i));

                    this.players.get(source).cards.remove(i);
                }
            }
            iterations++;
        } while (iterations < Math.pow(this.players.get(source).cards.size(), 2));

        // add all cards from buffer to destination player
        this.players.get(destination).cards.addAll(cardBuffer);

        // if no cards were moved, say go fish
        if (cardBuffer.size() == 0) {
            System.out.println(this.players.get(source).getPlayerID() + " goes Go Fish!");
            takeCardFromPool(source);
        }

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
