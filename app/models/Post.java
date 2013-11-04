package models;

import java.util.Date;
import java.util.List;

import models.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
@Table(name="Post")
public class Post extends Model {
    @Id
    @GeneratedValue
	private int id;
    private String title;
    private String content;
    private Date postDate;
    @ManyToOne
    @JoinColumn(name="nom")
    private Wall wall;
    @OneToMany
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name="login")
    private Member autor;
	
    
    public static Finder<Long, Post> find = new Finder<Long, Post>(Long.class, Post.class);
    
    // Getters and Setters  ==============================================================================
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Wall getWall() {
		return wall;
	}
	public void setWall(Wall wall) {
		this.wall = wall;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Member getAutor() {
		return autor;
	}
	public void setAutor(Member autor) {
		this.autor = autor;
	}
	
	// Methodes statics  ================================================================================
	
	public static List<Post> all(){
		return find.all();
	}
	
	public static List<Post> listPosts (String login, String wall){
		return find.where().eq("login",login).eq("wall",wall).findList();
	}
	
	public static Post create(Post post){
		post.postDate = new Date();
		post.save();
		return post;
	}
	
}
