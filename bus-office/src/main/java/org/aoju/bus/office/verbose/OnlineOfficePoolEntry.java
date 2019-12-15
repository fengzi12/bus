package org.aoju.bus.office.verbose;

import org.aoju.bus.core.lang.Assert;
import org.aoju.bus.core.lang.exception.InstrumentException;
import org.aoju.bus.core.utils.ClassUtils;
import org.aoju.bus.core.utils.StringUtils;
import org.aoju.bus.http.HttpClient;
import org.aoju.bus.office.builtin.MadeInOffice;
import org.aoju.bus.office.metric.AbstractOfficePoolEntry;
import org.aoju.bus.office.metric.RequestConfig;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static java.lang.Math.toIntExact;

/**
 * 负责执行通过不依赖于office安装的{@link OnlineOffice}提交的任务。
 * 它将向LibreOffice在线服务器发送转换请求，并等待任务完成或达到配置的任务执行超时.
 *
 * @author Kimi Liu
 * @version 3.6.6
 * @since JDK 1.8+
 */
public class OnlineOfficePoolEntry extends AbstractOfficePoolEntry {

    private final String connectionUrl;

    /**
     * 使用指定的配置创建新的池条目.
     *
     * @param connectionUrl 指向LibreOffice在线服务器的URL.
     * @param config        输入配置.
     */
    public OnlineOfficePoolEntry(
            final String connectionUrl,
            final OnlineOfficeEntryConfig config) {
        super(config);

        this.connectionUrl = connectionUrl;
    }

    private static File getFile(final URL url) {
        try {
            return new File(
                    new URI(StringUtils.replace(url.toString(), " ", "%20")).getSchemeSpecificPart());
        } catch (URISyntaxException ex) {
            return new File(url.getFile());
        }
    }

    private static File getFile(final String resourceLocation) throws FileNotFoundException {
        Assert.notNull(resourceLocation, "Resource location must not be null");
        if (resourceLocation.startsWith("classpath:")) {
            final String path = resourceLocation.substring("classpath:".length());
            final String description = "class path resource [" + path + "]";
            final ClassLoader cl = ClassUtils.getDefaultClassLoader();
            final URL url = (cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path));
            if (url == null) {
                throw new FileNotFoundException(
                        description + " cannot be resolved to absolute file path because it does not exist");
            }
            return getFile(url.toString());
        }
        try {
            return getFile(new URL(resourceLocation));
        } catch (MalformedURLException ex) {
            return new File(resourceLocation);
        }
    }

    /**
     * Https SSL证书
     *
     * @param X509TrustManager
     * @return SSLSocketFactory
     */
    private static SSLSocketFactory createTrustAllSSLFactory(X509TrustManager X509TrustManager) {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{X509TrustManager}, new SecureRandom());
            return sc.getSocketFactory();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 HostnameVerifier
     *
     * @return
     */
    private static HostnameVerifier createTrustAllHostnameVerifier() {
        return (hostname, session) -> true;
    }

    private String buildUrl(final String connectionUrl) throws MalformedURLException {
        final URL url = new URL(connectionUrl);
        final String path = url.toExternalForm().toLowerCase();
        if (StringUtils.endsWithAny(path, "lool/convert-to", "lool/convert-to/")) {
            return StringUtils.appendIfMissing(connectionUrl, "/");
        } else if (StringUtils.endsWithAny(path, "lool", "lool/")) {
            return StringUtils.appendIfMissing(connectionUrl, "/") + "convert-to/";
        }
        return StringUtils.appendIfMissing(connectionUrl, "/") + "lool/convert-to/";
    }

    @Override
    protected void doExecute(final MadeInOffice task) throws InstrumentException {
        try {
            final RequestConfig requestConfig =
                    new RequestConfig(
                            buildUrl(connectionUrl),
                            toIntExact(config.getTaskExecutionTimeout()),
                            toIntExact(config.getTaskExecutionTimeout()));
            task.execute(new OnlineConnect(new HttpClient(), requestConfig));

        } catch (IOException ex) {
            throw new InstrumentException("Unable to create the HTTP client", ex);
        }
    }

    @Override
    protected void doStart() throws InstrumentException {
        taskExecutor.setAvailable(true);
    }

    @Override
    protected void doStop() throws InstrumentException {
        // Nothing to stop here.
    }

    private KeyStore loadStore(
            final String store,
            final String storePassword,
            final String storeType,
            final String storeProvider)
            throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException,
            NoSuchProviderException {

        if (store != null) {
            Assert.notNull(storePassword, "The password of store {0} must not be null", store);

            KeyStore keyStore;

            final String type = storeType == null ? KeyStore.getDefaultType() : storeType;
            if (storeProvider == null) {
                keyStore = KeyStore.getInstance(type);
            } else {
                keyStore = KeyStore.getInstance(type, storeProvider);
            }

            try (FileInputStream instream = new FileInputStream(getFile(store))) {
                keyStore.load(instream, storePassword.toCharArray());
            }

            return keyStore;
        }
        return null;
    }

    private static class X509TrustManager implements javax.net.ssl.X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

}
