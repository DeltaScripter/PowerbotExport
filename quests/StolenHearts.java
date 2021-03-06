package quests;

import java.awt.Point;






import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Npc;
import org.powerbot.script.rt6.Player;

import quests.Vars.TeleportLode;
import quests.Vars.TeleportType;

public class StolenHearts extends DeltaNode{

	
	public StolenHearts(ClientContext ctx) {
		super(ctx);
	}

	public final Tile[] pathToOzanDraynor = new Tile[] { 
			new Tile(3209,3238,0), new Tile(3213,3247,0), new Tile(3213,3257,0),
			new Tile(3216,3266,0), new Tile(3207,3272,0), new Tile(3197,3278,0),
		    new Tile(3190,3282,0), new Tile(3182,3285,0), new Tile(3175,3287,0)
			, new Tile(3167,3288,0), new Tile(3157,3289,0), new Tile(3146,3293,0)
			, new Tile(3134,3294,0), new Tile(3122,3297,0), new Tile(3113,3296,0)
			, new Tile(3105,3300,0), new Tile(3109,3291,0), new Tile(3106,3283,0)
			, new Tile(3105,3276,0), new Tile(3097,3273,0), new Tile(3094,3275,0)};
	
	public final Tile[] pathToHQ = new Tile[] {
			new Tile(3232, 3221, 0), new Tile(3231, 3226, 0), new Tile(3228, 3230, 0), 
			new Tile(3224, 3233, 0), new Tile(3221, 3238, 0), new Tile(3220, 3243, 0), 
			new Tile(3217, 3247, 0), new Tile(3212, 3247, 0), new Tile(3207, 3247, 0), 
			new Tile(3202, 3247, 0), new Tile(3197, 3246, 0), new Tile(3192, 3245, 0), 
			new Tile(3187, 3245, 0), new Tile(3182, 3245, 0), new Tile(3179, 3249, 0), 
			new Tile(3175, 3252, 0), new Tile(3172, 3256, 0), new Tile(3167, 3258, 0), 
			new Tile(3162, 3257, 0), new Tile(3157, 3257, 0), new Tile(3153, 3260, 0), 
			new Tile(3148, 3260, 0), new Tile(3143, 3258, 0), new Tile(3139, 3255, 0), 
			new Tile(3134, 3253, 0), new Tile(3129, 3251, 0), new Tile(3124, 3251, 0), 
			new Tile(3120, 3254, 0), new Tile(3119, 3259, 0), new Tile(3114, 3260, 0), 
			new Tile(3109, 3263, 0), new Tile(3106, 3267, 0), new Tile(3105, 3272, 0), 
			new Tile(3104, 3277, 0), new Tile(3104, 3282, 0), new Tile(3105, 3287, 0), 
			new Tile(3106, 3292, 0), new Tile(3106, 3297, 0), new Tile(3103, 3293, 0), 
			new Tile(3102, 3288, 0), new Tile(3102, 3283, 0), new Tile(3102, 3278, 0), 
			new Tile(3104, 3273, 0), new Tile(3106, 3268, 0), new Tile(3109, 3264, 0), 
			new Tile(3113, 3261, 0), new Tile(3117, 3258, 0), new Tile(3122, 3256, 0), 
			new Tile(3126, 3252, 0), new Tile(3124, 3248, 0) };
	
	public final Tile[] pathToJailHouse = new Tile[] { 
			new Tile(3232, 3220, 0), new Tile(3232, 3225, 0), new Tile(3229, 3229, 0), 
			new Tile(3226, 3233, 0), new Tile(3223, 3237, 0), new Tile(3220, 3242, 0), 
			new Tile(3218, 3247, 0), new Tile(3213, 3249, 0), new Tile(3208, 3249, 0), 
			new Tile(3203, 3249, 0), new Tile(3198, 3248, 0), new Tile(3193, 3247, 0), 
			new Tile(3188, 3247, 0), new Tile(3183, 3246, 0), new Tile(3178, 3246, 0), 
			new Tile(3174, 3243, 0), new Tile(3170, 3240, 0), new Tile(3165, 3238, 0), 
			new Tile(3160, 3239, 0), new Tile(3155, 3238, 0), new Tile(3150, 3240, 0), 
			new Tile(3145, 3241, 0), new Tile(3142, 3245, 0), new Tile(3138, 3248, 0), 
			new Tile(3134, 3251, 0), new Tile(3129, 3251, 0), new Tile(3126, 3247, 0), 
			new Tile(3127, 3247, 0), new Tile(3125, 3249, 0), new Tile(3121, 3252, 0), 
			new Tile(3119, 3257, 0), new Tile(3115, 3260, 0), new Tile(3110, 3260, 0), 
			new Tile(3107, 3264, 0), new Tile(3105, 3269, 0), new Tile(3103, 3274, 0), 
			new Tile(3103, 3279, 0), new Tile(3104, 3284, 0), new Tile(3105, 3289, 0), 
			new Tile(3105, 3294, 0), new Tile(3105, 3299, 0), new Tile(3104, 3294, 0), 
			new Tile(3104, 3289, 0), new Tile(3104, 3284, 0), new Tile(3103, 3279, 0), 
			new Tile(3102, 3274, 0), new Tile(3104, 3269, 0), new Tile(3108, 3266, 0), 
			new Tile(3112, 3263, 0), new Tile(3116, 3260, 0), new Tile(3118, 3255, 0), 
			new Tile(3122, 3252, 0), new Tile(3126, 3249, 0), new Tile(3126, 3247, 0) };
	
