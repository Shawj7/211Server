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

     public void run()
     {
       int n=0;
       while(elements>0)
       {
         buf.add(n);
         n++;
         elements--;
       }
     }
       
   }   