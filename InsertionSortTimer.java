import java.awt.*;
import java.util.*;

/**
 *  Program sorts cards via insertion sort, omitting graphics
 *
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class InsertionSortTimer {

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

  
  
  /**   Method to sort cards by inserting each card  from 
  *   @param unsorted pile at its right spot in the 
  *   @return sorted cardpile.
  */
  public static CardPile sort(CardPile unsorted) {
    // Here is the result list you will be creating
    CardPile sorted = new CardPile(2,2);
    

    while (unsorted.size() > 0 ) {
     //remove one card at a time
      Card current = unsorted.removeFirst();
      
      if (sorted.size() == 0) {
        sorted.addFirst(current); //add first if list is empty
      } else {
        if (current.compareTo(sorted.getFirst())<= 0){
          sorted.addFirst(current); //add first if current is the biggest card         
        } else if (current.compareTo(sorted.getLast()) > 0){
          sorted.addLast(current); //add last if current is the smallest card
        } else {
          ListIterator <Card> pos = sorted.listIterator(0);
          
          //find where current belongs
          while (pos.hasNext()) {
            Card c = pos.next();

            //add current where left is smaller and right is bigger
            if((current.compareTo(c) <= 0)){
              pos.previous();
              pos.add(current); 
              break;
            } 
          }
        }
      }
    }
    return sorted;
  }
}