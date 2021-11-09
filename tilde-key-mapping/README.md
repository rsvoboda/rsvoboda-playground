# Tilde key mapping on Logitech MX Keys

Change tilde mapping:
```bash
hidutil property --set '{"UserKeyMapping":[
{"HIDKeyboardModifierMappingSrc":0x700000035,"HIDKeyboardModifierMappingDst":0x700000064},
{"HIDKeyboardModifierMappingSrc":0x700000064,"HIDKeyboardModifierMappingDst":0x700000035}
]}'
```

Reset the mapping:
```bash
hidutil property --set '{"UserKeyMapping":[]}'
```