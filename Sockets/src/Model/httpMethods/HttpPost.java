package Model.httpMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class HttpPost{

	public String getResponse(String request) {
		
		//long time = System.currentTimeMillis();
        

		StringBuilder builder = new StringBuilder();
		request = request.replace("\n\n", "\n");

		builder.append("HTTP/1.1 200 OK").append("\n")
	   	   .append("Connection: Keep-Alive\n")
	   	   .append("Date: Thu, 06 Aug 1998 12:00:15 GMT\n")
	   	   .append("Server: Apache/1.3.0 (Unix)\n")
	   	   .append("Last-Modified: Mon, 22 Jun 1998\n")
	   	   .append("Content-Length: " + request.length() + "\n")
	   	   .append("Content-Type: text/html;charset=utf-8\n")
	   	   .append("\n")
	   	   .append("Requisição enviado pelo cliente ao servidor:\n")
	   	   .append(request)
	   	   .append("\n");
		return builder.toString();
	}
	
}
