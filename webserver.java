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
    public void run()
    {
      while(elements>0)
      {
        buf.remove();
        elements--;
      }
    }
   
  }   