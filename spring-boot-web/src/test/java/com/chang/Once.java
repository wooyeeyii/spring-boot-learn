package com.chang;

import java.math.BigDecimal;

public class Once {

    public static void main(String[] args) {

        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.65");
        System.out.println(df.format(122220.123456789));
        System.out.println(df.format(new BigDecimal(122220.123456789)));


        BigDecimal b = new BigDecimal(122220.123456789);
        BigDecimal f1 = b.setScale(-2, BigDecimal.ROUND_FLOOR);
        System.out.println(f1);
    }

}
