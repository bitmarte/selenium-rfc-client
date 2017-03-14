## Contrib
Take a look below for our contrib flow:

### Bugfix flow
* Open a new issue for each bug and wait a response
* If the bug will be detected, a new branch (bugfix) will be opened with a spanshot version
* Commit and push the fixes in the right branches
* Depending on severity, a new branch (release) will be created
* Please open a pull request from bugfix to the right release branch
* After merge the bugfix branch will be deleted

### Feature flow
* Open a new issue for each feature and wait a response
* If the feature will be approved, a new branch (feature) will be opened with a spanshot version
* Commit and push the features in the right branches
* Depending on the world, a new branch (release) will be created
* Please open a pull request from feature to the right release branch
* After merge the feature branch will be deleted

### Assumptions
* Master branch will be alligned with the last release one
* Versions:
 * major.minor.bugfix (eg. 1.0.3)
* pom.xml will be updated with the follow commands:
 * mvn versions:set -DnewVersion=<newVersion>
 * mvn versions:commit