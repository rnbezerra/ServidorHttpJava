package Principal;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpPrincipal;


class MyHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
    	
        String response = "Esta e uma resposta. " + t.getProtocol() + " " + t.getPrincipal().getName();
       
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
        
//        File file = new File("imagem.jpg");
//
//        byte[] b = new byte[(int) file.length()];
//        FileInputStream fileInputStream = new FileInputStream(file);
//        fileInputStream.read(b);
//
//        t.getResponseHeaders().add("Content-Type", "image/jpeg");       
//        t.sendResponseHeaders(200, b.length);
//        OutputStream os = t.getResponseBody();
//        os.write(b);
//        os.flush();
//        os.close();

        t.close();
    }
}

public class Servidor extends Thread {
	
	@Override
	public void run() {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
		    server.createContext("/request", new MyHandler());
		    server.setExecutor(null); // creates a default executor
		    server.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

