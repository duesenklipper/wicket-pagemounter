# wicket-pagemounter

Current versions:
 - **1.0.wicket7** for Wicket 7.x
 - **1.0.wicket6** for Wicket 6.x
 - **1.0.wicket5** for Wicket 1.5.x


A small utility that automatically mounts all WebPages (or a certain subset thereof) using the fully qualified classname, optionally with a prefix.

Usage:

    Application.init:
        new PageMounter("my.package", this).mountAllPagesExtending("foo", MyPage.class);
        
This will take all non-abstract page classes extending `MyPage` that are in `my.package` (or a subpackage) and mount them at `foo/<classname>`, for example:

    foo/my.package.MyConcretePage

To use it, add the following repository to your `pom.xml`:

    <repositories>
      <repository>
        <id>duesenklipper</id>
        <url>http://duesenklipper.github.com/maven/releases</url>
        <snapshots>
          <enabled>false</enabled>
        </snapshots>
        <releases>
          <enabled>true</enabled>
        </releases>
      </repository>
    </repositories>

Then add the following dependency:

    <dependency>
        <groupId>de.wicketbuch.extensions</groupId>
        <artifactId>pagemounter</artifactId>
        <version>1.0.wicket7</version>
    </dependency>



