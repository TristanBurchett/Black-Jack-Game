import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Encapsulates all the rules of BlackJack.
 * 
 * @author Tristan Burchett
 * @version 1
 */
public class BlackJack
{
    public static final int DEALER_MUST_HIT_IF_BELOW = 17;
    public static final int LOSE_IF_SCORE_IS_OVER = 21;
    
    public static int totalValueOfHand(ArrayList<Card> hand) {
        int total = 0;
        
        for(Card card : hand) {
            if (!card.isFaceDown())
                total += card.getPointValue();
        }
        
        // modify the value of any aces in hand to maximize the player's chances of winning
        // aces can have a value of 1 or 11 so pick whichever value gets the player closer to 21,
        // without going over
        for(Card card : hand) {
            if(!card.isFaceDown() && card.getRank().equals("Ace") && total+10 <= 21) {
                total+=10; // ace value going from 1 to 11 if possible
            }
        }
        
        return total;
    }

    // uses the rules of Blackjack to find the winner between the 2 players
    public static void getWinner(Player player1, Player player2) {
        int player1Total = player1.totalValueOfHand();
        int player2Total = player2.totalValueOfHand();

        String message;
        if ((player1Total > player2Total) && (player1Total <= 21 && player2Total <= 21)) {
            message = player1.getName() + " wins!";
        }
                
        else if ((player1Total < player2Total) && (player1Total <= 21 && player2Total <= 21)) {
            message = player2.getName() + " wins!";
        }
                
        else if (player1Total > 21 && player2Total <= 21) {
            message = player2.getName() + " wins!"; // automatic win
        }
                
        else if (player2Total > 21 && player1Total <= 21) {
            message = player1.getName() + " wins!"; // automatic win
        }
        
        else if (player1Total > 21 && player2Total > 21) {
            message = "Both players lose";
        }
        
        else if (player1Total == player2Total && (player1Total <= 21 && player2Total <= 21)) {
            message = "Tie";
        }
        
        else {
            message = "Should not get here!";
        }
        
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
    }
}
