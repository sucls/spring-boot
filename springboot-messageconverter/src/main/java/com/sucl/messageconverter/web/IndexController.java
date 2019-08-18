package com.sucl.messageconverter.web;

import com.sucl.messageconverter.ext.Message;
import com.sucl.messageconverter.ext.ResponseFile;
import com.sucl.messageconverter.ext.ResponseFileEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author sucl
 * @since 2019/8/2
 */
@Controller
//@RestController
@RequestMapping
public class IndexController {

    @ResponseBody
    @GetMapping("/hello")
    public String index(){
        return "hello world";
    }

    @ResponseFile
    @GetMapping("/file")
    public ResponseFileEntity toFile(){
        ResponseFileEntity responseFileEntity = new ResponseFileEntity();
        responseFileEntity.setIn(new ByteArrayInputStream("这是一段测试文字".getBytes()));
        return responseFileEntity;
    }

    @ResponseBody
    @GetMapping("/message")
    public Message message(){
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setDate(new Date());
        message.setInfo("消息");
        return message;
    }

}
