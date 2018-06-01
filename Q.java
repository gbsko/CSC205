/*Program: Q
 *Description: This program implements the "TBI" methods of class Q.
 *By: Gabe Skorski
 *Date: 11.11.2017
 */

package com.gabeskorski.apps;

import java.util.LinkedList;

/**
 * class Q implements a Queue ADT using class LinkedList from the Java class
 * library.
 *
 * @creator gdt
 * @created 02017.03.11
 */

public class Q
{

	// all Q objects have the same default capacity...
	private final static int DFLT_CAPACITY = 4;

	// every Q object has the following instance variables...
	private LinkedList<Object> ll;
	private int capacity;

	/**
	 * Constructs an empty Q having DFLT_CAPACITY.
	 */
	public Q()
	{
		this(DFLT_CAPACITY);
	}

	/**
	 * Constructs an empty Q having client supplied capacity.
	 *
	 * @param capacity
	 *            switched to DFLT_CAPACITY if less than one
	 */
	public Q(int c)
	{
		capacity = c < 1 ? DFLT_CAPACITY : c;
		ll = new LinkedList<Object>();
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Adds an object to the "end" of this Q.
	 *
	 * @param object
	 *            to add
	 * @return true if queue content changed; false if param is null or queue is
	 *         full
	 */
	public boolean enqueue(Object o)
	{
		Boolean added;
		if (o == null || this.isFull())
		{
			added = false;
		} else
		{
			ll.addLast(o);
			added = true;
		}
		return added;
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Removes and returns the "front" object from this Q.
	 *
	 * @return front of queue or null if queue is empty
	 */
	public Object dequeue()
	{
		if (this.isEmpty())
			return null;
		return ll.removeFirst();
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Returns, but does not remove, "front" object in this Q.
	 *
	 * @return front of queue or null if queue is empty
	 */
	public Object front()
	{
		if (this.isEmpty())
			return null;
		return ll.peek();
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Returns number of objects in this Q.
	 *
	 * @return size of queue
	 */
	public int size()
	{
		return ll.size();
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Returns the capacity of this Q.
	 *
	 * @return capacity of queue
	 */
	public int capacity()
	{
		return this.capacity;
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Q is full if size equals capacity.
	 *
	 * @return true if full
	 */
	public boolean isFull()
	{
		return this.capacity() == this.size();
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Q is empty if size is zero.
	 *
	 * @return true if empty
	 */
	public boolean isEmpty()
	{
		return this.size() == 0;
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Removes all of the objects from this Q.
	 */
	public void clear()
	{
		ll.clear();
	}

	/**
	 * Construct a string representation of this Q.
	 *
	 * @return string representation of this queue
	 */
	public String toString()
	{
		return ll.toString() + "\n...size: " + ll.size() + "; capacity: " + capacity + "; isFull(): " + isFull()
				+ "; isEmpty(): " + isEmpty();
	}

	/**
	 * TBI (To Be Implemented)
	 *
	 * Constructs and returns a new Q that contains that the second "half" of
	 * this Q. New Q capacity equals this Q capacity. In addition, the last half
	 * of this Q is removed.
	 * 
	 * Examples...
	 *
	 * this Q before split: 1, 3, 4, 8 this Q after split: 1, 3 new Q: 4, 8
	 *
	 * this Q before split: 1, 3, 4, 8, 14 this Q after split: 1, 3 new Q: 4, 8,
	 * 14
	 *
	 * this Q before split: 13 this Q after split: 13 new Q: empty (i.e. size is
	 * 0)
	 *
	 * @return new Q object
	 */
	public Q split()
	{
		Q newQ = new Q(this.capacity);

		if (this.size() > 1)
		{

			for (int i = 0; i < this.size(); i++)
			{
				newQ.ll.addFirst(this.ll.removeLast());
			}
		}
		return newQ;
	}

	/**
	 * The main() method is used to test class Q.
	 */
	public static void main(String[] argv)
	{
		String[] items =
		{ "1st", "2nd", "3rd", "4th", "5th", };
		Q q = new Q();
		System.out.println("*** test enqueue() ***");
		for (int i = 0; i < items.length; i++)
		{
			System.out.print(items[i] + "...");
			if (!q.enqueue(items[i]))
				System.out.print("not ");
			System.out.println("enqueued");
		}
		System.out.println(q);
		System.out.println("\n*** test front() and dequeue() ***");
		for (int i = 0, j = q.size(); i < j; i++)
		{
			System.out.print("front(): " + q.front());
			System.out.println("; dequeue(): " + q.dequeue());
		}
		System.out.println(q);
		if (q.front() == null)
			System.out.println("\n*** test front() when Q empty... passed ***");
		q.enqueue("James");
		q.enqueue("Gosling");
		System.out.println("\n*** test clear() ***");
		System.out.println("before: " + q);
		q.clear();
		System.out.println("after: " + q);
		System.out.println("\n*** test split() odd size ***");
		test_split(0);
		System.out.println("\n*** test split() even size ***");
		test_split(1);
		q = new Q();
		System.out.println("\n*** test split() empty Q ***");
		System.out.println("before split(): " + q);
		Q n = q.split();
		System.out.println("after split(): " + q);
		System.out.println("new Q: " + n);
		q = new Q();
		q.enqueue("foo");
		System.out.println("\n*** test split() size=1 Q ***");
		System.out.println("before split(): " + q);
		n = q.split();
		System.out.println("after split: " + q);
		System.out.println("new Q: " + n);

	}

	private static void test_split(int x)
	{
		final int NEW_Q = 8;
		Q q = new Q(NEW_Q * 2);
		for (int i = 1 - x; i < NEW_Q; i++)
			q.enqueue(new Integer(i));
		System.out.println("before split(): " + q);
		Q newq = q.split();
		System.out.println("after split(): " + q);
		System.out.println("new Q: " + newq);
	}

}

/*
 *
 *** 
 * test enqueue() *** 1st...enqueued 2nd...enqueued 3rd...enqueued
 * 4th...enqueued 5th...not enqueued [1st, 2nd, 3rd, 4th] ...size: 4; capacity:
 * 4; isFull(): true; isEmpty(): false
 *** 
 * test front() and dequeue() *** front(): 1st; dequeue(): 1st front(): 2nd;
 * dequeue(): 2nd front(): 3rd; dequeue(): 3rd front(): 4th; dequeue(): 4th []
 * ...size: 0; capacity: 4; isFull(): false; isEmpty(): true
 *** 
 * test front() when Q empty... passed ***
 *** 
 * test clear() *** before: [James, Gosling] ...size: 2; capacity: 4; isFull():
 * false; isEmpty(): false after: [] ...size: 0; capacity: 4; isFull(): false;
 * isEmpty(): true
 *** 
 * test split() odd size *** before split(): [1, 2, 3, 4, 5, 6, 7] ...size: 7;
 * capacity: 16; isFull(): false; isEmpty(): false after split(): [1, 2, 3]
 * ...size: 3; capacity: 16; isFull(): false; isEmpty(): false new Q: [4, 5, 6,
 * 7] ...size: 4; capacity: 16; isFull(): false; isEmpty(): false
 *** 
 * test split() even size *** before split(): [0, 1, 2, 3, 4, 5, 6, 7] ...size:
 * 8; capacity: 16; isFull(): false; isEmpty(): false after split(): [0, 1, 2,
 * 3] ...size: 4; capacity: 16; isFull(): false; isEmpty(): false new Q: [4, 5,
 * 6, 7] ...size: 4; capacity: 16; isFull(): false; isEmpty(): false
 *** 
 * test split() empty Q *** before split(): [] ...size: 0; capacity: 4;
 * isFull(): false; isEmpty(): true after split(): [] ...size: 0; capacity: 4;
 * isFull(): false; isEmpty(): true new Q: [] ...size: 0; capacity: 4; isFull():
 * false; isEmpty(): true
 *** 
 * test split() size=1 Q *** before split(): [foo] ...size: 1; capacity: 4;
 * isFull(): false; isEmpty(): false after split: [foo] ...size: 1; capacity: 4;
 * isFull(): false; isEmpty(): false new Q: [] ...size: 0; capacity: 4;
 * isFull(): false; isEmpty(): true
 *
 * 
 */