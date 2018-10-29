
public class MustTransitionException extends Exception
{
	public final ClientState newState;
	public MustTransitionException(ClientState c)
	{
		super("The operation was successful, but a transition to ClientState "+c.name()+" is required to proceed.");
		newState = c;
	}
	
	
	
	
	
	/**
	 * Eclipse wanted it. I'm not going to use it.
	 */
	private static final long serialVersionUID = -4950952323628850442L;
}
