package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Member;
import models.Post;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

    public static Result index() {
    	List <Member> members;
    	members  = Member.all();
        return ok(index.render(members));
    } 
    
}
