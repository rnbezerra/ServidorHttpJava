package Model;

import Model.httpHelper.HttpHelper;
import Model.httpHelper.HttpRequestResponse;

public interface HttpMethodsInterface {
	
	public String getResponse(HttpRequestResponse request);
	public HttpRequestResponse translateInput(String input);
}
