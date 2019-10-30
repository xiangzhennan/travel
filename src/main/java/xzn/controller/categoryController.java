package xzn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xzn.domain.Category;
import xzn.service.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
* 页眉菜单栏处理
*/
@Controller
@RequestMapping("/category")
public class categoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response) throws IOException {
        List<Category> categories = categoryService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(categories);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
