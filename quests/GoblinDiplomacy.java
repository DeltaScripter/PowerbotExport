package quests;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

import quests.Vars.TeleportType;

public class GoblinDiplomacy extends Node{

	public GoblinDiplomacy(MethodContext ctx) {
		super(ctx);
	}

	public final Tile[] pathToGoblins = new Tile[] { 
			new Tile(2959,3411,0), new Tile(2957,3414,0),
			new Tile(2956,3417,0),
			new Tile(2947,3432, 0), new Tile(2944,3447, 0),
			new Tile(2947,3464, 0), new Tile(2955,3479, 0),
			new Tile(2954,3492, 0), new Tile(2956,3508, 0),
			new Tile(2958,3511, 0)};
	
	final Area GoblinDoor = new Area(new Tile[] {new Tile(2953, 3512, 0), new Tile(2953, 3506, 0), new Tile(2959, 3507, 0), 
			new Tile(2961, 3515, 0) });
	
	public boolean init = false;
	//G.E
	public int itemsArray[] = {0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {289,1767,1769};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {3,1,1};
	public int itemDPrice[] = {4000,4000,4500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Goblin mail", "Blue dye", "Orange dye"};//contains the names of the items needing to be purchased.
	
	public Vars Vars = new Vars();
	public Method Method = new Method(ctx);
	public int bankItems[] = {288,288,288,1767,1769,287};
	public int bankItemAmount[] = {1,1,1,1,1,1};
	
	public void execute(){
		DeltaQuester.numSteps  =5;
		
		while(ctx.settings.get(1114)==1){
			Method.pressContinue();
			if(ctx.settings.get(2137)==6){
				Method.state("The Goblin Diplomacy quest has been completed.");
				Method.sleep(2000);
				DeltaQuester.e = true;
				break;
			}
		}		
		DeltaQuester.numSteps  =5;
		
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
		
		if(!DeltaQuester.checkedBank && (ctx.settings.get(2137) & 0x7) != 6){
			Method.checkBank();
		}else
	    if(Vars.useBank &&(ctx.settings.get(2137) & 0x7) != 6){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if (DeltaQuester.GEFeature) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(!DeltaQuester.exchangeBank){
			Method.exchangeBank(289,288,3);
		}else
		if(!init){
			if(!Method.FaladorLodeIsActive()){
				Method.state("Falador lodestone must be active, skipping quest.");
				Method.sleep(2000);
				DeltaQuester.e  =true;
			}else init = true;
		}else
			if ((ctx.settings.get(2137) & 0x7) == 6) {
					DeltaQuester.progress = 4;
					if(ctx.widgets.get(1244).isValid()){
						ctx.widgets.get(1244,19).click(true);
					}else{
						DeltaQuester.progress = 5;
					Method.state("The Goblin Diplomacy quest has been completed.");
					Method.sleep(2000);
					DeltaQuester.e = true;
					}
				}else
				if((ctx.settings.get(2137) & 0x7) ==5){
					DeltaQuester.progress = 4;
					if(ctx.settings.get(1114)==0){
						cS0();
						}else Method.isChatting("Person");
				}else
				if((ctx.settings.get(2137) & 0x7) ==4){
					DeltaQuester.progress = 3;
					if(ctx.settings.get(1114)==0){
						cS0();
						}else Method.isChatting("Person");
				}else
				if((ctx.settings.get(2137) & 0x3) ==3){
					DeltaQuester.progress = 2;
					if(ctx.settings.get(1114)==0){
						cS0();
						}else Method.isChatting("Person");
				}else
				if((ctx.settings.get(2137) & 0x1) ==0){
					DeltaQuester.progress = 1;
					cS0();
				}
	}


	private void createMails() {
		if(ctx.settings.get(1114)==0){
	if(Method.inventoryContains(286) && Method.inventoryContains(288) &&Method.inventoryContains(1769)){
		Method.combineItems(288, 1769);
	}else if (!Method.inventoryContains(287) &&Method.inventoryContains(288) &&Method.inventoryContains(1767)){
		Method.combineItems(288, 1767);
	}
		}
		
	}

	private boolean doorContains(){
		for(GameObject o : ctx.objects.select().id(77969).nearest().first()){
			if(GoblinDoor.contains(o.getLocation())){
				return true;
			}
		}
		
		return false;
	}
	private void cS0() {
		Player local = ctx.players.local();
		final String opt[] = { "No,", "brown armour here", "blue armour here",
				"orange armour here" };
		final String opt2[] = { "What about a different", "Do you want me",
				"No," };

			//ctx.camera.setYaw(50);
		if (!Method.objIsNotNull(77969)&& new Tile(2957, 3514, 0).distanceTo(local.getLocation()) < 4 || new Tile(2957, 3514, 0).distanceTo(local.getLocation()) < 4 && Method.objIsNotNull(77969) && !doorContains()) {
			createMails();
			if (ctx.settings.get(2137) == 3 || ctx.settings.get(2137) == 516
					|| ctx.settings.get(2137) == 1029) {
				if (!Method.findOption(opt))
					if (!Method.isChatting("Goblin")) {
						Method.speakTo(4493, "Person");
					}
			} else if (!Method.findOption(opt2))
				if (!Method.isChatting("Goblin")) {
					Method.speakTo(4493, "Person");
				}

		} else if (new Tile(2957, 3509, 0).distanceTo(local.getLocation()) < 4) {// Tile by goblin
																// generals.
			if (!local.isInMotion())
				Method.interactO(77969, "Open", "Door");

		} else if (Vars.DYNAMICV) {
			Method.walking(pathToGoblins, "Walking to goblins", false);
		} else if (new Tile(2965, 3404, 0).distanceTo(local.getLocation()) < 5) {// Tile of Falador
																// lodestone.
			Vars.DYNAMICV = true;
		} else
			Method.teleportTo(TeleportType.FALADOR.getTeleport(),TeleportType.FALADOR.getName());// Teleport to Falador.

	}

	public boolean activate(){
		return (DeltaQuester.scriptToStart==16);
	}
}
