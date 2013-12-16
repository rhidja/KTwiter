package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name="Profile")
public class Profile extends Model{
	@Id
	@GeneratedValue
	private long id;
	private String nom;
	private String prenom;
	private String sexe;
	@OneToOne
	private Member member;
	private String type;
	private Date dateNaissance;
	
	// Getters and Setters ================================================
	
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	// Methodes statics ====================================================================================
	
	public static Finder<Long, Profile> find = new Finder<Long, Profile>(Long.class, Profile.class);
	
	public static List<Profile> all(){
		return find.all();
	}
	
	public static Profile getProfile(Member member){
		return find.where().eq("member",member).findUnique();
	}
}