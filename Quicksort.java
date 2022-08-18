import java.util.Collections;

/**
 *  Program sorts cards via QuickSort algorithm
 *  and demonstrates use of a recorder.
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class Quicksort {

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
    cards = Quicksort.sort(cards,recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: QuickSort");
  }

  /**   Method to sort cards by recursively dividing pile in two.
  *   @param unsorted pile 
  *   @return sorted cardpile
  *   Records visual demonstration through @param record.
  */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    //pile to store result
    CardPile result = new CardPile(2,2);
    //piles to store each partition
    CardPile smaller = new CardPile(2,2);
    CardPile bigger = new CardPile(2,2);
    record.add(unsorted);

    //stop condition: a list with 1 or 0 elements
    if (unsorted.size() <= 1) {
      return unsorted; 
    }

    // pick first element to be pivot  
    Card pivot = unsorted.getFirst(); 
  
    while (!unsorted.isEmpty()) {
      Card current = unsorted.removeFirst();
      
      //partition list into two groups
      if (current.compareTo(pivot) > 0) {
        bigger.addLast(current);
      } else if (current.compareTo(pivot) < 0){
        smaller.addLast(current);
      }
    }

    //recursively sort each of the lists  
    CardPile smaller_sorted = sort (smaller, record);
    CardPile bigger_sorted = sort (bigger, record);


    //glue lists together with pivot in the middle
    result.addAll(smaller_sorted);
    result.add(pivot);
    result.addAll(bigger_sorted);
  

    record.add(smaller_sorted);// register the partitions with the recorder
    record.add(bigger_sorted);
    record.add(smaller);
    record.add(pivot);
    record.add(bigger);
    record.next();

    // record the sorted result
    record.add(result);
    record.next();

    // return the sorted result here
    return result;
  }
}
