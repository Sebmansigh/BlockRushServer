public class PlayingBehavior extends StateBehavior
{
	public static PlayingBehavior instance;
	
	static
	{
		instance = new PlayingBehavior();
	}
	
	private PlayingBehavior(){}

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

