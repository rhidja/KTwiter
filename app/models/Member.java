package models;

import java.util.List;

import models.Post;
import models.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.EntityManager;

import play.db.ebean.Model;

@Entity
@Table(name="Member")
public class Member extends Model{
	private int id;
	private String login;
	private String email;
	private String motPasse;
	private Profile profile;
	private List<Post> posts;
    private List<Comment> comments;
	
	
	// Getters and Setters ================================================================================
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotPasse() {
		return motPasse;
	}
	
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	
	@OneToOne(mappedBy="member")
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@OneToMany(mappedBy="autor")
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@OneToMany(mappedBy="autor")
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	// Methodes statics ====================================================================================
	
	public static Finder<Long, Member> find = new Finder<Long, Member>(Long.class, Member.class);
	
	public static List<Member> all(){
		return find.all();
	}
	
	public static Member getMember(String login){
		return find.where().eq("login",login).findUnique();
	}
	
	public static Boolean isMember (String login, String motPasse){
		return find.where().eq("login",login).eq("motPasse",motPasse).findRowCount()>0;
	}
	
	public static void setMember(Member member){
		member.save();
	}
}
