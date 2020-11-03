import java.util.*;

class user implements Runnable
   {											
     private int id;
     private int elements;
     public static Buffer buf;
     public user(int i, int elem, Buffer b)							//Created user will add a certain number of elements to the buffer.
     {
       id=i;
       elements=elem;
       buf=b;
     }

     public int getID()
     {
       return id;
     }
     public int getElements()
     {
       return elements;
     }
     public void giveElements(int elem)
     {
       elements=elements+elem;
     }

     public void run()
     {
       int n=0;
       while(n<elements)
       {
         buf.add(n, id, elements);
         //System.out.println("User " + id + " adds an element " + (n+1) + "/" + elements);
         
         n++;
         //elements--;
       }
     }
       
   }   