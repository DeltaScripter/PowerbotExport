package dqquests;
/*package quests;

import org.powerbot.script.methods.ClientContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.Tile;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class LostCity extends Node{

	public LostCity(ClientContext ctx) {
		super(ctx);
	}


	public final Tile[] pathToStartQuest= new Tile[] {
			new Tile(3231, 3220, 0), new Tile(3231, 3225, 0), new Tile(3232, 3230, 0), 
			new Tile(3228, 3234, 0), new Tile(3224, 3237, 0), new Tile(3221, 3241, 0), 
			new Tile(3219, 3246, 0), new Tile(3214, 3248, 0), new Tile(3209, 3248, 0), 
			new Tile(3204, 3248, 0), new Tile(3199, 3248, 0), new Tile(3196, 3243, 0), 
			new Tile(3193, 3239, 0), new Tile(3190, 3235, 0), new Tile(3186, 3232, 0), 
			new Tile(3181, 3230, 0), new Tile(3177, 3227, 0), new Tile(3175, 3222, 0), 
			new Tile(3172, 3218, 0), new Tile(3170, 3213, 0), new Tile(3165, 3213, 0), 
			new Tile(3160, 3215, 0), new Tile(3155, 3217, 0), new Tile(3150, 3219, 0), 
			new Tile(3145, 3218, 0), new Tile(3143, 3213, 0), new Tile(3147, 3210, 0), 
			new Tile(3150, 3206, 0), new Tile(3149, 3202, 0) };
	
	public final Tile[] pathToLeprechaun = new Tile[] {
			new Tile(3232, 3220, 0), new Tile(3232, 3225, 0), new Tile(3229, 3229, 0), 
			new Tile(3225, 3232, 0), new Tile(3222, 3236, 0), new Tile(3220, 3241, 0), 
			new Tile(3219, 3246, 0), new Tile(3214, 3247, 0), new Tile(3209, 3249, 0), 
			new Tile(3204, 3249, 0), new Tile(3199, 3249, 0), new Tile(3196, 3245, 0), 
			new Tile(3192, 3242, 0), new Tile(3187, 3239, 0), new Tile(3183, 3236, 0), 
			new Tile(3179, 3233, 0), new Tile(3174, 3232, 0), new Tile(3169, 3232, 0), 
			new Tile(3164, 3230, 0), new Tile(3159, 3229, 0), new Tile(3155, 3226, 0), 
			new Tile(3150, 3224, 0), new Tile(3147, 3219, 0), new Tile(3144, 3215, 0), 
			new Tile(3139, 3212, 0), new Tile(3138, 3212, 0) };
	
	public final Tile[] pathToDocks = new Tile[] { 
			new Tile(3232, 3220, 0), new Tile(3232, 3225, 0), new Tile(3230, 3230, 0), 
			new Tile(3225, 3231, 0), new Tile(3223, 3236, 0), new Tile(3222, 3241, 0), 
			new Tile(3220, 3246, 0), new Tile(3215, 3246, 0), new Tile(3210, 3247, 0), 
			new Tile(3205, 3247, 0), new Tile(3200, 3245, 0), new Tile(3195, 3242, 0), 
			new Tile(3190, 3242, 0), new Tile(3185, 3242, 0), new Tile(3180, 3242, 0), 
			new Tile(3175, 3242, 0), new Tile(3170, 3241, 0), new Tile(3166, 3238, 0), 
			new Tile(3161, 3237, 0), new Tile(3156, 3237, 0), new Tile(3151, 3236, 0), 
			new Tile(3146, 3234, 0), new Tile(3142, 3231, 0), new Tile(3137, 3229, 0), 
			new Tile(3132, 3227, 0), new Tile(3127, 3225, 0), new Tile(3122, 3223, 0), 
			new Tile(3117, 3222, 0), new Tile(3112, 3222, 0), new Tile(3109, 3226, 0), 
			new Tile(3107, 3231, 0), new Tile(3104, 3236, 0), new Tile(3104, 3241, 0), 
			new Tile(3104, 3246, 0), new Tile(3104, 3251, 0), new Tile(3104, 3256, 0), 
			new Tile(3103, 3261, 0), new Tile(3104, 3266, 0), new Tile(3104, 3271, 0), 
			new Tile(3105, 3276, 0), new Tile(3106, 3281, 0), new Tile(3108, 3286, 0), 
			new Tile(3110, 3291, 0), new Tile(3110, 3296, 0), new Tile(3105, 3298, 0), 
			new Tile(3100, 3299, 0), new Tile(3096, 3296, 0), new Tile(3093, 3292, 0), 
			new Tile(3090, 3288, 0), new Tile(3085, 3286, 0), new Tile(3080, 3284, 0), 
			new Tile(3076, 3281, 0), new Tile(3073, 3277, 0), new Tile(3068, 3276, 0), 
			new Tile(3064, 3273, 0), new Tile(3063, 3268, 0), new Tile(3063, 3263, 0), 
			new Tile(3062, 3258, 0), new Tile(3059, 3254, 0), new Tile(3054, 3252, 0), 
			new Tile(3050, 3249, 0), new Tile(3045, 3247, 0), new Tile(3041, 3244, 0), 
			new Tile(3040, 3239, 0), new Tile(3043, 3235, 0), new Tile(3038, 3233, 0), 
			new Tile(3033, 3233, 0), new Tile(3028, 3232, 0), new Tile(3028, 3227, 0), 
			new Tile(3028, 3222, 0), new Tile(3024, 3219, 0), new Tile(3019, 3218, 0), 
			new Tile(3014, 3215, 0), new Tile(3009, 3215, 0), new Tile(3013, 3218, 0), 
			new Tile(3018, 3218, 0), new Tile(3023, 3218, 0), new Tile(3026, 3222, 0), 
			new Tile(3025, 3227, 0), new Tile(3026, 3232, 0), new Tile(3030, 3236, 0), 
			new Tile(3035, 3236, 0), new Tile(3040, 3236, 0), new Tile(3044, 3233, 0), 
			new Tile(3045, 3233, 0) };
	
	public final Tile[] pathToDungeon = new Tile[] { 
			new Tile(2834, 3335, 0), new Tile(2829, 3334, 0), new Tile(2825, 3337, 0), 
			new Tile(2824, 3342, 0), new Tile(2828, 3345, 0), new Tile(2833, 3345, 0), 
			new Tile(2837, 3348, 0), new Tile(2842, 3349, 0), new Tile(2847, 3349, 0), 
			new Tile(2851, 3346, 0), new Tile(2856, 3345, 0), new Tile(2858, 3350, 0), 
			new Tile(2859, 3355, 0), new Tile(2856, 3359, 0), new Tile(2856, 3364, 0), 
			new Tile(2856, 3369, 0), new Tile(2854, 3374, 0), new Tile(2849, 3376, 0), 
			new Tile(2844, 3376, 0), new Tile(2839, 3376, 0), new Tile(2834, 3376, 0), 
			new Tile(2830, 3373, 0), new Tile(2825, 3372, 0), new Tile(2819, 3375, 0) };
	
	public final Tile[] pathToShack = new Tile[] { 
			new Tile(3232, 3219, 0), new Tile(3232, 3214, 0), new Tile(3234, 3209, 0), 
			new Tile(3235, 3204, 0), new Tile(3237, 3199, 0), new Tile(3241, 3196, 0), 
			new Tile(3244, 3192, 0), new Tile(3242, 3187, 0), new Tile(3238, 3183, 0), 
			new Tile(3235, 3179, 0), new Tile(3235, 3174, 0), new Tile(3233, 3169, 0), 
			new Tile(3230, 3165, 0), new Tile(3226, 3161, 0), new Tile(3221, 3159, 0), 
			new Tile(3216, 3158, 0), new Tile(3211, 3158, 0), new Tile(3206, 3160, 0), 
			new Tile(3201, 3161, 0), new Tile(3198, 3165, 0), new Tile(3200, 3169, 0) };
	
	final Area Entrana = new Area(new Tile[] { 
			new Tile(2792, 3397, 0), new Tile(2792, 3330, 0), new Tile(2880, 3321, 0), 
			new Tile(2870, 3399, 0) });
	
	
	public Tile initTile;
	public int bankItems[] = {771,772};
	public int bankItemAmount[] = {1,1};
	
	private Timer wait = new Timer(0);
	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	
	public boolean activate() {
		return DeltaQuester.scriptToStart==28;
	}

	
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		DeltaQuester.numSteps = 5;
		while(ctx.varpbits.varpbit(1045)!=0){//The setting used when the mini boat appears.
			Method.state("Now sailing");
			Method.sleep(20);
		}
		Method.resetTeleporting();
		Method.foodSupport();
		
		
		if(DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2551)&0x7) != 6)
			Method.determineBank(bankItems);
		
		if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2551)&0x7) != 6){
			System.out.println("In check bank");
			Method.checkBank();
		}else
	    if(Vars.useBank && (ctx.varpbits.varpbit(2551)&0x7) != 6){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if((ctx.varpbits.varpbit(2551)&0x7) == 6){
			DeltaQuester.progress  =5;
			Method.state("The Lost City quest has been completed");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.varpbits.varpbit(2551)&0x7) == 4||(ctx.varpbits.varpbit(2551)&0x7) == 5){
			DeltaQuester.progress  =4;
			cs3();//Make the staff, then enter the mysterious shed
		}else
		if((ctx.varpbits.varpbit(2551)&0x3) == 2||(ctx.varpbits.varpbit(2551)&0x3) == 3){
			DeltaQuester.progress  =3;
			cs2();//Get to the dungeon and defeat the tree spirit, then chop the tree again.
		}else
		if((ctx.varpbits.varpbit(2551)&0x1) == 1){
			DeltaQuester.progress  =2;
			cs1();//Speak to the leprechaun
		}else if((ctx.varpbits.varpbit(2551)&0x1) ==0){
			DeltaQuester.progress  =1;
		cs0();//Start the quest(speak to the warrior)
		}
	}


	private void cs3() {
		Player local = ctx.players.local();
		while(!Method.teleporting &&Method.inventoryContains(771)){
			if(ctx.widgets.get(1179).isValid()){
				if(ctx.widgets.get(1179,0).getText().contains("What do you want to use")){
					ctx.widgets.get(1179,16).click(true);
				}
			}else Method.interactInventory(771, "Craft", "Dramen branch");
		}
		if( !wait.isRunning()&& Method.EquipmentContains(772)){
			if(new Tile(3200, 3169, 0).distanceTo(local.getLocation())<10){
				Method.interactO(2406, "Open", "Door");
			}else if(Vars.DYNAMICV3){
				Method.walking(pathToShack, "Walking to shack",false);
			}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
				Vars.DYNAMICV3 = true;
			}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		}else
		if(!Method.teleporting &&Method.inventoryContains(772)){
			if(!wait.isRunning())
				wait = new Timer(5000);
			Method.interactInventory(772, "Wield", "Dramen staff");
		}else cs2();
		
	}


	private void cs2() {//31,39
		Player local = ctx.players.local();
		final String[] opt  ={"Well, that is"};
		while(Method.objIsNotNull(2415)){
			Method.interactO(2415, "Cross", "Plank");
		}
		if(initTile!=null){
			if(new Tile(initTile.x()+34, initTile.getY()-39,0).distanceTo(local.getLocation())<10){
				if((ctx.varpbits.varpbit(2551)&0x3) == 3){//After defeating the spirit
					
					if(!local.isInMotion())
					Method.interactO(1292, "Chop","Tree");
					
				}else if(Method.npcIsNotNull(655)){
					if(Method.isInCombat()){
						Method.basicFightNPC(655);
					}else Method.npcInteract(655, "Attack");
				}else Method.interactO(1292, "Chop", "Dramen Tree");
			}else {
				Method.state("Walking to special tree");
				Method.clickOnMap(new Tile(initTile.x()+34, initTile.getY()-39,0));
			}
		}else
		if(Method.objIsNotNull(31130)){
			initTile = local.getLocation();
		}else
		if(Vars.DYNAMICV2){
			if (new Tile(2819, 3375, 0).distanceTo(local.getLocation()) < 10) {
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Monk")){
						Method.interactO(2408, "Climb", "Ladder");
					}
				}
			} else 	Method.walking(pathToDungeon, "Walking to the dungeon", false);
		}else
		if(Entrana.contains(local.getLocation())){
			Vars.DYNAMICV2  =true;
		}else
		if(new Tile(3045, 3233, 0).distanceTo(local.getLocation())<10){
			Method.npcInteract(2729, "Travel");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDocks, "Walking to Port Sarim docks", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10 || TeleportLode.DRAYNOR.getTile().distanceTo(local.getLocation())<10 
				|| TeleportLode.PORTSARIM.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),TeleportType.PORTSARIM.getName());
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}


	private void cs1() {
		Player local = ctx.players.local();
		final String[] opt = {"I've been in that"};
		if( new Tile(3138, 3212, 0).distanceTo(local.getLocation())<10){
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Leprechaun")){
					if(Method.npcIsNotNull(654)){
						Method.speakTo(654, "Leprechaun");
					}else
					Method.interactO(2409, "Chop", "Tree");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToLeprechaun, "Walking to the leprechaun", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}


	private void cs0() {
		Player local = ctx.players.local();
		final String[] opt ={"Looks like you","If it's hidden","Who's Zan","I don't think you've","Do you know"};
		if( new Tile(3149, 3202, 0).distanceTo(local.getLocation())<10){
			if (!Method.findOption(opt)) {
				if (!Method.isChatting("Warrior")) {
					Method.speakTo(650, "Warrior");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToStartQuest, "Walking to the wizard", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

}
*/