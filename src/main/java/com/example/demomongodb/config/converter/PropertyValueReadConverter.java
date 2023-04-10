package com.example.demomongodb.config.converter;

import com.example.demomongodb.domain.PropertyConfig;
import com.example.demomongodb.domain.TimeSpan;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author: Michael
 * @date: 9/21/2022 12:59 PM
 */
public class PropertyValueReadConverter implements Converter<Document, PropertyConfig> {

    private static Logger logger = LoggerFactory.getLogger(PropertyValueReadConverter.class);

    @Override
    public PropertyConfig convert(Document source) {
        logger.info(source + " enter....");
        PropertyConfig propertyConfig = new PropertyConfig();
        String property_type = source.getString("property_type");
        String property_name = source.getString("property_name");
        Document property_value = (Document) source.get("property_value");
        if (property_type.equals("GREEN_ZONE")) {
            Date start_ts = property_value.getDate("start_ts");
            Date end_ts = property_value.getDate("end_ts");
            TimeSpan timeSpan=new TimeSpan(start_ts,end_ts);
            propertyConfig.setPropertyValue(timeSpan);
        } else {
            propertyConfig.setPropertyValue(property_value);
        }
        propertyConfig.setPropertyType(property_type);
        propertyConfig.setPropertyName(property_name);
        return propertyConfig;
    }
}
