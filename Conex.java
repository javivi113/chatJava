package xhat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Conex extends Thread{
	private Socket socketCli;
	private int cont;
	public Conex(Socket s, int cont) {
		socketCli = s;
		this.cont = cont;
	}
	@Override
	public void run() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketCli.getInputStream()));
			String linea;
			while ((linea = bufferedReader.readLine()) != null){
				String msg = "Cliente" +cont +": "+linea;
				//System.out.println(msg);
				Server.sockets.forEach(socket -> {
					if(socket!=socketCli) {
						try {
							BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
							bufferedWriter.write(msg);
							bufferedWriter.newLine();
							bufferedWriter.flush();
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}

				});
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
