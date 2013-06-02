CONTEXT =	../webapps/ROOT
JARS =	bean1.jar servlets.jar
CLSDIR = 	$(CONTEXT)/WEB-INF/classes
CLASSES = 	Simple.class Login_Servlet.class
LIBDIR = 	$(CONTEXT)/WEB-INF/lib

all:	install

clean:	/bin/rm -f $(JARS) $(CLASSES) $(CLSDIR)/* $(LIBDIR)/*

install:	$(JARS) # $(CLASSES)
	install -m600 $(JARS) $(LIBDIR)	
	install -m600 $(CLASSES) $(CLSDIR)

bean1.jar:	dcwbeans/bean1.class bean1.mf
	jar cfm bean1.jar bean1.mf dcwbeans/bean1.class

dcwbeans/bean1.class:	dcwbeans/bean1.java
			javac -d . dcwbeans/bean1.java

Simple.class:	Simple.java
	javac -cp /usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar Simple.java

servlets.jar:	$(CLASSES)
	jar cf servlets.jar $(CLASSES)

Login_Servlet.class:	Login_Servlet.java
	javac -cp /usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar  Login_Servlet.java 										
