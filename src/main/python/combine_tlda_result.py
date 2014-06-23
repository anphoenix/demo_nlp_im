# -*- coding: utf-8 -*-
__author__ = 'stefanie & DJ'


'''

'''

import sys, os, re, shutil

def analyzeTopics(inputTopic):
    str = inputTopic.read()
    strwithoutEnter = str.replace("\n", "")
#In this case, '' should be in the beginning since it's before Topic 0
    lineTopics = strwithoutEnter.split("Topic")
    print lineTopics
    return lineTopics
    
def returnFirst3Topics(fields):
#topic number should be greater than 3
    topics = [1, 2, 3]
    
    for i in range(4, len(fields)):
        for j in range(0, 3):
           if fields[i] > fields[topics[j]]:
               topics[j] = i
               break
    return topics

def associate_topic(inputData, lineTopics, outputFile, path):
    l = inputData.readline()
    while l:
        line=l.strip()
        if line:
            fields = line.split("\t")
        pid=fields[0].split(".")[0]
#        print "processing" + pid
        related = returnFirst3Topics(fields)
#        print related + [fields[related[0]], fields[related[1]], fields[related[2]]] + fields
        outputFile.write("Topics:" + returnFirst3Words(lineTopics[related[0]]) + ";" + returnFirst3Words(lineTopics[related[1]]) + ";" + returnFirst3Words(lineTopics[related[2]]) + "\nIntro:" + returnIntro(pid, path) + "\n")
        l = inputData.readline()
        
def returnIntro(pid, path):
    filePath = path + "\originaldocs\sample1_complete\\txt\\" + pid + ".intro.txt"
    print filePath
    introFile = file(filePath, "r")
    intro = introFile.readline()
    introFile.close()
    return intro
        
def returnFirst3Words(eachTopics):
    line = eachTopics.split("\t")
    topics = line[1] + "," + line[3] + "," + line[5]
    return topics

'''
def usage():
    print "One parameter is required as the LDA result *.theta file path"
'''

if __name__ == "__main__":

    if len(sys.argv) < 1:  # Expect more then two argument: the input data file and output folder
        path="/output/tlda/TopicsDistributionOnUsers.txt"
    else :
       path=sys.argv[1]
    inputFile = path + "\output\\tlda\TopicsDistributionOnUsers.txt"
    topicFile = path + "\output\\tlda\WordsInTopics.txt"
    outputFile = path + "\output\weibo.txt"
    try:
        print inputFile
        inputData = file(inputFile, "r")
        inputTopic = file(topicFile, "r")
        outputFile = file(outputFile, "w")
    except IOError:
        sys.stderr.write("ERROR: Cannot read inputfile %s.\n" % sys.argv)
        sys.exit(1)
    lineTopics = analyzeTopics(inputTopic)
    associate_topic(inputData, lineTopics, outputFile, path)
    inputData.close()
    inputTopic.close()
    outputFile.close()
    
    

