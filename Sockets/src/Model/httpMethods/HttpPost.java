package Model.httpMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class HttpPost{

	public String getResponse() {

		//long time = System.currentTimeMillis();
        

		StringBuilder builder = new StringBuilder();
		
		String mensagem = "Resposta do servidor: dados, dados, dados...";

		builder.append("HTTP/1.1 200").append("\n")
	   	   .append("Connection: Keep-Alive\n")
//	   	   .append("Date: Thu, 06 Aug 1998 12:00:15 GMT\n")
	   	   .append("Server: Apache/1.3.0 (Unix)\n")
	   	   .append("Last-Modified: Mon, 22 Jun 1998\n")
	   	   .append("Content-Length: " + mensagem.length() + "\n")
	   	   .append("Content-Type: text/html;charset=utf-8\n")
	   	   .append("\n")
	   	   .append(mensagem + "\n")
	   	   .append("\n");
		return builder.toString();
	}
	
}
