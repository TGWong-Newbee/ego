package intercepter;

import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 王俊 on 2019/8/27.
 */
public class OrderIntercepter implements HandlerInterceptor {
    @Autowired
    private JedisDao jedisDao;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String value= CookieUtils.getCookieValue(httpServletRequest, "TT_TOKEN");
        if(value!=null&&!value.equals("")){
            String json=jedisDao.get(value);
            if (json!=null&!json.equals("")){
                return  true;
            }
        }
        if (httpServletRequest.getParameter("num")!=null) {
            int num = Integer.parseInt(httpServletRequest.getParameter("num"));
            //下面的代码表示没有登陆的情况
            //重定向到登录页面
            httpServletResponse.sendRedirect("http://localhost:8084/user/showLogin?interUrl="+httpServletRequest.getRequestURL()+"%3Fnum="+num);
        }
        httpServletResponse.sendRedirect("http://localhost:8084/user/showLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
