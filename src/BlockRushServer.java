import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Sebmansigh
 *
 */
public class BlockRushServer
{
	public static CopyOnWriteArraySet<ClientThread> Clients = new CopyOnWriteArraySet<ClientThread>();
	public static volatile boolean keepLiving = true;

	public static final ServerSocket serverSocket;
	
	static
	{
		ServerSocket temp;
		try
		{
			temp = new ServerSocket(22196);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			temp = null;
			System.exit(-1);
		}
		serverSocket = temp;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		CommandThread.thread.start();
		MatchmakingThread.thread.start();
		
		System.out.println("BlockRush game server v0.0");
		
		int IDnum = 0;
		while(keepLiving)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				
				ClientThread newClient = new ClientThread(++IDnum,clientSocket);
				addClient(newClient);
				newClient.start();
			}
			catch(SocketException e)
			{
				System.err.println("Connection Cancelled. "+e.getMessage());
				break;
			}
			catch(IOException e)
			{
				System.err.println("Connection Failed. "+e.getMessage());
				break;
			}
		}
		serverSocket.close();
		System.out.println("Main Thread terminated.");
	}

	static void addClient(ClientThread client)
	{
		Clients.add(client);
	}
	
	static void removeClient(ClientThread client)
	{
		Clients.remove(client);
	}
}
