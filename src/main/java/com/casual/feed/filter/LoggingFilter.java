package com.casual.feed.filter;

import com.casual.feed.context.RequestContext;
import com.casual.feed.context.RequestContextHolder;
import com.casual.feed.util.Guid;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: ayang
 */
public class LoggingFilter implements Filter {
    public static final String THREAD_ID = "threadId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        insertIntoContext();
        try {
            chain.doFilter(request, response);
        } finally {
            clearContext();
        }
    }

    private void insertIntoContext() {
        String threadId = Guid.createRandomGuid().toGuidString();
        MDC.put(THREAD_ID, threadId);
        RequestContext context = new RequestContext(threadId);
        RequestContextHolder.setRequestContext(context);
    }

    private void clearContext() {
        RequestContextHolder.removeRequestContext();
        MDC.remove(THREAD_ID);
    }

    @Override
    public void destroy() {
    }
}
