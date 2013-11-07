$(document).ready(function($) {
	
	$(".btn-admin").click(function(e) {
    	$('article').load('/admin');
    });
	$(".btn-signup").click(function(e) {
    	$('article').load('/signup');
    });
	$(".btn-signin").click(function(e) {
    	$('article').load('/signin');
    });	
	$(".btn-about").click(function(e) {
    	$('article').load('/about');
    });
	$(".btn-logout").click(function(e) {
		$("#id_login").load('/logout');
    });
	
	$(".btn-contact").click(function(e) {
        $.ajax({
        	type : 'GET',
        	url : '/signin'
        });
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
	
	$(".btn-signin-submit").click(function(e) {
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
	
	$(".btn-comment").click(function(e) {
		$(this).find('.comDiv').removeClass("hidden");
	});
	
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
            	//$("#div_posts").html(data);
            }
        });
        return false;
	});
	
});