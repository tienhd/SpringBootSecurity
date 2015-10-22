package us.yoloz.config;


import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 2/24/15
 * Time: 12:01 PM
 */
@Configuration
public class TomcatConfiguration
{
    @Value("${keystore.file}")
    private String keystoreFile;

    @Value("${keystore.pass}")
    private String keystorePass;

    @Value("${server.port}")
    private int serverPort;

    @Bean
    public EmbeddedServletContainerCustomizer servletContainerCustomizer()
    {
        return new EmbeddedServletContainerCustomizer()
        {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer servletContainer)
            {
                TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = ((TomcatEmbeddedServletContainerFactory) servletContainer);
                tomcatEmbeddedServletContainerFactory.addConnectorCustomizers(
                        new TomcatConnectorCustomizer()
                        {
                            @Override
                            public void customize(Connector connector)
                            {
                                AbstractHttp11Protocol httpProtocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
                                httpProtocol.setCompression("on");
                                httpProtocol.setCompressionMinSize(256);
                                String mimeTypes = httpProtocol.getCompressableMimeTypes();
                                String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE;
                                httpProtocol.setCompressableMimeTypes(mimeTypesWithJson);
                            }
                        }
                );

                tomcatEmbeddedServletContainerFactory.addConnectorCustomizers(
                        new TomcatConnectorCustomizer()
                        {
                            @Override
                            public void customize(Connector connector)
                            {
                                connector.setPort(serverPort);
                                connector.setSecure(true);
                                connector.setScheme("https");
                                Http11NioProtocol httpsProtocol = (Http11NioProtocol) connector.getProtocolHandler();
                                httpsProtocol.setSSLEnabled(true);
                                httpsProtocol.setKeystoreFile(keystoreFile);
                                httpsProtocol.setKeystorePass(keystorePass);
                                httpsProtocol.setKeystoreType("PKCS12");

                                httpsProtocol.setCompression("on");
                                httpsProtocol.setCompressionMinSize(256);
                                String mimeTypes = httpsProtocol.getCompressableMimeTypes();
                                String mimeTypesWithJson = mimeTypes + "," + MediaType.APPLICATION_JSON_VALUE;
                                httpsProtocol.setCompressableMimeTypes(mimeTypesWithJson);
                            }
                        });
            }
        };
    }
}
