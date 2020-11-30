import json
import csv
filename = "C:\\Users\ghazal\Desktop\googleplaystore.csv"
filename2 = "C:\\Users\ghazal\Desktop\file.json"
f = open(filename2, "a")
with open(filename, 'r') as data:
    for line in csv.DictReader(data):
        f.write("\n" + line)
f.close()