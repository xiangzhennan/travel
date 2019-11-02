package xzn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xzn.domain.ResultInfo;
import xzn.domain.User;
import xzn.service.UserService;
import xzn.service.UserServiceImpl;
import xzn.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
//注册
@Controller
@RequestMapping("/user")
public class registController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/regist")
    public void regist(User user,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String check = request.getParameter("check");
        //验证码校验
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("check");
        session.removeAttribute("check");
        if(checkCode==null||!checkCode.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo(false);
            info.setErrorMsg("验证码错误");
            JsonUtil.writeJsonToClient(info,response);
            return;
        }
        //验证码正确,尝试注册
        boolean flag = userService.regist(user);
        ResultInfo info = new ResultInfo(flag);
        if (!flag){
            info.setErrorMsg("注册失败");
        }
        JsonUtil.writeJsonToClient(info,response);
    }
}
