package Model;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Scanner;

import Model.httpHelper.HttpRequestResponse;
import Model.httpMethods.HttpPost;

public class HttpRequestHandler extends Thread{

	private static final String POST = "post";
	private static final String GET = "get";
	private static final String HEAD = "head"; 
	
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
            
			String request = readRequestToTheEnd(input);
			
			String response = "";
			
			if(request.toUpperCase().startsWith("POST")){
				HttpPost post = new HttpPost();
				response = post.getResponse(post.translateInput(request));
			}
			else if(request.toUpperCase().startsWith("GET")){
				//TODO pegar resposta do GET
			}
			else if(request.toUpperCase().startsWith("HEAD")){
				//TODO pegar resposta do HEAD
			}
			
						
			output.write(response.getBytes());
			output.flush();
			
            output.close();
            input.close();
            System.out.println("Request processed: " + response + "\n" + request);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
	}

	private String readRequestToTheEnd(InputStream input) {
		StringBuilder builder = new StringBuilder();
		byte[] buffer;
		
		try {
			
			buffer = new byte[input.available()];
			input.read(buffer);
			for (byte b : buffer) {
				builder.append((char)b);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	
}
