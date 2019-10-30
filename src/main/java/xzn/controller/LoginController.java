package xzn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xzn.domain.ResultInfo;
import xzn.domain.User;
import xzn.service.UserService;
import xzn.service.UserServiceImpl;
import xzn.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ResultInfo info;
    @RequestMapping("/login")
    public void login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String check = request.getParameter("check");
        //验证码校验
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("check");
        session.removeAttribute("check");
        if(checkCode==null||!checkCode.equalsIgnoreCase(check)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            JsonUtil.writeJsonToClient(info,response);
            return;
        }
        //密码校验
        User user1 = userService.findUserByPassword(user);
        boolean flag = user1 != null;
        info.setFlag(flag);
        if (!flag){
            info.setErrorMsg("用户名或密码错误");
        }else{
            session.setAttribute("user",user1);
        }
        JsonUtil.writeJsonToClient(info,response);
    }
    @RequestMapping("/findOne")
    public void findOne(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute("user");
        if (user==null)return;
        JsonUtil.writeJsonToClient(user,response);
    }
}
