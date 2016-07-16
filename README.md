# wicket-pagemounter

A small utility that automatically mounts all WebPages (or a certain subset thereof) using the fully qualified classname, optionally with a prefix.

Usage:

    Application.init:
        new PageMounter("my.package", this).mountAllPagesExtending("foo", MyPage.class);
        
This will take all non-abstract page classes extending `MyPage` that are in `my.package` (or a subpackage) and mount them at `foo/<classname>`, for example:

    foo/my.package.MyConcretePage

