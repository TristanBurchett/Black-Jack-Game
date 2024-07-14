import javax.swing.*;
import java.awt.*;

/**
 * This class was copied/adapted from 
 * https://stackhowto.com/how-to-set-background-image-in-java-swing/
 * 
 * It does not figure into my line count for this project.
 * I simply couldn't figure out background images in Swing containers without help from the internet.
 */
public class ImageBackground extends JTextArea
{
    private Image image;
    
    public ImageBackground(ImageIcon icon)
    {
        this.image = icon.getImage();
        setOpaque(false);
    }

    public void paintComponent(Graphics graphics) 
    {
        graphics.drawImage(image, 0, 0, this);
        super.paintComponent(graphics);
    }
}