import java.util.ArrayDeque;
import java.util.Collections;

/**
 *  Program sorts cards via MergeSort algorithm
 *  and demonstrates use of a recorder.
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class MergeSort {
  
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
    recorder.display("Card Sort Demo: MergeSort");
  }



  /**   Method to sort cards by comparing two cards at a time.
  *   @param unsorted pile 
  *   @return sorted cardpile
  *   Records visual demonstration through @param record.
  */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    //queue to store singletons
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();


    //create singletons to hold each card
    while (!unsorted.isEmpty()) {
      CardPile singletons = new CardPile (2,2);
      singletons.add(unsorted.removeFirst());
      queue.add(singletons);
    }

    for (CardPile pile : queue) { // add all piles
      record.add(pile); //record each pile
    }

    
    while (queue.size() > 1) {
      //take two first singletons
      CardPile list1 = queue.removeFirst();
      CardPile list2 = queue.removeFirst();

      //will store merged list1 and 2
      CardPile merged = new CardPile (2,2);


      //merge lists together
      while (!list1.isEmpty() || !list2.isEmpty()) {
        Card first_one = list1.peekFirst(); //element of list1
        Card first_two = list2.peekFirst(); //element of list2


        
        if (list1.isEmpty()) {
          merged.addLast(list2.removeFirst());  //add remaining from list2
        } else if (list2.isEmpty()) {
          merged.addLast(list1.removeFirst());  //add remaining from list1    
        } else if (first_one.compareTo(first_two) <= 0 ) {
          merged.addLast(list1.removeFirst());  //add list1 first if 1<2       
        } else if (first_two.compareTo(first_one) <= 0 ) {
          merged.addLast(list2.removeFirst()); //add list2 first 2<1       
        }
        // add merged instead of list 1 and 2

        record.next();
        for (CardPile p: queue){
          record.add(p);
        }
      }

      
      //put resulting merge to the end of queue
      queue.addLast(merged);
      record.add(merged);
      record.next();
    }

    // return the sorted result here
    return queue.remove();
  }
}
