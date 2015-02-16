package quests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class GunnarsGround extends DeltaNode{

	
	public GunnarsGround(ClientContext ctx) {
		super(ctx);
	}

	public final Tile[] pathToDororan = new Tile[] { 
			new Tile(3213, 3375, 0), new Tile(3211, 3380, 0), new Tile(3209, 3385, 0), 
			new Tile(3209, 3390, 0), new Tile(3209, 3395, 0), new Tile(3210, 3400, 0), 
			new Tile(3211, 3405, 0), new Tile(3210, 3410, 0), new Tile(3209, 3415, 0), 
			new Tile(3208, 3420, 0), new Tile(3207, 3425, 0), new Tile(3204, 3429, 0), 
			new Tile(3199, 3430, 0), new Tile(3194, 3430, 0), new Tile(3189, 3428, 0), 
			new Tile(3184, 3427, 0), new Tile(3179, 3428, 0), new Tile(3174, 3429, 0), 
			new Tile(3170, 3426, 0), new Tile(3165, 3424, 0), new Tile(3161, 3421, 0), 
			new Tile(3156, 3419, 0), new Tile(3151, 3417, 0), new Tile(3146, 3416, 0), 
			new Tile(3141, 3416, 0), new Tile(3136, 3417, 0), new Tile(3131, 3417, 0), 
			new Tile(3126, 3417, 0), new Tile(3121, 3416, 0), new Tile(3118, 3420, 0), 
			new Tile(3113, 3421, 0), new Tile(3108, 3422, 0), new Tile(3103, 3421, 0), 
			new Tile(3098, 3421, 0), new Tile(3096, 3422, 0) };
	
	public final Tile[] pathToJeffery = new Tile[] {
			new Tile(3213, 3375, 0), new Tile(3211, 3380, 0), new Tile(3209, 3385, 0), 
			new Tile(3209, 3390, 0), new Tile(3209, 3395, 0), new Tile(3210, 3400, 0), 
			new Tile(3211, 3405, 0), new Tile(3210, 3410, 0), new Tile(3209, 3415, 0), 
			new Tile(3208, 3420, 0), new Tile(3207, 3425, 0), new Tile(3204, 3429, 0), 
			new Tile(3199, 3430, 0), new Tile(3194, 3430, 0), new Tile(3189, 3428, 0), 
			new Tile(3184, 3427, 0), new Tile(3179, 3428, 0), new Tile(3174, 3429, 0), 
			new Tile(3170, 3426, 0), new Tile(3165, 3424, 0), new Tile(3161, 3421, 0), 
			new Tile(3156, 3419, 0), new Tile(3151, 3417, 0), new Tile(3146, 3416, 0), 
			new Tile(3141, 3416, 0), new Tile(3136, 3417, 0), new Tile(3131, 3417, 0), 
			new Tile(3126, 3417, 0), new Tile(3121, 3416, 0), new Tile(3118, 3420, 0), 
			new Tile(3113, 3421, 0), new Tile(3108, 3422, 0), new Tile(3103, 3421, 0), 
			new Tile(3100, 3426, 0), new Tile(3101, 3431, 0), new Tile(3101, 3436, 0), 
			new Tile(3097, 3438, 0), new Tile(3092, 3439, 0), new Tile(3090, 3444, 0), 
			new Tile(3089, 3449, 0), new Tile(3093, 3452, 0), new Tile(3092, 3457, 0), 
			new Tile(3089, 3461, 0), new Tile(3086, 3465, 0), new Tile(3083, 3469, 0), 
			new Tile(3081, 3474, 0), new Tile(3079, 3479, 0), new Tile(3079, 3484, 0), 
			new Tile(3083, 3488, 0), new Tile(3085, 3493, 0), new Tile(3086, 3498, 0), 
			new Tile(3089, 3502, 0), new Tile(3093, 3505, 0), new Tile(3098, 3505, 0), 
			new Tile(3103, 3502, 0), new Tile(3108, 3500, 0) };
	
	public final Tile[] pathToGudran = new Tile[] { 
			new Tile(3099, 3424, 0), new Tile(3095, 3421, 0), new Tile(3090, 3419, 0), 
			new Tile(3085, 3419, 0), new Tile(3080, 3419, 0), new Tile(3079, 3416, 0) };
	
	public final Tile[] pathToGunthor = new Tile[] {
			new Tile(3098, 3423, 0), new Tile(3093, 3420, 0), new Tile(3088, 3419, 0), 
			new Tile(3083, 3417, 0), new Tile(3078, 3416, 0), new Tile(3078, 3421, 0), 
			new Tile(3081, 3425, 0), new Tile(3079, 3430, 0), new Tile(3079, 3435, 0), 
			new Tile(3077, 3440, 0), new Tile(3077, 3442, 0) };
	
	final DeltaArea FatherDoor = new DeltaArea(new Tile[] { 
			new Tile(3075, 3438, 0), new Tile(3075, 3433, 0), new Tile(3082, 3434, 0), 
			new Tile(3081, 3439, 0) });
	
	public int itemDAmount[] = {1,1,1};
	public int bankItems[] = {1755,19770,19774,19771,19772};
	public int bankItemAmount[] = {1,1,1,1,1};
	private Method Method = new Method(ctx);
	private Vars Vars = new Vars();
	
	public void execute() {
		DeltaQuester.numSteps = 13;
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		
		
		
		if(DeltaQuester.checkedBank &&(ctx.varpbits.varpbit(2111) & 0x7F) != 100)
			Method.determineBank(bankItems);
		
			if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2111) & 0x7F) != 100){
			Method.checkBank();
		}else
	    if(quests.Vars.useBank && (ctx.varpbits.varpbit(2111) & 0x7F) != 100){
			Method.useBank(bankItems, bankItemAmount);
		}else if(!Method.interference())
		if ((ctx.varpbits.varpbit(2111) & 0x7F) == 100) {
			DeltaQuester.progress =13;
			Method.state("The Gunnar's Ground quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x7F) == 75) {
			DeltaQuester.progress =12;
			cs5();
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x7F) == 65||(ctx.varpbits.varpbit(2111) & 0x7F) == 70) {
			DeltaQuester.progress =11;
			cs3();//
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x7F) == 60) {
			DeltaQuester.progress =11;
			cs0();//Get the poem from Dororan
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x7F) == 45||(ctx.varpbits.varpbit(2111) & 0x7F) == 50||(ctx.varpbits.varpbit(2111) & 0x7F) == 55) {
			DeltaQuester.progress =10;
			cs0();//Help Doroan with his poem
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x7F) == 40) {
			DeltaQuester.progress =9;
			cs0();//Speak with Dororan again to find out what to do next
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x3F) == 35) {
			DeltaQuester.progress =8;
			cs3();//Speak with Gudran again about the result of the conversation with her father
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x1F) == 30) {
			DeltaQuester.progress =7;
			cs4();//Speak with gunthor and get rejected
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x1F) == 25) {
			DeltaQuester.progress =6;
			cs3();//Speak with Gudran(show her the ring) and be given the next Method
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x1F) == 20) {
			DeltaQuester.progress =5;
			cs0();//Show Dororan the ring and accept his next Method
		} else
		if ((ctx.varpbits.varpbit(2111) & 0xF) == 15) {
			DeltaQuester.progress =4;
			cs2();//Combine the chisel with the ring to make it engraved
		} else
		if ((ctx.varpbits.varpbit(2111) & 0xF) == 10) {
			DeltaQuester.progress =3;
			cs1();//Speak to Jeffery to gather the ring then speak to Dororan regarding what to do next
		} else
		if ((ctx.varpbits.varpbit(2111) & 0x7) == 5) {
			DeltaQuester.progress =2;
			cs0();//Speak to Dororan to gain more information as to what he wants
		} else if ((ctx.varpbits.varpbit(2111) & 0x1) == 0) {
			DeltaQuester.progress =1;
			cs0();// Begin the quest by speaking with Dororan.
		}
	}

	private void cs5() {//Finish the quest
		final String opt[] = {"I'll consider"};
		Player local = ctx.players.local();
		if(new Tile(3080,3416,0).distanceTo(local.tile())<8){
			Method.skipPics();
			if(!Method.findOption(opt))
			if(!Method.isChatting("Couple")){Vars.DYNAMICV = false;
				Method.speakTo(2866, "Couple");
			}
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToGudran, "Walking to Gudran", false);
		}else cs0();
	}

	private void cs4() {//Speak to the father, Gunthor
		Player local = ctx.players.local();
		final String opt[] = {"Good","Please wait a moment","You're barbarians","Your daughter seeks permission","I need to speak with you"};
		//SceneObject door = SceneEntities.getNearest(11621);
		if(new Tile(3077, 3442, 0).distanceTo(local.tile())<6){
			
			Method.skipPics();
			if(!Method.widgetOpenCloseIt(1370,30))//returns false if the fire widget is not open(it causes problems)
			if(!Method.findOption(opt))
			if(!Method.isChatting("Gunthor")){Vars.DYNAMICV2 = false;Vars.DYNAMICV = false;
				Method.speakTo(2876, "Gunthor");
			}
		}else if(Vars.DYNAMICV2){
			if(new Tile(3079,3438,0).distanceTo(local.tile())<6 && Method.objIsByTile(new Tile(3079,3438,0), 11621, 6)){
				Method.interactO(11621, "Open", "Door");
			}else Method.walking(pathToGunthor, "Walking to Gunthor", false);
			
		}else cs0();//Walk to Dororan to get the variable active
		
	}

	private void cs3() {//Speak to Gudran
		final String opt[] = {"Goodbye then","Now's your chance to find out","Why would he do that","Don't be silly","Why's that","They're just starting","What should we do now","So, you want me to","A secret admirer","The ring isn't from me","Yes"};
		Player local = ctx.players.local();
		if(ctx.varpbits.varpbit(1114)==1){
			if(!Method.findOption(opt))
			if(!Method.isChatting("People")){
				Method.sleep(50);
			}
		}else
		if(new Tile(3080,3416,0).distanceTo(local.tile())<8){
			Method.skipPics();
			if(!Method.findOption(opt))
			if(!Method.isChatting("Gudran")){Vars.DYNAMICV2 = true;Vars.DYNAMICV = false;
				Method.speakTo(2864, "Gudran");
			}
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToGudran, "Walking to Gudran", false);
		}else cs0();
		
	}

	private void cs2() {//engrave aring
		if(!ctx.bank.opened()){
		Method.interactInventory(19770, "Engrave", "Ring");
		}else ctx.bank.close();
		
	}

	private void cs1() {//speak to the friend named jefferey
		Player local = ctx.players.local();
		final String opt[]  ={"Just a plain, gold","Yes, he did","This splendid love","I was hoping you would trade me","I'm here about a"};
		if((ctx.varpbits.varpbit(2111)>>7 &0x7) == 4){
			if(new Tile(3108, 3499, 0).distanceTo(local.tile()) < 8){Vars.DYNAMICV3 = true;}
			cs0();
		}else
		if (new Tile(3108, 3499, 0).distanceTo(local.tile()) < 8) {
			if(!Method.findOption(opt)){Vars.DYNAMICV = false;Vars.DYNAMICV2=false;Vars.DYNAMICV3 = true;
			if(!Method.isChatting("Jeffery")){
				Method.speakTo(637, "Jeffery");
			}
		}
		} else if (Vars.DYNAMICV2) {
			Method.walking(pathToJeffery, "Walking to Jeffery", false);
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToJeffery, "Walking to Jeffery", false);
		} else if (TeleportLode.VARROCK.getTile().distanceTo(local.tile()) < 10) {
			Vars.DYNAMICV = true;
		} else Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
	  }

	private void cs0() {//soeak to doroan the dwarf
		Player local = ctx.players.local();
		final String opt[] = {"Swept to war","Threw the ball","Threat","Stock","Longsword","Stray","Cucumber","More words","I'm sure that's not","What are we going to do","No, she liked the ring","Very well","Of course","It's come out perfectly","That sounds simple","What do you want me to","There must be something","Get to the point","Angina","Yes","ever learn to fly"};
		if(new Tile(3107,3501,0).distanceTo(local.tile())<10){
			Vars.DYNAMICV3 = true;
		}
		if(new Tile(3096, 3422, 0).distanceTo(local.tile())<9){//Dororan location
			if(new Tile(3096, 3422, 0).distanceTo(local.tile())<8){
				Method.skipPics();
			if(!Method.startQuestOpen())
				if(!Method.findOption(opt)){Vars.DYNAMICV = false; 
				Vars.DYNAMICV2 = true;
				Vars.DYNAMICV3 = false;
					if(!Method.isChatting("Dororan")){
						Method.speakTo(1168, "Dororan");
					}
				}
			}else Method.clickOnMap(new Tile(3096, 3422, 0));
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToGudran, "Walking back to Dororan", true);
		}else if(Vars.DYNAMICV3){
			Method.walking(pathToJeffery, "Walking back to Dororan", true);
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDororan, "Walking to Dororan", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),TeleportType.VARROCK.getName());
		
	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart == 9);
	}
	
}
