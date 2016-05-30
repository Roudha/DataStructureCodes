
// doublyLinked.java
// demonstrates doubly-linked list
// to run this program: C>java DoublyLinkedApp
////////////////////////////////////////////////////////////////
class Node {
	public int idata; // data item
	public String data; // data item
	public Node next; // next link in list
	public Node previous; // previous link in list
	// -------------------------------------------------------------

	public Node(int d) // constructor
	{
		idata = d;
	}

	public Node(int d,String s) // constructor
	{
		idata = d;
		data = s;
	}

	// -------------------------------------------------------------
	public void displayLink() // display this link
	{
		System.out.print("key : " + idata + " data : " + data);
	}
	// -------------------------------------------------------------
} // end class Link
////////////////////////////////////////////////////////////////

class DoublyList {
	private Node first; // ref to first item
	private Node last; // ref to last item
	// -------------------------------------------------------------

	public DoublyList() // constructor
	{
		first = null; // no items on list yet
		last = null;
	}

	// -------------------------------------------------------------
	public boolean isEmpty() // true if no links
	{
		return first == null;
	}

	// -------------------------------------------------------------
	public void insertFirst(String s) // insert at front of list
	{
		Node newLink = new Node(0,s); // make new link

		if (isEmpty()) // if empty list,
			last = newLink; // newLink <-- last
		else
			first.previous = newLink; // newLink <-- old first
		newLink.next = first; // newLink --> old first
		first = newLink; // first --> newLink
	}

	// -------------------------------------------------------------
	public void insertLast(String s) // insert at end of list
	{
		Node newLink = new Node(0,s); // make new link
		if (isEmpty()) // if empty list,
			first = newLink; // first --> newLink
		else {
			last.next = newLink; // old last --> newLink
			newLink.previous = last; // old last <-- newLink
		}
		last = newLink; // newLink <-- last
	}

	// -------------------------------------------------------------
	public Node deleteFirst() // delete first link
	{ // (assumes non-empty list)
		Node temp = first;
		if (first.next == null) // if only one item
			last = null; // null <-- last
		else
			first.next.previous = null; // null <-- old next
		first = first.next; // first --> old next
		return temp;
	}

	// -------------------------------------------------------------
	public Node deleteLast() // delete last link
	{ // (assumes non-empty list)
		Node temp = last;
		if (first.next == null) // if only one item
			first = null; // first --> null
		else
			last.previous.next = null; // old previous --> null
		last = last.previous; // old previous <-- last
		return temp;
	}

	// -------------------------------------------------------------
	// insert dd just after key
	public boolean insertAfter(int key, int i,String s) { // (assumes non-empty list)
		Node current = first; // start at beginning
		while (current.idata != key) // until match is found,
		{
			current = current.next; // move to next link
			if (current == null)
				return false; // didn't find it
		}
		Node newLink = new Node(i,s); // make new link

		if (current == last) // if last link,
		{
			newLink.next = null; // newLink --> null
			last = newLink; // newLink <-- last
		} else // not last link,
		{
			newLink.next = current.next; // newLink --> old next
											// newLink <-- old next
			current.next.previous = newLink;
		}
		newLink.previous = current; // old current <-- newLink
		current.next = newLink; // old current --> newLink
		return true; // found it, did insertion
	}

	// -------------------------------------------------------------
	public Node deleteKey(int key) // delete item w/ given key
	{ // (assumes non-empty list)
		Node current = first; // start at beginning
		while (current.idata != key) // until match is found,
		{
			current = current.next; // move to next link
			if (current == null)
				return null; // didn't find it
		}
		if (current == first) // found it; first item?
			first = current.next; // first --> old next
		else // not first
				// old previous --> old next
			current.previous.next = current.next;

		if (current == last) // last item?
			last = current.previous; // old previous <-- last
		else // not last
				// old previous <-- old next
			current.next.previous = current.previous;
		return current; // return value
	}

	// -------------------------------------------------------------
	public void displayForward() {
		System.out.print("List (first-->last): ");
		Node current = first; // start at beginning
		while (current != null) // until end of list,
		{
			current.displayLink(); // display data
			current = current.next; // move to next link
		}
		System.out.println("");
	}

	// -------------------------------------------------------------
	public void displayBackward() {
		System.out.print("List (last-->first): ");
		Node current = last; // start at end
		while (current != null) // until start of list,
		{
			current.displayLink(); // display data
			current = current.previous; // move to previous link
		}
		System.out.println("");
	}

	// -------------------------------------------------------------
	public Node findMiddle() {
		Node slow, fast ;
		slow = fast = first ; 
		
		while(fast!=null)
		{
			fast= fast.next ;
			if(fast!=null && fast.next!=null) {
				slow = slow.next; 
				fast = fast.next; 
			}
		}
		return slow ;
	}
	
	// -------------------------------------------------------------
	public Node findMiddle2() {
		Node front, back ;
		front = first ; 
		back = last ; 
		
		while(front!=back && front.next!=back)
		{
			//front.displayLink(); System.out.print(" "); 
			//back.displayLink(); System.out.print(" "); 
			front = front.next ;
			back = back.previous ;
			System.out.flush();
		}
		return front ;
	}
	
	public int size()
	{
		int count = 0;
		
		Node current = first;
		
		while (current != null)
		{
			current = current.next;
			count++;
		}
		
		return count;
	}
	
	public void addList(DoublyList d)
	{
		Node current = d.first;
		
		while (current != null)
		{
			insertLast(current.data);
			current = current.next;
		}
	}

	public void reverse()
	{
		if (size() < 2) return;
		
		Node current = last;
		first = null;
		
		while (current != null) // until start of list,
		{
			insertLast(current.data);
			current = current.previous; // move to previous link
		}
	}

} // end class DoublyLinkedList
////////////////////////////////////////////////////////////////

class DoublyLinkedApp {
	public static void main(String[] args) { // make a new list
		

		DoublyList theList1 = new DoublyList();
		theList1.insertFirst("Roudha ");
		theList1.insertLast("M");

		System.out.println(theList1.size());
		
		theList1.displayForward();
		theList1.displayBackward();
		
		//theList.deleteFirst();
		theList1.displayForward();
		
		System.out.println(theList1.size());
		DoublyList theList2=new DoublyList();
		
		theList2.addList(theList1);
		theList2.insertLast("ME");
		theList2.displayForward();
		theList2.reverse();
		theList2.displayForward();
		
		theList1.deleteKey(0);
		theList1.displayForward();
	} // end main()
} // end class DoublyLinkedApp
////////////////////////////////////////////////////////////////