import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServerThread st = new ServerThread();
        //System.out.println("ServerThread listening on tcp/" + st.getPort());

        Client c = new Client(st.getAddress(), st.getPort());

        System.out.println("[C] " + c.readFromServer());
        //c.sendToServer("test");

        //System.out.println("[C] " + c.readFromServer());

        Scanner in = new Scanner(System.in);
        in.nextLine();

        System.out.println("Goodbye");

        c.onClose();
        st.onClose();
    }
}