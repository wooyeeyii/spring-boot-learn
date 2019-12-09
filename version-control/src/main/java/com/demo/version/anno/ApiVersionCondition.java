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
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }

    /**
     * 根据头部信息查找匹配到的筛选条件
     */
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

    /**
     * 不同筛选条件比较,用于排序
     */
    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        // 优先匹配最新的版本号
        return apiVersionCondition.getApiVersion().compareTo(this.apiVersion);
    }

    public String getApiVersion() {
        return apiVersion;
    }


}
