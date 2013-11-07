package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.members.*;

import com.fasterxml.jackson.databind.JsonNode;


public class CtrlComment extends Controller{
	
	public static Result getComments() {	
    	List <Comment> comments;
    	comments  = Comment.all();
		return ok();
	} 
	
	public static Result submitComment() {	
		JsonNode body = request().body().asJson();
    	Comment comment = new Comment();
    	comment.setAutor(Member.getMember(body.get("auteur").asText()));
    	comment.setPost(Post.getPost(body.get("postid").asInt()));
    	comment.setContent(body.get("content").asText());
    	Comment.setComment(comment);
		return ok("OK");//redirect(routes.CtrlPost.listPost());
	} 
}
