function createNewElement() {
    var numquestions = document.getElementById("numID").value;
    var numofquestions = parseInt(numquestions)
    
    var questionAnswerHTML = "";
    for (var i = 1; i <= numofquestions; i++) { 
       questionAnswerHTML += 
        ` <label for="answer${i}">
             Answer: ${i}
         </label>
         <input type="text" 
                id="nom_ingredient${i}" 
                placeholder="Le nom ${i}" /><br>
         <label for="answer${i}">
              Answer: ${i}
         </label>
         <input type="text" 
                 id="type${i}" 
                 placeholder="le type ${i}" /><br>`
     ;   
    } 
 document.getElementById("list_ingredient").innerHTML = questionAnswerHTML; 
}
$(document).ready(function(){
	  $("#myForm").submit(function(event){
	    event.preventDefault();
	    var numquestions = $("#numID").val();	
	    var data_ingredient= [];
	    for(var i = 1; i <= numquestions; i++) {
			 data_ingredient.push({nom: $("#nom_ingredient"+i).val(), type: $("#type"+i).val()});
		}
	    var formData = {
	      nom: $("#nom_recette").val(),
		  ingredient: data_ingredient,
	      instructions: $("#instructions").val(),
	      tempsPreparation: $("#tempsPreparation").val(),
	      tempsCuisson: $("#tempsCuisson").val(),
	      image: $("#image").val()
	    };
	    $.ajax({
	    	  type: "POST",
	    	  contentType: "application/json",
	    	  url: "http://localhost:8080/add_recette",
	    	  data: JSON.stringify(formData),
	    	  success: function (data) {
	    	    alert(JSON.stringify(data));
	    	    location.reload();
	    	  },
	    	  error: function (xhr, status, error) {
	    	    alert("Error: " + JSON.stringify(formData));
	    	  }
	    	});
	  });
	});
