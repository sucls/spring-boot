package com.sucl.convert.entity;

import lombok.Data;

/**
 * @author sucl
 * @since 2019/8/8
 */
@Data
public class Request {

    private String id;
    private String method;
    private String date;
    private String name;
    private String description;
}
