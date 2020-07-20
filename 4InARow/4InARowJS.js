//User variables
var namePlayerOne;
var namePlayerTwo;
var p1Filed = false;
var p2Filed = false;      

//Field and game variables
var nrOfRows = 6;
var nrOfColumns = 7;
var seqStonesToWin = 4;
var colHeight= [-1,-1,-1,-1,-1,-1,-1];
var counter = 0;
var startColor = 'blue';
var secondColor = 'red';
var winningColor = "rgba(6, 224, 38, 0.57)";
var colorLastMove;
var colorUpcomingMove;
var field = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1]]; //red or blue or -1
var weHaveAWinner = false;
var onclickIgnore = false;
var abort = false;

//Player name entry behaviour
function confirmName(player){
	if(player == 1){
		namePlayerOne = document.getElementById("name1").value;
		if(namePlayerOne != ""){
			p1Filed = true;
		}
	}else{
		namePlayerTwo = document.getElementById("name2").value;
		if(namePlayerTwo != ""){
			p2Filed = true;
		}
	}
	if(p1Filed && p2Filed){
		hidePlayerNames();
	}
}

//Hide player name field after player names have been confirmed
function hidePlayerNames(){
	var x = document.getElementById("playerNamesDiv")
	x.style.display = "none";
	showGameField();
}
// Initial hiding of the game field during name initialisation
function hideGameField(){
	document.getElementById("playGridId").style.display = "none";
	document.getElementById("boxed").style.display = "none";
}

// Showing of the game field after player names have been confirmed
function showGameField(){
	document.getElementById("playGridId").style.display="";    
	document.getElementById("boxed").style.display="";
	document.getElementById("P1").innerHTML = "<span style='color:" + startColor + ";'>Player 1: " + namePlayerOne + "</span>";
	document.getElementById("P2").innerHTML = "<span style='color:" + secondColor + ";'>Player 2: " + namePlayerTwo + "</span>";
}

// Highlight cell upon mousehover
function highlight(col){
	if((colorUpcomingMove == startColor) || (counter == 0)){ //It is blue's turn (blue also starts the game)
		for(i = colHeight[col]+1; i < nrOfRows; i++){
			document.getElementById("cell"+ col + "" + i).style.backgroundColor = "rgba(137, 188, 235, 0.63)"
		}
		
	}else{ //It is red's turn
		for(i = colHeight[col]+1; i < nrOfRows; i++){
				document.getElementById("cell"+ col + "" + i).style.backgroundColor = "rgba(235, 137, 137, 0.63)"
			}
	}
}

function highlightOff(x,col,row){
	if(field[col][row] != -1){
		x.style.backgroundColor = field[col][row];
	}else{
		for(i = colHeight[col]+1; i < nrOfRows; i++){
			document.getElementById("cell" + col + "" + i).style.backgroundColor = "rgba(255, 255, 255, 0.8)";
		}
		x.style.backgroundColor = "rgba(255, 255, 255, 0.8)";
	}    
}


// Main algo for playing the game
function algoStart(z,col,row){
	if(abort==false){
		var curColHeight = colHeight[col];                  //col height before token drop update
		if(validSelection(curColHeight)){                   //column is not yet full
			counter++;
			if(counter % 2 != 0){                           // uneven number == blue, 
				colorLastMove = startColor; 
				colorUpcomingMove = secondColor;
			}else{                                          //even number == red
				colorLastMove = secondColor;
				colorUpcomingMove = startColor;
			}
			tokenDrop(z, col,curColHeight);                   // drop animation + update field
			
			var [winnerQ, winningCells] = winCheck(col,curColHeight);
			console.log(winnerQ);
			if(winnerQ){
				document.getElementById("P1").innerHTML = "<span style='color:" + startColor + ";'>" + namePlayerOne + " --YOU WIN--" + "</span>";
				document.getElementById("P2").innerHTML = "<span style='color:" + secondColor + ";'>" + namePlayerTwo + " --YOU LOSE--" + "</span>";
				document.getElementById("boxed").style.backgroundColor = winningColor;
				// document.getElementById("cell" + col + "" + (curColHeight+1)).style.backgroundColor = winningColor;
				abort = true;
				// randomWinningColors(winningCells);
			}else if(counter == 42){
				alert("The game has ended in a tie");
			}
		}else{                                              // column is full
			alert("This is not a valid move");
		}
	}
	
}

