import sys
from collections import defaultdict

"""
	Gives 1 command line argument and check if  all char in argument is unique
"""


def create_hashtable(list):
    hash_table = defaultdict(lambda: 0)
    for character in list:
        hash_table[character] += 1

    return hash_table


def is_unique(hash_table, character):
    for character in hash_table:
        if (hash_table[character] != 1):
            print("False")
            return False
        print("True")
        return True


def main():
    # Hello world
    input = sys.argv[1]
    hash_table = create_hashtable(input)

    is_unique(hash_table)


if (__name__ == "__main__"):
    main()
