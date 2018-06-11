/* BlackJack Final      Author:Kelli Stasiak        Due Date:  May 15, 2018 */

package blackjack2;

/**
 *
 * @author Kelli
 */
public class Card {
       public int cardIdNumber;
   
    Card(){
        
    }
    
    Card(int cardIdNumber){
        this.cardIdNumber = cardIdNumber;
    }
    
    public String ImageName(int cardIdNumber){
        
        return String.valueOf("file:\\C:\\Users\\Kelli\\Documents\\NetBeansProjects\\BlackJack\\images\\" + cardIdNumber + ".png");
    }//end ImageName
    
    public String HiddenCard(){
        return String.valueOf("file:\\C:\\Users\\Kelli\\Documents\\NetBeansProjects\\BlackJack\\images\\b2fv.png");
    }
    
    public int GettingCardValue(int cardIdNumber){
        if (cardIdNumber == 1 || cardIdNumber == 14 ||cardIdNumber == 27 || cardIdNumber == 40){
            return 1;
        }//end if
        
        if (cardIdNumber == 2 || cardIdNumber == 15 ||cardIdNumber == 28 || cardIdNumber == 41){
            return 2;
        }//end if
        
        if (cardIdNumber == 3 || cardIdNumber == 16 ||cardIdNumber == 29 || cardIdNumber == 42){
            return 3;
        }//end if
        
        if (cardIdNumber == 4 || cardIdNumber == 17 ||cardIdNumber == 30 || cardIdNumber == 43){
            return 4;
        }//end if
        
        if (cardIdNumber == 5 || cardIdNumber == 18 ||cardIdNumber == 31 || cardIdNumber == 44){
            return 5;
        }//end if
        
        if (cardIdNumber == 6 || cardIdNumber == 19 ||cardIdNumber == 32 || cardIdNumber == 45){
            return 6;
        }//end if
        
        if (cardIdNumber == 7 || cardIdNumber == 20 ||cardIdNumber == 33 || cardIdNumber == 46){
            return 7;
        }//end if
        
        if (cardIdNumber == 8 || cardIdNumber == 21 ||cardIdNumber == 34 || cardIdNumber == 47){
            return 8;
        }//end if
        
        if (cardIdNumber == 9 || cardIdNumber == 22 ||cardIdNumber == 35 || cardIdNumber == 48){
            return 9;
        }//end 
        
        if (cardIdNumber == 10 || cardIdNumber == 23 ||cardIdNumber == 36 || cardIdNumber == 49){
            return 10;
        }//end if
        
        if (cardIdNumber == 11 || cardIdNumber == 24 ||cardIdNumber == 37 || cardIdNumber == 50){
            return 10;
        }//end if
        
        if (cardIdNumber == 12 || cardIdNumber == 25 ||cardIdNumber == 38 || cardIdNumber == 51){
            return 10;
        }//end if
        
        if (cardIdNumber == 13 || cardIdNumber == 26 ||cardIdNumber == 39 || cardIdNumber == 52){
            return 10;
        }//end if
        
        else {
            return 0;
        }//end else
    }//end GettingCardValue Method
}
