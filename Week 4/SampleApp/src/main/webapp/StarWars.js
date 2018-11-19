/**
 * 
 */

window.onload = function() {
    loadHomeView();
    $('#homeNav').on('click',loadHomeView);
    $('#charactersNav').on('click',loadCharactersView);
}

function loadHomeView() {
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			//do things w/ response
			$('#view').html(xhr.responseText);
		}
	}
	xhr.open("GET", "home.view", true);
	xhr.send();
	
}

function loadCharactersView() {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			// do things w/ response
			$('#view').html(xhr.responseText);
		}
	}
	xhr.open("GET", "character.view", true);
	xhr.send();
		
}

function getCharacters() {
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let chars = JSON.parse(xhr.responseText);
			for (let c of chars) {
				var li = $(`<li>${c.name}<li>`);
				$('#charList').append(li);
			}
		}
	}
	xhr.open("GET","chars");
	xhr.send();
}

function useTheForce() {
    var xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function(){
        console.log(xhr.readyState);
        if(xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            globalperson = JSON.parse(xhr.responseText);
            $('#output').html(JSON.parse(xhr.responseText).name);
        }
    }

    //STEP 3- open request
    var id = $('#swId').val();
    var url = `https://swapi.co/api/people/${id}/`;

    xhr.open('GET',url,true);

    xhr.send();

    console.log("SENT REQUEST " + xhr.responseText); // `SENT REQUEST ${xhr.responseText}`

}

