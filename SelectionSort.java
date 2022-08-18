import java.util.*;
import java.awt.*;
import java.util.Collections;

/**
 *  Program sorts cards via SelectionSort algorithm
 *  and demonstrates use of a recorder.
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class SelectionSort {
  
  /** Starts the program running */
  public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true),2,2);

    // mix up the cards
    Collections.shuffle(cards);

    // if you want to sort in array form, use:
    Card[] card_arr = cards.toArray(new Card[0]);

    // in your program, this would be a call to a sorting algorithm
    cards = SelectionSort.sort(cards,recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: SelectionSort");
  }


  /**   Method to sort cards by finding the smallest card in 
  *   @param unsorted and adding it at the tail of
  *   @return sorted cardpile.
  *   Records visual demonstration through @param record.
  */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    // register the starting configuration with the recorder
    record.add(unsorted);

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
      
      record.next();         // tell it this is a new step
      record.add(sorted);   // the allegedly sorted pile
      record.add(unsorted);  // the unsorted pile
    }  

    return sorted;
  }
}
