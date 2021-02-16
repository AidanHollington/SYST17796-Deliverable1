/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 * Plays a selected card game
 *
 * @author aidanhollington
 */
public class PlayGame {

    public static void main(String[] args) {
        // there will be more here once the other classes are more complete!
        
        GoFish game = new GoFish();
        System.out.println("Test of Go Fish!");
        
        System.out.println(game.getGameName());
        
        game.play();
    }
}
