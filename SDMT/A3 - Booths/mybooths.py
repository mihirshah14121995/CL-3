from booths import *
from flask import Flask,request,render_template

app = Flask(__name__)

@app.route('/') #what url should trigger our function
def mainpage():
    return render_template('index.html',product=None)

@app.route('/eval',methods=['POST','GET'])
def multiply():
	n1 = int(request.form['num1'])
	n2 = int(request.form['num2'])
	z = MyBooths(n1,n2)
	p = binaryToDecimal(z[:-1])        
	return render_template('index.html',product=p,a=n1,b=n2)

app.run()
