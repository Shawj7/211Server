import java.util.*;
public class semaphore
  {
    private int count; 
    private int size; //total size of buffer

    public semaphore(int value)
    {
      count=value;
    }

    public synchronized void p()
    {
      count--;
      if(count<0)
      {
        try
        {
            wait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      }
    }

    public synchronized void v()
    {
        count++;
        notify();
    }
      
    
     
   //This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
   //can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.

  }

