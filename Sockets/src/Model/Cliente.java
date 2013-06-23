package Model;

import java.io.IOException;

import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class Cliente extends Thread
{

	/**
	 * @param args
	 */
	
	private Socket conexao;
	private static boolean conexaoStatus;
	public Cliente(Socket conexao)
	{	
		this.conexao = conexao;
	}
	
	public static void main(String[] args)
	{
		
		try
		{
			System.out.println("Cliente > O cliente se conectou ao servidor com sucesso");
			Socket conexao = new Socket( "localhost" , 80);
			conexao.setKeepAlive(true);
			conexaoStatus = conexao.getKeepAlive();
			Thread c = new Cliente(conexao);
		
			c.start();
			
			
			
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		
		httpRequisitionSend(conexao);
		httpResponseView(conexao);
		
		try
		{
			conexao.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void httpRequisitionSend( Socket cliente )
	{
		//		 *****************MENSAGEM DE REQUISIÇÃO HTTP***************************
		int opcao = 2;
		PrintStream saida = null;
		try
		{
			saida = new PrintStream( cliente.getOutputStream() );
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		switch(opcao){
		
		case 1:
			saida.println	("POST /teste HTTP/1.1");
			break;
		case 2:
			saida.println	("GET /teste HTTP/1.1");
			break;
		case 3:
			saida.println	("HEAD /teste HTTP/1.1");
			break;
		default:
			saida.println	("Opcao inexistente");
			return;
		}
		saida.println	("Host: "+ cliente.getLocalAddress() );
		saida.println 	("User-agent: Mozilla");
		if(conexaoStatus == true)
			saida.println	("Connection: Keep-Alive");
		else 
			saida.println ("Connection: close");
		saida.println 	("Accept-language: pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
		saida.println		("\n\n");
		saida.flush();
		
	}
	
	private void httpResponseView( Socket cliente)
	{
		
		//		***************AGUARDA MENSAGEM DE RESPOSTA HTTP************************
		// so pra conseguir commitar
		
		Scanner entrada = null;
		try
		{
			entrada = new Scanner( cliente.getInputStream() );
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		while( entrada.hasNextLine() )
		{
			System.out.println( entrada.nextLine() );
		}
		
		entrada.close();
		
	}

}
