#SERVER SIDE CODE

import socket               # Import socket module 
import hashlib 

mystring = raw_input('Enter String to hash: ') 
hex_dig=hashlib.sha1(mystring).hexdigest(); 

s = socket.socket()         # Create a socket object 
#host = socket.gethostname() # Get local machine name 
port = 12346                # Reserve a port for your service. 
s.bind(('192.168.105.190', port))        # Bind to the port 

s.listen(5)                 # Now wait for client connection. 
while True: 
   c, addr = s.accept()     # Establish connection with client. 
   print 'Got connection from', addr 
   c.send(mystring) 
   
   print 'Hash Value of message',hex_dig;	 

   c.close()                # Close the connection 


'''
SERVER OUTPUT : 

admin1@admin:~$ cd Desktop/ 
admin1@admin:~/Desktop$ python server.py 
Enter String to hash: abcd 
Got connection from ('192.168.60.10', 17881) 
Hash Value of message 81fe8bfe87576c3ecb22426f8e57847382917acf 

'''
