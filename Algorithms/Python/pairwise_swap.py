

"""
	Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as
	possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on)
"""


def swap(value):
    tmp = value >> 1
    for i in range(0, 32, 2):
        if ((tmp & (1 << i)) != 0):
            value |= (1 << i)
        else:
            value &= (~(1 << i))

    return value


def main():
    ...


if (__name__ == "__main__"):
    main()
