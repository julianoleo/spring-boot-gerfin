package com.juliano.gerfin.logs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LogType {

    INFO("INFO"),
    DEBUG("DEBUG"),
    WARN("warn"),
    ERROR("ERROR");

    @Getter
    private final String value;

}
