import com.sun.net.httpserver.HttpServer;


public class retrieveClass {

	public static void getMessages(HttpServer server, MessageList messageList) {
		server.createContext("/retrieve", new EchoServer());
		//URLEncoder encode = new URLEncoder();

	}
}
