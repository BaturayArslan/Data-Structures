import sys
from typing import Dict
from collections import defaultdict

"""
	Check Permutation: Given two strings, write a method to decide
	if one is a permutation of the other

"""


def create_hashmap(list: str) -> Dict[str, int]:
    hashmap = defaultdict(lambda: 0)
    for character in list:
        hashmap[character] += 1
    return hashmap


def is_permutation(first_hashmap: Dict[str, int], second_hashmap: Dict[str, int]) -> bool:
    try:
        for key in first_hashmap:
            if (first_hashmap[key] != second_hashmap[key]):
                return False
        for key in second_hashmap:
            if (first_hashmap[key] != second_hashmap[key]):
                return False
        return True

    except KeyError as e:
        return False


def main():
    first_str = sys.argv[1]
    second_str = sys.argv[2]

    first_hashmap = create_hashmap(first_str)
    second_hashmap = create_hashmap(second_str)

    if (is_permutation(first_hashmap, second_hashmap)):
        print("True")
        return
    print("False")


if (__name__ == "__main__"):
    main()
