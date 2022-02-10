package com.juliano.gerfin.logs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Logs {
    private static final Logger LOGGER = LoggerFactory.getLogger(Logs.class);
    private static final String HEADER_APPID = "API-REST";
    private static final String SIGLA_APP = "ApiMysql";

    public LogRequestResponse logRequest(
            HttpServletRequest request,
            HttpHeaders headers,
            Object resposta,
            LogType level,
            String mensagem ) {

        if(headers != null) headers = filterHeader(headers);

        LogRequestResponse logJson = new LogRequestResponse(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()),
                SIGLA_APP,
                level,
                new LogRequest(headers, request.getMethod(), request.getRequestURI(), request.getParameterMap()),
                resposta,
                mensagem);
        logByLevel(level, logJson);
        return logJson;
    }

    public void logByLevel(LogType level, LogRequestResponse logJson){
        String log = jsonfy(logJson);
        switch (level) {
            case INFO:
                LOGGER.info("{}", log);
                break;
            case WARN:
                LOGGER.warn("{}", log);
                break;
            case ERROR:
                LOGGER.error("{}", log);
                break;
            case DEBUG:
                LOGGER.debug("{}", log);
                break;
        }
    }

    private String jsonfy(LogRequestResponse logJson){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(logJson);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public HttpHeaders filterHeader(HttpHeaders headers) {
        List<String> appid = headers.get(HEADER_APPID);
        HttpHeaders filterHeaders = new HttpHeaders();
        if (appid != null)
            filterHeaders.set(HEADER_APPID, appid.get(0));
        return filterHeaders;
    }
}
