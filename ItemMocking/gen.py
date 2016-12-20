import sys, random

numOfRecords = int(sys.argv[1])
weightRangeLow = int(sys.argv[2])
weightRangeHigh = int(sys.argv[3])
valueRangeLow = int(sys.argv[4])
valueRangeHigh = int(sys.argv[5])

lines = []
for i in range(numOfRecords):
	lines.append("a " + str(random.randint(weightRangeLow, weightRangeHigh)) + " " + str(random.randint(valueRangeLow, valueRangeHigh)))
	
lines = "\n".join(lines)

with open("output_gen.txt", 'w') as fp:
	fp.writelines(lines)