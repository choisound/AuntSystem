package com.cyx.controller;


import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.FilterChain;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.filter.OncePerRequestFilter;  

/** 
* 登录过滤 
*  
* @author geloin 
* @date 2012-4-10 下午2:37:38 
*/  
public class SessionFilter extends OncePerRequestFilter {  
  @Override  
  protected void doFilterInternal(HttpServletRequest request,  
          HttpServletResponse response, FilterChain filterChain)  
          throws ServletException, IOException {  
      // 请求的uri  
      String uri = request.getRequestURI();  
      // uri中包含background时才进行过滤  
      if (uri.contains("Manager") && !uri.contains("ManagerLogin")) {  
          // 是否过滤  
          boolean doFilter = true;  
          if (doFilter) {  
              // 执行过滤  
              // 从session中获取登录者实体  
              Object obj = request.getSession().getAttribute("AdminLogin");  
              if (null == obj) {  
                  request.setCharacterEncoding("UTF-8");  
                  response.setCharacterEncoding("UTF-8");  
                  request.getRequestDispatcher("/ManagerLogin.jsp").forward(request, response);
              } else {  
                  // 如果session中存在登录者实体，则继续  
                  filterChain.doFilter(request, response);  
              }  
          } 
      }else {  
          // 如果uri中不包含background，则继续  
          filterChain.doFilter(request, response);  
      }  
  }  
}  
