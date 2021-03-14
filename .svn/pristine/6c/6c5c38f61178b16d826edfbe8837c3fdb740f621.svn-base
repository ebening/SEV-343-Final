    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.web.context;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.UserConnection;
import com.adinfi.seven.business.services.ServiceUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedProperty;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter extends org.springframework.web.filter.DelegatingFilterProxy {

        private ServiceUsuarios serviceUsuarios;
    
        private String timeoutPage = "/faces/pages/admin/Login.xhtml?timeout=1";
        private String loginPage = "/faces/pages/admin/Login.xhtml";
        private String changePasswordPage = "/faces/pages/admin/ChangePassword.xhtml";
        private String newSessionPage = "/faces/pages/admin/Login.xhtml?newSession=";
        
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {    
            
        //System.out.println("validando peticion");
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            //si requiero tener session
            if (isRequireSessionControl(httpServletRequest)){ 
                
                //si mi sesion es inválida
                if(isSessionInvalid(httpServletRequest)) {
                
                    //System.out.println("Requiere validacion y esta invalida");
                    if (isAJAXRequest(httpServletRequest)) {
                        //System.out.println("preparando respuesta de timeout ajax");
                        StringBuilder sb = new StringBuilder();
                        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"")
                            .append(httpServletRequest.getContextPath()).append(isLogin(httpServletRequest)?loginPage:timeoutPage).append("\"></redirect></partial-response>");
                        httpServletResponse.setHeader("Cache-Control", "no-cache");
                        httpServletResponse.setCharacterEncoding("UTF-8");
                        httpServletResponse.setContentType("text/xml");
                        PrintWriter pw = response.getWriter();
                        pw.println(sb.toString());
                        pw.flush();
                        return;
                    }


                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + (isLogin(httpServletRequest)?loginPage:timeoutPage));
                    return;
                
                //si mi sesion es valida, valido sesiones activas
                }else{
                    if(this.serviceUsuarios != null){
                        //System.out.println("Service usuarios no es nulo");
                        CatUsuarios cu = (CatUsuarios) httpServletRequest.getSession().getAttribute("userLoged");
                        Long loginTime = (Long) httpServletRequest.getSession().getAttribute("currentSessionTimestamp");
                        
                        UserConnection con = serviceUsuarios.getUserConnectionByUserId(cu.getIdusuarios());
                        if(con.getLastConnection() > loginTime){
                            System.out.println("Ya existe una nueva sesion, redirigiendo");
                            httpServletRequest.getSession().setAttribute("userLoged", null);
                            String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(con.getLastConnection()));
                            String time = new SimpleDateFormat("HH:mm:ss").format(new Date(con.getLastConnection()));
                            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + newSessionPage + date + "-" + time);
                            return;
                        }else{
                            //System.out.println("misma session, continuando normal");
                        }
                    }else{
                        System.out.println("Service usuario es nulo");
                    }
                }
                
                
            }else{
                //System.out.println("no se requiere validacion");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isAJAXRequest(HttpServletRequest request) {
        boolean check = false;
        String facesRequest = request.getHeader("Faces-Request");
        if (facesRequest != null && facesRequest.equals("partial/ajax")) {
            check = true;
        }
        //System.out.println("es solicitud ajax: " + check);
        return check;
    }

    private boolean isLogin(HttpServletRequest httpServletRequest) {
        String requestPath = httpServletRequest.getRequestURI();
        //System.out.println("validando path completo: " + requestPath);
        boolean res = requestPath.endsWith(loginPage) || httpServletRequest.getContextPath().equals(requestPath);
        //System.out.println("es login: " + requestPath + ": " + res);
        return res;
    }
    
    private boolean isRequireSessionControl(HttpServletRequest httpServletRequest) {
        String requestPath = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        //System.out.println("path a validar: " + requestPath);
        //System.out.println("contexto: " + contextPath);
        boolean res =  !requestPath.contains(timeoutPage) && 
                !requestPath.contains(loginPage) && 
                !requestPath.contains(changePasswordPage) &&
                !requestPath.contains("javax.faces.resource") && 
                !requestPath.equals(contextPath + "/");
        //System.out.println("requiere validacion: " + requestPath + ": " + res);
        return res;
    }

    private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
        boolean isInvalid =  httpServletRequest.getRequestedSessionId() != null && !httpServletRequest.isRequestedSessionIdValid();
        //System.out.println("es sesion invalida: " + isInvalid);
        return isInvalid;
    }

    @Override
    public void destroy() {
    }

    /**
     * @return the serviceUsuarios
     */
    public ServiceUsuarios getServiceUsuarios() {
        return serviceUsuarios;
    }

    /**
     * @param serviceUsuarios the serviceUsuarios to set
     */
    public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
        this.serviceUsuarios = serviceUsuarios;
    }
}