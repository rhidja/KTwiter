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
	
	public static Result wall() {
    	List <Post> posts;
    	posts  = Post.getPostsByM(Member.getMember(session().get("Connected")));
		return ok(listposts.render(posts));
	}
	
	public static Result likePost() {
		JsonNode body = request().body().asJson();
		Post post = Post.getPost(body.get("post-id").asInt());
		post.setLikePost(post.getLikePost()+1);
		post.update();
		return redirect(routes.CtrlPost.listPost());
	}
	
	public static Result deletePost() {
		JsonNode body = request().body().asJson();
		Post post = Post.getPost(body.get("post-id").asInt());
		post.delete();
		return redirect(routes.CtrlPost.listPost());
	}
	
	public static Result submitPost() {	
		JsonNode body = request().body().asJson();
    	Post post = new Post();
    	post.setContent(body.get("post").asText());
    	post.setAutor(Member.getMember(session().get("Connected")));
    	Post.setPost(post);
		return redirect(routes.CtrlPost.listPost());
	}  
	
}
