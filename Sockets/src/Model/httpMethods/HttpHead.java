package Model.httpMethods;

import java.text.DateFormat;
import java.util.Calendar;

public class HttpHead {

	
	public String getResponse(String request){
		
		StringBuilder builder = new StringBuilder();
		
		request = request.replace("\n\n", "\n");
		DateFormat dataFormato = DateFormat.getDateInstance(DateFormat.FULL);//new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss");
		Calendar data = Calendar.getInstance();

		builder.append("HTTP/1.1 200 OK\n");
		
		if(request.toLowerCase().contains("keep-alive"))
			builder.append("Connection: Keep-Alive\n");
		else
			builder.append("Connection: Close\n");
		
   	   builder.append("Date: ").append(dataFormato.format(data.getTime() )).append("\n")
		   	   .append("Server: Apache/1.3.0 (Unix)\n");
   	   
		   	   data.add(data.DAY_OF_MONTH, -3); 
		   	   builder.append("Last-Modified: ").append(dataFormato.format(data.getTime() )).append("\n")
		   	   
		   	   .append("Content-Length: " + request.length() + "\n")
		   	   .append("Content-Type: text/html;charset=utf-8\n")
		   	   .append("\n");
		
//		
//		
//		request = request.replace("\n\n", "\n");
//
//		builder.append("HTTP/1.1 200").append("\n")
//	   	   .append("Connection: Keep-Alive\n")
//	   	   .append("Date: Thu, 06 Aug 1998 12:00:15 GMT\n")
//	   	   .append("Server: Apache/1.3.0 (Unix)\n")
//	   	   .append("Last-Modified: Mon, 22 Jun 1998\n")
//	   	   .append("Content-Length: " + request.length() + "\n")
//	   	   .append("Content-Type: text/html;charset=utf-8\n")
//	   	   .append("\n");
		return builder.toString();
		
		
	}
	
}
