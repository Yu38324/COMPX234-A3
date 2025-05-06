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
    private String fR(String op, String key, String value)
     {
        
        String request = String.format("%03d %s (%s, %s)", 6 + key.length() + value.length(), op, key, value);

        return request;
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


                //split the request into operation and key
                String[] parts = request.split(" ");
                String operation = parts[0];
                String key = parts[1];
                //check if has value or not
                String value = parts.length >  3? parts[2] : null;
                //check the length of the key which can't be more than 970


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
                String response = reader.readLine();
                

            }

            
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
