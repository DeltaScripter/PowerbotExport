
package quests;


import java.util.ArrayList;

import org.powerbot.script.rt6.ClientContext;



public class testquest extends DeltaNode{

public testquest(ClientContext ctx) {
super(ctx);
}//end bracket for ending the script constructor

//Variables..
public ArrayList<Integer> goalsName = new ArrayList<Integer>();
public boolean start = true;//is one start initially to run only once
public Method Method = new Method(ctx);


//this is where path will go, below me:
//our functions
public boolean activate(){
return DeltaQuester.scriptToStart==42;
}//the end bracket for the activate method





public void onStart(){

if(start){
goalsName.add(1+0);//for the path0 interacting with object

//add stuff to array
start = false;//sets it to false, so it runs only once
}//the end bracket for the start if statement

}//the end bracket for the onStart method




public void execute(){
onStart();

if(goalsName.size()>=1){
//begin of block for cs0

//position for stuff in the loop+0
if(goalsName.size()>=1&&goalsName.get(0)==(1+0)){
	while(Method.inventoryGetCount(2434)<23423){//0
if(Method.interactOReturn(4324,"234")){
}//ending bracket for inserted while loop0
goalsName.remove(0);//removing goal0 of clicking object, path0
}//end of interact obj block0
}else goalsName.remove(0);//I AM A COOKIE, method count:1 closing bracket for specific goal:1
//end bracket for cs0
}else System.out.println(0);//end bracket for the overall goal>=1 if statement



}//the end bracket for the execute method









}//this is the end of the script bracket
