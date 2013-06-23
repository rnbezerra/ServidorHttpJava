package Model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.Scanner;

import Model.httpMethods.HttpGet;
import Model.httpMethods.HttpHead;
import Model.httpMethods.HttpPost;

public class HttpRequestHandler extends Thread{

	private Socket clientSocket;

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
			
			byte[] fileRead;
			
			if(request.toUpperCase().startsWith("POST")){
				HttpPost post = new HttpPost();
				response = post.getResponse(request);
				
				output.write(response.getBytes());
			}
			else if(request.toUpperCase().startsWith("GET")){
				HttpGet get = new HttpGet();
				response = get.getResponse(request);
				
				output.write(response.getBytes());
				
				if(get.isThereAFile()){
					File file = new File(get.getFileName());

					byte[] b = new byte[(int) file.length()];
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(b);
					fileInputStream.close();
					

					PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
					printWriter.println();					
					output.write(b);
				}
				
			}
			else if(request.toUpperCase().startsWith("HEAD")){
				//TODO pegar resposta do HEAD
				HttpHead head = new HttpHead();
				response = head.getResponse(request);
				
				output.write(response.getBytes());
			}
			
						
			
			output.flush();
			
            output.close();
            input.close();
            System.out.println(request);
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
