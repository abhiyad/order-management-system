package com.catalogue.userorderservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<Long>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Long> orderList) {
        List<String> stringList = new ArrayList<>();
        for(Long orderID : orderList){
            stringList.add(String.valueOf(orderID));
        }
        return String.join(SPLIT_CHAR, stringList);
    }

    @Override
    public List<Long> convertToEntityAttribute(String string) {
        List<String >stringSequence = Arrays.asList(string.split(SPLIT_CHAR));
        List<Long> longSequence = new ArrayList<Long>();
        for (String item : stringSequence ) {
            longSequence.add(Long.valueOf(item));
        }
        return longSequence;
    }
}