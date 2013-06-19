package Principal;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.ws.spi.http.HttpExchange;

public class Cliente extends Thread{
	
	@Override
	public void run(){
		testeHttp();
		/*
		final int PORTA = 80;
		String host;

		Scanner entrada = new Scanner(System.in);
		System.out.print("\nCLIENTE-INFO> Entre o endereco do servidor: ");
		host = entrada.next();
		try {
			InetAddress address = InetAddress.getByName(host);
		}
		catch(UnknownHostException uhEx) {
			System.out.println("CLIENTE-INFO> Erro: host nao encontrado!");
			System.exit(1);
		}
		Socket link = null;
		try {
			System.out.println("\nCLIENTE-INFO> Abrindo conexao com o servidor...");
			link = new Socket(host, PORTA);
			// Define entrada no cliente
			Scanner input = new Scanner(link.getInputStream());
			// Define saida para o servidor
			PrintWriter output = new PrintWriter(link.getOutputStream(),true);
			Scanner userEnt = new Scanner(System.in);
			String mensagem, resposta;
			System.out.print("CLIENTE-INFO> Entre a mensagem ou digite FIM para terminar: ");
			mensagem = userEnt.nextLine();
			while (true) {
				if(!mensagem.toUpperCase().equals("FIM"))output.println(mensagem);
				else {
					output.println(mensagem);
					break;
				}
				resposta = input.nextLine();
				System.out.println("\nSERVIDOR> " + resposta);
				System.out.print("CLIENTE-INFO> Entre a mensagem ou digite FIM para terminar: ");
				mensagem = userEnt.nextLine();
			}
			System.out.println("\nCLIENT-INFO> Fechando conexao...");
			link.close();
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		} 
		finally {
			try {
				
			}
			catch (IOException ioEx) {
				System.out.println("Erro: nao foi possivel a desconexao!");
				System.exit(1);
			}
		} */
	}

	private void testeHttp() {
		try {

			String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
		    String host = "localhost";
		    Socket socket = new Socket(InetAddress.getByName(host), 80);
		    
		    String path = "/request";
//		    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		    OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF8");
		    out.write("POST " + path + " HTTP/1.1\r\n");
		    out.write("Host: " + host + "\r\n");
//		    wr.write("Content-Length: " + data.length() + "\r\n");
		    out.write("Content-Type: text/html\r\n");
		    out.write("Conection: close\r\n");
//		    String json = "{\"nome\":\"Rafael\",\"idade\":18,\"sexo\":\"masculino\",\"jogos\":[\"Skyrim\",\"Bioshock\",\"Portal\"]}\r\n";
//		    out.write(json);
		    //HttpPost httpost = new HttpPost("http://test.localhost");
		    
		    /*
		    wr.write("{\r\n");
		    wr.write("\"nome\":\"Rafael\",\r\n");
		    wr.write("\"idade\": 18,");
			wr.write("\"sexo\": \"masculino\",");
			wr.write("\"jogos\":[");
			wr.write("\"Skyrim\",");
			wr.write("\"Bioshock\",");
			wr.write("\"Portal\"");
			wr.write("]");
			wr.write("}");*/
		    out.write("\r\n");
		    out.flush();

		    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		      System.out.println(line);
		    }
		    out.close();
		    rd.close();	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
	}
	
}
