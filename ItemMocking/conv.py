#import file

weights = []
vals = []
i = 0
with open('output_gen.txt') as fp:
	for line in fp:
		i = i + 1
		current = line.strip().split(' ')
		weights.append(current[1])
		vals.append(current[2])
		
weights = "{" + ", ".join(weights) + "}"
vals = "{" + ", ".join(vals) + "}"
with open('output.txt', 'w') as fp:
	fp.write("W = " + weights + "\nV = " + vals)
	