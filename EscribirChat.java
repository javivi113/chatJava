package xhat;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class EscribirChat implements Runnable{
	Socket socket;
	public EscribirChat(Socket s) {
		socket=s;
	}
	@Override
    public void run() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                OutputStream outputStream = socket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                if (!message.equals("\n")||!message.trim().equals("")) {
                	writer.write(message);
                    writer.newLine();
                    writer.flush();
				}                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
	
}
