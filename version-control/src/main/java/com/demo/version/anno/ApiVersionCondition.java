package com.demo.version.anno;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    private String apiVersion;

    public ApiVersionCondition(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition apiVersionCondition) {
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        String version = httpServletRequest.getHeader("api-version");
        if (StringUtils.isNotEmpty(version)) {
            if (version.compareTo(this.apiVersion) >= 0) {
                return this;
            } else {
                return null;
            }
        } else {
            return this;
        }
    }

    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        return apiVersionCondition.getApiVersion().compareTo(this.apiVersion);
    }

    public String getApiVersion() {
        return apiVersion;
    }

}
