/**********************************Message CLASS INFO*****************************************

This class is responsible for creating a message, getting and setting the name, getting and
setting the content of the message getting and setting the time, converting all the variables
into a string, and generating the XML to represent the message.  

*********************************************************************************************/

import java.net.URLDecoder;

public class Message {
	private String name;
	private String message;
	private String time;
	
	
	@SuppressWarnings("deprecation")
	Message(String name, String message, String time) {
		this.name = URLDecoder.decode(name);
		this.message = URLDecoder.decode(message);
		this.time = time;
	}

	public String getName()	{
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime()	{
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String toString() {
		return this.name + " " + this.message + " " + this.time;
	}
	
	public String toXMLString()	{
		String r = "<message user=\"" + this.getName() + "\" time=\"" + this.getTime() + "\">";
		r += this.getMessage();
		r += "</message>";
		return r;
	}
}
