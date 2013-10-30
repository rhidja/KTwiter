$(document).ready(function($) {
	
	$(".btn-signup").click(function(e) {
    	$('article').load('/signup');
    });
	
	$(".btn-logout").click(function(e) {
		$("#id_login").load('/logout');
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
	
	$(".btn-signin").click(function(e) {
		$email = $("#idEmail").val();
        $motPasse = $("#idPassword").val();
        $.ajax({
                type : 'POST',
                url : '/signin',
                contentType : "application/json; charset=UTF-8",
                data : JSON.stringify({"email" : $email,"motPasse" : $motPasse}),
                success : function(data) {
                        $("#id_login").html(data);        
                }
        		
        });
        return false;
	});
 
});