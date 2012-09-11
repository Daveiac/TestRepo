'''
Created on 11. sep. 2012

@author: David
'''

A = [[4,-1,0],
     [4,-16,4],
     [0,-1,4]]

x = [[1,1,1]]

b = [2,2,4]

def Jacobi(itr,S): #S = signifikante siffer
    n = len(A)
    for m in range(0,itr):
        x.append([])
        for j in range (0,n):
            x[m+1].append(b[j])
            for k in range(0,n):
                if(k != j):
                    x[m+1][j]-= A[j][k]*x[m][k]
            x[m+1][j] *= (1.0/A[j][j])
            print "x["+str(m+1)+"]["+str(j)+"]",round(x[m+1][j],S-1)
        print 

Jacobi(5,6)

