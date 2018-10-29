public class NewBehavior extends StateBehavior
{
	public static NewBehavior instance;
	
	static
	{
		instance = new NewBehavior();
	}
	
	private NewBehavior(){}

	@Override
	public boolean handle(ClientThread client, ClientEvent e) throws MustTransitionException
	{
		switch(e)
		{
		case onKill:
			throw new MustTransitionException(ClientState.FINISHED);
		case onThreadStart:
			client.sendLine("hello");
			return true;
		case onMatchmakingEnqueue:
			throw new MustTransitionException(ClientState.ENQUEUED);
		}
		return false;
	}

	@Override
	public void processInput(ClientThread client, String inputLine)
	{
		switch(inputLine)
		{
		case "quickPlay":
			MatchmakingThread.enqueue(client);
			return;
		case "quit":
			client.kill();
			return;
		}
		throw new IllegalStateException("Unrecognized input line: '"+inputLine+"'");
	}
}
