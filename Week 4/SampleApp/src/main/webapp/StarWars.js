/**
 * 
 */

window.onload = function() {
    $('#homeNav').on('click',loadHomeView);
    $('#charNav').on('click',loadCharactersView);
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
			$('#order66').on('click',function() {
				
				useTheForce();
				getView();
				
			});
		}
	}
	xhr.open("GET", "characters.view", true);
	xhr.send();
		
}

function useTheForce() {
    var xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function(){
        console.log(xhr.readyState);
        if(xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            var globalperson = JSON.parse(xhr.responseText);
            name = JSON.parse(xhr.responseText).name;
            $('#output').html(name);
        }
    }

    //STEP 3- open request
    var id = $('#swId').val();
    var type = $('#swType').val();
    var url = `https://swapi.co/api/${type}/${id}`;
    console.log(id);
    console.log(type);

    xhr.open('GET',url,true);

    xhr.send();

    console.log("SENT REQUEST " + xhr.responseText);

}

function getView() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			$('#view').html(xhr.responseText);
		}
		
	}
	
	xhr.open("GET", "final.view", true);
	xhr.send();
	
	
}
