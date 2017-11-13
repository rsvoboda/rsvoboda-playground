#JAX-RS 2.0 versus 2.1 - diff of specification
```bash
pdftotext jaxrs-2_1-final-spec.pdf > jaxrs-2_1-final-spec.txt
pdftotext jsr339-jaxrs-2.0-final-spec.pdf > jsr339-jaxrs-2.0-final-spec.txt

SPEC_FILE=jsr339-jaxrs-2.0-final-spec
sed  "/May 22, 2013/d" ${SPEC_FILE}.txt | sed  "/July 13, 2017/d" | sed  "/^JAX-RS$/d" | grep -E -v '^[0-9]+$' > ${SPEC_FILE}-tuned.txt

SPEC_FILE=jaxrs-2_1-final-spec
sed  "/May 22, 2013/d" ${SPEC_FILE}.txt | sed  "/July 13, 2017/d" | sed  "/^JAX-RS$/d" | grep -E -v '^[0-9]+$' > ${SPEC_FILE}-tuned.txt

meld jsr339-jaxrs-2.0-final-spec-tuned.txt jaxrs-2_1-final-spec-tuned.txt
```

## JAX-RS 2.1 presentations
 * https://www.slideshare.net/delabassee/jaxrs-21-reloaded
 * https://www.slideshare.net/delabassee/rest-in-an-async-world
 * https://abhirockzz.gitbooks.io/rest-assured-with-jaxrs/content/JAX-RS%202.1%20-%20the%20latest%20&%20greatest.html
