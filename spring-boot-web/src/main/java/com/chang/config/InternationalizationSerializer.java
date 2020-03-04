package com.chang.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InternationalizationSerializer extends StdScalarSerializer<Object> {

    @Autowired
    private MessageSource messageSource;

    public InternationalizationSerializer() {
        super(String.class, false);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value instanceof String) {
            String msg = messageSource.getMessage((String) value, null, (String) value, LocaleContextHolder.getLocale());
            gen.writeString(msg);
        } else if (value instanceof Enum) {
            String key = value.getClass().getSimpleName() + "." + ((Enum) value).name();
            String msg = messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
            gen.writeString(msg);
        }
    }

}
