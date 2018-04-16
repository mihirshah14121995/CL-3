
#CLIENT SIDE CODE

#!/usr/bin/python           # This is client.py file 

import socket               # Import socket module 
import hashlib 

s = socket.socket()         # Create a socket object 
#host = socket.gethostname() # Get local machine name 
port = 12346                # Reserve a port for your service. 

s.connect(('192.168.105.190', port)) 
a=s.recv(1024) 
#a=s.recv(1024) 
print 'The received message is:',a 
print 'Hash Value of message',hashlib.sha1(a).hexdigest() 

s.close                     # Close the socket when done 

''' 	
CLIENT OUTPUT :

admin1@admin:~/Desktop$ python client.py 
The received message is: abcd 
Hash Value of message 81fe8bfe87576c3ecb22426f8e57847382917acf 
admin1@admin:~/Desktop$ 
'''

