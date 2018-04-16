#/usr/bin/python
from string import ascii_lowercase, ascii_uppercase

""" function to encrypt the plaintext """
def encrypt(string, displacement):
	result = ""
	for character in string:
		if character in ascii_lowercase:
			result += chr((ord(character) - ord('a') + displacement) % 26 + ord('a'))
		elif character in ascii_uppercase:
			result += chr((ord(character) - ord('A') + displacement) % 26 + ord('A'))
		else:
			result += character
	return result
	
""" function to decrypt the plaintext """
def encrypt(string, displacement):
	result = ""
	for character in string:
		if character in ascii_lowercase:
			result += chr((ord(character) - ord('a') - displacement) % 26 + ord('a'))
		elif character in ascii_uppercase:
			result += chr((ord(character) - ord('A') - displacement) % 26 + ord('A'))
		else:
			result += character
	return result

print "1. Encrypt"
print "2. Decrypt"
print "Enter your choice"
c = int(input())
m = str(raw_input('Enter message '))
k = int(raw_input('Enter key '))
if c == 1:
	print encrypt(m, k)
elif c == 2:
	print decrypt(m, k)
else:
	exit()
