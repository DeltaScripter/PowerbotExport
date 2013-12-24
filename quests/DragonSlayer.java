/*package Method;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

public class DragonSlayer extends Node{

	public DragonSlayer(MethodContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	public final Tile[] pathToGuild = new Tile[] { 
			new Tile(3213, 3377, 0), new Tile(3208, 3376, 0), new Tile(3203, 3375, 0), 
			new Tile(3198, 3375, 0), new Tile(3194, 3372, 0), new Tile(3192, 3367, 0), 
			new Tile(3190, 3365, 0), new Tile(3190, 3364, 0) };
	
	public final Tile[] pathToOziach = new Tile[] { 
			new Tile(3214, 3375, 0), new Tile(3211, 3379, 0), new Tile(3210, 3384, 0), 
			new Tile(3210, 3389, 0), new Tile(3211, 3394, 0), new Tile(3213, 3399, 0), 
			new Tile(3212, 3404, 0), new Tile(3211, 3409, 0), new Tile(3211, 3414, 0), 
			new Tile(3211, 3419, 0), new Tile(3208, 3424, 0), new Tile(3204, 3427, 0), 
			new Tile(3199, 3429, 0), new Tile(3194, 3429, 0), new Tile(3189, 3429, 0), 
			new Tile(3184, 3429, 0), new Tile(3179, 3430, 0), new Tile(3174, 3430, 0), 
			new Tile(3169, 3430, 0), new Tile(3165, 3427, 0), new Tile(3161, 3424, 0), 
			new Tile(3157, 3421, 0), new Tile(3152, 3420, 0), new Tile(3147, 3420, 0), 
			new Tile(3142, 3418, 0), new Tile(3137, 3418, 0), new Tile(3132, 3418, 0), 
			new Tile(3127, 3418, 0), new Tile(3122, 3418, 0), new Tile(3117, 3419, 0), 
			new Tile(3112, 3421, 0), new Tile(3107, 3421, 0), new Tile(3102, 3421, 0), 
			new Tile(3098, 3424, 0), new Tile(3100, 3429, 0), new Tile(3101, 3434, 0), 
			new Tile(3097, 3437, 0), new Tile(3093, 3440, 0), new Tile(3091, 3445, 0), 
			new Tile(3090, 3450, 0), new Tile(3089, 3455, 0), new Tile(3088, 3460, 0), 
			new Tile(3087, 3465, 0), new Tile(3082, 3467, 0), new Tile(3081, 3472, 0), 
			new Tile(3080, 3477, 0), new Tile(3080, 3482, 0), new Tile(3083, 3486, 0), 
			new Tile(3084, 3491, 0), new Tile(3083, 3496, 0), new Tile(3080, 3500, 0), 
			new Tile(3075, 3502, 0), new Tile(3070, 3503, 0), new Tile(3065, 3505, 0), 
			new Tile(3066, 3510, 0), new Tile(3067, 3513, 0) };
	
	public final Tile[] pathToCastle = new Tile[] {//Lumbridge castle
			new Tile(3233, 3222, 0), new Tile(3229, 3219, 0), new Tile(3224, 3218, 0), 
			new Tile(3219, 3218, 0), new Tile(3215, 3221, 0), new Tile(3213, 3226, 0), 
			new Tile(3208, 3228, 0), new Tile(3203, 3230, 0), new Tile(3204, 3228, 0) };
	
	public final Tile[] pathToMaze = new Tile[] {//path to Melzar's maze
			new Tile(2966, 3404, 0), new Tile(2964, 3399, 0), new Tile(2964, 3394, 0), 
			new Tile(2965, 3389, 0), new Tile(2964, 3384, 0), new Tile(2967, 3380, 0), 
			new Tile(2972, 3379, 0), new Tile(2977, 3378, 0), new Tile(2981, 3375, 0), 
			new Tile(2986, 3373, 0), new Tile(2990, 3370, 0), new Tile(2994, 3367, 0), 
			new Tile(2999, 3365, 0), new Tile(3002, 3361, 0), new Tile(3005, 3357, 0), 
			new Tile(3006, 3352, 0), new Tile(3008, 3347, 0), new Tile(3008, 3342, 0), 
			new Tile(3007, 3337, 0), new Tile(3007, 3332, 0), new Tile(3007, 3327, 0), 
			new Tile(3006, 3322, 0), new Tile(3005, 3317, 0), new Tile(3005, 3312, 0), 
			new Tile(3005, 3307, 0), new Tile(3005, 3302, 0), new Tile(3005, 3297, 0), 
			new Tile(3005, 3292, 0), new Tile(3006, 3287, 0), new Tile(3007, 3282, 0), 
			new Tile(3006, 3279, 0), new Tile(3003, 3275, 0), new Tile(2998, 3273, 0), 
			new Tile(2993, 3271, 0), new Tile(2988, 3269, 0), new Tile(2983, 3268, 0), 
			new Tile(2978, 3267, 0), new Tile(3010, 3214, 0), new Tile(3008, 3219, 0), 
			new Tile(3004, 3222, 0), new Tile(3003, 3227, 0), new Tile(3005, 3232, 0), 
			new Tile(3005, 3237, 0), new Tile(3003, 3242, 0), new Tile(3003, 3247, 0), 
			new Tile(3003, 3252, 0), new Tile(3001, 3257, 0), new Tile(2997, 3260, 0), 
			new Tile(2993, 3263, 0), new Tile(2988, 3264, 0), new Tile(2983, 3266, 0), 
			new Tile(2978, 3267, 0), new Tile(2975, 3263, 0), new Tile(2971, 3260, 0), 
			new Tile(2966, 3258, 0), new Tile(2962, 3255, 0), new Tile(2959, 3251, 0), 
			new Tile(2957, 3246, 0), new Tile(2953, 3243, 0), new Tile(2948, 3241, 0), 
			new Tile(2944, 3244, 0), new Tile(2941, 3248, 0) };
	
	public final Tile[] pathToOracle = new Tile[] { 
			new Tile(2965, 3402, 0), new Tile(2965, 3407, 0), new Tile(2965, 3412, 0), 
			new Tile(2969, 3415, 0), new Tile(2974, 3415, 0), new Tile(2979, 3417, 0), 
			new Tile(2983, 3420, 0), new Tile(2986, 3424, 0), new Tile(2989, 3428, 0), 
			new Tile(2994, 3430, 0), new Tile(2999, 3432, 0), new Tile(3004, 3431, 0), 
			new Tile(3009, 3432, 0), new Tile(3013, 3435, 0), new Tile(3018, 3436, 0), 
			new Tile(3023, 3438, 0), new Tile(3027, 3441, 0), new Tile(3030, 3445, 0), 
			new Tile(3033, 3449, 0), new Tile(3034, 3454, 0), new Tile(3037, 3458, 0), 
			new Tile(3040, 3462, 0), new Tile(3042, 3467, 0), new Tile(3039, 3471, 0), 
			new Tile(3034, 3471, 0), new Tile(3029, 3472, 0), new Tile(3024, 3474, 0), 
			new Tile(3020, 3471, 0), new Tile(3015, 3472, 0), new Tile(3012, 3477, 0), 
			new Tile(3008, 3480, 0), new Tile(3007, 3485, 0), new Tile(3010, 3489, 0), 
			new Tile(3013, 3493, 0), new Tile(3014, 3498, 0), new Tile(3012, 3503, 0), 
			new Tile(3011, 3503, 0) };
	
	public final Tile[] pathToDwarvenMine = new Tile[] { 
			new Tile(2966, 3402, 0), new Tile(2965, 3407, 0), new Tile(2967, 3412, 0), 
			new Tile(2972, 3413, 0), new Tile(2976, 3416, 0), new Tile(2981, 3417, 0), 
			new Tile(2985, 3420, 0), new Tile(2987, 3425, 0), new Tile(2990, 3429, 0), 
			new Tile(2995, 3430, 0), new Tile(3000, 3431, 0), new Tile(3005, 3431, 0), 
			new Tile(3010, 3432, 0), new Tile(3013, 3436, 0), new Tile(3015, 3441, 0), 
			new Tile(3017, 3446, 0), new Tile(3017, 3449, 0) };
	
	public final Tile[] pathToGoblinVillage = new Tile[] { 
			new Tile(2966, 3403, 0), new Tile(2966, 3408, 0), new Tile(2966, 3413, 0), 
			new Tile(2961, 3415, 0), new Tile(2956, 3417, 0), new Tile(2953, 3421, 0), 
			new Tile(2949, 3424, 0), new Tile(2947, 3429, 0), new Tile(2946, 3434, 0), 
			new Tile(2945, 3439, 0), new Tile(2945, 3444, 0), new Tile(2945, 3449, 0), 
			new Tile(2946, 3454, 0), new Tile(2948, 3459, 0), new Tile(2948, 3464, 0), 
			new Tile(2951, 3468, 0), new Tile(2956, 3470, 0), new Tile(2957, 3475, 0), 
			new Tile(2957, 3480, 0), new Tile(2955, 3485, 0), new Tile(2955, 3490, 0), 
			new Tile(2955, 3495, 0), new Tile(2956, 3500, 0), new Tile(2956, 3505, 0), 
			new Tile(2957, 3510, 0), new Tile(2956, 3513, 0) };
	
	public final Tile[] pathToJail = new Tile[] { 
			new Tile(2966, 3404, 0), new Tile(2965, 3399, 0), new Tile(2964, 3394, 0), 
			new Tile(2964, 3389, 0), new Tile(2965, 3384, 0), new Tile(2968, 3380, 0), 
			new Tile(2973, 3378, 0), new Tile(2978, 3378, 0), new Tile(2982, 3375, 0), 
			new Tile(2986, 3372, 0), new Tile(2991, 3369, 0), new Tile(2995, 3366, 0), 
			new Tile(3000, 3364, 0), new Tile(3004, 3361, 0), new Tile(3006, 3356, 0), 
			new Tile(3007, 3351, 0), new Tile(3007, 3346, 0), new Tile(3007, 3341, 0), 
			new Tile(3007, 3336, 0), new Tile(3007, 3331, 0), new Tile(3006, 3326, 0), 
			new Tile(3006, 3321, 0), new Tile(3007, 3316, 0), new Tile(3007, 3311, 0), 
			new Tile(3007, 3306, 0), new Tile(3007, 3301, 0), new Tile(3007, 3296, 0), 
			new Tile(3007, 3291, 0), new Tile(3007, 3286, 0), new Tile(3008, 3281, 0), 
			new Tile(3011, 3277, 0), new Tile(3014, 3273, 0), new Tile(3016, 3268, 0), 
			new Tile(3017, 3263, 0), new Tile(3017, 3258, 0), new Tile(3017, 3253, 0), 
			new Tile(3017, 3248, 0), new Tile(3020, 3244, 0), new Tile(3021, 3239, 0), 
			new Tile(3020, 3234, 0), new Tile(3020, 3229, 0), new Tile(3019, 3224, 0), 
			new Tile(3018, 3219, 0), new Tile(3016, 3214, 0), new Tile(3010, 3215, 0), 
			new Tile(3015, 3214, 0), new Tile(3017, 3209, 0), new Tile(3017, 3204, 0), 
			new Tile(3014, 3200, 0), new Tile(3010, 3198, 0) };
	
	public final Tile[] pathToNed = new Tile[] {
			new Tile(3232, 3220, 0), new Tile(3232, 3225, 0), new Tile(3229, 3229, 0), 
			new Tile(3225, 3232, 0), new Tile(3223, 3237, 0), new Tile(3221, 3242, 0), 
			new Tile(3218, 3246, 0), new Tile(3213, 3247, 0), new Tile(3208, 3247, 0), 
			new Tile(3203, 3247, 0), new Tile(3198, 3246, 0), new Tile(3193, 3246, 0), 
			new Tile(3188, 3244, 0), new Tile(3183, 3246, 0), new Tile(3178, 3246, 0), 
			new Tile(3176, 3241, 0), new Tile(3172, 3238, 0), new Tile(3167, 3238, 0), 
			new Tile(3162, 3239, 0), new Tile(3159, 3235, 0), new Tile(3154, 3234, 0), 
			new Tile(3149, 3236, 0), new Tile(3146, 3232, 0), new Tile(3142, 3229, 0), 
			new Tile(3137, 3228, 0), new Tile(3132, 3228, 0), new Tile(3127, 3226, 0), 
			new Tile(3123, 3223, 0), new Tile(3118, 3221, 0), new Tile(3114, 3224, 0), 
			new Tile(3110, 3227, 0), new Tile(3108, 3232, 0), new Tile(3107, 3237, 0), 
			new Tile(3106, 3242, 0), new Tile(3104, 3247, 0), new Tile(3104, 3252, 0), 
			new Tile(3103, 3257, 0), new Tile(3104, 3299, 0), new Tile(3108, 3296, 0), 
			new Tile(3108, 3291, 0), new Tile(3108, 3286, 0), new Tile(3107, 3281, 0), 
			new Tile(3104, 3277, 0), new Tile(3104, 3272, 0), new Tile(3104, 3267, 0), 
			new Tile(3103, 3262, 0), new Tile(3104, 3257, 0), new Tile(3103, 3257, 0) };
	
	public final Tile[] pathToDocks = new Tile[] { 
			new Tile(2966, 3404, 0), new Tile(2963, 3400, 0), new Tile(2963, 3395, 0), 
			new Tile(2963, 3390, 0), new Tile(2964, 3385, 0), new Tile(2968, 3382, 0), 
			new Tile(2972, 3379, 0), new Tile(2977, 3377, 0), new Tile(2981, 3374, 0), 
			new Tile(2986, 3372, 0), new Tile(2990, 3369, 0), new Tile(2994, 3366, 0), 
			new Tile(2999, 3364, 0), new Tile(3002, 3360, 0), new Tile(3005, 3356, 0), 
			new Tile(3006, 3351, 0), new Tile(3007, 3346, 0), new Tile(3008, 3341, 0), 
			new Tile(3007, 3336, 0), new Tile(3007, 3331, 0), new Tile(3007, 3326, 0), 
			new Tile(3007, 3321, 0), new Tile(3007, 3316, 0), new Tile(3007, 3311, 0), 
			new Tile(3006, 3306, 0), new Tile(3006, 3301, 0), new Tile(3005, 3296, 0), 
			new Tile(3005, 3291, 0), new Tile(3006, 3286, 0), new Tile(3009, 3282, 0), 
			new Tile(3010, 3277, 0), new Tile(3014, 3274, 0), new Tile(3018, 3271, 0), 
			new Tile(3018, 3266, 0), new Tile(3018, 3261, 0), new Tile(3018, 3257, 0), 
			new Tile(3018, 3252, 0), new Tile(3019, 3247, 0), new Tile(3020, 3242, 0), 
			new Tile(3025, 3241, 0), new Tile(3028, 3237, 0), new Tile(3028, 3232, 0), 
			new Tile(3028, 3227, 0), new Tile(3027, 3222, 0), new Tile(3027, 3217, 0), 
			new Tile(3026, 3212, 0), new Tile(3026, 3207, 0), new Tile(3010, 3216, 0), 
			new Tile(3015, 3214, 0), new Tile(3016, 3209, 0), new Tile(3021, 3207, 0), 
			new Tile(3026, 3206, 0), new Tile(3030, 3203, 0), new Tile(3035, 3201, 0), 
			new Tile(3040, 3201, 0), new Tile(3043, 3203, 0) };
	
	
	public final int bankItems[] = {1542,1540};
	
	public final int bankItems2[] = {1542,1543,1544,1545,1546,1547,1548};//Contents: melzar's key, red key, orange key,etc
	public final int bankItems3[] = {1791,1907,13431,950};
	public final int bankItems4[] = {1539,960,960,960};//Steel nails and planks
	public final int bankItems5[] = {1535,1536,1537,1538,1540};//Maps and drag shield
	public final int bankItems6[] = {11279};//Elvargs head
	//Malzar map piece 1535
	//Lazar's map piece 1537
	//Completed map is 1538
	public Tile initTile;
	
	public boolean init = true;
	public boolean hasStuff = false;
	public boolean hasShieldEquip = false;
	public boolean hasMalzarMap = false;
	public boolean hasLazarMap = false;
	public boolean hasWormMap = false;
	
	public boolean hasRedKey = false;
	public boolean hasOrangeKey = false;
	public boolean hasYellowKey = false;
	public boolean hasBlueKey = false;
	public boolean hasPurpleKey  =false;
	public boolean hasGreenKey = false;
	
	
	public int itemsArray[] = {0,0,0,0,0,0,0};//contains the states of items needing to be purchased.
	public int itemDAmount[] = {1,1,1,1,1,3,90};
	public int itemDID[] = {1540,1791,1907,13431,950,961,1539};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {1500,1500,1000,1500,1500,4000,100};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Anti-dragon shield","Bowl (unfired)","Wizard's mind bomb","Crayfish cage","Silk","Plank","Steel nails"};//contains the names of the items needing to be purchased.

	Method Method = new Method(ctx);
	public boolean activate() {
		return DeltaQuester.scriptToStart==32;
	}

	public void execute() {
		DeltaQuester.numSteps = 16;
		Method.resetTeleporting();
		Method.foodSupport();
		//if(Method.useBank && DeltaQuester.GEFeature){
		//	Method.useBank(bankItems, 1, 90, 1);
		//}else
		//if (DeltaQuester.GEFeature ) {
		//	Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		//}else if(Method.useBank && DeltaQuester.GEFeature){
		//	Method.useBank(bankItems, 1, 90, 90);
		//}else
			if((ctx.settings.get(2268)&0x1F) == 10){
			DeltaQuester.progress = 16;
			Method.state("The Dragon Slayer quest has been completed.");
			ctx.game.sleep(2000);
			DeltaQuester.e = true;
		}else
		if(init){
			init();//Determines what part of the quest the player is on(map pieces)
		}else if((ctx.settings.get(2268)&0x1F) == 9){
			DeltaQuester.progress = 15;
			cs1();//Speak to Oziach
		}else
		if((ctx.settings.get(2268)&0x3F) == 2||(ctx.settings.get(2268)&0x3F) == 3||(ctx.settings.get(2268)&0x7) == 6||(ctx.settings.get(2268)&0x7) == 7||(ctx.settings.get(2268)&0x1F) == 8){
			if((ctx.settings.get(2268)&0x1F) == 8){
				DeltaQuester.progress = 14;
				cs12();
			}else
			if((ctx.settings.get(2268)&0x7) == 7){
				DeltaQuester.progress = 13;
				cs11();//Set sail for Crandor
			}else if((ctx.settings.get(2269)&0xFFFFF)==0 || (ctx.settings.get(2269)&0x7)==4){
			//if((ctx.settings.get(3077)&0x1F)==16){
				
				if(hasMalzarMap){
					if(hasLazarMap){
						if(hasWormMap){
							
							if((ctx.settings.get(2269)>>21&0x1)==1){//After speaking to Ned
								if((ctx.settings.get(2268)&0x7) == 6){
									DeltaQuester.progress = 12;
									cs10();
								}else
								if((ctx.settings.get(2268)&0x3F) == 3){
									DeltaQuester.progress = 11;
									cs9();//Repair the ship
								}else {
									DeltaQuester.progress = 10;
									cs8();//Buy the ship in Port Sarim
								}
							}else{
								DeltaQuester.progress = 9;
							cs7();//Speak to Ned about him being captain
							}
						}else
						if((ctx.settings.get(2269)>>23&0x1)==1){//Setting to determine if we spoke to the goblins in the village yet
							DeltaQuester.progress = 8;
							cs6();//Obtain Wormmap from the goblin in the jail
						}else{
							DeltaQuester.progress = 7;
							cs5();//Speak to the goblins in the village
						}
					}else{
						DeltaQuester.progress = 6;
					cs4();//Obtains Lazar's map piece
					}
				}else{
					DeltaQuester.progress = 5;
					cs3();//Obtains Malzar's mpa piece
				}
			//}else {
			//	DeltaQuester.progress = 4;
			//	cs2();//Speak to Duke Horacio and gather the shield
			//}
		}else{
			DeltaQuester.progress = 3;
			Method.useBank = true;//Resets variable so we can use the bank again
		cs0();//Speak to the Guildmaster and gather more information
		}
		}else if(Method.useBank){
			Method.useBank(bankItems, 1,1,90);
		}else
		if((ctx.settings.get(2268)&0x3F) == 1){
		DeltaQuester.progress = 2;
		cs1();//Speak to Oziach about obtaining a Rune platebody
		}else
		if((ctx.settings.get(2268)&0x3F) == 0){
		DeltaQuester.progress = 1;
		cs0();//Begin the quest by speaking to the Guildmaster in Varrok
		}
		
	}

	private void cs12() {//742 Elvarg ID
		Method.skipPics();
		if(!Method.isChatting("People"))
		if(new Tile(2855,9636,0).canReach()){
			Vars.DYNAMICV = false;
			if(Players.getLocal().getInteracting()!=null){
				Method.basicFightNPC(742);
			}else Method.npcInteract(742, "Attack");
		}else
		if(new Tile(2845,9636,0).distanceTo()<4){
			Method.interactO(25161, "Climb", "Rocks");
		}else if(SceneEntities.getNearest(31130)!=null){
			new Tile(2845,9636,0).clickOnMap();
		}else
		if(new Tile(2835,3258,0).distanceTo()<4){
			Method.interactO(25154, "Enter", "Hole");
		}else
		if(new Tile(2855,3239,0).canReach()){
			Method.findPath(new Tile(2835,3258,0));
		}else if((ctx.settings.get(2269)>>10&0x1)==1){
			cs11();//Set sail again
		}else cs9();//Repair the ship
		
	}

	private void cs11() {
		final String opt[] = {"Yes, let's"};
		while(ctx.settings.get(1114)==1){
			Method.isChatting("Cutscene");
		}
		if(Method.useBank){
			Method.useBank(bankItems5, 23, 1, 90);
		}else if(Bank.isOpen()){
			Bank.close();
		}else
		if(!Method.teleporting && Inventory.getItem(1535)!=null){
			Method.combineItems(1535, 1536);
		}else if(hasShieldEquip){
			
			if(new Tile(3047,3207,1).canReach()){
				if(!Method.findOption(opt))
					if(!Method.isChatting("Ned")){
						Method.speakTo(6082, "Ned");
					}
			}else
			if(new Tile(3047,3204,0).distanceTo()<7){
				Method.interactO(2593, "Cross", "Gangplank");
			}else cs8();//Get the player to the docks
			
		}else if(Equipment.getItem(1540)!=null){
			hasShieldEquip  =true;
		}else Method.interactInventory(1540, "Wear", "Dragon Shield");
		
	}

	private void cs10() {
		if(!Method.teleporting && (Inventory.getItem(1540)==null&&Inventory.getItem(1538)==null)){
			Method.useBank = true;
		}
		if(Method.useBank){
			Method.useBank(bankItems5, 22, 1, 90);
		}else if(Bank.isOpen()){
			Bank.close();
		}else
		if(!Method.teleporting && Inventory.getItem(1535)!=null){
			Method.combineItems(1535, 1536);
		}else if(hasShieldEquip){
			cs7();//Speak to Ned
		}else if(Equipment.getItem(1540)!=null){
			hasShieldEquip  =true;
		}else Method.interactInventory(1540, "Wear", "Dragon Shield");
		
	}

	private void cs9() {//Repair the ship
		if(Method.useBank && !new Tile(3048,3208,0).canReach()){
			Method.useBank(bankItems4, 2, 90, 0);
		}else
		if(!hasStuff){
			if(!Method.teleporting && Inventory.getItem(960)!=null && Inventory.getItem(1539)!=null){
				hasStuff =true;
			}else Method.useBank = true;
		}else
		if(new Tile(3048,3208,0).canReach()){
			Vars.DYNAMICV = false;
			Task.sleep(1200);
			Method.useBank = true;
			Method.interactO(25036, "Repair", "Hole");
		}else
		if(new Tile(3047,3207,1).canReach()){
			Method.interactO(2590, "Climb", "Ladder");
		}else
		if(new Tile(3047,3204,0).distanceTo()<7){
			Method.interactO(2593, "Cross", "Gangplank");
		}else cs8();//Get the player to the docks
		
	}

	private void cs8() {//Buy the ship
		final String opt[] = {"Yep, sounds good","I'd like to buy"};
		
		if(!Method.teleporting && Inventory.getItem(960)==null && Inventory.getItem(1539)==null&&(ctx.settings.get(2268)&0x7) != 7
				&&(ctx.settings.get(2268)&0x1F) != 8){
			Method.useBank = true;
		}
		if(Method.useBank&&(ctx.settings.get(2268)&0x7) != 7&&(ctx.settings.get(2268)&0x1F) != 8){
			Method.useBank(bankItems4, 1, 90, 0);
		}else
		if(new Tile(3043, 3203, 0).distanceTo()<6){//Location at docks
			
			if(!Method.findOption(opt))
				if(!Method.isChatting("Klarense")){
					Method.speakTo(744, "Klarence");
				}
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDocks, "Walking to the docks in Port Sarim", false);
		}else if(Vars.PORTSARIMLODE.distanceTo()<10|| Vars.FALADORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(Vars.PORTSARIMTELEPORT);
		}else Method.teleportTo(Vars.FALADORETELEPORT);
		
	}

	private void cs7() {//Speaks to Ned
		final String opt[] = {"Will you take me to","You're a sailor? Could","Talk about something"};
		
		if(new Tile(3103, 3257, 0).distanceTo()<7){
			if(new Tile(3100,3257,0).canReach()){
				Vars.DYNAMICV  =false;
				if(!Method.findOption(opt))
					if(!Method.isChatting("Ned")){
						Method.speakTo(918, "Ned");
					}
			}else Method.interactO(1239, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToNed, "Walking to Ned", false);
		}else if(Vars.DRAYNORLODE.distanceTo()<10 || Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(Vars.DRAYNORTELEPORT);
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}

	private void cs6() {//Speaks to the goblin in the jail
		final String opt[] = {"Alright then, ","I suppose I could pay you","I believe you've got a"};
		
		 if(!Method.teleporting&& Inventory.getItem(1536)!=null){
				hasWormMap = true;
			}else
		if(new Tile(3012,3189,0).canReach() &&new Tile(3012,3189,0).distanceTo()<5){
			Vars.DYNAMICV = false;
			Method.skipPics();
			if(!Method.findOption(opt))
				if(!Method.isChatting("Goblin")){
					Method.speakTo(745, "Goblin");
				}
		}else
		if(new Tile(3010, 3198, 0).distanceTo()<4){
			if(new Tile(3012,3189,0).canReach()){
				new Tile(3012,3189,0).clickOnMap();
			}else Method.interactO(40108, "Open", "Door");
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToJail, "Walking to Port Sarim jail", false);
		}else if(Vars.FALADORLODE.distanceTo()<10 || Vars.PORTSARIMLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(Vars.PORTSARIMTELEPORT);
		}else Method.teleportTo(Vars.FALADORETELEPORT);
		
		
	}

	private void cs5() {
		final String opt[] = {"I've heard that one of your"};
		if(new Tile(2956, 3513, 0) .distanceTo()<7){
			if(new Tile(2957,3514,0).canReach()){
				Vars.DYNAMICV = false;
				if(!Method.findOption(opt))
					if(!Method.isChatting("Goblin")){
						Method.speakTo(4493, "Goblin");
					}
			}else Method.interactO(77970, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToGoblinVillage, "Walking to the goblin generals", false);
		}else if(Vars.FALADORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.FALADORETELEPORT);
		
	}

	private void cs4() {//Obtains Lazar's map piece
		final String opt[] = {"I seek a piece"};
		
		if(!Method.teleporting && ((ctx.settings.get(2269)>>17&0x1)!=1 || (ctx.settings.get(2269)>>18&0x1)!=1||(ctx.settings.get(2269)>>19&0x1)!=1||(ctx.settings.get(2269)>>20&0x1)!=1)
				&& (Inventory.getItem(1791)==null&&Inventory.getItem(1907)==null && Inventory.getItem(13431)==null&&Inventory.getItem(950)==null)){
			Method.useBank = true;
		}
		if(Method.useBank){
			Method.useBank(bankItems3, 5,1,90);
		}else if((ctx.settings.get(2269)&0x7)==4){//After speaking to the Oracle
			 if(!Method.teleporting&& Inventory.getItem(1537)!=null){
				hasLazarMap = true;
			}else
			if(SceneEntities.getNearest(25115)!=null){
				if(new Tile(3053,9841,0).canReach()){
					if(new Tile(3055,9841,0).distanceTo()<5){
						Vars.DYNAMICV = false;
						Method.skipPics();
						if(!Widgets.get(1186).validate()){
						Method.interactO(2587,"Open", "Chest");
						Method.interactO(2588,"Search", "Chest");
						}
					}
				}else
				if (new Tile(3049,9840,0).distanceTo() < 7) {
					if((ctx.settings.get(2269)>>18&0x1)==1){
						if((ctx.settings.get(2269)>>20&0x1)==1){
							if((ctx.settings.get(2269)>>19&0x1)==1){
								if((ctx.settings.get(2269)>>17&0x1)==1){
									Method.interactO(25115, "Open", "Magic door");
								}else Method.useItemOn(950,25115, "Door");
							}else Method.useItemOn(13431,25115, "Door");
						}else Method.useItemOn(1907,25115, "Door");
					}else Method.useItemOn(1791,25115, "Door");
					
				} else Method.findPath(new Tile(3049,9840,0));
			}
			else if(new Tile(3017, 3449, 0).distanceTo()<7){//Location outside Dwarven Mines
				if(!Method.isChatting("Dwarf"))
				Method.interactO(30942, "Climb-down", "Ladder");
			}else if(Vars.DYNAMICV){
				Method.walking(pathToDwarvenMine, "Walking to the Dwarven Mines", false);
			}else if(Vars.FALADORLODE.distanceTo()<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(Vars.FALADORETELEPORT);
			
			
			
		}else if(new Tile(3011, 3503, 0).distanceTo()<5){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
			if(!Method.isChatting("Oracle")){
				Method.speakTo(746, "Oracle");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOracle, "Walking to the Oracle", false);
		}else if(Vars.FALADORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.FALADORETELEPORT);
		
	}

	private void init() {//Determines what part of the quest 
		if(new Tile(3269, 3167, 0).distanceTo()<6){
			if(Bank.isOpen()){
				if(Bank.getItem(1535)!=null || Inventory.getItem(1535)!=null){
					hasMalzarMap = true;
				}
				if(Bank.getItem(1537)!=null || Inventory.getItem(1537)!=null){
					hasLazarMap = true;
				}
				if(Bank.getItem(1536)!=null || Inventory.getItem(1536)!=null){
					hasWormMap = true;
				}
				if(Bank.getItem(1538)!=null || Inventory.getItem(1538)!=null){
					hasWormMap = true;
					hasLazarMap = true;
					hasMalzarMap = true;
				}
				init = false;
			}else Bank.open();
		}else if(Vars.DYNAMICV3){
			Method.walking(Paths.pathToBank2, "Walking to Alkharid bank", false);
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV3 = true;
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}

	private void cs3() {//Obtains Malzar's map piece
		NPC smallrat = NPCs.getNearest(6088);
		NPC ghost = NPCs.getNearest(6094);
		NPC skeleton = NPCs.getNearest(6091);
		NPC zombie = NPCs.getNearest(6099);
		NPC melzar = NPCs.getNearest(753);
		NPC demon = NPCs.getNearest(6101);
		GroundItem redkey = GroundItems.getNearest(1543);
		GroundItem orangekey = GroundItems.getNearest(1544);
		GroundItem yellowkey = GroundItems.getNearest(1545);
		GroundItem bluekey = GroundItems.getNearest(1546);
		GroundItem purplekey = GroundItems.getNearest(1547);
		GroundItem greenkey = GroundItems.getNearest(1548);
		
		if(Method.useBank){
			Method.useBank(bankItems2, 20,1,90);
		}else if(!Method.teleporting && Players.getLocal().getInteracting()==null && Inventory.getItem(526)!=null){
			Method.interactInventory(526, "Drop", "Bones");
		}else if(!Method.teleporting&& Inventory.getItem(1535)!=null){
			hasMalzarMap = true;
		}else if(new Tile(2936,9656,0).canReach()){
			Vars.DYNAMICV = false;
			Method.skipPics();
			Method.interactO(2603, "Open", "Chest");
			Method.interactO(2604, "Search", "Chest");
		}else if(new Tile(2929,9652,0).canReach()){
			
			if(hasGreenKey){
				if(new Tile(2936,9655,0).distanceTo()<6){
				Method.interactO(2601, "Open", "Green door");
				}else new Tile(2936,9655,0).clickOnMap();
			}else
			if(!Method.teleporting && Inventory.getItem(1548)!=null){
				hasGreenKey = true;
			}else
			if(greenkey!=null){
				Method.interactG(greenkey.getId(), "Take", "Green key");
			}else
			if(Players.getLocal().getInteracting()!=null){
				Method.basicFightNPC(demon.getId());
			}else if(demon!=null){
				if(demon.getLocation().distanceTo()<7){//moves closer to the demon
				Method.npcInteract(demon.getId(), "Attack");
				}else demon.getLocation().clickOnMap();
			}
			
		}else if(new Tile(2930,9644,0).canReach()){
			
			if(hasPurpleKey){
				Method.interactO(2600, "Open", "Magenta door");
			}else
			if(!Method.teleporting && Inventory.getItem(1547)!=null){
				hasPurpleKey = true;
			}else
			if(purplekey!=null){
				Method.interactG(purplekey.getId(), "Take", "Purple key");
			}else
			if(Players.getLocal().getInteracting()!=null){
				Method.basicFightNPC(melzar.getId());
			}else Method.npcInteract(melzar.getId(), "Attack");
			
		}else if(SceneEntities.getNearest(31130)!=null && new Tile(2933,9640,0).canReach()){
			if(hasBlueKey){
				Method.interactO(2599, "Open", "Blue door");
			}else
			if(!Method.teleporting && Inventory.getItem(1546)!=null){
				hasBlueKey = true;
			}else
			if(bluekey!=null){
				Method.interactG(bluekey.getId(), "Take", "Blue key");
			}else
			if(Players.getLocal().getInteracting()!=null){
				Method.basicFightNPC(zombie.getId());
			}else Method.npcInteract(zombie.getId(), "Attack");
			
		}else if(new Tile(2924,3248,2).canReach()||new Tile(2938,3241,2).canReach()||new Tile(2939,3239,1).canReach()
				||new Tile(2938,3240,0).canReach()){
			
			if(new Tile(2938,3240,0).canReach()){
				Method.interactO(2605, "Climb-down", "Ladder");
			}else
			if(new Tile(2939,3239,1).canReach()){
				Method.interactO(1746, "Climb-down", "Ladder");
			}else
			if(new Tile(2938,3240,2).distanceTo()<2){
				Method.interactO(1746, "Climb-down", "Ladder");
			}else new Tile(2938,3240,2).clickOnMap();
		}else if(new Tile(2934,3253,2).canReach()){//third level, skeletons
			
			if(hasYellowKey){//enters to the above location
				if(new Tile(2923,3250,2).distanceTo()<2){
					Method.interactO(2598, "Open", "Yellow door");
				}else Method.findPath(new Tile(2923,3250,2));
			}else
			if(!Method.teleporting && Inventory.contains(1545)){
				hasYellowKey = true;
			}else if(yellowkey!=null){//pick up the orange key off the ground
				if(yellowkey.getLocation().distanceTo()<8){
					Method.interactG(yellowkey.getId(), "Take", "Yellow key");
				}else yellowkey.getLocation().clickOnMap();
			}else
			if(Players.getLocal().getInteracting()!=null){//fights a the skeleton to obtain the orange key
				Method.basicFightNPC(6091);
			}else if(skeleton!=null){
				if(skeleton.getLocation().distanceTo()<8){
					Method.npcInteract(skeleton.getId(), "Attack");
				}else skeleton.getLocation().clickOnMap();
			}
			
			
		}else if(new Tile(2933,3254,1).canReach()){//the final location in the second level(ghosts)
			Method.interactO(1747, "Climb", "Ladder");
		}else if(new Tile(2926,3255,1).canReach()){//second level; where the ghosts are
			
			if(hasOrangeKey){//enters to the above location
				if(new Tile(2930,3253,1).distanceTo()<2){
					Method.interactO(2597, "Open", "Orange door");
				}else Method.findPath(new Tile(2930,3253,1));
			}else
			if(!Method.teleporting && Inventory.contains(1544)){
				hasOrangeKey = true;
			}else if(orangekey!=null){//pick up the orange key off the ground
				if(orangekey.getLocation().distanceTo()<8){
					Method.interactG(orangekey.getId(), "Take", "Red key");
				}else orangekey.getLocation().clickOnMap();
			}else
			if(Players.getLocal().getInteracting()!=null){//fights a the ghost to obtain the orange key
				Method.basicFightNPC(ghost.getId());
			}else if(ghost!=null){
				if(ghost.getLocation().distanceTo()<8){
					if(ghost.getLocation().canReach()){
					Method.npcInteract(ghost.getId(), "Attack");
					}else Method.interactO(1530, "Open", "Door");
				}else ghost.getLocation().clickOnMap();
			}
			
		}else
		if(new Tile(2941, 3248, 0).distanceTo()<8||new Tile(2937,3249,0).canReach()||hasRedKey){
			if(new Tile(2925,3256,0).canReach()){
				if(new Tile(2927,3256,0).distanceTo()<2){
				Method.interactO(1747, "Climb", "Ladder");
				}else Method.findPath(new Tile(2927,3256,0));
			}else
			if(hasRedKey){
				if(new Tile(2926,3253,0).distanceTo()<2){
					Method.interactO(2596, "Open", "Red door");
				}else Method.findPath(new Tile(2926,3253,0));
			}else
			if(new Tile(2937,3249,0).canReach()){
				if(!Method.teleporting && Inventory.contains(1543)){
					hasRedKey = true;
				}else if(redkey!=null){
					if(redkey.getLocation().distanceTo()<8){
						Method.interactG(1543, "Take", "Red key");
					}else redkey.getLocation().clickOnMap();
				}else
				if(Players.getLocal().getInteracting()!=null){
					Method.basicFightNPC(smallrat.getId());
				}else if(smallrat!=null){
					if(smallrat.getLocation().distanceTo()<8){
						Method.npcInteract(smallrat.getId(), "Attack");
					}else smallrat.getLocation().clickOnMap();
				}
			}else Method.interactO(2595, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMaze, "Walking to Melzar's maze", false);
		}else if (Vars.PORTSARIMLODE.distanceTo()<10 || Vars.FALADORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(Vars.PORTSARIMTELEPORT);
		}else Method.teleportTo(Vars.FALADORETELEPORT);
		
	}


	private void cs1() {
		final String opt[] = {"A dragon, that sounds","I thought you were going","The Guildmaster of the","Can you sell me a "};
		
		if(!Method.teleporting && Inventory.getItem(11279)==null && (ctx.settings.get(2268)&0x1F) == 9){
			Method.useBank = true;
		}
		if(Method.useBank && (ctx.settings.get(2268)&0x1F) == 9){
			Method.useBank(bankItems6, 1, 1, 90);
		}else
		if(new Tile(3067, 3513, 0).distanceTo()<5){
			if(new Tile(3068,3516,0).canReach()){
				Vars.DYNAMICV = false;
				if(!Method.findOption(opt))
					if(!Method.isChatting("Oziach")){
						Method.speakTo(747, "Oziach");
					}
			}else Method.interactO(37123, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOziach, "Walking to Oziach", false);
		}else if(Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.VARROKTELEPORT);
		
	}

	private void cs0() {
		final String opt[] ={"I talked to Oziach","Can I have a quest?"};
		final String opt2[]= {"Where can I find the","I talked to Oziach"};
		final String opt3[]= {"How can I protect myself from","About my quest to kill"};
		final String opt4[]= {"Where is Melzar's map","How can I find the route","About my quest to kill"};
		final String opt5[]= {"Where is Thalzar's map","How can I find the route","About my quest to kill"};
		final String opt6[]= {"Where is Lozar's map","How can I find the route","About my quest to kill"};
		
		if(new Tile(3191,3364,0).distanceTo()<8){
			if(new Tile(3191,3361,0).canReach()){
				Vars.DYNAMICV = false;
				Method.skipPics();
				if((ctx.settings.get(2268)&0x3F) == 2){
					
					if((ctx.settings.get(2269)>>13&0x7)==5 && Widgets.get(1188).validate()){
						Method.findOption(opt3);
					}else
					if((ctx.settings.get(2269)>>15&0x1)==1 && Widgets.get(1188).validate()){
						Method.findOption(opt2);
					}else if((ctx.settings.get(2269)>>10&0xF)==14 && Widgets.get(1188).validate()){
						Method.findOption(opt4);
					}else if((ctx.settings.get(2269)>>11&0x7)==6 && Widgets.get(1188).validate()){
						Method.findOption(opt5);
					}else if((ctx.settings.get(2269)>>10&0xF)==8 && Widgets.get(1188).validate()){
						Method.findOption(opt6);
					}
						
					if(!Method.isChatting("Guildmaster")){
						Method.speakTo(198, "Guildmaster");
					}
				}else
				if(!Method.findOption(opt))
				if(!Method.isChatting("Guildmaster")){
					Method.speakTo(198, "Guildmaster");
				}
			}else Method.interactO(1805, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToGuild, "Walking to the Champions Guild", false);
		}else if(Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.VARROKTELEPORT);
	}
	
}
*/
