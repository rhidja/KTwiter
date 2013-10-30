package controllers;

import java.util.List;

import models.Member;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	List <Member> members;
    	members  = Member.all();
        return ok(index.render(members));
    }
}
