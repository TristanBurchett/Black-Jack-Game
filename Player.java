import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * This class controls the hand and cards of a player.
 * 
 * @author Tristan Burchett
 * @version 1
 */
public class Player
{
    private String name;
    private Card secretCard;
    private ArrayList<Card> cardsDown = new ArrayList<Card>();  // includes secretCard
    private boolean standing = false;

    public Player(String name, Card secretCard, Container container)
    {
        this.name = name;
        this.secretCard = secretCard;
        secretCard.setFaceDown(true);

        drawCard(secretCard, container); 
    }
    
    public String getName() {
        return name;
    }

    public void drawCard(Card card, Container container) {
        cardsDown.add(card);
        showCard(card, container);
    }
    
    // returns whether a hand has an ace in it
    private boolean containsAce(ArrayList<Card> cardsDown) {
        for(Card card : cardsDown) {
            if(card.getRank().equals("Ace")) {
                return true;
            }
        }
        
        return false;
    }
    
    // returns the total points within a hand
    public int totalValueOfHand() {
        return BlackJack.totalValueOfHand(cardsDown);
    }
    
    // Displays an image representing a cards in the player's hand.
    private void showCard(Card card, Container container) {
        container.add(card);
        container.revalidate();
    }
    
    public void hit(Deck deck, Container container) {
        Card newCard = deck.getCard();
        drawCard(newCard, container);
    }
    
    public void stand(Container container) {
        standing = true;
        secretCard.setFaceDown(false);
        showCard(secretCard, container);
    }
    
    public boolean isStanding() {
        return standing;
    }
    
}
