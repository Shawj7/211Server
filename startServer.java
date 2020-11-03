import java.util.*;
public class startServer
   {
  	Buffer b;										//Creation of buffer object  
	private int elements=0;
	private int numUsers=0;
	private int numServers=0;
	private int bufferSize=0;
	private int reference=0;
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
	   											//Creates execution scenario between user and webservers on buffer
        
        long startTime = System.currentTimeMillis();		
																
											//Instantiate all objects (webserver, users, buffer)
	b = new Buffer(bufferSize);
	user users[] = new user[numUsers];
	webserver servers[] = new webserver[numServers];
	Thread uThreads[] = new Thread[numUsers];
	Thread wThreads[] = new Thread[numServers];
	//int uElemForEach=(elements/numUsers); //elements for each user
	//int wElemForEach=(elements/numServers); //elements for each webserver
	reference=elements; //keep the total number of elements for splitting across servers
	for(int i=0;i<numUsers;i++) //create all users 
	{
		users[i]= new user(i, 0, b);
	}
	for(int i=0;i<numServers;i++) //create all servers
	{
		servers[i]=new webserver(i,0,b);
	}
	while(elements>0) //assaign elements equally to users
	{
		for(int i=0;i<numUsers;i++)
		{
			if(elements<0)
			{
				break;
			}
			users[i].giveElements(1);
			elements--;
		}
	}
	while(reference>0) //assaign elements equally to servers
	{
		for(int i=0;i<numServers;i++)
		{
			if(reference<0)
			{
				break;
			}
			servers[i].giveElements(1);
			reference--;
		}
	}
	for(int i=0;i<numUsers;i++)
	{
		uThreads[i]=new Thread(users[i]);
		uThreads[i].start();
	}
	for(int i=0;i<numServers;i++)
	{
		wThreads[i]=new Thread(servers[i]);
		wThreads[i].start();
	}
	/*for(int i=0;i<numServers;i++) //create all webservers and threads and start them
	{
		if((reference%numServers)!=0)
		{
			wElemForEach=(reference%numServers);
		}
		else
		{
			wElemForEach=(reference/numServers);
		}
		servers[i]= new webserver(i, wElemForEach, b);
		reference=reference-wElemForEach;
		wThreads[i]=new Thread(servers[i]);
		wThreads[i].start();
	}*/
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

	for(int i=0; i<numUsers;i++)
	{
		System.out.println("User "+users[i].getID()+" created "+users[i].getElements()+" elements");
	}
	for(int i=0; i<numServers;i++)
	{
			System.out.println("Server "+servers[i].getID()+" consumed "+servers[i].getElements()+" elements");
	}
	
												//Outputs the total number of elements added/removed from user and webserver		
	System.out.println("-----------------------");

	System.out.println("Buffer has " + b.size() + " elements remaining");
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
