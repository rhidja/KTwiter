package models;

import java.util.Date;

import models.Member;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.EntityManager;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name="Profile")
public class Profile extends Model{
	private String id;
	private Member member;
	private String nom;
	private String prenom;
	private String sexe;
	private String type;
	private Date dateNaissance;
	
	// Getters and Setters ================================================
	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@OneToOne
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
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
	
	//Methodes static =================================================================
	public static Finder<Long, Profile> find = new Finder<Long, Profile>(Long.class, Profile.class);
	
	public static Profile getProfile(String login){
		return find.where().eq("login",login).findUnique();
	}
	
	public static Boolean isAdmin (String login){
		return find.where().eq("login",login).eq("type","admin").findRowCount()>0;
	}
	
	public static void setProfile(Profile profile){
		profile.save();
	}

}