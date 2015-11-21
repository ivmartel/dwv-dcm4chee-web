#!/bin/bash
#Script to push build results on the repository gh-pages branch.

if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  echo -e "Starting to update gh-pages\n"
  #a bit out of scope, while we have a java environment...
  export DWV_VERSION=0.12.0
  wget https://github.com/ivmartel/dwv/releases/download/v$DWV_VERSION/dwv-$DWV_VERSION.zip
  unzip dwv-$DWV_VERSION.zip
  cd dwv-$DWV_VERSION
  jar -cvf dwv.war *
  cd ..
  #setup git
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  #using token clone gh-pages branch
  ls -la
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/ivmartel/dwv-dcm4chee-web.git gh-pages > /dev/null
  #go into directory and copy data we're interested in to that directory
  cd gh-pages
  #copy new dist
  cp -R ../dwv-dcm4chee-web/target/*.jar distrib
  cp -R ../dwv-$DWV_VERSION/*.war distrib
  #add, commit and push files
  git add -Af .
  git commit -m "Travis build $TRAVIS_BUILD_NUMBER pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null
  echo -e "Done updating.\n"
fi