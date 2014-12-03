package OldQuester.quests;


import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.RSWeb;
import OldQuester.DeltaOldQuester;
import OldQuester.OldMethod;
import OldQuester.OldNode;

public class OldRestlessGhost extends OldNode{

	//npcs
	private int PRIESTID = 921;
	private int SWAMPPRIEST = 923;
	private int GHOST = 922;
	
	//objects
	private int SWAMPDOOR = 15056;
	private int COFFIN = 2145;
	private int OPENCOFFIN = 15052;
	private int GHOSTDOOR = 7122;
	
	

	
	
	OldMethod Method = new OldMethod(ctx);
	public OldRestlessGhost(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return DeltaOldQuester.scriptSelect==1;
	}

	private boolean init = false;
	private int AMULET = 552;

	
	Tile dest = new Tile(-1,-1,-1);
	@Override
	public void execute() {
		DeltaOldQuester.numSteps = 6;
		// RSWeb web = new RSWeb(ctx);
		 //web.walkToTile(new Tile(3252,3404, 0));//some tile
		//Method.state("Closest node to you: " + web.getNearestNode(ctx.players.local().tile()));
		
		/*
		if(ctx.movement.destination().matrix(ctx).reachable()&&
				dest!=ctx.movement.destination()&& !ctx.players.local().inMotion()){
		    Tile prev = dest;
			dest = ctx.movement.destination();
			//System.out.println("Dest is now : " + dest);  
		if(ctx.movement.destination()!=prev){	
		System.out.println("new WebLine(ctx, new Tile"+prev + ",new Tile" + ctx.movement.destination() + "),");
		Method.sleep(3000);
		Method.state("Not ready - SLEEPING!");
		}
		Method.state("Ready to click!");
		}*/
		
		///*
			while(Method.inventoryContains(AMULET)){
				Method.interactInventory(AMULET, "Wear", "Amulet");
			}
		
		if((ctx.varpbits.varpbit(107)) ==5){
			DeltaOldQuester.progress=6;
			//COMPLETED QUEST!
			Method.state("COMPLETED QUEST!!");
			Method.sleep(2000);
			DeltaOldQuester.shutOff = true;
		}else		
		if((ctx.varpbits.varpbit(107)) ==4){
			DeltaOldQuester.progress=5;
          cs4();//get back to lumbridge and place skull in coffin
		}else	
		if((ctx.varpbits.varpbit(107)) ==3){
			DeltaOldQuester.progress=4;
	      cs3();//Go get the skull from the wizards tower
		}else	
		if((ctx.varpbits.varpbit(107)) ==2){
			DeltaOldQuester.progress=3;
	      cs2();//Speak to the ghost
		}else
		if((ctx.varpbits.varpbit(107)) ==1){
			DeltaOldQuester.progress=2;
		  cs1();//Speak to the swamp priest and get the ghost amulet
		}else{
			DeltaOldQuester.progress=1;
		  cs0();//Speak to the priest (Aereck) and begin the quest
		}
		//*/
		
	}
	private int BASEMENTLADDER = 2148;
	private int SKULL = 553;
	
	private void cs4() {//get to lumbridge and place skull in coffin
		
   if(Method.tileDisctanceToPlayer(new Tile(3110,3169,0))<15 && !new Tile(3110,3169,0).matrix(ctx).reachable()||
		   new Tile(3119,9566,0).matrix(ctx).reachable()||
		   new Tile(3108,9562,0).matrix(ctx).reachable()){//tile outside wizard tower
			
	if(Method.getPastDoor(new Tile(3105,3161,0), new Tile(3109,3164,0),SECONDTOWERDOOR)){
		Method.getPastDoor(new Tile(3109,3164,0), new Tile(3110,3169,0), FIRSTTOWERDOOR);
	}else
		if(Method.getPastDoor(new Tile(3112,9559,0), new Tile(3109,9559,0), BASEMENTDOOR)){//the door by the altar in our way
			if(Method.getToNearByTile(new Tile(3104,9576,0))){//tile by ladder leading out of basement
				Method.interactO(BASEMENTLADDER, "", "Basement ladder");
			}
		}
      }else{//If FREE of the wizards tower and all it's wonderful doors..
    	 
    	  if(Method.tileDisctanceToPlayer(new Tile(3245,3193))<15){//ghost shack area
  			
  			if(new Tile(3249,3194).matrix(ctx).reachable()){//inside ghost shack
  				if(Method.getToNearByTile(new Tile(3249,3194))){//inside ghost shack
  					//open up the coffin!
  				  if(Method.objIsNotNull(COFFIN)){//open the coffin
  						final int[] bounds = {-104, 124, -72, 0, -52, 32};
  						Method.preciseInteractO(COFFIN, bounds, "");
  					
  					}else if(Method.objIsNotNull(OPENCOFFIN)){//open the coffin
  						Method.useItemOn(SKULL, OPENCOFFIN, "");
  					}
  				}
  			}else if(Method.getToNearByTile(new Tile(3245,3193))){//outside the ghost shack
  				//open ghost door
  				final int[] bounds = {8, 28, -204, -8, 120, -12};
  					Method.preciseInteractO(GHOSTDOOR, bounds, "Open");
  				
  			}
  			
  			
  		}else{//walk to the ghost area
  			Method.state("Walking to the ghost's shack");
			
  			 RSWeb web = new RSWeb(ctx);
			 web.walkToTile(new Tile(3245,3193));//walk to the ghost's shack
			 /*
  			
  			ArrayList<Tile> dest = new ArrayList<Tile>();
  			dest.add(new Tile(3115,3210));//end of bridge
  			dest.add(new Tile(3208,3153));///the swamp
  			dest.add(new Tile(3245,3193));//final
  			
  			Method.walkTo(dest,"Walking to the ghost shack");//outside shack area
  		*/
  		}
    	  
    	  
    	  
      }
	}

