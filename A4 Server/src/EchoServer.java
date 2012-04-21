import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/*
 * The echo server will return information about the requests it receives
 */
public class EchoServer implements HttpHandler {

	String currAction;
	ParameterList parameterList;
	RoomList roomList;
	public EchoServer()	{
		roomList = new RoomList();
		parameterList = new ParameterList();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException, HTTPException	{
		parameterList = new ParameterList();
		String method = exchange.getRequestMethod();
		URI uri = exchange.getRequestURI();
		
		//Split up the URL into all its components and stick all the components into an arraylist of strings
		ArrayList<String> context = ParseURI(uri.toString());
		
		//keep track of the room and the action we've chosen to take
		String room = context.get(0);
		String action = context.get(1);
		currAction = action;
		
		//We've already extracted the room and the action from the list of elements
		//So we remove them to clean the arraylist up and so we don't have to
		//worry about them later
		context.remove(0);
		context.remove(0);
		
		//The rest of the elements will only be parameters and their values, so we can sort those out accordingly
		parameterList.GenerateParameters(context);
		roomList.setCurrRoom(room);
		try {
			// in the header specify that the body will contain HTML
			Headers respHeaders = exchange.getResponseHeaders();
			respHeaders.set("Content-Type", "text/xml");

			//if the desired action is to post a message
			if (action.equals("message")) {
				Message(respHeaders, exchange, method, uri, respHeaders);
			}
			else if (action.equals("retrieve")) {
				Retrieve(respHeaders, exchange, method, uri, respHeaders);
			}
			else {
				//We don't support any other action!!
				throw new HTTPException(404);
			}
		}
		catch (IOException e) {
			System.err.println("Could not process command... aborting.");
			return;
		}
	}

	/************ArrayList Method************
	If desired action is to post a message 
	 */
	private void Message(Headers respHeaders, HttpExchange exchange, String method, URI uri, Headers reqHeaders) throws IOException	{
		// acknowledge the request
		exchange.sendResponseHeaders(200, 0);
		OutputStream respBody = exchange.getResponseBody();
		
		//Fill in all the fields of the message
		String user = parameterList.getValueFromParameterName("user");
		String content = parameterList.getValueFromParameterName("content");
		
		//Get the time of the message, in milliseconds since the epoch
		Calendar c = Calendar.getInstance();
		Long l = c.getTimeInMillis();
		String time = l.toString();
		
		Message m = new Message(user, content, time);
		roomList.AddMessageToCurrentRoom(m);
		respBody.close();
	}

	/************ArrayList Method************
	If desired action is to retrieve message(s)
	 */
	private void Retrieve(Headers respHeaders, HttpExchange exchange, String method, URI uri, Headers reqHeaders) throws IOException {
		// acknowledge the request
		exchange.sendResponseHeaders(200, 0);
		OutputStream respBody = exchange.getResponseBody();

		long since = 0;
		if (parameterList.ParameterExists("since")) {
			since = Long.parseLong(parameterList.getValueFromParameterName("since"));
		}
		
		respBody.write(roomList.getXMLForCurrentRoom(since).getBytes());
		respBody.close();
	}

	/************ArrayList Method************
	Transforms the string of the URI into an String ArrayList
	by splitting it up into individual parameters.   
	 */
	private ArrayList<String> ParseURI(String s) {
		String stringURI = s;
		String delims = "[/?&=]";
		String[] parsedURI = stringURI.split(delims);
		System.out.println(parsedURI.length);
		ArrayList<String> r = new ArrayList<String>();
		int count = 0;
		while (count < parsedURI.length) {
			System.out.println(parsedURI[count]);
			r.add(parsedURI[count]);
			count++;
		}
		r.remove("");
		return r;
	}
}
