
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
       void start_serve(int port) {
           
           String host = "localhost";
           //int port = 51000;
           
          try{
            //listen to port 
            ServerSocket ss = new ServerSocket(port);
            //need bind or not?
            
            while(true){
                Socket s = ss.accept();


            }
            } catch(Exception e) {
                System.out.println("UnknownHostException: " + e.getMessage());
            }
       }


}
