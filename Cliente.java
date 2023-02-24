package xhat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
public class Cliente {
	static Socket socket;
	public static void main(String[] args) {
        try {
			socket = new Socket("localhost", 5557);
			Thread escritor= new Thread( new EscribirChat(socket));
			escritor.start();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String linea;
	        while ((linea = reader.readLine()) != null){
	            System.out.println(linea);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
