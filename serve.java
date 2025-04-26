import java.net.InetAddress;
import java.net.UnknownHostException;

public class serve {
    //get serve's IP address and host name
    void socket() {
        try{
            InetAddress s = InetAddress.getByName("localhost"); 
            System.out.println("InetAddress: " + s.getHostAddress());
            System.out.println("InetAddress: " + s.getHostName()); 
        } catch(UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
        }
        
    }
     void bind() {
        
    }
    void listen() {
        
    }
    void accept() {
        
    }
    void recv() {
        
        
    }
    String send() {
        
        return "OK";
    }
    void close() {
        
    }
}
