import java.util.ArrayDeque;

/**
 *  Program sorts cards via mergesort, omitting graphics
 *
 *  @author Sonora Halili & Frankie Fan
 *  @version March 2022
 */
public class MergeSortTimer {

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

  /**   Method to sort cards by comparing two cards at a time.
  *   @param unsorted pile 
  *   @return sorted cardpile
  */
  public static CardPile sort(CardPile unsorted) {
    //queue to store singletons
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();


    //create singletons to hold each card
    while (!unsorted.isEmpty()) {
      CardPile singletons = new CardPile (2,2);
      singletons.add(unsorted.removeFirst());
      queue.add(singletons);
    }

    
    while (queue.size() > 1) {
      //take two first singletons
      CardPile list1 = queue.removeFirst();
      CardPile list2 = queue.removeFirst();

      //will store merged list1 and 2
      CardPile merged = new CardPile (2,2);


      //merge lists together
      while (!list1.isEmpty() || !list2.isEmpty()) {
        Card first_one = list1.peekFirst(); //element in list1
        Card first_two = list2.peekFirst(); //element in list2


        
        if (list1.isEmpty()) {
          merged.addLast(list2.removeFirst());  //add remaining from list2
        } else if (list2.isEmpty()) {
          merged.addLast(list1.removeFirst());  //add remaining from list1    
        } else if (first_one.compareTo(first_two) <= 0 ) {
          merged.addLast(list1.removeFirst());  //add list1 first if 1<2       
        } else if (first_two.compareTo(first_one) <= 0 ) {
          merged.addLast(list2.removeFirst()); //add list2 first 2<1       
        }
        
      }

      
      //put resulting merge to the end of queue
      queue.addLast(merged);
    }

    // return the sorted result here
    return queue.remove();
  }
}


