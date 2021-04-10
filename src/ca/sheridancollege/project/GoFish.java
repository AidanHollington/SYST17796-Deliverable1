package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class which models a game of Go Fish
 *
 * @author aidanhollington
 */
public class GoFish extends Game {
    
    // instance of MenuSystem
    MenuSystem menuSystem = new MenuSystem();

    // instance variables
    private int numOfPlayers;
    private final int handSize;
    private int cardsInPool = 52;

    // current player
    public int currentPlayer = 0;

    // player selected by current player
    public int selectedPlayer;

    // which value the current player selected
    public int selectedValue;

    // string representation of each player's name
    public String playerString = "";

    // random object
    Random ran = new Random();

    // determines if the game is active
    private boolean gameActive = true;

    // the players of the game
    public ArrayList<Player> players;

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
     * Creates a player object for each player, using names from a String array
     *
     * @author aidanhollington
     * @param playerNames String array containing names of each player
     */
    public void setup(String[] playerNames) {
        // set variable for number of players
        this.numOfPlayers = playerNames.length;

        for (String playerName : playerNames) {
            this.players.add(new Player(playerName, this.handSize));
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

    @Override
    /**
     * Plays the game
     *
     * @author aidanhollington
     * @param input which Scanner object to use
     */
    public void play(Scanner input) {
        // iterator
        int i = 0;

        // create string representation of each player's name
        for (i = 1; i < this.players.size(); i++) {
            this.playerString += this.players.get(i - 1).getPlayerID() + " (player " + i + ")" + ", ";
        }

        this.playerString += this.players.get(i - 1).getPlayerID() + " (player " + (i) + ")";

        // main game loop
        do {
            // tell each player how many cards they have
            printPlayerCardCounts();

            // if there are no more cards in the pool, exit the game
            if (this.cardsInPool == 0) {
                this.gameActive = false;
            }

            // print how many cards are remaining in the pool
            System.out.println(this.cardsInPool + " card(s) remaining in pool.");

            // enter menu system
            this.menu(input);

        } while (this.gameActive);

        // when game is over, declare winner
        declareWinner();

    }

    /**
     * Adds a random card to the specified player's hand
     *
     * @author aidanhollington
     * @param destination which player to add to
     */
    public void takeCardFromPool(int destination) {
        this.players.get(destination).cards.add(new Card(SUITS[this.ran.nextInt(3)], this.ran.nextInt(13) + 1));
        this.cardsInPool -= 1;
    }

    /**
     * Adds a card with a specified suit and value to a specified player
     *
     * @author aidanhollington
     * @param cards ArrayList containing Card objects
     * @param destination which player to add to
     */
    public void addCard(ArrayList<Card> cards, int destination) {
        this.players.get(destination).cards.addAll(cards);
    }

    /**
     * Take all cards of a specified value from a specified player
     *
     * @author aidanhollington
     * @param value which value of card(s) to take
     * @param source which player to take from
     * @return all of the cards removed
     */
    public ArrayList<Card> takeCards(int value, int source) {

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

        return cardBuffer;

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

        // take cards from source player and store them in cardBuffer
        ArrayList<Card> cardBuffer = this.takeCards(value, source);

        // if no cards were moved, say go fish
        if (cardBuffer.isEmpty()) {
            System.out.println(this.players.get(source).getPlayerID() + " goes Go Fish!");
            takeCardFromPool(source);
        } else if (cardBuffer.isEmpty() == false) {
            // if cards were taken from source player, add all cards from source to destination player
            this.addCard(cardBuffer, destination);
        }

        // return number of cards moved in operation
        return cardBuffer.size();
    }

    /**
     * Check if a player has four suits of the same value
     *
     * @author aidanhollington
     * @param source which player to check
     * @param value which value to search for
     * @return whether four suits of the same rank were found
     */
    public boolean checkForFourSuitsofSameRank(int source, int value) {
        boolean hearts = false;
        boolean diamonds = false;
        boolean spades = false;
        boolean clubs = false;

        // loop though players entire deck of cards and check if there are four cards of the same suit
        for (int i = 0; i < this.players.get(source).cards.size(); i++) {

            //check if card has a suit of hearts, and if so flag it
            if (this.players.get(source).cards.get(i).getSuit().equals(SUITS[0]) && this.players.get(source).cards.get(i).getValue() == value) {
                hearts = true;
            }

            //check if card has a suit of diamonds, and if so flag it
            if (this.players.get(source).cards.get(i).getSuit().equals(SUITS[1]) && this.players.get(source).cards.get(i).getValue() == value) {
                diamonds = true;
            }

            //check if card has a suit of spades, and if so flag it
            if (this.players.get(source).cards.get(i).getSuit().equals(SUITS[2]) && this.players.get(source).cards.get(i).getValue() == value) {
                spades = true;
            }

            //check if card has a suit of clubs, and if so flag it
            if (this.players.get(source).cards.get(i).getSuit().equals(SUITS[3]) && this.players.get(source).cards.get(i).getValue() == value) {
                clubs = true;
            }

        }

        // if cards were found, return true, otherwise return false
        if (hearts == true && diamonds == true && spades == true && clubs == true) {
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
