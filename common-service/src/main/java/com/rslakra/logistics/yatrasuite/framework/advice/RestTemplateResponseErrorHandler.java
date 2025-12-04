package com.rslakra.logistics.yatrasuite.framework.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        HttpStatusCode statusCode = httpResponse.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    /**
     * @param httpResponse
     * @throws IOException
     */
    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        HttpStatusCode statusCode = httpResponse.getStatusCode();
        if (statusCode.is5xxServerError()) {
            // handle SERVER_ERROR
            throw new HttpServerErrorException((HttpStatus) statusCode, httpResponse.getStatusText());
        } else if (statusCode.is4xxClientError()) {
            // handle CLIENT_ERROR
            throw new HttpClientErrorException((HttpStatus) statusCode, httpResponse.getStatusText());
        }
    }
}
