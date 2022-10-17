# Get info from Java code about files in direcory, properties, etc.

```java
LOGGER.info("appDir: " + appDir);
try (Stream<Path> stream = Files.walk(appDir.toPath())) {
    stream.forEach(LOGGER::info);
}

LOGGER.info("PROPERTIES");
LinkedHashMap<String, String> collect = System.getProperties()
        .entrySet().stream()
        .collect(Collectors.toMap(k -> (String) k.getKey(), e -> (String) e.getValue()))
        .entrySet().stream().sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
collect.forEach((k, v) -> LOGGER.info(k + ":" + v));
```