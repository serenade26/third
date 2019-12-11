

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AuctionTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AuctionTest
{
    private Auction auction1;
    private Person person1;
    private Person person2;
    private Person person3;

    /**
     * Default constructor for test class AuctionTest
     */
    public AuctionTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        auction1 = new Auction();
        auction1.enterLot("car 1");
        auction1.enterLot("car 2");
        auction1.enterLot("car 3");
        person1 = new Person("Alex");
        person2 = new Person("Jack");
        person3 = new Person("Jane");
        auction1.makeABid(2, person1, 10000);
        auction1.makeABid(2, person2, 50000);
        auction1.makeABid(1, person3, 1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testUnsold()
    {
        java.util.ArrayList<Lot> lots = auction1.getUnsold();
       
        assertEquals(1, lots.size());
    }

    @Test
    public void testRemove()
    {
        auction1.enterLot("extra");
        
        assertNotNull(auction1.getLot(4));
    }
}


