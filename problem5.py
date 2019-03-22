# This problem was asked by Jane Street.

# cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair. 
# For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.

# Given this implementation of cons:

# def cons(a, b):
#     def pair(f):
#         return f(a, b)
#     return pair
# Implement car and cdr.

##########################################################################

#Thats more of a tricky python question than programming in general


# This is the tricky function we receive:
def cons(a, b):
    def pair(f):        # 'cons' creates another function, 'pair', wich receives a parameter 'f'
        return f(a, b)  # Parameter 'f' turns out to be yet another function, that is not provided!
    return pair         # In the end, what is returned is not a proper pair, its a 
                        # function, that receives a (inexistent) function wich must create a pair...

# Then you have to code this sucker here
def f(a,b):
    return (a,b) 

# In the two required functions, you have to call 'pair', passing 'f' as parameter
# and then you get your pair...
def car(pair):
    return pair(f)[0]

def cdr(pair):
    return pair(f)[1]


# TESTS

assert 3 == car(cons(3, 4))
assert 4 == cdr(cons(3, 4))
