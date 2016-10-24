**Current version**: 1.0.1.wicket{5|6|7} for Wicket 1.5.x, 6.x, 7.x

**New:** These artifacts are now on Maven Central, no more custom repository 
needed.


# wicket-pagemounter

A small utility that automatically mounts all WebPages (or a certain subset thereof) using the fully qualified classname, optionally with a prefix.

Usage:

    Application.init:
        new PageMounter("my.package", this).mountAllPagesExtending("foo", MyPage.class);
        
This will take all non-abstract page classes extending `MyPage` that are in `my.package` (or a subpackage) and mount them at `foo/<classname>`, for example:

    foo/my.package.MyConcretePage

## Maven coordinates

    <dependency>
        <groupId>de.wicketbuch.extensions</groupId>
        <artifactId>pagemounter</artifactId>
        <version>1.0.1.wicket7</version>
    </dependency>

Make sure you choose the correct version for the version of Wicket you are
using, they are suffixed with `.wicket5`, `.wicket6`, `.wicket7` 
respectively.

This project uses [Semantic Versioning](http://semver.org/), so you can rely on
things not breaking within a major version.
