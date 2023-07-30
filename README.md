# SemVer for Java

The semver package for Java provides functionalities which conform to the semantic versioning convention: https://semver.org/  
These functionalities include the following:
* Validate a given version string to conform to the SemVer convention
* Sort versions in descending and ascending order
* Get latest and oldest version from a given list

## Usage
```java
import semver;

// returns true
SemverTools.isValidSemver("2.0.0-rc.1+build.123");

// returns ["1.0.0-alpha", "1.0.0-alpha.1", "1.0.0-alpha.beta", "1.0.0-beta", "1.0.0-beta.2", "1.0.0-beta.11", "1.0.0-rc.1", "1.0.0"]
List<String> versionsList = Arrays.asList("1.0.0", "1.0.0-alpha.beta", "1.0.0-alpha", "1.0.0-beta.11", "1.0.0-rc.1", "1.0.0-beta.2", "1.0.0-alpha.1", "1.0.0-beta");
SemverTools.sortAscending(versionsList);

// returns ["2.1.1", "2.1.0", "2.0.0", "1.0.0"]
List<String> versionsList = Arrays.asList("1.0.0", "2.1.0", "2.1.1", "2.0.0");
SemverTools.sortDescending(versionsList);

// returns "2.1.1"
List<String> versionsList = Arrays.asList("1.0.0", "2.1.0", "2.1.1", "2.0.0");
SemverTools.getLatestVersion(versionsList);

// returns "1.0.0-alpha"
List<String> versionsList = Arrays.asList("1.0.0", "1.0.0-alpha.beta", "1.0.0-alpha", "1.0.0-beta.11", "1.0.0-rc.1", "1.0.0-beta.2", "1.0.0-alpha.1", "1.0.0-beta");
SemverTools.getOldestVersion(versionsList);
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first [here](https://github.com/deanabadie/semver/issues)
to discuss what you would like to change.

Please make sure to update tests as appropriate.