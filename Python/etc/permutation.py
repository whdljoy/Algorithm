##조합
##another
def combinations_2(array, r):
    for i in range(len(array)):
        if r == 1: # 종료 조건
            yield [array[i]]
        else:
            for next in combinations_2(array[i+1:], r-1):
                yield [array[i]] + next
def gen_combinations(arr, n): 
    result =[] 
    if n == 0: 
        return [[]] 
    for i in range(0, len(arr)): 
        elem = arr[i] 
        rest_arr = arr[i + 1:] 
        for C in gen_combinations(rest_arr, n-1): 
            result.append([elem]+C) 
    return result
##############################################
## 중복조합

def combinations_3(array, r):
    for i in range(len(array)):
        if r == 1:
            yield [array[i]]
        else:
            ## array[i+1: ] -> array[i: ] 변경
            for next in combinations_3(array[i:], r-1):
                yield [array[i]] + next
## #################################################
#순열
def permutations_2(array, r):
    for i in range(len(array)):
        if r == 1:
            yield [array[i]]
        else:
            for next in permutations_2(array[:i]+array[i+1:], r-1):
                yield [array[i]] + next

##another
def gen_permutations(arr, n): 
    result = [] 
    if n == 0: 
        return [[]] 
    for i, elem in enumerate(arr): 
        for P in gen_permutations(arr[:i] + arr[i+1:], n-1): 
            result += [[elem]+P] 
    return result 

###################################################
#중복 순열
def permutations_4(array, r):
    for i in range(len(array)):
        if r == 1:
            yield [array[i]]
        else:
            for next in permutations_4(array, r-1):
                yield [array[i]] + next