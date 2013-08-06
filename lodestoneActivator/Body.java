package lodestoneActivator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;

import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

import quests.Node;


@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Lodestone Unlocker", 
description = "Unlocks lodestones around the map", website = "",hidden =true,topic =816007, version = .5)
public class Body extends PollingScript{

	public final Tile pathToVarrock[] = new Tile[]{
			new Tile(3207,3233,0),new Tile(3217,3232,0), new Tile(3221,3223,0),
			new Tile(3233,3223,0), new Tile(3247,3226,0),new Tile(3260,3231,0),
			new Tile(3259,3241,0), new Tile(3255,3248,0), new Tile(3249,3255,0),
			new Tile(3249,3268,0), new Tile(3247,3270,0), new Tile(3241,3279,0),
			new Tile(3239,3292,0), new Tile(3238,3308,0), new Tile(3239,3323,0),
			new Tile(3240,3332,0), new Tile(3231,3335,0), new Tile(3225,3347,0),
			new Tile(3220,3358,0), new Tile(3217,3366,0), new Tile(3214,3376,0)};
	public final Tile pathToFalador[] = new Tile[] {
			new Tile(2900,3535,0), new Tile(2900,3525,0),new Tile(2899,3515,0),
			new Tile(2911,3505,0),new Tile(2917,3494,0),new Tile(2925,3486,0),
			new Tile(2924,3473,0),new Tile(2924,3462,0),new Tile(2922,3451,0),
			new Tile(2929,3440,0),new Tile(2940,3439,0),new Tile(2946,3434,0),
			new Tile(2952,3423,0),new Tile(2959,3414,0),new Tile(2967,3405,0)
	};
	
	private final List<Node> nodeList = Collections.synchronizedList(new ArrayList<Node>());
	private Timer teleportTimer = new Timer(0);
	private Timer walkingTimer = new Timer(0);
	
	public Body(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				     addNode(new walkTo(ctx));
			}
		});
	}
	@Override
	public int poll() {
		
		for(Node node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 200;
	}
	
	   private void addNode(final Node...nodes) {
		   
	        for(Node node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }
   class walkTo extends Node{

	   
	private boolean dynamicV;

	public walkTo(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		
		if((ctx.settings.get(3) >> 11 & 1)==1){//Varrok is active
			if((ctx.settings.get(3) >>6 &1)==1){
				System.out.println("Finished");
				getController().stop();
			}else unlockLodeStone(TeleportLode.FALADOR.getTile(), 69835,pathToFalador,TeleportLode.BURTHORPE.getTile(),TeleportType.BURTHHORPE.getTeleport());
		}else unlockLodeStone(TeleportLode.VARROCK.getTile(), 69840,pathToVarrock,TeleportLode.LUMMBRIDGE.getTile(),TeleportType.LUMBRIDGE.getTeleport());
		
	}



	private void unlockLodeStone(Tile lodeArea, int lodestoneID, Tile[] pathToLode,Tile teleLodeTile, int teleport) {
		if(lodeArea.distanceTo(ctx.players.local().getLocation())<7){
			dynamicV = false;
			for(GameObject lode: ctx.objects.select().id(lodestoneID).nearest().first()){
				lode.interact("Activate");
			}
		}else if(dynamicV){
			if(new Tile(2943,3440,0).distanceTo(ctx.players.local().getLocation())<8 && 
					objIsNotNull(28690) && objIsByTile(new Tile(2943,3440,0),28690,5)){
				for(GameObject gate: ctx.objects.select().id(28690).nearest().first()){
				 gate.interact("Open");
				}
			}else
			walk(pathToLode);
		}else if(teleLodeTile.distanceTo(ctx.players.local().getLocation())<7){
			dynamicV = true;
		}else teleportTo(teleport);
		
		
	}

	public boolean objIsByTile(Tile tile, int object, int dist) {
		for(GameObject obj : ctx.objects.select().id(object).nearest(tile)){
			if(obj.getLocation().distanceTo(tile)<dist){
				return true;
			}
		}
		return false;
	}
	public boolean objIsNotNull(int id) {
		if(!ctx.objects.select().id(id).first().isEmpty()){
			return true;
		}
		return false;
	}
	   
   }
   public void walk(Tile[] tile){
	   if(!walkingTimer.isRunning()){
		   ctx.movement.newTilePath(tile).traverse();
		   walkingTimer = new Timer(3800);
	   }
   }
   public void teleportTo(int tele){
	  
	   if(ctx.players.local().getAnimation()==-1 && !teleportTimer.isRunning())
	   if(ctx.widgets.get(1092).isValid()){
		   ctx.mouse.move(ctx.widgets.get(1092,tele).getCenterPoint());
		   ctx.mouse.click(true);
		   teleportTimer = new Timer(3000);
	   }else ctx.widgets.get(1465,10).interact("Teleport");//select lodestone button
	   
	   
   }

}
