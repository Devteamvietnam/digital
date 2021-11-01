package com.devteam.core.filter;

import com.devteam.lib.util.text.StringUtil;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Repeatable filter
 *
 */
public class RepeatableFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest && StringUtil.equalsAnyIgnoreCase(request.getContentType(),
                MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE))
        {
            requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request, response);
        }
        if (null == requestWrapper)
        {
            chain.doFilter(request, response);
        }
        else
        {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy()
    {

    }
}
