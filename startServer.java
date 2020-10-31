import java.util.*;
public class startServer
   {
  	Buffer b;										//Creation of buffer object  
	private int elements=0;
	private int numUsers=0;
	private int numServers=0;
	private int bufferSize=0;
	private Scanner scan = new Scanner(System.in); //for reading user input


        public startServer()
   {	
	System.out.println("Enter buffer capacity");					//Insert user inputted values for program execution
	bufferSize=scan.nextInt();
    System.out.println("Enter number of users");
    numUsers=scan.nextInt();
    System.out.println("Enter number of servers");
    numServers=scan.nextInt();
    System.out.println("Enter total number of elements");
	elements=scan.nextInt();
    //startServer start = new startServer();
	   											//Creates execution scenario between user and webservers on buffer
        
        long startTime = System.currentTimeMillis();		
																
											//Instantiate all objects (webserver, users, buffer)
	b = new Buffer(bufferSize);
	user users[] = new user[numUsers];
	webserver servers[] = new webserver[numServers];
	Thread uThreads[] = new Thread[numUsers];
	Thread wThreads[] = new Thread[numServers];
	
	for(int i=0;i<numUsers;i++) //create all users and threads and start them
	{
		users[i]= new user(i, (elements/numUsers), b);
		uThreads[i]=new Thread(users[i]);
		uThreads[i].start();
	}
	for(int i=0;i<numServers;i++) //create all webservers and threads and start them
	{
		servers[i]= new webserver(i, (elements/numServers), b);
		wThreads[i]=new Thread(servers[i]);
		wThreads[i].start();
	}
	
	try
	{
		for(int i=0; i<numUsers; i++)
		{
			uThreads[i].join();
		}
		for(int i=0; i<numServers; i++)
		{
			wThreads[i].join();
		}
		
	}
   catch(InterruptedException e)
	{
		System.out.println("Error");
	}


									
	
												//Equally subdivide user inputted elements across all user objects

	System.out.println("-----------------------");
	
												//Outputs the total number of elements added/removed from user and webserver		

	System.out.println("-----------------------");
	//System.out.println("Buffer has " + X + " elements remaining");			//Check to see buffer if all elements produced from users have been successfully removed by webservers
	System.out.println("-----------------------");
												//Checks if all users and web servers successfully finished
				
	long endTime = System.currentTimeMillis();
	System.out.println("-----------------------");
     	System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		
	
    }
  
public static void main(String[] args)
  {

    /*System.out.println("Enter buffer capacity");					//Insert user inputted values for program execution
    bufferSize=scan.nextInt();
    System.out.println("Enter number of users");
    numUsers=scan.nextInt();
    System.out.println("Enter number of servers");
    numServers=scan.nextInt();
    System.out.println("Enter total number of elements");
	elements=scan.nextInt();*/
    startServer start = new startServer();
  }
}
