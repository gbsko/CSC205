package com.gabeskorski.apps;

import java.util.LinkedList;
import java.util.Queue;

/*
 * class BST implements a Binary Search Tree (BST) that is used 
 * to demonstrate traversal algorithms.
 *
 * @creator gdt
 * @created 02016.11.13
 */

public class BST {

   public Node root;
   public int nodecnt;

   public BST() { root = null; nodecnt = 0; }

   public BST inOrderTraversal(Node node) {
      if (root == null) return this;
      if (node == null) node = root;
      if (node.left != null) 
         inOrderTraversal(node.left);
      node.visit(); 
      if (node.right != null) 
         inOrderTraversal(node.right);
      return this;
   }

   public BST preOrderTraversal(Node node) {
      if (root == null) return this;
      if (node == null) node = root;
      node.visit(); 
      if (node.left != null) 
         preOrderTraversal(node.left);
      if (node.right != null) 
         preOrderTraversal(node.right);
      return this;
   }

   public BST postOrderTraversal(Node node) {
      if (root == null) return this;
      if (node == null) node = root;
      if (node.left != null) 
         postOrderTraversal(node.left);
      if (node.right != null) 
         postOrderTraversal(node.right);
      node.visit(); 
      return this;
   }

   public BST levelOrderTraversal(Node node) {
      System.out.println("NEEDS TO BE IMPLEMENTED...");
      /*
       * the algorithm...
       * + instantiate a queue
       * + enqueue root node
       * + while queue not empty
       *   - node = dequeue
       *   - visit node (print)
       *   - enqueue node's children (left then right)
       */
      Queue<Object> q = new LinkedList<>();
      q.add(root);
      while(!q.isEmpty()){
    	  q.remove(node);
    	  node.visit();
    	  q.add(node.left);
    	  q.add(node.right);
      }
      
      return this;
   }

   public BST insert(int key) {
      if (root == null) {
         root = new Node(key, null);
         nodecnt++;
         return this;
      }
      return insert(key, root);
   }

   public BST insert(int key, Node parent) {
      if (key == parent.key) return this;
      nodecnt++;
      if (key < parent.key) {
         if (parent.left == null) {
            parent.left = new Node(key, parent);
            return this;
         }
         return insert(key, parent.left);
      } 
      if (parent.right == null) {
         parent.right = new Node(key, parent);
         return this;
      }
      return insert(key, parent.right);
   }

   // delete parameter key if found... always returns this 
   //
   public BST delete(int key) {
      /*
       * three cases:  node being deleted has zero kids; node being 
       * deleted has one child; node being deleted has two children
       */
	   
	   if (find(key) == null) return this;
	   
	   Node n = find(key);
	   
	   if (n.left ==  null && n.right == null){
		   n = null;
	   }
	   

	   

	   
	   
	   
      return this;
   }

   public Node find(int key) {
      if (root == null) return null;
      return find(key, root);
   }

   // begin search for parameter key starting at parameter n...
   // returns null if key not found
   //
   public Node find(int key, Node n) {
      //System.out.println("NEEDS TO BE IMPLEMENTED...");
      if (n == null) return null;
      if (n.key == key) return n;
      if (key < n.key){
    	  n = n.left;
      } else if (key > n.key) {
    	  n = n.right;
      }
      return find(key, n);
         }

   // begin search for minimum starting with parameter n...
   //
   public Node min(Node n) {
      //System.out.println("NEEDS TO BE IMPLEMENTED...");
      if (n == null) return n;
      n = n.left;
      return min(n);
   }

   // begin search for maximum starting with parameter n...
   //
   public Node max(Node n) {
      //System.out.println("NEEDS TO BE IMPLEMENTED...");
	     if (n == null) return n;
	      n = n.right;
	      return max(n);
   }

   /*
    * BST.main(String[] argv) is used to test class BST
    */
   public static void main(String[] argv) {

      int[] a = { 30, 20, 40, 18, 25, 24, 27, 23, 21, 22, 29, 35, 42 };

      // testing delete...
      for (int i = 0; i < a.length; i++)
         delete(init(a), a[i]);
      
      // testing min and max...
      BST tree = init(a);
      System.out.println("min = " + tree.min(tree.root).key +
                         "; max = " + tree.max(tree.root).key);

      // testing find...
      int[] f = { a[0], a[a.length-1], 205, a[a.length/2] };
      for (int i = 0; i < f.length; i++)
         System.out.println("find(" + f[i] + "): " + 
                            ((tree.find(f[i]) == null) ? 
                             "not found" : "found"));

      // testing level-order traversal...
      System.out.println("\nlevel-order traversal:");  
      tree.levelOrderTraversal(null);

      // testing deleting/traversal small trees (1, 2, 3 nodes)...
      deleteSmallTrees();

   }

   static BST init(int[] a) {
      BST tree = new BST();
      System.out.print("\ninputs: ");
      for (int i = 0; i < a.length; i++) {
         tree.insert(a[i]);
         System.out.print(a[i] + " ");
      }
      System.out.println();
      return tree;
   }

   static void delete(BST tree, int key) {
      tree.preOrderTraversal(null);
      System.out.println("\ndelete(" + key + ")...");
      tree.delete(key);
      tree.preOrderTraversal(null);
   }

   static void deleteSmallTrees() {

      BST t = new BST();
      System.out.println("\ninputs: 10");
      t.insert(10);
      t.levelOrderTraversal(null);
      System.out.println("\ndelete(10)...");
      t.delete(10);
      if (t.root != null) {
         System.out.println("\t??? there should be no traversal");
         t.levelOrderTraversal(null);
      } else
         System.out.println("\tempty tree (no traversal)");

      t = new BST();
      System.out.println("\ninputs: 10 5");
      t.insert(10);
      t.insert(5);
      t.inOrderTraversal(null);
      System.out.println("\ndelete(10)...");
      t.delete(10);
      t.inOrderTraversal(null);

      t = new BST();
      System.out.println("\ninputs: 10 15");
      t.insert(10);
      t.insert(15);
      t.inOrderTraversal(null);
      System.out.println("\ndelete(10)...");
      t.delete(10);
      t.inOrderTraversal(null);

      t = new BST();
      System.out.println("\ninputs: 10 15 5");
      t.insert(10);
      t.insert(15);
      t.insert(5);
      t.inOrderTraversal(null);
      System.out.println("\ndelete(10)...");
      t.delete(10);
      t.inOrderTraversal(null);

   }

   static void printTraversals(BST tree) {
      System.out.println("\nin-order:");    
      tree.inOrderTraversal(null);

      System.out.println("\npre-order traversal:");   
      tree.preOrderTraversal(null);

      System.out.println("\npost-order traversal:");  
      tree.postOrderTraversal(null); 

      System.out.println("\nlevel-order traversal:");  
      tree.levelOrderTraversal(null); 
   }
}

class Node {

   public int key; 
   public Node parent;
   public Node left;
   public Node right;

   public Node(int k, Node p) { 
      this(k, p, null, null);
   }

   public Node(int k, Node p, Node l, Node r) { 
      key = k; 
      parent = p;
      right = r; 
      left = l;
   }

   public void visit() { 
      System.out.print("\t" + key);
      if (parent == null) 
         System.out.print(" (root  node)");
      else
         System.out.print(" (parent: " + parent.key + ")"); 
      System.out.print(" left: ");
      if (left == null) System.out.print("na");
      else System.out.print(left.key);
      System.out.print("; right: ");
      if (right == null) System.out.println("na");
      else System.out.println(right.key);
   }
}
