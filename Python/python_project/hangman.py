import random


def play_the_game(lis):
    word = list(random.choice(lis))

    a = list("-" * (len(word)))
    print("".join(a))
    guess_time = 8
    seen = set()
    while guess_time != 0:
        print()
        print("Input a letter:")
        guess = input()

        if len(guess) != 1:
            print("Please, input a single letter.")
            seen.add(guess)

        elif not guess.isalpha() or not guess.islower():
            print("Please, enter a lowercase letter from the English alphabet.")
            seen.add(guess)

        elif guess in seen:
            print("You've already guessed this letter.")

        elif guess not in word:
            print("That letter doesn't appear in the word.")
            guess_time -= 1
            seen.add(guess)
        else:
            for j in range(len(word)):
                if guess == word[j]:
                    a[j] = guess
            seen.add(guess)

        print("".join(a))
        if a == word:
            print(f'You guessed the word {"".join(word)}!')
            print("You survived!")
            return [1, 0]

    if a != word:
        print("You lost!")
        return [0, 1]


def show_result(a):
    print(f'You won: {a[0]} times.')
    print(f'You lost: {a[1]} times.')
    return


word_lis = ["python", "java", "swift", "javascript"]
print("H A N G M A N")
scores = [0, 0]
while True:
    print('Type "play" to play the game, "results" to show the scoreboard, and "exit" to quit:')
    com = input()
    if com == "play":
        res = play_the_game(word_lis)
        scores[0] += res[0]
        scores[1] += res[1]
    elif com == "results":
        show_result(scores)
    elif com == "exit":
        break

