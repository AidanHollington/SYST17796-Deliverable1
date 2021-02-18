/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import ca.sheridancollege.project.Game;
import ca.sheridancollege.project.Player;
import java.util.ArrayList;

/**
 *
 * @author aidanhollington
 */
public class GoFish extends Game {

    private int numOfPlayers;
    private int handSize;
    private ArrayList<Player> players;// the players of the game

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
        players.get(0).addCard("Hearts", 3);
    }

    public void setUp(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i], this.handSize));
        }

    }

    public void dealHands() {

    }

    @Override
    public String toString() {
        String string = players.get(0).getPlayerID();

        return string;
    }

    @Override
    public void declareWinner() {

    }

}