// Check whether column is full or not
function validSelection(curColHeight){
	if(curColHeight < 5){
		return true;
	}else{
		return false;
	}
}

//Update cell color and colHeight
function tokenDrop(z, col, curColHeight){
	dropAnimation(col);
	setTimeout(function(){
		document.getElementById("cell" + col + "" + (curColHeight+1)).style.backgroundColor = colorLastMove;
		colHeight[col]+=1;
		field[col][curColHeight+1] = colorLastMove;
	},100);
}

function dropAnimation(col){
	tempCount = 0;
	for(i=nrOfRows -1; i>colHeight[col]+1; i--){
		tempCount++;
		if(tempCount == 1){
			document.getElementById("cell" + col + "" + i).style.backgroundColor = colorLastMove;
		}
		// alert("i= " + i + " col = " + col);
		task(i,col,tempCount);
	}
}

function task(i,col,tempCount){
	setTimeout(function(){
		// console.log("cell" + col + "" + (i-1));
		if(tempCount == 1){
			document.getElementById("cell" + col + "" + i).style.backgroundColor = "rgba(255, 255, 255, 0.8)";
			document.getElementById("cell" + col + "" + (i-1)).style.backgroundColor = colorLastMove;
		}else{
			document.getElementById("cell" + col + "" + (i-1)).style.backgroundColor = colorLastMove;
			document.getElementById("cell" + col + "" + i).style.backgroundColor = "rgba(255, 255, 255, 0.8)";   
		}
	}, (25 * tempCount));

}

// Check if there is a winner
function winCheck(col, curColHeight){
	var ArrayLists;
	var winArrayHor = [[col,curColHeight + 1]];
	var winArrayVer = [[col,curColHeight + 1]];
	var winArrayDiagUpDown = [[col,curColHeight + 1]];
	var winArrayDiagDownUp = [[col,curColHeight + 1]];
	horLeft(col,curColHeight,winArrayHor);
	horRight(col,curColHeight,winArrayHor);
	verDown(col,curColHeight,winArrayVer);
	diagLeftUp(col,curColHeight,winArrayDiagUpDown);
	diagRightDown(col,curColHeight,winArrayDiagUpDown);
	diagRightUp(col,curColHeight,winArrayDiagDownUp);
	diagLeftDown(col,curColHeight,winArrayDiagDownUp);
	arrayLists = [winArrayHor,winArrayVer,winArrayDiagUpDown,winArrayDiagDownUp];
	// console.log(JSON.stringify(arrayLists));
	var [someBool, newArray] = visualizeWinningTokens(arrayLists);
	if(someBool){
		return [true,newArray];
	}
	return [false,newArray];
}

function visualizeWinningTokens(arrayLists){
	var successCount = 0;
	var onlyWinningSeq =[];
	
	for(i=0;i<arrayLists.length;i++){
		if(arrayLists[i].length >= 4){
			successCount++;
			onlyWinningSeq.push(arrayLists[i]); 
			for(j=0;j<arrayLists[i].length;j++){
				// console.log("cell" + arrayLists[i][j][0] + "" + arrayLists[i][j][1]);
				document.getElementById("cell" + arrayLists[i][j][0] + "" + arrayLists[i][j][1]).style.backgroundColor = winningColor;
				console.log("hier");
			}
		}
		// else{
		//     console.log("ArrayList " + i + " is not lang genoeg");
		// }

	}
	if(successCount > 0){
		return [true,onlyWinningSeq];
	}
	return[false,onlyWinningSeq];
}

// function randomWinningColors(someArray){
// 	while(abort){
// 		console.log("abort = " + abort);
// 		for(i=0;i<someArray.length;i++){
// 			for(j=0;j<someArray[i].length;j++){
// 				const randomColor = Math.floor(Math.random()*16777215).toString(16);
// 				document.getElementById("cell" + someArray[i][j][0] + "" + someArray[i][j][1]).style.backgroundColor = randomColor;
// 			}
// 		}
// 	}
// }   

