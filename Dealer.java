import java.awt.*;

/**
 * This is basically the same as a Player, but with automatic rules for
 * when to hit versus stand.
 *
 * @author Tristan Burchett
 * @version 1
 */
public class Dealer extends Player
{
    public Dealer(Card secretCard, Container container)
    {
        super("Dealer", secretCard, container);
    }
    
    /** 
     * @return true if the dealer isn't done playing
     */
    public boolean takeTurn(Deck deck, Container container) {
        if (totalValueOfHand() >= BlackJack.DEALER_MUST_HIT_IF_BELOW) {
            stand(container);
            return false;
        }
        else {
            hit(deck, container);
            return true;
        }
    }
}