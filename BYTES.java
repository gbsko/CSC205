package com.gabeskorski.apps;

import java.util.*;

/*
 * This is the start of a program that is going to be a 
 * Bingo-like game called BYTES. One human (the user)
 * plays against one or more imaginary players.
 *
 * @creator gdt
 * @created 02014.02.22
 * @version 02014.03.16
 * @version 02014.03.23
 *          + moved getBalls() from class Card to class Ball
 *          + made String[] names class level vs. local to main()
 *          + renamed Card.print() to Card.display()
 *          + added winner() instance method to class Player to
 *            increment games won counter
 *          + added played() instance method to class Player to
 *            increment games played counter
 *          + play() does a return instead of System.exit()
 *            when user enters EXIT value 
 * @see http://azfoo.net/gdt/csc110/assignments/bytes.html
 */

public class BYTES {
   private static Vector<Player> players = new Vector<Player>();
   private static Scanner in = new Scanner(System.in);
   private static int nplayers;
   private static Player human = null;
   private static String[] names = { "Doug Engelbart", "Grace Hopper", 
           "Charles Babbage", "Ada Lovelace", "Anita Borg", "Dennis Ritchie", 
           "Jim Gray", "Alan Turing", "George Boole", "Seymour Cray", 
           "Vannevar Bush", "Randy Pausch", "John McCarthy", "John Postel" };

   public static void main(String[] argv) {
      System.out.print("Enter your name: ");
      human = new Player(in.nextLine().trim(), true);
      players.add(human);
      jumble(names);
      do { 
         System.out.print("Enter number of players (1 or more): ");
         nplayers = in.nextInt();
      } while (nplayers <= 0);
      int i, j;
      for (i = 1, j = 0; i < nplayers && j < names.length; i++, j++)
         players.add(new Player(names[j]));
      if (i < nplayers) 
         do players.add(new Player()); 
         while (++i < nplayers);
      System.out.println("Hello " + human.getName() + 
                         "! You're playing BYTES with the following players:");
      for (i = 1; i < nplayers; i++)
         System.out.println("\t" + players.elementAt(i).getName());
      play();
      for (i = 0; i < nplayers; i++)
         System.out.println(players.elementAt(i));
   }

   private static void play() {
      final int EXIT = -1;
      final int PEEK = 0;
      Random rng = new Random(System.currentTimeMillis()); 
      Ball[] balls = Ball.getBalls();
      int nballs = balls.length;
      int iball = 0;
      Ball ball = null;
      char col;
      String row;
      int i, x, y;
      boolean winner = false;
      Player p = null;
      we_have_a_winner:
      do {
         human.displayCard();
         ball = balls[iball];
         x = ball.getCol();
         y = ball.getRow();
         col = Card.BYTES[x];
         row = Card.PICKS[x][y];
         System.out.println(col + " ==> " + row);
         for (i = 0; i < nplayers; i++) {
            p = players.elementAt(i);
            winner = p.checkCard(p, x, y);
            if (winner) 
               break we_have_a_winner;
         }
         do {
            System.out.print("enter an integer to continue (" + 
                             EXIT + " to exit; " + PEEK + " to peek): ");
            y = in.nextInt();
            if (y == EXIT) return;
            if (y != PEEK) break;
            for (i = 1; i < nplayers; i++) {
               p = players.elementAt(i);
               System.out.println(i + ") " + p.getName());
            }
            System.out.println(i + ") peek at everyone");
            System.out.print("enter id [1-" + nplayers + 
                             "] of who you want to peek at: ");
            i = in.nextInt();
            if (i < 0 || i > nplayers) {
               System.out.println("invalid peek id entered");
               continue;
            }
            if (i == nplayers) {
               i = 1;
               y = nplayers;
            } else
               y = i + 1;
            for (; i < y; i++) {
               p = players.elementAt(i);
               System.out.println("peeking at " + p.getName());
               p.displayCard();
            }
         } while (true);
         if (++iball == nballs) {
            System.out.println("exhausted the balls");
            break;
         }
      } while (!winner);
      if (winner) {
         if (p.getIsHuman()) 
            System.out.println("BYTES! You're a winner " + 
                                human.getName() + "!");
         else
            System.out.println("Bummer.... " + p.getName() + " has won.");
         System.out.println("Here's the winning card...");
         p.displayCard();
         p.winner();
      }
      for (i = 0; i < nplayers; i++) 
         players.elementAt(i).played();
   }

   public static void jumble(Object[] x) {
      Object tmp = null;
      final int NSWAPS = 32;
      int i, j;
      int n = 0;
      Random rng = new Random(System.currentTimeMillis()); 
      do {
         i = rng.nextInt(x.length);
         j = rng.nextInt(x.length);
         tmp = x[i];
         x[i] = x[j];
         x[j] = tmp;
      } while (++n < NSWAPS);
   }
}

class Player {
   // class data...
   private static int name_i = 0;
   private static String ANONYMOUS = "Anonymous";

   // instance data...
   private String name;
   private Card card; 
   private int ngames;
   private int nwon;
   private boolean isHuman;

   public Player() {
      name = makeName();
      card = new Card();
      ngames = nwon = 0;
      isHuman = false;
   }

   public Player(String name) {
      this.name = name;
      card = new Card();
      ngames = nwon = 0;
      isHuman = false;
   }

   public Player(String name, boolean human) {
      this(name);
      isHuman = true;
   }

   public boolean getIsHuman() { return isHuman; }

   public String getName() { return name; }

   public void displayCard() { card.display(); }

   public void winner() { nwon++; }

   public void played() { ngames++; }

