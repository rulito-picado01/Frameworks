package javalin.controllers;


import io.javalin.http.Context;
import io.javalin.http.Handler;

public class DemoController implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        var value = context.queryParam("param1");
        context.result("Hola Mundo! - desde Javalin. Param: " + value);
    }
}
