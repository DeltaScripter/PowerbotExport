package lodestoneActivator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

import quests.Node;


@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "DeltaLodestone", 
description = "Simply unlocks F2P lodestones.", website = "",topic =816007, version = .5)
public class Body extends PollingScript implements PaintListener{

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
	public final Tile pathToDraynor[] = new Tile[] {
			new Tile(3209,3233,0),new Tile(3208,3240,0),new Tile(3212,3246,0),
			new Tile(3212,3254,0),new Tile(3217,3265,0),new Tile(3211,3274,0),
			new Tile(3201,3276,0),new Tile(3193,3282,0),new Tile(3181,3287,0),
			new Tile(3173,3288,0),new Tile(3162,3288,0),new Tile(3152,3291,0),
			new Tile(3143,3293,0),new Tile(3134,3295,0),new Tile(3124,3296,0),
			new Tile(3114,3296,0),new Tile(3106,3299,0)
	};
	public final Tile pathToTaverly[] = new Tile[] {
			new Tile(2898,3539,0),new Tile(2900,3530,0),new Tile(2899,3520,0),
			new Tile(2892,3511,0),new Tile(2890,3498,0),new Tile(2893,3488,0),
			new Tile(2893,3475,0),new Tile(2894,3462,0),new Tile(2890,3454,0),
			new Tile(2881,3446,0),new Tile(2877,3442,0)
	};
	public final Tile pathToPortSarim[] = new Tile[] {
			new Tile(2966,3394,0),new Tile(2965,3383,0),new Tile(2974,3379,0),
			new Tile(2985,3372,0),new Tile(2995,3367,0),new Tile(3002,3357,0),
			new Tile(3006,3345,0),new Tile(3006,3335,0),new Tile(3006,3329,0),
			new Tile(3006,3318,0),new Tile(3007,3308,0),new Tile(3006,3297,0),
			new Tile(3007,3286,0),new Tile(3006,3275,0),new Tile(3006,3265,0),
			new Tile(3004,3254,0),new Tile(3004,3244,0),new Tile(3005,3234,0),
			new Tile(3005,3225,0),new Tile(3011,3217,0)
	};
	public final Tile pathToAlkharid[] = new Tile[] {
		new Tile(3212,3233,0),new Tile(3220,3224,0),new Tile(3231,3218,0),
		new Tile(3237,3223,0),new Tile(3245,3226,0),new Tile(3257,3227,0),
		new Tile(3263,3230,0),new Tile(3271,3229,0),new Tile(3279,3222,0),
		new Tile(3282,3211,0),new Tile(3284,3201,0),new Tile(3289,3195,0),
		new Tile(3292,3189,0),new Tile(3298,3184,0)
	};
	private final List<Node> nodeList = Collections.synchronizedList(new ArrayList<Node>());
	private Timer teleportTimer = new Timer(0);
	private Timer walkingTimer = new Timer(0);
	private String state;
	
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
		
		if((ctx.settings.get(3) >> 11 & 0x1)==1){//Varrok is active
			if((ctx.settings.get(3) >>6 &0x1)==1){//Falador
				if((ctx.settings.get(3) >>4 &0x1)==1){//Draynor
					if((ctx.settings.get(3) >> 10 &0x1) == 1){//Taverly
						if((ctx.settings.get(3) >> 8 & 0x1) == 1){//Port Sarim
							if((ctx.settings.get(3) & 0x1) == 1){//Alkharid
								System.out.println("Finished");
								getController().stop();
							}else unlockLodeStone(TeleportLode.ALHARID.getTile(), 69829,pathToAlkharid,TeleportLode.LUMMBRIDGE.getTile(),TeleportType.LUMBRIDGE.getTeleport(),"Unlocking Alkharid");
						}else unlockLodeStone(TeleportLode.PORTSARIM.getTile(), 69837,pathToPortSarim,TeleportLode.FALADOR.getTile(),TeleportType.FALADOR.getTeleport(),"Unlocking Port Sarim");
					}else unlockLodeStone(TeleportLode.TAVERLY.getTile(), 69839,pathToTaverly,TeleportLode.BURTHORPE.getTile(),TeleportType.BURTHHORPE.getTeleport(),"Unlocking Taverly");
				}else unlockLodeStone(TeleportLode.DRAYNOR.getTile(), 69833,pathToDraynor,TeleportLode.LUMMBRIDGE.getTile(),TeleportType.LUMBRIDGE.getTeleport(),"Unlocking Draynor");
			}else unlockLodeStone(TeleportLode.FALADOR.getTile(), 69835,pathToFalador,TeleportLode.BURTHORPE.getTile(),TeleportType.BURTHHORPE.getTeleport(),"Unlocking Falador");
		}else unlockLodeStone(TeleportLode.VARROCK.getTile(), 69840,pathToVarrock,TeleportLode.LUMMBRIDGE.getTile(),TeleportType.LUMBRIDGE.getTeleport(),"Unlocking Varrock");
		
	}



	private void unlockLodeStone(Tile lodeArea, int lodestoneID, Tile[] pathToLode,Tile teleLodeTile, int teleport,String condition) {
		state = condition;
		if(lodeArea.distanceTo(ctx.players.local().getLocation())<7){
			dynamicV = false;
			for(GameObject lode: ctx.objects.select().id(lodestoneID).nearest().first()){
				lode.interact("Activate");
				ctx.game.sleep(700,1000);
			}
		}else if(dynamicV){
			if(new Tile(2943,3440,0).distanceTo(ctx.players.local().getLocation())<8 && 
					objIsNotNull(28690) && objIsByTile(new Tile(2943,3440,0),28690,5)){
				for(GameObject gate: ctx.objects.select().id(28690).nearest().first()){
				 gate.interact("Open");
				}
			}else walk(pathToLode);
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
				   ctx.game.sleep(300);
				   teleportTimer = new Timer(3000);
		  
	   }else {
		   ctx.widgets.get(1465,10).interact("Teleport");//select lodestone button
		   ctx.game.sleep(300);
	   }
	   
	   
   }
 private Font myFont = new Font("Consolas",Font.BOLD,14);
@Override
public void repaint(Graphics g) {
	g.setColor(Color.GREEN);
	g.setFont(myFont);
	g.drawString("State: " + state, 20, 130);
	
}

}
