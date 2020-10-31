import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     									
	private LinkedList<Object> buf_list;
  private int buf_size;	
  private semaphore uSem = new semaphore(1);
  private semaphore wSem = new semaphore(0);

  //private int elements; //the total amount of elements
  //private int currentAdded; //the amount of elements already added to the buffer		

	
     public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	{
	   buf_list = new LinkedList<Object>();
     buf_size=n;
  }

  public void add(int n)
  {
    uSem.p(); //try to acquire the semaphore
      buf_list.add(n);
      System.out.println("Item Added");
    wSem.v(); //notify waiting servers
  }

  public void remove()
  {
    wSem.p();
    buf_list.remove(0);
    System.out.println("Item Removed");
    uSem.v(); //notify waiting users
  }


}	  
