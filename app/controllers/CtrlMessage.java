package controllers;


import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;



public class CtrlMessage extends Controller{

	
	public static Result getFrmMessage() {	
		long member1 = (Member.getMember(session().get("Connected")).getId());
		List <Message> messages;
    	messages  = Message.all(member1);
    	List <Member> members;
    	members  = Member.all();
    	String member = session().get("Connected");
    	
		return ok(views.html.messages.render(member,messages,members));
	} 
	
	public static Result sendMessage(){
		
		if(request().accepts("application/json"))
        {
		JsonNode body = request().body().asJson();
    	Message message = new Message();
    	//Member.getLoginM(Member.getMember(session().get("Connected")).getId());
    	message.setExpediteur(Member.getMember(session().get("Connected")).getId());    	
    	message.setRecepteur(body.get("recepteur").asText());
    	message.setMessage(body.get("message").asText());
    	message.setTitre(body.get("titre").asText());
    	Message.setMsg(message);
    	message.notify();
//    	
//		return redirect(routes.CtrlPost.getPosts());
		
		return ok();
        }
		else{
			
			return badRequest("il faut verifier la requete si elle est en Json");
		}
		
	}
	
	public static Result deleteMessage() {
		
		return TODO;
//		JsonNode body = request().body().asJson();
//		Message message = Message.getMessage(body.get("message-id").asInt());
//		message.delete();
//		return redirect(routes.CtrlMessage.getMessages());
	}
	

	
	
}
