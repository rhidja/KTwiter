package models;

import java.util.List;

import models.Post;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.EntityManager;

import play.db.ebean.Model;

@Entity
@Table(name="Member")
public class Member extends Model{
	private int id;
	private String nom;
	private String prenom;
	private String login;
	private String email;
	private String motPasse;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
