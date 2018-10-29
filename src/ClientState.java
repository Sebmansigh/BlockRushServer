public enum ClientState
{
	NEW     (NewBehavior.instance),
	ENQUEUED(EnqueuedBehavior.instance),
	PLAYING (PlayingBehavior.instance),
	FINISHED(FinishedBehavior.instance);
	
	ClientState(StateBehavior b)
	{
		stateBehavior = b;
	}

	public final StateBehavior stateBehavior;
}
