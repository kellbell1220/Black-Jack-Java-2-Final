/* BlackJack Final      Author:Kelli Stasiak        Due Date:  May 15, 2018 */

package blackjack2;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    
    public static ArrayList<Integer> CreateTheDeck(){
    
        ArrayList<Integer> deck = new ArrayList<>();
        
        for(int i = 1; i <= 52; i++){
            deck.add(i);
        }//end for
        
        ShuffleTheDeck(deck);
        
//        for (int i = 1; i <= deck.size(); i++){
//            System.out.println(deck.get(i));
//        }
        
        return deck;
    }//end CreateTheDeck    
    
    public static void ShuffleTheDeck(ArrayList <Integer> deck){
//        for(int i = 0; i< cardDeck.size(); i++){
//            int j = (int)(Math.random() * cardDeck.size());
//                                  
//            //Swapping out values in ArrayList
//            cardDeck.set(j, cardDeck.get(i));
//            cardDeck.set(i, cardDeck.get(j));
//        }//end for
        Collections.shuffle(deck);
    }//end ShuffleTheDeck    
}
    

