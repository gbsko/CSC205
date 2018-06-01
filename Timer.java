package com.gabeskorski.apps;

/*
 * class Timer demonstrates the timing of code snippets by
 * using the largest locally defined int[] array it can instantiate.
 *
 * @creator gdt
 * @created 02017.03.25
 * @used again on 02017.10.24
 */

public class Timer {
   public static void main(String[] argv) {
      int[] x = null;
      int nbytes = Integer.MAX_VALUE;
      do {
         try {
            x = new int[nbytes];
         } catch (OutOfMemoryError e) {
            System.out.println("x = new int[" + nbytes + "] failed");
            nbytes /= 2;
         }
      } while (x == null);

      long t = System.nanoTime();
      for (int i = 0; i < x.length; i++)
         x[i] = i;
      p("x[" + x.length + "] init took ", t); 

      t = System.nanoTime();
      for (int i = 0; i < x.length; i++)
         x[i] = i % 2;
      p("...update took ", t);

      t = System.nanoTime();
      for (int i = 0; i < x.length && x[i] != -1; i++) 
         ; // do nothing... empty statement (NOP in assembly)
      p("...search took ", t); 

      int[] y = null;
      nbytes = x.length;
      do {
         try {
            y = new int[nbytes];
         } catch (OutOfMemoryError e) {
            System.out.println("y = new int[" + nbytes + "] failed");
            nbytes /= 2;
         }
      } while (y == null);

      t = System.nanoTime();
      for (int i = 0; i < y.length; i++) 
         y[i] = x[i];
      p("...copy into y[" + y.length + "] took ", t); 

      t = System.nanoTime();
      System.arraycopy(x, 0, y, 0, y.length);
      p("...System.arraycopy() took ", t); 
   }

   static int[] z = null;

   static void p(String h, long t) {
      long x = System.nanoTime() - t;
      System.out.println(h + x + " nanoseconds (" + 
                         (x / 1000000000.0) + " seconds)");

   }
}

/*
 *

x = new int[2147483647] failed
x = new int[1073741823] failed
x = new int[536870911] failed
x = new int[268435455] failed
x[134217727] init took 103121134 nanoseconds (0.103121134 seconds)
...update took 163402271 nanoseconds (0.163402271 seconds)
...search took 64043427 nanoseconds (0.064043427 seconds)
y = new int[134217727] failed
y = new int[67108863] failed
...copy into y[33554431] took 40217324 nanoseconds (0.040217324 seconds)
...System.arraycopy() took 45822301 nanoseconds (0.045822301 seconds)

 *
 */