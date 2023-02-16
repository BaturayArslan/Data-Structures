import sys
from collections import defaultdict
from typing import DefaultDict, Dict

#  TODO:: This code has bug with palesss and pale or pasles and pale input fix later...

"""
	One Away: There are three types of edits that can be performed on strings: insert a character,
	remove a character, or replace a character. Given two strings, write a function to check if they are
	one edit (or zero edits) away.
	EXAMPLE:
	pale, ple -> true
	pales, pale -> true
	pale, bale -> true
	pale, bake -> false 
"""


def create_hashmap(input: str) -> DefaultDict[str, int]:
    hashmap = {}

    for character in input:
        if (character in hashmap):
            hashmap[character] += 1
        else:
            hashmap[character] = 1

    return hashmap


def one_way(
        first_hashmap: DefaultDict[str, int],
        second_hashmap: DefaultDict[str, int]) -> bool:

    counter = 0
    for character in first_hashmap.keys():
        if (character in second_hashmap):
            if (first_hashmap[character] != second_hashmap[character]):
                counter += 1
                if (counter > 1):
                    return False
        else:
            counter += 1
            if (counter > 1):
                return False

    counter = 0
    for character in second_hashmap.keys():
        if (character in first_hashmap):
            if (first_hashmap[character] != second_hashmap[character]):
                counter += 1
                if (counter > 1):
                    return False
        else:
            counter += 1
            if (counter > 1):
                return False

    return True


def main():
    first_input = sys.argv[1]
    second_input = sys.argv[2]

    first_hashmap = create_hashmap(first_input)
    second_hashmap = create_hashmap(second_input)

    if (one_way(first_hashmap, second_hashmap)):
        print("True")
        return

    print("False")


# if (__name__ == "__main__"):
main()
