import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread {

    ServerSocket server;
    Thread thread;
    static String SERVER_TYPE = "Default Server";

    public ServerThread() {
        this(0); // automatically allocates random port
    }
    public ServerThread(int port) {
        try {
            server = new ServerSocket(port, 50, InetAddress.getLoopbackAddress());

            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while ( getContinue() ) {
                        try (Socket socket = server.accept()) {
                            //Server-specific logic will be in this method, implementation left to subclasses.
                            onAccept(socket);
                        } catch (Exception e) {
                            System.out.println("[Thread Run, Server Accept] " + e);
                        }
                    }
                }
            });

            thread.start();

        } catch (Exception e) {
            System.out.println("[ServerThread Constructor] " + e);
        }
    }

    public int getPort() {
        return server.getLocalPort();
    }

    public InetAddress getAddress() {
        return server.getInetAddress();
    }

    public void onAccept(Socket socket) {
        //System.out.println();

        //Only one will work in the same instance
        
        sendServerInfo(socket);
        //echoToConsole(socket);
    }

    public void sendServerInfo(Socket socket) {
        try (OutputStream ouput = socket.getOutputStream()) {
            String message = SERVER_TYPE + " listening on tcp/" + this.getPort();
            ouput.write((message).getBytes());
            System.out.println("[S] " + message);
        } catch (Exception e) {
            System.out.println("[onAccept, send] " + e);
        }
    }

    public void echoToConsole(Socket socket) {
        try (Scanner input = new Scanner(socket.getInputStream())) {
            int iter=0;
            if (input.hasNextLine()) {
                System.out.println("[S] " + input.nextLine());
                iter++;
            }
            System.out.println("echo iterations: " + iter);

        } catch (Exception e) {
            System.out.println("[onAccept, echo] " + e);
        }
        finally {
            System.out.println("Leaving echo");
        }
    }
    public void onClose() {
        thread.interrupt();
    }
    public boolean getContinue() {
        return ! thread.isInterrupted();
    }
}
