CONTEXT =	webapps/ROOT
CLSDIR = 	$(CONTEXT)/WEB-INF/classes
LIBDIR = 	$(CONTEXT)/WEB-INF/lib
JARS =		$(BEANSJAR) build/servlets.jar
CLASSES =	$(call toclass,$(wildcard src/*.java))
BEANS =		$(wildcard src/beans/*.java)
BEANSCLASSES =	$(call toclass,$(BEANS))
BEANSJAR =	build/beans.jar
CLASSPATH =	$(BEANSJAR):/usr/lib/tomcat7/tomcat-7.0.29/lib/servlet-api.jar:$(LIBDIR)/gson-2.2.4.jar:$(LIBDIR)/jlatexmath-1.0.2.jar:$(LIBDIR)/postgresql-9.2-1002.jdbc4.jar

all:	install

clean:	
	/bin/rm -f $(JARS) $(CLASSES) $(BEANSCLASSES)

install:	clean $(JARS)
	install -m600 $(JARS) $(LIBDIR)
	install -m600 $(CLASSES) $(CLSDIR)

$(BEANSJAR):	$(BEANSCLASSES)
	javac -d build -cp $(CLASSPATH) $(BEANS)	
	jar cfm $(BEANSJAR) src/beans.mf $(BEANSCLASSES) -C build/ beans

build/servlets.jar:	$(CLASSES)|$(BEANSJAR)
	javac -d build -cp $(CLASSPATH) $(wildcard src/*.java)	
	jar cf build/servlets.jar $(CLASSES)		

define	toclass
	$(patsubst src%,build%,$(patsubst %.java,%.class,$1))
endef
