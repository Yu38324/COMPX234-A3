import java.io.*;
import java.net.*;

public class client {
    //get client's IP address and host name
    // void socket(int port) {
    //     try{
    //         //socket?
    //         //port?
    //         InetAddress c = InetAddress.getByName("localhost"); 
    //         System.out.println("InetAddress: " + c.getHostAddress()+ ":" + port);
    //         System.out.println("InetAddress: " + c.getHostName()); 
    //     } catch(UnknownHostException e) {
    //         System.out.println("UnknownHostException: " + e.getMessage());
    //     }



    // class ClientTask implements Runnable{
    //     public void run(){
    //         try {
    //             //get socket

    //             //connect to server

    //             //message

    //         } catch (Exception e) {
    //             System.err.println("Error .");
    //         }
    //     }

    // }

    private String host;
    private int port;
    private String fileName;

    public client(String host, int port, String fileName) {
        this.host = host;
        this.port = port;
        this.fileName = fileName;
    }
    private String fR(String op, String key, String value) {
        int length = 6 + key.length() + (value != null ? value.length() : 0);
        return String.format("%03d %s %s%s", length, op, key, value != null ? " " + value : "");
    }
    public void client_Request(){
        try {
            Socket socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String request;

            while ((request = fileReader.readLine()) != null) {
                //check the format
                if (request.length() < 7) {
                    System.out.println(request+":ERR Invalid message format");
                    continue;
                }


                //split the request into operation and key
                String[] parts = request.split(" ");
                String operation = parts[0];
                String key = parts[1];
                //check if has value or not
                String value = parts.length >  3? parts[2] : null;
                //check the length of the key which can't be more than 970
                if (key.length() > 970) {
                    System.out.println(request+":ERR Key is too long");
                    continue;
                }


                //creat the message to send to the server
                String message ;
                switch(operation) {
                    case"READ":
                        message = fR("R", key, value);
                        break;
                    case"GET":
                        message = fR("G", key, value);
                        break;
                    case"PUT":
                        message = fR("P", key, value);
                        break;
                    default:
                        System.out.println(request+":ERR Unknown operation");
                        continue;
            
                }
                writer.println(message);
                System.out.println(message);
                String response = reader.readLine();
                

            }

            fileReader.close();
            reader.close();
            writer.close();
            socket.close();
            
        } catch (Exception e) {
        }   
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java client <host> <port> <file>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String fileName = args[2];

        client c = new client(host, port, fileName);
        c.client_Request();
    }

    // public static void main(String[] args) {
    //     InetAddress c;
    //     try {
    //         c = InetAddress.getByName("localhost");
    //     } catch (UnknownHostException e) {
    //         System.out.println("UnknownHostException: " + e.getMessage());
    //         return;
    //     }
    //     Socket socket;
    //     try {
    //         // int port = 51000;
    //         socket = new Socket(c, 51000);
    //         // socket = new Socket(c,port);
    //         PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    //         writer.println(args[0]);
    //         BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    //         String line = reader.readLine();
    //         System.out.println(line);
    //         socket.close();




    //     }catch (IOException e) {
    //         System.err.println("IOException: " + e.getMessage());
    //         return;
    //     }

    // }

  
}
