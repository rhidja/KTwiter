$(document).ready(function($) {
	
	$(".btn-admin").click(function(e) {
    	$('article').load('/admin');
    });
	$(".btn-about").click(function(e) {
    	$('article').load('/about');
    });
	$(".btn-contact").click(function(e) {
    	$('article').load('/contact');
    });
	//====================================

// Connexion =======================================================================================================

	// Cette fonction permet de charger le formulaire de connexion.
	$(".btn-signin").click(function(e) {
    	$('article').load('/signin');
    });
	
	// Cette fonction permet de se connecter.
	$("#article").click(".btn-signin-submit",function(e) {
		$login = $("#inputLogin").val();
        $motPasse = $("#inputPassword").val();
        $.ajax({
                type : 'POST',
                url : '/signin',
                contentType : "application/json; charset=UTF-8",
                data : JSON.stringify({"login" : $login,"motPasse" : $motPasse}),
                success : function(data) {
                	if(data=="ok"){
                		window.location = '/members';
                	}
                }	
        });
        return false;
	});
	
	// Cette fonction permet de se deconnecter.
	$("#article").click(".btn-logout",function(e) {
		$("#id_login").load('/logout');
    });


// S'inscrire ======================================================================================================
	
	$(".btn-signup").click(function(e) {
    	$('article').load('/signup');
    });
	
	$(".btn-submit-member").click(function(e) {
		$nom = $("#inputNom").val();
		$prenom = $("#inputPrenom").val();
		$login = $("#inputLogin").val();
		$email = $("#inputEmail").val();
        $motPasse = $("#inputPassword").val();
        $.ajax({
                type : 'POST',
                url : '/signup',
                contentType : "application/json; charset=UTF-8",
                data : JSON.stringify({"nom" : $nom,"prenom" : $prenom,"login" : $login,"email" : $email,"motPasse" : $motPasse}),
                success : function(data) {
                	$('article').load('article.scala.html');
                }
        });
        return false;
	});
	
// Pour faire un post =============================================================================================	
	$(".btn-post-submit").click(function(e) {
		$auteur = $("#inputAuteur").val();
        $post = $("#inputPost").val();
        $.ajax({
                type : 'POST',
                url : '/post',
                contentType : "application/json; charset=UTF-8",
                data : JSON.stringify({"auteur" : $auteur,"post" : $post}),
                success : function(data) {
                	$("#div_posts").html(data);
                }
        });
        return false;
	});
	
// Pour faire un commentaire a un post. ===========================================================================
	
	// En cliquant sur un post un champ et ub bouton seront affiches.
	$(".btn-comment").click(function(e) {
		$(this).find('.comDiv').removeClass("hidden");
	});
	
	// Pour poster un commentaire. 
	$(".btn-save-comment").click(function(e) {
		$auteur  = $("#inputAuteur").val();
		$postid  = $(this).prevAll(".postId").val();
		$content = $(this).prevAll(".comCont").val();
		
		$.ajax({
            type : 'POST',
            url : '/comment',
            contentType : "application/json; charset=UTF-8",
            data : JSON.stringify({"auteur" : $auteur,"postid" : $postid,"content" : $content}),
            success : function(data) {
            	
            }
        });
        return false;
	});
	// Cliquer sur un membre pour voir son profile
	
	$("#article").on("click",".view-profile",function(e) {
		$login= $(this).parent().prevAll(".login").text();
		$.ajax({
            type : 'POST',
            url : '/profile',
            contentType : "application/json; charset=UTF-8",
            data : JSON.stringify({"login" : $login}),
            success : function(data) {
            	$('article').html(data);
            }
        });
	});
});