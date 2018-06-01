/*Program: MySet
 *Description: This program implements the "TBI" methods of a Set data structure.
 *By: Gabe Skorski
 *Date: 11.07.2017
 */

package com.gabeskorski.apps;

/**
 * This Java application implements a Set data structure
 * that stores a set of Objects. MySet objects have a fixed 
 * capacity (i.e. they don't grow) and duplicate members
 * are not allowed.
 *
 * @creator gdt 
 * @created 02017.03.04
 */

interface CSC205_Set {
   boolean isMember(Object obj);
   boolean isEmpty();
   boolean isFull();
   int capacity();
   int size();
   void clear();
   String toString();
   int add(Object obj);
   void remove(Object obj);
   boolean equals(MySet that);
   MySet union(MySet that);
   MySet intersection(MySet that);
   MySet difference(MySet that);
}

public class MySet implements CSC205_Set {

   public static final int ADD_DUP_ERRNO = -1;
   public static final int ADD_FULL_ERRNO = -2;

   private static final int CAPACITY = 7;

   private Object[] data; 
   private int size;

   /**
    * Constructs a MySet object having a default CAPACITY.
    */
   public MySet() { this(CAPACITY); }

   /**
    * Constructs a MySet object having a client supplied capacity.
    *
    * @param capacity  becomes CAPACITY if <= 0
    */
   public MySet(int capacity) {
      if (capacity <= 0) capacity = CAPACITY;
      data = new Object[capacity];
      size = 0;
   }

   /**
    * Constructs a MySet object containing Integer objects.
    *
    * @param e int[] of values that get added to this MySet
    */
   public MySet(int[] e) {
      data = new Object[CAPACITY];
      size = e.length > CAPACITY ? CAPACITY : e.length;
      for (int i = 0; i < size; i++)
         data[i] = new Integer(e[i]);
   }

   /**
    * Checks to see of an object is a member of this MySet.
    * 
    * @param obj the class Object object to search for
    * @return true if object is a member of this MySet
    * @return false if object is not a member of this MySet
    */
   public boolean isMember(Object obj) {
      for (int i = 0; i < size; i++)
         if (data[i].equals(obj))
            return true;
      return false;
   }

   /**
    * Checks to see if this MySet has zero members (elements).
    *
    * @return true if this MySet has zero members
    * @return false if this MySet has more than zero members
    */
   public boolean isEmpty() { return size == 0; }

   /**
    * Checks to see if this MySet is at capacity.
    *
    * @return true if at capacity
    * @return false if not at capacity
    */
   public boolean isFull() { return size == data.length; }

   /**
    * Returns the number of members in this MySet.
    *
    * @return number of members in this MySet
    */
   public int size() { return size; }

   /**
    * An alias for size().
    *
    * @return number of members
    */
   public int cardinality() { return size; }

   /**
    * Getter method so client can check the capacity of this MySet.
    *
    * @return capacity of this MySet
    */
   public int capacity() { return data.length; }

   /**
    * Sets the size of this MySet to zero.
    */
   public void clear() { size = 0; }

   /**
    * Constructs a String representation of this MySet.
    *
    * @return string representation of this MySet
    */
   public String toString() {
      StringBuffer sb = new StringBuffer("{");
      int n = size - 1;
      for (int i = 0; i < n; i++)
         sb.append(data[i] + ", ");
      if (size > 0)
         sb.append(data[n]);
      return new String(sb) + "}";
   }

   /* 
    * TBI (To Be Implemented) -- The remaining instance methods
    * need to be implemented as part of the #Set assignment.
    */

   /**
    * TBI (To Be Implemented)
    * Adds an object to this MySet. Duplicates are prohibited.
    *
    * @param obj the class Object to add to this MySet
    * @return ADD_FULL_ERRNO if this MySet is at capacity; 
    *         else return ADD_DUP_ERRNO if object already 
    *         a member of this MySet; else return the new 
    *         size of this MySet 
    */
   public int add(Object obj) {
	   if (this.isFull()) return ADD_FULL_ERRNO;
	   if (this.isMember(obj)) return ADD_DUP_ERRNO;
	   this.data[size] = obj;
	   size++;
	   return size;
   }

   /**
    * TBI (To Be Implemented)
    * Removes an object from this MySet. The method does nothing
    * if the object is not a member. 
    *
    * @param obj the object to remove from this MySet
    */
   public void remove(Object obj) {
	   if (this.isMember(obj)){
		   for(int i = 0; i < this.size(); i++){
			   if(this.data[i].equals(obj)){
				   size--;
				   for(int j = i; j < this.size(); j++){
					   this.data[j] = this.data[j+1];
				   }
			   }
		   }
		   
	   }
   }

