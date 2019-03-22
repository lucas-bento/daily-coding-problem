# This problem was asked by Uber.

# Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
# For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
# Follow-up: what if you can't use division?



# Brute force solution
# For each element, iterate the entire array and multiply, skiping the current element
# Complexity: O(n²)
def solution(elements): 
    result = []

    for i in range(0, len(elements)):
        current = None
        
        for j in range(0, len(elements)):
            if i==j:
                continue
            if current == None:
                current = elements[j]
            else:
                current = current * elements[j]

        result.insert(i, current)

    return  result


print(solution([1,2,3,4,5]))
print(solution([3,2,1]))