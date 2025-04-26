import java.net.InetAddress;
import java.net.UnknownHostException;

public class client {
    //get client's IP address and host name
    void socket() {
        try{
            InetAddress c = InetAddress.getByName("localhost"); 
            System.out.println("InetAddress: " + c.getHostAddress());
            System.out.println("InetAddress: " + c.getHostName()); 
        } catch(UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
        }
        
    }
    void connect() {

    }
    String send() {
        return "Send";
    }
    void recv() {

    }
    void close() {
        
    }
    
}
