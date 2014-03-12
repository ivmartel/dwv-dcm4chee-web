/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at http://sourceforge.net/projects/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Agfa-Gevaert AG.
 * Portions created by the Initial Developer are Copyright (C) 2008
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See listed authors below.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package org.dcm4chee.web.webview.dwv;

import org.dcm4chee.web.common.webview.link.spi.WebviewerLinkProviderSPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Franz Willer <franz.willer@gmail.com>
 * @version $Revision$ $Date$
 * @since May 15, 2010
 */
public class DwvLinkProvider extends WebviewerLinkProviderSPI {
    private static final long serialVersionUID = 4548297230882756086L;

    private String baseUrl = "/dwv/viewers/mobile/index.html?type=manifest&input=";
    private String wadoUrl = "http://localhost:8080/weasis-pacs-connector/manifest?";
    
    private String getFinalURL(String wadoUrl) {
        StringBuilder buffer = new StringBuilder(baseUrl);
        String encodedUrl = "";
        try {
           encodedUrl = URLEncoder.encode(wadoUrl,"UTF-8");
        }
        catch( UnsupportedEncodingException e )
        {
            System.err.println("UnsupportedEncodingException: " + e.getMessage());
        }
        buffer.append(encodedUrl);
        return buffer.toString();
    }
    
    @Override
    public String getName() {
        return "dwv";
    }

    @Override
    public void setBaseURL(String url) {
        if (url != null) {
            baseUrl = url;
            if (baseUrl.indexOf("?") == -1) {
                baseUrl += "?";
            }
        }
    }

    @Override
    public boolean supportPatientLevel() {
        return true;
    }

    @Override
    public boolean supportStudyLevel() {
        return true;
    }

    @Override
    public boolean supportSeriesLevel() {
        return true;
    }

    @Override
    public boolean supportInstanceLevel() {
        return true;
    }

    @Override
    public boolean supportPresentationState() {
        return false;
    }

    @Override
    public boolean supportKeySelectionObject() {
        return false;
    }

    @Override
    public boolean supportStructuredReport() {
        return false;
    }

    @Override
    public String getUrlForPatient(String patientId, String issuer) {
        StringBuilder buffer = new StringBuilder(wadoUrl);
        buffer.append("patientID=");
        buffer.append(patientId);
        return getFinalURL(buffer.toString());
    }

    @Override
    public String getUrlForStudy(String studyIuid) {
        StringBuilder buffer = new StringBuilder(wadoUrl);
        buffer.append("studyUID=");
        buffer.append(studyIuid);
        return getFinalURL(buffer.toString());
    }

    @Override
    public String getUrlForSeries(String seriesIuid) {
        StringBuilder buffer = new StringBuilder(wadoUrl);
        buffer.append("seriesUID=");
        buffer.append(seriesIuid);
        return getFinalURL(buffer.toString());
    }

    @Override
    public String getUrlForInstance(String sopIuid) {
        StringBuilder buffer = new StringBuilder(wadoUrl);
        buffer.append("objectUID=");
        buffer.append(sopIuid);
        return getFinalURL(buffer.toString());
    }

    @Override
    public String getUrlForPresentationState(String iuid) {
        return null;
    }

    @Override
    public String getUrlForKeyObjectSelection(String iuid) {
        return null;
    }

    @Override
    public String getUrlForStructuredReport(String arg0) {
        return null;
    }

}
