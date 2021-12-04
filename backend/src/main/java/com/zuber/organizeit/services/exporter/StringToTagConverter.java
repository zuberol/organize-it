package com.zuber.organizeit.services.exporter;

import com.zuber.organizeit.Model.Tag;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class StringToTagConverter
        implements Converter<String, Tag> {

    @Override
    public Tag convert(String from) {
        return new Tag(null, List.of(from.split(",")));
    }
}
