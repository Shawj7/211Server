import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     									
	private LinkedList<Object> buf_list;
  private int buf_size;	
  semaphore uSem = new semaphore(buf_size);
  semaphore wSem = new semaphore(0);
  //private semaphore uSem = new semaphore(10);
  //private semaphore wSem = new semaphore(0);

  //private int elements; //the total amount of elements
  //private int currentAdded; //the amount of elements already added to the buffer		

	
  public Buffer(int n)						//Buffer creation, with n indicating the maximum capacity
	{
	   buf_list = new LinkedList<Object>();
     buf_size=n;
  }

  public void add(int n, int id, int elements)
  {
    uSem.p(1); //try to acquire the semaphore
      buf_list.add(n);
      System.out.println("User " + id + " adds an element " + (n+1) + "/" + elements);
      //System.out.println("Item Added");
    wSem.v(); //notify waiting servers
  }

  public void remove(int n, int id, int elements)
  {
    wSem.p(0);
    buf_list.remove(0);
    System.out.println("Server " + id + " removes an element " + (n+1) + "/" + elements);

    //System.out.println("Item Removed");
    uSem.v(); //notify waiting users
  }

  public int size()
  {
    return buf_list.size();
  }




}	  
