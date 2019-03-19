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