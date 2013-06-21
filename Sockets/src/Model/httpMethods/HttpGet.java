package Model.httpMethods;

import java.text.DateFormat;
import java.util.Calendar;


public class HttpGet
{
	
	public String getResponse(String request) {
		
	    

		StringBuilder builder = new StringBuilder();
		request = request.replace("\n\n", "\n");
		DateFormat dataFormato = DateFormat.getDateInstance(DateFormat.FULL);
		Calendar data = Calendar.getInstance();
		String mensagem = "Resposta do servidor: dados, dados, dados...";
		
		builder.append("HTTP/1.1 200 OK\n");
		
		if(request.contains("Keep-Alive"))
			builder.append("Connection: Keep-Alive\n");
		else
			builder.append("Connection: Close\n");
		
   	   builder.append("Date: ").append(dataFormato.format(data.getTime() )).append("\n")
   	   
		   	   .append("Server: Apache/1.3.0 (Unix)\n");
   	   
		   	   data.add(data.DAY_OF_MONTH, -3); 
		   	   builder.append("Last-Modified: ").append(dataFormato.format(data.getTime() )).append("\n")
		   	   
		   	   .append("Content-Length: " + request.length() + "\n")
		   	   .append("Content-Type: text/html;charset=utf-8\n")
		   	   .append(mensagem).append("\n")
		   	   .append("\n");
		return builder.toString();
	}

}

