from math import *

def f(x):
    return e**(-(x**2))

def simp(_2m,a,b):
    m = _2m/2
    h = ((b-a)*1.0)/_2m
    summ = 0
    for i in range(0,m):
        temp = f(a+2*i*h) + 4*f(a+(2*i+1)*h) + f(a+(2*i+2)*h)
        summ+= temp
    return (h/3)*summ

print simp(10,0,1)
