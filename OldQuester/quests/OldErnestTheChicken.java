package OldQuester.quests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.RSWeb;
import OldQuester.DeltaOldQuester;
import OldQuester.OldMethod;
import OldQuester.OldNode;

public class OldErnestTheChicken extends OldNode{

	public OldErnestTheChicken(ClientContext ctx) {
		super(ctx);
	}

	private int LARGEDOOR_ID = 135;//mansion door
	
	private int COMMONDOOR_ID = 11470;//door basic
	
	private int STAIRS_ID = 11498;//stairs we first see upon entering
	
	private int STAIRSUP_ID = 11511;//second stairs
	
	private int STAIRSTOP_ID = 9584;//stairs leading down from top floor
	
	private int STAIRSSECONDDOWN_ID = 11499;//second floor stairs leading down
	
	private int VERONICA_ID = 3561;//Veronica ID
	
	private int SCIENTIST_ID = 3562;//Scientist ID
	
	
	
	@Override
	public boolean activate() {
		return DeltaOldQuester.scriptSelect==5;
	}
	OldMethod Method = new OldMethod(ctx);

	@Override
	public void execute() {
		DeltaOldQuester.numSteps = 3;
		
		if((ctx.varpbits.varpbit(32)) ==2){//the setting'32' changed from 1 to 2 after talking with scientist
			DeltaOldQuester.progress=3;
			cs2();
		}else
		if((ctx.varpbits.varpbit(29)) ==1 || (ctx.varpbits.varpbit(29)) ==2){
			DeltaOldQuester.progress=2;
			cs1();//Speak to the scientist at the top of the mansion
		}else
		if((ctx.varpbits.varpbit(29)) ==0){
			DeltaOldQuester.progress=1;
			cs0();//Speak to Veronica and start the quest
		}
	
		
	}

	private void cs2() {
		
		final int[] CommonDoorBounds1 = {-60, 68, -212, 0, 80, 32};
		final int[] CommonDoorBounds2 = {12, 132, -208, 36, -36, 32};
		
		//get out of area with scientist
		if(ctx.game.floor()==1){
			
			if(new Tile(3106,3363,1).matrix(ctx).reachable()){//second floor at main stairs
				  Method.interactO(STAIRSSECONDDOWN_ID, "Climb", "Door");
			}
			
		}else
		if(ctx.game.floor()==2){
			
			if(new Tile(3105,3364,2).matrix(ctx).reachable()){//outside scientist room by ladder down
				  Method.interactO(STAIRSTOP_ID, "Climb", "Stairs");
			}else
			if(new Tile(3110,3364,2).matrix(ctx).reachable()){//inside scientist room
				  Method.interactO(COMMONDOOR_ID, "Open", "Door");
			}
		}else{//above segment of code tries to get player out of scientist area and go down stairs
			
			
			//code for getting to basement
			
			if(new Tile(3102,3360,0).matrix(ctx).reachable()){//area before secret bookcase
				
			}else
			if(new Tile(3106,3370,0).matrix(ctx).reachable()){
				if(Method.getToTile(new Tile(3103,3365,0),3))
					 Method.getPastDoor(new Tile(3103,3365,0) , new Tile(3103,3363,0), COMMONDOOR_ID,CommonDoorBounds2);
			}else 
			if(new Tile(3106,3367,0).matrix(ctx).reachable()){
				if(Method.getToTile(new Tile(3106,3368,0),3))
				 Method.getPastDoor(new Tile(3105,3367,0) , new Tile(3106,3370,0), COMMONDOOR_ID,CommonDoorBounds1);
			}else cs1();//enter draynor mansion
			
			
		}
	}

	private void cs1() {//Speak to the scientist at the top of the mansion
		
		final int[] LargeDoorBounds = {-116, 132, -248, 0, 88, 156};
		final int[] CommonDoorBounds = {24, 124, -260, 24, -32, 32};
		
		final String[] opt = {"Change him back","I'm looking for a guy"};
		
		
		if(ctx.game.floor()==2){
				
			if(new Tile(3110,3365,2).matrix(ctx).reachable()){//inside scientist room
				  if(Method.getToNearByTile(new Tile(3110,3365,2))){
						Method.generalChatWithNPC(opt, SCIENTIST_ID);
				  }
			  }else
			  if(new Tile(3105,3364,2).matrix(ctx).reachable()){//top flr outside the scientist room
				  Method.interactO(COMMONDOOR_ID, "Open", "Door");
			  }
		}else
		if(ctx.game.floor()==1){
			
			  if(Method.getToNearByTile(new Tile(3105,3364,1))){//by second stairs
				  Method.state("Now by stairs");
				  Method.interactO(STAIRSUP_ID, "Climb", "Stairs");
			  }
			
		}else
		  if(new Tile(3109,3360,0).matrix(ctx).reachable()){//area by stairs
			  if(Method.getToNearByTile(new Tile(3108,3360,0))){//by stairs
				  Method.state("Now by stairs");
				  Method.interactO(STAIRS_ID, "Climb", "Stairs");
			  }
			 
		  }else if(new Tile(3109,3356,0).matrix(ctx).reachable()){//inside the mansion after big door
			  Method.getPastDoor(new Tile(3109,3356,0) , new Tile(3108,3360,0), COMMONDOOR_ID,CommonDoorBounds);
		  }else
		  if(Method.tileDisctanceToPlayer(new Tile(3108,3352,0))<10){//outside mansion
			  if(Method.getPastDoor(new Tile(3108,3352,0) , new Tile(3108,3356,0), LARGEDOOR_ID,LargeDoorBounds)){
				  
				Method.state("Got past mansion door");
				Method.clickOnMap(new Tile(3109,3360,0));//by bottom staris leading up
				
			  }
		  }else{
			  Method.state("Walking to Draynor mansion");
				RSWeb web = new RSWeb(ctx);
				web.walkToTile(new Tile(3108,3352,0));
		  }
		
		
	}

	private void cs0() {//Start quest by speaking with Veronica outside Draynor mansion
		final String[] opt = {"Aha, sounds"};
		
		if(Method.tileDisctanceToPlayer(new Tile(3109,3330,0))<7){//Veronica's Loc
			Method.generalChatWithNPC(opt, VERONICA_ID);
		}else{
			Method.state("Walking to the Veronica");
			RSWeb web = new RSWeb(ctx);
			web.walkToTile(new Tile(3109,3330,0));
		}
		
	}

}
