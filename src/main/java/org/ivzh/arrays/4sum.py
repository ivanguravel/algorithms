# https://cses.fi/problemset/task/1642

n, x = map(int, input().split())
a = list(map(int, input().split()))
 
pairs = {}
 
for k in range(2, n):
    j = k - 1
    for i in range(j):
        pairs[a[i] + a[j]] = (i, j)
 
    for l in range(k + 1, n):
        need = x - a[k] - a[l]
 
        if need in pairs:
            i, j = pairs[need]
            print(i + 1, j + 1, k + 1, l + 1)
            exit()
 
