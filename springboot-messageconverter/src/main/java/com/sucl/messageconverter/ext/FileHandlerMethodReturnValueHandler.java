package com.sucl.messageconverter.ext;

import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.UUID;

/**
 * 注意顺序
 *
 * @author sucl
 * @see RequestMappingHandlerAdapter#getDefaultReturnValueHandlers
 * @since 2019/8/7
 */
public class FileHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler, Ordered {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseFile.class) ||
                returnType.hasMethodAnnotation(ResponseFile.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
//        ResolvableType resolvableType = ResolvableType.forMethodParameter(returnType);

        mavContainer.setRequestHandled(true);

        ResponseFile methodResponseFile = returnType.getMethodAnnotation(ResponseFile.class);
        if (methodResponseFile == null) {
            methodResponseFile = returnType.getContainingClass().getAnnotation(ResponseFile.class);
        }
        Assert.notNull(methodResponseFile, "no ResponseFile at method :" + returnType.getMethod().getName());

        String fileType = methodResponseFile.type();

        HttpServletResponse response = getServletResponse(webRequest);

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + UUID.randomUUID().toString() + "." + fileType);

        ServletOutputStream out = response.getOutputStream();

        if (returnValue instanceof ResponseFileEntity) {
            FileCopyUtils.copy(((ResponseFileEntity) returnValue).getIn(), out);
        } else {
            FileCopyUtils.copy(new ByteArrayInputStream("non".getBytes()), out);
        }

//        response.getWriter().write(returnValue.toString());

    }

    public HttpServletResponse getServletResponse(NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
//        return new ServletServerHttpResponse(response);
        return response;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }
}
