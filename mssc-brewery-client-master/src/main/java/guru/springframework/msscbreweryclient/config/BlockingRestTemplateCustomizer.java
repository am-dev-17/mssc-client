package guru.springframework.msscbreweryclient.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final ApacheClientConfigurationProperties apacheClientConfigurationProperties;

    public BlockingRestTemplateCustomizer(ApacheClientConfigurationProperties apacheClientConfigurationProperties) {
        this.apacheClientConfigurationProperties = apacheClientConfigurationProperties;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connnectionManager = new PoolingHttpClientConnectionManager();
        connnectionManager.setMaxTotal(apacheClientConfigurationProperties.getMaxConnections());
        connnectionManager.setDefaultMaxPerRoute(apacheClientConfigurationProperties.getMaxPerRoute());

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(apacheClientConfigurationProperties.getConnectionTimeout())
                .setSocketTimeout(apacheClientConfigurationProperties.getSocketTimeout())
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connnectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
