package OldQuester.quests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import web.RSWeb;
import OldQuester.DeltaOldQuester;
import OldQuester.OldMethod;
import OldQuester.OldNode;

public class OldCooksAssistant extends OldNode{

	public OldCooksAssistant(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return DeltaOldQuester.scriptSelect==3;
	}
	OldMethod Method = new OldMethod(ctx);
	
	private int MILKBUCKET = 1927;
	
	private boolean init = true;
	
	private boolean hasMilk = false;
	private boolean hasEgg = false;
	private boolean hasFlour = false;

	@Override
	public void execute() {
		
		
		
			if(hasMilk==false && Method.inventoryContains(MILKBUCKET)){
				System.out.println("Found milk");
				hasMilk = true;
			}
			if(hasEgg==false && Method.inventoryContains(EGG)){
				System.out.println("Found egg");
				hasEgg = true;
			}
			if(hasFlour==false && Method.inventoryContains(POTFLOUR)){
				System.out.println("Found flour");
				hasFlour = true;
			}
			
		if((ctx.varpbits.varpbit(29)) ==2){
			//COMPLETED QUEST!
			Method.state("COMPLETED QUEST!!");
			Method.sleep(2000);
			DeltaOldQuester.shutOff = true;
		}else
		if((ctx.varpbits.varpbit(29)) ==1){
			cs1();//Gather/make the items and finish the quest by speaking to the cook again
		}else
		if((ctx.varpbits.varpbit(29)) ==0){
			cs0();//Speak to the cook and start the quest
		}
		
	}

	boolean cowPenOpen = false;
	private int COWGATE = 7158;
	private int COW = 2691;
	
	private int EGGGATE = 7158;
	private int EGG = 1944;
	private int POTFLOUR = 1933;
	
	private int WHEATITEM  = 1947;
	private int WHEATOBJ  =15507;
	
	private int WHEATGATE = 7160;
	
	private boolean hasWheat = false;
	
	private int MILLDOOR = 7111;
	private int LADDERBOTTOM = 12964;
	private int LADDERSECOND = 12965;
	private int LADDERTOP = 12966;
	private int HOPPER = 24961;
	private int CONTROLS = 24964;
	private int BIN  =1782;
	
