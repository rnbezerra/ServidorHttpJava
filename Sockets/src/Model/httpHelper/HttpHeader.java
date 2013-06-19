package Model.httpHelper;

import java.util.HashMap;

public class HttpHeader {

	private HashMap<String, String> headers;

//	public HashMap<String, String> getHeaders() {
//		return headers;
//	}
	
	public String getAllHeadersAsString(){
		StringBuilder builder =new StringBuilder();
		for (String header : this.headers.values()) {
			builder.append(header)
				   .append("\n");
		}
		return builder.toString();
	}
	
	public void addHeader(String header, String value){
		this.headers.put(header, header + ": " + value);
	}
	
	public void resetHeader(){
		this.headers.clear();
	}
			

	
	
}
