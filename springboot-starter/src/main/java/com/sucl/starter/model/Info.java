package com.sucl.starter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sucl
 * @since 2019/8/19
 */
@Data
public class Info implements Serializable {

    private static final long serialVersionUID = 5609131302763045026L;

    private String id;

    private String name;

    private String content;
}
