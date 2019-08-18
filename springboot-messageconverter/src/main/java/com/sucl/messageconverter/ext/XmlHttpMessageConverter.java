package com.sucl.messageconverter.ext;

import com.thoughtworks.xstream.XStream;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author sucl
 * @since 2019/8/7
 */
public class XmlHttpMessageConverter extends AbstractHttpMessageConverter<Message> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public XmlHttpMessageConverter(){
        this(DEFAULT_CHARSET);
    }

    public XmlHttpMessageConverter(Charset charset){
        super(charset,MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz == Message.class;
    }

    @Override
    protected Message readInternal(Class<? extends Message> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Message message, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        XStream xStream = new XStream();
        xStream.alias("message",Message.class);
//        StreamUtils.copy(xStream.toXML(message), DEFAULT_CHARSET, outputMessage.getBody());
        xStream.toXML(message,outputMessage.getBody());
    }
}
