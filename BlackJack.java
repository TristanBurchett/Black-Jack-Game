import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Write a description of class BlackJack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackJack
{
    private static ArrayList<String> cardDeck = new ArrayList<String>();
    private static String secretCardPlayer1;
    private static String secretCardPlayer2;
    
    public BlackJack()
    {
        for(int i = 1; i <= 52; i++) {
            if(i >=1 && i <=4) {cardDeck.add("Ace");}
            if(i >=5 && i <=8) {cardDeck.add("2");}
            if(i>=9 && i<=12)  {cardDeck.add("3");}
            if(i >=13 && i <=16) {cardDeck.add("4");}
            if(i >=17 && i <=20) {cardDeck.add("5");}
            if(i>=21 && i <= 24) {cardDeck.add("6");}
            if(i >=25 && i <=28) {cardDeck.add("7");}
            if(i >=29 && i <=32) {cardDeck.add("8");}
            if(i >=33 && i <=36) {cardDeck.add("9");}
            if(i >=37 && i <=40) {cardDeck.add("10");}
            if(i >=41 && i <=44) {cardDeck.add("Jack");}
            if(i >=45 && i <=48) {cardDeck.add("Queen");}
            if(i >=49 && i <=52) {cardDeck.add("King");}
                
        }
    }
    
    private void printCards() {
        for(int i = 0; i < 52; i++) {
            System.out.println(cardDeck.get(i));
        }
    }
    
    private static String getCard() {
        Random random = new Random();
        String drawnCard = cardDeck.get(random.nextInt(cardDeck.size()-1));
        cardDeck.remove(drawnCard);
        return drawnCard;
    }
    
    private static int cardValue(String card) {
        switch(card) {
            case "2": return 2;
            case "3": return 3; 
            case "4": return 4; 
            case "5": return 5; 
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "Jack": return 10;
            case "Queen": return 10;
            case "King": return 10;
            default: return 1; // starting ace
            
        }
    }
    
    private static void showCurrentCardsDown(ArrayList<String> cardsDown) {
        System.out.print("YOUR CURRENT CARDS ARE: ");
        for(String card : cardsDown) {
            System.out.print(card + " ");
        }
        
        System.out.println();
    }
    
    private static boolean containsAce(ArrayList<String> cardsDown) {
        for(String card : cardsDown) {
            if(card.equals("Ace")) {
                return true;
            }
        }
        
        return false;
    }
    
    private static int totalValueDown(ArrayList<String> cardsDown, String secretCard) {
        int total = 0;
        for(String card : cardsDown) {
            if(!card.equals("UNKNOWN")) {
                total+=cardValue(card);
            }
            
            else {
                total+=cardValue(secretCard); 
            }
        }
        
        return total;
    }
    
    private static int modifyAces(ArrayList<String> cardsDown, int addedCardValue, String secretCard) {
        int currentTotal = totalValueDown(cardsDown, secretCard);
        for(String card : cardsDown) {
            if(card.equals("Ace") && (currentTotal+addedCardValue) > 21) {
                currentTotal-=10; // ace value going from 11 to 1
            }
            
            if(card.equals("Ace") && (currentTotal-addedCardValue+10) <= 21) {
                currentTotal+=10; // ace value going from 1 to 11 if possible
            }
        }
        
        return currentTotal;
    }
    
    public static void getWinner(int player1Total, int player2Total) {
        if ((player1Total > player2Total) && (player1Total <= 21 && player2Total <=21)) {
            System.out.println("Player 1 wins!");
            return;
        }
                
        if ((player1Total < player2Total) && (player1Total <= 21 && player2Total <=21)) {
            System.out.println("Player 2 wins!");
            return;
        }
                
        if (player1Total > 21 && player2Total <= 21) {
            System.out.println("Player 2 wins!"); // automatic win
            return;
        }
                
        if (player2Total > 21 && player1Total <= 21) {
            System.out.println("Player 1 wins!"); // automatic win
            return;
        }
        
        if (player1Total > 21 && player2Total > 21) {
            System.out.println("Both players lose");
            return;
        }
        
        if (player1Total == player2Total && (player1Total <= 21 && player2Total <=21)) {
            System.out.println("Tie");
        }
    }
    
    public static void main(String args[]) {
        BlackJack player1 = new BlackJack();
        System.out.println("Welcome to Blackjack 2024 at Big Money Casino LLC! \n");
        System.out.println("You start with a face-up card and a secret card (not shown). Try to get as close to 21 without going over. \n");

        ArrayList<String> cardsDownPlayer1 = new ArrayList<String>();
        ArrayList<String> cardsDownPlayer2 = new ArrayList<String>();
        int player1Total = totalValueDown(cardsDownPlayer1, secretCardPlayer1);
        int player2Total = totalValueDown(cardsDownPlayer2, secretCardPlayer2);
        
        String startingCardPlayer1 = getCard(); // get an initial card
        System.out.println("Player 1 got a " + startingCardPlayer1); // show user said card
        player1Total+= cardValue(startingCardPlayer1); // update the totalValue
        cardsDownPlayer1.add(startingCardPlayer1); // add card to all of the cards down
        player1Total = totalValueDown(cardsDownPlayer1, secretCardPlayer2); // affirm total with totalValueDown()
        player1Total = modifyAces(cardsDownPlayer1, cardValue(startingCardPlayer1), secretCardPlayer1); // modify aces for best scenario
        
        String startingCardPlayer2 = getCard(); // get an initial card
        System.out.println("Player 2 got a " + startingCardPlayer2); // show user said card
        player2Total+= cardValue(startingCardPlayer2); // update the totalValue
        cardsDownPlayer2.add(startingCardPlayer2); // add card to all of the cards down
        player2Total =totalValueDown(cardsDownPlayer2, secretCardPlayer2); // affirm total with totalValueDown()
        player2Total = modifyAces(cardsDownPlayer2, cardValue(startingCardPlayer2), secretCardPlayer2); // modify aces for best scenario
        
        secretCardPlayer1 = getCard();
        player1Total += cardValue(secretCardPlayer1);
        cardsDownPlayer1.add("UNKNOWN");
        player1Total = modifyAces(cardsDownPlayer1, cardValue(secretCardPlayer1), secretCardPlayer1);
        
        secretCardPlayer2 = getCard();
        player2Total += cardValue(secretCardPlayer2);
        cardsDownPlayer2.add("UNKNOWN");
        player2Total = modifyAces(cardsDownPlayer2, cardValue(secretCardPlayer2), secretCardPlayer2);
        
        boolean hitPlayer1 = true;
        boolean hitPlayer2 = true;
        Scanner scanner = new Scanner(System.in);
        
        while(hitPlayer1 || hitPlayer2) {
            System.out.println("Player One's current total is " + (player1Total - cardValue(secretCardPlayer1)) + "\n");
            showCurrentCardsDown(cardsDownPlayer1);
            System.out.println("Would Player 1 like to hit (1) or stand(any other key)");
            String hitOrStandPlayer1 = scanner.nextLine();
            
            System.out.println("Player Two's current total is " + (player2Total - cardValue(secretCardPlayer2)) + "\n");
            showCurrentCardsDown(cardsDownPlayer2);
            System.out.println("Would Player 2 like to hit (1) or stand(any other key)");
            String hitOrStandPlayer2 = scanner.nextLine();
            
            // end game
            if(!hitOrStandPlayer1.equals("1")) {hitPlayer1 = false; System.out.println("Player One's score was " + player1Total);}
            if(!hitOrStandPlayer2.equals("1")) {hitPlayer2 = false; System.out.println("Player Two's score was " + player2Total);}
            if(!hitOrStandPlayer1.equals("1") && !hitOrStandPlayer2.equals("1")) {
                getWinner(player1Total, player2Total);
                break;
            }
            
            // continuing game
            else {
                String strValPlayer1 = getCard();
                cardsDownPlayer1.add(strValPlayer1);
                player1Total = totalValueDown(cardsDownPlayer1, secretCardPlayer1);
                player1Total = modifyAces(cardsDownPlayer1, cardValue(secretCardPlayer1), secretCardPlayer1);
                
                String strValPlayer2 = getCard();
                cardsDownPlayer2.add(strValPlayer2);
                player2Total = totalValueDown(cardsDownPlayer2, secretCardPlayer2);
                player2Total = modifyAces(cardsDownPlayer2, cardValue(secretCardPlayer2), secretCardPlayer2);
                
                // dealing with aces
                if(strValPlayer1.equals("Ace") && hitPlayer1) {
                    System.out.println("You got an ace"); 
                    player1Total+=11;
                    player1Total = modifyAces(cardsDownPlayer1, cardValue(strValPlayer1), secretCardPlayer1);
                }
                
                if(strValPlayer2.equals("Ace") && hitPlayer2) {
                    System.out.println("You got an ace"); 
                    player2Total+=11;
                    player2Total = modifyAces(cardsDownPlayer2, cardValue(strValPlayer2), secretCardPlayer2);
                }
                
                // dealing with all other cards
                else {
                    if(hitPlayer1) {
                        System.out.println("Player 1 got a " + strValPlayer1);
                        player1Total+= cardValue(strValPlayer1);
                        player1Total = modifyAces(cardsDownPlayer1, cardValue(strValPlayer1), secretCardPlayer1);
                    }
                    
                    if(hitPlayer2) {
                        System.out.println("Player 2 got a " + strValPlayer2);
                        player2Total+= cardValue(strValPlayer2);
                        player2Total = modifyAces(cardsDownPlayer2, cardValue(strValPlayer2), secretCardPlayer2);
                    }
                }
            }
        }
        
        getWinner(player1Total, player2Total);
    }
    
}
