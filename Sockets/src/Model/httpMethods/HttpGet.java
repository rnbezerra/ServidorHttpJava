package Model.httpMethods;

public class HttpGet
{
	
	public String getResponse(String request) {
		
		//long time = System.currentTimeMillis();
	    

		StringBuilder builder = new StringBuilder();
		request = request.replace("\n\n", "\n");
		
		
			System.out.println("FOI AQUI");
		
		builder.append("HTTP/1.1 200").append("\n");
		if(request.contains("Keep-Alive"))
			builder.append("Connection: Keep-Alive\n");
		else
			builder.append("Connection: Close\n");
		
   	   builder.append("Date: Thu, 06 Aug 1998 12:00:15 GMT\n")
		   	   .append("Server: Apache/1.3.0 (Unix)\n")
		   	   .append("Last-Modified: Mon, 22 Jun 1998\n")
		   	   .append("Content-Length: " + request.length() + "\n")
		   	   .append("Content-Type: text/html;charset=utf-8\n")
		   	   .append("\n");
		return builder.toString();
	}

}

