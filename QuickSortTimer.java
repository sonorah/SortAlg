import java.util.Collections;


/**
 *  Program sorts cards via quick sort, omitting graphics
 *
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class QuickSortTimer {

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

  /**   Method to sort cards by recursively dividing pile in two.
  *   @param unsorted pile 
  *   @return sorted cardpile
  */
  public static CardPile sort(CardPile unsorted) {
    //pile to store result
    CardPile result = new CardPile(2,2);
    //piles to store each partition
    CardPile smaller = new CardPile(2,2);
    CardPile bigger = new CardPile(2,2);


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
    CardPile smaller_sorted = sort (smaller);
    CardPile bigger_sorted = sort (bigger);


    //glue lists together with pivot in the middle
    result.addAll(smaller_sorted);
    result.add(pivot);
    result.addAll(bigger_sorted);
  
    // return the sorted result here
    return result;
  }
}


