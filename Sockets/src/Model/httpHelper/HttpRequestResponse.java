package Model.httpHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HttpRequestResponse {

	private HttpHeader headers;
	private byte[] body;
	
	public HttpRequestResponse() {
		this.headers = new HttpHeader();
	}
	
	public HttpHeader getHeaders() {
		return headers;
	}


	public void setHeaders(HttpHeader headers) {
		this.headers = headers;
	}


	public byte[] getBody() {
		return body;
	}


	public void setBody(byte[] body) {
		this.body = body;
	}

	public void setBody(String body) {
		this.body = body.getBytes();
	}
	
	public void setBody(File file){
		
        byte[] b = new byte[(int) file.length()];
        FileInputStream fileInputStream;
		try {
			
			fileInputStream = new FileInputStream(file);
	        fileInputStream.read(b);
	        this.body = b;
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
