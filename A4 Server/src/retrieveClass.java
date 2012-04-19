import com.sun.net.httpserver.HttpServer;


public class retrieveClass {

	
	
	public static void getMessages(HttpServer server) {
		server.createContext("/retrieve", new EchoServer());
		//URLEncoder encode = new URLEncoder();

	}
}
