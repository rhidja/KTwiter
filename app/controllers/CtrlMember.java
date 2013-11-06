package controllers;

import java.util.List;

import models.Member;
import models.Post;
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
	static String member;       
	
	public static Result members() {
    	List <Post> posts;
    	posts  = Post.all();
		return ok(members.render(member,posts));
    }
	
	public static Result signup() {	
        return ok(signup.render());
    }
	
	public static Result about() {	
        return ok(about.render());
    }
	
	public static Result signin() {	
        return ok(signin.render(userForm));
    }	
	
	public static Result logout() {	
        return ok(signin.render(userForm));
    }
	
    public static Result submitMember() {
        
    	if(request().accepts("application/json"))
        {
        	Member member =new Member();    
        	JsonNode body = request().body().asJson(); 
        	member.setNom(body.get("nom").asText());
        	member.setPrenom(body.get("prenom").asText());
        	member.setLogin(body.get("login").asText());
        	member.setEmail(body.get("email").asText());
        	member.setMotPasse(body.get("motPasse").asText());
        	member.save();
            return ok();
        }
        return badRequest();
    }
    
    public static Result submitSignin(){
    	JsonNode body = request().body().asJson();
    	if(Member.isMember(body.get("login").asText(), body.get("motPasse").asText())){
    		member = body.get("login").asText();
    		session("Connected",member);
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
