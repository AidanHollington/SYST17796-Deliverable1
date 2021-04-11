/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aidanhollington
 */
public class GoFishTest {

    // list of player names
    public String[] playerNames = {"Player1", "Player2", "Player3"};

    // which hand size to use for GoldFish instances
    public int handSize = 7;

    public GoFishTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of takeCards method, of class GoFish.
     */
    @Test
    public void testTakeCardsGood() {
        System.out.println("takeCards (Good)");

        // player id to check
        int source = 0;

        // create new instance of GoFish
        GoFish instance = new GoFish(handSize);

        // create new Card ArrayList to contain the expected result
        ArrayList<Card> expResult = new ArrayList();

        // setup 
        instance.setup(this.playerNames);

        // add two new cards
        expResult.add(new Card("Hearts", 5));
        expResult.add(new Card("Spades", 13));

        // add expected result to player 1
        instance.addCard(expResult, 0);

        // create Card ArrayList to hold results
        ArrayList<Card> result = new ArrayList();

        // copy cards from player to result
        result.add(instance.getCardAtIndex(source, 0));
        result.add(instance.getCardAtIndex(source, 1));

        // check if two cards added to player 1 are as expected
        assertEquals(expResult, result);
    }

    /**
     * Test of takeCards method, of class GoFish.
     */
    @Test
    public void testTakeCardsBad() {
        System.out.println("takeCards (Bad)");

        // player id to check
        int source = 0;

        // create new instance of GoFish
        GoFish instance = new GoFish(handSize);

        // create new Card ArrayList to contain the expected result
        ArrayList<Card> expResult = new ArrayList();

        // create new Card ArrayList to temporarily hold cards
        ArrayList<Card> cards = new ArrayList();

        // setup 
        instance.setup(this.playerNames);

        // add two new cards
        expResult.add(new Card("Hearts", 5));

        // add two new cards
        cards.add(new Card("Hearts", 5));
        cards.add(new Card("Spades", 13));

        // add expected result to player 1
        instance.addCard(expResult, 0);

        // create Card ArrayList to hold results
        ArrayList<Card> result = new ArrayList();

        // copy cards from player to result
        result.add(instance.getCardAtIndex(source, 0));

        // check if two cards added to player 1 are as expected
        assertEquals(expResult, result);
    }

