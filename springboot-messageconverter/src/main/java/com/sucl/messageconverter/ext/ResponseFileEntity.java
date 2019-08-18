package com.sucl.messageconverter.ext;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author sucl
 * @since 2019/8/7
 */
@Data
public class ResponseFileEntity implements Serializable {

    private InputStream in;
}
