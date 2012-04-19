import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Iterator;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/*
 * The echo server will return information about the requests it receives
 */
@SuppressWarnings("restriction")  // disables the "access restriction" warnings for com.sun.net.httpserver.*
public class EchoServer implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException, HTTPException {

		String method = exchange.getRequestMethod();
		URI uri = exchange.getRequestURI();
		Headers reqHeaders = exchange.getRequestHeaders();

		try {

			// in the header specify that the body will contain HTML
			Headers respHeaders = exchange.getResponseHeaders();
			respHeaders.set("Content-Type", "text/html");

			// acknowledge the request
			exchange.sendResponseHeaders(200, 0);

			OutputStream respBody = exchange.getResponseBody();
			respBody.write("<?xml version=\"1.0\"?>".getBytes());
			respBody.write("<html><body><table>".getBytes());

			// print the request information

			// 1. method and URI
			respBody.write(keyValueToHTMLString("Method", method).getBytes());
			respBody.write(keyValueToHTMLString("URI", uri.toString()).getBytes());

			// 2. headers
			Iterator<String> iter = reqHeaders.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				respBody.write(keyValueToHTMLString(key, reqHeaders.get(key).toString()).getBytes());
			}

			// and we're done...
			respBody.write("</table></body></html>".getBytes());
			respBody.close();
		}
		catch (IOException e) {
			System.err.println("Could not process message... aborting.");
			return;
		}

	}

	private String keyValueToHTMLString(String key, String value) {
		return "<tr><td>" + key + "</td><td>" + value + "</td></tr>";
	}
}