   /**
    * TBI (To Be Implemented)
    * Checks to see if two MySet objects are equal.
    * hint: a.difference(b) and b.difference(a) 
    *       are both empty, then...
    *
    * @param that MySet to compare again this MySet
    * @return true if the two MySets are equal; else
    *         return false 
    */
   public boolean equals(MySet that) {
	   if (this.difference(that).size() == 0 && that.difference(this).size() == 0){
		   return true;
	   } else {
		   return false;
	   }
   }

   /**
    * TBI (To Be Implemented)
    * Instantiates a new MySet object that contains all of the elements 
    * of this MySet and all of the elements of that MySet (duplicates 
    * are not allowed).
    *
    * @param that MySet to do union with this MySet
    * @return the union of this and that MySets
    */
   public MySet union(MySet that) {
	   MySet uniSet = new MySet();
	   for(int i = 0; i < this.size(); i++){
		   uniSet.add(this.data[i]);
	   }
	   for(int i = 0; i < that.size(); i++){
		   uniSet.add(that.data[i]);
	   }
	   return uniSet;
   }

   /**
    * TBI (To Be Implemented)
    * Instantiates a new MySet object that contains all of the 
    * members that are found in both this MySet and that MySet.
    *
    * @param that MySet to do intersection with this MySet
    * @return the intersection of this and that MySets
    */
   public MySet intersection(MySet that) {
	   MySet interSet = new MySet();
	   for(int i = 0; i < that.size(); i++){
		   if (this.isMember(that.data[i])){
			   interSet.add(that.data[i]);
		   }
	   }
	   return interSet;
   }

   /**
    * TBI (To Be Implemented)
    * Instantiates a new MySet object that contains all of the 
    * members of this MySet that are not found in that MySet.
    *
    * @param that MySet to do difference with this MySet
    * @return the difference of this and that MySets
    */
   public MySet difference(MySet that) {
	   MySet diffSet = new MySet();
	   for(int i = 0; i < this.size; i++){
		   diffSet.add(this.data[i]);
	   }
	   for(int i = 0; i < that.size(); i++){
		   if (diffSet.isMember(that.data[i])){
			diffSet.remove(that.data[i]);   
		   }
	   }
	   
	   return diffSet;
   }

   /**
    * The main() method is used exclusively for testing.
    */
   public static void main(String[] argv) {
      Object[] objects = {
         new Integer(205),
         new String("Java supports OOP"),
         new Boolean(true),
         new Byte((byte)42),
         new Integer(240),
         new Byte((byte)42),
         new String("foo"),
         new Boolean(true),
         new String("foo"),
         new String("Java creator: James Gosling"),
         new Integer(240),
         new Double(3.14159265),
         new Object(),
      };

      MySet test = new MySet();

      for (int i = 0; i < objects.length; i++) {
         System.out.print(objects[i]);
         int rv = test.add(objects[i]);
         switch (rv) {
            case ADD_FULL_ERRNO: 
               System.out.println(" not added b/c test is full");
               break;
            case ADD_DUP_ERRNO:
               System.out.println(" not added b/c it's a duplicate");
               break;
            default:
               System.out.println(" added (" + rv + ")");
               break;
         }
      }
      System.out.println(test);
      dump(test);
      test.clear();
      System.out.println("test cleared: " + test);
      dump(test);

      final String SEPARATOR = "\n=========================";
      System.out.println(SEPARATOR);

      final int[] A = { 5, 7, 3, 2 };
      final int[] B = { 2, 6 };
      final int[] C = { 1, 2, 5 };         
      final int[] D = { 3, 2, 7, 5 };       
      final int[] E = { 5, 2, 1 };

      MySet a = new MySet(A); System.out.println("A: " + a);
      MySet b = new MySet(B); System.out.println("B: " + b);
      MySet c = new MySet(C); System.out.println("C: " + c);
      MySet d = new MySet(D); System.out.println("D: " + d);
      MySet e = new MySet(E); System.out.println("E: " + e);

      System.out.println(a + ".equals(" + b + ") is " + a.equals(b));
      System.out.println(c + ".equals(" + d + ") is " + c.equals(d));
      System.out.println(c + ".equals(" + e + ") is " + c.equals(e));

      System.out.println(a + ".union(" + b + "): " + a.union(b));
      System.out.println(a + ".intersection(" + b + "): " + a.intersection(b));
      System.out.println(a + ".difference(" + b + "): " + a.difference(b));
      System.out.println(b + ".difference(" + a + "): " + b.difference(a)); 

      System.out.println(c + ".union(" + d + "): " + c.union(d));
      System.out.println(c + ".intersection(" + d + "): " + c.intersection(d)); 
      System.out.println(c + ".difference(" + d + "): " + c.difference(d));
      System.out.println(d + ".difference(" + c + "): " + d.difference(c));
      System.out.println(c + ".difference(" + e + "): " + c.difference(e));

      System.out.println(a + ".union(" + a + "): " + a.union(a));
      System.out.println(a + ".intersection(" + a + "): " + a.intersection(a));
      System.out.println(a + ".difference(" + a  +"): " + a.difference(a));

      Integer x = new Integer(A[1]);
      System.out.print(a);
      a.remove(x);
      System.out.println(".remove(" + x + "): " + a);
      
      // miscelleanous testing...
      MySet n0 = new MySet();
      MySet n1 = new MySet();
      System.out.println(n0 + ".union(" + n1 + "): " + n0.union(n1));
      System.out.println(n0 + ".intersection(" + n1 + "): " + 
                         n0.intersection(n1)); 
      System.out.println(n0 + ".difference(" + n1 + "): " + n0.difference(n1));
      System.out.println(n0 + ".union(" + a + "): " + n0.union(a));
      System.out.println(n0 + ".intersection(" + a + "): " + 
                         n0.intersection(a)); 
      System.out.println(n0 + ".difference(" + a + "): " + n0.difference(a));
      System.out.println(a + ".union(" + n0 + "): " + a.union(n0));
      System.out.println(a + ".intersection(" + n0 + "): " + 
                         a.intersection(n0));
      System.out.println(a + ".difference(" + n0 + "): " + a.difference(n0));
   }

