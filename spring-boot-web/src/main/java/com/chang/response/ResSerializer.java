package com.chang.response;

import com.chang.service.TestService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
/*public class ResSerializer extends JsonSerializer<Res> {

    @Autowired
    private TestService service;

    @Override
    public void serialize(Res value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        service.test();

        gen.writeStartObject();
//        gen.writeFieldName("orderBs");
//        gen.writeString("自定义");
        gen.writeFieldName("de");
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        gen.writeNumber(value.getDe());
        gen.writeObjectField("flag", value.getFlag());
        gen.writeEndObject();
    }
}*/

public class ResSerializer {

}
