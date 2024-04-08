# SBOM Analysis
 - Dependency-Track - https://dependencytrack.org/
 -- http://localhost:8080/dashboard
 - CycloneDX CLI - https://github.com/CycloneDX/cyclonedx-cli

## Convert SPDX to CycloneDX
```
~/Downloads/cyclonedx-osx-x64 convert --input-file ~/Downloads/abc.json --output-file ~/Downloads/abc-cyclone-dx.json --input-format spdxjson --output-format json
```