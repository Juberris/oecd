package cl.sii.cts2.data.export.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

@Slf4j
public class Http {


    public <T extends HttpRequestBase> T wrap(T request) {
        if (log.isTraceEnabled()) {
            log.trace("--URI--");
            log.trace(String.valueOf(request.getURI()));
            log.trace("--Method--");
            log.trace(request.getMethod());
            log.trace("--Request Headers--");
            Header[] headers = request.getAllHeaders();
            if (headers != null) {
                for (Header header : headers) {
                    log.trace(header.getName() + "=" + header.getValue());
                }
            }
        }
        return request;
    }

    public String process(HttpResponse response) throws IOException {
        if (log.isTraceEnabled()) {
            log.trace("--Status Line--");
            StatusLine statusLine = response.getStatusLine();
            log.trace(String.valueOf(statusLine));
            log.trace("--Response Headers--");
            Header[] headers = response.getAllHeaders();
            if (headers != null) {
                for (Header header : headers) {
                    log.trace(header.getName() + "=" + header.getValue());
                }
            }
            log.trace("--Response Body--");
        }
        HttpEntity entity = response.getEntity();
        try {
            String body = IOUtils.toString(entity.getContent());
            if (log.isTraceEnabled()) {
                log.trace(body);
            }
            return body;
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    public void process(HttpResponse response, DownloadCallback callback) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        if (log.isTraceEnabled()) {
            log.trace("--Status Line--");
            log.trace(String.valueOf(statusLine));
            log.trace("--Response Headers--");
            Header[] headers = response.getAllHeaders();
            if (headers != null) {
                for (Header header : headers) {
                    log.trace(header.getName() + "=" + header.getValue());
                }
            }
            log.trace("--Response Body--");
        }
        HttpEntity entity = response.getEntity();
        try {
            callback.call(statusLine.getStatusCode(), statusLine.getReasonPhrase(), entity.getContent());
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    public static interface DownloadCallback {
        void call(int code, String msg, InputStream data) throws IOException;
    }

    public static void run(String url, HttpProxeable proxeable) {
        DefaultHttpClient client = create(url);
        try {
            proxeable.run(client);
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando proxeable de URL [" + url + "]", e);
        } finally {
            HttpClientUtils.closeQuietly(client);
        }
    }

    public static interface HttpProxeable {
        void run(DefaultHttpClient client) throws Exception;
    }

    public static DefaultHttpClient create(String url) {
        return create(url, null);
    }

    public static DefaultHttpClient create(String url, HttpParams httpParams) {
        if (httpParams == null) {
            httpParams = new BasicHttpParams();
        }
        String hosts = System.getProperty("https.proxyHost");
        String ports = System.getProperty("https.proxyPort");
        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");
        try {
            URL urlObj = new URL(url);
            DefaultHttpClient client = new DefaultHttpClient(httpParams);
            if (hosts != null && ports != null && "https".equalsIgnoreCase(urlObj.getProtocol())) {
                HttpHost proxy = new HttpHost(hosts, Integer.parseInt(ports));
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            } else if (host != null && port != null && "http".equalsIgnoreCase(urlObj.getProtocol())) {
                HttpHost proxy = new HttpHost(host, Integer.parseInt(port));
                client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            }
            String trustAll = System.getProperty("https.trustAll");
            if (trustAll != null && trustAll.equalsIgnoreCase("true") && "https".equalsIgnoreCase(urlObj.getProtocol())) {
                //TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
                //SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                //client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", urlObj.getPort(), sf));
                SSLContext sslContext = SSLContext.getInstance("SSL");
                // set up a TrustManager that trusts everything
                sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                } }, new SecureRandom());
                SSLSocketFactory sf = new SSLSocketFactory(sslContext);
                Scheme httpsScheme = new Scheme("https", 443, sf);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(httpsScheme);
                client.getConnectionManager().getSchemeRegistry().register(httpsScheme);
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException("Error construyendo HttpClient de URL [" + url + "]", e);
        }
    }
}

