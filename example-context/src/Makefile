CONTEXT =	../webapps/ROOT
JARS =	servlets.jar beans.jar
CLSDIR = 	$(CONTEXT)/WEB-INF/classes
CLASSES = 	Display_Servlet.class beans/User_bean.class beans/Method.class Person_Servlet.class
LIBDIR = 	$(CONTEXT)/WEB-INF/lib

all:	install

clean:	/bin/rm -f $(JARS) $(CLASSES) $(CLSDIR)/* $(LIBDIR)/*

install:	$(JARS) # $(CLASSES)
	install -m600 $(JARS) $(LIBDIR)	
	install -m600 $(CLASSES) $(CLSDIR)

servlets.jar:	$(CLASSES)
	jar cf servlets.jar $(CLASSES)

beans.jar:	beans.mf beans/User_bean.class beans/Method.class 
	jar cfm beans.jar beans.mf beans/User_bean.class beans/Method.class

beans/User_bean.class:	beans/User_bean.java
	javac -d . beans/User_bean.java

beans/Method.class:	beans/Method.java
	javac -d . beans/Method.java

Display_Servlet.class:	Display_Servlet.java
	javac -cp  /usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar  Display_Servlet.java

Person_Servlet.class:	Person_Servlet.java
	javac -cp /usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar:beans.jar Person_Servlet.java
