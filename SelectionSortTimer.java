import java.util.*;
import java.awt.*;
import java.util.Collections;

/**
 *  Program sorts cards via selection sort, omitting graphics
 *
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class SelectionSortTimer {

  /** Starts the program running */
  public static void main(String args[]) {
    
    if (args.length<1) {
      System.err.println("Please specify how many cards to sort!");
    } else {
      Card[] deck = Card.newDeck(true);
      CardPile cards = new CardPile(2,2);
      
      for (int i = 0; i<Integer.parseInt(args[0]); i++ ) {
        cards.add(deck[(int)(52*Math.random())]);
      }

      sort(cards);
      
    }
  }

  /**   Method to sort cards by finding the smallest card in 
  *   @param unsorted and adding it at the tail of
  *   @return sorted cardpile.
  */
  public static CardPile sort(CardPile unsorted) {

    // Here is the result list you will be creating
    CardPile sorted = new CardPile(2,2);

    
    while (unsorted.size() > 0 ) {
      ListIterator <Card> pos = unsorted.listIterator(0); 
      Card min = pos.next();
      
      while (pos.hasNext()) {
        Card current = pos.next();
        
        if (current.compareTo(min) <= 0) {
          min = current; //find smallest card
        }  
      }

      unsorted.remove(min); //remove smallest from unsorted
      sorted.addLast(min); //add smallest to the tail of sorted

    }  

    return sorted;
  }
}