	private void cs1() {//Gather/make the items and finish the quest
		
		final int[] bounds = {100, 152, -84, 0, -124, 104};//egg
		final int[] bounds2 = {-116, 132, -132, 8, -12, 0};//wheat
		final int[] bounds3 = {-128, 144, -152, 36, 112, 160};//mill
		
		if(ctx.game.floor()==2){
			hasWheat = true;
		}
		
		
		if(hasMilk){
			
			if(hasEgg){
				
				if(hasFlour){
					
					 if(Method.tileDisctanceToPlayer(new Tile(3167,3302,0))<10 && !new Tile(3167,3300,0).matrix(ctx).reachable()){
						  Method.getPastDoor(new Tile(3166,3304,0),new Tile(3166,3301,0), MILLDOOR,bounds3);
					  }else{//if not trapped in the mill
						  
						  cs0();
						  
					  }
					
				}else{//get the flour!
					if(Method.tileDisctanceToPlayer(new Tile(3237,3295,0))<15 && !new Tile(3237,3295,0).matrix(ctx).reachable()){
						Method.getPastDoor(new Tile(3235,3295,0),new Tile(3238,3296,0), EGGGATE, bounds);
					}else{//if not trapped in egg area..
						if(ctx.varpbits.varpbit(695)==1){//a repeat of the bottom part, this is when we have flour waiting for us
							
							   if(ctx.game.floor()==2){
								   Method.interactO(LADDERTOP, "", "top ladder");
							   }else
								   if(ctx.game.floor()==1){
									   Method.interactO(LADDERSECOND, "Climb-down", "top ladder");
								   }else
							  if(Method.tileDisctanceToPlayer(new Tile(3163,3291,0))<15 && !new Tile(3162,3288,0).matrix(ctx).reachable()){//if stuck in wheat pen..
								  Method.getPastDoor(new Tile(3163,3292,0),new Tile(3162,3288,0) ,WHEATGATE,bounds2);
							  }else{//if not stuck in wheat pen...
								  
								  if(Method.tileDisctanceToPlayer(new Tile(3167,3302,0))<10){
									  if(Method.getPastDoor(new Tile(3166,3301,0) , new Tile(3166,3304,0), MILLDOOR,bounds3)){
										 Method.interactO(BIN, "", "Flour bin");
									  }
								  }else{
									  Method.state("Walking to mill");
										RSWeb web = new RSWeb(ctx);
										web.walkToTile(new Tile(3167,3302,0));
								  }
								  
							  }
							
							
							
						}else 
						if(hasWheat){
							
							if(!Method.inventoryContains(WHEATITEM)){
								Method.interactO(CONTROLS, "", "Controls");
							}else
							if(ctx.game.floor()==2){
								Method.useItemOn(WHEATITEM, HOPPER, "");
							}else
							if(ctx.game.floor()==1){
								Method.interactO(LADDERSECOND, "Climb-up", "Ladder second");
							}else
							
						  if(Method.tileDisctanceToPlayer(new Tile(3163,3291,0))<15 && !new Tile(3162,3288,0).matrix(ctx).reachable()){//if stuck in wheat pen..
							  Method.getPastDoor(new Tile(3163,3292,0),new Tile(3162,3288,0) ,WHEATGATE,bounds2);
						  }else{//if not stuck in wheat pen...
							  
							  if(Method.tileDisctanceToPlayer(new Tile(3167,3302,0))<10){
								  if(Method.getPastDoor(new Tile(3166,3301,0) , new Tile(3166,3304,0), MILLDOOR,bounds3)){
									  if(Method.getToNearByTile(new Tile(3165,3306,0))){//ladder tile
										  Method.interactO(LADDERBOTTOM, "", "Ladder bottom");
									  }
								  }
							  }else{
								  Method.state("Walking to mill");
									RSWeb web = new RSWeb(ctx);
									web.walkToTile(new Tile(3167,3302,0));
							  }
							  
						  }
							
							
							
						}else if(Method.inventoryContains(WHEATITEM)){
							hasWheat = true;
						}else {//gather some wheat
							
							
							if(Method.tileDisctanceToPlayer(new Tile(3163,3289,0))<10){
								if(Method.getPastDoor(new Tile(3162,3288,0),new Tile(3163,3292,0) ,WHEATGATE,bounds2)){
									Method.interactO(WHEATOBJ, "", "Wheat");
								}
							}else{
							
							Method.state("Walking to wheat pen");
							RSWeb web = new RSWeb(ctx);
							web.walkToTile(new Tile(3164,3289,0));
							
							}
							
							
						}
						
					
						
						
					}
					
				}
				
				
				
			}else
			if(Method.tileDisctanceToPlayer(new Tile(3254,3270,0))<15 && !new Tile(3250,3267,0).matrix(ctx).reachable()){
				Method.getPastDoor(new Tile(3256,3268,0),new Tile(3251,3267,0), COWGATE);
			}else{//if NOT stuck in the cow pen..
				
				
				
				if(new Tile(3230,3299,0).matrix(ctx).reachable()&&
						Method.tileDisctanceToPlayer(new Tile(3238,3296,0))<10){//egg location in pen
					if(Method.getToNearByTile(new Tile(3230,3298,0)))
					Method.interactG(EGG, "Take","Egg");
					
				}else
				
				if(Method.tileDisctanceToPlayer(new Tile(3238,3296,0))<10){//egg pen
					Method.getPastDoor(new Tile(3238,3296,0), new Tile(3235,3295,0), EGGGATE, bounds);
				}else{
				Method.state("Walking to egg pen");
				RSWeb web = new RSWeb(ctx);
				if(!ctx.players.local().inMotion())
				web.walkToTile(new Tile(3238,3296,0));
				}
			}
			
			
			
		}else//get the milk!
		
		if(Method.tileDisctanceToPlayer(new Tile(3255,3271,0))<15){
			
			if(Method.getPastDoor(new Tile(3251,3267,0), new Tile(3256,3268,0), COWGATE)){
				if(Method.getToNearByTile(new Tile(3256,3272,0))){//tile by the cow
					Method.npcInteract(COW, "Milk");
				}
			}
			
		}else{
		Method.state("Walking to cow pen");
		RSWeb web = new RSWeb(ctx);
		web.walkToTile(new Tile(3252,3267,0));
		}
	}

	private int COOK = 4626;
	private void cs0() {//Speak to the cook and start the quest
	   
		final String[] opt = {"I'm always happy","What's wrong"};
		
		if(Method.tileDisctanceToPlayer(new Tile(3209,3213,0))<7){//Cook's Loc
			Method.generalChatWithNPC(opt, COOK);
		}else{
			Method.state("Walking to the Lumbridge cook");
			RSWeb web = new RSWeb(ctx);
			web.walkToTile(new Tile(3209,3213,0));
		}
		
	}

}
