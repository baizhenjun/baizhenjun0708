package com.example.java_springboot.aspect;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.java_springboot.annotation.CodeField;
import com.example.java_springboot.annotation.FormatterCodes;
import com.example.java_springboot.model.ResponseMessage;
import com.example.java_springboot.util.Func;
import lombok.SneakyThrows;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CodeSetHandler implements ResponseBodyAdvice {

//    @Resource
//    DynamicCodeService dynamicCodeService;

    private ThreadLocal<Map<String, Map<String,Object>>> formatCodes = new ThreadLocal<>();
    private ThreadLocal<Map<String, String>> codeFields = new ThreadLocal<>();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.hasMethodAnnotation(FormatterCodes.class);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Func.isEmpty(o)) {
            return o;
        } else {
            this.initCodeSet(methodParameter);
            return formatResult(o);
        }
    }

    private void initCodeSet(MethodParameter methodParameter) throws Exception {
        FormatterCodes formatterCodes = methodParameter.getMethodAnnotation(FormatterCodes.class);
        Map<String, Map<String,Object>> codeSet = new HashMap<>();
        Map<String, String> fieldCode = new HashMap<>();
        for(CodeField codeField: formatterCodes.value()){
            boolean isStatic = codeField.isStatic();
            Object formatCode = codeField.code().getInfo();
            String field = codeField.field();
            if(isStatic && formatCode instanceof Map){
                // 静态代码集
                if(!Func.isEmpty(formatCode)){
                    codeSet.put(field, (Map<String, Object>) formatCode);
                    fieldCode.put(field, field + "_");
                }
            } else if(!isStatic && formatCode instanceof String){
                // 动态代码集
//                Map<String,Object> dyc = dynamicCodeService.getCodeInfo((String) formatCode);
//                if(!Func.isEmpty(dyc)){
//                    codeSet.put(field, dyc);
//                    fieldCode.put(field, field + "_");
//                }
            } else{
                throw new Exception("动态代码集请将isStatic设置为false");
            }
        }
        formatCodes.set(codeSet);
        codeFields.set(fieldCode);
    }

    private Object formatResult(Object result){
        if(formatCodes.get().isEmpty()){
            return result;
        }

        Object temp = result;
        if(result instanceof ResponseMessage){
            temp = ((ResponseMessage) result).getBody();
        }

        if(temp instanceof List){
            for(int i = 0; i< ((List) temp).size(); i++){
                ((List) temp).set(i, formatItem(((List) temp).get(i)));
            }
        } else if (temp instanceof Page){
            List records = ((Page) temp).getRecords();
            for(int i=0; i<records.size(); i++){
                records.set(i, formatItem(records.get(i)));
            }
        } else {
            temp = formatItem(temp);
        }
        // 不用remove也会被回收
        formatCodes.remove();
        codeFields.remove();

        if(result instanceof ResponseMessage){
            return ((ResponseMessage) result).setBody(temp);
        }
        return temp;
    }

    private Object formatItem(Object result){
        if(result!=null){
            Map<String, Object> map = BeanMap.create(result);
            Map<String, Object> resultMap = new HashMap(map.size());
            map.forEach((k,v) -> {
                resultMap.put(k, v);
                if(!Func.isNull(codeFields.get().get(k)) && !Func.isNull(v)){
                    resultMap.put(codeFields.get().get(k), formatCodes.get().get(k).get(v));
                }
            });
            return resultMap;
        }else{
            return new HashMap<String,Object>();
        }
    }
}
