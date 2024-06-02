package javalin;

import io.javalin.Javalin;
import javalin.controllers.DemoController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .get("/hola", new DemoController())
                .start(7070);
    }
}
