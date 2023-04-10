package com.example.demomongodb.repository;

import java.util.Arrays;

/**
 * @author: Michael
 * @date: 9/14/2022 4:37 PM
 */
public enum DocumentumTypeEnum {

    PPT("ppt"),
    WORD("doc");

    private String value;

    DocumentumTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contain(String ext){
        return Arrays.stream(DocumentumTypeEnum.values()).anyMatch(f->f.name().equalsIgnoreCase(ext));
    }

    public static void main(String[] args) {
        String ext="WORD";
        boolean contain = contain(ext);
        System.out.println(contain);

    }

}

