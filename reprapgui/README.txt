For testing the Windowlicker framework is used. Not presently a Maven repo but hopefully this will change.

In the meantime:

svn checkout http://windowlicker.googlecode.com/svn/trunk/ windowlicker-read-only

Then run build.sh and wait for all the tests to complete. To add to your maven repository run:

mvn install:install-file -Dfile=windowlicker-swing-DEV.jar -DgroupId=com.objogate.wl -DartifactId=windowlicker-swing -Dversion=1.0.0 -Dpackaging=jar

