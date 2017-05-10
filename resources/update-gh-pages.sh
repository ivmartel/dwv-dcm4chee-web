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
  export DWV_VERSION=0.19.2
  if [ ! -f gh-pages/distrib/dwv-$DWV_VERSION.war ]; then
    echo -e "Creating dwv war file\n"
    wget https://github.com/ivmartel/dwv/releases/download/v$DWV_VERSION/dwv-$DWV_VERSION.zip
    unzip -qq dwv-$DWV_VERSION.zip
    cd dwv-$DWV_VERSION
    jar -cvf dwv-$DWV_VERSION.war *
    cp dwv-$DWV_VERSION.war ../gh-pages/distrib
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
