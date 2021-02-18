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

/**
 *
 * @author aidanhollington
 */
public class GoFish extends Game {

    private int numOfPlayers;
    private int handSize;
    private ArrayList<Player> players;// the players of the game

    Random ran = new Random();

    // args constructor
    public GoFish(int handSize) {
        // call super class
        super("GoFish", handSize);
        this.handSize = handSize;

        // create ArrayList to hold each player's information
        players = new ArrayList();

    }

    public int getHandSize() {
        return this.handSize;
    }

    @Override
    public void play() {

    }

    public void setUp(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i], this.handSize));
        }
    }

    public void dealHands() {
        // create hand of cards
        final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < handSize; j++) {
                this.players.get(i).cards.add(new Card(SUITS[this.ran.nextInt(3)], this.ran.nextInt(13) + 1));
            }
        }

    }

    public String toString(int playerId) {

        String string = "";

        // print each card in the player's hand
        for (int j = 0; j < players.get(playerId).cards.size(); j++) {
            string += this.players.get(playerId).cards.get(j).getSuit() + " " + this.players.get(playerId).cards.get(j).getValue() + "\n";
        }

        return string;
    }

    @Override
    public void declareWinner() {

    }

}
