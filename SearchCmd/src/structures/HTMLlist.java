package structures;

import searchers.Searcher;
import searchers.Searcher1;

/**
 * @author Dimitrios Tsirozoglou
 * 
 * An HTMLlist is actually made to help us in the implementation of a Linked List. We can construct a linked list
 * be starting adding items (by making the pointer field pointing to another HTMLlist object) to an HTMLlist object.
 * 
 * An HTMLlist object can be constructed with two parameters declaring a linked list of String objects with a pointer
 * next, pointing to the next HTMLlist object in the list.
 * Or our HTMLlist can also be constructed with three parameters declaring a linked list of HTMLlist objects containing 
 * a field with a String, an HTMLlist and a pointer next pointing to the next HTMLlist object in the list.
 */
public class HTMLlist {
    public String str;
    public HTMLlist next;
    public HTMLlist url;

    /**
     * Constructs an HTMLlist that has two fields.
     * The field str which is the String of the HTMLlst.
     * The field next which is an HTMLlist pointer, pointing to an HTMLlist. 
     * @param s The first String Object of the HTMLlst.
     * @param n The HTMLlist pointer,pointing to an HTMLlist.
     */
    public HTMLlist (String s, HTMLlist n){
        str = s;
        next = n;
    }
    
    /**
     * Constructs an HTMLlist that has three fields.
     * The field str which is the String of the HTMLlst.
     * The field next which is an HTMLlist pointer, pointing to an HTMLlist. 
     * The field url which is the HTMLlist object  contained in our HTMLlist.
     * @param s The first String Object of the HTMLlst.
     * @param n The HTMLlist pointer,pointing to an HTMLlist.
     * @param u The first HTMLlist Object contained in our HTMLlist.
     */
    public HTMLlist (String s, HTMLlist n, HTMLlist u){
        str = s;
        next = n;
        url = u;
    }    
    
    /**
     * This method enquires the number of the items of an HTMLlist.
     * @param l The HTMLlist that we want to find the number of items it contains.
     * @return The number of the items in the given List.
     */
    public int getNumberOfItemsOfList(HTMLlist l){
    	int counter = 0; 
    	while (l != null){
            counter +=1;
            l = l.next;
        }
    	return counter;    	
    }
    
    /**
     * This method checks if the given HTMLlist equals this. HTMLlist
     * @param l The HTMLlist to be checked for equality
     * @return true if both lists have the same number and contains the same strings in their str field.
     */
    public boolean equals(HTMLlist l){
    	HTMLlist pageMatch = new HTMLlist (null,null);
    	int elements =l.getNumberOfItemsOfList(l);
    	while (l!=null){
    		if (Searcher.exists(this, l.str))
    			pageMatch = Searcher1.addToHTMLlist(pageMatch, l.str);
    		else
    			return false;
    		l = l.next;
    	}
    	if ((pageMatch.getNumberOfItemsOfList(pageMatch) == this.getNumberOfItemsOfList(this))
    			&(this.getNumberOfItemsOfList(this) == elements))
    		return true;
    	else
    		return false;
    }
    
    /* 
     * Overrides toString method of the object so as that we can have in a String
     * all the contains of the HTMLlist object.
     */
    public String toString(){
    	HTMLlist temp;
    	temp = this;
    	String listOfWebsites = "";
	
    	while (temp!=null){
    		listOfWebsites = listOfWebsites +" \n"+ temp.str;
    		temp = temp.next;
    	}
    	return listOfWebsites;
	}
}