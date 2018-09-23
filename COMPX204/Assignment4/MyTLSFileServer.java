import java.io.*;
import java.security.KeyStore;

import javax.net.ssl.*;

class MyTLSFileServer {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            SSLContext ctx = SSLContext.getInstance("TLS");
            KeyStore ks = KeyStore.getInstance("JKS");
            char[] passphrase = "p@ssw0rd".toCharArray();
            ks.load(new FileInputStream("server.jks"), passphrase);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, passphrase);
            ctx.init(kmf.getKeyManagers(), null, null);
            SSLServerSocketFactory ssf = ctx.getServerSocketFactory();
            SSLServerSocket ss = (SSLServerSocket)ssf.createServerSocket(port);
            String EnabledProtocols[] = {"TLSv1.2", "TLSv1.1"};
            ss.setEnabledProtocols(EnabledProtocols);
            SSLSocket s = (SSLSocket)ss.accept();
            
            InputStream in = s.getInputStream();
            System.out.print(in.read());
        
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }
}