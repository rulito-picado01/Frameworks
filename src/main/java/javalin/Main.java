package javalin;

import io.javalin.Javalin;
import javalin.controllers.DemoController;
import javalin.controllers.JsonController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .get("/hola", new DemoController())
                .get("/json", new JsonController())
                .start(7070);
    }
}
