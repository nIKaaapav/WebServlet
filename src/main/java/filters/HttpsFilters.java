package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface HttpsFilters extends Filter {
    @Override
    default void init(FilterConfig filterConfig) throws ServletException {}

    default boolean isHttp(ServletRequest rq, ServletResponse rs) {
        return rq instanceof HttpServletRequest && rs instanceof HttpServletResponse;
    }

    @Override
    default void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isHttp(servletRequest, servletResponse)) {
            HttpServletRequest rq = (HttpServletRequest) servletRequest;
            HttpServletResponse rs = (HttpServletResponse) servletResponse;
            doHttpFilter(rq, rs, filterChain);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    default void destroy() { }

    void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain chain) throws ServletException, IOException;

}
