import javax.net.ssl.*;
import javax.naming.InvalidNameException;
import javax.naming.ldap.*;

import java.security.cert.X509Certificate;

class MyTLSFileClient {
    static String getCommonName(X509Certificate cert) throws InvalidNameException {
        String name = cert.getSubjectX500Principal().getName();
        LdapName ln = new LdapName(name);
        String cn = null;
        for(Rdn rdn: ln.getRdns()){
            if("CN".equalsIgnoreCase(rdn.getType())) {
                cn = rdn.getValue().toString();
            }
        }

        return cn;
    }

    public static void main(String[] args) {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.startHandshake();
            SSLSession sesh = socket.getSession();
            X509Certificate cert = (X509Certificate)sesh.getPeerCertificates()[0];
            System.out.print("Certificate Name: " + getCommonName(cert));
            socket.close();
        } catch (Exception e) {

        }
    }
}