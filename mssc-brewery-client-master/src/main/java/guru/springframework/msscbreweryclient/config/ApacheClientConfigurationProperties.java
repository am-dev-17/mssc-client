package guru.springframework.msscbreweryclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ApacheClientConfigurationProperties {

    @Value("${apache.client.maxConnections}")
    private Integer maxConnections;
    @Value("${apache.client.maxPerRoute}")
    private Integer maxPerRoute;
    @Value("${apache.client.connectionTimeout}")
    private Integer connectionTimeout;
    @Value("${apache.client.socketTimeout}")
    private Integer socketTimeout;

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    public Integer getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(Integer maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
}
