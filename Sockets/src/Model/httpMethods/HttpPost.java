package Model.httpMethods;

import Model.HttpMethodsInterface;
import Model.httpHelper.HttpHelper;
import Model.httpHelper.HttpRequestResponse;

public class HttpPost implements HttpMethodsInterface{

	@Override
	public String getResponse(HttpRequestResponse request) {
	
		long time = System.currentTimeMillis();
        
		StringBuilder builder = new StringBuilder();
		
		builder.append("HTTP/1.1 200 OK").append("\n")//topo da mensagem
			   .append("\n")
			   .append("Mensagem do servidor: ").append(time).append("\n");
		return builder.toString();
	}
	
	@Override
	public HttpRequestResponse translateInput(String input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
