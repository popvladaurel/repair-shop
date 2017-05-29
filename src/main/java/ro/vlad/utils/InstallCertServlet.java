/*
 * Copyright 2006 Sun Microsystems, Inc.  All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **/
/**
 * Originally from:
 * http://blogs.sun.com/andreas/resource/InstallCert.java
 *
 * Modified into a Servlet
 * source: https://github.com/escline/InstallCert
 **/
package ro.vlad.utils;

import javax.net.ssl.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static ro.vlad.persistence.JpaListener.LOGGER;
import static ro.vlad.utils.Modal.Color.GREEN;
import static ro.vlad.utils.Modal.setMessage;

/**
 * Class used to add the server's SSL certificate to our own KeyStore
 */
@WebServlet(urlPatterns = "/InstallCertServlet", name = "Get the keys to the kingdom of ANAF")
public class InstallCertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = "anaf.ro";
        String pass = "changeit";
        Integer port = 443;
        File file = new File(getClass().getResource("/appKeyStore").getFile());
        LOGGER.info("Loading KeyStore " + file + "...");
        try (InputStream inputStream = new FileInputStream(file)){
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(inputStream, pass.toCharArray());
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            X509TrustManager defaultTrustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
            SavingTrustManager savingTrustManager = new SavingTrustManager(defaultTrustManager);
            context.init(null, new TrustManager[]{savingTrustManager}, null);
            SSLSocketFactory factory = context.getSocketFactory();
            LOGGER.info("Opening connection to " + host + "...");
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.setSoTimeout(10000);
            try {
                LOGGER.info("Starting SSL handshake...");
                socket.startHandshake();
                socket.close();}
            catch (SSLException e) {}
            X509Certificate[] chain = savingTrustManager.chain;
            if (chain == null) {
                LOGGER.info("Could not obtain server certificate chain");
                return;}
            LOGGER.info("Server sent " + chain.length + " certificate(s):");
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            for (int i = 0; i < chain.length; i++) {
                X509Certificate cert = chain[i];
                LOGGER.info(" " + (i + 1) + " Subject " + cert.getSubjectDN());
                LOGGER.info("   Issuer  " + cert.getIssuerDN());
                sha1.update(cert.getEncoded());
                LOGGER.info("   sha1    " + toHexString(sha1.digest()));
                md5.update(cert.getEncoded());
                LOGGER.info("   md5     " + toHexString(md5.digest()));
                String alias = host + "-" + (i + 1);
                keyStore.setCertificateEntry(alias, cert);
                try (OutputStream out = new FileOutputStream(file)) {
                    keyStore.store(out, pass.toCharArray());}
                LOGGER.info("Added certificate to keystore using alias '" + alias + "'");}}
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();}
        catch (CertificateException e) {
            e.printStackTrace();}
        catch (KeyStoreException e) {
            e.printStackTrace();}
        catch (KeyManagementException e) {
            e.printStackTrace();}
        setMessage(req, new Modal(GREEN, "ANAF certificate installed!", "/jsp/company/company.jsp"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);}

    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int b : bytes) {
            b &= 0xff;
            sb.append(HEXDIGITS[b >> 4]);
            sb.append(HEXDIGITS[b & 15]);
            sb.append(' '); }
        return sb.toString();}

    private static class SavingTrustManager implements X509TrustManager {
        private final X509TrustManager trustManager;
        private X509Certificate[] chain;
        SavingTrustManager(X509TrustManager trustManager) {
            this.trustManager = trustManager; }

        public X509Certificate[] getAcceptedIssuers() {
	   
	    /** 
	     * This change has been done due to the following resolution advised for Java 1.7+
		http://infposs.blogspot.kr/2013/06/installcert-and-java-7.html
       	     **/ 
	    
	    return new X509Certificate[0];	
            //throw new UnsupportedOperationException();
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            throw new UnsupportedOperationException();}

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            this.chain = chain;
            trustManager.checkServerTrusted(chain, authType); }}
}
