import java.util.ArrayList;
import java.io.File;
import java.util.Random;

/**
 * Write a description of class Deck here.
 *
 * @author Tristan Burchett
 * @version 1
 */
public class Deck
{
    public static ArrayList<Card> cards = new ArrayList<Card>();
    
    public Deck() {        
        for(int i = 1; i <= 52; i++) {
            // For now all cards are clubs.  Suits don't really matter for blackjack.
            if(i >=1 && i <=4) {cards.add(new Card("Ace", 1, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\aceclubs.GIF"), false));}
            if(i >=5 && i <=8) {cards.add(new Card("Two", 2, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\2clubs.GIF"), false));}
            if(i>=9 && i<=12)  {cards.add(new Card("Three", 3, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\3clubs.GIF"), false));}
            if(i >=13 && i <=16) {cards.add(new Card("Four", 4, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\4clubs.GIF"), false));}
            if(i >=17 && i <=20) {cards.add(new Card("Five", 5, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\5clubs.GIF"), false));}
            if(i>=21 && i <= 24) {cards.add(new Card("Six", 6, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\6clubs.GIF"), false));}
            if(i >=25 && i <=28) {cards.add(new Card("Seven", 7, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\7clubs.GIF"), false));}
            if(i >=29 && i <=32) {cards.add(new Card("Eight", 8, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\8clubs.GIF"), false));}
            if(i >=33 && i <=36) {cards.add(new Card("Nine", 9, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\9clubs.GIF"), false));}
            if(i >=37 && i <=40) {cards.add(new Card("Ten", 10, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\10clubs.GIF"), false));}
            if(i >=41 && i <=44) {cards.add(new Card("Jack", 10, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\jackclubs.GIF"), false));}
            if(i >=45 && i <=48) {cards.add(new Card("Queen", 10, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\queenclubs.GIF"), false));}
            if(i >=49 && i <=52) {cards.add(new Card("King", 10, new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\kingclubs.GIF"), false));}
                
        }
    }

    /**
     * Gets a random card from the deck, without replacement.
     */ 
    public Card getCard() {
        Random random = new Random();
        Card drawnCard = cards.get(random.nextInt(cards.size()-1));
        cards.remove(drawnCard);
        return drawnCard;
    }
    
}
