package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import models.Member;


@Entity
@Table(name="message")
public class Message extends Model{

	@Id
	@GeneratedValue
	private long id;
	private String message;
	private String titre;
	private Date dateEnvoi;
	@ManyToOne
	private long expediteur;
	@ManyToOne
	private String recepteur;
	
	
	 // Getters and Setters  ==============================================================================
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(long expediteur) {
		this.expediteur = expediteur;
	}
	public String getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(String recepteur) {
		this.recepteur = recepteur;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateEnvoi() {
		return dateEnvoi;
	}
	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}	
	
	
	// Methodes statics  ================================================================================
	
	public static Finder<Long, Message> find = new Finder<Long, Message>(Long.class, Message.class);
	
	public static List<Message> all(long rec){
		return find.where().eq("recepteur", rec).order().desc("dateEnvoi").findList();
	}
	
	public static List<Message> getMessageByE (Member member){
		return find.where().eq("expediteur",member).findList();
	}
	
	public static List<Message> getMessageByR (Member member){
		return find.where().eq("recepteur",member).findList();
	}
	public static Message getMsg(long mesId){
		return find.where().eq("id",mesId).findUnique();
	}
	public static long getExp(long mesId){
		return find.select("expediteur").where().eq("id",mesId).findUnique().expediteur;
		
	}
	
	
	public static void setMsg(Message message){
		message.dateEnvoi = new Date();
		message.save();
	}

		
	
	
	
}
