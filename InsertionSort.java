import java.util.Collections;
import java.awt.*;
import java.util.*;

/**
 *  Program sorts cards via InsertionSort algorithm
 *  and demonstrates use of a recorder.
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class InsertionSort {
  
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
    cards = MergeSort.sort(cards,recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: InsertionSort");
  }


  /**   Method to sort cards by inserting each card  from 
  *   @param unsorted pile at its right spot in the 
  *   @return sorted cardpile.
  *   Records visual demonstration through @param record.
  */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    // register the starting configuration with the recorder
    record.add(unsorted);
    //store sorted cards
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

      record.next();         // tell it this is a new step
      record.add(sorted);   // the allegedly sorted pile
      record.add(unsorted);  // the unsorted pile
      
    }


    // return the sorted result here
    return sorted;
  }
}
