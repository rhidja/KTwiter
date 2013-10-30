package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import models.Member;

@Entity
@Table(name="Comment")
public class Comment {
	@Id
	private int id;
	private String content;
	@ManyToOne
	private Post post;
	@ManyToOne
	private Member member;
	
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
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	//public Member getAuteur() {
		//return auteur;
	//}
	//public void setAuteur(Member auteur) {
		//this.auteur = auteur;
	//}
	
}
