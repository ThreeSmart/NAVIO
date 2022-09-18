var oBar=document.getElementById('person');
var roomLift=document.getElementById('roomLift');
var room401=document.getElementById('room401');
var room402=document.getElementById('room402');
var room403=document.getElementById('room403');
var room404=document.getElementById('room404');
var room405=document.getElementById('room405');
var room406=document.getElementById('room406');
var room407=document.getElementById('room407');
var roomKitchen=document.getElementById('roomKitchen');
var roomWharehouse=document.getElementById('roomWharehouse');
var roomToilet=document.getElementById('roomToilet');
var roomHidden=document.getElementById('roomHidden');
var roomOut=document.getElementById('roomOut');
var roomManToilet=document.getElementById('roomManToilet');
var roomWomanToilet=document.getElementById('roomWomanToilet');

var blueBackground = "#08214A";





roomLift.onclick = function(){
    roomClicked(roomLift,blueBackground);
};

room401.onclick = function(){
    roomClicked(room401,blueBackground);
};
room402.onclick = function(){
    roomClicked(room402,blueBackground);
};
room403.onclick = function(){
    roomClicked(room403,"red");
};
room404.onclick = function(){
    roomClicked(room404,"red");
};
room405.onclick = function(){
    roomClicked(room405,"red");
};
room406.onclick = function(){
    roomClicked(room406,"red");
};
room407.onclick = function(){
    roomClicked(room407,"red");
};

roomKitchen.onclick = function(){
    roomClicked(roomKitchen,"red");
};
roomWharehouse.onclick = function(){
    roomClicked(roomWharehouse,"red");
};
roomToilet.onclick = function(){
    roomClicked(roomToilet,"red");
};

roomHidden.onclick = function(){
    roomClicked(roomHidden,"red");
};
roomOut.onclick = function(){
    roomClicked(roomOut,"red");
};
roomManToilet.onclick = function(){
    roomClicked(roomManToilet,"red");
};
roomWomanToilet.onclick = function(){
    roomClicked(roomWomanToilet,"red");
};



function roomClicked(room,color) {
	room.style.fill = color;
}
var personSpeed = 250;
var id = setInterval(frame, personSpeed	);
var posY = 13;
var posX = 143;

function frame() {
	if(posY < 20){
		posY++;
		oBar.setAttribute("cy",posY);
		if(Math.random() > 0.8){
			oBar.setAttribute("cx",posX + 1);
		}else{
			oBar.setAttribute("cx",posX);
		}
	}
	else if(posX > 104){
		posX--;
		oBar.setAttribute("cx",posX);
		if(Math.random() > 0.8){
			oBar.setAttribute("cy",posY + 1);
		}else{
			oBar.setAttribute("cy",posY);
		}
	}
	else if(posY < 310){
		posY++;
		oBar.setAttribute("cy",posY);
		if(Math.random() > 0.8){
			oBar.setAttribute("cx",posX + 1);
		}else{
			oBar.setAttribute("cx",posX);
		}
	}
	else{
		setInterval(goRight, personSpeed);
		clearInterval(id);
	}
	
}
function goRight() {
	if(posX < 165){
		posX++;
		oBar.setAttribute("cx",posX);
		if(Math.random() > 0.8){
			oBar.setAttribute("cy",posY + 1);
		}else{
			oBar.setAttribute("cy",posY);
		}
	}
}
