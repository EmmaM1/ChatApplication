import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(1400);
      System.out.println("Waiting for client...");
      Socket server = serverSocket.accept();
      System.out.println("Connected to " + server.getRemoteSocketAddress());
      DataInputStream input = new DataInputStream(server.getInputStream());
      String user = input.readUTF();
      System.out.println(user + " has joined");
      DataOutputStream output = new DataOutputStream(server.getOutputStream());
      output.writeUTF("Connected to " + server.getLocalSocketAddress());
      while (true) {
        try {
          System.out.println(input.readUTF());
        } catch (IOException e) {
          System.out.println(user + " has disconnected");
          serverSocket.close();
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
