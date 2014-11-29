package dqquests;


import org.powerbot.script.Tile;


import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Hud.Window;

import dqbody.DeltaQuester;
import dqbody.Method;
import dqbody.DeltaNode;
import dqbody.Paths;
import dqbody.Vars;
import dqbody.Vars.TeleportLode;
import dqbody.Vars.TeleportType;

public class DragonSlayer extends DeltaNode{

	public DragonSlayer(ClientContext ctx) {
		super(ctx);
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
	
	public final Tile[] pathToLumbridgeBank = new Tile[] { 
	new Tile(3221,3242,0), new Tile(3214,3257,0)		 
	};

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
	
	public final int bankItems[] = {1542};//just the maze key for melzar palace
	public final int bankItemAmount1[] = {1};
	
	public final int bankItems2[] = {1907,1791,13431,950};//the stuff for the oracle/door
	public final int bankItemAmount2[] = {1,1,1,1};
	
	public final int bankItems3[] = {960,1539,1540,1535,1536,1537};//planks & nails, shield, map pieces
	public final int bankItemAmount3[] = {3,90,1,1,1,1};
	
	
	public final int bankItems4[] = {1539,960,960,960};//Steel nails and planks
	public final int bankItems5[] = {1535,1536,1537,1538,1540,1539,960,960,960};//Maps and drag shield + some nails and planks(in the case of us dying AFTEr sailing the ship)
	public final int bankItemAmount5[] = {1,1,1,1,1,90,1,1,1};//Maps and drag shield
	public final int bankItems6[] = {11279};//Elvargs head
	//Malzar map piece 1535
	//Lazar's map piece 1537
	//Completed map is 1538
	
	public int itemsArray[] = {0,0,0,0,0,0,0};//contains the states of items needing to be purchased.
	public int itemDAmount[] = {1,1,1,1,1,3,90};
	public int itemDID[] = {1540,1791,1907,13431,950,961,1539};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {4500,2500,2000,3500,2500,4000,200};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Anti-dragon shield","Bowl (unfired)","Wizard's mind bomb",
			"Crayfish cage","Silk","Plank","Steel nails"};//contains the names of the items needing to be purchased.

	Method Method = new Method(ctx);
	Vars Vars = new Vars();
	
	
	public boolean activate() {
		return DeltaQuester.scriptToStart==32;
	}
	
