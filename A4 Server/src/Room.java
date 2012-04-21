/***********************************Room CLASS INFO********************************************

This class is responsible for creating a room along with the necessary variable, adding a 
message, getting the Room name and the messages, transforming the message to a string, 
and outputting the messages of the room in XML format.  

*********************************************************************************************/

import java.util.ArrayList;


public class Room {
	private String name;
	private ArrayList<Message> messages;
	public Room (String name) {
		this.name = name;
		messages = new ArrayList<Message>();
	}

	public void AddMessage(Message m) {
		messages.add(m);
	}

	public String getName()	{
		return name;
	}

	public ArrayList<Message> getMessages()	{
		return messages;
	}

	public String toString() {
		String r = "";
		for (int i = 0; i < messages.size(); i++) {
			r += messages.get(i).toString();
		}
		return r;
	}

	public String toXMLString(long since) {
		String r = "<?xml version=\"1.0\"?>";
		r += "<message-list>";
		for (int i = 0; i < this.getMessages().size(); i++) {
			String strTime = this.getMessages().get(i).getTime();
			long time = Long.parseLong(strTime);
			if (since < time) {
				r += this.getMessages().get(i).toXMLString();
			}
		}
		r += "</message-list>";
		return r;
	}
}
