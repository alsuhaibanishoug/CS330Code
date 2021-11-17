// A Java program for a Client
import java.net.*;
import java.io.*;


public class Client{

	// initialize socket and input output streams
	private Socket socket		 = null;
	private DataInputStream input = null;
	private DataInputStream in = null;
	private DataOutputStream out	 = null;



	// constructor to put ip address and port
	public Client(String address, int port)
	{

		// establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
			input = new DataInputStream(System.in);

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
			
			// takes input from the server socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

		// string to read message from input
		String line = "";
        

		// keep reading until "Over" is input

		while (!line.equals("3")){


			System.out.println("1- Open mode");
			System.out.println("2- Secure mode");
			System.out.println("3- Quit");
		
		
			try
            {
                line = input.readLine();

				if (line.startsWith("2")){
					line = Alice(line ,3);
				}	
					
					out.writeUTF(line);	
					line = in.readUTF();
					System.out.println(line);	
				
			 }
			
            catch(IOException i)
            {
                System.out.println(i);
            }
			
		}

		System.out.println("Closing connection");

		// close the connection
		try
		{
			input.close();
			in.close();
			out.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}
	public static String Alice(String s , int key)
	{

		char[] chars=s.toCharArray();
		 		
		for(int i=0 ; i< chars.length ; i++)
		{
				chars[i] +=key;
		}
	
		return String.valueOf(chars);
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 500);
	}
}