	public void execute() {
	
		
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		DeltaQuester.numSteps = 16;
		Method.resetTeleporting();
		Method.foodSupport();
		
		//fully created map has an ID of 1538
		
		//combine map pices if you have all three
			if(ctx.hud.opened(Window.BACKPACK)&&
					Method.inventoryContains(1536)&&
					Method.inventoryContains(1537)&&
					Method.inventoryContains(1535)){//one of the map pieces
				Method.state("Combining map pieces");
				Method.combineItems(1536, 1537);//combine w/other map piece
			}else
		
		if(!DeltaQuester.checkedBank){//gets all the items in bank(stores it in an array)
			Method.checkBank();
		}else
	   if (DeltaQuester.GEFeature) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else
			if((ctx.varpbits.varpbit(2268)&0x1F) == 10){
				System.out.println("Step 16");
			DeltaQuester.progress = 16;
			Method.state("The Dragon Slayer quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else
		if(init){
			Vars.useBank = true;//make sure this is here if you want it to actually check the bank after using the G.E
			init();//Determines what part of the quest the player is on(map pieces), it checks alkharid bank for the map pieces you have
		}else
			if((ctx.varpbits.varpbit(2268)&0x1F) == 9){
				System.out.println("Step 15");
			DeltaQuester.progress = 15;
			cs1();//Speak to Oziach
		}else
		if((ctx.varpbits.varpbit(2268)&0x3F) == 2||(ctx.varpbits.varpbit(2268)&0x3F) == 3||(ctx.varpbits.varpbit(2268)&0x7) == 6||(ctx.varpbits.varpbit(2268)&0x7) == 7||(ctx.varpbits.varpbit(2268)&0x1F) == 8){
			if((ctx.varpbits.varpbit(2268)&0x1F) == 8){//changes to this setting upon crash landing on island
				System.out.println("Step 14");
				DeltaQuester.progress = 14;
				if(Vars.useBank){
					Method.useBank(bankItems5, bankItemAmount5);//the shield and some food and map
				}else
				cs12();//fight the dragon
			}else
			if((ctx.varpbits.varpbit(2268)&0x7) == 7){//the setting while we're sailing the sea/ being attacked then crashing
				System.out.println("Step 13");
				DeltaQuester.progress = 13;
				cs11();//Set sail for Crandor
			}else if((ctx.varpbits.varpbit(2269)&0xFFFFF)==0 || (ctx.varpbits.varpbit(2269)&0x7)==4){//grabs the map peices and etc
			//if((ctx.varpbits.varpbit(3077)&0x1F)==16){//did we get the dragon shield from the duke yet?
				
				if(hasMalzarMap){
					if(hasLazarMap){
						if(hasWormMap){
							
							if((ctx.varpbits.varpbit(2269)>>21&0x1)==1){//After speaking to Ned
								if((ctx.varpbits.varpbit(2268)&0x7) == 6){//ready to go off and fight dragon
									System.out.println("Step 12");
									DeltaQuester.progress = 12;
									if(Vars.useBank){
										Method.useBank(bankItems5, bankItemAmount5);//the shield and some food and map
									}else cs10();//equips sheild & speaks to ned
								}else
								if((ctx.varpbits.varpbit(2268)&0x3F) == 3){//we need to repair the ship
									System.out.println("Step 11");
									DeltaQuester.progress = 11;
									//to prevent it trying to walk to Port Sarim while at G.E
									if(ctx.bank.opened()){
										Vars.DYNAMICV = false;
									}
									//grab needed items and head off to port sarim
									if(Vars.useBank){
										Method.useBank(bankItems3, bankItemAmount3);//planks & nails
									}else
									cs9();//Repair the ship
								}else {//we gotta' buy the ship
									System.out.println("Step 10");
									DeltaQuester.progress = 10;
									System.out.println("HEREER");
									cs8();//Buy the ship in Port Sarim
								}
							}else{
								System.out.println("Step 9");
								DeltaQuester.progress = 9;
							cs7();//Speak to Ned about him being captain
							}
						}else
						if((ctx.varpbits.varpbit(2269)>>23&0x1)==1){//Setting to determine if we spoke to the goblins in the village yet
							System.out.println("Step 8");
							DeltaQuester.progress = 8;
							cs6();//Obtain Wormmap from the goblin in the jail
						}else{
							System.out.println("Step 7");
							DeltaQuester.progress = 7;
							cs5();//Speak to the goblins in the village
						}
					}else{
						
					if(Vars.useBank){
						Method.useBank(bankItems2, bankItemAmount2);//the ingredients for the oracle
					}else{
						System.out.println("Step 6");
						DeltaQuester.progress = 6;
					    cs4();//Obtains Lazar's map piece(from oracle)
					}
				}
				}else{
					if(DeltaQuester.checkedBank)
						Method.determineBank(bankItems);
				
			    if(Vars.useBank&&!new Tile(2936,9656,0).matrix(ctx).reachable()){//tile is the final area where you get the map piece
			    	Method.useBank(bankItems, bankItemAmount1);//grabs the maze key
				}else{
					System.out.println("Step 5");
					DeltaQuester.progress = 5;
					cs3();//Obtains Malzar's map piece
				}
				//DeltaQuester.progress = 4;
				//cs2();//Speak to Duke Horacio and gather the shield
			
				}
		}else{
			System.out.println("Step 3");
			DeltaQuester.progress = 3;
			//Method.useBank = true;//Resets variable so we can use the bank again
		     cs0();//Speak to the Guildmaster and gather more information
		    }
		}else
		if((ctx.varpbits.varpbit(2268)&0x3F) == 1){
		System.out.println("Step 2");
		DeltaQuester.progress = 2;
		cs1();//Speak to Oziach about obtaining a Rune platebody
		}else
		if((ctx.varpbits.varpbit(2268)&0x3F) == 0){
			System.out.println("Step 1");
		DeltaQuester.progress = 1;
		cs0();//Begin the quest by speaking to the Guildmaster in Varrok
		}
		
	}

	private void cs12() {//Fight the dragon
		final String opt[] = {"Yes, let's"};//safety procaution, actual conversation occurs in cs11
		Method.skipPics();
		
		if(!hasShieldEquip)
		if(Method.EquipmentContains(1540)){
			hasShieldEquip  =true;
		}else if(Method.inventoryContains(1540)){
			Method.interactInventory(1540, "Wear", "Dragon Shield");
		}
		if(hasShieldEquip)
		if(!Method.findOption(opt))
		if(!Method.isChatting("People"))
		if(new Tile(2855,9636,0).matrix(ctx).reachable()){//elvarg's room
			Vars.DYNAMICV = false;//to make sure our player in engaged in combat w/elvarg
			if(ctx.players.local().interacting()!=null&&
					ctx.players.local().inCombat()&&
					Method.npcIsNotNull(742)&&
					Method.getInteractingNPC().id()==742){
				Method.fightNPC(742);
			}else Method.npcInteract(742, "Attack");//fight the dragon elvargs
		}else
		if(new Tile(2845,9636,0).distanceTo(ctx.players.local().tile())<4){
			Method.interactO(25161, "Climb", "Rocks");
		}else if(Method.objIsNotNull(31130)){
			Method.clickOnMap(new Tile(2845,9636,0));
		}else
		if(new Tile(2835,3258,0).distanceTo(ctx.players.local().tile())<4){
			Method.interactO(25154, "Enter", "Hole");
		}else
		if(new Tile(2855,3239,0).matrix(ctx).reachable()){//some tile on the island
			ctx.movement.findPath(new Tile(2835,3258,0)).traverse();//top of mountain
		}else if(Vars.useBank){
			Method.useBank(bankItems3, bankItemAmount3);//planks & nails
		}else if((ctx.varpbits.varpbit(2269)>>8&0x7)==7){
			cs11();//Set sail again
		}else{
			cs9();//repair the ship
		}
		
	}

	private void cs11() {//get on the ship and set sail for the island, ship will crash
		final String opt[] = {"Yes, let's"};
		while(ctx.varpbits.varpbit(1114)==1){
			Method.skipPics();
			Method.isChatting("Cutscene");
		}
		
		System.out.println("Inside cs11");
		
		//combine the map pieces to make a whole one if you haven't already
		if( Method.inventoryContains(1535)){//the mappieces
			Method.combineItems(1535, 1536);
		}else if(hasShieldEquip){//got the shield equipped?
			if(new Tile(3047,3208,0).matrix(ctx).reachable()&& ctx.game.floor()==0){
				Method.interactO(272, "Climb-up", "Ladder");
			}else
			if(new Tile(3047,3207,1).matrix(ctx).reachable()&& ctx.game.floor()==1){
				if(!Method.findOption(opt))
					if(!Method.isChatting("Ned")){
						Method.speakTo(6082, "Ned");
					}
			}else
			if(new Tile(3047,3204,0).distanceTo(ctx.players.local().tile())<7){
				if(Method.byCloseLoc(new Tile(3047, 3204,0),4))//by boat
				Method.interactO(2593, "Cross", "Gangplank");
			}else cs8();//Get the player to the docks
			
		}else if(Method.EquipmentContains(1540)){
			hasShieldEquip  =true;
		}else Method.interactInventory(1540, "Wear", "Dragon Shield");
		
	}

	private void cs10() {//prepare the character then speak to Ned
		if( (!Method.inventoryContains(1540)&&!Method.inventoryContains(1538))){
			Method.useBank = true;
		}
		while(Method.inventoryGetCount(1540)==1){
			Method.interactInventory(1540, "Wear", "Dragon Shield");
		}
		if( Method.inventoryContains(1535)){
			Method.combineItems(1535, 1536);
		}else if(hasShieldEquip){
			cs7();//Speak to Ned
		}else if(Method.EquipmentContains(1540)){
			hasShieldEquip  =true;
		}else Method.interactInventory(1540, "Wear", "Dragon Shield");
		
	}

	private void cs9() {//Repair the ship
		
		
		if(new Tile(3048,3208,0).matrix(ctx).reachable()&&ctx.game.floor()==0){
			Vars.DYNAMICV = false;
			Method.interactO(25036, "Repair", "Hole");
		}else
		if(new Tile(3047,3207,1).matrix(ctx).reachable()&&ctx.game.floor()==1){
			Method.interactO(2590, "Climb", "Ladder");
		}else
		if(new Tile(3047,3204,0).distanceTo(ctx.players.local().tile())<7){
			if(Method.byCloseLoc(new Tile(3047, 3204,0),4))//by boat
			Method.interactO(2593, "Cross", "Gangplank");
		}else cs8();//Get the player to the docks
		
	}

	private void cs8() {//Buy the ship
		final String opt[] = {"Yep, sounds good","I'd like to buy"};
		
		if( !Method.inventoryContains(960) && !Method.inventoryContains(1539)&&(ctx.varpbits.varpbit(2268)&0x7) != 7
				&&(ctx.varpbits.varpbit(2268)&0x1F) != 8){
			Method.useBank = true;
		}
		//if(Method.useBank&&(ctx.varpbits.varpbit(2268)&0x7) != 7&&(ctx.varpbits.varpbit(2268)&0x1F) != 8){
		//	Method.useBank(bankItems4, 1, 90, 0);
		//}else
		if(new Tile(3043, 3203, 0).distanceTo(ctx.players.local().tile())<6){//Location at docks
			
			if(!Method.findOption(opt))
				if(!Method.isChatting("Klarense")){
					Method.speakTo(744, "Klarence");
				}
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDocks, "Walking to the docks in Port Sarim", false);
		}else if(TeleportLode.PORTSARIM.getTile().distanceTo(ctx.players.local().tile())<10|| 
				TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),"Port Sarim");
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}

