package com.msp.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class MspFilter
 */
public abstract class MspFilter implements Filter {
	
	private final static Logger log = Logger.getLogger(MspFilter.class);
    /**
     * Default constructor. 
     */
    public MspFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			String msg = "### 只能对HttpServletRequest和HttpServletResponse有效......";
			log.error(msg);
			throw new ServletException(msg);
		}
		
		HttpServletRequest  req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		doHttpFilter(req, res, chain);
	}
	
	protected abstract void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException ;
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
