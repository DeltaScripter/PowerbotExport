/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.ChatOptions;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ShiloVillage extends Node{

	public boolean hasBelt = false;
	
	public final Tile[] pathToPort = new Tile[] { 
			new Tile(3232, 3220, 0), new Tile(3232, 3225, 0), new Tile(3230, 3230, 0), 
			new Tile(3228, 3235, 0), new Tile(3225, 3239, 0), new Tile(3222, 3243, 0), 
			new Tile(3218, 3246, 0), new Tile(3213, 3246, 0), new Tile(3208, 3246, 0), 
			new Tile(3203, 3247, 0), new Tile(3198, 3248, 0), new Tile(3193, 3248, 0), 
			new Tile(3188, 3246, 0), new Tile(3183, 3246, 0), new Tile(3178, 3246, 0), 
			new Tile(3173, 3246, 0), new Tile(3168, 3247, 0), new Tile(3165, 3251, 0), 
			new Tile(3161, 3254, 0), new Tile(3156, 3256, 0), new Tile(3151, 3259, 0), 
			new Tile(3146, 3259, 0), new Tile(3141, 3260, 0), new Tile(3136, 3261, 0), 
			new Tile(3131, 3261, 0), new Tile(3126, 3261, 0), new Tile(3121, 3261, 0), 
			new Tile(3116, 3261, 0), new Tile(3111, 3263, 0), new Tile(3107, 3266, 0), 
			new Tile(3105, 3271, 0), new Tile(3105, 3276, 0), new Tile(3106, 3281, 0), 
			new Tile(3106, 3286, 0), new Tile(3106, 3291, 0), new Tile(3106, 3296, 0), 
			new Tile(3103, 3292, 0), new Tile(3103, 3287, 0), new Tile(3103, 3282, 0), 
			new Tile(3101, 3277, 0), new Tile(3101, 3272, 0), new Tile(3102, 3267, 0), 
			new Tile(3103, 3262, 0), new Tile(3098, 3262, 0), new Tile(3093, 3262, 0), 
			new Tile(3090, 3258, 0), new Tile(3086, 3254, 0), new Tile(3081, 3252, 0), 
			new Tile(3078, 3256, 0), new Tile(3078, 3261, 0), new Tile(3078, 3266, 0), 
			new Tile(3077, 3271, 0), new Tile(3073, 3275, 0), new Tile(3068, 3276, 0), 
			new Tile(3063, 3275, 0), new Tile(3059, 3272, 0), new Tile(3055, 3269, 0), 
			new Tile(3051, 3266, 0), new Tile(3047, 3263, 0), new Tile(3042, 3263, 0), 
			new Tile(3039, 3259, 0), new Tile(3038, 3254, 0), new Tile(3039, 3249, 0), 
			new Tile(3042, 3245, 0), new Tile(3041, 3240, 0), new Tile(3038, 3236, 0), 
			new Tile(3033, 3236, 0), new Tile(3028, 3235, 0), new Tile(3027, 3230, 0), 
			new Tile(3027, 3225, 0), new Tile(3026, 3220, 0), new Tile(3028, 3217, 0) };
	
	public final Tile[] pathToMoselRei = new Tile[] {
			new Tile(2956, 3147, 0), new Tile(2952, 3147, 0), new Tile(2948, 3147, 0), 
			new Tile(2944, 3147, 0), new Tile(2940, 3147, 0), new Tile(2936, 3148, 0), 
			new Tile(2932, 3148, 0), new Tile(2928, 3150, 0), new Tile(2924, 3150, 0), 
			new Tile(2920, 3150, 0), new Tile(2916, 3150, 0), new Tile(2912, 3151, 0), 
			new Tile(2908, 3152, 0), new Tile(2904, 3154, 0), new Tile(2900, 3154, 0), 
			new Tile(2896, 3153, 0), new Tile(2892, 3153, 0), new Tile(2888, 3153, 0), 
			new Tile(2884, 3154, 0), new Tile(2880, 3156, 0), new Tile(2876, 3158, 0), 
			new Tile(2873, 3161, 0), new Tile(2870, 3164, 0), new Tile(2868, 3168, 0), 
			new Tile(2868, 3172, 0), new Tile(2868, 3176, 0), new Tile(2866, 3180, 0), 
			new Tile(2862, 3181, 0), new Tile(2858, 3183, 0), new Tile(2854, 3185, 0), 
			new Tile(2851, 3188, 0), new Tile(2848, 3191, 0), new Tile(2845, 3194, 0), 
			new Tile(2841, 3196, 0), new Tile(2837, 3196, 0), new Tile(2833, 3196, 0), 
			new Tile(2829, 3194, 0), new Tile(2827, 3190, 0), new Tile(2824, 3187, 0), 
			new Tile(2821, 3184, 0), new Tile(2817, 3184, 0), new Tile(2813, 3183, 0), 
			new Tile(2809, 3181, 0), new Tile(2806, 3178, 0), new Tile(2802, 3177, 0), 
			new Tile(2798, 3176, 0), new Tile(2794, 3174, 0), new Tile(2790, 3172, 0), 
			new Tile(2786, 3173, 0), new Tile(2783, 3176, 0), new Tile(2779, 3176, 0), 
			new Tile(2775, 3176, 0), new Tile(2771, 3176, 0), new Tile(2767, 3174, 0), 
			new Tile(2764, 3171, 0), new Tile(2762, 3167, 0), new Tile(2761, 3163, 0), 
			new Tile(2761, 3159, 0), new Tile(2761, 3155, 0), new Tile(2762, 3151, 0), 
			new Tile(2765, 3148, 0), new Tile(2766, 3144, 0), new Tile(2769, 3140, 0), 
			new Tile(2772, 3137, 0), new Tile(2775, 3134, 0), new Tile(2777, 3130, 0), 
			new Tile(2779, 3126, 0), new Tile(2782, 3123, 0), new Tile(2785, 3120, 0), 
			new Tile(2788, 3117, 0), new Tile(2791, 3114, 0), new Tile(2794, 3110, 0), 
			new Tile(2797, 3107, 0), new Tile(2800, 3104, 0), new Tile(2803, 3101, 0), 
			new Tile(2805, 3097, 0), new Tile(2805, 3093, 0), new Tile(2805, 3089, 0), 
			new Tile(2805, 3085, 0), new Tile(2808, 3082, 0), new Tile(2811, 3079, 0), 
			new Tile(2814, 3076, 0), new Tile(2815, 3072, 0), new Tile(2817, 3068, 0), 
			new Tile(2817, 3064, 0), new Tile(2814, 3061, 0), new Tile(2811, 3057, 0), 
			new Tile(2809, 3053, 0), new Tile(2811, 3049, 0), new Tile(2814, 3046, 0), 
			new Tile(2814, 3042, 0), new Tile(2815, 3038, 0), new Tile(2815, 3034, 0), 
			new Tile(2815, 3030, 0), new Tile(2815, 3026, 0), new Tile(2815, 3022, 0), 
			new Tile(2815, 3018, 0), new Tile(2815, 3014, 0), new Tile(2813, 3010, 0), 
			new Tile(2810, 3006, 0), new Tile(2809, 3002, 0), new Tile(2808, 2998, 0), 
			new Tile(2807, 2994, 0), new Tile(2807, 2990, 0), new Tile(2807, 2986, 0), 
			new Tile(2809, 2982, 0), new Tile(2806, 2978, 0), new Tile(2802, 2975, 0), 
			new Tile(2799, 2972, 0), new Tile(2795, 2969, 0), new Tile(2794, 2958, 0), 
			new Tile(2797, 2955, 0), new Tile(2800, 2952, 0), new Tile(2804, 2950, 0), 
			new Tile(2808, 2950, 0), new Tile(2812, 2948, 0), new Tile(2816, 2946, 0), 
			new Tile(2820, 2945, 0), new Tile(2824, 2944, 0), new Tile(2828, 2944, 0), 
			new Tile(2832, 2944, 0), new Tile(2836, 2943, 0), new Tile(2840, 2943, 0), 
			new Tile(2844, 2943, 0), new Tile(2848, 2943, 0), new Tile(2852, 2943, 0), 
			new Tile(2856, 2942, 0), new Tile(2860, 2942, 0), new Tile(2864, 2941, 0), 
			new Tile(2868, 2941, 0), new Tile(2872, 2941, 0), new Tile(2876, 2942, 0), 
			new Tile(2879, 2945, 0), new Tile(2881, 2949, 0), new Tile(2881, 2951, 0), 
			new Tile(2882, 2953, 0) };
	
	public final Tile[] pathToCave = new Tile[] { 
			new Tile(2808, 3086, 0), new Tile(2810, 3081, 0), new Tile(2813, 3077, 0), 
			new Tile(2817, 3074, 0), new Tile(2820, 3070, 0), new Tile(2824, 3067, 0), 
			new Tile(2828, 3064, 0), new Tile(2831, 3060, 0), new Tile(2834, 3056, 0), 
			new Tile(2839, 3055, 0), new Tile(2844, 3052, 0), new Tile(2849, 3050, 0), 
			new Tile(2854, 3047, 0), new Tile(2859, 3046, 0), new Tile(2864, 3045, 0), 
			new Tile(2869, 3043, 0), new Tile(2874, 3043, 0), new Tile(2879, 3043, 0), 
			new Tile(2884, 3043, 0), new Tile(2889, 3044, 0), new Tile(2893, 3047, 0), 
			new Tile(2898, 3048, 0), new Tile(2903, 3048, 0), new Tile(2908, 3048, 0), 
			new Tile(2913, 3049, 0), new Tile(2913, 3054, 0), new Tile(2911, 3059, 0), 
			new Tile(2908, 3063, 0), new Tile(2914, 3061, 0), new Tile(2915, 3056, 0), 
			new Tile(2916, 3051, 0), new Tile(2917, 3046, 0), new Tile(2921, 3043, 0), 
			new Tile(2923, 3038, 0), new Tile(2926, 3034, 0), new Tile(2929, 3030, 0), 
			new Tile(2930, 3025, 0), new Tile(2932, 3020, 0), new Tile(2933, 3015, 0), 
			new Tile(2933, 3010, 0), new Tile(2933, 3005, 0), new Tile(2930, 3001, 0), 
			new Tile(2926, 2998, 0), new Tile(2921, 2997, 0), new Tile(2923, 3002, 0) };
	
	public final Tile[] pathToCaimIsles = new Tile[] {
			new Tile(2808, 3085, 0), new Tile(2809, 3080, 0), new Tile(2812, 3076, 0), 
			new Tile(2815, 3072, 0), new Tile(2817, 3067, 0), new Tile(2817, 3062, 0), 
			new Tile(2817, 3057, 0), new Tile(2814, 3053, 0), new Tile(2812, 3048, 0), 
			new Tile(2812, 3043, 0), new Tile(2812, 3038, 0), new Tile(2814, 3033, 0), 
			new Tile(2815, 3028, 0), new Tile(2816, 3023, 0), new Tile(2815, 3018, 0), 
			new Tile(2815, 3013, 0), new Tile(2814, 3008, 0), new Tile(2812, 3003, 0), 
			new Tile(2808, 3000, 0), new Tile(2803, 3000, 0), new Tile(2799, 2996, 0), 
			new Tile(2797, 2991, 0), new Tile(2795, 2986, 0), new Tile(2794, 2981, 0), 
			new Tile(2793, 2978, 0) };
	
	public final Tile[] pathToRash = new Tile[] { 
			new Tile(2809, 3086, 0), new Tile(2812, 3090, 0), new Tile(2815, 3094, 0), 
			new Tile(2820, 3097, 0), new Tile(2825, 3097, 0), new Tile(2830, 3097, 0), 
			new Tile(2834, 3094, 0), new Tile(2837, 3090, 0), new Tile(2841, 3087, 0), 
			new Tile(2845, 3084, 0), new Tile(2848, 3080, 0), new Tile(2852, 3077, 0), 
			new Tile(2856, 3074, 0), new Tile(2861, 3072, 0), new Tile(2866, 3072, 0), 
			new Tile(2871, 3072, 0), new Tile(2875, 3069, 0), new Tile(2878, 3065, 0), 
			new Tile(2881, 3061, 0), new Tile(2885, 3057, 0), new Tile(2888, 3053, 0), 
			new Tile(2893, 3052, 0), new Tile(2898, 3050, 0), new Tile(2903, 3049, 0), 
			new Tile(2908, 3047, 0), new Tile(2913, 3046, 0), new Tile(2917, 3049, 0), 
			new Tile(2917, 3054, 0), new Tile(2915, 3059, 0), new Tile(2914, 3064, 0), 
			new Tile(2912, 3069, 0), new Tile(2912, 3074, 0), new Tile(2912, 3079, 0), 
			new Tile(2909, 3084, 0), new Tile(2907, 3089, 0), new Tile(2912, 3091, 0), 
			new Tile(2916, 3093, 0) };
	
	Area crossedArea = new Area(new Tile[] { new Tile(2905, 3064, 0), new Tile(2911, 3039, 0), new Tile(2921, 3041, 0), 
			new Tile(2912, 3071, 0) });
	
	public Tile initTile;//For traversal of caves.
	public Tile initTile2;//For traversal of caves.
	public final int BELTID = 625;
	public final int SPADEID = 952;
	public final int UNLITTORCHID = 596;
	public boolean hasScroll1 = false;
	public boolean hasScroll2 = false;
	public boolean hasCorpse = false;
	public boolean hasBoneShard = false;
	//public boolean hasBerviNote = false;
	public boolean hasSwordPom = false;
	public boolean hasLocater = false;
	public boolean hasNotes = false;
	public boolean hasNecklace = false;
	public boolean shownLoc = false;
	public boolean shownSwordPom = false;
	public boolean shownNotes = false;
	public boolean wearingNecklace = false;
	public boolean hasFinalBody = false;
	
	public boolean showedScroll1 = false;
	public boolean showedScroll2 = false;
	public boolean showedBone = false;
	public boolean readScroll1 = false;
	public boolean readScroll2 = false;
	public boolean crossed = false;//Determines if we crossed the log.
	public boolean checkedDoor = false;
	public Timer wait;

	public int[] bankItems = {1794,526,596,590,952,954,1755,604,605,607,608,609,610,611,616,618,623,625,624};
	
	public int itemsArray[] = {0,0,0,0,0,0,0};//contains the states of items needing to be purchased.
	public int itemDID[] = {1794,527,596,590,952,954,1755};//contains the ids of the items needing to be purchased.
	public int itemDAmount[] = {1,3,1,1,1,1,1};
	public int itemDPrice[] = {500,500,400,400,500,400,500};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Bronze wire", "Bones", "Unlit torch","Tinderbox","Spade","Rope","Chisel"};//contains the names of the items needing to be purchased.
	
	public void execute() {
		Method.foodSupport();
		DeltaQuester.numSteps = 9;
		if(Vars.LUMMBRIDGELODE.distanceTo()<10 ||Vars.DRAYNORLODE.distanceTo()<10||Vars.PORTSARIMLODE.distanceTo()<10){
			Method.teleporting =false;
			Vars.DYNAMICV = true;
		}
		if (DeltaQuester.GEFeature) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(Method.useBank){
		Method.useBank(bankItems, 8, 3, 1);
		}else {
			if((Vars.STEPX & 0xF) ==15){
				DeltaQuester.progress = 9;
				Quests.state("The Shilo Village quest has been completed.");
				Task.sleep(2000);
				DeltaQuester.e = true;
				}else
			if((Vars.STEPX & 0xF) ==14){
				DeltaQuester.progress = 8;
					CS10();//
				}else
			if((Vars.STEPX & 0xF) ==10 ||(Vars.STEPX & 0xF) ==11||(Vars.STEPX & 0xF) ==12){
				DeltaQuester.progress = 7;
					CS9();//Enter the cave and kill the boss.
				}else
			if((Vars.STEPX & 0xF) ==9){
			DeltaQuester.progress = 6;
			
				CS7();//Gather items, show them to Truf, make some stuff, then unlock the final cave..
			}else
			if((Vars.STEPX & 0x7) ==7||(Vars.STEPX & 0xF) ==8){
			DeltaQuester.progress = 5;
			if(!Method.teleporting &&!Method.teleporting&&!Method.teleporting&&Inventory.getItem(604)!=null){
				CS5();
			}else
			CS4();//Gathers the two scrolls and the corpse
			}else
			if((Vars.STEPX & 0x7) ==6){
				DeltaQuester.progress = 4;
				CS3();//Enter the cave initially.
			}else
			if((Vars.STEPX & 0x7) ==5){
				DeltaQuester.progress = 3;
				CS2();//Walk to cave and use rope on the mound.
			}else
			if((Vars.STEPX & 0x1) ==1 ||(Vars.STEPX & 0x7) ==4){
				DeltaQuester.progress = 2;
				CS1();//Walk to the entrance of the cave and use the spade and torch on the mound.
			}else
			if((Vars.STEPX & 0x1) ==0){
				DeltaQuester.progress = 1;
				CS0();//Speak to mosol Rei + gain belt then speak to Trufitus regarding the issue
			}
		}
	}
	
	private void CS10() {
		if(hasFinalBody){
			getToTomb();
		}else
		if(!Method.teleporting&&!Method.isInCombat()&&Inventory.getItem(609)!=null){//Rash's corpse
			initTile2 = null;
			initTile =null;
			Vars.DYNAMICV = false;
			Vars.DYNAMICV2 = false;
			Vars.DYNAMICV3 = false;
			readScroll1 = true;//We've boviously read the scrolls by now
			readScroll2 = true;
			hasFinalBody = true;
		}else CS9();
		
	}

	private void CS9() {
		if(initTile!=null){
			if(new Tile(initTile.getX()-36,initTile.getY()-39,0).canReach()){
				while(Widgets.get(1186).validate()||Widgets.get(1189).validate()){
					ChatOptions.getContinueOption().select(true);
				}
				if(Method.isInCombat()){
					if(Players.getLocal().getInteracting()!=null){
					Method.basicFightNPC(Players.getLocal().getInteracting().getId());
					}
				}else if(NPCs.getNearest(507)!=null){//First form.
					Method.npcInteract(507, "Attack");
				}else if(NPCs.getNearest(508)!=null){
					Method.npcInteract(508, "Attack");
				}else Method.interactO(2258, "Search", "Tomb Dolmen");
			}else
			if(new Tile(initTile.getX(),initTile.getY()-18,0).canReach()){
				if(new Tile(initTile.getX()-36,initTile.getY()-45,0).distanceTo()<10){
					if(Method.isInCombat()){
						final int[] monst = {5358,5353,5357,505};
						for(int y: monst){
						Method.basicFightNPC(y);
						}

					}else
					if((Vars.STEPX & 0xF) ==12||(Vars.STEPX & 0xF) ==14){
						Method.state("Opening door");
						Method.interactO(2246, "Open", "Skeleton door");
						Method.interactO(2247, "Open", "Skeleton door");
						Method.interactO(2248, "Open", "Skeleton door");
						Method.interactO(2249, "Open", "Skeleton door");
					}else//If you haven't used the bone on the door yet.
					if(Widgets.get(1186).validate() || Widgets.get(1189).validate()){
						ChatOptions.getContinueOption().select(true);
					}else
					if(Inventory.getItem(526)!=null){
						if(Inventory.getSelectedItem()!=null){
							Method.interactO(2246, "Use", "Skeleton door");
							Method.interactO(2247, "Use", "Skeleton door");
							Method.interactO(2248, "Use", "Skeleton door");
							Method.interactO(2249, "Use", "Skeleton door");
						}else Method.interactInventory(526, "Use", "Bones");
					}else Method.state("You need bones x 3");
				}else {
					Method.state("Walking to the skeleton gate");
					new Tile(initTile.getX()-36,initTile.getY()-45,0).clickOnMap();
				}
			}else
			if(new Tile(initTile.getX(),initTile.getY()-10,0).canReach()){
				Method.interactO(2257, "Climb", "Rocks");
			}else
			if(!new Tile(initTile.getX(),initTile.getY()-10,0).canReach()){
				if(new Tile(initTile.getX(),initTile.getY()-10,0).distanceTo()<4){
					Method.interactO(2255, "Open", "Metal door");
				}else new Tile(initTile.getX(),initTile.getY()-10,0).clickOnMap();
			}
		}else if(SceneEntities.getNearest(31130)!=null){//CAve check.
			initTile = Players.getLocal().getLocation();
		}else if(new Tile(2917,3092,0).distanceTo()<10){//Location outside the cave.
			if((Settings.get(2795)&0x3) ==2){//SEtting determining door state.
				Method.interactO(37446, "Enter", "Door");
			}else CS8();//Get to the cave part 2
		}else CS7();//part 1 of getting to the cave door
	}

	private void CS8() {
		if(	new Tile(2916, 3093, 0).distanceTo()<10){
			if(wearingNecklace){
			if(checkedDoor){
				
				if((Settings.get(2795) & 1) ==1){
				if(Inventory.getItem(605)!=null){//key itself
					if(Inventory.getSelectedItem()!=null){
						Method.interactO(37447, "Use", "Door");
					}else Method.interactInventory(605, "Use", "Bone Key");
				}else if(Widgets.get(1186).validate()){//Skips through dialogue
					ChatOptions.getContinueOption().select(true);
				}else if(Inventory.getSelectedItem()!=null){//Creates the key.
					Method.interactInventory(604, "Use", "Bone Shard");
				}else Method.interactInventory(1755, "Use", "Chisel");
				}else if(SceneEntities.getNearest(37447)!=null){//With foilage
					Method.interactO(37447, "Search", "Dirty door");
					}
				
				
			}else if(Widgets.get(1186).validate()){//Checks if it contains some text.
				if (Widgets.get(1186,1).getText().contains("seems to be some sort")||
						Widgets.get(1186,1).getText().contains("you see that it has")){
					initTile = null;
					initTile2 = null;
					checkedDoor = true;
				}
			}else if((Settings.get(2795) & 1) ==1){
				Method.interactO(37447, "Open", "Clean door");
			}else if(SceneEntities.getNearest(37447)!=null){//With foilage
				Method.interactO(37447, "Search", "Dirty door");
				}
			}else if(Equipment.containsOneOf(616)){
				wearingNecklace = true;
			}else Method.interactInventory(616, "Wear", "Bone Necklace");
		}else if(crossed){
			Method.walking(pathToRash, "Walking to the demon",false);
		}else if(new Tile(2911,3049,0).distanceTo()<20){
			while(Players.getLocal().getAnimation()!=-1){
				Task.sleep(20,40);
			}
			if(crossedArea.contains(Players.getLocal().getLocation())){
				crossed = true;
			}else if(SceneEntities.getNearest(2332)!=null && SceneEntities.getNearest(2332).getLocation().distanceTo()<8){
				Method.interactO(2332, "Cross", "Log");
			}else SceneEntities.getNearest(2332).getLocation().clickOnMap();
		}else Method.walking(pathToCave, "Walking to a mysterious cave",false);
		
	}
	@SuppressWarnings("deprecation")
	private void CS7() {
		if((Vars.STEPX & 0xF) ==10 ||(Vars.STEPX & 0xF) ==11||(Vars.STEPX & 0xF) ==12
				||(Vars.STEPX & 0xF) ==14){
			hasSwordPom = true;
			hasLocater = true;
			hasNotes = true;
			shownLoc = true;
			shownSwordPom = true;
			shownNotes = true;
		}
		if(!Method.teleporting&& Inventory.containsOneOf(618,616)){
			hasSwordPom = true;
			shownSwordPom = true;
		}	if(hasNecklace){
			CS8();
		}else
		if(!Method.teleporting &&Inventory.getItem(604)==null&&Inventory.getItem(605)==null){//Bone shard or bone key
			CS4();
		}else if(hasSwordPom){
				if(hasLocater){
					if(hasNotes){
					if(new Tile(2808,3085,0).distanceTo()<20){//Trufitus location.
						if(new Tile(2808,3085,0).distanceTo()<10){
							if(Widgets.get(1184).validate()){
								if(Widgets.get(1184,13).getText().contains("My guess is that")){
									shownSwordPom = true;
								ChatOptions.getContinueOption().select(true);
								}else if(Widgets.get(1184,13).getText().contains("Absolutely")){
									shownLoc = true;
									ChatOptions.getContinueOption().select(true);
								}else if(Widgets.get(1184,13).getText().contains("The information is quite specific")){
									shownNotes = true;
									ChatOptions.getContinueOption().select(true);
								}
								ChatOptions.getContinueOption().select(true);
							}else
							if(shownLoc){
								if(shownSwordPom){
									if(shownNotes){
										if(hasNecklace){//Bone key.
										CS8();
										}else//the id 616 is the necklace.
										if(Inventory.getItem(616)!=null || Equipment.appearanceContainsOneOf(616)){
											hasNecklace = true;
										}else
										if(Inventory.getItem(618)!=null){
											if(Widgets.get(1189).validate()){
												ChatOptions.getContinueOption().select(true);
											}else
											if(Inventory.getSelectedItem()!=null){
												Method.interactInventory(1794, "Use", "Bronze wire");
											}else Method.interactInventory(618, "Use", "Beads");
										}else//Below will craft the sword into beads.
										if(Widgets.get(1189).validate()){
											ChatOptions.getContinueOption().select(true);
										}else
										if(Inventory.getSelectedItem()!=null){
											Method.interactInventory(623, "Use", "Sword handle");
										}else Method.interactInventory(1755, "Use", "Chisel");
									}else if(Widgets.get(1186).validate()){
										ChatOptions.getContinueOption().select(true);
									}else if(!Method.isChatting("Trufitus")){
										if(Inventory.getSelectedItem()!=null){
											Method.npcInteract(740, "Use");
										}else Method.interactInventory(624, "Use", "Notes");
									}
								}else if(Widgets.get(1186).validate()){
									ChatOptions.getContinueOption().select(true);
								}else if(!Method.isChatting("Trufitus")){
									if(Inventory.getSelectedItem()!=null){
										Method.npcInteract(740, "Use");
									}else Method.interactInventory(623, "Use", "Sword");
								}
							}else if(Widgets.get(1186).validate()){
								ChatOptions.getContinueOption().select(true);
							}else if(!Method.isChatting("Trufitus")){
								if(Inventory.getSelectedItem()!=null){
									Method.npcInteract(740, "Use");
								}else Method.interactInventory(611, "Use", "Orb");
							}
						}else Method.findPath(new Tile(2808,3085,0));
					}else CS0();//Get to Trufitus's location
					}else if(!Method.teleporting&& Inventory.getItem(624)!=null){
						Vars.DYNAMICV = false;
						Vars.DYNAMICV2=false;
						Vars.DYNAMICV3 = false;
						crossed = false;
						hasNotes = true;
					}else getToTomb();
				}else if(!Method.teleporting&& Inventory.getItem(611)!=null){
					Vars.DYNAMICV = false;
					Vars.DYNAMICV2=false;
					Vars.DYNAMICV3 = false;
					hasLocater = true;
				}else getToTomb();
			}else if(!Method.teleporting&& Inventory.getItem(623)!=null){
				Vars.DYNAMICV = false;
				Vars.DYNAMICV2=false;
				Vars.DYNAMICV3 = false;
				hasSwordPom = true;
			}else getToTomb();
		
		
		
	}
	
	private void getToTomb() {//Gets to the tomb and searchs the rock for the items.
		if(initTile!=null){
			if(new Tile(initTile.getX()+5,initTile.getY()-23,0).distanceTo()<7){
				if((Vars.STEPX & 0xF) ==14){
					while(Widgets.get(1189).validate()){
						ChatOptions.getContinueOption().select(true);
					}
					if (!Method.isChatting("Rash")) {
						if (Inventory.getSelectedItem() != null) {
							Method.interactO(2235, "Use", "Rocks");
						} else 	Method.interactInventory(609, "Use","Rash's corpse");
					}
					}else
				if(Widgets.get(1186).validate()){
					ChatOptions.getContinueOption().select(true);
				}else if(!Method.isChatting("Rocks")){
					Method.interactO(2235, "Search", "Rocks");
				}
				
			}else {
				Method.state("Walking closer to the strange rocks.");
				new Tile(initTile.getX()+5,initTile.getY()-23,0).clickOnMap();
			}
		  }else if(SceneEntities.getNearest(2235)!=null){
			  initTile = Players.getLocal().getLocation();
		  }else if(Vars.DYNAMICV3){
			  CS6();
		  }else if(new Tile(2809,3085,0).distanceTo()<10){
			  Vars.DYNAMICV3 = true;
		  } else{//Since we've already entered the cave once; we must have read the scrolls.
			  CS0();
		  }
		
	}
	private void CS6() {
		final String opt[] = {"Yes please"};
		if(new Tile(2794,2981,0).contains(Players.getLocal().getCentralPoint())||
				new Tile(2794,2977,0).contains(Players.getLocal().getCentralPoint())){//Two location where we can't climb.
				Method.state("Moving out of area");
				new Tile(2797,2979,0).clickOnMap();//Safe location, away from the loc where we can't climb.
		}else
		if(new Tile(2788,2980,0).canReach()||new Tile(2768,2979,0).canReach()){//If you can reach the top of the hill
			if(new Tile(2763,2988,0).distanceTo()<8){//The locaiton by the entrance to the tomb
				while(Widgets.get(1186).validate()){
					ChatOptions.getContinueOption().select(true);
				}
				
				
				if(readScroll1){
					if(readScroll2){
						if(Widgets.get(468).validate()){
							Method.state("Exiting the scroll screen");
							Players.getLocal().getLocation().clickOnMap();
						}else if(Widgets.get(1186).validate()){
							ChatOptions.getContinueOption().select(true);
						}else if(!Method.findOption(opt)){
						Method.interactO(2234, "Search", "Stacked Rocks");
							}
					}else if(!Method.findOption(opt)){
						if(Widgets.get(468).validate()){
								initTile = null;
								readScroll2 = true;
						}else Method.interactInventory(608, "Read", "Crumpled Scroll");
					}
				}else if(!Method.findOption(opt)){
					if(Widgets.get(220).validate()){
							readScroll1 = true;
					}else Method.interactInventory(607, "Read", "Tattered Scroll");
				}
			}else {
				Method.state("Walking closer to the entrance of the tomb");
				new Tile(2763,2988,0).clickOnMap();//Find path to the tomb.
			}
		}else if(new Tile(2795,2979,0).distanceTo()<8){
			if(Players.getLocal().isIdle() && wait==null ||(wait!=null && !wait.isRunning())){
				Method.interactO(2231, "Climb", "Cliff");
				wait = new Timer(1500);
			
			}
		}else Method.walking(pathToCaimIsles, "Walking to a tomb",false);
		
	}
	private void CS5() {
		final String opt3[] = {"Anything that can"};
		final String opt4[] = {"The spirit said something about","He said something after he","It appeared when I buried"};
		if(showedScroll1){
			if(showedScroll2){
				if(showedBone){
					CS6();
				}else if(new Tile(2808,3085,0).distanceTo()<20){//Trufitus location.
					if(new Tile(2808,3085,0).distanceTo()<10){
						
						while(Widgets.get(1186).validate()){
							ChatOptions.getContinueOption().select(true);
						}
						if (Widgets.get(1184).validate()) {
							if (Widgets.get(1184, 13).getText().contains("If you found")||
									Widgets.get(1184, 13).getText().contains("But I think we must")) {
								Vars.DYNAMICV = false;
								Vars.DYNAMICV2 = false;
								Vars.DYNAMICV3 = false;
								showedBone = true;
							}
							ChatOptions.getContinueOption().select(true);
						} else
						if(!Method.findOption(opt4)){
						if(!Method.isChatting("Trufitus")){
							if(Inventory.getSelectedItem()!=null){
								Method.npcInteract(740, "Use");//Trufitus
							}else Method.interactInventory(604, "Use", "Bone Shard");
						}
						}
					}else Method.findPath(new Tile(2808,3085,0));
				}else CS0();//Get to Trufitus's location
			}else if(new Tile(2808,3085,0).distanceTo()<20){//Trufitus location.
				if(new Tile(2808,3085,0).distanceTo()<10){
					while(Widgets.get(1186).validate()){
						if(Widgets.get(1186,1).getText().contains("Trufitus gives back")){
						showedScroll2  = true;	
						ChatOptions.getContinueOption().select(true);
						}else
						ChatOptions.getContinueOption().select(true);
					}
					if(!Method.findOption(opt3)){
					if(!Method.isChatting("Trufitus")){
						if(Inventory.getSelectedItem()!=null && !Widgets.get(1184).validate()){
							Method.npcInteract(740, "Use");//Trufitus
						}else Method.interactInventory(608, "Use", "Crumpled Scroll");
					}
					}
				}else Method.findPath(new Tile(2808,3085,0));
			}else CS0();//Get to Trufitus's location
		}else if(new Tile(2808,3085,0).distanceTo()<20){//Trufitus location.
			if(new Tile(2808,3085,0).distanceTo()<10){
				while(Widgets.get(1186).validate()){
					if(Widgets.get(1186,1).getText().contains("Trufitus hands the tattered")||Widgets.get(1186,1).getText().contains("It may be possible")){
					showedScroll1  = true;	
					Task.sleep(1200,1300);
					ChatOptions.getContinueOption().select(true);
					}else
					ChatOptions.getContinueOption().select(true);
				}
				if(!Method.isChatting("Trufitus")){
					if(Inventory.getSelectedItem()!=null){
						Method.npcInteract(740, "Use");//Trufitus
						wait = new Timer(4000);
					}else Method.interactInventory(607, "Use", "Tattered Scroll");
				}
				
			}else Method.findPath(new Tile(2808,3085,0));
		}else CS0();//Get to Trufitus's location
		
	}

	private void CS4() {
		SceneObject caveCheck = SceneEntities.getNearest(31130);
		SceneObject cave2Check = SceneEntities.getNearest(31889);
		NPC skele = NPCs.getNearest(5354,502);
		if(initTile==null && initTile2==null && !hasScroll1){//If you're outside(never been in the caves) and you have the items.
				if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(604)!=null){
					hasScroll1 = true;
					hasScroll2  =true;
					hasCorpse = true;
					hasBoneShard = true;
				}
				if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(607)!=null &&!Method.teleporting&&Inventory.getItem(608)!=null&&!Method.teleporting&&Inventory.getItem(610)!=null){
					hasScroll1 = true;
					hasScroll2  =true;
					hasCorpse = true;
				}
					
			
		}
		final String opt[] = {"Yes, I'll wriggle through","Yes, I'll give it a go!"};
		final String opt2[] = {"Yes, I may find something else on the","Yes, I'll carefully move the rocks to see",};
		final String opt3[] = {"The spirit said something about","He said something after he","It appeared when I buried"};
		while(Widgets.get(1186).validate()){
				if( Widgets.get(1186,1).getText().contains("You see nothing here")){
					hasScroll1 = true;
				}
				Task.sleep(400,700);
			ChatOptions.getContinueOption().select(true);
		}
		if(cave2Check!=null || hasCorpse){
			if(initTile2!=null|| hasCorpse){
				//Method.state("Y:"+(initTile2.getLocation().getY() - Players.getLocal().getLocation().getY())+": X:"+(initTile2.getLocation().getX() - Players.getLocal().getLocation().getX()));
				if(hasScroll1){//If you've gathered the first scroll!
					if(hasScroll2){//If you've gathered the second scroll!
						if(hasCorpse){//If we've gathered the corpse!
							if(hasBoneShard){
								if(new Tile(2809,3085,0).distanceTo()<10){
									if(!Method.findOption(opt3)){
									if(!Method.isChatting("Trufitus")){
										if(Inventory.getSelectedItem()!=null){
											Method.npcInteract(740, "Use");
										}else Method.interactInventory(604, "Use", "Bone Shard");
									}
									}
									
								}else Method.findPath(new Tile(2809,3085,0));
							}else
							if(new Tile(2808,3085,0).distanceTo()<20){//Trufitus location.
								if(new Tile(2795,3089,0).distanceTo()<4){
									if(!Method.teleporting&&Inventory.getItem(610)!=null){//Corpse
									 if(!Method.isChatting("Spirit")){
										 if(wait==null||!wait.isRunning())
										Method.interactInventory(610, "Bury", "Corpse");
										wait = new Timer(4000);
									 }	
									
									}else if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(604)!=null){
										hasBoneShard = true;
									}
								}else Method.findPath(new Tile(2795,3089,0));
							}else CS0();//Get to Trufitus's location
							
							
						}else if(new Tile(initTile2.getX()+47, initTile2.getY()+45,0).distanceTo()<8){//Third item
							if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(610)!=null){//The corpse ID, the one we retrieved from the gallows.
								Vars.DYNAMICV = false;
								Vars.DYNAMICV2 = false;
								Vars.DYNAMICV3 = false;
								hasCorpse = true;//We now found the corpse!
							}else
							if(Widgets.get(1186).validate() ||Widgets.get(1189).validate()){
								ChatOptions.getContinueOption().select(true);
							}else if(Method.isInCombat()){
								final int[] monst = {5358,5353,5357,505};
								for(int y: monst){
								Method.basicFightNPC(y);
								}

							}else
							if(!Method.findOption(opt2) && wait==null || (!Method.findOption(opt2)&&wait!=null && !wait.isRunning())){
						
							Method.interactO(2224, "Search", "Gallows");
							wait = new Timer(4000);
							}
						}else {
							Method.state("Walking to gather the last item");
							new Tile(initTile2.getX()+47, initTile2.getY()+45,0).clickOnMap();
						}
						
						
					}else //Below gather the second scroll item.
						if(new Tile(initTile2.getX()+53, initTile2.getY()+2,0).distanceTo()<8){//Second item
						if(Widgets.get(137).validate()){
							if(Widgets.get(137,58).getChild(0).getText().contains("You find nothing in the")){
								hasScroll2 = true;
							}else
						if(!Method.findOption(opt2)){
						Method.interactO(2223, "Search", "Pile of Rocks");
						}
						}else Method.state("Requires updating");
					}else {
						Method.state("Walking closer to the second item");
						new Tile(initTile2.getX()+53, initTile2.getY()+2,0).clickOnMap();
					}
					
				}else//Below gathers the first scroll..
			
				if(new Tile(initTile2.getX()-2, initTile2.getY()+35,0).distanceTo()<4){//First item
					if(Method.isInCombat()){
						Method.basicFightNPC(505);
					}else
					if(!Method.findOption(opt2)){
					Method.interactO(2222, "Search", "Pile of Rocks");
					}
				}else {
					Method.state("Walking closer to the first item");
					new Tile(initTile2.getX()-2, initTile2.getY()+35,0).clickOnMap();
				}
			}else initTile2 = Players.getLocal().getLocation();
		}else
		if(caveCheck!=null){//Below will enter the second part of the cave.
			if(initTile!=null){
				if(new Tile(initTile.getX()-10, initTile.getY()-28,0).distanceTo()<9){
					while(Method.isInCombat()){
						Method.basicFightNPC(skele.getId());
					}
					if(!Method.findOption(opt)){
					Method.interactO(2220, "Search", "Cave-in");
					}
				}else {
					Method.state("Walking closer to a crack in the cave wall");
					new Tile(initTile.getX()-10, initTile.getY()-28,0).clickOnMap();
				}
			}else initTile = Players.getLocal().getLocation();
		}else if(new Tile(2921, 3001, 0).distanceTo() < 10){//<<<This part is outside the cave area.
			while(Widgets.get(1186).validate()){
				ChatOptions.getContinueOption().select(true);
			}
			if (!Method.findOption(opt)) {
				if (SceneEntities.getNearest(2217) != null) {
					Method.interactO(2217, "Look", "Mound of Earth");
				} else if (SceneEntities.getNearest(2219) != null) {
					Method.interactO(2219, "Search", "Mound of Earth");
				}
			}
		}else CS3();
		
	}
	private void CS3() {
		if (new Tile(2921, 3001, 0).distanceTo() < 10) {//The entrance to the cave..
			String t = Widgets.get(137,58).getChild(0).getText();//Text box spot 1 location..
			final String opt[] = {"Yes, I'll give it a go!"};
			while(Widgets.get(1186).validate()){
				ChatOptions.getContinueOption().select(true);
			}
			if (SceneEntities.getNearest(2219) != null ||(t.contains("rope attached!") && SceneEntities.getNearest(2218) != null)) {//The mound ID when we've used the rope on it.
				if(!Method.findOption(opt)){
				Method.interactO(2218, "Search", "Mound of Earth");
				Method.interactO(2219, "Search", "Mound of Earth");
				}
			}else if (SceneEntities.getNearest(2218) != null) {
				if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(954)!=null){//If you have some extra rope in your inventory, use it on the cave.
				CS2();//Use the rope on the Mound.
				}
			} else Method.interactO(2217, "Look", "Mound of Earth");
		} else CS1();
	}
	private void CS2() {
		if (new Tile(2921, 3001, 0).distanceTo() < 10) {
			if (SceneEntities.getNearest(2218) != null) {
				if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(954)!=null){
					if(Inventory.getSelectedItem()!=null){
						Method.interactO(2218, "Use", "Fissure");
					}else Method.interactInventory(954, "Use", "Rope");
				}

			} else if (Inventory.getSelectedItem() != null) {
				if (Players.getLocal().isIdle()) {
					Method.interactO(2217, "Use", "Mound of Earth");
				}
			} else Method.interactInventory(SPADEID, "Use", "Spade");
		} else CS1();
		
	}
	private void CS1() {
		if(new Tile(2921, 3001, 0).distanceTo()<10){
			crossed = false;
			
			if(SceneEntities.getNearest(2218)!=null){
				while(Widgets.get(1186).validate()||Widgets.get(1189).validate()){
					ChatOptions.getContinueOption().select(true);
				}
				if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(594)!=null){//The lit torch..
					if(Inventory.getSelectedItem()!=null){
						Method.interactO(2218, "Use", "Fissure");
					}else Method.interactInventory(594, "Use", "Lit torch");
				}else
				if(!Method.teleporting&&!Method.teleporting&&Inventory.getItem(UNLITTORCHID)!=null){
					Method.interactInventory(UNLITTORCHID, "Light", "Unlit torch");
				}
			}else //Use your spade on the mound of earth...
			if(Inventory.getSelectedItem()!=null){
				if(Players.getLocal().isIdle()){
					Method.interactO(2217, "Use", "Mound of Earth");
				}
			}else Method.interactInventory(SPADEID, "Use", "Spade");
			
			//Getting to the entrance of cave..
		}else if(Vars.DYNAMICV3){
			if(crossed){
				Method.walking(pathToCave, "Walking to a mysterious cave: "+crossed, false);
			}else if(new Tile(2911,3049,0).distanceTo()<18){
				while(Players.getLocal().getAnimation()!=-1){
					Task.sleep(20,40);
				}
				if(new Tile(2910,3049,0).distanceTo()<3){
					hasBelt = false;
					crossed = true;
					
				}else Method.interactO(2332, "Cross", "Log");
			}else Method.walking(pathToCave, "Walking to a mysterious cave: "+crossed, false);
		}else if(new Tile(2808,3086,0).distanceTo()<10){
			Vars.DYNAMICV3 = true;
		}else CS0();
		
	}
	private void CS0() {
		SceneObject plank = SceneEntities.getNearest(2082);
		NPC officer = NPCs.getNearest(376,377,378);
		final String[] t= {"Yes"};
		final String[] moselOption= {"Yes, I'm sure and I'll","I'll go to see","What can we do?","Who is she?","Why do I need to run?"};
		final String[] trufitusOptions= {"Yes, I will seriously look","I am going to search","Tell me more","Do you know anything more about","Mosol Rei said something"};
		while(Settings.get(1045)==5){
			Method.state("Waiting");
			Task.sleep(50);
		}
		if(hasBelt){
			if(new Tile(2808,3085,0).distanceTo()<10){
				Vars.DYNAMICV2 = false;
				while(Widgets.get(1189).validate()){ChatOptions.getContinueOption().select(true);}
				if(!Method.findOption(trufitusOptions)){
					if (!Method.isChatting("Trufitus")) {
						if (Inventory.getSelectedItem() != null) {
							Method.npcInteract(740, "Use");
						} else
							Method.interactInventory(BELTID, "Use",
									"Wampum belt");

					}
				}
			}else if(Vars.DYNAMICV2){
				Method.walking(pathToMoselRei, "Walking back to Trufitus", true);
			}else if(new Tile(2882, 2951, 0).distanceTo()<8){
				Vars.DYNAMICV2 = true;
			}else Method.state("Do not start this quest with the belt in your inventory");
		}else if(!Method.teleporting&&Inventory.getCount(BELTID)>0){
			hasBelt = true;
		}else
		if(new Tile(2882, 2951, 0).distanceTo()<8){//Tile by Mosel Rei
			while(Widgets.get(1186).validate()){
				ChatOptions.getContinueOption().select(true);
				Task.sleep(50);
			}
			if(!Method.findOption(moselOption)){
				Vars.DYNAMICV2 = false;
				if(!Method.isChatting("Mosel Rei")){
					Method.speakTo(500, "Mosel Rei");
				}
			}
		}else if(Vars.DYNAMICV2){
			final Tile gate =new Tile(2812,3182,0);
			if(gate.distanceTo()<11 && !gate.canReach() ){
				Method.interactO(24369, "Open", "Gate");//The gate ID
			}else
		        Method.walking(pathToMoselRei, "Walking to Mosel Rei", false);
		}else if(new Tile(2956,3147,0).distanceTo()<3){//Tile of arrival to Musa Point
			Method.teleporting = false;
			Vars.DYNAMICV2 = true;
		}else if(plank!=null &&plank.getLocation().distanceTo()<4){
			if(plank.isOnScreen())
				Method.interactO(2082, "Cross", "Plank");
				Task.sleep(50);
		}else if(new Tile(3028,3217, 0).distanceTo()<20){//Tile of arrival to Port Sarim Docking location
			if(new Tile(3028,3217, 0).distanceTo()<10){
			if(!Method.findOption(t)){
				if(!Method.isChatting("Officer")){
					if(!Players.getLocal().isMoving())
					Method.speakTo(officer.getId(), "Officer");
				}
			}
			}else Method.findPath(new Tile(3028,3217, 0));
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPort, "Walking to Port Sarim", false);
		}else if(Method.isPortSarimLodeAct()){//If port sarim tele is active, use it instead.
			if(Vars.PORTSARIMLODE.distanceTo()<6){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(Vars.PORTSARIMTELEPORT);
		}else if(Method.DraynorLodeIsActive()){//if draynor teleport is available, is it instead.
			if(Vars.DRAYNORLODE.distanceTo()<6){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(Vars.DRAYNORTELEPORT);
			
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<7){//if not..
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}
	

	public boolean activate() {
		return DeltaQuester.scriptToStart==23;
	}

}
*/