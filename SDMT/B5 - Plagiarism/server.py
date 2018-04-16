from flask import Flask , request, render_template
app = Flask(__name__)

@app.route('/')
def mainpage():
    return render_template('index.html',msg=None)
    
@app.route('/eval',methods=['POST','GET'])
def check():
	mystr = str(request.form['string'])
	a = checker(mystr)
	print a
	return render_template('index.html',msg=a,string1=mystr)
	
def process_string(string):
	#you can add delimiters here using replace(old, new) depending on contents of data.txt and input string
	return string.replace(',' , ' ').replace(';' , ' ').split()

def checker(input_data):
    file_data=""
    with open('data.txt','rt') as f:
        for line in f:
            file_data = file_data + line
            
	a = process_string(file_data)
    print "Data file -",
    print a
    
    b = process_string(input_data)
    print "Input string -",
    print b
    
    count = 0
    for i in b:
    	if i in a:
	        count=count+1
                
    print "count = ",count
    percentage = str(float(count)/len(b)*100.0) + "%"                
    return percentage

app.run()
