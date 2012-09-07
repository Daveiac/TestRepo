x = [0,10,15,20,22.5,30]
f = [0,227.04,362.78,517.35,602.97,901.67]

def lagrange(y):
    L = []
    summ = 0
    for i in range(0,len(x)):
        L.append(1)
        for j in range(0, len(x)):
            if (j != i):
                L[i] *= 1.0*(y-x[j])/(x[i]-x[j])
        summ += L[i]*f[i];
    return summ

print lagrange(16)
