import sys


def URLify(input: str) -> str:
    result = ""
    for character in input:
        if (character == " "):
            result = result + "%20"
            continue
        result = result + character
    return result


def main():
    input = sys.argv[1]
    print(URLify(input))


if (__name__ == "__main__"):
    main()
