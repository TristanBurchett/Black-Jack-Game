import java.io.File;
import javax.swing.*;

/**
 * Constructs a card with its rank, value, and image.  Note that we don't
 * represent suits because they don't matter for Blackjack.
 * 
 * Ranks are represented as strings like "Two", "Three", ... "King", "Ace".
 * 
 * Because cards can have different values in different situations, 
 * the value of the card must be specified by the caller.
 *
 * @author Tristan Burchett
 * @version 1
 */

public class Card extends JLabel
{
    private String rank;
    private int pointValue; 
    private File cardImage;
    private boolean isFaceDown;
    
    public Card(String rank, int pointValue, File cardImage, boolean isFaceDown) {
        this.rank = rank;
        this.pointValue = pointValue;
        this.cardImage = cardImage;
        setFaceDown(isFaceDown);
    }
    
    public String getRank() {
        return rank;
    }
    
    public int getPointValue() {
        return pointValue;
    }
    
    public void setRank(String s) {
        this.rank = s;
    }
    
    public void setFaceDown(boolean isFaceDown) {
        this.isFaceDown = isFaceDown;
        
        // update my UI
        removeAll();
        File file;
        if (isFaceDown)
            file = new File("C:\\Users\\limin\\OneDrive\\Documents\\Java Code BlueJ\\BlackjackNoGUI\\cards\\faceDown.GIF");
        else
            file = cardImage;
        ImageIcon cardImg = new ImageIcon(file.getAbsolutePath());
        setIcon(cardImg);
        revalidate();
    }

    public boolean isFaceDown() {
        return isFaceDown;
    }
    
    /**
     * @returns a description the Card
     */
    public String toString() {
        return "a " + rank + " which is worth " + pointValue + " points.";
    }
}
