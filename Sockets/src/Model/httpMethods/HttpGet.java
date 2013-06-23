package Model.httpMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class HttpGet {

	private boolean hasFile;
	private String fileName;

	public String getResponse(String request) {

		StringBuilder builder = new StringBuilder();
		request = request.replace("\n\n", "\n");
		DateFormat dataFormato = DateFormat.getDateInstance(DateFormat.FULL);
		Calendar data = Calendar.getInstance();
//		String mensagem = "Resposta do servidor: dados, dados, dados...";

		StringTokenizer token = new StringTokenizer(request);
		
		if (token.nextToken().toLowerCase().endsWith("get")) {
			
			String arquivo = token.nextToken();
			if(arquivo.startsWith("/")) arquivo = arquivo.substring(1);
			
			
			if(!new File(arquivo).exists()){				
				builder.append("HTTP/1.1 501 FILE NOT FOUND\n");
	
				if (request.toLowerCase().contains("keep-alive"))
					builder.append("Connection: Keep-Alive\n");
				else
					builder.append("Connection: Close\n");
	
				builder.append("Date: ").append(dataFormato.format(data.getTime()))
						.append("\n")
	
						.append("Server: Apache/1.3.0 (Unix)\n");
	
				data.add(data.DAY_OF_MONTH, -3);
	
				builder.append("Last-Modified: ")
						.append(dataFormato.format(data.getTime())).append("\n")
						.append("Content-Length: " + request.length() + "\n")
						.append("Content-Type: text/html;charset=utf-8\n")
						.append("\n").append("\n");
			}
			else{
				
				this.hasFile = true;
				this.fileName = arquivo;
				
				String contenttype = getFileType();
				
				
				builder.append("HTTP/1.1 200 OK\r\n");
				
				if (request.toLowerCase().contains("keep-alive"))
					builder.append("Connection: Keep-Alive\r\n");
				else
					builder.append("Connection: Close\r\n");
	
				builder.append("Date: ").append(dataFormato.format(data.getTime()))
						.append("\r\n")
	
						.append("Server: Apache/1.3.0 (Unix)\r\n");
	
				data.add(data.DAY_OF_MONTH, -3);
	
				builder.append("Last-Modified: ")
						.append(dataFormato.format(data.getTime())).append("\r\n")
						.append("Content-Length: " + new File(this.getFileName()).length()).append("\r\n")
						.append("Content-Type: ").append(contenttype).append("\r\n")
						.append("\r\n");
			}
		}
		return builder.toString();
	}
//
//	public String getImage(String path) {
//
//		File file = new File(path);
//
//		byte[] b = new byte[(int) file.length()];
//		FileInputStream fileInputStream;
//		try {
//			fileInputStream = new FileInputStream(file);
//			fileInputStream.read(b);
//			fileInputStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		StringBuffer image = new StringBuffer();
//		for (byte tempByte : b) {
//			image.append((char)tempByte);
//		}
//				
//		return image.toString();
//
//	}

	public boolean isThereAFile() {
		return this.hasFile;
	}

	public String getFileName() {
		return (!fileName.endsWith("/") ? fileName : fileName + "index.html");
	}

	private String getFileType() {
		String filename = this.fileName.toLowerCase();
		
		if(filename.endsWith(".jpg") || filename.endsWith(".jpeg")) filename = "image/jpeg";
		else if(filename.endsWith(".txt")) filename = "text/html; charset=utf-8";
		else if(filename.endsWith(".png")) filename = "image/png";
		else if(filename.endsWith(".css")) filename = "text/css";
		else if(filename.endsWith(".html") || filename.endsWith(".htm") || filename.endsWith("/")) filename = "text/html";
		
		return filename;
	}

}
