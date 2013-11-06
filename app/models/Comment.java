package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model.Finder;
import models.Member;

@Entity
@Table(name="Comment")
public class Comment {
	private int id;
	private String content;
	private Post post;
	private Member autor;
	
	// Getters and Setters =======================================================================
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@ManyToOne
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	@ManyToOne
	public Member getAutor() {
		return autor;
	}
	public void setAutor(Member autor) {
		this.autor = autor;
	}
	
	// Methodes statiques  ======================================================================
	
	public static Finder<Long, Comment> find = new Finder<Long, Comment>(Long.class, Comment.class);
	
	public static List<Comment> all(){
		return find.all();
	}
	
	public static List<Comment> getComments(Post post){
		return find.where().eq("post",post).findList();
	}
	
	
}
