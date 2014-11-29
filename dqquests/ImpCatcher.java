package dqquests;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Player;

import dqbody.DeltaQuester;
import dqbody.Method;
import dqbody.DeltaNode;
import dqbody.Vars;
import dqbody.Vars.TeleportLode;
import dqbody.Vars.TeleportType;



public class ImpCatcher extends DeltaNode {

	
	public ImpCatcher(ClientContext ctx) {
		super(ctx);
	}

	public final static Tile[] pathToWizard = new Tile[] { 
		new Tile(3233,3221,0),new Tile(3222,3241,0),
		new Tile(3203,3237,0), new Tile(3205,3246,0),new Tile(3218,3249),
		new Tile(3217,3259,0), new Tile(3214,3271,0),new Tile(3203,3277,0),
		new Tile(3196,3279,0),new Tile(3184,3284,0), new Tile(3174,3287,0),
		new Tile(3169,3289,0), new Tile(3156,3290), new Tile(3145,3293,0),
		new Tile(3135,3294,0), new Tile(3119,3296,0),new Tile(3108,3297),
		new Tile(3108,3286,0), new Tile(3103,3271,0), new Tile(3105,3258,0),
		new Tile(3105,3243,0), new Tile(3106,3228,0), new Tile(3103,3217,0),
		new Tile(3102,3206,0), new Tile(3101,3194,0), new Tile(3100,3185,0),
		new Tile(3101,3172,0), new Tile(3103,3161,0), new Tile(3102,3156,0)};
	//G.E
	public int itemDAmount[] = {1,1,1,1};
	public int itemsArray[] = {0,0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1474,1470,1476,1472};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {5500,5500,5500,5500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Black bead", "Red bead","White bead","Yellow bead"};//contains the names of the items needing to be purchased.
	public int bankItems[] = {1474,1470,1476,1472};
	public int bankItemAmount[] = {1,1,1,1};
	public Method Method = new Method(ctx);
	public Vars Vars = new Vars();
	
	boolean q = true;
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		DeltaQuester.numSteps = 3;
	
		if(DeltaQuester.checkedBank)
		Method.determineBank(bankItems);
		if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2669)&0x3)!=2){
			Method.checkBank();
		}else
		if(Vars.useBank && (ctx.varpbits.varpbit(2669)&0x3)!=2){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if (DeltaQuester.GEFeature && (ctx.varpbits.varpbit(2669)&0x3)!=2) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
		if((ctx.varpbits.varpbit(2669)&0x3)==2){
			DeltaQuester.progress = 3;
			DeltaQuester.state = "The Imp Catcher quest has been completed.";
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.varpbits.varpbit(2669)&0x1)==1){
			DeltaQuester.progress = 2;
			cS1();//Finish the quest by giving beads
		}else
		if((ctx.varpbits.varpbit(2669)&0x1)==0){
			DeltaQuester.progress = 1;
			cS1();//Start the quest
		}
	}
	
	private void cS1() {
		final String opt[]  ={"I've got","Can I help"};
		Player local = ctx.players.local();
		
		if(ctx.game.floor()==1){
			if(new Tile(3107,3147,1).distanceTo(local.tile())<5){
				Method.skipPics();
				if(Method.npcIsNotNull(16187)){
					
					if(Method.getNPC(16187).animation()==-1)
						  if(!Method.startQuestOpen())
						   if(!Method.findOption(opt))
						     if(!Method.isChatting("Wizard")){
						      	Method.speakTo(16187, "Wizard");
						}
				}
			}else Method.findPath(new Tile(3107,3147,1), "Walking to the wizard");
		}else if(new Tile(3103,3159,0).distanceTo(local.tile())<5){
			Method.interactO(79770, "Ascend","Portal");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToWizard, "Walking to the Wizards Tower",false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<5||
				TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<5){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
	}
	

	public boolean activate(){
		
		return DeltaQuester.scriptToStart ==17;
	}
	
}
