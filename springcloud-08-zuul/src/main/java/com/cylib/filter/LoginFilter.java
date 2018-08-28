package com.cylib.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: aelchao aelchao207@gmail.com
 * @Date: 2018/8/27 下午2:03
 */
@Component
public class LoginFilter extends ZuulFilter {

    // 声明过滤器类型，过滤器何时执行
    @Override
    public String filterType() {
        return "pre";
    }
    // 声明过滤器执行顺序，数字越小，优先级越高
    @Override
    public int filterOrder() {
        return 0;
    }
    // 声明当前过滤器是否可用
    @Override
    public boolean shouldFilter() {
        return true;
    }
    // 过滤器核心方法
    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        try {
            response.sendRedirect("http://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
