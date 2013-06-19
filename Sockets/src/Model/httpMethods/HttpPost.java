package Model.httpMethods;

import java.util.ArrayList;
import java.util.Arrays;

import Model.HttpMethodsInterface;
import Model.httpHelper.HttpHelper;
import Model.httpHelper.HttpRequestResponse;

public class HttpPost implements HttpMethodsInterface{

	@Override
	public String getResponse(HttpRequestResponse request) {
	
		System.out.println(request.getHeaders().getAllHeadersAsString());
		long time = System.currentTimeMillis();
        
		StringBuilder builder = new StringBuilder();
		
		builder.append("HTTP/1.1 200 OK").append("\n")//topo da mensagem
			   .append("\n")
			   .append("Mensagem do servidor: ").append(time).append("\n");
		return builder.toString();
	}
	
	@Override
	public HttpRequestResponse translateInput(String input) {
		
		HttpRequestResponse request = new HttpRequestResponse();
		ArrayList<String> linhas = new ArrayList<String>(Arrays.asList(input.split("\n")));
//		for (String l : input.split("\n")) {
//			linhas.add(l);
//		}
//		
		linhas.remove(0);
				
		for (String l : linhas) {
			if(l.indexOf(":") != -1){
				request.getHeaders().addHeader(l.replace(" ","").split(":")[0], l.replace(" ","").split(":")[1]);
			}
		}
				
		return request;
	}
	
}
