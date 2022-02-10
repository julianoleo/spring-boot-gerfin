package com.juliano.gerfin.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LogRequest {

    private HttpHeaders headers;
    private String metodo;
    private String uri;
    private Map<String, String[]> parametros;

    public LogRequest(HttpHeaders headers2, String method, String requestURI, Map<String, String[]> parameterMap) { }
}
