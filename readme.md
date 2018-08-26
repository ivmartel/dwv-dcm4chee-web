dwv-dcm4chee-web
================

[dcm4chee](http://www.dcm4che.org/)-web3 link provider to preview DICOM series and instances using a viewer based on [dwv](https://github.com/ivmartel/dwv), a pure HTML5/JavaScript **D**ICOM **W**eb **V**iewer. 

Released under GNU GPL license (see [license.txt](license.txt)). 

[![Build Status](https://travis-ci.org/ivmartel/dwv-dcm4chee-web.svg?branch=master)](https://travis-ci.org/ivmartel/dwv-dcm4chee-web)

Installation
------------
You need a working version of dcm4chee. In the `server\default\deploy` folder, copy:
 * the [weasis-pacs-connector](https://github.com/nroduit/weasis-pacs-connector),
 * the `dwv-dcm4chee-web.jar`,
 * the desired dwv viewer as a `war` file (has to be named `dwv-viewer.war`).

Check out the [wiki](https://github.com/ivmartel/dwv-dcm4chee-web/wiki) for download links and version details.

dcm4chee configuration
----------------------
From the JMX console (default at [http://localhost:8080/jmx-console](http://localhost:8080/jmx-console)):
 * select `service=WebConfig` in the `dcm4chee.web` section,
 * set `WebviewerNames = dwv` (dwv should be in the `InstalledWebViewer`),
 * check that `WebviewerBaseUrl = NONE`,
 * click the `Apply Changes` button.

Launch
-------
From dcm4che web interface (default at [http://localhost:8080/dcm4chee-web3](http://localhost:8080/dcm4chee-web3)) you should now be able to directly launch dwv from the `Open Web Viewer` icon at the Patient, Series and Study level as for the [Weasis](http://www.dcm4che.org/confluence/display/WEA/Installing+Weasis+in+DCM4CHEE) web viewer (see [snapshot](https://dcm4che.atlassian.net/wiki/display/WEA/Home?preview=/3670024/3670343/screen1b.png)).

As a check if things go west, DWV should be availalble from [http://localhost:8080/dwv/viewers/mobile](http://localhost:8080/dwv/viewers/mobile).

Other
------
More information on the [wiki](https://github.com/ivmartel/dwv-dcm4chee-web/wiki)...
