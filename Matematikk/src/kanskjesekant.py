from math import *

def f(x):
    return x - cos(x)

x = [0.5,1]

for n in range(1,20):
    if(x[n] == x[n-1]) :
        break
    x.append(0)
    x[n+1] = x[n]-f(x[n])*((x[n]-x[n-1])/(f(x[n])-f(x[n-1])))

for i in range(0,len(x)):
    print "x"+str(i)+":",round(x[i],6)
