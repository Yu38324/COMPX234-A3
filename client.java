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
    void main() {
        InetAddress c;
        try {
            c = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
            return;
        }
        Socket socket;
        try {
            // int port = 51000;

            // socket = new Socket(c,port);
            //




        }catch (Exception e) {
            System.out.println("SocketException: " + e.getMessage());
            return;
        }

    }

  
}
