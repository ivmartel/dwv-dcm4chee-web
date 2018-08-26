#!/bin/bash
#Script to push build results on the repository gh-pages branch.

if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  echo -e "Starting to update gh-pages\n"
  #setup git
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  #using token clone gh-pages branch
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/ivmartel/dwv-dcm4chee-web.git gh-pages > /dev/null
  #a bit out of scope, while we have a java environment...
  export DWV_VIEWER_NAME=dwv-jqmobile
  export DWV_VIEWER_VERSION=0.2.0
  if [ ! -f gh-pages/distrib/dwv-viewer_$DWV_VIEWER_NAME-$DWV_VIEWER_VERSION.war ]; then
    echo -e "Creating dwv-viewer war file\n"
    wget https://github.com/ivmartel/$DWV_VIEWER_NAME/releases/download/v$DWV_VIEWER_VERSION/$DWV_VIEWER_NAME-$DWV_VIEWER_VERSION.zip
    unzip -qq $DWV_VIEWER_NAME-$DWV_VIEWER_VERSION.zip
    cd $DWV_VIEWER_NAME-$DWV_VIEWER_VERSION
    jar -cvf dwv-viewer_$DWV_VIEWER_NAME-$DWV_VIEWER_VERSION.war *
    cp dwv-viewer_$DWV_VIEWER_NAME-$DWV_VIEWER_VERSION.war ../gh-pages/distrib
    cd ..
  fi
  #copy new dist
  cp -R dwv-dcm4chee-web/target/*.jar gh-pages/distrib
  #add, commit and push files
  cd gh-pages
  git add -Af .
  git commit -m "Travis build $TRAVIS_BUILD_NUMBER pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null
  echo -e "Done updating.\n"
fi
