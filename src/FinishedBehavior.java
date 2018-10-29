public class FinishedBehavior extends StateBehavior
{
	public static FinishedBehavior instance;
	
	static
	{
		instance = new FinishedBehavior();
	}
	
	private FinishedBehavior(){}

	@Override
	public boolean handle(ClientThread client, ClientEvent e) throws MustTransitionException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processInput(ClientThread client, String inputLine)
	{
		
	}
}

