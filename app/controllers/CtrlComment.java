package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;


public class CtrlComment extends Controller{
	
	public static Result getComments() {	
    	List <Comment> comments;
    	comments  = Comment.all();
		return TODO;
	} 
	
	public static Result likeComment() {
		JsonNode body = request().body().asJson();
		Comment comment = Comment.getComment(body.get("comment-id").asInt());
		comment.setLikeComment();
		comment.update();
		return redirect(routes.CtrlPost.getPosts());
	}
	
	public static Result deleteComment() {
		JsonNode body = request().body().asJson();
		Comment comment = Comment.getComment(body.get("comment-id").asInt());
		comment.delete();
		return redirect(routes.CtrlPost.getPosts());
	}
	
	public static Result submitComment() {	
		JsonNode body = request().body().asJson();
    	Comment comment = new Comment();
    	comment.setAutor(Member.getMember(session().get("Connected")));
    	comment.setPost(Post.getPost(body.get("post-id").asInt()));
    	comment.setContent(body.get("comment").asText());
    	Comment.setComment(comment);
    	
		return redirect(routes.CtrlPost.getPosts());
	} 
}
