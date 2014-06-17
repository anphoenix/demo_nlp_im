# -*- coding: utf-8 -*-
__author__ = 'stefanie'


'''

'''

import sys

def parseTheta(inputData):
    l = inputData.readline()
    while l:
        line = l.strip()
        if line:
            fields = line.split("\t")
            max = 0
            maxindex = -1;
            for index, field in enumerate(fields):
                if float(field) > max:
                    max = float(field)
                    maxindex = index
            print str(maxindex) + "\t" + str(max)
        l = inputData.readline()

def usage():
    print "One parameter is required as the LDA result *.theta file path"


if __name__ == "__main__":

    if len(sys.argv) < 1:  # Expect more then two argument: the input data file and output folder
        usage()
        sys.exit(2)
    try:
        inputData = file(sys.argv[1], "r")
    except IOError:
        sys.stderr.write("ERROR: Cannot read inputfile %s.\n" % arg)
        sys.exit(1)
    parseTheta(inputData)


