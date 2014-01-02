package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table (name="Wall")
public class Wall extends Model{
	@Id
	@GeneratedValue
	private long id;
	private String nom;
	//private Member abonne;
	//private Member abonnement;
	@OneToMany
	private List<Post> posts;

	
	///////////// Getter and Setter ///////////////////::
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
//	public String getAbonne() {
//		return abonne;
//}
//	public void setAbonne(String abonne) {
//		this.abonne = abonne;
//}
//	public String getAbonnement() {
//		return abonnement;
//}
//	public void setAbonnement(String abonnement) {
//		this.abonnement = abonnement;
//}
	
}
