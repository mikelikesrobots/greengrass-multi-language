import sys
import time
from src.hello_client import HelloClient


def main():
    args = sys.argv[1:]
    client = HelloClient(args[0])

    while True:
        client.tick()
        time.sleep(1)


if __name__ == "__main__":
    main()