 public final Tile[] pathToShore = new Tile[] { 
			new Tile(3127, 3244, 0), new Tile(3129, 3249, 0), new Tile(3124, 3249, 0), 
			new Tile(3119, 3247, 0), new Tile(3115, 3244, 0), new Tile(3114, 3239, 0), 
			new Tile(3117, 3235, 0), new Tile(3121, 3232, 0), new Tile(3121, 3227, 0), 
			new Tile(3121, 3222, 0), new Tile(3122, 3217, 0), new Tile(3124, 3212, 0), 
			new Tile(3126, 3207, 0), new Tile(3125, 3202, 0), new Tile(3127, 3201, 0) };
 
 public final Tile[] pathToShoreFL = new Tile[] { 
		 new Tile(3234, 3219, 0), new Tile(3231, 3224, 0), new Tile(3230, 3229, 0), 
			new Tile(3227, 3233, 0), new Tile(3225, 3238, 0), new Tile(3222, 3242, 0), 
			new Tile(3218, 3245, 0), new Tile(3213, 3246, 0), new Tile(3208, 3246, 0), 
			new Tile(3203, 3246, 0), new Tile(3198, 3246, 0), new Tile(3193, 3246, 0), 
			new Tile(3188, 3244, 0), new Tile(3183, 3243, 0), new Tile(3178, 3243, 0), 
			new Tile(3173, 3241, 0), new Tile(3168, 3239, 0), new Tile(3163, 3238, 0), 
			new Tile(3158, 3236, 0), new Tile(3153, 3234, 0), new Tile(3148, 3233, 0), 
			new Tile(3143, 3232, 0), new Tile(3139, 3229, 0), new Tile(3134, 3226, 0), 
			new Tile(3131, 3222, 0), new Tile(3129, 3217, 0), new Tile(3128, 3212, 0), 
			new Tile(3128, 3207, 0), new Tile(3126, 3202, 0), new Tile(3103, 3300, 0), 
			new Tile(3107, 3297, 0), new Tile(3108, 3292, 0), new Tile(3107, 3287, 0), 
			new Tile(3106, 3282, 0), new Tile(3103, 3278, 0), new Tile(3102, 3273, 0), 
			new Tile(3102, 3268, 0), new Tile(3102, 3263, 0), new Tile(3102, 3258, 0), 
			new Tile(3102, 3253, 0), new Tile(3102, 3248, 0), new Tile(3102, 3243, 0), 
			new Tile(3104, 3238, 0), new Tile(3105, 3233, 0), new Tile(3108, 3229, 0), 
			new Tile(3111, 3225, 0), new Tile(3115, 3222, 0), new Tile(3118, 3218, 0), 
			new Tile(3121, 3214, 0), new Tile(3124, 3210, 0), new Tile(3126, 3205, 0), 
			new Tile(3126, 3200, 0) };
 
 public final Tile[] pathToAlkharid = new Tile[] { 
		 new Tile(3206,3232,0), new Tile(3219,3227,0),
		 new Tile(3231, 3221, 0), new Tile(3232, 3226, 0), new Tile(3237, 3226, 0), 
			new Tile(3242, 3226, 0), new Tile(3247, 3226, 0), new Tile(3252, 3225, 0), 
			new Tile(3257, 3225, 0), new Tile(3262, 3227, 0), new Tile(3266, 3230, 0), 
			new Tile(3271, 3229, 0), new Tile(3275, 3226, 0), new Tile(3279, 3223, 0), 
			new Tile(3281, 3218, 0), new Tile(3282, 3213, 0), new Tile(3284, 3208, 0), 
			new Tile(3284, 3203, 0), new Tile(3284, 3198, 0), new Tile(3287, 3194, 0), 
			new Tile(3291, 3191, 0), new Tile(3293, 3186, 0), new Tile(3292, 3181, 0), 
			new Tile(3292, 3176, 0), new Tile(3292, 3171, 0), new Tile(3292, 3166, 0), 
			new Tile(3291, 3165, 0) };
 
 public final DeltaArea ThugDoor = new DeltaArea(new Tile[] { 
		 new Tile(3087, 3276, 0), new Tile(3087, 3272, 0), new Tile(3093, 3272, 0), 
			new Tile(3092, 3277, 0) });
	
