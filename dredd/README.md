# Dredd

Dredd (https://github.com/apiaryio/dredd)  is HTTP API Testing Framework.

Dredd reads your API description and step by step validates whether your API implementation replies with responses as they are described in the documentation.

## Usage
```bash
node app.js

# API Blueprint definition
dredd api-description.apib http://127.0.0.1:3000

# Swagger definition
dredd api-description.yml http://127.0.0.1:3000
```