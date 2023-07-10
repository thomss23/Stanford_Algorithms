def karatsuba(num1, num2):

    num_digits = max(len(str(num1)), len(str(num2)))

    if num_digits == 1:
        return num1 * num2
    
    digits_split_at = num_digits // 2
    
    a = num1 // 10 ** digits_split_at
    b = num1 % 10 ** digits_split_at
    c = num2 // 10 ** digits_split_at
    d = num2 % 10 ** digits_split_at

    ac = karatsuba(a, c)
    bd = karatsuba(b, d)
    aplusb_cplusd = karatsuba(a + b, c + d)

    return 10 ** (2 * digits_split_at) * ac + 10 ** digits_split_at * (aplusb_cplusd - ac - bd) + bd

print(karatsuba(3141592653589793238462643383279502884197169399375105820974944592,2718281828459045235360287471352662497757247093699959574966967627), 1234*5678)
