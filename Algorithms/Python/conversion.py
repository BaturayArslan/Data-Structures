

"""
	Conversion: Write a function to determine the number of bits you would need to flip to convert
	integer A to integer B.
	EXAMPLE
	Input: 29 (or: 11101), 15 (or: 01111)
	Output: 2 
"""


def conversionCost(A, B):
    counter = 0
    for i in range(0, 32):
        if (A & (1 << i) != B & (1 << i)):
            counter += 1

    return counter


if (__name__ == "__main__"):
    result = conversionCost(29, 15)
    print(result)
