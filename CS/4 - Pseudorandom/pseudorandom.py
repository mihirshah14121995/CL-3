import time

epoch = time.time()

print "Value of time =",
print epoch

#values used to randomize

num = 1
t = int(epoch)
a = t+t/2+t/4		#randomize here as much as you want

choice = int(raw_input("How many random numbers do you want? "))
for i in range (0,choice):
	num = (t*num)%a
	print num

