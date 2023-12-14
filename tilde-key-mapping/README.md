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

Resources:
 - https://apple.stackexchange.com/questions/329085/tilde-and-plus-minus-%C2%B1-in-wrong-place-on-keyboard
 - https://karabiner-elements.pqrs.org/
