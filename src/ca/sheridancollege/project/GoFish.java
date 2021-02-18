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
 * Class modelling a game of Go Fish
 * @author aidanhollington
 */
public class GoFish extends Game {

    // instance variables
    private int numOfPlayers;
    private int handSize;
    private ArrayList<Player> players;// the players of the game
    
    

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
     * @return starting hand size
     */
    public int getHandSize() {
        return this.handSize;
    }

    /**
     * Creates a player object for each player, using their names
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
     * @author aidanhollington
     */
    public void dealHands() {
        // create Random class
        Random ran = new Random();
        
        // create hand of cards
        final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};

        // creates a number of cards (stored in handSize), all randomized, for each player
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < handSize; j++) {
                this.players.get(i).cards.add(new Card(SUITS[ran.nextInt(3)], ran.nextInt(13) + 1));
            }
        }

    }

    /**
     * Creates a list of each card in a specified player's hand
     * @author aidanhollington
     * @param playerId
     * @return formatted string
     */
    public String toString(int playerId) {
        String string = "";

        // print each card in the player's hand
        for (int j = 0; j < this.players.get(playerId).cards.size(); j++) {
            string += this.players.get(playerId).cards.get(j).getSuit() + " " + this.players.get(playerId).cards.get(j).getValue() + "\n";
        }

        return string;
    }

    @Override
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
        System.out.print("Enter a suit: ");
        String suit = input.next();

        System.out.print("Enter a value: ");
        int value = input.nextInt();

        this.players.get(playerID).cards.add(new Card(suit, value));

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
     */
    public void moveCards(int source, int destination, String suit) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(source).cards.get(i).getSuit().equals(suit)) {
                players.get(destination).cards.add(players.get(source).cards.remove(i));
            }
        }
    }

    /**
     * Move all cards of a specific value from one player to another
     *
     * @author aidanhollington
     * @param source ID of player to move from
     * @param destination ID of player to move to
     * @param value what value should be moved
     */
    public void moveCards(int source, int destination, int value) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(source).cards.get(i).getValue() == value) {
                players.get(destination).cards.add(players.get(source).cards.remove(i));
            }
        }
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
