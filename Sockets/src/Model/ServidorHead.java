package Model;

import java.net.*;
import java.io.*;

public class ServidorHead{

	ServerSocket ServidorHead;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String Mensagem;
	String[] SplitMensagem;
	
	ServidorHead() {
	}
	
	public void run() {
		
		try{
            //1. creating a server socket
            ServidorHead = new ServerSocket(8080, 0);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = ServidorHead.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            //4. The two parts communicate via the input and output streams
            do{
                try{
                	  Mensagem = (String)in.readObject();
                	  SplitMensagem = Mensagem.split(" ");
                	  
                      System.out.println("client>" + SplitMensagem[0]);
                      if (Mensagem .equals("bye"))
                          sendMessage("bye");
                	
                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }
            }while(!Mensagem.equals("bye"));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                ServidorHead.close();
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
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServidorHead servidorHead = new ServidorHead();
		while(true){
			
			servidorHead.run();
			
			
		}
		
	}

}
