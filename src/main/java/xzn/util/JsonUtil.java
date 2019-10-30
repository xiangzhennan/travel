package xzn.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {
    public static void writeJsonToClient(Object object, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
