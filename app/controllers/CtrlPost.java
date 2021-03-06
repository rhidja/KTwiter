package controllers;

import java.util.LinkedList;
import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;

public class CtrlPost extends Controller {
	
	public static Result getPosts() {
    	List <Post> posts;
    	posts  = Post.all();
    	String member = session().get("Connected");
		return ok(views.html.posts.render(member,posts));
	}
	
    public static Result search() {
    	List <Post> posts;
      	JsonNode body = request().body().asJson();
      	Member member = Member.getMember(body.get("search").asText());
		posts  = Post.getPostsByM(member);
		return ok(views.html.posts.render(session().get("Connected"),posts));
    }
	
	public static Result wall() {
    	List <Post> posts;
    	String member = session().get("Connected");
    	posts  = Post.getPostsByM(Member.getMember(member));
    	
		return ok(views.html.posts.render(member,posts));
	}
	
	public static Result likePost() {
		JsonNode body = request().body().asJson();
		
		if (Aime.getLike(body.get("post-id").asLong(), Member.getMember(session().get("Connected")))){
			Post post = Post.getPost(body.get("post-id").asInt());
			post.setLikePost(post.getLikePost()+1);
			post.update();
			Aime aime=new Aime();
			aime.setpId(body.get("post-id").asInt());
			aime.setmId(Member.getMember(session().get("Connected")));
			Aime.setAime(aime);
			return redirect(routes.CtrlPost.getPosts());
		}
		else{
			Post post = Post.getPost(body.get("post-id").asInt());
			post.setLikePost(post.getLikePost()-1);
			post.update();
			Aime aime=Aime.getAime(body.get("post-id").asInt());
			aime.delete();
			return redirect(routes.CtrlPost.getPosts());}
	}
	
	public static Result deletePost() {
		JsonNode body = request().body().asJson();
		Post post = Post.getPost(body.get("post-id").asInt());
		post.delete();
		Aime aime=Aime.getAime(body.get("post-id").asInt());
		aime.delete();
		return redirect(routes.CtrlPost.getPosts());
	}
	
	public static Result submitPost() {	
		JsonNode body = request().body().asJson();
    	Post post = new Post();
    	post.setContent(body.get("post").asText());
    	post.setAutor(Member.getMember(session().get("Connected")));
    	Post.setPost(post);
		return redirect(routes.CtrlPost.getPosts());
	}  
	
}
