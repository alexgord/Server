import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


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
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
	    server.createContext("/test", new EchoServer());
	    server.setExecutor(null); // creates a default executor
	    server.start();
		//handle(exchange);
		
		HttpExchange exchange = null;
		//echo.handle(exchange);
	}

}
