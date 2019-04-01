# This problem was asked by Stripe.

# Given an array of integers, find the first missing positive integer in linear time and constant space.
# In other words, find the lowest positive integer that does not exist in the array.
# The array can contain duplicates and negative numbers as well.

# For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

# You can modify the input array in-place.

#############################################################################################################
# The solution involves understanding that the return value MUST be in the range 1..n+1, where n == array size
# So you only need to check the positive numbers that are in the index range of the array, and ignore the rest

# Best i could do....
# Using an auxiliary array, so not constant space
def find_first_missing_with_extra_space(array):
    length = len(array)

    # This array controls wich possible answer value is present in the input
    # At start, none are present.
    control = [False] * length

    for i in range(0, length):
        x = array[i]

        # As we find the numbers that could be our answer, we save them in the control array,
        # using the control's index as identifier
        if 0 < x <= length:
            control[x-1] = True

    # First not found in the control array (plus 1) is our number
    for i in range(0, length):
        y = control[i]
        if not y:
            return i+1

    # If all positions in the control are present, the answer is lenght+1 
    return length + 1

# After searching online, I've got some tips... 
def find_first_missing(array):
    length = len(array)

    head = 0
    tail = length - 1

    # Split the array, between positive and not positive numbers
    while head < tail: 
        if array[tail] > 0 >= array[head]:
            temp = array[head]
            array[head] = array[tail]
            array[tail] = temp

        if array[head] > 0:
            head = head + 1

        if array[tail] < 0 and tail != head:
            tail = tail - 1

    # Follow the same strategy from the previous implementation on the positive part of the array,
    # but using the array's elements own sign as a flag, instead of using a control array
    for i in range(0, tail+1):
        current = abs(array[i])

        if 0 < current <= tail:
            mark_value = array[current-1]

            if mark_value > 0:
                array[current-1] = mark_value * -1 # Mark previous position as 'present'

    for i in range(0, tail+1):
        if array[i] > 0: # First not 'present' identifies the answer
            return i + 1

    return tail + 1 # If all are present, answer is the next larger integer that could not fit in the array

# Tests

def test(array, wanted_value):
    a = find_first_missing_with_extra_space(array)
    b = find_first_missing(array)
    print("a:%s" % a)
    print("b:%s" % b)
    assert wanted_value == a == b


test([3, 4, -1, 1], 2)
test([1, 2, 0], 3)
test([3, 5, 9, 4, 1, 2], 6)
test([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], 10)

