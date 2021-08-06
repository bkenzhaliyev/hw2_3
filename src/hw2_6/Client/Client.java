package hw2_6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private final int PORT = 8189;
    private final String IP_ADDRESS = "localhost";
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("Введите сообщение...");
                        String str = sc.nextLine();

                        if (str.equals("/end")) {
                            System.out.println("Client disconnected");
                            break;
                        }
                            out.writeUTF(str);
   //                        System.out.println("Client: " + sMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


            new Thread(() ->{
                while (true) {
                    String sMsg = null;
                    try {
                        sMsg = in.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Server msg: " + sMsg);
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
