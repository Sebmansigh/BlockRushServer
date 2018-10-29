import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @author Sebmansigh
 *
 */
public class ClientThread extends Thread
{
	private Socket clientSocket;
	private volatile boolean keepLiving = true;
	public final int ClientID;
	private volatile ClientStateMachine stateMachine;
	
	private PrintWriter outputWriter = null;
	private BufferedReader inputReader = null;
	
	public ClientThread(int ID, Socket cs)
	{
		ClientID = ID;
		clientSocket = cs;

		stateMachine = new ClientStateMachine(this);
		
	}

	public void receiveEvent(ClientEvent e)
	{
		stateMachine.doEvent(e);
	}
	
	
	public void run()
	{
		System.out.println("Client Thread "+ClientID+" running");
		
		try
		{
			outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			receiveEvent(ClientEvent.onThreadStart);
			
			while(this.keepLiving && BlockRushServer.keepLiving)
			{
				clientSocket.setSoTimeout(30000);
				try
				{
					String inputLine = inputReader.readLine();
					if(inputLine == null)
					{
						break;
					}
					///The '!' message is simply a heartbeat message to keep the connection alive.
					///It does not affect anything else ever, so the server state doesn't need to know it happened.
					else if(! inputLine.equals("!"))
					{
						processInput(inputLine,outputWriter);
						outputWriter.flush();
					}
					/*
					else
					{
						System.out.println("Client "+ClientID+" Heartbeat.");
					}
					*/
				}
				catch(SocketTimeoutException e)
				{
					System.out.println("Client "+ClientID+" Timed Out.");
					kill();
				}
				catch(SocketException e)
				{
					System.out.println("Client "+ClientID+" Disconnected.");
					kill();
				}
			}
			
			//System.out.println("Client "+ClientID+" Exited loop normally.");
			clientSocket.close();
			//System.out.println("Client "+ClientID+" Socket closed.");
			inputReader.close();
			//System.out.println("Inputstream closed.");
			outputWriter.flush();
			outputWriter.close();
			//System.out.println("Outputstream closed.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//System.out.println("Finally Block Reached.");
			BlockRushServer.removeClient(this);
			//System.out.println("Client removed.");
			try
			{

				//System.out.println("Double-checking that socket is closed.");
				if(!clientSocket.isClosed())
				{
					clientSocket.close();
				}

				//System.out.println("Socket Closed for sure.");
			}
			catch(IOException e)
			{
				//Don't care, I was terminating the thread anyways.
			}

		}
		System.out.println("Client Thread "+ClientID+" Terminated");
	}
	
	public void kill()
	{
		//System.out.println("Client "+ClientID+" killed; currently dying.");
		stateMachine.doEvent(ClientEvent.onKill);
		keepLiving = false;
	}
	
	private void processInput(String inputLine,PrintWriter out)
	{
		stateMachine.processInput(this,inputLine,out);
	}
	
	public void sendLine(String string)
	{
		//System.out.println("Sending client "+ClientID+" string '"+string+"'");
		outputWriter.println(string);
		outputWriter.flush();
	}
}
