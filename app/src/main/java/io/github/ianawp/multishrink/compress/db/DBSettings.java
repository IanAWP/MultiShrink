//package io.github.ianawp.multishrink.compress.db;
//
//import org.greenrobot.greendao.annotation.Convert;
//import org.greenrobot.greendao.annotation.Entity;
//import org.greenrobot.greendao.converter.PropertyConverter;
//
//import io.github.ianawp.multishrink.compress.JobSettings;
//import io.github.ianawp.multishrink.compress.OutputFormat;
//
///**
// * Created by IanAWP on 21/04/2017.
// */
//
//@Entity( nameInDb = "JOB_SETTINGS")
//public class DBSettings implements JobSettings {
//    @Convert(converter = OutputFormatConverter.class, columnType = String.class)
//    private OutputFormat format;
//
//
//    private int resolution;
//
//    @Override
//    public OutputFormat getFormat() {
//        return format;
//    }
//
//    @Override
//    public int getResolution() {
//        return resolution;
//    }
//
//
//    static class OutputFormatConverter implements PropertyConverter<OutputFormat, String> {
//        @Override
//        public OutputFormat convertToEntityProperty(String databaseValue) {
//            return OutputFormat.valueOf(databaseValue);
//        }
//
//        @Override
//        public String convertToDatabaseValue(OutputFormat entityProperty) {
//            return entityProperty.name();
//        }
//    }
//}
