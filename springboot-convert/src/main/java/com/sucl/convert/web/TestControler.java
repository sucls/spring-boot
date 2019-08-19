package com.sucl.convert.web;

import com.sucl.convert.entity.Record;
import com.sucl.convert.entity.Request;
import com.sucl.convert.method.RequestData;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author sucl
 * @since 2019/7/26
 */
@RestController
@RequestMapping
public class TestControler {

    /**
     * @param date
     * @return
     */
    @GetMapping
    public Object test(@RequestParam("date")
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") // 方法②
                               Date date) {
        return date;
    }

    //方法①
//    @InitBinder
//    public void initBinding(WebDataBinder dataBinder){
//        dataBinder.registerCustomEditor(Date.class, new DatePropertyEditor() );
//    }

    /**
     * arr=1&arr=2
     *
     * @param arr
     * @return
     */
    @PostMapping("/array")
    public Object array(String[] arr) {
        return arr;
    }

    @PostMapping("/record")
    public Object record(@RequestBody Record record) {
        return record;
    }

    @PostMapping("/request")
    public Object request(@RequestData Request request) {
        return request;
    }

}
