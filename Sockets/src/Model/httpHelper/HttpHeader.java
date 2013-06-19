package Model.httpHelper;

import java.util.HashMap;

public class HttpHeader {

	private HashMap<String, String> headers;

	public HttpHeader() {
		headers = new HashMap<String, String>();
	}
	
//	public HashMap<String, String> getHeaders() {
//		return headers;
//	}
	/**
	 * Este metodo retorna todos os headers que foram cadastrados devidamente formatados em um string
	 * @return 
	 */
	public String getAllHeadersAsString(){
		StringBuilder builder =new StringBuilder();
		for (String header : this.headers.values()) {
			builder.append(header)
				   .append("\n");
		}
		return builder.toString();
	}

	/**
	 * Este metodo adiciona um novo header e seu valor à lista de headers 
	 * @param header - nome do header. Ex.: "Content-Type"
	 * @param value - valor do header. Ex.: "text/json"
	 */
	public void addHeader(String header, String value){
		this.headers.put(header, header + ": " + value);
	}
	
	/**
	 * Remove todos os headers inseridos
	 */
	public void resetHeader(){
		this.headers.clear();
	}
			

	
	
}
