package com.vparking.cloud.common.http.rest.interceptor;

import com.vparking.cloud.common.utils.LoggingUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by 44032090 on 2017/7/6.
 */
public class RestRequestInterceptorForLog implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        log(request,body,response);

        return response;
    }

    private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append("URL :" + request.getURI().toString());
        sb.append(" ,Header : " + request.getHeaders().toString() + ",");
        sb.append("Request Body : " + new String(body));
        sb.append("]");
        LoggingUtils.error(sb.toString());
    }
}