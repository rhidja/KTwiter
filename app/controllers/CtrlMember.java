package controllers;

import java.util.List;

import models.Member;
import models.Post;
import models.Profile;
import models.User;
import akka.io.Tcp.Bind;
import controllers.Application;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.members.*;

import com.fasterxml.jackson.databind.JsonNode;

public class CtrlMember extends Controller {
	
	static Form<User> userForm = Form.form(User.class);     
	
	public static Result members() {
    	List <Post> posts;
    	posts  = Post.all();
    	String member = session().get("Connected");
		return ok(members.render(member,posts));
    }
	
	public static Result profile() {
    	if(request().accepts("application/json"))
        {
        	Member member;
        	JsonNode body = request().body().asJson();
        	member = Member.getMember(body.get("login").asText());
        	return ok(profile.render(member));
        }
    	return badRequest();
    }
	
	public static Result signup() {	
        return ok(signup.render());
    }
	
	public static Result signin() {	
        return ok(signin.render());
    }	
	
	public static Result logout() {	
        return ok(signin.render());
    }
	
    public static Result submitMember() {
        
    	if(request().accepts("application/json"))
        {
        	Member member =new Member();    
        	JsonNode body = request().body().asJson(); 
        	member.setLogin(body.get("login").asText());
        	member.setEmail(body.get("email").asText());
        	member.setMotPasse(body.get("motPasse").asText());
        	member.save();
        	
        	Profile profile = new Profile();
        	profile.setMember(member);
        	profile.save();
        }
        return badRequest();
    }
    
    public static Result submitSignin(){
    	JsonNode body = request().body().asJson();
    	if(Member.isMember(body.get("login").asText(), body.get("motPasse").asText())){
    		String member = body.get("login").asText();
    		session().put("Connected",member);
    		return ok("ok");
    	}
    	else{
    		return ok("nok");
    	}
    }
    
    public static Result editMember(){
    	return TODO;
    }
}
