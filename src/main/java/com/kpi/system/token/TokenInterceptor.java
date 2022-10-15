//package com.kpi.system.token;
//
//import com.alibaba.fastjson.JSONObject;
//import com.kpi.system.token.TokenUtil;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
//        HandlerMethod handlerMethod=(HandlerMethod)handler;
////判断如果请求的类是swagger的控制器，直接通行。
//        if(handlerMethod.getBean().getClass().getName().equals("springfox.documentation.swagger.web.ApiResourceController")){
//            return  true;
//        }
//        if(request.getMethod().equals("OPTIONS")){
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }
//        response.setCharacterEncoding("utf-8");
//        String token = request.getHeader("token"); //前端vue将token添加在请求头中
//        if(token != null){
//            boolean result = TokenUtil.verify(token);
//            if(result){
//                System.out.println("通过拦截器");
//                return true;
//            }
//        }
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        try{
//            JSONObject json = new JSONObject();
//            json.put("msg","token verify fail");
//            json.put("code","50000");
//            response.getWriter().append(json.toJSONString());
//            System.out.println("认证失败，未通过拦截器");
//
//        }catch (Exception e){
//            e.printStackTrace();
//            response.sendError(500);
//            return false;
//        }
//        return false;
//
//
//    }
//
//}
