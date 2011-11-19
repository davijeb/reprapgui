
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

MAVEN

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

Some libraries used in this project are not current hosted on an artifact repository. What follows is the steps required to get the required libraries
and add them to Maven (they can be used outside of Maven but that's probably not a good idea).

For testing the Windowlicker framework is used to simulate user interaction with the GUI. The project is located in a svn repository
and can be obtained using:

svn checkout http://windowlicker.googlecode.com/svn/trunk/ windowlicker-read-only

then run build.sh and wait for all the tests to complete (may take some time). 

To add to your maven repository run:

mvn install:install-file -Dfile=windowlicker-swing-DEV.jar  -DgroupId=com.objogate.wl -DartifactId=windowlicker-swing -Dversion=1.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=windowlicker-swing-CORE.jar -DgroupId=com.objogate.wl -DartifactId=windowlicker-core  -Dversion=1.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=windowlicker-swing-WEB.jar  -DgroupId=com.objogate.wl -DartifactId=windowlicker-web   -Dversion=1.0.0 -Dpackaging=jar

In addition, the following JAR is used for the look and feel for the front-end. Get the latest from: http://www.jtattoo.net/Download.html.

To add to your maven repository run:

mvn install:install-file -Dfile=JTatoo.jar -DgroupId=com.jatoo -DartifactId=plaf -Dversion=1.0.0 -Dpackaging=jar

Another third-party dependency is for a testing library called springockito which allows the DI spring beans to be Mocked as they are tested. This means that @Autowired
beans can be mocked as is they were @Spy annotated. The latest jar can be found at https://bitbucket.org/kubek2k/springockito/downloads.

To add to your maven repository run:

mvn install:install-file -Dfile=springockito-1.0.0-SNAPSHOT.jarspringockito-1.0.0-SNAPSHOT.jar -DgroupId=com.springockito -DartifactId=springockito -Dversion=1.0.0 -Dpackaging=jar






