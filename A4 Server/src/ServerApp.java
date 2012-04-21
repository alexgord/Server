/**************************************ID BLOCK**********************************************

Due Date:			April 22nd, 2012
Software Designers:	Alex Simard & Peter Johnston
Course:				420-603  Winter 2012
Deliverable:		Assignment #4 Part II --- Server
Description:		This program creates a server which supports dynamically created "chat rooms.  
					Users can post messages along with a user name to a room by using the 
					appropriate URL format, or by using the Android app.  Users can then retrieve 
					messages stored in a room, either from the beginning of time, or from a certain
					point (measured in milliseconds from the JAVA epoch).  
					Message format: 
					http://localhost:8000/room/message?user=peter&content=hello%20world
					Retrieve format:
					http://localhost:8000/room/retrieve?since=200

******************************************************************************************************/

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.HttpServer;


public class ServerApp extends EchoServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HTTPException 
	 */
	public static void main(String[] args) throws HTTPException, IOException {
		int port = Integer.parseInt(args[0]);
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/", new EchoServer());
	    server.setExecutor(null); // creates a default executor
	    server.start();
	}
}
