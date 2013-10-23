$(document).ready(function($) {
	//$btnInscrire =$(".btn-inscrire");
	$(".btn-inscrire").click(function(e) {
    	$('#article').load('/inscrire');
    });
	$(".btn-enregistrer").click(function(e) {
		$nom = $("#inputNom").val();
		$prenom = $("#inputPrenom").val();
		$login = $("#inputLogin").val();
		$email = $("#inputEmail").val();
        $motPasse = $("#inputPassword").val();
        $.ajax({
                type : 'POST',
                url : '/inscrire',
                contentType : "application/json; charset=UTF-8",
                data : JSON.stringify({"nom" : $nom,"prenom" : $prenom,"login" : $login,"email" : $email,"motPasse" : $motPasse}),
                success : function(data) {
                        alert(data);        
                }
        });
        return false;
	});
 
});