    /**
     * Test of moveCards method, of class GoFish.
     */
    @Test
    public void testMoveCardsGood() {
        System.out.println("moveCards (Good)");

        // player id to transfer from
        int source = 0;

        // player id to transfer to
        int destination = 2;

        // create new instance of GoFish
        GoFish instance = new GoFish(handSize);

        // create new Card ArrayList to contain the expected result
        ArrayList<Card> expResult = new ArrayList();

        // ArrayList to hold initial hands for test players
        ArrayList<Card> player1Cards = new ArrayList();

        // ArrayList to hold expected results
        ArrayList<Card> result = new ArrayList();

        // setup 
        instance.setup(this.playerNames);

        // add expected results
        expResult.add(new Card("Hearts", 5));
        expResult.add(new Card("Diamonds", 10));
        expResult.add(new Card("Spades", 8));
        expResult.add(new Card("Clubs", 13));

        // add cards to player 1
        player1Cards.add(new Card("Hearts", 5));
        player1Cards.add(new Card("Diamonds", 10));
        player1Cards.add(new Card("Spades", 8));
        player1Cards.add(new Card("Clubs", 13));

        // add cards to player 1
        instance.addCard(player1Cards, source);

        // move cards from player 1 to player 3
        instance.moveCards(source, destination, 5);
        instance.moveCards(source, destination, 10);
        instance.moveCards(source, destination, 8);
        instance.moveCards(source, destination, 13);

        // add all cards from player 2 to result ArrayList
        result.addAll(instance.takeCards(5, destination));
        result.addAll(instance.takeCards(10, destination));
        result.addAll(instance.takeCards(8, destination));
        result.addAll(instance.takeCards(13, destination));

        // check each card in expResult to make sure it matches the corresponding one in result
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getSuit(), result.get(i).getSuit());
            assertEquals(expResult.get(i).getValue(), result.get(i).getValue());
        }

    }

    /**
     * Test of moveCards method, of class GoFish.
     */
    @Test
    public void testMoveCardsBad() {
        System.out.println("moveCards (Bad)");

        // player id to transfer from
        int source = 0;

        // player id to transfer to
        int destination = 2;

        // create new instance of GoFish
        GoFish instance = new GoFish(handSize);

        // create new Card ArrayList to contain the expected result
        ArrayList<Card> expResult = new ArrayList();

        // ArrayList to hold initial hands for test players
        ArrayList<Card> player1Cards = new ArrayList();

        // ArrayList to hold expected results
        ArrayList<Card> result = new ArrayList();

        // setup 
        instance.setup(this.playerNames);

        // add expected results
        expResult.add(new Card("Hearts", 5));
        expResult.add(new Card("Diamonds", 10));
        expResult.add(new Card("Spades", 8));

        // add cards to player 1
        player1Cards.add(new Card("Hearts", 5));
        player1Cards.add(new Card("Diamonds", 10));
        player1Cards.add(new Card("Spades", 8));
        player1Cards.add(new Card("Clubs", 13));

        // add cards to player 1
        instance.addCard(player1Cards, source);

        // move cards from player 1 to player 3
        instance.moveCards(source, destination, 5);
        instance.moveCards(source, destination, 10);
        instance.moveCards(source, destination, 8);

        // add all cards from player 2 to result ArrayList
        result.addAll(instance.takeCards(5, destination));
        result.addAll(instance.takeCards(10, destination));
        result.addAll(instance.takeCards(8, destination));
        result.addAll(instance.takeCards(13, destination));

        // check each card in expResult to make sure it matches the corresponding one in result
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i).getSuit(), result.get(i).getSuit());
            assertEquals(expResult.get(i).getValue(), result.get(i).getValue());
        }

    }

    /**
     * Test of checkForFourSuitsofSameRank method, of class GoFish.
     */
    @Test
    public void testCheckForFourSuitsofSameRankGood() {
        System.out.println("checkForFourSuitsofSameRank (Good)");
        int source = 2;
        int value = 6;

        // expected result
        boolean expResult = true;

        // create new instance of GoFish
        GoFish instance = new GoFish(handSize);

        // create players in instance of GoFish
        instance.setup(playerNames);

        // create Card ArrayList to temporarily hold cards
        ArrayList<Card> cards = new ArrayList();

        // create four cards of different suits but identical values
        cards.add(new Card("Hearts", value));
        cards.add(new Card("Diamonds", value));
        cards.add(new Card("Spades", value));
        cards.add(new Card("Clubs", value));

        // add all of the cards to player
        instance.addCard(cards, source);

        // check if true
        boolean result = instance.checkForFourSuitsofSameRank(source, value);

        // check for expected result
        assertEquals(expResult, result);

    }

    /**
     * Test of checkForFourSuitsofSameRank method, of class GoFish.
     */
    @Test
    public void testCheckForFourSuitsofSameRankBad() {
        System.out.println("checkForFourSuitsofSameRank (Bad)");
        int source = 2;
        int value = 6;

        // expected result
        boolean expResult = false;

        // create new instance of GoFish
        GoFish instance = new GoFish(handSize);

        // create players in instance of GoFish
        instance.setup(playerNames);

        // create Card ArrayList to temporarily hold cards
        ArrayList<Card> cards = new ArrayList();

        // create four cards of identical suits and values
        cards.add(new Card("Hearts", value));
        cards.add(new Card("Hearts", value));
        cards.add(new Card("Hearts", value));
        cards.add(new Card("Hearts", value));

        // add all of the cards to player
        instance.addCard(cards, source);

        // check if false
        boolean result = instance.checkForFourSuitsofSameRank(source, value);

        // check for expected result
        assertEquals(expResult, result);

    }

}
