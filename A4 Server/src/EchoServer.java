import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.net.URLEncoder;

/*
 * The echo server will return information about the requests it receives
 */
@SuppressWarnings("restriction")  // disables the "access restriction" warnings for com.sun.net.httpserver.*
public class EchoServer implements HttpHandler {

	ArrayList<Room> rooms;
	String currRoom;
	String currAction;
	ArrayList<Parameter> parameters;
	public EchoServer()
	{
		rooms = new ArrayList<Room>();
		parameters = new ArrayList<Parameter>();
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException, HTTPException
	{

		String method = exchange.getRequestMethod();
		URI uri = exchange.getRequestURI();
		Headers reqHeaders = exchange.getRequestHeaders();
		ArrayList<String> context = ParseURI(uri.toString());
		String room = context.get(0);
		String action = context.get(1);
		currAction = action;
		context.remove(0);
		context.remove(0);
		parameters = GenerateParameters(context);
		//parameters = 
		URLEncoder encode;
		if (!RoomExists(room))
		{
			rooms.add(new Room(room));
		}
		currRoom = room;
		try {

			// in the header specify that the body will contain HTML
			Headers respHeaders = exchange.getResponseHeaders();
			respHeaders.set("Content-Type", "text/html");

			if (action.equals("message"))
			{
				Message(respHeaders, exchange, method, uri, respHeaders);
			}
			else
			{
				if (action.equals("retrieve"))
				{
					Retrieve(respHeaders, exchange, method, uri, respHeaders);
				}
				else
				{
				}
			}

		}
		catch (IOException e) {
			System.err.println("Could not process message... aborting.");
			return;
		}
	}

	private String keyValueToHTMLString(String key, String value) {
		return "<tr><td>" + key + "</td><td>" + value + "</td></tr>";
	}

	private void Message(Headers respHeaders, HttpExchange exchange, String method, URI uri, Headers reqHeaders) throws IOException
	{
		// acknowledge the request
		exchange.sendResponseHeaders(200, 0);
		OutputStream respBody = exchange.getResponseBody();
		//respBody.write("<?xml version=\"1.0\"?>".getBytes());
		//respBody.write("<html><body><table>".getBytes());

		// print the request information

		// 1. method and URI
		//respBody.write(keyValueToHTMLString("Method", method).getBytes());
		//respBody.write(keyValueToHTMLString("URI", uri.toString()).getBytes());

		// 2. headers
		//Iterator<String> iter = reqHeaders.keySet().iterator();
		//while (iter.hasNext()) {
		//String key = iter.next();
		//respBody.write(keyValueToHTMLString(key, reqHeaders.get(key).toString()).getBytes());
		//}

		// and we're done...
		//respBody.write("</table></body></html>".getBytes());
		respBody.write(("You are now in Message in room " + currRoom).getBytes());
		respBody.close();
	}

	private void Retrieve(Headers respHeaders, HttpExchange exchange, String method, URI uri, Headers reqHeaders) throws IOException
	{
		// acknowledge the request
		exchange.sendResponseHeaders(200, 0);
		OutputStream respBody = exchange.getResponseBody();
		//respBody.write("<?xml version=\"1.0\"?>".getBytes());
		//respBody.write("<html><body><table>".getBytes());

		// print the request information

		// 1. method and URI
		//respBody.write(keyValueToHTMLString("Method", method).getBytes());
		//respBody.write(keyValueToHTMLString("URI", uri.toString()).getBytes());

		// 2. headers
		//Iterator<String> iter = reqHeaders.keySet().iterator();
		//while (iter.hasNext()) {
		//String key = iter.next();
		//respBody.write(keyValueToHTMLString(key, reqHeaders.get(key).toString()).getBytes());
		//}

		// and we're done...
		//respBody.write("</table></body></html>".getBytes());
		respBody.write("You are now in Retrieve<br />".getBytes());
		for (int i = 0; i < parameters.size(); i++)
		{
			respBody.write((parameters.get(i).ToString() + "<br />").getBytes());
		}
		respBody.close();
	}

	private ArrayList<String> ParseURI(String s)
	{
		String stringURI = s;
		String delims = "[/?&=]";
		String[] parsedURI = stringURI.split(delims);
		System.out.println(parsedURI.length);
		ArrayList<String> r = new ArrayList<String>();
		int count = 0;
		while (count < parsedURI.length)
		{
			System.out.println(parsedURI[count]);
			r.add(parsedURI[count]);
			count++;
		}
		r.remove("");
		return r;
	}
	
	public boolean RoomExists(String s)
	{
		boolean r = false;
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).equals(s))
			{
				r = true;
				break;
			}
		}
		return r;
	}
	
	public ArrayList<Parameter> GenerateParameters(ArrayList<String> input)
	{
		ArrayList<Parameter> r = new ArrayList<Parameter>();
		for (int i = 0; i < input.size(); i++)
		{
			r.add(new Parameter(input.get(0), input.get(1)));
			input.remove(0);
			input.remove(0);
		}
		return r;
	}
}
