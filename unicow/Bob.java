package unicow;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Widget;

import unicow.UniData.items;

public class Bob extends MethodProvider{

	public Bob(MethodContext arg0) {
		super(arg0);
	}
	UniMethod Method = new UniMethod(ctx);
	
	public static int[] summoningPotion = {12140,12142,12144,12146};//the potion & Id during drinking
	
	public Widget SUMMONING_BASE = ctx.widgets.get(671);//the main widget id
	public Component SUMMONING_CLOSEBUTTON = ctx.widgets.get(671,21);
	public Component SUMMONING_FIRSTSLOTF = ctx.widgets.get(671,25).getChild(2);//familiar
	public Component SUMMONING_FIRSTSLOTP = ctx.widgets.get(671,30);//player
	public Component SCREEN_FAMILIARBUTTON = ctx.widgets.get(1430, 5);//button to take items quickly
	
	static int familiarItem = -1;//stores the id of whatever the familiar is carrying
	
	public boolean give(int itemID, int familiarID){
		SUMMONING_FIRSTSLOTF = ctx.widgets.get(671,25).getChild(2);
		
		if(SUMMONING_CLOSEBUTTON.isVisible()){
			
			while(SUMMONING_FIRSTSLOTF.getItemId()!=-1){//beast's first slot
				familiarItem = SUMMONING_FIRSTSLOTF.getItemId();//stores item beast is carrying
				System.out.println("Returning true: "+ SUMMONING_FIRSTSLOTF.getItemId());
				return true;
			}
				for(int i = 0; i< 27;i++){
					if(SUMMONING_FIRSTSLOTP.getChild(i).getItemId()==items.HORN.getID()){
						System.out.println("ID: "+SUMMONING_FIRSTSLOTP.getChild(i).getItemId());
						ctx.game.sleep(Random.nextInt(500, 1000));
						SUMMONING_FIRSTSLOTP.getChild(i).interact("Store-All");
						familiarItem = SUMMONING_FIRSTSLOTF.getItemId();//stores item beast is carrying
						break;
					}
				}
			
			
			
		}else {
			System.out.println("Interacting with familiar");
			Method.npcInteract(familiarID, "Store");
		}
		
		return false;
	}
	public void takeItems(){
		SCREEN_FAMILIARBUTTON.interact("Take BoB");
	}
	public boolean familiarExpired(){
		return ctx.settings.get(1831)==-1;
	}
	
}
