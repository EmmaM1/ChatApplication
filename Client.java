import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  public static void main(String[] args) {
    try {
      System.out.println("Connecting to localhost on port 1400...");
      Socket clientSocket = new Socket("localhost", 1400);
      System.out.print("Enter your username: ");
      Scanner userInput = new Scanner(System.in);
      String user = userInput.next();
      OutputStream outputStream = clientSocket.getOutputStream();
      DataOutputStream output = new DataOutputStream(outputStream);
      output.writeUTF(user);
      InputStream inputStream = clientSocket.getInputStream();
      DataInputStream input = new DataInputStream(inputStream);
      System.out.println(input.readUTF());
      System.out.println("Type 'disconnect' to disconnect from the server");
      String message;
      userInput.nextLine();
      while(true) {
        message = userInput.nextLine();
        if (message.equals("disconnect")) {
          break;
        }
        output.writeUTF(user + ": " + message);
      }
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
