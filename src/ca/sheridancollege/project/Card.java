/**
 * SYST 17796 Project Winter 2021 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the
 * code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @author aidanhollington
 */
public class Card {
    //default modifier for child classes

    // instance variables
    private String suit; //clubs, spades, diamonds, hearts
    private int value;//1-13

    // constructor
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }
        
    /**
     * @return the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
