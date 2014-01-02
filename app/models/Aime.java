package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import models.Member;



@Entity
@Table(name="Aime")
public class Aime extends Model {
	
	@ManyToOne
	private Member mId;
	@Id
	private long pId;
	@Id
	private long cId;
	
	///////// Getter and Setter ////////////////
	
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public Member getmId() {
		return mId;
	}
	public void setmId(Member mId) {
		this.mId = mId;
	}
	public long getcId() {
		return cId;
	}
	public void setcId(long cId) {
		this.cId = cId;
	}
	
	////////////// Static Methods ///////////////////
	public static Finder<Long, Aime> find = new Finder<Long, Aime>(Long.class, Aime.class);
	
	
	public static boolean getLike(long idp,Member idm){
		if( find.where().eq("pId",idp).eq("mId", idm).findUnique() != null){
		 return false;
		}
		else{
			return true;
		}
	}
	public static boolean getLikeC(long idc,Member idm){
		if( find.where().eq("cId",idc).eq("mId", idm).findUnique() != null){
		 return false;
		}
		else{
			return true;
		}
	}
	
	public static Aime getAime(long idp){
		return find.where().eq("pId",idp).findUnique();
	}
	
	public static Aime getAimeC(long idc){
		return find.where().eq("cId",idc).findUnique();
	}
	
	
	public static void setAime(Aime aime){

		aime.save();
	}
	
}