   public boolean checkCard(Player p, int x, int y) {
      return card.check(p, x, y);
   }


   public String toString() {
      return name + " won " + nwon + " of " + ngames + " game(s)";
   }

   private String makeName() {
      name_i++;
      return ANONYMOUS + name_i;
   }

}

class Card {
   // class data...
   public final static char[] BYTES = { 'B', 'Y', 'T', 'E', 'S' };
   public final static String[][] PICKS = {
      // http://azfoo.net/gdt/csc110/assignments/bytes.html
      { "if", "else", "switch", "while", "do", "for", "break", "continue",
        "return", "try", "catch", "finally", "import", "super", "throw" },
      { "+", "-", "/", "*", "%", "==", "!=", "<", ">", "<=", ">=",
        "&&", "||", "!",  "?:" },
      { "boolean", "byte", "char", "short", "int", "long", "float", "double",
        "^",  "&",  "|",  ">>",  "<<",  ">>>",  "~" },
      { "static", "final", "const", "public", "private", "protected",
        "++",  "--",  "()",  "unary +",  "unary -",  "[] ", ".",  
        "new",  "instanceof" },
      { "c", "c++", "java", "perl", "php", "python", "ruby", "bash", "c#", 
        "objective-c", "javascript", "basic", "fortran", "awk", "go" },
   };
   private static final int MARKED = -1;
   private static final String BYTEMARK = "*BYTES*";
   private static final int CELL_WIDTH = 12;
   private static final int USED_ARRAY_PADDING = 64;
   private static int NROWS = 5;
   private static int NCOLS = 5;
   private static int seedadj = 1;

   // instance data...
   private byte[][] card; 
   private boolean used[][] = new boolean[NROWS][USED_ARRAY_PADDING];
   private Random rng;

   public Card() {
      rng = new Random(System.currentTimeMillis() + seedadj);
      seedadj++;  // used to avoid duplicate cell entries
      card = new byte[NROWS][NCOLS];
      int i, j, x;
      // note: middle cell is not a freebie...
      for (i = 0; i < NROWS; i++) 
         for (j = 0; j < NCOLS; j++) {
            x = rng.nextInt(PICKS[i].length);
            while (used[i][x]) {
               x++;
               if (x >= PICKS[i].length)
                  x = 0;
            }
            card[i][j] = (byte)x;
            used[i][x] = true;
         }
   }

   public void display() {
      int i, j, x;
      x = CELL_WIDTH * BYTES.length;
      for (j = 0; j < x; j++)
         System.out.print("=");
      System.out.println();
      for (j = 0; j < BYTES.length; j++) {
         System.out.print(BYTES[j]);
         for (i = 1; i < CELL_WIDTH; i++)
            System.out.print(" ");
      }
      System.out.println();
      x = CELL_WIDTH * BYTES.length;
      for (j = 0; j < x; j++)
         System.out.print("=");
      System.out.println();
      for (i = 0; i < NROWS; i++) {
         for (j = 0; j < NCOLS; j++) 
            if (card[j][i] == MARKED)
               pad(BYTEMARK);
            else 
               pad(PICKS[j][card[j][i]]); 
         System.out.println();
      }
   }

   public boolean check(Player p, int x, int y) {
      for (int i = 0; i < card[x].length; i++) {
         if (card[x][i] == y) {
            card[x][i] = MARKED;
            if (p.getIsHuman()) 
               System.out.println("You got one!");
            else
               System.out.println(p.getName() + " got one... d'oh");
            return isWinner(x, i);
         }
      }
      return false;
   }

   private boolean isWinner(int a, int b) {
      int i = 0;
      int j = 0;
      for (; i < NROWS; i++)
         if (card[a][i] != MARKED)
            break;
      if (i == NROWS)
         return true;
      for (i = 0; i < NCOLS; i++)
         if (card[i][b] != MARKED)
            break;
      if (i == NCOLS)
         return true;
      // check top-right to bottom-left diagonal...
      if (a == b) {
         i = 0;
         for (j = 0; j < NROWS; i++, j++)
            if (card[i][j] != MARKED)
               return false;
         return true;
      }
      // check top-left to bottom-right diagonal...
      for (i = 0, j = NCOLS - 1; i < NROWS; i++, j--)
         if (card[i][j] != MARKED)
            return false;
      return true;
   }

   private void pad(String s) {
      System.out.print(s);
      for (int i = s.length(); i < CELL_WIDTH; i++)
         System.out.print(" ");
   }

   public void dump() {
      for (int i = 0; i < NROWS; i++) {
         for (int j = 0; j < NCOLS; j++) 
            System.out.print(card[j][i] + " "); 
         System.out.println();
      }
   }

}

class Ball {
   private byte col;
   private byte row;

   public Ball(int c, int r) { 
      col = (byte)c; 
      row = (byte)r; 
   }
   public byte getCol() { return col; }
   public byte getRow() { return row; }

   public static Ball[] getBalls() {
      int i, j, n = 0;
      for (i = 0; i < Card.BYTES.length; i++) 
         n += Card.PICKS[i].length;
      Ball[] balls = new Ball[n];
      n = 0;
      for (i = 0; i < Card.BYTES.length; i++)
         for (j = 0; j < Card.PICKS[i].length; j++)
            balls[n++] = new Ball(i, j);
      // BYTES.jumble(balls);
      Ball tmp = null;
      Random rng = new Random(System.currentTimeMillis()); 
      do {
         i = rng.nextInt(balls.length);
         j = rng.nextInt(balls.length);
         tmp = balls[i];
         balls[i] = balls[j];
         balls[j] = tmp;
      } while (--n >= 0);
      return balls;
   }
}