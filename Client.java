import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;

    public Client(InetAddress address, int port) {
        try {
            this.socket = new Socket(address, port);
        } catch (Exception e) {
            System.out.println("[Client Constructor] " + e);
        }
    }

    public String readFromServer() {
        try (Scanner input = new Scanner(socket.getInputStream())) {
            while (input.hasNextLine())
                return input.nextLine();
        } catch (Exception e) {
            System.out.println("[Client read] " + e);
        }

        return "";
    }

    public void sendToServer(String message) {
        try (OutputStream output = socket.getOutputStream()) {
            output.write(message.getBytes());
        } catch (Exception e) {
            System.out.println("[Client send] " + e);
        }
    }

    public void onClose() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("[Client close] " + e);
        }
    }

}
