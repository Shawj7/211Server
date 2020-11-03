import java.util.*;

class webserver implements Runnable
  {										//Web server removes elements from the buffer

    private int id;
    private int elements;
    public static Buffer buf;

    public webserver(int i, int elem, Buffer b)			
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
        buf.remove(n, id, elements);
        //System.out.println("Server " + id + " removes an element " + (n+1) + "/" + elements);
        n++;
      }
    }
   
  }   