   private static void dump(MySet set) {
      System.out.println("...isEmpty(): " + set.isEmpty() + "; isFull(): " +
                         set.isFull() + "; size(): " + set.size() +
                         "; capacity(): " + set.capacity());
   }
   
}

/*
 * The output of you program must match the following...
 *

205 added (1)
Java supports OOP added (2)
true added (3)
42 added (4)
240 added (5)
42 not added b/c it's a duplicate
foo added (6)
true not added b/c it's a duplicate
foo not added b/c it's a duplicate
Java creator: James Gosling added (7)
240 not added b/c test is full
3.14159265 not added b/c test is full
java.lang.Object@6bc7c054 not added b/c test is full
{205, Java supports OOP, true, 42, 240, foo, Java creator: James Gosling}
...isEmpty(): false; isFull(): true; size(): 7; capacity(): 7
test cleared: {}
...isEmpty(): true; isFull(): false; size(): 0; capacity(): 7

=========================
A: {5, 7, 3, 2}
B: {2, 6}
C: {1, 2, 5}
D: {3, 2, 7, 5}
E: {5, 2, 1}
{5, 7, 3, 2}.equals({2, 6}) is false
{1, 2, 5}.equals({3, 2, 7, 5}) is false
{1, 2, 5}.equals({5, 2, 1}) is true
{5, 7, 3, 2}.union({2, 6}): {5, 7, 3, 2, 6}
{5, 7, 3, 2}.intersection({2, 6}): {2}
{5, 7, 3, 2}.difference({2, 6}): {5, 7, 3}
{2, 6}.difference({5, 7, 3, 2}): {6}
{1, 2, 5}.union({3, 2, 7, 5}): {1, 2, 5, 3, 7}
{1, 2, 5}.intersection({3, 2, 7, 5}): {2, 5}
{1, 2, 5}.difference({3, 2, 7, 5}): {1}
{3, 2, 7, 5}.difference({1, 2, 5}): {3, 7}
{1, 2, 5}.difference({5, 2, 1}): {}
{5, 7, 3, 2}.union({5, 7, 3, 2}): {5, 7, 3, 2}
{5, 7, 3, 2}.intersection({5, 7, 3, 2}): {5, 7, 3, 2}
{5, 7, 3, 2}.difference({5, 7, 3, 2}): {}
{5, 7, 3, 2}.remove(7): {5, 3, 2}
{}.union({}): {}
{}.intersection({}): {}
{}.difference({}): {}
{}.union({5, 3, 2}): {5, 3, 2}
{}.intersection({5, 3, 2}): {}
{}.difference({5, 3, 2}): {}
{5, 3, 2}.union({}): {5, 3, 2}
{5, 3, 2}.intersection({}): {}
{5, 3, 2}.difference({}): {5, 3, 2}

*/