	private int FIRSTTOWERDOOR = 12664;
	private int SECONDTOWERDOOR = 23972;
	private int LADDERTOP = 2147;
	private int ALTAR = 15050;
	private int BASEMENTDOOR = 7122;
	
private void cs3() {//get the skull from wizards tower
	if(Method.tileDisctanceToPlayer(new Tile(3248,3194))<15 && !new Tile(3245,3193).matrix(ctx).reachable()){//tile otuside of shack
		//open ghost door
		final int[] bounds = {8, 28, -204, -8, 120, -12};
			Method.preciseInteractO(GHOSTDOOR, bounds, "Open");
	}else{//if not stuck inside the ghost shack...
		//do other stuff..
		if(new Tile(3104,9576,0).matrix(ctx).reachable()){//basement stuff here
			
			if(Method.getPastDoor(new Tile(3110,9559,0),new Tile(3112,9559,0) ,BASEMENTDOOR)){//gets past the first door in our way
				if(Method.getToNearByTile(new Tile(3119,9566,0))){//altar tile
					Method.interactO(ALTAR, "", "Altar");
				}
			}
		}else//below gets us to the basement of wizard tower
		if(Method.tileDisctanceToPlayer(new Tile(3108,3168))<15){//outside the wizards tower AREA
			
		
			if(new Tile(3105,3161,0).matrix(ctx).reachable()){//tile by the trap door leading to basement
				if(Method.getToNearByTile(new Tile(3105,3161,0))){//Tile by the ladder leading down to basement
					Method.interactO(LADDERTOP, "", "Ladder leading to basement");
				}
			}else
			if(new Tile(3109,3165,0).matrix(ctx).reachable()){//inside first door of tower
				if(Method.getToNearByTile(new Tile(3109,3165,0)))//tile as above
				Method.interactO(SECONDTOWERDOOR, "", "Second door of wizard tower");//open the second door
			}else if(Method.getToNearByTile(new Tile(3109,3168,0))){//the tile JUST outisde the tower
				final int[] bounds = {-120, 32, -180, 0, -8, 32};//open the door
				Method.preciseInteractO(FIRSTTOWERDOOR, bounds, "Open");
			}
		}else{//walk to the wizards tower
			Method.state("Walking to the Wizards Tower");
			
			 RSWeb web = new RSWeb(ctx);
			 web.walkToTile(new Tile(3108,3168));//walk to the wizard's tower
			 
			 
			 /*
			
			ArrayList<Tile> dest = new ArrayList<Tile>();
			dest.add(new Tile(3208,3153));///the swamp
			dest.add(new Tile(3115,3210));//another end of bridge
			dest.add(new Tile(3108,3168));//final
			
			Method.walkTo(dest,"Walking to the Wizards Tower");
		*/
		}
		
	}
	
	
	}


