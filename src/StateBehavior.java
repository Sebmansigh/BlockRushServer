public abstract class StateBehavior
{
	///Tries to do something when event e is raised.
	///Returns whether or not it is successful, or throws
	///MustTransitionException if the state machine needs to change state
	public abstract boolean handle(ClientThread client,ClientEvent e) throws MustTransitionException;

	///Takes a line of input received from the client and decides what to do with it.
	///The calling client thread is passed in if you wish to raise any events with it.
	///May throw IllegalFormatException if the String is not valid.
	public abstract void processInput(ClientThread thread, String inputLine);
}