 public final DeltaArea JailDoor = new DeltaArea(new Tile[] { 
		 new Tile(3125, 3249, 0), new Tile(3125, 3245, 0), new Tile(3129, 3245, 0), 
			new Tile(3129, 3248, 0) });
 
 
	public int bankItems[]  ={25129,25127};
	public int bankItemAmount[]  ={1,1};
	public Vars Vars = new Vars();
	public Method Method = new Method(ctx);
	
	
	boolean q = true;
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		if(ctx.camera.pitch()<70)
			ctx.camera.pitch(90);
		
		while(ctx.widgets.component(669,17).visible()){
			System.out.println("Closing hint dialogue");
			ctx.widgets.component(669,19).click();
			Method.sleep(2000);
		}
	
		
		DeltaQuester.numSteps  =16;
		Method.resetTeleporting();
		Method.foodSupport();
	
		if(DeltaQuester.checkedBank)
			Method.determineBank(bankItems);
			if(!DeltaQuester.checkedBank && (ctx.varpbits.varpbit(2449)&0x7F)!=105){
			Method.checkBank();
		}else
	    if(quests.Vars.useBank && (ctx.varpbits.varpbit(2449)&0x7F)!=105){
			Method.useBank(bankItems, bankItemAmount);
		}else
		if((ctx.varpbits.varpbit(2449)&0x7F)==105){
			DeltaQuester.progress = 16;
			Method.state("The Stolen Hearts quest has been completed.");
			Method.sleep(2000);
			DeltaQuester.e = true;
		}else
		if((ctx.varpbits.varpbit(2449)&0x7F)==100){
			System.out.println("cs14 last step");
			DeltaQuester.progress = 15;
			cs14();//
		}else
		if((ctx.varpbits.varpbit(2449)&0x7F)==90||(ctx.varpbits.varpbit(2449)&0x7F)==95){
			System.out.println("cs13 fourteenth step");
			DeltaQuester.progress = 14;
			cs13();//Finish the cut scene conversation with Emir's death
		}else
		if((ctx.varpbits.varpbit(2449)&0x7F)==85 ||(ctx.varpbits.varpbit(2449)&0xFF)==213){
			System.out.println("cs12 thirteenth step");
			DeltaQuester.progress = 13;
			cs12();//Solve the puzzle and place the key in the display
		}else
		if((ctx.varpbits.varpbit(2449)&0x7F)==70||(ctx.varpbits.varpbit(2449)&0x7F)==72||(ctx.varpbits.varpbit(2449)&0x7F)==75||(ctx.varpbits.varpbit(2449)&0x7F)==80){
			System.out.println("cs11 twelveth step");
			DeltaQuester.progress = 12;
			cs11();//Get's the player into the puzzle room + completes the conversation at that window ledge
		}else
		if((ctx.varpbits.varpbit(2449)&0x7F)==65){
			System.out.println("cs10 eleventh step");
			DeltaQuester.progress = 11;
			cs10();//Speak to the guards at Aklharid palace with Ozan
		}else
		if((ctx.varpbits.varpbit(2449)&0x3F)==60){
			System.out.println("cs9 tenth step");
			DeltaQuester.progress = 10;
			cs9();//Make Ozan follow us to Alkharid initially
		}else
		if((ctx.varpbits.varpbit(2449)&0x3F)==55){
			System.out.println("cs8 ninth step");
			DeltaQuester.progress = 9;
			cs8();//Grabs the scroll after the fight in both situations(if you don't pick it up right away after the fight.)
		}else
		if((ctx.varpbits.varpbit(2449)&0x3F)==50){
			System.out.println("cs7 eighth step");
			DeltaQuester.progress = 8;
			cs7();//Finish the conversations and defeat the three enemies at the shore
		}else
		if((ctx.varpbits.varpbit(2449)>>21&0x3)==2){
		
			DeltaQuester.progress = 8;
			cs6();//Complete the initial cutscene at the shore
		}else
		if((ctx.varpbits.varpbit(2449)&0x3F)==45){
			System.out.println("cs5 seventh step");
			DeltaQuester.progress = 7;
			cs5();//Get ozan to the shore
		}else
		if((ctx.varpbits.varpbit(2449)>>5&0x1)==1||(ctx.varpbits.varpbit(2449)>>5&0x3F)==53||(ctx.varpbits.varpbit(2449)>>5&0x3F)==45||(ctx.varpbits.varpbit(2449)>>5&0x3F)==13||(ctx.varpbits.varpbit(2449)>>5&0x3F)==5||(ctx.varpbits.varpbit(2449)>>5&0x3F)==1){
			System.out.println("cs4 sixth step");
			DeltaQuester.progress = 6;
			cs4();//Complete the conversation with Khnum and friends
		}else
		if((ctx.varpbits.varpbit(2449)&0x1F)==30){
			System.out.println("cs3 fifth step");
			DeltaQuester.progress = 5;
			cs3();//Complete the initial conversation with Khnum and friends
		}else
		if((ctx.varpbits.varpbit(2449)&0x1F)==25||(ctx.varpbits.varpbit(2449)>>21 & 0xF) ==15){
			System.out.println("cs2 fourth step");
			DeltaQuester.progress = 4;
			cs2();//Complete the trailing Khnum scene
		}else
		if((ctx.varpbits.varpbit(2449)&0x1F)==20){
			System.out.println("cs0 third step");
			DeltaQuester.progress = 3;
			cs0();//Report to Ozan about finding Khnum's HQ
		}else
		if((ctx.varpbits.varpbit(2449)&0x7)==5 ||(ctx.varpbits.varpbit(2449)&0xF)==10||(ctx.varpbits.varpbit(2449)&0xF)==15){
			System.out.println("cs1 second step");
			DeltaQuester.progress = 2;
			cs1();//Speak to Khnum in his base and take his entry examination.
		}else
		if((ctx.varpbits.varpbit(2449)&0x1)==0){
			System.out.println("cs0 first step");
			DeltaQuester.progress = 1;
			cs0();//Start the quest
		}else System.out.println("Lost");
			
	}

	private void cs14() {
		
		if(!Method.isChatting("Osman")){
			Method.speakTo(15907, "Osman");
		}
		
	}

	private void cs13() {
		final String opt[] = {"Calm Ozan down","Hamzanama"};
		Method.skipPics();
		if((ctx.varpbits.varpbit(1113)&0x1)==1){
			if(!Method.findOption(opt)){
			if(!Method.isChatting("People")){
				Method.sleep(30);
			}
			}
		}else cs11();
		
	}

	private void cs12() {
		if(Method.objIsNotNull(75870) && Method.getObject(75870).tile().distanceTo(ctx.players.local().tile())<10||
				Method.objIsNotNull(75871) && Method.getObject(75871).tile().distanceTo(ctx.players.local().tile())<10){
				if(ctx.widgets.component(1191,0).visible() || ctx.widgets.component(1184,0).visible()){
					System.out.println("Closing dialogue");
					Method.pressContinue();
				}
				if(Method.inventoryContains(25129)){//orb item
					
					if(!ctx.widgets.component(1189,2).visible())//The screen that appearrs after getting the puzzle completely
				Method.interactO(76708, "Place", "Display");
					else {
						Method.pressContinue();
						//ctx.widgets.component(1189,3).click();
					}
			}else//Gather the key
			if(ctx.widgets.component(1351,29).visible()){
				System.out.println("Here");
				if(ctx.widgets.component(1351,0).visible()){
					Method.state("Finishing the puzzle");
					dragMouse(ctx.widgets.component(1351,26).centerPoint(),ctx.widgets.component(1351,0).centerPoint());
				}else if((ctx.varpbits.varpbit(2450)>>14 &0x1) ==1){
					if((ctx.varpbits.varpbit(2450)>>18 &0x1) ==1){
							Method.state("Weighing weights");
							ctx.widgets.component(1351,84).click();
						
					}else dragMouse(ctx.widgets.component(1351,12).centerPoint(),ctx.widgets.component(1351,20).centerPoint());
					
				}else dragMouse(ctx.widgets.component(1351,6).centerPoint(),ctx.widgets.component(1351,26).centerPoint());
			
			} else {
				System.out.println("Here7");
				Method.interactO(75870, "Inspect", "Puzzle");
			}
				
			
		}else {
			System.out.println("Here4");
			cs11();//get player to puzzle roo
		}
		
	}
	private void dragMouse(Point point, Point endLocation) {
		ctx.input.move(point);
		ctx.input.drag(point, true);
		ctx.input.drag(endLocation, true);
		Method.sleep(1800);
	}
	private void cs11() {//Perform the amazing parkour course on the roofs of buildings in Alkharid
		final String opt[] ={"Yes"};
		Player local = ctx.players.local();
		
		//ctx.camera.setYaw(60);
		if(ctx.varpbits.varpbit(1114)==2){
			Method.isChatting("People");
		}else
		if ((ctx.varpbits.varpbit(2449)>>23 & 0x3) ==3 || ctx.game.floor()==1){//Ozan is following us again.
			if((ctx.varpbits.varpbit(2145)>>28 &0x7) == 4){
				//if(Camera.getPitch()<60)
				//	Camera.setPitch(70);
				if (!Method.isChatting("People"))// obj.tile().distanceTo(ctx.players.local().tile())>5&&
					if (new Tile(3285, 3164, 2).distanceTo(local.tile()) < 17&& ctx.game.floor() == 2
								&& new Tile(3270, 3168, 2).distanceTo(local.tile())>9) {
						if (new Tile(3291, 3150, 2).distanceTo(local.tile()) < 6) {
							
								Method.interactO(75920, "Break", "Window");
								
						} else Method.clickOnMap(new Tile(3291, 3150, 2));
					}else
				if(new Tile(3282,3164,1).distanceTo(local.tile())<5 && ctx.game.floor()==1){
					Method.interactO(75919, "Climb","Object");
				}else
			if(new Tile(3268,3172,2).distanceTo(local.tile())<15 && ctx.game.floor()==2){
				if(new Tile(3272,3166,2).distanceTo(local.tile())<4){
					if((ctx.varpbits.varpbit(2449) &0xF) !=8){//this settings occurs after Osan hooks rope.
						while(ctx.varpbits.varpbit(1113)==5){
							Method.isChatting("Ozan");
						}
						Method.interactO(75914, "Leave it", "Rope");
						Method.sleep(3000);
					}else
					if(!ctx.objects.select().id(75917).nearest().first().isEmpty()){
						Method.state("Moving/moved");
						for(GameObject rope : ctx.objects.select().id(75917).nearest().first()){
						//	ctx.camera.setYaw(50);
							ctx.input.move(rope.tile().matrix(ctx).point(Random.nextDouble() * 0.6D - 4.2D,+0.60D,-1060));
							ctx.input.click(true);
						
						}
					}
						
				}else {
					Method.state("Moving to rope");
					Method.clickOnMap(new Tile(3272,3166,2));
				}
			}else
			if(new Tile(3270,3174,1).distanceTo(local.tile())<5 && ctx.game.floor()==1){
				Method.interactO(75913, "Climb","Object");
			}else
			if(new Tile(3271,3184,1).distanceTo(local.tile())<3 && ctx.game.floor()==1){
				Method.interactO(75911, "Bounce","Object");
			}else
			if(new Tile(3272,3187,1).distanceTo(local.tile())<5 && ctx.game.floor()==1){
				Method.interactO(75910, "Jump","Object");
			}else
			if(new Tile(3277,3187,1).distanceTo(local.tile())<5 && ctx.game.floor()==1){
				Method.interactO(75909, "Swing","Object");
			}else
			if(new Tile(3277,3190,2).distanceTo(local.tile())<5 && ctx.game.floor()==2){
				Method.interactO(75908, "Climb","Object");
			}else
			if(new Tile(3287,3191,3).distanceTo(local.tile())<5 && ctx.game.floor()==3){
			
				Method.interactO(75906, "Jump","Object");
			}else
			if(new Tile(3289,3188,2).distanceTo(local.tile())<5 && ctx.game.floor()==2){
				Method.interactO(75905, "Climb","Object");
			}else for(GameObject obj : ctx.objects.select().id(76369).nearest().first()){//finds and assigns the object
				if(obj.tile().distanceTo(local.tile())<6 &&ctx.players.local().tile().floor()==2){
						//^if distance to is less than 6 and if player is on plane 2
						Method.interactO(75572, "Cross","Object");
					}else break;
			}
			if(new Tile(3303,3186,1).distanceTo(local.tile())<5 && ctx.game.floor()==1){
				Method.interactO(75903, "Climb","Object");
			}else
			if(new Tile(3316,3175,1).distanceTo(local.tile())<15 && ctx.game.floor()==1){
				if(new Tile(3313,3185,1).distanceTo(local.tile())<3){
				Method.interactO(75902, "Walk across","Object");
				}else Method.clickOnMap(new Tile(3313,3185,1));
			}else
			if(new Tile(3317,3184,2).distanceTo(local.tile())<6 && ctx.game.floor()==2){
				Method.interactO(75901, "Slide","Object");
			}else
			if(new Tile(3321,3191,2).distanceTo(local.tile())<5 && ctx.game.floor()==2){//Top floor init.
				Method.interactO(75900, "Walk across","Object");
			}else
			if(new Tile(3322,3195,1).distanceTo(local.tile())<5 && ctx.game.floor()==1){
				Method.interactO(75898, "Climb","Object");
			}else if(new Tile(3323,3191,0).distanceTo(local.tile())<8){
			Method.interactO(76279, "Climb","Stairs");
			}else if(ctx.game.floor()==0)
				Method.clickOnMap(new Tile(3319,3193,0));
				
				
			}else if(!Method.isChatting("Ozan")){
				Method.speakTo(15862, "Ozan");
				Method.speakTo(15859, "Ozan");
			}
		}else if(new Tile(3296,3180,0).distanceTo(local.tile())<15 || Vars.DYNAMICV3){//Get ozan to follow us
			if(new Tile(3295,3181,0).distanceTo(local.tile())<5||Vars.DYNAMICV3){
				Vars.DYNAMICV2 = true;
				if(!Method.findOption(opt)){
				if(!Method.isChatting("Ozan")){
					Method.speakTo(15859, "Ozan");
				}
				}
			}else Method.clickOnMap(new Tile(3295,3181,0));
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToAlkharid, "Walking to Ozan", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV2 = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	private void cs10() {//Speak to the guard in Alkharid
		final String opt[] = {"Yes"};
		Player local = ctx.players.local();
		if((ctx.varpbits.varpbit(2449)>>23 & 0x3) ==3){//When Ozan is following us.(From alkharid)
			if(new Tile(3291, 3165, 0).distanceTo(local.tile())<8){
					if(!Method.isChatting("Guard")){
						Vars.DYNAMICV3 = true;
						Method.speakTo(15900, "Guard");
					}
				
			}else if(Vars.DYNAMICV2){
				Method.walking(pathToAlkharid, "Walking to Alkharid",false);
			}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
				Vars.DYNAMICV2 = true;
			}else if(ctx.widgets.component(1184,1).valid()){
				Method.isChatting("Ozan");
			}else {
				Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
				Method.sleep(1400);
			}
		}else if(new Tile(3296,3180,0).distanceTo(local.tile())<15){
			if(new Tile(3295,3181,0).distanceTo(local.tile())<5){
				Vars.DYNAMICV2 = true;
				if(!Method.findOption(opt)){
				if(!Method.isChatting("Ozan")){
					Method.speakTo(15859, "Ozan");
				}
				}
			}else Method.clickOnMap(new Tile(3295,3181,0));
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToAlkharid, "Walking to Ozan", false);
		}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV2 = true;
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
		
	}

	private void cs9() {//gets our player in the alkarid castle area from the shore
		
		if((ctx.varpbits.varpbit(2449)>>23 & 0x3) ==3){//Ozan begins to follow us again(when it becomes this setting)
			if(new Tile(3291, 3165, 0).distanceTo(ctx.players.local().tile())<8){
				System.out.println("but.. we're in range ..");
				Method.state("Within range");//We never actually get within range due to a setting change
			}else if(Vars.DYNAMICV2){
				Method.walking(pathToAlkharid, "Walking to Alkharid",false);
			}else if(TeleportLode.LUMMBRIDGE.getTile().distanceTo(ctx.players.local().tile())<10){
				Vars.DYNAMICV2 = true;
			}else teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
			
				
			
		}else {
			cs7();//Speak to ozan again so he can start following us
		}
	
	}

	public void teleportTo(int loc, String teleName) {
		
		System.out.println("Trying to teleport");
	if(!Method.isChatting("Ozan"))//to click through Ozan's extra teleporting dialogue
		if(!ctx.widgets.component(1184,1).valid()&&//for when Ozan's extra dialogue pops up during teleporting..
				ctx.widgets.component(1092,1).valid()&&//the widget for teleport selection
				   ctx.widgets.component(1092,0).visible()){
			//click the lummbridge teleport
			ctx.input.move(ctx.widgets.component(1092,loc).centerPoint());
			ctx.widgets.component(1092,loc).click(true);
			Method.sleep(2000);
			
		}else if(ctx.players.local().idle())
			ctx.widgets.component(1477,38).component(1).interact("Teleport");//teleport screen widget (by mini map)
		}
	
	private void cs8() {
		System.out.println("Here");
		if(!Method.teleporting &&Method.inventoryContains(25127)){
			cs7();
		}else if(!ctx.groundItems.select().id(25127).nearest().first().isEmpty()){
			Method.interactG(25127, "Take", "Scroll");
		}else cs7();
		
	}

	private void cs7() {
		
		final String opt[]  ={"Yes"};
		int drs[]  ={15897,15899,15898};
		Player local = ctx.players.local();
		
		if(Method.npcIsNotNull(15858)){
			if(new Tile(3128,3203,0).distanceTo(local.tile())<8){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Ozan")){
						Method.speakTo(15858, "Ozan");
					}
				}
			}else if(Vars.DYNAMICV){
				if(!Method.isChatting("People"))
				Method.walking(pathToShoreFL, "Walking to the shore", false);
			}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<10||TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
				Vars.DYNAMICV = true;
			}else if(Method.DraynorLodeIsActive()){
				Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
			}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
			
		}else if(Method.npcIsNotNull(15860)||Method.npcIsNotNull(15895)){//This is where the fighting actually occurs.
			if(Method.npcIsNotNull(15897) || Method.npcIsNotNull(15899) || Method.npcIsNotNull(15898)){
				try{
				for(int i =0;i<= drs.length;){
				if(Method.getInteractingNPC()!=null&&ctx.players.local().inCombat()){//if fighting
					Method.fightNPC(drs[i]);
				}else if(Method.npcIsNotNull(drs[i])){//one of the npcs
						Method.npcInteract(drs[i], "Attack");
					}else i++;
				}
				}catch(Exception e){e.printStackTrace();}
			}else if(!Method.isChatting("Ozan")){
				Method.speakTo(15860, "Ozan");
			}
		}else if(Vars.DYNAMICV){
			if(!Method.isChatting("People"))
			Method.walking(pathToShoreFL, "Walking to the shore", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<10||TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
		
	}

	private void cs6() {
		Player local = ctx.players.local();
		if((ctx.varpbits.varpbit(1113)&0x1)==1){
			Method.isChatting("People");
				
			
		}else if(Vars.DYNAMICV){
			if(!Method.isChatting("People"))
			Method.walking(pathToShoreFL, "Walking to the shore", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<10||TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	private void cs5() {
		Player local = ctx.players.local();
		System.out.println("DYNAMICV is currently: "+Vars.DYNAMICV);
		
		
		if((ctx.varpbits.varpbit(2449)>>22 &0x7) ==7){//Ozan is following us setting
			System.out.println("Ozan is now following us.");
			
			if( new Tile(3127, 3201, 0).distanceTo(local.tile())<2){
			//will never reach - beach cutscene starts
			}else if(Vars.DYNAMICV){
				if(new Tile(3127,3247,0).distanceTo(local.tile())<15){
					if(!ctx.objects.select().id(3434).nearest().first().isEmpty()){
						for(GameObject jailDoor : ctx.objects.select().id(3434).nearest().first()){
							if(JailDoor.contains(jailDoor.tile())){
								/*Our camera zooms WAY in during a cutscene
								 * making the door it needs to open off screen, we must zoom out by scrolling*/
								ctx.input.move(300,279);//the center of screen
								ctx.input.scroll(true);//true = scrolling out
								Method.interactO(3434, "Open", "Door");
							}else Method.walking(pathToShore, "Walking to the shore",false);
						}
					}else Method.walking(pathToShore, "Walking to the shore",false);
					
				}else Method.walking(pathToShore, "Walking to the shore",false);
			}else if(new Tile(3127,3245,0).distanceTo(local.tile())<10){
				Vars.DYNAMICV = true;
			}
		}else if(new Tile(3126,3245,0).distanceTo(local.tile())<7){
				for(GameObject jailDoor : ctx.objects.select().id(3434).nearest().first()){
					if(JailDoor.contains(jailDoor.tile())){
						Method.interactO(3434, "Open", "Door");
					}
				}
				if(!Method.isChatting("Ozan")){
					Method.speakTo(15857, "Ozan");
					Method.speakTo(15862, "Ozan");//id of the Ozan following us
				}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToJailHouse, "Walking to the Jailhouse", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<10 || TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	private void cs4() {
		
		Player local = ctx.players.local();
		final String opt[] = {"How about some sort","We'll find them","I'll lock","What will you","Yes"};
		
		if (new Tile(3125, 3245, 0).distanceTo(local.tile()) < 15) {
			if(Method.npcIsNotNull(15864)){
				
			if ((Method.getNPC(15864).tile().distanceTo(local.tile())<5)) {
				if(!Method.findOption(opt)){
					if(!Method.isChatting("People")){
						Method.speakTo(15864, "Khnum");
					}
				}
			} else if(Method.objIsByTile(new Tile(3128,3247,0), 3434, 5)){
				if(Method.getToTile(new Tile(3128,3247,0)))
				Method.interactO(3434, "Open", "Metal Door");
			}else Method.clickOnMap(Method.getNPC(15864).tile());
				}
			
		}else if(Vars.DYNAMICV){
			Method.walking(pathToHQ, "Walking to Khnum's HQ", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<10 || TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	private void cs3() {
		final String opt[] = {"Yes"};
		final String opt2[] = {""};
		Player local = ctx.players.local();
		if((ctx.varpbits.varpbit(1114)&0x3)==2){//Cutscene with speech occurs
			if (!Method.findOption(opt2)) {
				if (!Method.isChatting("People")) {
					Method.sleep(20);
				}
			}
		}else
		if((ctx.varpbits.varpbit(2449)>>21 & 0xF) ==15){//Outside the HQ in trailing scene(no longer trailing Khnum)
			if(!Method.isChatting("Ozan")){
			if(!ctx.objects.select().id(75896).nearest().first().isEmpty()){
				for(GameObject door : ctx.objects.select().id(75896).nearest().first()){
				if(door.tile().distanceTo(local.tile())<7){
			Method.interactO(75896, "Open", "Metal Door");
				}else {
					Method.state("Walking to the HQ");
					Method.clickOnMap(door.tile());
				}
			}
			}
			}
		}else if(new Tile(3094,3277,0).distanceTo(local.tile())<8){//Below will start the trailing scene
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Ozan")){
					Method.speakTo(15856, "Ozan");
				}
			}
		}else cs0();//Get the player to Ozan in Draynor
		
	}

	private void cs2() {//Trail Khnum to his jail
	
		
		Player local = ctx.players.local();
		final String opt[] = {"Yes"};
		if((ctx.varpbits.varpbit(2449)>>21 & 0xF) ==15){//The setting change when we enter the trailing scene
			if(!Method.isChatting("Ozan")){
			if(!ctx.npcs.select().id(15869).nearest().first().isEmpty()){//khnum
				//Method.state("Distance differential:"+(NPCs.getNearest(15869).tile().getMapPoint().x - (Players.local().tile().getMapPoint().x)));
				for(Npc k : ctx.npcs.select().id(15869).nearest().first()){//khnums following ID
					
				if (k.tile().distanceTo(local.tile()) > 7) {
						Method.state("Following..");
						Method.clickOnMap(k.tile());
					} else {//if we are too close to knum - back up a bit
						Method.state("Easing off..");
						int differ = ctx.players.local().tile().matrix(ctx).mapPoint().x - k.tile().matrix(ctx).mapPoint().x;
						System.out.println("Differ: "+ differ);
						if(differ < 0 ){
							ctx.input.click(ctx.players.local().tile().matrix(ctx).mapPoint().x  + differ, ctx.players.local().tile().matrix(ctx).mapPoint().y,true);
						}else ctx.input.click(ctx.players.local().tile().matrix(ctx).mapPoint().x, ctx.players.local().tile().matrix(ctx).mapPoint().y - differ,true);
						
					}
				}
			}
			}
		}else if(new Tile(3094,3277,0).distanceTo(local.tile())<8){//Below will start the trailing scene
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Ozan")){
					Method.speakTo(15856, "Ozan");
				}
			}
		}else cs0();//Get the player to Ozan in Draynor
		
	}

	private void cs1() {//Speak to Khnum in his hide-out, become a member of skulls, speak to Ozan again afterwards
		Player local = ctx.players.local();
		final String opt[] = {"I'll be back soon","Let me in or I'll poke your"};
		final String opt2[] = {"Take their","Fake their","Kick them in the","What jobs are","Tell me what","Crystal"};
		
		//if inside the gang's base
		if(!ctx.npcs.select().id(15863).nearest().first().isEmpty()){//The ID of Knuhm - gang leader in basement
			for(Npc Khunm : ctx.npcs.select().id(15863).nearest().first()){
			if(Khunm.tile().distanceTo(local.tile())<7){
				if(!Method.findOption(opt2)){
					if(!Method.isChatting("Khnum")){
						Method.speakTo(15863, "Khnum");
					}
				}
			}else Method.clickOnMap(Khunm.tile());
			}
		}else if(new Tile(3093,3274,0).distanceTo(local.tile())<7){//Below enters Khunm's base
							
						if (!Method.findOption(opt)) {
							if (!Method.isChatting("Bouncer")) {
								Method.interactO(75852, "Open", "Trapdoor");
							}
						}
			
			
		}else cs0();//Get the player to the location by the criminals base
		
	}

	private void cs0() {//Speaks to Ozan initially and begins the quest
		Player local = ctx.players.local();
		
		final String opt[] ={"find the","Talk about finding the HQ","No, I'm ready."};
		if(!ctx.objects.select().id(52289).nearest().first().isEmpty()){//If the player is downstairs(Khnum's base)
				for(GameObject ladder : ctx.objects.select().id(75851).nearest().first()){
				if(ladder.tile().distanceTo(local.tile())<7){
					Method.interactO(ladder.id(), "Climb", "Ladder");
				}else Method.clickOnMap(ladder.tile());
				}
			
		}else
		if(new Tile(3093, 3273, 0).distanceTo(local.tile())<8){
			Method.skipPics();
			if(!Method.startQuestOpen())
			if(!ctx.objects.select().id(1239).nearest().first().isEmpty()){
				for(GameObject thugDoor : ctx.objects.select().id(1239).nearest().first()){
					if(!ThugDoor.contains(thugDoor.tile())){
						if (!Method.findOption(opt)) {
							if (!Method.isChatting("Ozan")) {
								Method.speakTo(15856, "Ozan");
							}
						}
				  }else Method.interactO(1239, "Open", "Door");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOzanDraynor, "Walking to Ozan", false);
		}else if(TeleportLode.DRAYNOR.getTile().distanceTo(local.tile())<10 || TeleportLode.LUMMBRIDGE.getTile().distanceTo(local.tile())<10){
			Vars.DYNAMICV = true;
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(TeleportType.DRAYNOR.getTeleport(),TeleportType.DRAYNOR.getName());
		}else Method.teleportTo(TeleportType.LUMBRIDGE.getTeleport(),TeleportType.LUMBRIDGE.getName());
		
	}

	public boolean activate() {
		return (DeltaQuester.scriptToStart==8);
		
	}


}
