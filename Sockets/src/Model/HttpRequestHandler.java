package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Model.httpHelper.HttpRequestResponse;
import Model.httpMethods.HttpPost;

public class HttpRequestHandler extends Thread{
	
	private Socket clientSocket;
	/*	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	*/
	public HttpRequestHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		super.run();
		try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
			
//			Scanner input  = new Scanner(clientSocket.getInputStream());
//			PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            
			//String request = readRequestToTheEnd(input);
			
			String response = new HttpPost().getResponse(new HttpRequestResponse());
						
			output.write(response.getBytes());
			output.flush();
			
            output.close();
            input.close();
            System.out.println("Request processed: " + response);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
	}

	private String readRequestToTheEnd(InputStream input) {
		 Scanner s = new Scanner(input).useDelimiter("\\A");
		 return s.hasNext() ? s.next() : "";
	}
	
	
}
