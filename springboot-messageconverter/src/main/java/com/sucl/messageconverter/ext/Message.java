package com.sucl.messageconverter.ext;

import lombok.Data;

import java.util.Date;

/**
 * @author sucl
 * @since 2019/8/7
 */
@Data
public class Message {

    private String id;
    private String info;
    private Date date;
}
