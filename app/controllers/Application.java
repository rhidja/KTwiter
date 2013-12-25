package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
		  response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		  List <Post> posts;
		  posts  = Post.all();
		  String member = session().get("Connected");
		  return ok(index.render(member,posts));
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
