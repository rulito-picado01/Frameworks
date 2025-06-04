package javalin;

import io.javalin.Javalin;
import javalin.controllers.DemoController;
import javalin.controllers.JsonController;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .get("/hola", new DemoController())
                .get("/json", new JsonController())
                .exception(Exception.class, (ex, ctx) -> {
                    Map<String, Object> body = new HashMap<>();
                    body.put("error", ex.getClass().getName());
                    body.put("message", ex.getMessage());
                    body.put("timestamp", LocalDateTime.now());
                    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    ctx.json(body);
                })
                .start(7070);
    }
}
