#!/usr/bin/env python3

def print_grid(g):
    print("---------")
    for i in g:
        print(f'| {" ".join(i)} |')
    print("---------")
    return


def move(g):
    steps = 0

    while True:
        step = input().split()
        if step[0].isnumeric() and step[1].isnumeric():
            a = int(step[0]) - 1
            b = int(step[1]) - 1
            if not 0 <= a <= 2 or not 0 <= b <= 2:
                print("Coordinates should be from 1 to 3!")
            elif g[a][b] != "X" and g[a][b] != "O":
                if steps % 2 == 0:
                    g[a][b] = "X"
                else:
                    g[a][b] = "O"
                steps += 1
                ls = ["".join(g[0]), "".join(g[1]), "".join(g[2]),
                      g[0][0] + g[1][0] + g[2][0], g[0][1] + g[1][1] + g[2][1],
                      g[0][0] + g[1][1] + g[2][2], g[0][2] + g[1][1] + g[2][0]]
                print_grid(g)
                if "XXX" in ls:
                    print("X wins")
                    return
                elif "OOO" in ls:
                    print("O wins")
                    return
                elif steps == 9:
                    print("Draw")
                    return
            else:
                print("This cell is occupied! Choose another one!")
        else:
            print("You should enter numbers!")


def main():
    grid = [
        [" ", " ", " "],
        [" ", " ", " "],
        [" ", " ", " "]
    ]
    print_grid(grid)
    move(grid)


main()
