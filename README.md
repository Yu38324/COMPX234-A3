# COMPX234-A3
 Assignment3 for System and Network  

--- 
`4.27`

What is the port?  
protocal?  
HTTP => port: 80  
For the assignment, maybe the protocal we create for READ,PUT,GET?  
They use different ports?  
If Y, 
do start_server will have more ports?  
  
`Every new client come in, we create a new serve thread`    
  

---
`4.28`  

Application Layer -> HTTP,SMTP (port?)   
Transport Layer -> TCP,UDP
  
---
`4.29`    
  
No, different operations via same port. One server usually listen on one port.  
We need to use synchronized to make sure the operation is thread safe.
  
---
`5.6`  
Error handling client.  
Maybe because it can't find the key, so it will throw an exception. 
Error: value %d -> %s  
and add some e.message to the error.  
I have searched how to run clients in the same time online. And created run_clients.bat  
Add counters to code.  

---
`5.7`

Add limit to client.
I tested the code, but it the numTuple hasn't been changed.




