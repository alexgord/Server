import com.sun.net.httpserver.HttpServer;


public class messageClass {
	public static void postMessage(HttpServer server, MessageList messageList) {
		server.createContext("/message", new EchoServer());
		System.out.println("Awww yeahh!!");
		
	}
}
