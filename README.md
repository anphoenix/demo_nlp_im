demo_nlp_im
===========
interest mining demo

1. LDA example based on Gibbs sampling. 

This demo use IKAnalyzer as a chinese word segmentation. Since the project is a MAVEN project, but IKAnalyzer is not added to the global MAVEN repo, please add the ik-analyzer.jar in dependencies folder to your local MAVEN repo using the following command:

'''
mvn install:install-file -Dfile=ik-analyzer.jar -DgroupId=org.wltea.ik-analyzer -DartifactId=ik-analyzer -Dversion=3.2.8 -Dpackaging=jar
'''
