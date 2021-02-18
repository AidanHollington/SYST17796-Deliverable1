/**
 * SYST 17796 Project Winter 2021 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific child of
 * this class and instantiate the methods given.
 *
 * @author dancye
 * @author aidanhollington
 */
public abstract class Game extends Player {

    private final String gameName;//the title of the game
    
    public Game(String givenName, int handSize) {
        super("players", handSize);
        
        gameName = givenName;
    }

    /**
     * @return the gameName
     */
    public String getGameName() {
        return gameName;
    }

    
    /**
     * Play the game. This might be one method or many method calls depending on
     * your game.
     */
    public abstract void play(Scanner input);

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public abstract void declareWinner(); 

}//end class
