package Model;

import java.net.*;
import java.io.*;

public class ClienteHead{

	Socket ClienteHead;
	ObjectOutputStream out;
	ObjectInputStream in;
	String Mensagem;
	
	ClienteHead(){
	}
	
	 public void run()
	    {
	        try{
	            //1. creating a socket to connect to the server
	            ClienteHead = new Socket("localhost", 8080);
	            System.out.println("Connected to localhost in port 2004");
	            //2. get Input and Output streams
	            out = new ObjectOutputStream(ClienteHead.getOutputStream());
	            out.flush();
	            in = new ObjectInputStream(ClienteHead.getInputStream());
	            //3: Communicating with the server
	            do{
	                try{
	                    Mensagem = (String)in.readObject();
	                    System.out.println("server>" + Mensagem);
	                    sendMessage("HEAD /somedir/page.html HTTP/1.1");
	                    Mensagem = "bye";
	                    sendMessage(Mensagem);
	                }
	                catch(ClassNotFoundException classNot){
	                    System.err.println("data received in unknown format");
	                }
	            }while(!Mensagem.equals("bye"));
	        }
	        catch(UnknownHostException unknownHost){
	            System.err.println("You are trying to connect to an unknown host!");
	        }
	        catch(IOException ioException){
	            ioException.printStackTrace();
	        }
	        finally{
	            //4: Closing connection
	            try{
	                in.close();
	                out.close();
	                ClienteHead.close();
	            }
	            catch(IOException ioException){
	                ioException.printStackTrace();
	            }
	        }
	    }
	    void sendMessage(String msg)
	    {
	        try{
	            out.writeObject(msg);
	            out.flush();
	            System.out.println("client>" + msg);
	        }
	        catch(IOException ioException){
	            ioException.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) {

	ClienteHead clienteHead = new ClienteHead();
	clienteHead.run();
		
	}

}
