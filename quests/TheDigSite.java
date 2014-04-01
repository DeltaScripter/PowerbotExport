/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class TheDigSite extends Node{
	
	public final Tile[] pathToExaminerFV = new Tile[] {
			new Tile(3216, 3378, 0), new Tile(3222, 3378, 0),
			new Tile(3228, 3377, 0), new Tile(3233, 3376, 0),
			new Tile(3239, 3376, 0), new Tile(3244, 3374, 0),
			new Tile(3247, 3373, 0), new Tile(3253, 3373, 0),
			new Tile(3258, 3372, 0), new Tile(3264, 3372, 0),
			new Tile(3270, 3372, 0), new Tile(3276, 3372, 0),
			new Tile(3282, 3372, 0), new Tile(3288, 3373, 0),
			new Tile(3292, 3369, 0), new Tile(3296, 3365, 0),
			new Tile(3299, 3360, 0), new Tile(3303, 3356, 0),
			new Tile(3303, 3350, 0), new Tile(3303, 3344, 0),
			new Tile(3303, 3338, 0), new Tile(3307, 3334, 0),
			new Tile(3312, 3332, 0), new Tile(3317, 3335, 0),
			new Tile(3323, 3335, 0), new Tile(3328, 3336, 0),
			new Tile(3332, 3340, 0), new Tile(3334, 3345, 0),
			new Tile(3340, 3344, 0), new Tile(3342, 3347, 0),
			new Tile(3348, 3347, 0), new Tile(3353, 3346, 0),
			new Tile(3356, 3343, 0), new Tile(3356, 3343, 0),
			new Tile(3356, 3343, 0)};
	
	public final Tile[] pathToMuseumFV = new Tile[] {
			new Tile(3213, 3377, 0), new Tile(3213, 3383, 0),
			new Tile(3213, 3389, 0), new Tile(3211, 3394, 0),
			new Tile(3211, 3400, 0), new Tile(3210, 3405, 0),
			new Tile(3210, 3411, 0), new Tile(3210, 3413, 0),
			new Tile(3211, 3418, 0), new Tile(3215, 3423, 0),
			new Tile(3219, 3427, 0), new Tile(3224, 3426, 0),
			new Tile(3229, 3428, 0), new Tile(3234, 3431, 0),
			new Tile(3240, 3431, 0), new Tile(3243, 3436, 0),
			new Tile(3245, 3441, 0), new Tile(3247, 3445, 0),
			new Tile(3252, 3448, 0), new Tile(3255, 3444, 0)};
	
	public final Tile[] pathToStudent1 =new Tile[] {
			new Tile(3214, 3376, 0), new Tile(3219, 3375, 0),
			new Tile(3225, 3375, 0), new Tile(3230, 3373, 0),
			new Tile(3235, 3372, 0), new Tile(3241, 3372, 0),
			new Tile(3246, 3371, 0), new Tile(3252, 3371, 0),
			new Tile(3257, 3370, 0), new Tile(3262, 3371, 0),
			new Tile(3267, 3372, 0), new Tile(3273, 3372, 0),
			new Tile(3278, 3370, 0), new Tile(3283, 3371, 0),
			new Tile(3286, 3371, 0), new Tile(3291, 3372, 0),
			new Tile(3292, 3367, 0), new Tile(3294, 3362, 0),
			new Tile(3298, 3358, 0), new Tile(3299, 3353, 0),
			new Tile(3301, 3348, 0), new Tile(3301, 3342, 0),
			new Tile(3302, 3337, 0), new Tile(3306, 3333, 0),
			new Tile(3311, 3331, 0), new Tile(3316, 3334, 0),
			new Tile(3322, 3334, 0), new Tile(3327, 3335, 0),
			new Tile(3330, 3340, 0), new Tile(3334, 3345, 0),
			new Tile(3340, 3344, 0), new Tile(3342, 3347, 0),
			new Tile(3347, 3345, 0), new Tile(3351, 3341, 0),
			new Tile(3347, 3345, 0), new Tile(3342, 3347, 0),
			new Tile(3336, 3347, 0), new Tile(3334, 3352, 0),
			new Tile(3334, 3358, 0), new Tile(3336, 3363, 0),
			new Tile(3340, 3367, 0), new Tile(3343, 3372, 0),
			new Tile(3340, 3377, 0), new Tile(3343, 3382, 0),
			new Tile(3344, 3387, 0), new Tile(3344, 3393, 0),
			new Tile(3344, 3399, 0), new Tile(3345, 3404, 0),
			new Tile(3345, 3410, 0), new Tile(3345, 3416, 0),
			new Tile(3345, 3422, 0), new Tile(3345, 3423, 0),
			new Tile(3345, 3425, 0), new Tile(3345, 3425, 0),
			new Tile(3345, 3425, 0), new Tile(3345, 3425, 0),
			new Tile(3345, 3425, 0)};

	public final Tile[] pathToBush = new Tile[] {
			new Tile(3347, 3421, 0), new Tile(3348, 3416, 0),
			new Tile(3353, 3415, 0), new Tile(3358, 3413, 0),
			new Tile(3359, 3408, 0), new Tile(3359, 3402, 0),
			new Tile(3363, 3397, 0), new Tile(3367, 3393, 0),
			new Tile(3370, 3388, 0), new Tile(3373, 3383, 0),
			new Tile(3376, 3378, 0), new Tile(3371, 3375, 0),
			new Tile(3366, 3376, 0), new Tile(3371, 3375, 0),
			new Tile(3376, 3377, 0), new Tile(3374, 3382, 0),
			new Tile(3368, 3382, 0), new Tile(3363, 3380, 0),
			new Tile(3360, 3375, 0), new Tile(3357, 3370, 0),
			new Tile(3358, 3372, 0)};

public final Tile[] pathToSecondStudent = 	 new Tile[] {
		new Tile(3348, 3423, 0), new Tile(3354, 3422, 0),
		new Tile(3359, 3421, 0), new Tile(3364, 3420, 0),
		new Tile(3369, 3419, 0), new Tile(3368, 3417, 0)};

public final Tile[] pathToPan =new Tile[] {
		new Tile(3369, 3417, 0), new Tile(3369, 3417, 0),
		new Tile(3367, 3416, 0), new Tile(3365, 3410, 0),
		new Tile(3366, 3405, 0), new Tile(3365, 3399, 0),
		new Tile(3365, 3393, 0), new Tile(3364, 3388, 0),
		new Tile(3369, 3390, 0), new Tile(3368, 3385, 0), 
		new Tile(3370, 3380, 0), new Tile(3369, 3379, 0)};

public final Tile[] pathToSecondStudentFromPan =new Tile[] {
		new Tile(3378, 3378, 0), new Tile(3375, 3383, 0),
		new Tile(3375, 3389, 0), new Tile(3374, 3394, 0),
		new Tile(3374, 3400, 0), new Tile(3374, 3406, 0),
		new Tile(3374, 3412, 0), new Tile(3370, 3416, 0),
		new Tile(3366, 3415, 0)};

public final Tile[] pathToFind = new Tile[] {
		new Tile(3214, 3376, 0), new Tile(3219, 3375, 0),
		new Tile(3224, 3377, 0), new Tile(3230, 3376, 0),
		new Tile(3236, 3376, 0), new Tile(3241, 3375, 0),
		new Tile(3247, 3375, 0), new Tile(3252, 3373, 0),
		new Tile(3258, 3373, 0), new Tile(3263, 3371, 0),
		new Tile(3269, 3371, 0), new Tile(3274, 3369, 0),
		new Tile(3279, 3371, 0), new Tile(3285, 3370, 0),
		new Tile(3290, 3372, 0), new Tile(3295, 3369, 0),
		new Tile(3299, 3364, 0), new Tile(3302, 3359, 0),
		new Tile(3303, 3354, 0), new Tile(3305, 3349, 0),
		new Tile(3303, 3344, 0), new Tile(3304, 3339, 0),
		new Tile(3307, 3334, 0), new Tile(3312, 3331, 0),
		new Tile(3317, 3335, 0), new Tile(3323, 3335, 0),
		new Tile(3328, 3337, 0), new Tile(3332, 3341, 0),
		new Tile(3333, 3346, 0), new Tile(3333, 3352, 0),
		new Tile(3334, 3358, 0), new Tile(3338, 3362, 0),
		new Tile(3340, 3364, 0), new Tile(3343, 3369, 0),
		new Tile(3342, 3374, 0), new Tile(3340, 3379, 0),
		new Tile(3344, 3383, 0), new Tile(3344, 3389, 0),
		new Tile(3344, 3395, 0), new Tile(3344, 3401, 0),
		new Tile(3345, 3406, 0), new Tile(3345, 3406, 0),
		new Tile(3345, 3412, 0), new Tile(3345, 3418, 0),
		new Tile(3350, 3420, 0), new Tile(3355, 3421, 0),
		new Tile(3361, 3421, 0), new Tile(3364, 3426, 0),
		new Tile(3366, 3431, 0), new Tile(3371, 3434, 0),
		new Tile(3372, 3439, 0), new Tile(3372, 3439, 0)};

public final Tile[] pathToChestFV =new Tile[] {
		new Tile(3216, 3378, 0), new Tile(3222, 3378, 0),
		new Tile(3228, 3377, 0), new Tile(3233, 3376, 0),
		new Tile(3239, 3376, 0), new Tile(3244, 3375, 0),
		new Tile(3245, 3375, 0), new Tile(3250, 3374, 0),
		new Tile(3255, 3372, 0), new Tile(3261, 3372, 0),
		new Tile(3266, 3371, 0), new Tile(3271, 3370, 0),
		new Tile(3277, 3370, 0), new Tile(3282, 3371, 0),
		new Tile(3288, 3371, 0), new Tile(3293, 3367, 0),
		new Tile(3296, 3362, 0), new Tile(3300, 3358, 0),
		new Tile(3303, 3353, 0), new Tile(3304, 3348, 0), 
		new Tile(3303, 3343, 0), new Tile(3304, 3338, 0),
		new Tile(3305, 3337, 0), new Tile(3309, 3333, 0),
		new Tile(3314, 3332, 0), new Tile(3319, 3331, 0),
		new Tile(3324, 3335, 0), new Tile(3328, 3339, 0),
		new Tile(3330, 3344, 0), new Tile(3330, 3350, 0),
		new Tile(3334, 3354, 0), new Tile(3339, 3358, 0),
		new Tile(3341, 3359, 0), new Tile(3346, 3361, 0),
		new Tile(3350, 3365, 0), new Tile(3353, 3370, 0),
		new Tile(3358, 3371, 0), new Tile(3360, 3376, 0),
		new Tile(3364, 3380, 0), new Tile(3369, 3382, 0),
		new Tile(3370, 3379, 0)};



	public boolean init = false;
	public boolean foundTeddy = true;
	public boolean foundSkull = true;
	public boolean foundCup =true;
	public boolean aSet = false;
	public boolean cInv = false;
	public boolean r = false, y = false; //Used for resetting cS2.
	public Tile initTile;
	public Tile initTile2;
	public int[] students = {0,0,0};
	public final int[] junk = {526,1203,526,1151,1237,434,1061,2313,1203};
	public final int[] unStackJunk = {695,698,688,697,684};
	public final Tile[] dropTiles = {new Tile(3369,3434,0), new Tile(3374,3434,0), new Tile(3380,3439,0), new Tile(3377,3444,0)};
	public final int[] cS6Settings = {898};
	//G.E Function
	public int[] itemsArray = {0,0,0,0,0,0,0,0};
	public int itemDAmount[] = {1,1,3,1,1,1,1,1};
	public final int[] itemDID = {1978, 233, 955, 229,590, 1625,1059,1061};
	public final String[] itemDString = {"Cup of tea", "Pestle and mortar", "Rope", "Vial", "Tinderbox", "Uncut opal", "Leather gloves", "Leather boots"};
	public final int[] itemDPrice = {530, 2550, 500, 200,200,200,200,300};

	public boolean inits = false;

	public void execute() {
		if(!inits){
			if(!Method.VarrokLodeIsActive()){
				Method.state("Varrok lodestone must be active, skipping quest.");
				Task.sleep(2000);
				DeltaQuester.e  =true;
			}else inits = true;
		}else
		if(Widgets.get(137,58).getChild(0).getText().contains("You don't have enough space")){
			Quests.state("Out of room in inventory.");
		}else
		if (DeltaQuester.GEFeature) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else{//Code goes here.
			Quests.getSettings(2205);
			init();
			DeltaQuester.numSteps = 10;
			Quests.foodSupport();
			if(Vars.VARROKLODE.distanceTo()<7)
				Method.teleporting = false;
			
			dropJunk();
			if(Settings.get(2205)==8){
				cS8();
				DeltaQuester.progress = 9;
			}else
			if(Settings.get(2209)==966 ||Settings.get(2209)==974|| Settings.get(2209)==990){
				cS7();
				DeltaQuester.progress = 8;
			}else
			if(Quests.containsSetting(cS6Settings, 2209) || Settings.get(2205)==6){
				DeltaQuester.progress = 7;
				cS6();//head into the cave and examine rocks
			}else{
			switch(Quests.stepX){
			
			case 0:
				DeltaQuester.progress = 1;
				cS0();//Start the quest...and take level 1 exaam.
				break;
				
			case 1:
				DeltaQuester.progress = 2;
				cS1();//Find the museum man.
				break;
				
			case 2:
				DeltaQuester.progress = 3;
				cS2();//Help out the three students.
			break;
			
			case 3:
				DeltaQuester.progress = 4;
				cS3();//Take the second-level exam.
				break;
				
			case 4:
				DeltaQuester.progress = 5;
				cS4();//take the third.
				break;
				
			case 5:
				DeltaQuester.progress = 6;
				cS5();//Find and show the talisman to the expert.
				break;
				
			case 9:
				DeltaQuester.progress = 10;
				DeltaQuester.state = "The Dig Site quest has been completed.";
				Task.sleep(2000);
				DeltaQuester.e = true;
				break;
			
			}
			}
		}
	}

	private void cS8() {
		if(!Method.teleporting){
			if(Widgets.get(1189).validate()){
				Quests.clickContinue();
			}else
		if(Quests.tActive()){
			Quests.clickContinue();
		}else if(Inventory.getItem(699)!=null){//tablet id
			if(SceneEntities.getNearest(31130)!=null){//If your inside the cave.
			SceneObject rope = SceneEntities.getNearest(2353);//rope id
			if(rope!=null){
				if(rope.getLocation().distanceTo()<5){
					Quests.DYNAMICV = false;
					showETali();
				}else Walking.walk(rope.getLocation());
			}
			}else showETali();
		}else
		if(SceneEntities.getNearest(31130)!=null){
			SceneObject tablet = SceneEntities.getNearest(17369);
			if(tablet!=null){
				if(tablet.getLocation().distanceTo()<5){
					Quests.interactO(17369, "Take");
				}else Walking.walk(tablet.getLocation());
			}else Quests.state("Cannot find tablet.");
		}else if (new Tile(3353, 3408, 0).distanceTo() < 13) {
				Quests.DYNAMICV = true;
					if(new Tile(3352,3416,0).distanceTo()<5){
						if(Inventory.getItem(954)!=null  && Widgets.get(137).validate() && !Widgets.get(137).getChild(57).getChild(0).getText().contains("There is")){
							if(Inventory.getSelectedItem()!=null){
								Quests.interactO(2350, "Use");
							}else Quests.interactInventory(954, "Use");
						}else Quests.interactO(2350, "Operate");
					}else Quests.findPath(new Tile(3352,3416,0));
				
			}else if(new Tile(3348,3410,0).distanceTo()<8){
				Quests.findPath(new Tile(3353,3408,0));
			}else if(Quests.DYNAMICV){
				Quests.walking(pathToStudent1, "Walking to winch",false);
			}else if(Quests.varrokLode.distanceTo()<5){
				Quests.DYNAMICV = true;
			}else Method.teleportTo(51);
		}else Method.teleportTo(51);
	}

	private void cS7() {
		
		if(!Method.teleporting){
			if(aSet){
				if(Inventory.getItem(707)!=null || Settings.get(2205)==7){
					cS6();
				}else if(Quests.tActive()){
					Quests.clickContinue();
				}else
				if(Inventory.getItem(706)!=null){
					if(Inventory.getSelectedItem()!=null){
						Quests.interactInventory(706, "Use");
					}else 	Quests.interactInventory(708, "Use");
				}else
				if(Inventory.getItem(705)!=null){
					if(Inventory.getSelectedItem()!=null){
						Quests.interactInventory(705, "Use");
					}else 	Quests.interactInventory(704, "Use");
				}else
				if(Inventory.getItem(703)!=null && Inventory.getItem(701)!=null){
					Quests.DYNAMICV = false;
					if(Inventory.getSelectedItem()!=null){
						Quests.interactInventory(703, "Use");
					}else Quests.interactInventory(701, "Use");
				}else
				showETali();
			}else if(Inventory.getItem(703)!=null || Inventory.getItem(705)!=null|| Inventory.getItem(706)!=null|| Inventory.getItem(707)!=null || Settings.get(2205)==7){//the actual boom thing potion..
				aSet = true;
			}else 
		if(Inventory.getItem(709)!=null || new Tile(3373,3378,0).distanceTo()<6 || Inventory.getItem(700)!=null){
			openChest();
		}else
		if(NPCs.getNearest(614)!=null && NPCs.getNearest(614).isOnScreen()){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chatting()){
				Quests.clickContinue();
				}else if(Quests.findOption("How could I")){
					Quests.clickOption(1188, Quests.value);
				}
			}else Quests.speakTo(614);
		}else
		if(new Tile(3369,3427,0).distanceTo()<5){
			if(Inventory.getItem(954)!=null  && Widgets.get(137).validate() && !Widgets.get(137).getChild(58).getChild(0).getText().contains("There is")){
				if(Inventory.getSelectedItem()!=null){
					Quests.interactO(2351, "Use");
				}else Quests.interactInventory(954, "Use");
			}else Quests.interactO(2351, "Operate");
			
		}else if(SceneEntities.getNearest(31130)!=null){//inside first cave...
			if(new Tile(SceneEntities.getNearest(2353).getLocation().x(),SceneEntities.getNearest(2353).getLocation().getY(),0).distanceTo()<5){
				Quests.interactO(2353, "Climb");
			}else new Tile(SceneEntities.getNearest(2353).getLocation().x(),SceneEntities.getNearest(2353).getLocation().getY(),0).clickOnMap();
		}else if(new Tile(3354,3417,0).distanceTo()<17){
			Quests.findPath(new Tile(3369,3427,0));
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToStudent1, "Walking to next winch",false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		}else Method.teleportTo(51);
	}



	private void openChest() {
		if(new Tile(3370,3379,0).distanceTo()<9){
			if(Inventory.getItem(709)==null){
				if(Inventory.getItem(700)!=null){
					if(new Tile(3364,3379,0).distanceTo()<8){
						if(Inventory.getItem(702)!=null){//init chemical.
							if(Inventory.getItem(704)!=null){//ground coal.
								Quests.DYNAMICV = false;
								aSet=true;
							}else
							if(Inventory.getItem(973)!=null){//coal
								Quests.interactInventory(973, "Grind");
							}else Quests.interactO(2375, "Search");
							
						}else if(Quests.tActive()){
							Quests.clickContinue();
						}else if(Settings.get(2209)==990){
							if(Inventory.getSelectedItem()!=null){
								Quests.interactO(2359, "Use");
							}else Quests.interactInventory(229,"Use");
						}else if(Inventory.getSelectedItem()!=null){
							Quests.interactO(2359, "Use");
						}else Quests.interactInventory(676, "Use");
					}else Quests.findPath(new Tile(3364,3379,0));
				}else
				Quests.interactO(2360, "Search");
			}else
			if(Inventory.getSelectedItem()!=null){
				Quests.interactO(2361, "Use");
			}else Quests.interactInventory(709, "Use");
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToChestFV, "Walking to chest.",false);
		}else if(Quests.varrokLode.distanceTo()<6){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		
	}



	private void cS6() {
		if(SceneEntities.getNearest(31130)!=null){
			if(Inventory.getItem(708)!=null){//stupid root.
				if(new Tile(SceneEntities.getNearest(2353).getLocation().x()+8,SceneEntities.getNearest(2353).getLocation().getY()-2,0).distanceTo()<5){//by the rocks.
					if(Settings.get(2209)==990 || Settings.get(2209)==7){
						if(Widgets.get(1179).validate()){
							Widgets.get(1179, 54).click(true);
						}else
						if(Quests.tActive()){
							Quests.clickContinue();
						}else if(Inventory.getItem(707)!=null){
						if(Inventory.getSelectedItem()!=null){
							Quests.interactO(2362, "Use");
						}else Quests.interactInventory(707, "Use");
						}else Quests.interactO(2362, "Search");
					}else
					Quests.interactO(2362, "Search");
				}else new Tile(SceneEntities.getNearest(2353).getLocation().x()+8,SceneEntities.getNearest(2353).getLocation().getY()-2,0).clickOnMap();
			}else Quests.interactG(708);
		}else
		if (new Tile(3353, 3408, 0).distanceTo() < 13) {
			Quests.DYNAMICV = true;
			if (Settings.get(2205)==6 || Settings.get(2205)==7) {
				if(new Tile(3352,3416,0).distanceTo()<5){
					if(Inventory.getItem(954)!=null  && Widgets.get(137).validate() && !Widgets.get(137).getChild(58).getChild(0).getText().contains("There is")){
						if(Inventory.getSelectedItem()!=null){
							Quests.interactO(2350, "Use");
						}else Quests.interactInventory(954, "Use");
					}else Quests.interactO(2350, "Operate");
				}else Quests.findPath(new Tile(3352,3416,0));
			} else {
				if (Quests.tActive()) {
						if(Quests.chatting()){
							Quests.clickContinue();
						}
				} else if (NPCs.getNearest(4564).getLocation().distanceTo() < 6) {
					if (Inventory.getSelectedItem() != null) {
						Quests.npcInteract(4564, "Use");
					} else
						Quests.interactInventory(696, "Use");
				} else
					NPCs.getNearest(4564).getLocation().clickOnMap();
			}
		}else if(new Tile(3348,3410,0).distanceTo()<8){
			Quests.findPath(new Tile(3353,3408,0));
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToStudent1, "Walking to winch",false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		
	}

	@SuppressWarnings("deprecation")
	private void dropJunk() {
		if(!Method.teleporting){
			if(Tabs.INVENTORY.isOpen()){
				if(Inventory.getItem(1059,1061)!=null && init==true){
					Quests.interactInventory(1059, "Drop");
					Quests.interactInventory(1061, "Drop");
				}else
				if(Inventory.containsOneOf(junk)){
					for(int i: junk){
						Quests.interactInventory(i, "Drop");
					}
				}
			}
		}
		
	}

	private void cS5() {
		if(!Method.teleporting){
		if(Inventory.getItem(670)!=null){//brush..
			if(Inventory.getItem(669)!=null){//jar
					if (Inventory.getItem(681) != null) {
						showETali();//Show the expert the talisman.
					} else {
						if (new Tile(3374, 3442, 0).distanceTo() < 8) {
							if(!Inventory.isFull()){
								if(Camera.getPitch()<70)
									Camera.setPitch(80);
					if(Inventory.getSelectedItem()!=null){
						Quests.DYNAMICV = false;
						if(Players.getLocal().isIdle())
						Quests.interactO(2376, "Use");
						Task.sleep(2300,2400);
					}else Quests.interactInventory(676, "Use");
							}else{
								for(int i = 0; i < dropTiles.length;){
									if(dropTiles[i].distanceTo()<2){
										for(int g: unStackJunk){
											if(Inventory.getItem(g)!=null){
												Quests.interactInventory(g, "Drop");
												i++;
												Task.sleep(1200,1300);
											}else break;
										}
									}else Quests.findPath(dropTiles[i]);
								}
							}
				}else if(new Tile(3375,3432,0).distanceTo()<10){
					Quests.findPath(new Tile(3374,3442,0));
				}else if(Quests.DYNAMICV){
					Quests.walking(pathToFind, "Walking to level 3 dig site.",false);
				}else if(Quests.varrokLode.distanceTo()<5){
					Quests.DYNAMICV = true;
				}else Method.teleportTo(51);
				}
			}else gatherSpecimenJar();
			
		}else gatherSpecimenBrush();
		}else Method.teleportTo(51);
	}

	private void showETali() {
		if(new Tile(3360,3337,0).distanceTo()<4){
			
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				Quests.clickContinue();
			}if(Settings.get(2209)==1022){
				if(Inventory.getSelectedItem()!=null){
					Quests.npcInteract(619, "Use");
				}else if(!Quests.tActive()){
					Quests.interactInventory(699, "Use");
				}
			}else if(Settings.get(2209)==990){
				if(Inventory.getSelectedItem()!=null){
					Quests.npcInteract(619, "Use");
				}else if(!Quests.tActive()){
					if(Inventory.getItem(702)!=null){
					Quests.interactInventory(702, "Use");
					}else Quests.interactInventory(700, "Use");
				}
			}else Quests.speakTo(619);
		}else if(new Tile(3352,3339,0).distanceTo()<15){
			Quests.DYNAMICV = true;
			if(!new Tile(3354,3333,0).canReach()){
				Quests.interactO(17316, "Open");
			}else Quests.findPath(new Tile(3360,3336,0));
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToExaminerFV, "Walking to show expert.",false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		
	}

	private void gatherSpecimenJar() {
		if(new Tile(3354,3333,0).distanceTo()<4){
			Quests.DYNAMICV = false;
			Quests.interactO(17302, "Open");
			Quests.interactO(17303, "Search");
		}else if(new Tile(3352,3339,0).distanceTo()<15){
			Quests.DYNAMICV = true;
			if(!new Tile(3354,3333,0).canReach()){
				Quests.interactO(17316, "Open");
			}else Quests.findPath(new Tile(3354,3333,0));
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToExaminerFV, "Walking to gather specimen jar.",false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		
	}

	private void gatherSpecimenBrush() {
		if(new Tile(3346,3396,0).distanceTo()<8 || (NPCs.getNearest(4565)!=null && NPCs.getNearest(4565).getLocation().distanceTo()<5)){
			if(!Players.getLocal().isMoving())
				Quests.npcInteract(4565, "Steal");
			Quests.DYNAMICV = false;
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToStudent1, "Walking to pickpocket workman.",false);
		}else if(Quests.varrokLode.distanceTo()<5){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		
	}

	private void cS4() {
		if(y){
			cS2();
		}else {
			if(students[2]==0){
				y=true;
			}else{
			students[0]=0;
			students[1]=0;
			students[2]=0;
			}
		}
	}

	private void cS3() {
		if(r){
			cS2();
		}else {
			if(students[2]==0){
				r=true;
			}else{
				students[0]=0;
				students[1]=0;
				students[2]=0;
			}
		}
		
	}

	private void cS2() {
		if(students[0]==1){
			if(students[1]==1){
				if(students[2]==1){
					cS0();//Take the exam again.
				}else if(foundSkull){
					if(new Tile(3360,3399,0).distanceTo()<5 || (NPCs.getNearest(615)!=null && NPCs.getNearest(615).getLocation().distanceTo()<7)){
						if(Quests.tActive()){
							Quests.DYNAMICV = false;
							if(Quests.chat(1191, 17,"No sorry")){
								foundSkull = false;
							}else if(Quests.chat(1191, 17, "I'll remember that")){
								students[2]=1;
							}
							if(Quests.chatting()){
								Quests.clickContinue();
							}
						}else Quests.speakTo(615);
					}else Quests.findPath(new Tile(3360,3399,0));
				}else if(new Tile(3350,3400,0).distanceTo()<6){
					if(Inventory.getItem(671)!=null && Inventory.getItem(670)!=null){//animal skull then that thooth brush ting.
						foundSkull=true;
					}else Quests.npcInteract(4565, "Steal");
				}else Quests.findPath(new Tile(3350,3400,0));
				
			}else if(foundCup){
				if(new Tile(3369,3419,0).distanceTo()<8 || (NPCs.getNearest(616)!=null && NPCs.getNearest(616).getLocation().distanceTo()<6)){
					if(Quests.tActive()){
						if(Quests.chat(1191, 17,"No sorry")){
							foundCup = false;
						}else if(Quests.chat(1191, 17, "Thanks for")){
							students[1]=1;
						}else
						if(Quests.chatting()){
							Quests.clickContinue();
						}
					}else Quests.speakTo(616);
				}else Quests.walking(pathToSecondStudent, "Walking to second student",false);
			}else if(Inventory.getItem(672)!=null){//Special cup ID
				if(new Tile(3369,3419,0).distanceTo()<6){
					foundCup=true;
				}else Quests.walking(pathToSecondStudentFromPan, "Walking to second student",false);
			}else if(Inventory.getItem(677)!=null || Inventory.getItem(679)!=null){
				if(new Tile(3379,3379,0).distanceTo()<6){
					if(Inventory.getItem(679)!=null){
						Quests.interactInventory(679, "Search");
					}else
					if(Quests.tActive()){
						if(Quests.chatting()){
							Quests.clickContinue();
						}else if(Quests.findOption("So how do")){
							Quests.clickOption(1188, Quests.value);
						}
					}else Quests.interactO(2363, "Pan");
				}else Quests.findPath(new Tile(3379,3379,0));
			}else if(new Tile(3369,3379,0).distanceTo()<5){
				if(!Players.getLocal().isMoving())
				Quests.interactG(677);//Grab the pan.
			}else Quests.walking(pathToPan, "Walking to pan",false);
		}else if(foundTeddy){//Below covers the entire method of finishing the first student.
			if(new Tile(3347,3423,0).distanceTo()<5){
				if(Quests.tActive()){
					if(Quests.chat(1191, 17, "No sorry,")){
						foundTeddy = false;
					}else if(Quests.chat(1191, 17, "thanks for your")){
						students[0]=1;
					}
					if(Quests.chatting()){
						Quests.clickContinue();
					}
				}else Quests.speakTo(617);
			}else if(Quests.DYNAMICV){
				Quests.walking(pathToStudent1, "Walking to the first student",false);
			}else if(Quests.varrokLode.distanceTo()<5){
				Quests.DYNAMICV = true;
			}else Method.teleportTo(51);
		}else if(Inventory.getItem(673)!=null){//teady bear.
			foundTeddy = true;
		}else if(new Tile(3358,3372,0).distanceTo()<5){
			if(Quests.tActive()){
				Quests.clickContinue();
			}else Quests.interactO(2358, "Search");
		}else Quests.walking(pathToBush, "Walking to bush",false);
		
	}

	private void cS1() {
		
		if(Settings.get(2209)==768){
		cS0();//Goes back to the examiner.
		}else
		if(new Tile(3255,3448,0).distanceTo()<6){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chatting()){
					Quests.clickContinue();
				}
			}else Quests.speakTo(646);
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToMuseumFV,"Walking to the curator",false);
		}else if(Quests.varrokLode.distanceTo()<6){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);
		
	}

	private void cS0() {
		 
		NPC examiner = NPCs.getNearest(618);
		if(new Tile(3356,3344,0).distanceTo()<8){
			if(Quests.tActive()){
				Quests.DYNAMICV = false;
				if(Quests.chatting()){
					Quests.clickContinue();
				}else if(Quests.findOption("Can I take")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I certainly")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("contents and history")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("All that have")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Gloves and")){
					Quests.clickOption(1188, Quests.value);//End of exam 1..
				}else if(Quests.findOption("Samples taken in")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Finds must be")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Always handle with")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("I am ready")){
					Quests.clickOption(1188, Quests.value);//end of exam 2
				}else if(Quests.findOption("Samples cleaned,")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Brush carefully")){
					Quests.clickOption(1188, Quests.value);
				}else if(Quests.findOption("Handle bones very")){
					Quests.clickOption(1188, Quests.value);
				}else{
					Task.sleep(1200,1300);
					students[0]=0;
					students[1]=0;
					students[2]=0;
				}
			}else if(examiner!=null && examiner.getLocation().canReach()){
				if(examiner.getLocation().distanceTo()<5){
					Quests.speakTo(618);//Examiner.
				}else Quests.findPath(examiner.getLocation());
			}else Quests.interactO(17316, "Open");
		}else if(Quests.DYNAMICV){
			Quests.walking(pathToExaminerFV, "Walking to the examiner",false);
		}else if(Quests.varrokLode.distanceTo()<6){
			Quests.DYNAMICV = true;
		}else Method.teleportTo(51);//Varrok teleport.
		
	}

	@SuppressWarnings("deprecation")
	private void init() {
		if(!Method.teleporting){
		if (!init) {
			Quests.state("Initializing");
			if(cInv==false){
			if(Equipment.containsOneOf(1059,1061)){
				init = true;
			}else cInv= true;
			}else
			if(Inventory.containsOneOf(1059, 1061) ){
			    Quests.interactInventory(1059, "Wear");
			    Quests.interactInventory(1061, "Wear");
			    cInv=false;
			}else init = true;
		}
	}
	}


	public boolean activate() {
		return (DeltaQuester.scriptToStart == 14);
	}

}
*/