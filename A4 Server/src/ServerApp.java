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
	public static void main(String[] args) throws HTTPException, IOException
	{
		//MessageList messageList = new MessageList();
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/", new EchoServer());
	    server.setExecutor(null); // creates a default executor
	    server.start();
	}
}
