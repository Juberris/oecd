SET COUNTRY=%1
openssl req -x509 -sha256 -newkey rsa:4096 -keyout CTS%COUNTRY%.key -out CTS%COUNTRY%.crt -days 1024 -nodes -subj "/CN=cts.oecd.sii.%COUNTRY%/O=cts.oecd.sii.%COUNTRY%" -addext "subjectAltName = DNS:cts.oecd.sii.%COUNTRY%"
openssl pkcs12 -export -out CTS%COUNTRY%.p12 -inkey CTS%COUNTRY%.key -in CTS%COUNTRY%.crt -password pass:testcts
