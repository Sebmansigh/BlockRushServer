import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public final class ServerCommand
{
	private ServerCommand(){}
	
	public static void execute(String commandString,PrintStream out)
	{
		String[] commandParts = commandString.trim().split(" +");
		String commandName = commandParts[0].toLowerCase();

		execute(commandName,out,  Arrays.copyOfRange(commandParts, 1, commandParts.length)  );
	}
	
	public static void execute(String commandName,PrintStream out,String... args)
	{
		//System.out.println("RECIEVED COMMAND "+commandName+Arrays.toString(args));
		switch(commandName)
		{
		case "quit":
			if(args.length == 0)
			{
				out.println("Shutting down server...");
				BlockRushServer.keepLiving = false;
				try
				{
					BlockRushServer.serverSocket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				StringBuilder outStr = new StringBuilder();
				outStr.append("To use command 'quit', do not pass parameters.");
				out.println(outStr);
			}
			break;
		case "numConnections":
			if(args.length == 0)
			{
				out.println("There are "+BlockRushServer.Clients.size()+" clients connected to the server.");
			}
			else
			{
				StringBuilder outStr = new StringBuilder();
				outStr.append("To use command 'numConnections', do not pass parameters.");
				out.println(outStr);
			}
			break;
		}
	}
}
