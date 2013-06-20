package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private static boolean serverStopped;
	private static ServerSocket serverSocket;
	
	public static boolean isServerStopped() {
		return serverStopped;
	}

	public static void stopServer() {
		serverStopped = true;
	}

	public static ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void setServerSocket(ServerSocket serverSocket) {
		Servidor.serverSocket = serverSocket;
	}


	public static void main(String[] args) {
	
		try {
			serverSocket = new ServerSocket(80);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		while(! isServerStopped()){
	        Socket clientSocket = null;
	        try {
	            clientSocket = serverSocket.accept();
	        } catch (IOException e) {
	            if(isServerStopped()) {
	                System.out.println("Server Stopped.") ;
	                return;
	            }
	            throw new RuntimeException(
	                "Error accepting client connection", e);
	        }
	        
	        new HttpRequestHandler(clientSocket).start();
	    }
	}
	
}
