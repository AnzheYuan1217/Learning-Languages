import math
import argparse


def ann(a, p, n, i):
    if a == 0:  # calculate a
        i = i * 0.01 / 12
        a = p * ((i * (1 + i) ** n) / ((1 + i) ** n - 1))
        if a % 1 == 0:
            a = a // 1
        else:
            a = a // 1 + 1
        print(f'Your annuity payment = {int(a)}!')
    elif p == 0:  # calculate p
        i /= 1200
        p = a / ((i * (1 + i) ** n) / (((1 + i) ** n) - 1))
        print(f'Your loan principal = {p}!')
    else:  # calculate n
        i /= 1200
        n = math.log(a / (a - i * p), 1 + i)
        if n % 1 != 0:
            n += 1
        n //= 1

        y = int(n // 12)
        m = n % 12
        if m > 11:
            months = ""
            y += 1
        elif m % 1 != 0:
            months = f'{m // 1 + 1} months'
        else:
            months = f' and {m // 1} months'

        years = f'{y} years'

        print(f'It will take {years}{months} to repay this loan!')

    return int(a * n - p)


def dif(p, n, i):
    i /= 1200
    sum = 0
    for j in range(1, n + 1):
        d = p / n + i * (p - (p * (j - 1)) / n)
        if d % 1 != 0:
            d = (d // 1 + 1)
        sum += int(d)
        print(f'Month {j}: payment is {int(d)}')
    # p = a / ((i * (1 + i) ** n) / (((1 + i) ** n) - 1))
    return int(sum - p)


parser = argparse.ArgumentParser(description="Haha")
parser.add_argument('-t', '--type', default='')
parser.add_argument('-a', '--payment', type=float, default=0)
parser.add_argument('-p', '--principal', type=float, default=0)
parser.add_argument('-n', '--periods', type=int, default=0)
parser.add_argument('-i', '--interest', type=float, default=0)

args = parser.parse_args()

t = args.type  # type
a = args.payment  # payment
p = args.principal  # principal
n = args.periods  # periods
i = args.interest  # interest
valid = 0

if a != 0:
    valid += 1
if p != 0:
    valid += 1
if n != 0:
    valid += 1

if t != "diff" and t != "annuity":
    print("Incorrect parameters")
elif t == "diff" and a != 0:
    print("Incorrect parameters")
elif i == 0:
    print("Incorrect parameters")
elif valid > 2:
    print("Incorrect parameters")
elif a < 0 or p < 0 or n < 0 or i < 0:
    print("Incorrect parameters")
else:
    if t == "diff":
        print(f'Overpayment = {dif(p, n, i)}')
    else:
        print(f'Overpayment = {ann(a, p, n, i)}')
