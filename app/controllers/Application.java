package controllers;

import models.Panic;
import play.mvc.Controller;
import play.mvc.results.RenderJson;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static List<Panic> panics = new ArrayList<>();

    public static void index() {
        render();
    }

    public static void getData() {
        throw new RenderJson(panics.size());
    }

    public static void submit() {
        String sessionHash = Panic.getPanicHash(session);
        long sessionTime = Panic.getPanicTime();

        for (Panic panic : panics) {
            if (panic.getId().equals(sessionHash)) {
                panic.setTime(sessionTime);
                index();
                return;
            }
        }

        panics.add(new Panic(sessionHash, sessionTime));
        index();
    }
}