def Binary_search(low,high,key):
    if(low<=high):
        mid=(low+high)/2
        
        if(key==data[mid][0]):
            return mid
            
        elif(key>data[mid][0]):
            return Binary_search(mid+1,high,key)
        
        else:
            return Binary_search(low,mid-1 , key)
            
    else:
        return -1    

data=[]

f=open("bank.txt","r")
line=""

line=f.readline()

while(line):
    temp=[]
    temp=line.split(",")
    data.append(temp)
    line=f.readline()
    
data.sort()


key=raw_input("Enter the account no to search: ")
ind=0
ind=Binary_search(0,len(data)-1, key)

if(ind==-1):
    print "Account not found"

else:
    print "Account found:-"
    print "Account Number: ",data[ind][0]
    print "Name: ",data[ind][1]
    print "IFSC: ",data[ind][2]
    print "Moblie no: ",data[ind][3].translate(None,'\n')
  
    
'''
OUTPUT
         
admin1@admin1-ubuntu:~$ python bs.py
Enter the account no to search13098
Account found:-
Account Number:  13098
Name:  Mukesh Ambani
IFSC:  AxB1038
Moblie no:  6758857382
'''   
    
    
    











