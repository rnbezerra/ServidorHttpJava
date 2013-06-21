package Model.httpMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class HttpPost{

	public String getResponse(String request) {

		//long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
        String now = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(calendar.getTime());
        calendar.add(calendar.MONTH, -3);
        String lastModified = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(calendar.getTime());

        String connection = (request.contains("Connection: Keep-Alive") ? "Keep-Alive" : "Close");
        
		StringBuilder builder = new StringBuilder();
		
		String mensagem = "Resposta do servidor: dados, dados, dados...";

		builder.append("HTTP/1.1 200").append("\n")
	   	   .append("Connection: ").append(connection).append("\n")
	   	   .append("Date: ").append(now).append("\n")
	   	   .append("Last-Modified: ").append(lastModified).append("\n")
	   	   .append("Server: Apache/1.3.0 (Unix)\n")
	   	   .append("Content-Length: " + mensagem.length() + "\n")
	   	   .append("Content-Type: text/html;charset=utf-8\n")
	   	   .append("\n")
	   	   .append(mensagem + "\n")
	   	   .append("\n");
		return builder.toString();
	}
	
}
