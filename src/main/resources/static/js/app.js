let userScore=0;
let cpuScore=0;
const userScoreSpan = document.getElementById("user-score");
const cpuScoreSpan = document.getElementById("cpu-score");
const scoreBoardDiv = document.querySelector(".score-board");
const resaultDiv = document.querySelector(".result > p");
const paperDiv = document.getElementById("p");
const rockDiv = document.getElementById("r");
const scissorDiv = document.getElementById("s");

const maxScore = 10;
let endGame = false;

function convertToWord(x){
   if (x==="r") {
      return "Rock";
   }
   if (x==="p") {
      return "Paper";
   }
   return "Scissor";
}

function win(userChoice, cpuChoice){
   console.log("WIN");
   userScore++;

   if(userScore===maxScore){
      endGame=true;
      userScoreSpan.innerHTML = userScore;
      resaultDiv.innerHTML= ` You win game, congratulations!`;
      document.getElementById('userPoint').value = userScore;
      document.getElementById('cpuPoint').value = cpuScore;
      document.getElementById("save").disabled = true;
   }

   else{
      userScoreSpan.innerHTML = userScore;
      cpuScoreSpan.innerHTML = cpuScore;
      const userChoiceDiv = document.getElementById(userChoice);

      resaultDiv.innerHTML= ` ${convertToWord(userChoice)} beats ${convertToWord(cpuChoice)} You WIN !`;

      userChoiceDiv.classList.add("green-glow");

      setTimeout(function(){
         userChoiceDiv.classList.remove("green-glow");
      },500);
   }
}

function lose(userChoice, cpuChoice){
   cpuScore++;
   console.log("LOST");
   if(cpuScore===maxScore){
      endGame=true;
      cpuScoreSpan.innerHTML = cpuScore;
      resaultDiv.innerHTML= ` You lost game, try again!`;
      document.getElementById('userPoint').value = userScore;
      document.getElementById('cpuPoint').value = cpuScore;
      document.getElementById("save").disabled = false;
   }

   else {
      userScoreSpan.innerHTML = userScore;
      cpuScoreSpan.innerHTML = cpuScore;
      const userChoiceDiv = document.getElementById(userChoice);
      resaultDiv.innerHTML= ` ${convertToWord(userChoice)} loses to ${convertToWord(cpuChoice)} You LOST !`;
      userChoiceDiv.classList.add("red-glow");

      setTimeout(function(){
         userChoiceDiv.classList.remove("red-glow");
      },500);
   }
   

}

function draw(userChoice, cpuChoice){
   console.log("DRAW");
   userScoreSpan.innerHTML = userScore;
   cpuScoreSpan.innerHTML = cpuScore;
   const userChoiceDiv = document.getElementById(userChoice);
   
   resaultDiv.innerHTML= ` Draw, try again !`;
      
   userChoiceDiv.classList.add("grey-glow");
   
   setTimeout(function(){
      userChoiceDiv.classList.remove("grey-glow");
   },500);
}

function restartGame() {
   document.getElementById("save").disabled = true;
   console.log("restart gameeee");
   userScore = 0;
   cpuScore = 0;
   userScoreSpan.innerHTML = userScore;
   cpuScoreSpan.innerHTML = cpuScore;
   endGame=false;
}

function game(userChoice){
   if(!endGame){
      const cpuChoice = getCpuChoice();

      switch(userChoice + cpuChoice){
         case "pr":
         case "rs":
         case "sp":
            win(userChoice, cpuChoice);
            break;
         case "rp":
         case "ps":
         case "sr":
            lose(userChoice, cpuChoice);
            break;
         case "rr":
         case "pp":
         case "ss":
            draw(userChoice, cpuChoice);
            break;
       }
   }
}

function getCpuChoice(){ 
   const choices = ['p','r','s'];
   const randomChoice = (Math.floor(Math.random()*3)); 
   return choices[randomChoice];
}

function main(){

   document.getElementById("save").disabled = true;

   paperDiv.addEventListener('click', function(){
                            game("p");
                            });

   rockDiv.addEventListener('click', function(){
                            game("r");
                            });

   scissorDiv.addEventListener('click', function(){
                            game("s");
                            });
}

main();