	private void cs2() {//Speak to the ghost
		String opt[] = {"Yeah","Ooh...","Yep"};
		
		//get out of the priest's house first
		if(Method.tileDisctanceToPlayer(new Tile(3149,3175))<15 && !new Tile(3147,3171).matrix(ctx).reachable()){
			//open swamp door
			final int[] bounds = {112, -20, -208, 0, 160, 128};
				Method.preciseInteractO(SWAMPDOOR, bounds, "Open");
		}else if(Method.tileDisctanceToPlayer(new Tile(3245,3193))<15){//ghost shack area
			
			if(new Tile(3249,3194).matrix(ctx).reachable()){//inside ghost shack
				if(Method.getToNearByTile(new Tile(3249,3194))){//inside ghost shack
					
				  if(Method.npcIsNotNull(GHOST)){
						
					  if(!Method.findOption(opt))	
							if(!Method.isChatting("Ghost")){
								Method.npcInteract(GHOST, "");
							}
					  
					}else if(Method.objIsNotNull(COFFIN)){//open the coffin
						final int[] bounds = {-104, 124, -72, 0, -52, 32};
						Method.preciseInteractO(COFFIN, bounds, "");
					
					}else if(Method.objIsNotNull(OPENCOFFIN)){//open the coffin
						final int[] bounds = {-104, 124, -72, 0, -52, 32};
						Method.preciseInteractO(OPENCOFFIN, bounds, "");
					
					}
				}
			}else if(Method.getToNearByTile(new Tile(3245,3193))){//outside the ghost shack
				//open ghost door
				final int[] bounds = {8, 28, -204, -8, 120, -12};
					Method.preciseInteractO(GHOSTDOOR, bounds, "Open");
				
			}
			
			
		}else{//walk to the ghost area
			Method.state("Walking to the ghost's shack");
			
			 RSWeb web = new RSWeb(ctx);
			 web.walkToTile(new Tile(3245,3193));//walk to the ghost's shack
			 
			/*
			ArrayList<Tile> dest = new ArrayList<Tile>();
			dest.add(new Tile(3181,3154));//checkpoint by lummbridge mine
			dest.add(new Tile(3240,3173));//another chekcpoint
			dest.add(new Tile(3245,3193));//final
			
			Method.walkTo(dest,"Walking to the ghost shack");//outside shack area
			*/
		}
	}

	private void cs1() {//Speak to the swamp priest
		final String opt[] = {"He's got a ghost","sent me to talk"};
		
		
		if(Method.tileDisctanceToPlayer(new Tile(3242,3206))<15 && !new Tile(3236,3209).matrix(ctx).reachable()){//if inside church and can't reach the outside of the church
				if(Method.getToNearByTile(new Tile(3238,3210))){//tile inside church but by door exiting
					final int[] bounds = {120, -16, -228, -16, 144, 112};
  					Method.preciseInteractO(7129, bounds, "Open");
				}
			
		}else{
			if(Method.tileDisctanceToPlayer(new Tile(3147, 3171, 0))<15){
				
				if(new Tile(3147,3175).matrix(ctx).reachable()){//inside swamp priest house
					if(Method.getToNearByTile(new Tile(3147,3175))){//inside priest home
						
						  if(!Method.findOption(opt))	
								if(!Method.isChatting("Swamp Priest")){
									Method.npcInteract(SWAMPPRIEST, "");
								}
						
					}
				}else if(Method.getToNearByTile(new Tile(3147,3171))){
					//open swamp door
					final int[] bounds = {112, -20, -208, 0, 160, 128};
  					Method.preciseInteractO(SWAMPDOOR, bounds, "Open");
					
				}
				
			}else{
				Method.state("Walking to the priest in the swamp");
				
				 RSWeb web = new RSWeb(ctx);
	    		  web.walkToTile(new Tile(3147, 3171, 0));//walk to the priest in swamp
	    
				
				/*
				//walk to the swamp priest
				ArrayList<Tile> dest = new ArrayList<Tile>();
				dest.add(new Tile(3235,3164, 0));//checkpoint by lummbridge mine
				dest.add(new Tile(3147, 3171, 0));//final
				Method.walkTo(dest,"Walking to the swamp priest");//outside his home - b/c of door
			*/
				}
			
		}
	}

	private void cs0() {//Speak to the preist and start the quest
		
		final String opt[] = {"Ok, let me help then","I'm looking for a quest!"};
		
        	//new Tile(3242,3210,0).matrix(ctx).reachable()
			if(Method.tileDisctanceToPlayer(new Tile(3241,3205))<15){//inside the church
              if(new Tile(3242,3210,0).matrix(ctx).reachable()){//tile inside church
				if(Method.tileDisctanceToPlayer(new Tile(3241,3205))<6){//tile by the priest
					
				   if(!Method.findOption(opt))	
					if(!Method.isChatting("Priest")){
						Method.npcInteract(PRIESTID, "");
					}
					
				}else Method.clickOnMap(new Tile(3241,3205));//tile by the priest
              }else{
            	  if(Method.tileDisctanceToPlayer(new Tile(3237,3210,0))<4){//outside the church
					   
      				final int[] bounds = {120, -16, -228, -16, 144, 112};
      					Method.preciseInteractO(7129, bounds, "Open");
      					
      				 }else Method.clickOnMap(new Tile(3237,3210,0));//tile by outside door of church
              }
			}else {//walk to lummbridge
				Method.state("Walking to the Lumbridge church");
				 RSWeb web = new RSWeb(ctx);
	    		  web.walkToTile(new Tile(3237,3210,0));//walk to lumbridge entrance of church
	    
				/*
				ArrayList<Tile> dest = new ArrayList<Tile>();
				dest.add(new Tile(3237,3210,0));//final
				Method.walkTo(dest,"Walking to Lummbridge");//outside the church - b/c of door
				*/
			}
		
	}

}
