import sys
from collections import defaultdict
from typing import DefaultDict

"""
	 Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation
	is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
	EXAMPLE
	Input: Tact Coa
	Output: True (permutations: "taco cat", "atco eta", etc.)
"""


def is_polidrome_permutation(hashmap: DefaultDict[str, int], length: int) -> bool:
    counter = 0
    if (length % 2 == 0):
        # if length of string is even
        for character in hashmap.keys():
            if (hashmap[character] % 2 != 0):
                counter += 1
                if (counter > 2):
                    return False
        ...
    else:
        # if length of string is odd
        for character in hashmap.keys():
            if (hashmap[character] % 2 != 0):
                counter += 1
                if (counter > 1):
                    return False

    return True


def create_hashmap(input: str) -> DefaultDict[str, int]:
    hashmap = defaultdict(lambda: 0)
    for character in input:
        hashmap[character.lower()] += 1

    return hashmap


def main():
    input = sys.argv[1]
    hashmap = create_hashmap(input)
    if (is_polidrome_permutation(hashmap, len(input))):
        print("True")
        return
    print("False")


if (__name__ == "__main__"):
    main()
