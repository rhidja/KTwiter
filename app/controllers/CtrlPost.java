package controllers;

import java.util.List;

import models.*;
import akka.io.Tcp.Bind;
import controllers.Application;
import controllers.CtrlMember;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.members.*;

import com.fasterxml.jackson.databind.JsonNode;

public class CtrlPost extends Controller {
	
	public static Result listPost() {
    	List <Post> posts;
    	posts  = Post.all();
		return ok(listposts.render(posts));
	}
	
	
	public static Result submitPost() {	
		JsonNode body = request().body().asJson();
    	Post poste = new Post();
    	poste.setContent(body.get("post").asText());
    	poste.setAutor(Member.getMember(body.get("auteur").asText()));
    	Post.setPost(poste);
		return redirect(routes.CtrlPost.listPost());
	}  
	
}
