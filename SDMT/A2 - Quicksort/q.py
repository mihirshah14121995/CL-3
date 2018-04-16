
import xml.etree.ElementTree
from threading import Thread
import threading

threadLock=threading.RLock()
threadList=[]

def partition(low,high):
    pivot=data[low]
    i=low-1
    j=high+1
    
    while(True):
        
        i=i+1
        while not data[i]['acc_no']>pivot['acc_no']:
            
            k=i+1
            if(k<=len(data)-1):
                i=i+1
            else:
                break
        
        j=j-1
        while not data[j]['acc_no']<pivot['acc_no']:
            k=j-1
            if(k>=0):
                j=j-1
            else:
                break
        
        if(i>=j):
            data[low],data[j]=data[j],data[low]
            return j
        
        data[i],data[j]=data[j],data[i]
        
def Quicksort(low,high):
    if(low<high):
        
        threadLock.acquire()
        p=partition(low,high)
        threadLock.release()
        
        t1=Thread(target=Quicksort,args=(low,p-1))
        t2=Thread(target=Quicksort,args=(p+1,high))
        t1.start()
        t2.start()
        t1.join()
        t2.join()
        
d=xml.etree.ElementTree.parse('bank.xml').getroot()

data=[]
for accounts in d:
    dict1={}
    for acc in accounts:
        dict1[acc.tag]=acc.text
    
    data.append(dict1)
    
Quicksort(0,len(data)-1)


for i in data:
    for key in i:
        print key,": ",i[key]
        
    print
    

'''
Output

admin1@admin1:~$ python q.py
ph_no :  5473489956
ifsc :  AxB1004
acc_no :  13097
Name :  Mukesh Ambani

ph_no :  6437245780
ifsc :  AxB1002
acc_no :  13098
Name :  Narayan Murthy

ph_no :  5347856348
ifsc :  AxB1009
acc_no :  14580
Name :  Ratan Tata

ph_no :  3682953278
ifsc :  AxB1008
acc_no :  17876
Name :  Sunder Pichai

ph_no :  6437295678
ifsc :  AxB1000
acc_no :  19345
Name :  Bill Gates

'''



    
    

