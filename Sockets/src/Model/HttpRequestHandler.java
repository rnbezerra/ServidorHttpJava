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
            
			String request = readRequestToTheEnd(input);
			
			String response = new HttpPost().getResponse(new HttpRequestResponse());
						
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
		/*
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StringBuilder builder = new StringBuilder();
		
		try {
			
			byte[] buffer = new byte[1024]; //you can configure the buffer size
			while (input.read(buffer) != -1){//
				builder.append(buffer.toString());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();
		*/
	}
	
	
}
