dwv-dcm4chee-web
================

[dcm4chee](http://www.dcm4che.org/)-web3 link provider for the [dwv](https://github.com/ivmartel/dwv) web viewer. Only compatible with the dcm4chee-web3 interface and with dcm4chee installed at `localhost:8080` (until I fix [#1](https://github.com/ivmartel/dwv-dcm4chee-web/issues/1)).

Build requirements
------------------
 * Java jdk 7,
 * Maven [2.2.1](http://maven.apache.org/download.cgi) (didn't manage to build with v3.2.1).

Build instructions
------------------
 * download the dcm4chee-web-3.0.3 [sources](http://sourceforge.net/projects/dcm4che/files/dcm4chee-web/3.0.3/dcm4chee-web-3.0.3-src.zip/download),
 * build `dcm4chee-web\dcm4chee-web-urlprovider` by typing `mvn clean install` in its folder,
 * copy the `dwv-dcm4chee-web` folder in `dcm4chee-web`,
 * build it by typing `mvn clean install` in its folder.

This should create a `dwv-dcm4chee-web.jar` in the `dwv-dcm4chee-web\target` folder.

Installation
------------
In the dcm4chee `server\default\deploy` folder, copy:
 * the `dwv-dcm4chee-web.jar` (built or from its [releases](https://github.com/ivmartel/dwv-dcm4chee-web/releases))
 * the weasis-pacs-connector [v4.0.0](http://sourceforge.net/projects/dcm4che/files/Weasis/weasis-pacs-connector/4.0.0/weasis-pacs-connector.war/download),
 * the desired `dwv.war` (in the dwv [releases](https://github.com/ivmartel/dwv/releases)).

dcm4chee configuration
----------------------
From the JMX console `http://localhost:8080/jmx-console`:
 * select `service=WebConfig` in the `dcm4chee.web` section,
 * set `WebviewerNames = dwv` (dwv should be in the `InstalledWebViewer`),
 * check that `WebviewerBaseUrl = NONE`,
 * click the `Apply Changes` button.

That's it, you should now be able to directly launch dwv from the `Open Web Viewer` icon at the Patient, Series and Study level as for the [Weasis](http://www.dcm4che.org/confluence/display/WEA/Installing+Weasis+in+DCM4CHEE) web viewer (see [snapshot](http://www.dcm4che.org/confluence/download/attachments/16121882/screen1b.png)).

 
 
