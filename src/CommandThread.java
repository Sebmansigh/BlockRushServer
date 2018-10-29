import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class CommandThread extends Thread
{
	public static final CommandThread thread;
	
	static
	{
		thread = new CommandThread(System.in);
	}
	
	private InputStream inStream;
	private CommandThread(InputStream in)
	{
		inStream = in;
	}

	public void run()
	{
		/*
		Scanner Scan = new Scanner(System.in);
		while(Scan.hasNextLine())
		{
			System.out.println(Scan.nextLine());
		}
		*/
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		while(BlockRushServer.keepLiving)
		{
			try
			{
				String commandString;
				while((commandString = in.readLine()) != null)
				{
					ServerCommand.execute(commandString, System.out);
					if(!BlockRushServer.keepLiving)
					{
						System.out.println("Server shutting down.");
						break;
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Command Thread Terminated");
	}
}
