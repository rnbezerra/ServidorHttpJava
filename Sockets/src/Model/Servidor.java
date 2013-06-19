package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
	
	private boolean serverStopped;
	private ServerSocket serverSocket;
	
	public boolean isServerStopped() {
		return serverStopped;
	}

	public void stopServer() {
		serverStopped = true;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}


	//RUN================================================================
	@Override
	public void run() {
		super.run();
		
		try {
			this.serverSocket = new ServerSocket(80);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		while(! isServerStopped()){
	        Socket clientSocket = null;
	        try {
	            clientSocket = this.serverSocket.accept();
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
