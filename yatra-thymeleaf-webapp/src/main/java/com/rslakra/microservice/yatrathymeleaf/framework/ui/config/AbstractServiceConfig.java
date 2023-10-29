package com.rslakra.microservice.yatrathymeleaf.framework.ui.config;

import com.rslakra.frameworks.core.BeanUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractServiceConfig {

    private String baseServiceUrl;
    private int port;
    @Value("${uiPrefix}")
    private String uiPrefix;
    @Value("${apiPrefix}")
    private String apiPrefix;
    @Value("${restPrefix}")
    private String restPrefix;

    /**
     * @param baseServiceUrl
     * @param port
     */
    public AbstractServiceConfig(String baseServiceUrl, int port) {
        this.baseServiceUrl = baseServiceUrl;
        this.port = port;
    }

    /**
     * @param prefix
     * @param suffix
     * @return
     */
    private String getServiceUrl(String prefix, String suffix) {
        if (BeanUtils.isNotEmpty(prefix, suffix)) {
            return String.format("http://%s:%s%s%s", baseServiceUrl, port, prefix, suffix);
        } else if (BeanUtils.isEmpty(prefix) && BeanUtils.isNotEmpty(suffix)) {
            return String.format("http://%s:%s%s", baseServiceUrl, port, suffix);
        } else if (BeanUtils.isNotNull(prefix) && BeanUtils.isEmpty(suffix)) {
            return String.format("http://%s:%s%s", baseServiceUrl, port, prefix);
        } else {
            return String.format("http://%s:%s", baseServiceUrl, port);
        }
    }

    /**
     * @param suffix
     * @return
     */
    public String getUiUrl(String suffix) {
        return getServiceUrl(uiPrefix, suffix);
    }

    /**
     * @param suffix
     * @return
     */
    public String getApiUrl(String suffix) {
        return getServiceUrl(apiPrefix, suffix);
    }

    /**
     * @param suffix
     * @return
     */
    public String getRestUrl(String suffix) {
        return getServiceUrl(restPrefix, suffix);
    }
}
