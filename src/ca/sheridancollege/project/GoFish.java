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

    // args constructor
    public GoFish(int handSize) {
        // call super class
        super("GoFish", handSize);

    }

    @Override
    public void play() {

    }

    public void setUp(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            
        }

    }

    public void dealHands() {
        if (this.numOfPlayers < 2) {
            
        }
    }

    @Override
    public String toString() {
        String string = "temporary";

        return string;
    }

    @Override

    public void declareWinner() {
        
    }

}
