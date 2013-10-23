package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public static Result inscrire() {
        return ok(inscrire.render());
    }
    public static Result enregistrerUtilisateur() {
        if(request().accepts("application/json"))
        {
                JsonNode body = request().body().asJson();       
                String nom = body.get("nom").asText();
                String prenom = body.get("prenom").asText();
                String login = body.get("login").asText();
                String email = body.get("email").asText();
                String motPasse = body.get("motPasse").asText();
                
                String message = "Le login est : " + login + " et le mot de passe est : " + motPasse;
            return ok(message);
        }
        return badRequest();
    }

}
