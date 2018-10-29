public class EnqueuedBehavior extends StateBehavior
{
	public static EnqueuedBehavior instance;
	
	static
	{
		instance = new EnqueuedBehavior();
	}
	
	private EnqueuedBehavior(){}

	@Override
	public boolean handle(ClientThread client, ClientEvent e) throws MustTransitionException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processInput(ClientThread client, String inputLine)
	{
		// TODO Auto-generated method stub
		
	}
}

