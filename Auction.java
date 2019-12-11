import java.util.ArrayList;
import java.util.Iterator; // A.L. added for the new gotLot method

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            // A.L replaced the following two lines with the instantiation of anonymous object
            // Bid bid = new Bid(bidder, value);
            // boolean successful = selectedLot.bidFor(bid);
            boolean successful = selectedLot.bidFor(new Bid(bidder, value)); //A.L. replacement
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    highestBid.getValue());
            }
        }
    }

    /**
     * A.L. This method was renamed and rewritten to retrieve lot # regardless of its index position 
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLotDeprecated(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                    selectedLot.getNumber() +
                    " was returned instead of " +
                    lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            return null;
        }
    }

    /**
     * Show the full list of closed lots in this auction. A.L.
     */
    public void close()
    {
      
        lots.forEach(lot -> {
                if(lot.getHighestBid()!=null) 
                {System.out.println(lot.toString() + " by " + lot.getHighestBid().getBidder().getName());}
                else
                {System.out.println("Lot " + lot.getNumber() + ": " + lot.getDescription() + " had no bids.");}});
    }
    /**
     * Return the list of unsold lots in this auction. A.L.
     * @return A list of unsold lots
     */
    public ArrayList<Lot> getUnsold()
    {
        /* for(Lot lot : lots) {
        System.out.println(lot.toString());
        } */
        ArrayList<Lot> unsoldLots = new ArrayList<>(); 
        lots.forEach(lot -> {
                if(lot.getHighestBid()==null) 
                unsoldLots.add(lot);});
        return unsoldLots;
    }
     /**
     * A.L. rewrote this method to be independent of its index position in the list
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Iterator<Lot> iter = lots.iterator();
            // Traverse the list of lots to find the requested lot
            while(iter.hasNext()) {
                Lot selectedLot = iter.next();
                if(selectedLot.getNumber() == lotNumber)
                  return selectedLot;
            }
            // if no valid lot returned by this time, the lot requested does not exist
            return null;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            return null;
        }
    }
         /**
     * A.L. new method added
     * Remove the lot with the given lot number. Return null
     * if a lot with this number does not exist.
     * @param number The number of the lot to be removed.
     * @return The Lot with the given number, or null if there is no such lot.
     */
    public Lot removeLot(int number)
    {
        Lot removedLot = getLot(number);
        lots.remove(removedLot);
        return removedLot;
    }
}
