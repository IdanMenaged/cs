package auction;

import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestAuction extends TestCase
{
    private static String LS = System.lineSeparator();
    /*******************************   Access Properties  ******************************/
    private int getIntValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(o);  
        
        return value;
    }
    
    private double getDoubleValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        double value = fld.getDouble(o);  
        
        return value;
    }
    
    private char getCharValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        char value = fld.getChar(o);  
        
        return value;
    }
    
    private boolean getBooleanValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        boolean value = fld.getBoolean(o);  
        
        return value;
    }
    
     private Object getObjectValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        Object value = fld.get(o);  
        
        return value;
    }
    
    private int getStaticIntValue(Class cls, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(null);  
        
        return value;        
    }
    
    private void setIntValue(Object o, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }

     private void setStaticIntValue(Class cls, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(null, val);
     
    }
    private void setStringValue(Object o, String fldName, String val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }
    
    private void setDoubleValue(Object o, String fldName, double val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    } 

    private void setBooleanValue(Object o, String fldName, boolean val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }
    private void setObjectValue(Object o, String fldName, Object val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }
    private String removeMessage (String st, String message)
    {
        return st.replace(message, "");
    }

    /************************** Person ****************************/
    public void test_personConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Person p = new Person("Harry Potter");
        
        String name = (String)getObjectValue(p, "name");
        assertEquals("Person(\"Herry Potter\") -name", "Harry Potter", name);
    }
    
    public void test_personCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Person p = new Person("Harry Potter");
        Person copyP = new Person(p);
        
        String name = (String)getObjectValue(copyP, "name");
        assertEquals("copy Person(\"Herry Potter\") -name", "Harry Potter", name);
    }
    
    /************************** Bid ****************************/
    public void test_bidConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Person p = new Person("Harry Potter");
        Bid b = new Bid(p, 100);
        
        Person bidder = (Person)getObjectValue(b, "bidder");
        String bidderName = (String)getObjectValue(bidder, "name");
        assertEquals("Bid(Person(\"Herry Potter\"), 100) -bidder name", "Harry Potter", bidderName);
        assertTrue("Bid(Person(\"Herry Potter\"), 100) - bidder copied", bidder != p);
        
        int value = getIntValue(b, "value");
        
        assertEquals("Bid(Person(\"Herry Potter\"), 100) - value", 100, value);
 
    }
    
    public void test_bidCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Person p = new Person("Harry Potter");
        Bid b = new Bid(p, 100);
        Bid copyB = new Bid(b);
        
        int value = getIntValue(copyB, "value");
        assertEquals("copy Bid(Person(\"Herry Potter\"), 100) - value", 100, value);
         
        Person copyBidder = (Person)getObjectValue(copyB, "bidder");
        Person bidder = (Person)getObjectValue(b, "bidder");
        String name = (String)getObjectValue(copyBidder, "name");
        assertEquals("copy Bid(Person(\"Herry Potter\"), 100) -bidder name", "Harry Potter", name);
        
        // bidder not original
        assertTrue("Bid(Person(\"Herry Potter\"), 100) - bidder not original", copyBidder != p);
        
        // bidder copied
        assertTrue("Bid(Person(\"Herry Potter\"), 100) - bidder copied", copyBidder != bidder);
    }    
    
    /**************************  Item ***********************************/ 
    public void test_itemConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Item.class, "counter", 1);
        
        Item itm = new Item("expensive item");
        
        int counter = getStaticIntValue(Item.class, "counter");      
        assertEquals("Item(\"expensive item\") - counter", 2, counter);
        
        int itemId = getIntValue(itm, "itemId");
        assertEquals("Item(\"expensive item\") - itemId", 2, itemId); 
   
        String name = (String)getObjectValue(itm, "itemName");
        assertEquals("Item(\"expensive item\") - itemName", "expensive item", name);
     
        Bid highBid = (Bid)getObjectValue(itm, "highBid");
        assertEquals("Item(\"expensive item\") - hightBid", null, highBid);
        
        boolean sold = getBooleanValue(itm, "sold")  ;     
        assertEquals("Item(\"expensive item\") - sold",false, sold);        
    }
    
    
    public void test_itemCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Item.class, "counter", 4);
        
        Item itm = new Item("expensive item");
        Person p = new Person("Harry Potter");
        Bid b = new Bid(p, 2000);
        setObjectValue(itm, "highBid", b);
        
        Item copiedItem = new Item(itm);
        
        int counter = getStaticIntValue(Item.class, "counter");      
        assertEquals("copy  Item(\"expensive item\") - counter", 5, counter);
        
        int itemId = getIntValue(copiedItem, "itemId");
        assertEquals("copy Item(\"expensive item\") - itemId", 5, itemId); 
   
        String name = (String)getObjectValue(copiedItem, "itemName");
        assertEquals("copy Item(\"expensive item\") - itemName", "expensive item", name);
     
        Bid highBid = (Bid)getObjectValue(copiedItem, "highBid");
        assertTrue("copy Item(\"expensive item\") - hightBid - copied", highBid != b);
        
        Person copiedP = (Person)getObjectValue(highBid, "bidder");
        String copiedName = (String)getObjectValue(copiedP, "name");
        assertEquals("copy Item(\"expensive item\") - bid person name", 
                     "Harry Potter", copiedName);
        int val = getIntValue(highBid, "value");
        assertEquals("copy Item(\"expensive item\") - bid value", 
                    2000, val);        
        boolean sold = getBooleanValue(itm, "sold")  ;     
        assertEquals("Item(\"expensive item\") - sold",false, sold);        
    }
    public void test_bidForTrue() throws NoSuchFieldException, IllegalAccessException
    {
        Item itm = new Item("ver expensive");
        Person p = new Person("Harry Potter"); 
        Person newPerson = new Person("Hermione Granger");
        Bid newBid = new Bid(newPerson, 500);
        Bid b = new Bid(p, 100);
        setObjectValue(itm, "highBid", b);
        boolean accepted = itm.bidFor(newBid);
        
        assertEquals("bidFor((Harry Potter, 100)--> (Hermione Granger,500))", 
                      true, accepted);
        
        // bid updated
        Bid updatedB = (Bid)getObjectValue(itm,"highBid");
        Person updatedPerson = (Person)getObjectValue(updatedB, "bidder");
        String updatedName = (String)getObjectValue(updatedPerson, "name");
        assertEquals("bidFor((Harry Potter, 100)--> (Hermione Granger,500)) - bidder", 
                            "Hermione Granger", updatedName);
        int value = getIntValue(updatedB, "value");
        assertEquals("bidFor((Harry Potter, 100)--> (Hermione Granger,500)) - value",
                     500, value);
                     
        // bid copied
        assertTrue("bidFor((Harry Potter, 100)--> (Hermione Granger,500)) - bid copied",
                    newBid != updatedB);
                    
        // original bid object moved
                    
         assertTrue("bidFor((Harry Potter, 100)--> (Hermione Granger,500)) - bid not original",
                    b != updatedB);       
        
    }

    public void test_bidForFalse() throws NoSuchFieldException, IllegalAccessException
    {
        Item itm = new Item("ver expensive");
        Person newPerson = new Person("Harry Potter"); 
        Person p = new Person("Hermione Granger");
        Bid b = new Bid(p, 500);
        Bid newBid = new Bid(newPerson, 100);
        setObjectValue(itm, "highBid", b);
        boolean accepted = itm.bidFor(newBid);
        
        assertEquals("bidFor((Harry Potter, 100)--> (Hermione Granger,500))", 
                      false, accepted);
        
        // bid not updated
        Bid updatedB = (Bid)getObjectValue(itm,"highBid");
        Person updatedPerson = (Person)getObjectValue(updatedB, "bidder");
        String updatedName = (String)getObjectValue(updatedPerson, "name");
        assertEquals("bidFor((Hermione Granger,500)-->(Harry Potter, 100) ) - bidder", 
                            "Hermione Granger", updatedName);
        int value = getIntValue(updatedB, "value");
        assertEquals("bidFor((Hermione Granger,500)-->(Harry Potter, 100)) - value",
                     500, value);
                     
        // bid not copied
        assertTrue("bidFor((Hermione Granger,500)-->(Harry Potter, 100),500)) - bid not copied",
                    b == updatedB);
                       
        
    }
    public void test_bidForTrueOriginalBidNull() throws NoSuchFieldException, IllegalAccessException
    {
        Item itm = new Item("ver expensive");
 
        Person newPerson = new Person("Hermione Granger");
        Bid newBid = new Bid(newPerson, 500);

        boolean accepted = itm.bidFor(newBid);
        
        assertEquals("bidFor((Null)--> (Hermione Granger,500))", 
                      true, accepted);
        
        // bid updated
        Bid updatedB = (Bid)getObjectValue(itm,"highBid");
        Person updatedPerson = (Person)getObjectValue(updatedB, "bidder");
        String updatedName = (String)getObjectValue(updatedPerson, "name");
        assertEquals("bidFor((Null)--> (Hermione Granger,500)) - bidder", 
                            "Hermione Granger", updatedName);
        int value = getIntValue(updatedB, "value");
        assertEquals("bidFor((Null)--> (Hermione Granger,500)) - value",
                     500, value);
                     
        // bid copied
        assertTrue("bidFor((Harry Potter, 100)--> (Hermione Granger,500)) - bid copied",
                    newBid != updatedB);
    
    }     
    
    public void test_bidForFalseOriginalBidNull() throws NoSuchFieldException, IllegalAccessException
    {
        Item itm = new Item("ver expensive");
        setBooleanValue(itm, "sold", true);
        Person newPerson = new Person("Hermione Granger");
        Bid newBid = new Bid(newPerson, 500);
        
        boolean accepted = itm.bidFor(newBid);
        
        
        assertEquals("bidFor((Null)--> (Hermione Granger,500))", 
                      false, accepted);
        
        // bid updated
        Bid updatedB = (Bid)getObjectValue(itm,"highBid");
        
        assertEquals("bidFor((Null)--> (Hermione Granger,500), sold) - bid ot upadated", 
                            null, updatedB);
        

    } 
    
    public void test_bidForFalsesold() throws NoSuchFieldException, IllegalAccessException
    {
        Item itm = new Item("ver expensive");
        setBooleanValue(itm, "sold", true);
        Person p = new Person("Harry Potter"); 
        Person newPerson = new Person("Hermione Granger");
        Bid newBid = new Bid(newPerson, 500);
        Bid b = new Bid(p, 100);
        setObjectValue(itm, "highBid", b);
        boolean accepted = itm.bidFor(newBid);
        
        assertEquals("bidFor((Harry Potter, 100 sold)--> (Hermione Granger,500))", 
                      false, accepted);
        
        // bid updated
        Bid updatedB = (Bid)getObjectValue(itm,"highBid");
        Person updatedPerson = (Person)getObjectValue(updatedB, "bidder");
        String updatedName = (String)getObjectValue(updatedPerson, "name");
        assertEquals("bidFor((Harry Potter, 100), sold--> (Hermione Granger,500)) - bidder", 
                            "Harry Potter", updatedName);
        int value = getIntValue(updatedB, "value");
        assertEquals("bidFor((Harry Potter, 100)--> (Hermione Granger,500)) - value",
                     100, value);
                     
        // bid not copied
        assertTrue("bidFor((Harry Potter, 100), sold--> (Hermione Granger,500)) - bid copied",
                    b == updatedB);
    }
    
    /**************************  Auction ***********************************/ 
    public void test_AuctionConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        
        Item [] itms = (Item [])getObjectValue(a, "items");
        assertEquals("Auction() - items length", 100, itms.length);
        
        for (int i = 0; i < itms.length; i++)   
            assertEquals("Auction() - items[" + i + "]", null, itms[i]) ;
        
        
        int cur = getIntValue(a, "current");
        assertEquals("Auction() - current", 0, cur);
    }
    

     public void test_addItemFirst() throws NoSuchFieldException, IllegalAccessException
     {
        Auction a = new Auction();
        Person p = new Person("Ron Weasley");
       // Bid b = new Bid(p, 50);
        Item item = new Item("cheap");
        
        int origItemId = getIntValue(item, "itemId");
        
        boolean added = a.addItem(item);
        
        assertEquals("a.addItem(item) - first item - added", true, added);
        
        int cur = getIntValue(a, "current");
        assertEquals("a.addItem(item) - current", 1, cur);
        
        Item[] items = (Item [])getObjectValue(a,"items");
        Item addedItem = items[0];
        
        assertTrue("a.addItem(item) - added to current index", 
                   null != addedItem);
        String itemName = (String)getObjectValue(addedItem, "itemName");
        
        assertEquals("a.addItem(item) - name","cheap", itemName);
        int itemId = getIntValue(addedItem, "itemId");
        assertEquals("a.addItem(item) - itemId", origItemId, itemId); 
     
        Bid highBid = (Bid)getObjectValue(addedItem, "highBid");
        assertEquals("a.addItem(item) - hightBid", null, highBid);
        
        boolean sold = getBooleanValue(addedItem, "sold")  ;     
        assertEquals("a.addItem(item) - sold",false, sold); 
        
        // item copied
        assertTrue("a.addItem(item) - item copied", addedItem != item);
        
        for (int i = cur; i < items.length ; i++)
            assertEquals("a.addItem(item) - items[" + i +"]", null, items[i]);
        
    }
    
     public void test_addItemMiddle() throws NoSuchFieldException, IllegalAccessException
     {
        Auction a = new Auction();
        Person p = new Person("Ron Weasley");
        Bid b = new Bid(p, 50);
        Item item1 = new Item("cheap");

        for (int i = 0; i < 50; i++)
            a.addItem(item1);
        
        Item item = new Item("cheapest");
        boolean added = a.addItem(item); 
        int origItemId = getIntValue(item, "itemId");
        assertEquals("a.addItem(item) - first item - added", true, added);
        
        int cur = getIntValue(a, "current");
        assertEquals("a.addItem(item) - current", 51, cur);
        
        Item[] items = (Item [])getObjectValue(a,"items");
        Item addedItem = items[50];
        
        assertTrue("a.addItem(item) - added to current index", 
                   null != addedItem);
        String itemName = (String)getObjectValue(addedItem, "itemName");
        
        assertEquals("a.addItem(item) - name","cheapest", itemName);
        int itemId = getIntValue(addedItem, "itemId");
        assertEquals("a.addItem(item) - itemId", origItemId, itemId); 
     
        Bid highBid = (Bid)getObjectValue(addedItem, "highBid");
        assertEquals("a.addItem(item) - hightBid", null, highBid);
        
        boolean sold = getBooleanValue(addedItem, "sold")  ;     
        assertEquals("a.addItem(item) - sold",false, sold); 
        
        // item copied
        assertTrue("a.addItem(item) - item copied", addedItem != item);
        
        for (int i = cur; i < items.length ; i++)
            assertEquals("a.addItem(item) - items[" + i +"]", null, items[i]);
        
    } 
    
    public void test_addItemLast() throws NoSuchFieldException, IllegalAccessException
     {
        Auction a = new Auction();
        Person p = new Person("Ron Weasley");
        Bid b = new Bid(p, 50);
        Item item1 = new Item("cheap");

        for (int i = 0; i < 99; i++)
            a.addItem(item1);
        
        Item item = new Item("cheapest");
        boolean added = a.addItem(item); 
        int origItemId = getIntValue(item, "itemId");
        assertEquals("a.addItem(item) - first item - added", true, added);
        
        int cur = getIntValue(a, "current");
        assertEquals("a.addItem(item) -current", 100, cur);
        
        Item[] items = (Item [])getObjectValue(a,"items");
        Item addedItem = items[99];
        
        assertTrue("a.addItem(item) - added to current index", 
                   null != addedItem);
        String itemName = (String)getObjectValue(addedItem, "itemName");
        
        assertEquals("a.addItem(item) - name","cheapest", itemName);
        int itemId = getIntValue(addedItem, "itemId");
        assertEquals("a.addItem(item) - itemId", origItemId, itemId); 
     
        Bid highBid = (Bid)getObjectValue(addedItem, "highBid");
        assertEquals("a.addItem(item) - hightBid", null, highBid);
        
        boolean sold = getBooleanValue(addedItem, "sold")  ;     
        assertEquals("a.addItem(item) - sold",false, sold); 
        
        // item copied
        assertTrue("a.addItem(item) - item copied", addedItem != item);
        
        for (int i = cur; i < items.length ; i++)
            assertEquals("a.addItem(item) - items[" + i +"]", null, items[i]);
        
    }
    
    public void test_addItemNoRoom() throws NoSuchFieldException, IllegalAccessException
     {
        Auction a = new Auction();
        Person p = new Person("Ron Weasley");
        Bid b = new Bid(p, 50);
        Item item1 = new Item("cheap");

        for (int i = 0; i < 100; i++)
            a.addItem(item1);
        
        Item item = new Item("cheapest");
        boolean added = a.addItem(item); 
        int origItemId = getIntValue(item, "itemId");
        assertEquals("a.addItem(item) - first item - added", false, added); 
    }
    
    public void test_addBid() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Item.class, "counter", 0);
        Auction a = new Auction();
        // add 10 items
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Bid b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;
            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        Item [] origItems = (Item []) getObjectValue(a, "items");
        Item origItem = origItems[7];
        Bid origBid = (Bid)getObjectValue(origItem, "highBid");
        
        Person addBidPerson = new Person ("McGonagall");
        boolean accepted = a.addBid(8, addBidPerson, 200);
        
        assertEquals("addBid(albus dambeldor7, 70), not sold--> (McGonagall, 200))",
                        true, accepted);
                        
        Item [] items = (Item []) getObjectValue(a, "items");
        
        Item updatedItem = items[7];
        
        Bid b = (Bid)getObjectValue(updatedItem, "highBid");
        Person p = (Person)getObjectValue(b, "bidder");
        String name = (String)getObjectValue(p, "name");
        
        assertEquals("addBid(8, McGonagall, 200) - bidder name",
                     "McGonagall", name);
        int val = getIntValue(b, "value");

        assertEquals("addBid(8, McGonagall, 200) - bid vale",
                     200, val);
                     
        int itemId = getIntValue(updatedItem, "itemId");
        assertEquals("addBid(8, McGonagall, 200) - itemId", 8, itemId);
        
        // item copied
        assertTrue("addBid(8, McGonagall, 200) - item copied", b != origBid);
        
    }
        
        
    public void test_addBidOldNull() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Item.class, "counter", 0);
        Auction a = new Auction();
        // add 10 items
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Item item = new Item("cheap" + i)  ;
            a.addItem(item);
        }
        
        Person addBidPerson = new Person ("McGonagall");
        boolean accepted = a.addBid(8, addBidPerson, 200);
        
        assertEquals("addBid(albus dambeldor7, 70), not sold--> (McGonagall, 200))",
                        true, accepted);
                        
        Item [] items = (Item []) getObjectValue(a, "items");
        
        Item updatedItem = items[7];
        
        Bid b = (Bid)getObjectValue(updatedItem, "highBid");
        Person p = (Person)getObjectValue(b, "bidder");
        String name = (String)getObjectValue(p, "name");
        
        assertEquals("addBid(8, McGonagall, 200) - bidder name",
                     "McGonagall", name);
        int val = getIntValue(b, "value");

        assertEquals("addBid(8, McGonagall, 200) - bid vale",
                     200, val);
                     
        int itemId = getIntValue(updatedItem, "itemId");
        assertEquals("addBid(8, McGonagall, 200) - itemId", 8, itemId);
        
    }        
    
    public void test_addBidFalseLowOffer() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Item.class, "counter", 0);
        Auction a = new Auction();
        // add 10 items
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Bid b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;
            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        Item [] origItems = (Item []) getObjectValue(a, "items");
        Item origItem = origItems[7];
        Bid origBid = (Bid)getObjectValue(origItem, "highBid");
        
        Person addBidPerson = new Person ("McGonagall");
        boolean accepted = a.addBid(8, addBidPerson, 2);
        
        assertEquals("addBid(albus dambeldor7, 70), not sold--> (McGonagall, 200))",
                        false, accepted);
                        
        Item [] items = (Item []) getObjectValue(a, "items");
        
        Item updatedItem = items[7];
        
        Bid b = (Bid)getObjectValue(updatedItem, "highBid");
        Person p = (Person)getObjectValue(b, "bidder");
        String name = (String)getObjectValue(p, "name");
        
        assertEquals("addBid(8, McGonagall, 200) - bidder name",
                     "albus dumbledore7", name);
        int val = getIntValue(b, "value");

        assertEquals("addBid(8, McGonagall, 200) - bid vale",
                     70, val);
                     
        int itemId = getIntValue(updatedItem, "itemId");
        assertEquals("addBid(8, McGonagall, 200) - itemId", 8, itemId);
        
        // item not copied
        assertTrue("addBid(8, McGonagall, 200) - item not copied", b == origBid);  
    }
    
    public void test_addBidFalseSold() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Item.class, "counter", 0);
        Auction a = new Auction();
        // add 10 items
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Bid b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;
            setBooleanValue(item, "sold", true);
            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        Item [] origItems = (Item []) getObjectValue(a, "items");
        Item origItem = origItems[7];
        Bid origBid = (Bid)getObjectValue(origItem, "highBid");
        
        Person addBidPerson = new Person ("McGonagall");
        boolean accepted = a.addBid(8, addBidPerson, 200);
        
        assertEquals("addBid(albus dambeldor7, 70), not sold--> (McGonagall, 200))",
                        false, accepted);
                        
        Item [] items = (Item []) getObjectValue(a, "items");
        
        Item updatedItem = items[7];
        
        Bid b = (Bid)getObjectValue(updatedItem, "highBid");
        Person p = (Person)getObjectValue(b, "bidder");
        String name = (String)getObjectValue(p, "name");
        
        assertEquals("addBid(8, McGonagall, 200) - bidder name",
                     "albus dumbledore7", name);
        int val = getIntValue(b, "value");

        assertEquals("addBid(8, McGonagall, 200) - bid vale",
                     70, val);
                     
        int itemId = getIntValue(updatedItem, "itemId");
        assertEquals("addBid(8, McGonagall, 200) - itemId", 8, itemId);
        
        // item not copied
        assertTrue("addBid(8, McGonagall, 200) - item not copied", b == origBid);  
    }

    public void test_printSoldCount2sold() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Item item = new Item("cheap" + i)  ;
          
            if (i == 1 || i == 5)
                setBooleanValue(item, "sold", true);
            a.addItem(item);
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        a.printSoldCount();

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
          
        assertEquals("printSoldCount(2 sold)", "Number of sold items is: 2" + LS, st);
    }
        
    public void test_printSoldCountAllSold() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Item item = new Item("cheap" + i)  ;
  
            setBooleanValue(item, "sold", true);
            a.addItem(item);
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        a.printSoldCount();

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
          
        assertEquals("printSoldCount(2 sold)", "Number of sold items is: 10" + LS, st);
    }   
    
    public void test_printSoldCountNoSold() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Item item = new Item("cheap" + i)  ;
            a.addItem(item);
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        a.printSoldCount();

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
          
        assertEquals("printSoldCount(2 sold)", "Number of sold items is: 0" + LS, st);
    }   
    
    public void test_highestSold() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        // add 10 items
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Bid b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;
            if (i == 1 || i == 5)
                setBooleanValue(item, "sold", true);
            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        String name = a.highestSold();
        
        assertEquals("highetSold()", "cheap5", name);
    }
        
    public void test_highestSoldAllSold() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        // add 10 items
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            Bid b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;

            setBooleanValue(item, "sold", true);
            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        String name = a.highestSold();
        
        assertEquals("highetSold()", "cheap9", name);
    }  
    
    public void test_highestSoldAllSoldFirstHighest() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        // add 10 items
        Bid b;
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            if (i == 0)
                b = new Bid(p, 1000);
            else
                b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;

            setBooleanValue(item, "sold", true);
            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        String name = a.highestSold();
        
        assertEquals("highetSold()", "cheap0", name);
    } 
    
    public void test_highestSoldNoSold() throws NoSuchFieldException, IllegalAccessException
    {
        Auction a = new Auction();
        // add 10 items
        Bid b;
        for (int i = 0; i < 10; i++)
        {
            Person p = new Person("albus dumbledore" + i);
            if (i == 0)
                b = new Bid(p, 1000);
            else
                b = new Bid(p, 10 * i);
            Item item = new Item("cheap" + i)  ;

            setObjectValue(item, "highBid", b);
            a.addItem(item);
        }
        
        String name = a.highestSold();
        
        assertEquals("highetSold()", "none", name);
    }  
    
   public void test_main()
    {
          String stin = "item1" + LS + "bidder1" + LS + "100" + LS +
                        "item2" + LS + "bidder2" + LS + "200" + LS +
                        "item3" + LS + "bidder3" + LS + "300" + LS +
                        "item4" + LS + "bidder4" + LS + "400" + LS +
                        "item5" + LS + "bidder5" + LS + "500" + LS;
                                             
        AuctionMain.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        AuctionMain.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "please enter item name" + LS);
        actual = removeMessage(actual, "please enter bidder" + LS);
        actual = removeMessage(actual, "please enter offer" + LS);

 
        String expected = "Number of sold items is: 2" + LS + "Highest sold: item4" + LS;
        
        assertEquals("Window Main(): ", expected, actual);        
    }
}
