from typing import Protocol, Optional, Union, Iterable, Any, Tuple, List
from dataclasses import dataclass
from abc import abstractclassmethod


class MyDictionaryPro(Protocol):
    @abstractclassmethod
    def put(key: Union[Iterable[str], int], value: Any) -> None:
        ...

    @abstractclassmethod
    def get(key: Union[Iterable[str], int]) -> Any:
        ...

    @abstractclassmethod
    def length() -> int:
        ...

    @abstractclassmethod
    def get_hash(key: Union[Iterable[str], int]) -> int:
        ...

    @abstractclassmethod
    def update(key: Union[Iterable[str], int], value) -> None:
        ...


@dataclass
class MyBucket:
    key: Union[Iterable[str], int]
    value: Any
    hashCode: int


class MyDictionary(MyDictionaryPro):
    def __init__(self):
        self.__size = 16
        self.__length = 0
        self.__map = [None for _ in range(0, self.__size)]

    def length(self) -> int:
        return self.length

    def get_hash(self, key: Union[Iterable[str], int]) -> int:
        hash_val = hash(key)
        bin_hash_val = bin(hash_val)[-32:]
        return int(bin_hash_val, base=2) % self.__size

    def put(self, key: Union[Iterable[str], int], value: Any) -> None:
        # Insert new element to map and keep in mind collision and resizing and check key if its already in map
        # check if it is fullness higher than %70 if so resize map
        # check key is already in map if it is update key with new value
        if (self.fullness_ratio() >= 70):
            # Resize
            self._resize_map()

        hash_val = self.get_hash(key)
        location = self._find_index(key, hash_val)
        if (location != -1):
            # Update key and return
            self.__map[location] = value
            return

        # Insert a new key to hashmap
        index = self._collision_handler(hash_val)
        self.__map[index] = MyBucket(key, value, hash_val)
        self.__length += 1

    def _collision_handler(self, index: int) -> int:
        if (index >= self.__size):
            index = 0

        if (not self.__map[index]):
            return index

        return self.collision_handler(index + 1)

    def _find_index(self, key, index) -> int:
        if (index >= self.__size):
            index = 0

        if (not self.__map[index]):
            return -1

        if (self.__map[index].key == key):
            return index

        return self._find_index(key, index + 1)

    def fullness_ratio(self) -> float:
        return (self.__length / self.__size) * 100

    def _get_buckets(self) -> Tuple[MyBucket]:
        tmp = []
        for bucket in self.__map:
            if (bucket):
                tmp.append(bucket)
        return tmp

    def _resize_map(self):
        prev_buckets = self._get_buckets()
        self.__size *= 2
        self.__length = 0
        for bucket in prev_buckets:
            self.put(bucket.key, bucket.value)

    def length(self) -> int:
        return self.__length

    def get(self, key) -> Any:
        hash_val = self.get_hash(key)
        index = self._find_index(key, hash_val)
        if (index == -1):
            raise KeyError
        return self.__map[index].value

    def update(self, key, value) -> None:
        hash_val = self.get_hash(key)
        index = self._find_index(key, hash_val)
        if (index == -1):
            raise KeyError
        self.__map[index].value = value


def main():
    test = MyDictionary()
    test.put("hello", "world")
    test.put("merhaba", "dünya")
    print(test.get("merhaba"))


if (__name__ == "__main__"):
    main()
