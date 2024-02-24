package com.rslakra.microservice.yatrasuite.framework.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 8/3/23 8:14 PM
 */
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    /**
     * @param httpResponse
     * @return
     * @throws IOException
     */
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    /**
     * @param httpResponse
     * @throws IOException
     */
    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
            // throw new HttpClientErrorException(httpResponse.getStatusCode());
            throw new HttpServerErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            // if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            //                throw new RuntimeException(httpResponse.getStatusText());
            // }
            throw new HttpClientErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
        }
    }
}
