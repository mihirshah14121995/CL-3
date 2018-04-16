import random 
import math 
import hashlib 

p = int(raw_input("Enter value of p(must be a prime number) : ")) 
q=p-1 
k=0 
def test(): 
	global p,q,k 
	while(q%2==0): 
		k=k+1 
		q=q/2 
	a = random.randint(2,p-2) 
	print 'q = ',q 
	print 'k = ',k 
	print 'a = ',a 

	if((a**q) % p == 1): 
		return True 
	for j in range(0,k-1): 
		if( ( math.pow(a, (math.pow(2,j) * q) ) % p ) == 1):	 
			return True 
	return False 


def is_prime(number): 
    for i in (2, number): 
               if number%i == 0: 
                   return False 
               else: 
                   return True 


def modInverse(a, m): 
    a = a%m; 
    for x in range(1,m-1): 
       if ((a*x) % m == 1): 
          return x 

def gen_q(x,p): 
	q=p; 
	for i in range(x,p-1): 
		if (p-1)%i == 0 and is_prime(i) : 
			q=i 
			break 
	return q 

if not is_prime(p) and not test(): 
	print "p is not a prime number." 
	exit(0) 
	 
q = gen_q(2,p) 
k = random.randint(1,q-1) 
print "k : ",k 
print "q : ",q 
print "modInverse(k,q) : " , modInverse(k,q) 
while(modInverse(k,q) == None): 
	q = gen_q(2,q) 
	k = random.randint(1,q-1) 
	print "new q : ",q 

hex_dig = hashlib.sha1('Hello World').hexdigest() 

s=0; 
while(s==0): 
	r=0 
	while(r==0): 
		h = random.randint(2,p-2) 
		g = int(math.pow(h,(p-1)/q)%p) 
		x = random.randint(1,q-1) 
		y = int(math.pow(g,x)%p) 
		r = int(int(math.pow(g,k)%p)%q) 
		 
		s = int(modInverse(k,q)*(int(hex_dig,16) + x*r))% q 


print "k : ",k 

print "p : ",p,", q : ",q,", g : ",g 

print "private key(x) : ",x 

print "public key(y) : ",y 

print "hex_dig : ",hex_dig 

print "s = ",s 

#s = int(int(math.pow(k,-1))*(int(hex_dig,16) + x*r))% q 



print "Signature(r,s) :[",r," , ",s,"]" 

w= modInverse(s,q) 
u1=(int(hex_dig,16)*w) % q 
u2=(r*w) % q 
v=(((g**u1)*(y**u2)) % p) % q 
print "w : ",w,", u1 : ",u1,", u2 : ",u2,",v : ",v, 
if(v==r): 
	print "Signature Verified!!!" 
else: 
	print "Signature not Verified!!!" 


'''
	OUTPUT : 

admin1@admin:~/Desktop$ python dsa.py 
Enter value of p(must be a prime number) : 29 
k :  4 
q :  7 
modInverse(k,q) :  2 
k :  4 
p :  29 , q :  7 , g :  24 
private key(x) :  1 
public key(y) :  24 
hex_dig :  0a4d55a8d778e5022fab701977c5d840bbc486d0 
s =  2 
Signature(r,s) :[ 2  ,  2 ] 
w :  4 , u1 :  3 , u2 :  1 ,v :  2 Signature Verified!!! 
admin1@admin:~/Desktop$
'''


