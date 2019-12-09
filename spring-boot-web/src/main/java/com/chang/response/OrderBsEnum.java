package com.chang.response;

public enum OrderBsEnum {

    Short("卖出"),
    Long("买入"),
    Sell(""),
    Cover("");

    private String message;

    OrderBsEnum(String message) {
        this.message = message;
    }
}
