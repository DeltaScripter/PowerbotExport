/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class MythsOfTheWhiteLands extends Node{

	public final Tile[] pathToExplorerJack = new Tile[] { //He's in Lumbridge
			new Tile(3231, 3221, 0), new Tile(3231, 3226, 0), new Tile(3228, 3230, 0), 
			new Tile(3225, 3234, 0), new Tile(3222, 3238, 0), new Tile(3221, 3243, 0), 
			new Tile(3218, 3247, 0), new Tile(3213, 3247, 0), new Tile(3208, 3247, 0), 
			new Tile(3205, 3243, 0), new Tile(3205, 3240, 0) };
	
	public final Tile[] pathToWizardTowerCenter = new Tile[] { 
			new Tile(3233, 3222, 0), new Tile(3233, 3227, 0), new Tile(3230, 3232, 0), 
			new Tile(3226, 3235, 0), new Tile(3223, 3239, 0), new Tile(3220, 3243, 0), 
			new Tile(3216, 3246, 0), new Tile(3211, 3246, 0), new Tile(3206, 3246, 0), 
			new Tile(3201, 3247, 0), new Tile(3196, 3247, 0), new Tile(3192, 3244, 0), 
			new Tile(3187, 3244, 0), new Tile(3182, 3247, 0), new Tile(3178, 3244, 0), 
			new Tile(3174, 3241, 0), new Tile(3169, 3240, 0), new Tile(3164, 3240, 0), 
			new Tile(3160, 3237, 0), new Tile(3155, 3235, 0), new Tile(3150, 3236, 0), 
			new Tile(3145, 3235, 0), new Tile(3142, 3231, 0), new Tile(3137, 3229, 0), 
			new Tile(3132, 3228, 0), new Tile(3127, 3226, 0), new Tile(3124, 3222, 0), 
			new Tile(3119, 3222, 0), new Tile(3114, 3223, 0), new Tile(3109, 3224, 0), 
			new Tile(3103, 3299, 0), new Tile(3105, 3294, 0), new Tile(3107, 3289, 0), 
			new Tile(3107, 3284, 0), new Tile(3105, 3279, 0), new Tile(3103, 3274, 0), 
			new Tile(3102, 3269, 0), new Tile(3102, 3264, 0), new Tile(3102, 3259, 0), 
			new Tile(3104, 3254, 0), new Tile(3104, 3249, 0), new Tile(3104, 3244, 0), 
			new Tile(3104, 3239, 0), new Tile(3104, 3234, 0), new Tile(3107, 3230, 0), 
			new Tile(3109, 3225, 0), new Tile(3107, 3220, 0), new Tile(3104, 3215, 0), 
			new Tile(3101, 3211, 0), new Tile(3100, 3206, 0), new Tile(3100, 3201, 0), 
			new Tile(3100, 3196, 0), new Tile(3100, 3191, 0), new Tile(3102, 3186, 0), 
			new Tile(3102, 3181, 0), new Tile(3102, 3176, 0), new Tile(3102, 3171, 0), 
			new Tile(3102, 3166, 0), new Tile(3102, 3161, 0), new Tile(3103, 3156, 0) };
	
	public Timer timer = new Timer(0);
	public Tile initTile;
	public Tile initTile2;
	public boolean activate() {
		return DeltaQuester.scriptToStart==30;
	}

	
	public void execute() {
		DeltaQuester.numSteps = 9;
		if((Settings.get(2774)&0x3F) == 60){
			Method.state("Done quest");
		}else
		if((Settings.get(2774)&0xF) == 10||(Settings.get(2774)&0x1F) == 20){
			//Progress is within this method
			cs1();
		}else
		if((Settings.get(2774)&0x7) == 5){
			DeltaQuester.progress = 2;
			cs0();//Speak with Jack to gather more information on the myth
		}else
		if((Settings.get(2774)&0x7) == 0){
			DeltaQuester.progress = 1;
			cs0();//Speak to explorer jack to begin the quest
		}
		
	}


	private void cs1() {
		final String opt[] ={"I'm a friend of Explorer"};
		SceneObject pad = SceneEntities.getNearest(41625);
		if((Settings.get(2774)>>7&0x7)==6&& SceneEntities.getNearest(41633)!=null){
			
			DeltaQuester.progress = 9;
			if((Settings.get(2774)>>4 &0x1) !=1){
				if(Players.getLocal().isIdle()){
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
					Task.sleep(4000);
				}
			}else
			if(initTile2!=null){
				NPC iceblock = NPCs.getNearest(8298);
				Method.state("" + (initTile2.x() - Players.getLocal().getLocation().x()) + ": " + 
						(initTile2.getY() - Players.getLocal().getLocation().getY()));
				
				
				if(new Tile(initTile2.x()+6, initTile2.getY()+3,0).distanceTo()<1){
					Method.state("Very good");
				}else
				if(new Tile(initTile2.x()+1, initTile2.getY()+3,0).distanceTo()<1){
					if(iceblock!=null){
						if(iceblock.getLocation().distanceTo()<2){
						Method.npcInteract(8298, "Push");
						}else if(Players.getLocal().isIdle()){
							new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
							Task.sleep(4000);
						}
					}
				}else
				if(new Tile(initTile2.x(), initTile2.getY()+2,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY()+1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+2, initTile2.getY()+2,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+2, initTile2.getY()-3,0).distanceTo()<1){
					if(iceblock!=null){
						if(iceblock.getLocation().distanceTo()<2){
						Method.npcInteract(8298, "Push");
						}else if(Players.getLocal().isIdle()){
							new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
							Task.sleep(4000);
						}
					}
				}else
				if(new Tile(initTile2.x()+5, initTile2.getY()-6,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY()+1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+5, initTile2.getY()-2,0).distanceTo()<1){
					if(iceblock!=null){
						if(iceblock.getLocation().distanceTo()<2){
						Method.npcInteract(8298, "Push");
						}else if(Players.getLocal().isIdle()){
							new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()-1,0).click(true);
							Task.sleep(4000);
						}
					}
				}else
				if(new Tile(initTile2.x()+5, initTile2.getY()-1,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()-1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+1, initTile2.getY()-1,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else if(Players.getLocal().isIdle()){
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY()-1,0).click(true);
					Task.sleep(4000);
				}
				
			}else{
				Task.sleep(4000);
				initTile2 = Players.getLocal().getLocation();
			}
		}else
		if((Settings.get(2774)>>7&0x7)==5&& SceneEntities.getNearest(41629)!=null){
			DeltaQuester.progress = 8;
			if(initTile!=null){
				initTile2 = null;
				Method.state("Navigating maze # 6");
				if(new Tile(initTile.x()+11, initTile.getY()+1,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x()+12, initTile.getY()+2,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY()-1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x()+1, initTile.getY()+2,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x()+3, initTile.getY(),0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY()+1,0).click(true);
						Task.sleep(4000);
					}
				}else if(Players.getLocal().isIdle()){
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
					Task.sleep(4000);
				}
				
			}else if(pad!=null){
				if(pad.getLocation().equals(Players.getLocal().getLocation())){
				Task.sleep(4000);
				initTile = Players.getLocal().getLocation();
				}else Method.state("It does not equal players location");
			}
		}else
		if((Settings.get(2774)>>7&0x7)==4&& SceneEntities.getNearest(41629)!=null){
			DeltaQuester.progress = 7;
			if(initTile2!=null){
				initTile = null;
				Method.state("Navigating maze # 5");
				if(new Tile(initTile2.x()+11, initTile2.getY(),0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+13, initTile2.getY()+2,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY()-1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+7, initTile2.getY()+8,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY()-1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+7, initTile2.getY()+4,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+2, initTile2.getY()+4,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+2, initTile2.getY()+2,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
						Task.sleep(4000);
					}
				}else if(Players.getLocal().isIdle()){
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY()+1,0).click(true);
					Task.sleep(4000);
				}
				
			}else if(pad!=null){
				if(pad.getLocation().equals(Players.getLocal().getLocation())){
				Task.sleep(4000);
				initTile2 = Players.getLocal().getLocation();
				}else Method.state("It does not equal players location");
			}
		}else
		if((Settings.get(2774)>>7 &0x3) ==3 && SceneEntities.getNearest(41629)!=null){
			DeltaQuester.progress = 6;
			if(initTile!=null){
				initTile2 = null;
				Method.state("Navigating maze # 4");
				if(new Tile(initTile.x()+8, initTile.getY(),0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x()+8, initTile.getY()+5,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()-1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x()+4, initTile.getY()+5,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x()+4, initTile.getY()+4,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile.x(), initTile.getY()+8,0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY()-1,0).click(true);
						Task.sleep(4000);
					}
				}else if(Players.getLocal().isIdle()){
					new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
				
				}
			}else if(pad!=null){
				if(pad.getLocation().equals(Players.getLocal().getLocation())){
				Task.sleep(4000);
				initTile = Players.getLocal().getLocation();
				}else Method.state("It does not equal players location");
			}
			
		}else
		if(SceneEntities.getNearest(41661)!=null && (Settings.get(2774)>>8 & 0x3) ==1){
			DeltaQuester.progress = 5;
			if(initTile2!=null){
				initTile = null;
				Method.state("Navigating maze # 3");
				
				if(new Tile(initTile2.x()+11, initTile2.getY(),0).distanceTo()<1){
					if(Players.getLocal().isIdle()){
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
						Task.sleep(4000);
					}
				}else
				if(new Tile(initTile2.x()+11, initTile2.getY()-1,0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
					
				}else
				if(new Tile(initTile2.x()+8, initTile2.getY()-1,0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
					
				}else
				if(new Tile(initTile2.x()+9, initTile2.getY(),0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY()-1,0).click(true);
					
				}else if(Players.getLocal().isIdle())
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
				
			}else{
				Task.sleep(4000);
				initTile2 = Players.getLocal().getLocation();
			}
		}else
		if(SceneEntities.getNearest(41633)!=null){//Upon entering the first cave
			DeltaQuester.progress = 4;
			if(initTile!=null){
				Method.state("Navigating maze # 2");
				
				if(new Tile(initTile.x()+12, initTile.getY(),0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
					
				}else
				if(new Tile(initTile.x()+12, initTile.getY()+4,0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()-1,0).click(true);
					
				}else
				if(new Tile(initTile.x()+8, initTile.getY()+4,0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
					
				}else
				if(new Tile(initTile.x()+8, initTile.getY()+8,0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()-1,0).click(true);
					
				}else
				if(new Tile(initTile.x()+10, initTile.getY()+8,0).distanceTo()<1){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x()-1,Players.getLocal().getLocation().getY(),0).click(true);
					
				}else
				if(new Tile(initTile.x()+10, initTile.getY(),0).distanceTo()<3){
					if(Players.getLocal().isIdle())
						new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
					
				}else if(Players.getLocal().isIdle())
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
				
				
			}else initTile = Players.getLocal().getLocation();
			
			
		}else//Enters the first cave from the outside
			if(!Method.isChatting("Cutscene"))
		if(new Tile(2069,5803,0).canReach()){
			initTile = null;
			initTile2 = null;
			DeltaQuester.progress = 3;
			Method.state("Navigating maze # 1");
			if(new Tile(2093,5795,0).distanceTo()<7){
				if(new Tile(2093,5795,0).distanceTo()<4){
				Method.interactO(41665, "Squeeze", "Cave");
				}else new Tile(2093,5795,0).clickOnMap();
			}else
			if(new Tile(2083,5795,0).distanceTo()<4){
				if(Players.getLocal().isIdle())
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
			
			}else
			if(new Tile(2083,5783,0).distanceTo()<4){
				if(Players.getLocal().isIdle())
					new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()+1,0).click(true);
			}else
			if(new Tile(2069,5783,0).distanceTo()<4){
				if(Players.getLocal().isIdle())
					new Tile(Players.getLocal().getLocation().x()+1,Players.getLocal().getLocation().getY(),0).click(true);
			}else if(Players.getLocal().isIdle())
				new Tile(Players.getLocal().getLocation().x(),Players.getLocal().getLocation().getY()-1,0).click(true);
		}else
		if(new Tile(3093,3154,1).canReach()){//Second floor of wizards tower
			if(new Tile(3093,3154,1).distanceTo()<7){
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Isidor")){
					Method.speakTo(16175, "Isidor");
				}
			}
			}else Method.findPath(new Tile(3093,3154,1));
		}else
		if(new Tile(3103, 3156, 0).distanceTo()<8){
			if(!timer.isRunning()){
			Method.interactO(79770, "Ascend", "Beam of light");
			timer = new Timer(3500);
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToWizardTowerCenter, "Walking to the Wizard Tower", false);
		}else if(Vars.DRAYNORLODE.distanceTo()<10 || Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(Vars.DRAYNORTELEPORT);
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}


	private void cs0() {
		final String opt[] = {"How do I get to the","Myths of the White","I'd like to try the quest"};
		if(new Tile(3204,3242,0).distanceTo()<8){
			if(!Method.startQuestOpen()){
			if (!Method.findOption(opt)) {
				Vars.DYNAMICV = false;
				if (!Method.isChatting("Explorer Jack")) {
					Method.speakTo(7969, "Explorer Jack");
				}
			}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToExplorerJack, "Walking to Explorer Jack", false);
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}

}
*/