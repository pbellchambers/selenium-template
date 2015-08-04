#test-skeleton [![Build Status](https://api.shippable.com/projects/5521b1bc5ab6cc1352b899ac/badge/master)](https://app.shippable.com/projects/5521b1bc5ab6cc1352b899ac/builds/latest)

A skeleton project for cucumber/selenium tests

##Instructions for Running

The project is run using gradle (tested with v2.4):

```
gradle cucumber -DisBrowserStackTest=false -DisMobileTest=false -Dtags="~@wip"
```