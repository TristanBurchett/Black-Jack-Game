import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;

/**
 * This class draws the table, including the hands of both players, and buttons
 * to hit or stand.
 * 
 * @author Tristan Burchett
 * @version 1
 */
public class BlackjackGUI extends JFrame 
{
    Deck deck = new Deck();
    Player player;
    Dealer dealer;
    
    public BlackjackGUI() {
        // sets up the game with a background image and text displaying where cards are
        JFrame frame = new JFrame();
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Blackjack Game");
        Container table = new ImageBackground(new ImageIcon("C:\\Users\\limin\\Downloads\\casinoTable.jpg"));
        setContentPane(table);
        
        table.setLayout(new GridLayout(2,1));
        Container playerPanel = new ImageBackground(new ImageIcon("C:\\Users\\limin\\Downloads\\casinoTable.jpg"));
        Container dealerPanel = new ImageBackground(new ImageIcon("C:\\Users\\limin\\Downloads\\casinoTable.jpg"));
        playerPanel.setLayout(new FlowLayout());
        dealerPanel.setLayout(new FlowLayout());
        add(playerPanel);
        add(dealerPanel);
        
        JLabel labelPlayer = new JLabel("Player's Cards");
        playerPanel.add(labelPlayer);
        
        JLabel labelDealer = new JLabel("Dealer's Cards");
        dealerPanel.add(labelDealer);
        
        // TODO: add labels showing scores
        
        // buttons for the player
        JButton hitButton = new JButton("Hit");
        JButton standButton = new JButton("Stand");
        
        playerPanel.add(hitButton);
        playerPanel.add(standButton);
        
        player = new Player("Player", deck.getCard(), playerPanel);
        dealer = new Dealer(deck.getCard(), dealerPanel);
        
        // starting messages
        JOptionPane.showMessageDialog(null, "Welcome to Blackjack 2024 at Big Money Casino LLC!",
             "", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "You start with a face-up card and a secret card (not shown). Try to get as close to 21 without going over.", 
             "", JOptionPane.INFORMATION_MESSAGE);
        
        player.drawCard(deck.getCard(), playerPanel); // get an initial card
        dealer.drawCard(deck.getCard(), dealerPanel); // get an initial card
        
        // if a player hits, they get a card and their cards are displayed along with their score
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.hit(deck, playerPanel);
                JOptionPane.showMessageDialog(null, "Player's current total is " + player.totalValueOfHand() + "\n", 
                    "", JOptionPane.INFORMATION_MESSAGE);
                    
                dealer.takeTurn(deck, dealerPanel);
                
                if (player.totalValueOfHand() > BlackJack.LOSE_IF_SCORE_IS_OVER) {
                    JOptionPane.showMessageDialog(null, "You went over 21!\nYour score is " + player.totalValueOfHand(), 
                        "", JOptionPane.INFORMATION_MESSAGE);
                    hitButton.setEnabled(false);
                    standButton.setEnabled(false);
                }
            }
        });
        
        // if a player stands, their score will be displayed and they won't be able to hit any more buttons as the game ends
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stand(playerPanel);
                JOptionPane.showMessageDialog(null, "Player's score was " + player.totalValueOfHand(), 
                    "", JOptionPane.INFORMATION_MESSAGE);
                    
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
                
                while (dealer.takeTurn(deck, dealerPanel)) {
                    // keep going until the dealer is done playing
                }
                
                BlackJack.getWinner(player, dealer);
            }
        });
        
        setVisible(true);
    }
    
    public static void main(String args[]) {
        new BlackjackGUI();
    }
}