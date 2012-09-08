from math import *
def f(x):
    return 2*x - cos(x)

def d(x):
    return 2+sin(x)

y = 9

for x in range(1,10):
    print round(y,6)
    y = y - f(y)/d(y)