	private void cs7() {//Speaks to Ned
		final String opt[] = {"Will you take me to","You're a sailor? Could","Talk about something"};
		
		if(new Tile(3103, 3257, 0).distanceTo(ctx.players.local().tile())<7){
			if(new Tile(3100,3257,0).matrix(ctx).reachable()){
				Vars.DYNAMICV  =false;
				if(!Method.findOption(opt))
					if(!Method.isChatting("Ned")){
						Method.speakTo(918, "Ned");
					}
			}else Method.interactO(1239, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToNed, "Walking to Ned", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(ctx.players.local().tile())<10 || 
				TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),"Draynor");
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),"Lumbridge");
		
	}

	private void cs6() {//Speaks to the goblin in the jail
		final String opt[] = {"Alright then, ","I suppose I could pay you","I believe you've got a"};
		
		 if(!Method.teleporting&& Method.inventoryContains(1536)){
			 Vars.useBank = true;
				hasWormMap = true;
			}else
		if(new Tile(3012,3189,0).matrix(ctx).reachable() &&new Tile(3012,3189,0).distanceTo(ctx.players.local().tile())<5){
			Vars.DYNAMICV = false;
			Method.skipPics();
			if(!Method.findOption(opt))
				if(!Method.isChatting("Goblin")){
					Method.speakTo(745, "Goblin");
				}
		}else
		if(new Tile(3010, 3198, 0).distanceTo(ctx.players.local().tile())<4){
			if(new Tile(3012,3189,0).matrix(ctx).reachable()){
				Method.clickOnMap(new Tile(3012,3189,0));
			}else Method.interactO(40108, "Open", "Door");
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToJail, "Walking to Port Sarim jail", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10 || TeleportLode.PORTSARIM.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),"Port Sarim");
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
		
	}

	private void cs5() {
		final String opt[] = {"I've heard that one of your"};
		if(new Tile(2956, 3513, 0) .distanceTo(ctx.players.local().tile())<7){
			if(new Tile(2957,3514,0).matrix(ctx).reachable()){
				Vars.DYNAMICV = false;
				if(!Method.findOption(opt))
					if(!Method.isChatting("Goblin")){
						Method.speakTo(4493, "Goblin");
					}
			}else Method.interactO(77970, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToGoblinVillage, "Walking to the goblin generals", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}

	private void cs4() {//Obtains Lazar's map piece
		final String opt[] = {"I seek a piece"};
		
		if( ((ctx.varpbits.varpbit(2269)>>17&0x1)!=1 || (ctx.varpbits.varpbit(2269)>>18&0x1)!=1||(ctx.varpbits.varpbit(2269)>>19&0x1)!=1||(ctx.varpbits.varpbit(2269)>>20&0x1)!=1)
				&& (!Method.inventoryContains(1791)&&!Method.inventoryContains(1907) && !Method.inventoryContains(13431)&&!Method.inventoryContains(950))){
			Method.useBank = true;
		}
			if((ctx.varpbits.varpbit(2269)&0x7)==4){//After speaking to the Oracle
			 if(Method.inventoryContains(1537)){
				hasLazarMap = true;
			}else
			if(Method.objIsNotNull(25115)){
				if(new Tile(3053,9841,0).matrix(ctx).reachable()){
					if(new Tile(3055,9841,0).distanceTo(ctx.players.local().tile())<5){
						Vars.DYNAMICV = false;
						Method.skipPics();
						if(!ctx.widgets.component(1186,0).visible()){
						Method.interactO(2587,"Open", "Chest");
						Method.interactO(2588,"Search", "Chest");
						}
					}else Method.clickOnMap(new Tile(3055,9841,0));
				}else
				if (new Tile(3049,9840,0).distanceTo(ctx.players.local().tile()) < 7) {
					if((ctx.varpbits.varpbit(2269)>>18&0x1)==1){
						if((ctx.varpbits.varpbit(2269)>>20&0x1)==1){
							if((ctx.varpbits.varpbit(2269)>>19&0x1)==1){
								if((ctx.varpbits.varpbit(2269)>>17&0x1)==1){
									Method.interactO(25115, "Open", "Magic door");
								}else Method.useItemOn(950,25115, "Door");
							}else Method.useItemOn(13431,25115, "Door");
						}else Method.useItemOn(1907,25115, "Door");
					}else Method.useItemOn(1791,25115, "Door");
					
				} else ctx.movement.findPath(new Tile(3049,9840,0)).traverse();
			}
			else if(new Tile(3017, 3449, 0).distanceTo(ctx.players.local().tile())<7){//Location outside Dwarven Mines
				if(!Method.isChatting("Dwarf"))
				Method.interactO(30942, "Climb-down", "Ladder");
			}else if(Vars.DYNAMICV){
				Method.walking(pathToDwarvenMine, "Walking to the Dwarven Mines", false);
			}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
			
			
			
		}else if(new Tile(3011, 3503, 0).distanceTo(ctx.players.local().tile())<5){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
			if(!Method.isChatting("Oracle")){
				Method.speakTo(746, "Oracle");
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOracle, "Walking to the Oracle", false);
		}else if(TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}

	private void init() {//Determines what part of the quest by checking the bank
		Method.state("Initializing - checking bank for map pieces");
		if(new Tile(3181,3481, 0).distanceTo(ctx.players.local().tile())<6){//lummy bank(outside)
			if(ctx.bank.opened()){
				if(Method.bankContains(1535) || Method.inventoryContains(1535)){//malzar map
					System.out.println("Found Malzar map");
					hasMalzarMap = true;
				}
				if(Method.bankContains(1537) || Method.inventoryContains(1537)){//lazar map
					System.out.println("Found Lazar map");
					hasLazarMap = true;
				}
				if(Method.bankContains(1536) || Method.inventoryContains(1536)){//worm map
					System.out.println("Found Worm map");
					hasWormMap = true;
				}
				if(Method.bankContains(1538) || Method.inventoryContains(1538)){
					System.out.println("Found perfect map");
					hasWormMap = true;
					hasLazarMap = true;
					hasMalzarMap = true;
				}
				init = false;
				System.out.println("Finished initializing");
			}else {
				System.out.println("Attempting to open bank");
				ctx.bank.open();
			}
		}else if(Vars.DYNAMICV3){
			Method.walking(Paths.pathToGE, "Walking to Lummbridge bank", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV3 = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");
		
	}

	private void cs3() {//Obtains Malzar's map piece
		while(Method.backPackIsFull() && Method.inventoryContains(526)){
			Method.state("Backpack full, burying bones");
			Method.interactInventory(526, "Bury", "Bones");
		}
		
			if( ctx.players.local().interacting()==null && Method.inventoryContains((526))){
			Method.interactInventory(526, "Drop", "Bones");
		}else if(!Method.teleporting&& Method.inventoryContains((1535))){
			hasMalzarMap = true;
		}else if(new Tile(2936,9656,0).matrix(ctx).reachable()&& ctx.game.floor()==0){
			Vars.DYNAMICV = false;
			Vars.useBank = true;//use bank for later
			Method.skipPics();
			Method.interactO(2603, "Open", "Chest");
			Method.interactO(2604, "Search", "Chest");
		}else if(new Tile(2929,9652,0).matrix(ctx).reachable()&& ctx.game.floor()==0){
			
			if(hasGreenKey){
				if(new Tile(2936,9655,0).distanceTo(ctx.players.local().tile())<6){
				Method.interactO(2601, "Open", "Green door");
				}else Method.clickOnMap(new Tile(2936,9655,0));
			}else
			if( Method.inventoryContains(1548)){
				hasGreenKey = true;
			}else
			if(Method.gItemIsNotNull(1548)){//green key
				Method.interactG(1548, "Take", "Green key");
			}else
			if(Method.getInteractingNPC()!=null){//if fighting
				Method.fightNPC(6101);//fight the demon
			}else if(Method.npcIsNotNull(6101)){//demon
				if(Method.getNPC(6101).tile().distanceTo(ctx.players.local().tile())<7){//moves closer to the demon
				Method.npcInteract(6101, "Attack");
				}else Method.clickOnMap(Method.getNPC(6101).tile());//demon loc
			}
			
		}else if(new Tile(2929,9643,0).matrix(ctx).reachable()&& ctx.game.floor()==0){
			
			if(hasPurpleKey){
				Method.interactO(2600, "Open", "Magenta door");
			}else
			if( Method.inventoryContains(1547)){
				hasPurpleKey = true;
			}else
			if(Method.gItemIsNotNull(1547)){//purple key
				Method.interactG(1547, "Take", "Purple key");
			}else
			if(Method.getInteractingNPC()!=null){//if fighting
				Method.fightNPC(753);//melzar
			}else Method.npcInteract(753, "Attack");//melzar
			
		}else if(Method.objIsNotNull(31130) && new Tile(2933,9640,0).matrix(ctx).reachable()&& ctx.game.floor()==0){
			if(hasBlueKey){
				Method.interactO(2599, "Open", "Blue door");
			}else
			if( Method.inventoryContains(1546)){
				hasBlueKey = true;
			}else
			if(Method.gItemIsNotNull(1546)){//bluekey
				Method.interactG(1546, "Take", "Blue key");
			}else
			if(Method.getInteractingNPC()!=null){//if fighting
				Method.fightNPC(6099);//zombies
			}else Method.npcInteract(6099, "Attack");
			
		}else if(new Tile(2924,3248,2).matrix(ctx).reachable()&& ctx.game.floor()==2||
				new Tile(2938,3241,2).matrix(ctx).reachable()&& ctx.game.floor()==2||
				new Tile(2939,3239,1).matrix(ctx).reachable()&& ctx.game.floor()==1||
				new Tile(2938,3240,0).matrix(ctx).reachable()&& ctx.game.floor()==0){
			
			if(new Tile(2938,3240,0).matrix(ctx).reachable()&&ctx.game.floor()==0){
				Method.interactO(2605, "Climb-down", "Ladder");
			}else
			if(new Tile(2939,3239,1).matrix(ctx).reachable()&&ctx.game.floor()==1){
				Method.interactO(1746, "Climb-down", "Ladder");
			}else
			if(new Tile(2938,3240,2).distanceTo(ctx.players.local().tile())<2){
				Method.interactO(1746, "Climb-down", "Ladder");
			}else Method.clickOnMap(new Tile(2938,3240,2));
		}else if(new Tile(2934,3253,2).matrix(ctx).reachable() && ctx.game.floor()==2){//third level, skeletons
			
			if(hasYellowKey){//enters to the above location
				if(new Tile(2923,3250,2).distanceTo(ctx.players.local().tile())<2){
					Method.interactO(2598, "Open", "Yellow door");
				}else Method.clickOnMap((new Tile(2923,3250,2)));
			}else
			if( Method.inventoryContains(1545)){//yellow key
				hasYellowKey = true;
			}else if(Method.gItemIsNotNull(1545)){//pick up the orange key off the ground
				if(Method.getGroundItem(1545).tile().distanceTo(ctx.players.local().tile())<8){
					Method.interactG(1545, "Take", "Yellow key");
				}else Method.clickOnMap(Method.getGroundItem(1545).tile());//yellow key
			}else
			if(Method.getInteractingNPC()!=null){//fights a the skeleton to obtain the orange key
				Method.fightNPC(6091);
			}else if(Method.npcIsNotNull(6091)){
				if(Method.getNPC(6091).tile().distanceTo(ctx.players.local().tile())<8){
					Method.npcInteract(6091, "Attack");
				}else Method.clickOnMap(Method.getNPC(6091).tile());
			}
			
			
		}else if(new Tile(2933,3254,1).matrix(ctx).reachable() && ctx.game.floor()==1){//the final location in the second level(ghosts)
			Method.interactO(1747, "Climb", "Ladder");
		}else if(new Tile(2926,3255,1).matrix(ctx).reachable() && ctx.game.floor()==1){//second level; where the ghosts are
			
			if(hasOrangeKey){//enters to the above location
				if(new Tile(2930,3253,1).distanceTo(ctx.players.local().tile())<1){
					Method.interactO(2597, "Open", "Orange door");
				}else {
					new Tile(2930,3253,1).matrix(ctx).click();
					Method.clickOnMap((new Tile(2930,3253,1)));
				}
			}else
			if( Method.inventoryContains(1544)){//orange key
				hasOrangeKey = true;
			}else if(Method.gItemIsNotNull(1544)){//pick up the orange key off the ground
				if(Method.getGroundItem(1544).tile().distanceTo(ctx.players.local().tile())<8){
					Method.interactG(1544, "Take", "Orange key");
				}else Method.clickOnMap(Method.getGroundItem(1544).tile());//orange key loc
			}else
			if(Method.getInteractingNPC()!=null){//fights a the ghost to obtain the orange key
				Method.fightNPC(6094);//ghosts
			}else if(Method.npcIsNotNull(6094)){//ghosts
				if(Method.getNPC(6094).tile().distanceTo(ctx.players.local().tile())<8){
					if(Method.getNPC(6094).tile().matrix(ctx).reachable()){
					Method.npcInteract(6094, "Attack");
					}else Method.interactO(1530, "Open", "Door");
				}else Method.clickOnMap(Method.getNPC(6094).tile());//ghost loc
			}
			
		}else
		if(new Tile(2941, 3248, 0).distanceTo(ctx.players.local().tile())<8||new Tile(2937,3249,0).matrix(ctx).reachable()||hasRedKey){
			if(new Tile(2925,3256,0).matrix(ctx).reachable()){
				if(new Tile(2927,3256,0).distanceTo(ctx.players.local().tile())<2){
				Method.interactO(1747, "Climb", "Ladder");
				}else Method.clickOnMap((new Tile(2927,3256,0)));
			}else
			if(hasRedKey){
				if(new Tile(2926,3253,0).distanceTo(ctx.players.local().tile())<2){
					Method.interactO(2596, "Open", "Red door");
				}else Method.clickOnMap((new Tile(2926,3253,0)));
			}else
			if(new Tile(2937,3249,0).matrix(ctx).reachable() && ctx.game.floor()==0){//inside first floor
				if( Method.inventoryContains(1543)){
					hasRedKey = true;
				}else if(Method.gItemIsNotNull(1543)){//red key
					if(Method.getGroundItem(1543).tile().distanceTo(ctx.players.local().tile())<8){
						Method.interactG(1543, "Take", "Red key");
					}else Method.clickOnMap(Method.getGroundItem(1543).tile());
				}else
				if(Method.getInteractingNPC()!=null){
					System.out.println("Here4");
					Method.fightNPC(6088);//rat
				}else if(Method.npcIsNotNull(6088)){
					System.out.println("Here");
					if(Method.getNPC(6088).tile().distanceTo(ctx.players.local().tile())<8){
						System.out.println("Here2");
						Method.npcInteract(6088, "Attack");
					}else {
						System.out.println("Here3");
						Method.clickOnMap(Method.getNPC(6088).tile());
					}
				}
			}else Method.interactO(2595, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToMaze, "Walking to Melzar's maze", false);
		}else if (TeleportLode.PORTSARIM.getTile().distanceTo(ctx.players.local().tile())<10 || TeleportLode.FALADOR.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(TeleportType.PORTSARIM.getTeleport(),"Port Sarim");
		}else Method.teleportTo(TeleportType.FALADOR.getTeleport(),"Falador");
		
	}


	private void cs1() {//Speak to Oziach about obtaining a Rune platebody
		final String opt[] = {"A dragon, that sounds","I thought you were going","The Guildmaster of the","Can you sell me a "};
		
		if( !Method.inventoryContains(11279) && (ctx.varpbits.varpbit(2268)&0x1F) == 9){
			Method.useBank = true;
		}
		//if(Method.useBank && (ctx.varpbits.varpbit(2268)&0x1F) == 9){
		//	Method.useBank(bankItems6, 1, 1, 90);
		//}else
		if(new Tile(3067, 3513, 0).distanceTo(ctx.players.local().tile())<5){
			if(new Tile(3068,3516,0).matrix(ctx).reachable()){
				Vars.DYNAMICV = false;
				if(!Method.findOption(opt))
					if(!Method.isChatting("Oziach")){
						Method.speakTo(747, "Oziach");
					}
			}else Method.interactO(37123, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOziach, "Walking to Oziach", false);
		}else if(TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");
		
	}

	private void cs0() {//Begin the quest by speaking to the Guildmaster in Varrock
		final String opt[] ={"I talked to Oziach","Can I have a quest?"};
		final String opt2[]= {"Where can I find the","I talked to Oziach"};
		final String opt3[]= {"How can I protect myself from","About my quest to kill"};
		final String opt4[]= {"Where is Melzar's map","How can I find the route","About my quest to kill"};
		final String opt5[]= {"Where is Thalzar's map","How can I find the route","About my quest to kill"};
		final String opt6[]= {"Where is Lozar's map","How can I find the route","About my quest to kill"};
		
		if(new Tile(3191,3364,0).distanceTo(ctx.players.local().tile())<8){
			if(new Tile(3191,3361,0).matrix(ctx).reachable()){
				Vars.DYNAMICV = false;
				Method.skipPics();
				if((ctx.varpbits.varpbit(2268)&0x3F) == 2){
					
					if((ctx.varpbits.varpbit(2269)>>13&0x7)==5 && ctx.widgets.component(1188,0).visible()){
						Method.findOption(opt3);
					}else
					if((ctx.varpbits.varpbit(2269)>>15&0x1)==1 && ctx.widgets.component(1188,0).visible()){
						Method.findOption(opt2);
					}else if((ctx.varpbits.varpbit(2269)>>10&0xF)==14 && ctx.widgets.component(1188,0).visible()){
						Method.findOption(opt4);
					}else if((ctx.varpbits.varpbit(2269)>>11&0x7)==6 && ctx.widgets.component(1188,0).visible()){
						Method.findOption(opt5);
					}else if((ctx.varpbits.varpbit(2269)>>10&0xF)==8 && ctx.widgets.component(1188,0).visible()){
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
		}else if(TeleportLode.VARROCK.getTile().distanceTo(ctx.players.local().tile())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.VARROCK.getTeleport(),"Varrock");
	}
	
}

