# wrk2 on macOS

 - git clone https://github.com/giltene/wrk2.git
 - brew install openssl
 - adjust Makefile
```diff
diff --git a/Makefile b/Makefile
index a537a68..debeae4 100644
--- a/Makefile
+++ b/Makefile
@@ -1,4 +1,4 @@
-CFLAGS  := -std=c99 -Wall -O2 -D_REENTRANT
+CFLAGS  := -std=c99 -Wall -O2 -D_REENTRANT -Wno-implicit-function-declaration
 LIBS    := -lpthread -lm -lcrypto -lssl

 TARGET  := $(shell uname -s | tr '[A-Z]' '[a-z]' 2>/dev/null || echo unknown)
 ```
  - sudo make
  - ${PWD}/wrk --help