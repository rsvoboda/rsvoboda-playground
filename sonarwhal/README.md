# Sonarwhal - A linting tool for the web
Sonarwhal scans sites and identifies weak areas.

## Online use
Just go to https://sonarwhal.com/ and enter site URL which you want to check.

## Installation
As root or using sudo execute this:
```bash
npm install -g --engine-strict --unsafe-perm=true sonarwhal
```
`--unsafe-perm=true` workaround to get around permission denied exception.

Some details in https://github.com/sonarwhal/sonarwhal/issues/600 or https://github.com/npm/npm/issues/17268


As the user run `sonarwhal --init` or save my example config into ~/.sonarwhalrc

## WildFly 11.0.0 check
```bash
sonarwhal http://127.0.0.1:8080/

sonarwhal http://127.0.0.1:9990/error/index.html

sonarwhal http://127.0.0.1:9990/console/App.html#home
```

## My ~/.sonarwhalrc
 * increased value for waitFor (so I can type username and password) + rulesTimeout
 * 2 formatters to have details and summary at the end
```json
{
    "browserslist": [],
    "connector": {
        "name": "chrome",
        "options": {
            "waitFor": 10000,
            "defaultProfile": false
        }
    },
    "formatters": [
        "stylish", "summary"
    ],
    "ignoredUrls": [],
    "rules": {
        "amp-validator": "off",
        "apple-touch-icons": "error",
        "axe": "error",
        "content-type": "error",
        "disown-opener": "error",
        "highest-available-document-mode": "error",
        "html-checker": "error",
        "http-cache": "error",
        "image-optimization-cloudinary": "off",
        "manifest-app-name": "error",
        "manifest-exists": "error",
        "manifest-file-extension": "error",
        "manifest-is-valid": "error",
        "meta-charset-utf-8": "error",
        "meta-viewport": "error",
        "no-disallowed-headers": "error",
        "no-friendly-error-pages": "error",
        "no-html-only-headers": "error",
        "no-http-redirects": "error",
        "no-protocol-relative-urls": "error",
        "no-vulnerable-javascript-libraries": "error",
        "ssllabs": "off",
        "strict-transport-security": "error",
        "validate-set-cookie-header": "error",
        "x-content-type-options": "error"
    },
    "rulesTimeout": 1200000
}
```

##