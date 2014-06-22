demo_nlp_im
===========
interest mining demo

1. LDA example based on Gibbs sampling. 

This demo use IKAnalyzer as a chinese word segmentation. Since the project is a MAVEN project, but IKAnalyzer is not added to the global MAVEN repo, please add the ik-analyzer.jar in dependencies folder to your local MAVEN repo using the following command:

'''
mvn install:install-file -Dfile=ik-analyzer.jar -DgroupId=org.wltea.ik-analyzer -DartifactId=ik-analyzer -Dversion=3.2.8 -Dpackaging=jar
'''
fudannlp.jar is also a chinese word segmentation. but it's not a MAVEN project,please add the fudannlp.jar in dependencies folder to your local MAVEN repo using the following command:
'''
mvn install:install-file -Dfile=fudannlp.jar -DgroupId=org.fudan.nlp -DartifactId=fudannlp -Dversion=1.0.0 -Dpackaging=jar
'''

时间短语
介词
标点
形容词
惯用词
人称代词
拟声词
地名
省略词
语气词
指示代词
叹词
表情符
网址
从属连词
机构名
专有名
型号名
事件名
副词
序数词
把动词
方位词
名词
形谓词
能愿动词
结构助词
品牌名
趋向动词
数词
被动词
时态词
限定词
并列连词
量词
人名
动词
疑问代词
运算符