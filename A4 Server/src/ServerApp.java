import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.URLEncoder;
import java.net.URLDecoder;


public class ServerApp extends EchoServer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HTTPException 
	 */
	public static void main(String[] args) throws HTTPException, IOException {
		// TODO Auto-generated method stub
		//HttpExchange exchange = new HttpExchange();
		EchoServer echo = new EchoServer();
		MessageList messageList = new MessageList();
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		
		server.createContext("/", new EchoServer());
		
		messageClass message = null;
		retrieveClass retrieve = null;
		////message.postMessage(server, messageList);
		//message(server);
		////retrieve.getMessages(server, messageList);
		//retrieve(server);
	    server.setExecutor(null); // creates a default executor
	    server.start();
		//handle(exchange);

		HttpExchange exchange = null;
		//echo.handle(exchange);
	}

	public static void message(HttpServer server) {
		server.createContext("/message", new EchoServer());
	}


	public static void retrieve(HttpServer server) {
		server.createContext("/retrieve", new EchoServer());
		//URLEncoder encode = new URLEncoder();

	}
}