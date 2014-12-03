package OldQuester.quests;


import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.RSWeb;
import OldQuester.DeltaOldQuester;
import OldQuester.OldMethod;
import OldQuester.OldNode;

public class OldRuneMysteries extends OldNode{

	
	OldMethod Method = new OldMethod(ctx);
	public OldRuneMysteries(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return DeltaOldQuester.scriptSelect==2;
	}

	//objects
	private int BOTTOMSTAIRSLUMMY = 16671;
	private int DUKEDOOR = 7143;
	
	//npcs
	private int DUKE = 815;
	
	Tile dest = new Tile(-1,-1,-1);
	@Override
	public void execute() { 
		DeltaOldQuester.numSteps = 7;
		
		if(ctx.varpbits.varpbit(63)==6){
			DeltaOldQuester.progress=7;
			//COMPLETED QUEST!
			Method.state("COMPLETED QUEST!!");
			Method.sleep(2000);
			DeltaOldQuester.shutOff = true;
		}else
		if(ctx.varpbits.varpbit(63)==5){
			DeltaOldQuester.progress=6;
			cs1();//Give a package to Aubrey in Varrok
		}else
		if(ctx.varpbits.varpbit(63)==4){
			DeltaOldQuester.progress=5;
			cs2();//Talk to Aubrey again for his analysis
		}else
		if(ctx.varpbits.varpbit(63)==3){
			DeltaOldQuester.progress=4;
			cs2();//Give a package to Aubrey in Varrok
		}else
		if(ctx.varpbits.varpbit(63)==2){
			DeltaOldQuester.progress=3;
			cs1();//Talk to Sedridor(wizard) still -contining conversation...
		}else
		if(ctx.varpbits.varpbit(63)==1){
			DeltaOldQuester.progress=2;
			cs1();//Head to the wizards tower and show head wizard the talisman
		}else {
			DeltaOldQuester.progress=1;
			cs0();//Speak to Duke Horacio to begin the quest
		}
	
		
	}
	private int BASEMENTLADDER = 2148;
	private int DOORAUBREY = 11780;
	private int AUBREY = 637;
	private void cs2() {
		String opt[] = {"I have been sent here"};
		
		final int[] bounds = {12, 32, -204, 0, 120, -16};
		
		   if(Method.tileDisctanceToPlayer(new Tile(3110,3169,0))<15 && !new Tile(3110,3169,0).matrix(ctx).reachable()||//outside of wiz tower
				   new Tile(3119,9566,0).matrix(ctx).reachable()||
				   new Tile(3108,9562,0).matrix(ctx).reachable()||
				   new Tile(3104,9570,0).matrix(ctx).reachable()){//tile outside wizard tower
					
			if(Method.getPastDoor(new Tile(3105,3161,0), new Tile(3109,3164,0),SECONDTOWERDOOR)){
				Method.getPastDoor(new Tile(3109,3164,0), new Tile(3110,3169,0), FIRSTTOWERDOOR);
			}else 
				if(Method.getPastDoor(new Tile(3106,9570,0),new Tile(3109,9571,0), BASEMENTDOORBYWIZARD, bounds)){//the door by wizard
					if(Method.getToNearByTile(new Tile(3104,9576,0))){//tile by ladder leading out of basement
						Method.interactO(BASEMENTLADDER, "", "Basement ladder");
					}
				}
		      }else{//if free of the tower
		    	  
		    	  
		    	  final int[] bounds2 = {136, 12, -196, 24, 156, 136};
		    	  if(Method.tileDisctanceToPlayer(new Tile(3253,3398,0))<10){
		    		   if(Method.getPastDoor(new Tile(3253,3398,0), new Tile(3253,3401,0), DOORAUBREY, bounds2)){
		    			   if(Method.getToNearByTile(new Tile(3253,3401,0))){//tile by Aubrey in his home
		    				   if(!Method.findOption(opt))
		    					  if(!Method.isChatting("Aubrey"))
		    					   Method.npcInteract(AUBREY, "");
		    			   }
		    		   }
		    	  }else{
		    		  Method.state("Walking to Aubrey in Varrock");
		    		  //below walks to aubrey
		    		 RSWeb web = new RSWeb(ctx);
		    		  web.walkToTile(new Tile(3253,3396,0));//area by the essence tele guy
		    
		    	  }
		    	  
		      }
	}

	private int MIDDLESTAIRS = 16672;
	
	private int FIRSTTOWERDOOR = 12664;
	private int SECONDTOWERDOOR = 23972;
	private int LADDERTOP = 2147;
	private int BASEMENTDOORBYWIZARD = 7122;
	
