import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board{

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;
     /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};
    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ElevensBoard() {
        super(BOARD_SIZE, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    public boolean isLegal(List<Integer> selectedCards) 
    {
        if(selectedCards.size() == 2)
        {
            if(containsPairSum11(selectedCards) == true)
            {
                return true;
            }
        }
        if(selectedCards.size() == 3)
        {
            if(containsJQK(selectedCards) == true)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    public boolean anotherPlayIsPossible() 
    {
        List<Integer> checkCardIndexes = cardIndexes();
        return containsPairSum11(checkCardIndexes) || containsJQK(checkCardIndexes);
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    public boolean containsPairSum11(List<Integer> selectedCards) 
    {
        for(int i = 0; i < selectedCards.size(); i++)
        {
            int currentPointValue = cardAt(selectedCards.get(i)).pointValue();
            for(int j = 0; (j < selectedCards.size()) && (j != i); j++)
            {
                int otherCardPointValue = cardAt(selectedCards.get(j)).pointValue();
                if(currentPointValue + otherCardPointValue == 11)
                {
                    return true;
                }
                else
                {
                    continue;
                }
            }
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) 
    {
        boolean isKing = false;
        boolean isQueen = false; 
        boolean isJack = false;
        for(int i = 0; i < selectedCards.size(); i++)
        {
            if(cardAt(selectedCards.get(i)).rank().equals("king"))
            {
                isKing = true;
            }
            else if(cardAt(selectedCards.get(i)).rank().equals("queen"))
            {
                isQueen = true;
            }
            else if(cardAt(selectedCards.get(i)).rank().equals("jack"))
            {
                isJack = true;
            }
            else
            {
                continue;
            }
        }
        if((isKing == true) && (isQueen == true) && (isJack == true))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
