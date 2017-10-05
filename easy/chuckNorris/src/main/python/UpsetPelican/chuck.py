import sys
import math

def askMessage():
    message = input()
    return message

def binaryList(translatemessage):
    binchars = []
    translatemessage
    chars = [ bin(ord(ch))[2:].zfill(9) for ch in translatemessage ]
    for char in chars:
            char = char[2:]
            binchars.append(char)
    return binchars

def chuckarize(stringa):
    chuckval = "00" if stringa[0] == "0"  else "0"
    return "{} {}".format(chuckval, "0"*len(stringa))

def chuckarize_string(stringa):
    pointer = 0
    data = []
    while pointer < len(stringa):
        c = stringa[pointer]
        cc = 1
        for i in range(pointer+1, len(stringa)):
            if c != stringa[i]:
                break
            cc +=1
        pointer += cc
        data.append( chuckarize(c * cc))
    return " ".join(data)

def main():
    m = askMessage()
    bl = binaryList(m)
    answer = chuckarize_string("".join(bl))
    print(answer)


if __name__ == "__main__":
    sys.exit(main())