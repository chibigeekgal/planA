CONTEXT =	../webapps/ROOT
JARS =	servlets.jar beans.jar
CLSDIR = 	$(CONTEXT)/WEB-INF/classes
CLASSES =  Person_Servlet.class Question_Servlet.class
LIBDIR = 	$(CONTEXT)/WEB-INF/lib

all:	install

clean:	/bin/rm -f $(JARS) $(CLASSES) $(CLSDIR)/* $(LIBDIR)/*

install:	$(JARS) # $(CLASSES)
	install -m600 $(JARS) $(LIBDIR)	
	install -m600 $(CLASSES) $(CLSDIR)

servlets.jar:	$(CLASSES)
	jar cf servlets.jar $(CLASSES)

beans.jar:	beans.mf beans/User_bean.class beans/Question_bean.class beans/Method.class beans/UserMethod.class beans/QuestionMethod.class
	jar cfm beans.jar beans.mf beans/User_bean.class beans/Question_bean.class beans/Method.class beans/UserMethod.class beans/QuestionMethod.class

beans/User_bean.class:	beans/User_bean.java
	javac -d . beans/User_bean.java
beans/Question_bean.class:	beans/Question_bean.java
	javac -d . beans/Question_bean.java
beans/Method.class:	beans/Method.java
	javac -d . beans/Method.java
beans/UserMethod.class:	beans/UserMethod.java
	javac -d . beans/UserMethod.java
beans/QuestionMethod.class:	beans/QuestionMethod.java
	javac -cp .:$(LIBDIR)/gson-2.2.4.jar beans/QuestionMethod.java
Person_Servlet.class:	Person_Servlet.java | beans.jar
	javac -cp /usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar:beans.jar Person_Servlet.java
Question_Servlet.class:	Question_Servlet.java | beans.jar
	javac -cp /usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar:beans.jar:$(LIBDIR)/gson-2.2.4.jar Question_Servlet.java
