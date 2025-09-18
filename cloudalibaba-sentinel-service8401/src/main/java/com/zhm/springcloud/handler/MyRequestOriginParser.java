package com.zhm.springcloud.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;

public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");

    }
}
