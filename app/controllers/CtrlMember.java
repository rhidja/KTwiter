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
	static String login; 
	static String motPasse;       
	
	public static Result members() {
    	List <Post> posts;
    	posts  = Post.all();
		return ok(members.render(login,posts));
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
        	member.setNom(body.get("nom").toString());
        	member.setPrenom(body.get("prenom").toString());
        	member.setLogin(body.get("login").toString());
        	member.setEmail(body.get("email").toString());
        	member.setMotPasse(body.get("motPasse").toString());
        	member.save();
            return ok();
        }
        return badRequest();
    }
    
    public static Result submitSignin(){
    	JsonNode body = request().body().asJson();
    	login = body.get("login").toString();
    	motPasse = body.get("motPasse").toString();
    	if(Member.isMember(login, motPasse)){
    		session("Connected",login);
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
