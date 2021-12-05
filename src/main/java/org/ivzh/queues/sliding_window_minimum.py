# https://informatics.msk.ru/mod/statements/view3.php?chapterid=756#1

inp = input()
arg = inp.split(" ")
n = int(arg[0])
windowSize = int(arg[1])
arrayInStr = input()
nums = [int(x) for x in arrayInStr.split()]
queue = []
i = 0
while i < n:

    if len(queue) > 0 and i - windowSize + 1 > queue[0]:
        queue.pop(0)

    while len(queue) > 0 and nums[queue[-1]] > nums[i]:
        queue.pop()
    queue.append(i)

    if i >= windowSize - 1:
        print(nums[queue[0]])
    i += 1
