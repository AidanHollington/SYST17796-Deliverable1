/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private ArrayList<Player> players;// the players of the game

    // list of possible suits
    private final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};

    // args constructor
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

    }

    /**
     * Prompt for and add a card to a specified player's hand
     *
     * @author aidanhollington
     * @param playerID which player to add to
     * @param input which Scanner object to use
     */
    public void addCard(int playerID, Scanner input) {

        String suit;
        int value;

        while (true) {
            System.out.print("Enter a suit (Hearts, Diamonds, Spades, Clubs): ");
            suit = input.next();

            if ((suit.equals(SUITS[0])) || (suit.equals(SUITS[1])) || (suit.equals(SUITS[2])) || (suit.equals(SUITS[3]))) {
                break;
            } else {
                System.out.println("Please enter a valid suit.");
            }

        }

        while (true) {
            System.out.print("Enter a value (1-13): ");
            value = input.nextInt();

            if (value >= 1 && value <= 13) {
                break;
            } else {
                System.out.println("Please enter a valid value.");
            }
        }

        this.players.get(playerID).cards.add(new Card(suit, value));

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

        Card c = new Card(suit, value);

        int index = this.players.indexOf(c);

        this.players.get(playerID).cards.remove(index);
    }

    /**
     * Move all cards of a specific suit from one player to another
     *
     * @author aidanhollington
     * @param source ID of player to move from
     * @param destination ID of player to move to
     * @param suit what suit should be moved
     * @return whether any cards were moved or not
     */
    public boolean moveCards(int source, int destination, String suit) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(source).cards.get(i).getSuit().equals(suit)) {
                players.get(destination).cards.add(players.get(source).cards.remove(i));
            } else {
                return false;
            }

        }
        return true;
    }

    /**
     * Move all cards of a specific value from one player to another
     *
     * @author aidanhollington
     * @param source ID of player to move from
     * @param destination ID of player to move to
     * @param value what value should be moved
     * @return whether any cards were moved or not
     */
    public boolean moveCards(int source, int destination, int value) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(source).cards.get(i).getValue() == value) {
                players.get(destination).cards.add(players.get(source).cards.remove(i));
            } else {
                return false;
            }
        }
        return true;
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
