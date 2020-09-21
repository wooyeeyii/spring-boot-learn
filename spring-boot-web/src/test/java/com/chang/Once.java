package com.chang;

import java.math.BigDecimal;

public class Once {

    public static void main(String[] args) {

        String format1 = "_@_%s@-@";
        String format2 = " _#_%s#-#";
        String result = "";
        result += String.format(format1, new BigDecimal("0.0038345830"));
        result += String.format(format2, "%");
        result += String.format(format1, "~");
        BigDecimal big = new BigDecimal("0.393480000");
        result += String.format(format1, big);
        result += String.format(format2, "%");
        System.out.println(result);
    }

}
