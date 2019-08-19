package com.sucl.convert.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sucl
 * @date 2019/5/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private String id;

    //    @JsonFormat
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String[] arr;
}
