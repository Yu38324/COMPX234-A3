import java.io.*;
import java.net.*;

public class serve {
    //get serve's IP address and host name
    // void socket(int port) {
    //     try{
    //         //socket?
    //         InetAddress s = InetAddress.getByName("localhost"); 
    //         System.out.println("InetAddress: " + s.getHostAddress());
    //         System.out.println("InetAddress: " + s.getHostName()); 
    //     } catch(UnknownHostException e) {
    //         System.out.println("UnknownHostException: " + e.getMessage());
    //     }
        
    // }
    //get socket

    class ClientHandler implements Runnable {
        private Socket cs;

        //get the client's socket
        public ClientHandler(Socket socket) {
            this.cs = socket;
        }
        public void run(){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
                //get what client say
                String clientMessage;
                while((clientMessage = in.readLine()) != null) {
                    System.out.println("Client: " + clientMessage);
                    //respond
                    out.println("Server: " + clientMessage);
                    if(clientMessage.equals("exit")) {
                        System.out.println("Client disconnected.");
                        break;
                    }
                }
                //close connection
                in.close();
                out.close();
                cs.close();

                
                
    
            } catch (IOException e) {
                System.err.println("Error handling client.");
            }
            
    

        }
    }
    
        
        void start_serve(int port) {
           
           String host = "localhost";
           //int port = 51000;
           
          try{
            //listen to port 
            ServerSocket ss = new ServerSocket(port);
            //need bind or not?
            
            while(true){
                //Socket s = ss.accept();
                //create threads
                Socket clientSocket = ss.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                

            }
            } catch(Exception e) {
                System.out.println("UnknownHostException: " + e.getMessage());
            }
       }
       void main(String[] args) {
            int port = 51000;
            start_serve(port);
        }

}
