import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class server {
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

    
        //create a tuple space to store tuples
        ConcurrentHashMap<String, String> tupleSpace = new ConcurrentHashMap<>();


        // format responds
        private String fR(String state, String key, String value, String operation){
            //NNN : 7+ lenth of key + length of value + length of operation
            String respd = String.format("%03d %s (%s, %d) %s", 6+ key.length() + 
            value.length(), operation.length(), state, key, value, operation);
            return respd;
        }
        //format error responds
        private String fE(String key, String error){
            String respd = String.format("%03d %s (%s, %s)", 6+ key.length() + error.length(), key, error);
            return respd;

        }

        //READ
       private synchronized String read(String key){
        //: if a tuple with key k exists, the tuple (k, v) is read and the value v is 
        //returned; if k does not exist, the operation fails and v returns empty; 

        String value = tupleSpace.get(key);
        if(value != null) {
            return fR("OK", key, value, "read");
        }else{
            return fE(key,"dose not exist");
        }
       }
       
        //GET
        private synchronized String get(String key){
            //: if a tuple with key k exists, the tuple (k, v) is deleted and the value v is 
            //returned; if k does not exist, the operation fails and v returns empty;

            String value = tupleSpace.remove(key);
            if(value != null) {
                return fR("OK", key, value, "get");
            }else{
                return fE(key,"dose not exist");
            }
            
        }

        //PUT
        private synchronized String put(String key, String value){
            //: if the key k does not exist already, the tuple (k, v) is added to the tuple 
            //space and e returns 0; if a tuple with key k already exists, the operation fails and e 
            //equals 1 is returned. 

            String oldValue = tupleSpace.putIfAbsent(key, value);
            if(oldValue == null) {
                return fR("OK", key, value, "put");
            }else{
                return fE(key,"already exist");
            }
        }
        
    
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
                    //out.println("Server: " + clientMessage);
                    String command = clientMessage.substring(4,5);
                    String key = clientMessage.substring(5).split(" ")[0];
                    String respd;
                    
                    switch(command) {
                        case "R":
                            respd = read(key);
                            break;
                        case "G":
                            respd = get(key);
                            break;
                        case "P":
                        String value = clientMessage.substring(6 + key.length()).trim();
                        respd = put(key, value);
                            break;
                        default:
                            respd = "ERR Unknown command";
                    }
                    out.println(respd);


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
    
        
        void start_serve() {
           
           String host = "localhost";
           int port = 51000;
           
          try{
            //listen to port 
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server started on port " + port);
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

            start_serve();
        }

}
