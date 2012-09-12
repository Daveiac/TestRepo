A = [[4,0,0],
     [-1,4,0],
     [-1,-1,4]]

x = [[0,0,0]]

b = [4,3,2]

def GaussSeidel(itr,S): #S = signifikante siffer
    n = len(A)
    for m in range(0,itr):
        x.append([])
        for j in range (0,n):
            x[m+1].append(b[j])
            for k in range(0,j):
                x[m+1][j]-= A[j][k]*x[m+1][k]
            for t in range(j+1,n):
                x[m+1][j]-= A[j][t]*x[m][t]
            x[m+1][j] *= (1.0/A[j][j])
            print("x["+str(m+1)+"]["+str(j)+"]",round(x[m+1][j],S-1))
        print()

GaussSeidel(5,6)

        
    
    

