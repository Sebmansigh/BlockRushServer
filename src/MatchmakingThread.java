import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 
 */

/**
 * @author Sebmansigh
 *
 */
public class MatchmakingThread extends Thread
{
	public static final MatchmakingThread thread;
	private final ConcurrentLinkedQueue<ClientThread> UnmatchedClientQueue;
	
	static
	{
		thread = new MatchmakingThread();
	}
	
	private MatchmakingThread()
	{
		UnmatchedClientQueue = new ConcurrentLinkedQueue<ClientThread>();
	}
	
	public void run()
	{
		while(BlockRushServer.keepLiving)
		{
			if(UnmatchedClientQueue.size() >= 2)
			{
				
			}
		}
		System.out.println("Matchmaking Thread Terminated.");
	}
	
	public static void enqueue(ClientThread t)
	{
		thread.UnmatchedClientQueue.add(t);
		t.receiveEvent(ClientEvent.onMatchmakingEnqueue);
	}
}
