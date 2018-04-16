
import json
import sys

json_data='{"first_pos":2}'

parsed=json.loads(json_data)

first_col=parsed['first_pos']
x=[0 for i in range(9)]
x[1]=first_col

def n_queen(k):
    for i in range(1,9):
	if(place(k,i)):
	    x[k]=i
	    if(k==8):
	        print x
	        display()
	        exit()
	    else:
	        n_queen(k+1)
	        
def display():
    print"Final Solution:-\n"
    print"1\t2\t3\t4\t5\t6\t7\t8\n\n"
    
    for i in range(1,9):
	for j in range(1,9):
	    if(x[i]==j):
	        print "Q\t",
	    else:
	        print "-\t",
	
	print i,"\n\n\n"   

def place(k,i):
    
    for j in range(1,k):
	if(x[j]==i or (abs(x[j]-i)==abs(j-k))):
	    return False
	
	    
    return True
    
	
n_queen(2)

