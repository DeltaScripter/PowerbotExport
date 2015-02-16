
package quests;


import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class WolfWhistle extends DeltaNode{



	public final int RAREITEMS_ID = 23069;
	public final int WHITEHAIRMEAT_ID = 23067;
	public final int ANCIENTWOLFAMULET_ID = 23066;
	public final int EMBRIOEDPOUCH_ID = 23068;
	public final int RARESUMMONINGITEMS_ID = 23069;
	public final int PIKKUPSTIX_ID = 6988;
	public final int GIANTWOLFPOUCH_ID = 23070;
	public final int SCALECTRIX_ID = 15055;
	public final int STIXSTAIRSBOTTOM_ID = 66637;
	public final int STIXSTAIRSTOP_ID = 66638;
	public final int CLUTTEREDDRAWERS_ID = 28641;
	public final int PETSHOPOWNER_ID = 6893;
	public final int DEADBODY_ID = 67488;
	public final int OBELISK_ID = 67036;
	public final int WOLFPOUCH_ID = 23070;
	



	public final DeltaArea bonesArea = new DeltaArea(new Tile[] {
			new Tile(2847, 3475, 0), new Tile(2860, 3475, 0),
			new Tile(2860, 3483, 0), new Tile(2845, 3483, 0) });

	public final Tile[] pathToPetFT = new Tile[] { new Tile(2899, 3544, 0),
			new Tile(2899, 3536, 0), new Tile(2899, 3524, 0),
			new Tile(2898, 3514, 0), new Tile(2896, 3508, 0),
			new Tile(2892, 3500, 0), new Tile(2892, 3494, 0),
			new Tile(2893, 3484, 0), new Tile(2893, 3477, 0),
			new Tile(2894, 3468, 0), new Tile(2894, 3463, 0),
			new Tile(2888, 3452, 0), new Tile(2881, 3444, 0),
			new Tile(2883, 3436, 0), new Tile(2882, 3430, 0),
			new Tile(2886, 3417, 0), new Tile(2895, 3415, 0),
			new Tile(2907, 3417, 0), new Tile(2913, 3423, 0),
			new Tile(2920, 3425, 0), new Tile(2922, 3431, 0),
			new Tile(2931, 3433, 0) };

	public final Tile[] pathToBonesFPS = new Tile[] {
			new Tile(2932, 3433, 0), new Tile(2923, 3432, 0),
			new Tile(2920, 3425, 0), new Tile(2913, 3422, 0),
			new Tile(2907, 3417, 0), new Tile(2894, 3416, 0),
			new Tile(2887, 3418, 0), new Tile(2880, 3423, 0),
			new Tile(2871, 3433, 0), new Tile(2866, 3440, 0),
			new Tile(2863, 3445, 0), new Tile(2864, 3450, 0),
			new Tile(2864, 3455, 0), new Tile(2859, 3462, 0),
			new Tile(2856, 3467, 0), new Tile(2853, 3472, 0),
			new Tile(2853, 3479, 0) };

	public final Tile[] pathToBonesFTA = new Tile[] {
			new Tile(2899, 3532, 0), new Tile(2898, 3523, 0),
			new Tile(2898, 3515, 0), new Tile(2896, 3507, 0),
			new Tile(2891, 3499, 0), new Tile(2892, 3490, 0),
			new Tile(2891, 3479, 0), new Tile(2893, 3467, 0),
			new Tile(2892, 3460, 0), new Tile(2886, 3452, 0),
			new Tile(2882, 3440, 0), new Tile(2883, 3431, 0),
			new Tile(2872, 3436, 0), new Tile(2866, 3439, 0),
			new Tile(2864, 3445, 0), new Tile(2865, 3452, 0),
			new Tile(2863, 3458, 0), new Tile(2858, 3465, 0),
			new Tile(2855, 3470, 0), new Tile(2852, 3477, 0) };
	

	public final Tile[] pathToMrStix = new Tile[] {
			new Tile(2899, 3544, 0), new Tile(2899, 3536, 0),
			new Tile(2899, 3524, 0), new Tile(2898, 3514, 0),
			new Tile(2896, 3508, 0), new Tile(2892, 3500, 0),
			new Tile(2892, 3494, 0), new Tile(2893, 3484, 0),
			new Tile(2893, 3477, 0), new Tile(2894, 3468, 0),
			new Tile(2894, 3463, 0), new Tile(2888, 3452, 0),
			new Tile(2881, 3444, 0), new Tile(2883, 3436, 0),
			new Tile(2882, 3430, 0), new Tile(2886, 3417, 0),
			new Tile(2895, 3415, 0), new Tile(2907, 3417, 0),
			new Tile(2913, 3423, 0), new Tile(2922, 3435, 0),
			new Tile(2923, 3446, 0), new Tile(2929, 3449, 0) };

	public final Tile[] pathToPetShopFMS = new Tile[] {
			new Tile(2927, 3448, 0), new Tile(2923,3438, 0),
			new Tile(2932, 3433, 0) };

	
	public int bankItems[] = {23069,23067, 23066,23068, 23069,23070,};
	public int bankItemAmount[] = {1,1,1,1,1,1};
	Vars Vars = new Vars();
	Method Method = new Method(ctx);
	
	public WolfWhistle(ClientContext ctx) {
		super(ctx);
	}
	boolean q = true;
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		if(q){
			q = false;
		}
			Method.foodSupport();
			DeltaQuester.numSteps = 12;
			failsafe();
			
			if(DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2506)&0x3F)!=35)
				Method.determineBank(bankItems);
			
			if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2506)&0x3F)!=35){
				Method.checkBank();
			}else
		   if(quests.Vars.useBank && (ctx.varpbits.varpbit(2506)&0x3F)!=35){
				Method.useBank(bankItems, bankItemAmount);
			}else
			if((ctx.varpbits.varpbit(2506)&0x3F)==35){
				DeltaQuester.progress = 12;
				Method.state("The Wolf Whistle quest has been completed.");
			
				Method.sleep(2000);
				DeltaQuester.e = true;
			}else
			if((ctx.varpbits.varpbit(2506)&0x1F)==30){
				DeltaQuester.progress = 11;
				cs11();//Speak to Scaletrix and finish the quest
			
			}else
			if((ctx.varpbits.varpbit(2506)&0x1F)==25){
				DeltaQuester.progress = 10;
				cs10();//Show Pikkupstix your pouch
			}else
			if((ctx.varpbits.varpbit(2506)&0x1F)==20){
				DeltaQuester.progress = 9;
				cs9();//Crete the special pouch by using the obelisk
			}else
			if((ctx.varpbits.varpbit(2506)>>12&0x1)==1){
				DeltaQuester.progress = 8;
				cs8();//Still bringing the items
			
			}else
			if((ctx.varpbits.varpbit(2506)>>8&0x1)==1){
				DeltaQuester.progress = 7;
				cs7();//Bring the items to Piikupstix
			
			}else
			if((ctx.varpbits.varpbit(2506)>>9&0x1)==1){
				DeltaQuester.progress = 6;
				cs6();//Search the corpse in the mountains for an amulet
			
			}else
			if((ctx.varpbits.varpbit(2506)>>10&0x1)==1){
				DeltaQuester.progress = 5;
				cs5();//Buy hare meat from shop owner
			
			}else
			if((ctx.varpbits.varpbit(2506)&0xF)==15){
				DeltaQuester.progress = 4;
				cs4();//Search drawers in Pikkupstix's house for a pouch
			
			}else
			if((ctx.varpbits.varpbit(2506)&0xF)==10){
				DeltaQuester.progress = 3;
				cs3();//Go back and tell Pikkupstix about the kidnapping
			
			}else
			if((ctx.varpbits.varpbit(2506)&0x7)==5){
				DeltaQuester.progress = 2;
				cs2();//Speak to Scaletrix
			}else
			if(ctx.varpbits.varpbit(2506)>>15==1 || ctx.varpbits.varpbit(2506)==0){
				DeltaQuester.progress = 1;
				cs1();//Start the quest by speaking to Pikkupstix
			}
	}

	private void failsafe() {
		while(ctx.widgets.component(1370,31).visible()){//If the obelisk infusing area is selected.(pop-up screen appears)
			Method.state("Closing interface");
			Method.clickOnMap(ctx.players.local().tile());
		}
	}
	private void cs11() {
		Method Method = new Method(ctx);
		if(ctx.varpbits.varpbit(1114)==1){
			Method.isChatting("People");
		}else
		if (new Tile(2881,3430,0).distanceTo(ctx.players.local().tile())<6) {//Mrs stix region.
			Vars.DYNAMICV = false;
			if (!Method.isChatting("Scaletrix")) {
				if(ctx.varpbits.varpbit(1241)!=1)
				Method.speakTo(15055, "Scaletrix");
			}
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToMrStix,"Walking to Scaletrix.",false);
		} else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
	}

	private void cs10() {
		Method Method = new Method(ctx);
		final String opt[] = {"I need to ask","I made the giant"};
		if (new Tile(2932,3446,0).distanceTo(ctx.players.local().tile())<6) {
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
				if (!Method.isChatting("Pikkupstix")) {
					Method.speakTo(6988, "Pikkupstix");
				}
			
		} else if(Vars.DYNAMICV){
			Method.walking(pathToMrStix,"Walking to Pikkupstix",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
	
	}

	private void cs9() {
		Method Method = new Method(ctx);
		
		if (new Tile(2932,3446,0).distanceTo(ctx.players.local().tile())<6) {
				Method.state("Seraching for item in inventory");
				if(ctx.widgets.component(1184,0).visible()){
					Method.clickOnMap(ctx.players.local().tile());
				}
				if(Method.inventoryContains(RAREITEMS_ID)){
					if(ctx.backpack.itemSelected()){
						Method.interactO(OBELISK_ID, "Use","Obelisk");
					}else Method.interactInventory(RAREITEMS_ID, "Use", "Rare items");
					
				}
			
		} else if(Vars.DYNAMICV){
			Method.walking(pathToMrStix,"Walking to Pikkupstix.",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
	
		
	}

	private void cs8() {
		Method Method = new Method(ctx);
		final String opt[] = {"I need to ask"};
		if (new Tile(2932,3446,0).distanceTo(ctx.players.local().tile())<6) {//stix region.
				Vars.DYNAMICV = false;
				for(Npc stix : ctx.npcs.select().id(6988).nearest().first()){
					if(stix.animation()!=15924){
						if(!Method.findOption(opt))
							if (!Method.isChatting("Pikkupstix")) {
								Method.state("Temp-sleep");
								Method.sleep(4000);
								Method.speakTo(6988, "Pikkupstix");
							}
					}
				}
		} else if(Vars.DYNAMICV){
			Method.walking(pathToMrStix,"Walking to Pikkupstix",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
	}

	private void cs7() {
		final String opt[] = {"I need to ask"};
		
		if (new Tile(2932,3446,0).distanceTo(ctx.players.local().tile())<6) {//stix region.
			
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
				if (!Method.isChatting("Pikkupstix")) {
					Method.speakTo(6988, "Pikkupstix");
				}
			
		} else if(Vars.DYNAMICV){
			Method.walking(pathToMrStix,"Walking to Pikkupstix",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		
	}

	private void cs6() {
		Method Method = new Method(ctx);
		if (bonesArea.contains(ctx.players.local().tile())) {
			Vars.DYNAMICV = false;
			Method.interactO(DEADBODY_ID, "Search","Body");
		} else {
			if (Vars.hasBeenPT) {
				Method.state("Walking to bones location");
				//Method.walkToLocation(new Tile(2853, 3479, 0));
				
				Method.walking(pathToBonesFPS,"Walking to bones location.",false);
			} else {
				if (new Tile(2931,3434,0).distanceTo(ctx.players.local().tile())<6) {
					Vars.hasBeenPT = true;
				} else if(Vars.DYNAMICV){
					Method.state("Walking to bones location");
					//Method.walkToLocation(new Tile(2852, 3477, 0));
					Method.walking(pathToBonesFTA,"Walking to bones location.",false);
				}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
						TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
					Vars.DYNAMICV=true;
				}else if(Method.TaverlyLodeAct()){
					Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
				}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
			}
		}
		
	}

	private void cs5() {
		Method Method = new Method(ctx);
		final String opt[] = {"hare meat."};
		
		if (new Tile(2931,3434,0).distanceTo(ctx.players.local().tile())<6) {
			Vars.hasBeenInMS1 = false;
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
				if (!Method.isChatting("Pet shop owner")) {
					Method.speakTo(6893, "Pet shop owner");
				}
			
			
		} else {
			if (Vars.hasBeenInMS1) {
				if (ctx.game.floor()==1) {
					Method.interactO(STIXSTAIRSTOP_ID, "Climb","Stairs");
				}else {
					Method.state("Walking to the pet shop");
					//Method.walkToLocation(new Tile(2932, 3433, 0));
					Method.walking(pathToPetShopFMS,"Walking to the pet shop.",false);
				}
				//Method.walking(pathToPetShopFMS,"Walking to the pet shop.",false);
			} else {
				if (new Tile(2929,3447,0).distanceTo(ctx.players.local().tile())<6  && ctx.game.floor()==0) {
					Vars.hasBeenInMS1 = true;
				} else {
					if (ctx.game.floor()==1) {
						Method.interactO(STIXSTAIRSTOP_ID, "Climb","Stairs");
					} else if(Vars.DYNAMICV){
						Method.state("Walking to pet shop");
						//Method.walkToLocation(new Tile(2931, 3433, 0));
						Method.walking(pathToPetFT,"Walking to the pet shop.",false);
					}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
							TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
						Vars.DYNAMICV=true;
					}else if(Method.TaverlyLodeAct()){
						Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
					}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
				}
			}
		}
		
	}

	private void cs4() {
		Method Method = new Method(ctx);
		if (ctx.game.floor()==1) {
				Method.interactO(CLUTTEREDDRAWERS_ID, "Search","Drawer");
		} else {
			if (new Tile(2928,3448,0).distanceTo(ctx.players.local().tile())<5) {
				Method.interactO(STIXSTAIRSBOTTOM_ID, "Climb","Climbing device");
			} else if(Vars.DYNAMICV){
				Method.walking(pathToMrStix,"Walking to Pikkupstix",false);
			}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
					TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
				Vars.DYNAMICV=true;
			}else if(Method.TaverlyLodeAct()){
				Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
			}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
		}

	}

	private void cs3() {
		Method Method = new Method(ctx);
		final String opt[] = {"has been kidnapped"};
		
		if (new Tile(2932,3446,0).distanceTo(ctx.players.local().tile())<6) {//stix region.
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
			if(ctx.widgets.component(1243,1).valid()){
				ctx.widgets.component(1243,50).click(true);
			}else
			if (!Method.isChatting("Pikkupstix")) {
				Method.speakTo(6988, "Pikkupstix");
			}

			
		} else if(Vars.DYNAMICV){
			Method.walking(pathToMrStix,"Walking to Pikkupstix",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());
	}

	private void cs2() {

		if (new Tile(2881,3430,0).distanceTo(ctx.players.local().tile())<6) {//Mrs stix region.
				Vars.DYNAMICV = false;
					if (!Method.isChatting("Scaletrix")) {
						Method.speakTo(15055, "Scaletrix");
					}
		} else if (Vars.DYNAMICV) {
			Method.walking(pathToMrStix,"Walking to Scaletrix.",false);
		} else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());

	}

	private void cs1() {//start the quest
		Method Method = new Method(ctx);
		 final String opt[] = {"Do you have"};
		if (new Tile(2932,3446,0).distanceTo(ctx.players.local().tile())<7) {//stix region.
			
			if (!Method.startQuestOpen())
				if (!Method.findOption(opt))
					if (!Method.isChatting("Pikkipstix")) {
						Vars.DYNAMICV = false;
						Method.speakTo(6988, "Pikkupstix");
					}
		} else if(Vars.DYNAMICV){
			Method.state("Walking to Pikkupstix");
			//Method.walkToLocation(new Tile(2929, 3449, 0));
			Method.walking(pathToMrStix,"Walking to Pikkupstix",false);
		}else if(TeleportLode.BURTHORPE.getTile().distanceTo(ctx.players.local().tile())<6||
				TeleportLode.TAVERLY.getTile().distanceTo(ctx.players.local().tile())<6){
			Vars.DYNAMICV=true;
		}else if(Method.TaverlyLodeAct()){
			Method.teleportTo(TeleportType.TAVERLY.getTeleport(),TeleportType.TAVERLY.getName());
		}else Method.teleportTo(TeleportType.BURTHHORPE.getTeleport(),TeleportType.BURTHHORPE.getName());

	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart == 2);
	}
}