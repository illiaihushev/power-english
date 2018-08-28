package web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionValidationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        String urlPattern = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        if (urlPattern.charAt(urlPattern.length() - 1) == '/') {
            urlPattern = urlPattern.substring(0, urlPattern.length() - 1);
        }
        if (!(urlPattern.equals("/logIn") || (urlPattern.equals("/register")) ||
                urlPattern.equals("/sessionExpired"))) {
            if (httpServletRequest.getSession(false) == null) {
                ((HttpServletResponse) response).sendRedirect(httpServletRequest.getContextPath() + "/sessionExpired");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
