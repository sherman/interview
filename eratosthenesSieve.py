import math

def getPrimes(max):
	primes = [True for i in xrange(0, max + 1, 1)]
	primes[0] = False
	primes[1] = False

	for i in xrange(2, max + 1, 1):
		if primes[i]:
			k = 1
			for j in xrange(i * i, max + 1, k * i):
				primes[j] = False
				k = k + 1

	return [index for index, value in enumerate(primes) if value]

print getPrimes(20)

