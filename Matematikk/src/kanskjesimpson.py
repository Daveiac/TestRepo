from math import *

def f(x):
    return 1/(1+x**2)

def simp(m,a,b):
    h = (b-a)/m
    summ = 0
    for i in range(0,m,2):
        temp = f(a+i*h) + 4*f(a+(i+1)*h) + f(a+(i+2)*h)
        summ+= temp
    return (h/3)*summ

print simp(8,0,1.0)
