import sys
import math

n = int(input())
m = []
for i in input().split():
    t = int(i)
    m.append(t)

list.sort(m,reverse=True)

if n == 0:
    result = 0
else:
    result = min(m, key=abs)

print(result)