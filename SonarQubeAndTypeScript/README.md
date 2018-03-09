# SonarQube and TypeScript analysis
I did some checks on SonarQube for RESTEasy and noticed it contains by default plugin for TypeScript - https://docs.sonarqube.org/display/PLUG/SonarTS

Prerequisites: TypeScript version >=2.2, Node.js >=6ß

So I went ahead and analyzed OverBaard ;)


## Steps to reproduce
```bash
wget https://sonarsource.bintray.com/Distribution/sonarqube/sonarqube-7.0.zip && unzip -q sonarqube-7.0.zip
sonarqube-7.0/bin/macosx-universal-64/sonar.sh start

wget https://sonarsource.bintray.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-3.0.3.778-macosx.zip 
unzip -q sonar-scanner-cli-3.0.3.778-macosx.zip
TOOLS_DIR=${PWD}

git clone git@github.com:kabir/overbaard-redux.git
cd overbaard-redux/webapp/
yarn install
yarn run build
## yarn start

${TOOLS_DIR}/sonar-scanner-3.0.3.778-macosx/bin/sonar-scanner -Dsonar.projectKey=overbaard-webapp -Dsonar.sources=src/
```

And now you can open http://localhost:9000/dashboard?id=overbaard-webapp