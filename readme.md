dwv-dcm4chee-web
================

[dcm4chee](http://www.dcm4che.org/)-web3 link provider to preview DICOM series and instances using [dwv](https://github.com/ivmartel/dwv), a pure HTML5/JavaScript **D**ICOM **W**eb **V**iewer. 

Released under GNU GPL license (see [license.txt](license.txt)). 

[![Build Status](https://travis-ci.org/ivmartel/dwv-dcm4chee-web.svg?branch=master)](https://travis-ci.org/ivmartel/dwv-dcm4chee-web)

Build instructions
------------------
In order to build this project, you need: Java jdk and [Maven](http://maven.apache.org/download.cgi). Check out the build steps from the [travis](https://github.com/ivmartel/dwv-dcm4chee-web/blob/master/.travis.yml) file and the build log. The current build is generated using Java 1.7 and Maven 3.2.

The result is the `dwv-dcm4chee-web.jar` file.

Installation
------------
In the dcm4chee `server\default\deploy` folder, copy:
 * the `dwv-dcm4chee-web.jar` (built or from its [releases](https://github.com/ivmartel/dwv-dcm4chee-web/releases))
 * the weasis-pacs-connector [v4.0.0](http://sourceforge.net/projects/dcm4che/files/Weasis/weasis-pacs-connector/4.0.0/weasis-pacs-connector.war/download),
 * the desired `dwv`: download from this [page](http://ivmartel.github.io/dwv-dcm4chee-web/) and rename to `dwv.war`.

dcm4chee configuration
----------------------
From the JMX console `http://localhost:8080/jmx-console` (adapt to your host):
 * select `service=WebConfig` in the `dcm4chee.web` section,
 * set `WebviewerNames = dwv` (dwv should be in the `InstalledWebViewer`),
 * check that `WebviewerBaseUrl = NONE`,
 * click the `Apply Changes` button.

Launch
-------
From `http://localhost:8080/dcm4chee-web3` you should now be able to directly launch dwv from the `Open Web Viewer` icon at the Patient, Series and Study level as for the [Weasis](http://www.dcm4che.org/confluence/display/WEA/Installing+Weasis+in+DCM4CHEE) web viewer (see [snapshot](http://www.dcm4che.org/confluence/download/attachments/16121882/screen1b.png)).

As a check if things go west, DWV should be availalble from `http://localhost:8080/dwv/viewers/mobile`.

