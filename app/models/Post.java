package models;

import java.util.Date;
import java.util.List;

import models.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
@Table(name="Post")
public class Post extends Model {
	private int id;
    private String title;
    private String content;
    private Date postDate;
    private Member autor;
    private Wall wall;
    private List<Comment> comments;

    // Getters and Setters  ==============================================================================
    @Id
    @GeneratedValue
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
	@ManyToOne
	public Wall getWall() {
		return wall;
	}
	public void setWall(Wall wall) {
		this.wall = wall;
	}
	@OneToMany
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@ManyToOne
	public Member getAutor() {
		return autor;
	}
	public void setAutor(Member autor) {
		this.autor = autor;
	}
	
	// Methodes statics  ================================================================================
	
    public static Finder<Long, Post> find = new Finder<Long, Post>(Long.class, Post.class);
    
	public static List<Post> all(){
		return find.all();
	}
	
	public static List<Post> listPosts (String login, String wall){
		return find.where().eq("login",login).eq("wall",wall).findList();
	}

	public static List<Post> getPostsByM (Member member){
		return find.where().eq("autor",member).findList();
	}
	
	public static Post getPost(int postId){
		return find.where().eq("id",postId).findUnique();
	}
	
	public static void setPost(Post post){
		post.postDate = new Date();
		post.save();
	}
	
}
