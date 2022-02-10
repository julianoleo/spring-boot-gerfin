package com.juliano.gerfin.logs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"timestamp", "application", "level", "endpoint", "request", "response"})
public class LogRequestResponse {

    private LogRequest request;
    private Object response;
    private String message;

    @JsonProperty("application")
    private String apiName;

    private LogType level;
    private String timestamp;

    public LogRequestResponse(String timestamp, String apiName, LogType level, LogRequest request, Object response, String message) {
        this.request = request;
        this.response = response;
        this.message = message;
        this.apiName = apiName;
        this.level = level;
        this.timestamp = timestamp;
    }

    public LogRequest getRequest() {
        return request;
    }

    public void setRequest(LogRequest request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("application")
    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public LogType getLevel() {
        return level;
    }

    public void setLevel(LogType level) {
        this.level = level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
