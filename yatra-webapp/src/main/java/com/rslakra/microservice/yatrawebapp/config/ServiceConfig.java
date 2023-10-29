package com.rslakra.microservice.yatrawebapp.config;

import com.rslakra.frameworks.core.BeanUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
public abstract class ServiceConfig {

    private String baseServiceUrl;
    private int port;
    @Value("${restPrefix}")
    private String restPrefix;

    /**
     * @param baseServiceUrl
     * @param port
     */
    public ServiceConfig(String baseServiceUrl, int port) {
        this.baseServiceUrl = baseServiceUrl;
        this.port = port;
    }

    /**
     * @param suffix
     * @return
     */
    public String getServiceUrl(String suffix) {
        if (BeanUtils.isEmpty(suffix)) {
            return String.format("http://%s:%s", baseServiceUrl, port);
        } else {
            return String.format("http://%s:%s%s%s", baseServiceUrl, port, restPrefix, suffix);
        }
    }
}
