import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    
    public static Queue<String> player1 = new LinkedList<String>();
    public static Queue<String> player2 = new LinkedList<String>();
    public static int roundCount = 0;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            System.err.println(cardp1);
            player1.add(cardp1);
        }
        
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            System.err.println(cardp2);
            player2.add(cardp2);
        }

        playGame();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
    
    public static void playGame(){
        if( player1.peek() == null ) {
            System.out.println( "2 " + roundCount);
        } else if ( player2.peek() == null ){
            System.out.println( "1 " + roundCount);
        }
        roundCount++;
        compareCards(player1.remove(), player2.remove(), new LinkedList<String>(), new LinkedList<String>());
        playGame();
        
    }
    
    public static void addToDeck(int winner, Queue<String> first, Queue<String> second){
        if( winner == 1) {
            while(first.peek() != null){
                player1.add(first.remove());
            }
            while(second.peek() != null){
                player1.add(second.remove());
            }
        } else {
            while(first.peek() != null){
                player2.add(first.remove());
            }
            while(second.peek() != null){
                player2.add(second.remove());
            }
        }
    }
    
    public static void compareCards(String currentP1Card, String currentP2Card, Queue<String> warDeck1, Queue<String> warDeck2){
        String comp1 = currentP1Card.substring(0, currentP1Card.length()-1);
        String comp2 = currentP2Card.substring(0, currentP2Card.length()-1);
        int cardValue1 = convertCardValue(comp1);
        int cardValue2 = convertCardValue(comp2);
        
        if( cardValue1 > cardValue2 ) {
            warDeck1.add(currentP1Card);
            warDeck2.add(currentP2Card);
            addToDeck(1, warDeck1, warDeck2);
        } else if (cardValue2 > cardValue1 ) {
            warDeck1.add(currentP1Card);
            warDeck2.add(currentP2Card);
            addToDeck(2, warDeck1, warDeck2);
        } else {
            war(currentP1Card, currentP2Card, warDeck1, warDeck2);
        }     
            
    }
    
    public static int convertCardValue(String card){
        if(card.equals("J")){
            return 11;
        } else if(card.equals("Q")){
            return 12;
        } else if(card.equals("K")){
            return 13;
        } else if(card.equals("A")){
            return 14;
        } else {
            return Integer.valueOf(card);
        }
    }
    
    public static void war(String currentP1Card, String currentP2Card, Queue<String> warDeck1, Queue<String> warDeck2){
        Queue<String> temp1 = buildWarDeck(player1, currentP1Card, warDeck1);
        Queue<String> temp2 = buildWarDeck(player2, currentP2Card, warDeck2);
        
        if( player1.peek() == null || player2.peek() == null ){
            System.out.println("PAT");
            System.exit(1);
        }
        
        compareCards(player1.remove(), player2.remove(), temp1, temp2);
         
    }
    
    public static Queue<String> buildWarDeck(Queue<String> deck, String card, Queue<String> warDeck){
        if( warDeck == null ) {
            warDeck = new LinkedList<String>();
        }
        warDeck.add(card);
        for(int i = 0 ; i < 3; i++){
            if( deck.peek() != null ){
                warDeck.add(deck.remove());
            } else {
                return null;
            }
        }
        
        return warDeck;
    }
}
