import java.io.PrintWriter;

public class ClientStateMachine
{
	private ClientState state;
	private ClientThread client;
	
	public ClientStateMachine(ClientThread t)
	{
		client = t;
		state = ClientState.NEW;
	}
	
	public void doEvent(ClientEvent event)
	{
		try
		{
			if(!state.stateBehavior.handle(client, event))
			{
				throw new IllegalStateException("Attempted to perform event "+event.name()+" on state "+state.name());
			}
		}
		catch (MustTransitionException e)
		{
			state = e.newState;
			System.out.println("Client State -> "+state.name());
		}
	}

	public void processInput(ClientThread client,String inputLine, PrintWriter out)
	{
		state.stateBehavior.processInput(client,inputLine);
	}

}
