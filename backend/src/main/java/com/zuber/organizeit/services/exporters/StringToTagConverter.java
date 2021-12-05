package com.zuber.organizeit.services.exporters;

import com.zuber.organizeit.Model.Tag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToTagConverter
        implements Converter<String, Tag> {

    @Override
    public Tag convert(String from) {
        return new Tag(null, List.of(from.split(",")));
    }
}
