package controllers;

import java.util.List;

import models.Member;
import controllers.Application;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.*;
import views.html.members.*;

import com.fasterxml.jackson.databind.JsonNode;

public class CtrlMember extends Controller {
	
	public static Result signup() {	
        return ok(signup.render());
    }
	
	public static Result logout() {	
        return ok(signin.render());
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
    
    public static Result signin(){
    	JsonNode body = request().body().asJson();
    	String email = body.get("email").toString();
    	String motPasse = body.get("motPasse").toString();
    	List <Member> listMember = Member.exist(email, motPasse);
    	if(listMember.size()==0){
    		return ok(signin.render());
    	}
    	else {
    		String login = session("connected");
    		login = listMember.get(0).getLogin();
    		return ok(logout.render(login));
    	}
    }
    
    public static Result editMember(){
    	return TODO;
    }
}
