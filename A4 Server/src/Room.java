import java.util.ArrayList;


public class Room
{
	private String name;
	private ArrayList<Message> messages;
	public Room (String name)
	{
		this.name = name;
		messages = new ArrayList<Message>();
	}
	
	public void AddMessage(Message m)
	{
		messages.add(m);
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<Message> getMessages()
	{
		return messages;
	}
	
	public ArrayList<Message> getMessagesSinceTime(long t)
	{
		//TODO: PETER!!!!
		return null;
	}
	
	public String toString()
	{
		String r = "";
		for (int i = 0; i < messages.size(); i++)
		{
			r += messages.get(i).toString();
		}
		return r;
	}
}