	private int SEDRIDOR = 5034;
       private void cs1() {//go to wizard tower and speak to wizard in  basement
    	   
    		String opt[] = {"Yes, certainly","Ok, here you","I'm looking for the"};
    		 final int[] bounds2 = {136, 12, -196, 24, 156, 136};
    		 
    	   if(Method.tileDisctanceToPlayer(new Tile(3253,3400,0))<15 && !new Tile(3253,3397,0).matrix(ctx).reachable()){//are w ein Aubreys house?
    		   Method.getPastDoor(new Tile(3253,3401,0),new Tile(3253,3398,0), DOORAUBREY, bounds2);
    	   }else//below is if we ar enot trapped in Aubrey's house
		if(Method.tileDisctanceToPlayer(new Tile(3209,3223,1))<15 && ctx.game.floor()==1&&
				!new Tile(3206,3223,1).matrix(ctx).reachable()){//Are we trapped in the room w/duke?
			
			final int[] bounds = {148, 112, -228, -8, -4, 128};
				Method.getPastDoor(new Tile(3209,3223,1), new Tile(3206,3223,1), DUKEDOOR, bounds);
				
			}else if(ctx.game.floor()==1){
				if(!Method.getToTileAndClickObject(new Tile(3206,3228,1),MIDDLESTAIRS, "Climb-down")){//climb down the lummy stairs
					Method.state("Clicked on stairs");
				}
			}else if(new Tile(3105,9570,0).matrix(ctx).reachable()){// if in Sedridors room...
				if(Method.getToNearByTile(new Tile(3105,9570,0))){
					Method.speakToNPC(SEDRIDOR,opt);
				}
			}else if(new Tile(3104,9576,0).matrix(ctx).reachable()){//basement stuff here
					
					final int[] bounds = {12, 32, -204, 0, 120, -16};
					Method.getPastDoor(new Tile(3109,9571,0),new Tile(3106,9570,0), BASEMENTDOORBYWIZARD, bounds);
						
			}else//below gets us to the basement of wizard tower
				if(Method.tileDisctanceToPlayer(new Tile(3108,3168))<15){//outside the wizards tower AREA
				
				
				if(new Tile(3105,3161,0).matrix(ctx).reachable()){//tile by the trap door leading to basement
					if(Method.getToNearByTile(new Tile(3105,3161,0))){//Tile by the ladder leading down to basement
						Method.interactO(LADDERTOP, "", "Ladder leading to basement");
					}
				}else//below opens a bunch of doors to get to trap door..
				if(new Tile(3109,3165,0).matrix(ctx).reachable()){//inside first door of tower
					if(Method.getToNearByTile(new Tile(3108,3163,0)))//tile as above
					Method.interactO(SECONDTOWERDOOR, "", "Second door of wizard tower");//open the second door
				}else if(Method.getToNearByTile(new Tile(3109,3168,0))){//the tile JUST outisde the tower
					
					final int[] bounds = {-120, 32, -180, 0, -8, 32};//open the door
					Method.preciseInteractO(FIRSTTOWERDOOR, bounds, "Open");
					
				}
			}else{//walk to the wizards tower
				 Method.state("Walking to Sedridor in the wizard tower");
				  RSWeb web = new RSWeb(ctx);
	    		  web.walkToTile(new Tile(3108,3168));//area by tower
			}
			
		
	}

	private void cs0() {//Speak to Duke Harry to start quest
		final String opt[] = {"Sure, no problem","Have you any quests"};
		
		
		
		if(ctx.game.floor()==1){
			final int[] bounds = {148, 112, -228, -8, -4, 128};
			if(Method.getPastDoor(new Tile(3206,3222,1), new Tile(3208,3222,1), DUKEDOOR, bounds)){
				
				
				if(Method.getToNearByTile(new Tile(3210,3221,1)))
				   if(!Method.findOption(opt))	
						if(!Method.isChatting("Duke Horacio")){
							Method.npcInteract(DUKE, "");
						}
			}
		}else
		if(new Tile(3208,3226, 0).distanceTo(ctx.players.local())<15){
			if(Method.getToNearByTile(new Tile(3207,3227, 0))){//first stairs
				Method.interactO(BOTTOMSTAIRSLUMMY, "", "Stairs");
			}
		}else {//walk to lummbridge
			 Method.state("Walking to the duke in Lumbridge");
			  RSWeb web = new RSWeb(ctx);
    		  web.walkToTile(new Tile(3208,3226, 0));//lumbride tele area
    
		}
	}

}
