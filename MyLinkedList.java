/*Program: MyLinkedList
 *Description: This program implements the "TBI" methods of a LinkedList class.
 *By: Gabe Skorski
 *Date: 11.24.2017
 */

package com.gabeskorski.apps;

/*
 * This application is a singly linked-list that is
 * the code base for a programming assignment.
 *
 * @creator gdt
 * @created 02017.04.01
 * @updated 02017.10.27
 */

class Node
{

	// instance variables...
	private Object item;
	private Node next;

	// constructor...
	public Node(Object x)
	{
		item = x;
		next = null;
	}

	// getter/setter methods...
	public Object getItem()
	{
		return item;
	}

	public Node getNext()
	{
		return next;
	}

	public void setItem(Object item)
	{
		this.item = item;
	}

	public void setNext(Node n)
	{
		next = n;
	}

	// return a String object representation of "this" Node object...
	public String toString()
	{
		return item + " (next is " + (next == null ? "" : "not ") + "null)";
	}

}

/*
 * class MyLinkedList is not a complete class (i.e. there are missing methods).
 * The class was primarily created to support the Queue data structure.
 */

public class MyLinkedList
{

	private Node head = null; // nth(1) is the head Node object
	private int size = 0; // size == 0 implies empty linked-list

	// return the number of items in "this" linked-list...
	public int getSize()
	{
		return size;
	}

	// return the head Node for "this" linked-list...
	public Node getHead()
	{
		return head;
	}

	// set the size of "this" linked-list to zero...
	public void clear()
	{
		size = 0;
		head = null;
	}

	/*
	 * TBI (To Be Implemented) -- The remaining instance methods need to be
	 * implemented as part of the #MyLinkedList assignment.
	 */

	/**
	 * TBI (To Be Implemented) Prints "this" linked-list starting at the Node
	 * object parameter. The print format: [ followed by a space followed the
	 * elements separated by a space followed by ] Example: [ 0 1 2 3 4 1 ]
	 *
	 * @param Node
	 *            to begin printing from
	 * @return this MyLinkedList
	 */
	public MyLinkedList print(Node n)
	{
		System.out.print("[ ");
		while (n != null)
		{
			System.out.print(n.getItem() + " ");
			n = n.getNext();
		}
		System.out.print("]");
		System.out.println("");

		return this;

	}

	/**
	 * TBI (To Be Implemented) Removes an item from this MyLinkedList object.
	 *
	 * @param item
	 *            to remove from this MyLinkedList object
	 * @return true if item removed; else return false
	 */
	public boolean remove(Object item)
	{

		Boolean removed = false;
		
		if (size ==0) return removed;
		
		Node c = head;
		if (c.getItem() == item)
		{
			head = c.getNext();
			removed = true;
			size--;
		} else
		{
			while (c != null)
			{

				Node n = c.getNext();

				if (n.getItem().equals(item))
				{
					if (n.getNext() == null)
					{
						c.setNext(null);
					} else
					{
						c.setNext(n.getNext());
					}
					removed = true;
					size--;
					break;
				} else
				{
					c = c.getNext();
				}
			}
		}
		return removed;
	}

	/**
	 * TBI (To Be Implemented) Inserts an item into this MyLinkList object.
	 *
	 * Insert examples: [7 8 9] insert(5, 0) does nothing (return false) [7 8 9]
	 * insert(5, 1) results in [5 7 8 9] [7 8 9] insert(5, 2) results in [7 5 8
	 * 9] [7 8 9] insert(5, 3) results in [7 8 5 9] [7 8 9] insert(5, 4) does
	 * nothing (return false)
	 *
	 * @param item
	 *            to insert into this MyLinkedList object
	 * @param index
	 *            where item is to be inserted
	 * @return true if item inserted; else return false
	 */
	public boolean insert(Object item, int at_i)
	{
		Boolean inserted = false;
		int count = 1;
		Node i = new Node(item);
		Node c = head;
		Node p = null;
		
		if (at_i == 0 || at_i > size) return inserted;
		
		while(c.getNext() != null && count < at_i){
				p = c;
				c = c.getNext();
				count++;
			}
		
		i.setNext(c);
		p.setNext(i);
		size++;
		inserted = true;	
		return inserted;
	}

	/*
	 * TBI (To Be Implemented
	 * 
	 * Adds a new node assigned the item parameter to the end of the
	 * LinkedList. If it is empty, makes the new node the head.
	 * 
	 * @param item to add to the LinkedList
	 * 
	 * @return this LinkedList
	 */
	public MyLinkedList add(Object item)
	{
		size++;
		Node n = new Node(item);
		if (head == null)
			head = n;
		else
		{
			Node t = head;
			while (t.getNext() != null)
				t = t.getNext();
			t.setNext(n);
		}
		return this;
	}

	/*
	 * test class MyLinkedList...
	 */
	public static void main(String[] argv)
	{

		MyLinkedList list = new MyLinkedList();
		Integer[] z =
		{ new Integer(0), new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(1), };
		for (int i = 0; i < z.length; i++)
			list.add(z[i]);
		System.out.println("test remove()...");
		System.out.print("linked-list: ");
		list.print(list.head);
		remove(list, z[1]);
		remove(list, z[0]);
		remove(list, z[1]);
		remove(list, z[4]);
		remove(list, z[2]);
		remove(list, z[3]);
		remove(list, z[2]);

		System.out.println("\ntest insert()...");
		for (int i = 0; i < z.length; i++)
			list.add(z[i]);
		System.out.print("linked-list: ");
		list.print(list.head);
		Integer rm0 = new Integer(9);
		Integer rm1 = new Integer(6);
		insert(list, rm0, 2);
		insert(list, new Integer(8), 0);
		insert(list, new Integer(5), 9);
		insert(list, rm1, list.getSize());
		// insert(list, rm1, 1);
		remove(list, rm0);
		remove(list, rm1);
	}

	static void remove(MyLinkedList mll, Object i)
	{
		char c = mll.remove(i) ? 'T' : 'F';
		System.out.print("remove(" + i + "): " + c + "; ");
		mll.print(mll.head);
	}

	static void insert(MyLinkedList mll, Object o, int i)
	{
		char c = mll.insert(o, i) ? 'T' : 'F';
		System.out.print("insert(" + o + ", " + i + "): " + c + "; ");
		mll.print(mll.head);
	}
}