package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
	
    public static Result admin() {
    	List <Member> members;
    	members  = Member.all();
        return ok(admin.render(members));
    }
	
	public static Result about() {	
        return ok(about.render());
    }	
	public static Result contact() {	
        return ok(contact.render());
    }	
}
