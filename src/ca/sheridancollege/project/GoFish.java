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
    public GoFish() {
        // call super class
        super("GoFish");

    }

    @Override
    /**
     * Play the game. This might be one method or many method calls depending on
     * your game.
     */
    public void play() {
        
    }
    
    public void setUp() {
        
        System.out.println("How many players would like to play?");
    }
    
    
    @Override
    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public void declareWinner() {

    }

}