//Check vertical win
function verDown(col,curColHeight,winArrayVer){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempColHeight = curColHeight+1;
	while(cellsToCheck){
		// console.log(tempColHeight);
		if(tempColHeight !=0){
			tempColHeight--;
			if(field[col][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayVer.push([col,tempColHeight]);
				// console.log("winArrayVer");
				// console.log(JSON.stringify(winArrayVer));
			}else{
				cellsToCheck =  false;
			}
		}else{
			cellsToCheck = false;
		}
	}
	// console.log("verDown:" + tempCount);
}


//Check horizontal win
function horLeft(col,curColHeight,winArrayHor){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempCol = col;
	var tempColHeight = curColHeight +1;
	while(cellsToCheck){
		if(tempCol != 0){
			tempCol--;
			if(field[tempCol][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayHor.push([tempCol,tempColHeight]);
				// console.log("winArrayHor1");
				// console.log(JSON.stringify(winArrayHor));
			}else{
				cellsToCheck = false;
			}
		}else{
			cellsToCheck = false;
		} 
	}
	// console.log("horLeft:" + tempCount);
}

function horRight(col, curColHeight,winArrayHor){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempCol = col;
	var tempColHeight = curColHeight +1;
	while(cellsToCheck){
		if(tempCol < 6){
			tempCol++;
			if(field[tempCol][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayHor.push([tempCol,tempColHeight]);
				// console.log("winArrayHor2");
				// console.log(JSON.stringify(winArrayHor));
			}else{
				cellsToCheck = false;
			}
		}else{
			cellsToCheck = false;
		} 
	}
	// console.log("horRight:" + tempCount);
}

//Check diagonal win
function diagLeftUp(col,curColHeight,winArrayDiagUpDown){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempCol = col;
	var tempColHeight = curColHeight +1;
	while(cellsToCheck){
		if(tempCol != 0 && tempColHeight < 5){
			tempCol--;
			tempColHeight++;
			if(field[tempCol][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayDiagUpDown.push([tempCol,tempColHeight]);
				// console.log("winArrayDiagUpDown1");
				// console.log(JSON.stringify(winArrayDiagUpDown));
			}else{
				cellsToCheck = false;
			}
		}else{
			cellsToCheck = false;
		}
	}
	// console.log("diagLeftUp:" + tempCount);
}

function diagRightDown(col,curColHeight,winArrayDiagUpDown){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempCol = col;
	var tempColHeight = curColHeight +1;
	while(cellsToCheck){
		if(tempCol < 6 && tempColHeight != 0){
			tempCol++;
			tempColHeight--;
			if(field[tempCol][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayDiagUpDown.push([tempCol,tempColHeight]);
				// console.log("winArrayDiagUpDown2");
				// console.log(JSON.stringify(winArrayDiagUpDown));
			}else{
				cellsToCheck = false;
			}
		}else{
			cellsToCheck = false;
		}
	}
	// console.log("diagLeftUp:" + tempCount);
}

function diagRightUp(col,curColHeight,winArrayDiagDownUp){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempCol = col;
	var tempColHeight = curColHeight +1;
	while(cellsToCheck){
		if(tempCol <6 && tempColHeight < 5){
			tempCol++;
			tempColHeight++;
			if(field[tempCol][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayDiagDownUp.push([tempCol,tempColHeight]);
				// console.log("winArrayDiagDownUp1");
				// console.log(JSON.stringify(winArrayDiagDownUp));
			}else{
				cellsToCheck = false;
			}
		}else{
			cellsToCheck = false;
		}
	}
	// console.log("diagLeftUp:" + tempCount);
}

function diagLeftDown(col,curColHeight,winArrayDiagDownUp){
	var cellsToCheck = true;
	var tempCount = 0;
	var tempCol = col;
	var tempColHeight = curColHeight +1;
	while(cellsToCheck){
		if(tempCol != 0 && tempColHeight != 0){
			tempCol--;
			tempColHeight--;
			if(field[tempCol][tempColHeight] == colorLastMove){
				tempCount++;
				winArrayDiagDownUp.push([tempCol,tempColHeight]);
				// console.log("winArrayDiagDownUp2");
				// console.log(JSON.stringify(winArrayDiagDownUp));
			}else{
				cellsToCheck = false;
			}
		}else{
			cellsToCheck = false;
		}
	}
	// console.log("diagLeftUp:" + tempCount);
}