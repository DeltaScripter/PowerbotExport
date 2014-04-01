/*package quests;

import org.powerbot.script.Filter;
import org.powerbot.script.rs3.ClientContext;
import org.powerbot.script.rs3.Component;
import org.powerbot.script.rs3.GameObject;
import org.powerbot.script.rs3.Hud.Window;
import org.powerbot.script.rs3.Item;
import org.powerbot.script.rs3.ItemQuery;
import org.powerbot.script.rs3.MobileIdNameQuery;
import org.powerbot.script.rs3.Tile;

import lodestoneActivator.Data.TeleportLode;
import lodestoneActivator.Data.TeleportType;



public class TowerOfLife extends Node{

	public TowerOfLife(ClientContext ctx) {
		super(ctx);
	}
	public final Tile[] pathToEffigy = {
			new Tile(2637, 3335, 0),
			new Tile(2648, 3325, 0),
			new Tile(2654, 3313, 0),new Tile(2649, 3306, 0),
			new Tile(2638, 3300, 0),
			new Tile(2643, 3290, 0),new Tile(2641, 3281, 0),
			new Tile(2641, 3272, 0),new Tile(2637, 3260, 0),
			new Tile(2635, 3251, 0),new Tile(2634, 3243, 0),
			new Tile(2636, 3233, 0),new Tile(2639, 3223, 0),
			new Tile(2631, 3183, 0),new Tile(2641, 3219, 0)};
	public int items[] = {0,0,0,0};
	Method Method = new Method(ctx);
	Vars Vars  = new Vars();
	@Override
	public boolean activate() {
		return DeltaQuester.scriptToStart==40;
	}
	public boolean init = false;
	
	public boolean hasParts1 = false;
	public boolean hasParts2 = false;
	public boolean hasParts3 = false;
	@Override
	public void execute() {
		Method.setGeneralCamera();//get the camera pitch for general use on quests
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1362){
			Method.state("The Tower of Life quest has been completed");
			//ctx.environment.sleep(2000);
			DeltaQuester.e = true;
		}else	
		if(!init){
			if(ctx.hud.view(Window.WORN_EQUIPMENT)){
				ItemQuery<Item> t = ctx.equipment.select();
				for(Item item: t){
					if(item.getId()==10862){//helmet
						System.out.println("Found helmet");
						items[0]=1;
					}else if(item.getId()==10863){//shirt\
						System.out.println("Found shirt");
						items[1]=1;
					}else if(item.getId()==10865){//boots
						System.out.println("Found boots");
						items[2]=1;
					}else if(item.getId()==10864){//trouser
						System.out.println("Found trousers");
						items[3]=1;
					}
				}
				init = true;
			}
		}else
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1361){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
			hasParts3 = true;//so we don't grab parts we no longer need
			cs9();//speak to effigy yet again, at the base of the tower
		}else	
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1360){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
			hasParts3 = true;//so we don't grab parts we no longer need
			cs1();//speak to effigy yet again, at the base of the tower
		}else	
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1356||
		(ctx.varpbits.varpbit(2190)&0x7FF)==1358){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
			hasParts3 = true;//so we don't grab parts we no longer need
			cs7();
		}else		
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1355){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
			hasParts3 = true;//so we don't grab parts we no longer need
			cs1();//speak to effigy again, at the base of the tower
		}else	
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1354){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
			hasParts3 = true;//so we don't grab parts we no longer need
			cs7();//head up to the top of the tower initially
		}else		
		if((ctx.varpbits.varpbit(2190)&0x7FF)==1352){
			cs1();//Tell Effigy about the tower
		}else	
		if((ctx.varpbits.varpbit(2190)&0x3FF)==840){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
			hasParts3 = true;//so we don't grab parts we no longer need
		if(!Method.isChatting(""))
		cs6();//solve the puzzle # 3
		}else	
		if((ctx.varpbits.varpbit(2190)&0x1FF)==328){
			hasParts1 = true;//so we don't grab parts we no longer need
			hasParts2 = true;//so we don't grab parts we no longer need
		if(!Method.isChatting(""))
		cs6();//gather all parts for third machine
		}else	
		if((ctx.varpbits.varpbit(2190)&0xFF)==200){
				hasParts1 = true;//so we don't grab parts we no longer need
				hasParts2 = true;//so we don't grab parts we no longer need
			if(!Method.isChatting(""))
			cs5();//Caliberate the second machine.
		}else	
		if((ctx.varpbits.varpbit(2190)&0x7F)==72){
			hasParts1 = true;//so we don't grab parts we no longer need
			if(!Method.isChatting(""))
			cs5();//gather the parts and put them in the machine
		}else	
		if((ctx.varpbits.varpbit(2190)&0x3F)==40){
			hasParts1 = true;//so we don't grab parts we no longer need
			if(!Method.isChatting(""))
			cs4();//caliberate the first machine
		}else	
		if((ctx.varpbits.varpbit(2190)&0xF)==6 ||(ctx.varpbits.varpbit(2190)&0xF)==8){
			if(!Method.isChatting(""))
			cs4();//Gather the parts and fix the first machine
		}else
        if((ctx.varpbits.varpbit(2190)&0x7)==4){
			cs3();//Gather all the cloth items & speak to Bonafido
		}else
		if((ctx.varpbits.varpbit(2190)&0x3)==2){
			cs2();//Speak to Bonafido
		}else cs1();//speak to effigy and start quest
		
	}

	private void cs9() {
		if(Method.objIsNotNull(21904)){//downstairs
			if(getToObject(21904)){
					if(!Method.isChatting("Creature")){
						Method.npcInteract(5581, "Talk-to");
					}
			}
		}else
		if(new Tile(2646,3220,0).getMatrix(ctx).isReachable()){
			if(getToObject(21944)){
				Method.interactO(21944, "", "Trapdoor");
			}
		}else cs6();//get into the area
		
	}

	private void cs7() {
		final String[] opt = {"Sure","Take a rune","People mix","Run,",
				"Bury them","Fletching","By ignition","Get some logs"};
		//ctx.antipatterns.setEnabled(false);
		while(ctx.varpbits.varpbit(1113)!=0){
			Vars.DYNAMICV = false;
			Method.state("Cutscene");
			Method.pressContinue();
		}
		Method.skipPics();
		if(ctx.game.floor()==3){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
				if(!Method.isChatting("Creature")){
					Method.npcInteract(5599, "Talk-to");
				}
		}else cs6();
		
	}

	private void cs6() {
		final String opt[] = {"Yes"};
		
		if(hasParts3){
			if(ctx.game.floor()==3){
				Vars.DYNAMICV = false;
				if(!Method.findOption(opt))
					if(ctx.widgets.get(509,0).isVisible()){//puzzle itnerface
						solvePuzzle3();
					}else
				if(ctx.widgets.get(1186,0).isVisible()){
					Method.pressContinue();
				}else
				Method.interactO(21941, "Fix", "");//fix cage, put parts in..
			}else if(ctx.game.floor()==2){
				if(getToObject(17974)){
					Method.interactO(17974, "Climb-up", "Ladder");
				}
			}else if(ctx.game.floor()==1){
				if(getToObject(21871)){
					Method.interactO(21871, "Climb-up", "Stairs");
				}
			}else cs4();//get upstairs
		}else if(ctx.game.floor()==2){
			if(getToObject(21872)){
				Method.interactO(21872, "Climb-down", "Stairs");
			}
		}else if(ctx.game.floor()==1){
			if(getToObject(21872)){
				Method.interactO(21872, "Climb-down", "Stairs");
			}
		}else if(new Tile(2646,3220,0).getMatrix(ctx).isReachable()){//base of tower
			
			if(Method.inventoryGetCount(10870)>=4&&//fluid
						Method.inventoryGetCount(10876)>=5){//metal bars
					hasParts3 =true;
				}else
			if(Method.inventoryGetCount(10870)<4){//fluid
				gatherPart(21915);//box of fluid
			}else
			if(Method.inventoryGetCount(10876)<5){//metal bars
				gatherPart(21908);//box of metal bars
			}
			
			
		}else if(new Tile(2649,3226,0).distanceTo(ctx.players.local().getLocation())<7){
			Method.interactO(21814, "Open", "Door");//tower door outside
		}else cs2();//get to loc
	}

	private void solvePuzzle3() {
		Component horizontal = ctx.widgets.get(509,19);
		Component vertical = ctx.widgets.get(509,21);
		Component plus = ctx.widgets.get(509,27);
		Component minus = ctx.widgets.get(509,25);
		Component placeBar = ctx.widgets.get(509,13);
		Component rightButton = ctx.widgets.get(509,11);
		Component leftButton = ctx.widgets.get(509,9);
		
		Component length = ctx.widgets.get(509,30);
		
		if(ctx.widgets.get(509,40).getModelId()==22480){//fixed bar one(upperright)
			System.out.println("Here");
			if(ctx.widgets.get(509,42).getModelId()==22486){
				System.out.println("Here2");
				if(ctx.widgets.get(509,41).getModelId()==22483){
					System.out.println("Here3");
					if(ctx.widgets.get(509,45).getModelId()==22494){//next side here<
						System.out.println("Here4");
						if(ctx.widgets.get(509,44).getModelId()==22491){
							System.out.println("Here5");
							if(ctx.widgets.get(509,46).getModelId()==22497){
							
								if(ctx.widgets.get(509,48).getModelId()==22502){
									if(ctx.widgets.get(509,49).getModelId()==22505){
										if(ctx.widgets.get(509,50).getModelId()==22508){
											
											if(ctx.widgets.get(509,36).getModelId()==22469){
												if(ctx.widgets.get(509,37).getModelId()==22472){
													if(ctx.widgets.get(509,37).getModelId()==22475){
														Method.state("All done!");
													}else if(ctx.widgets.get(509,36).isVisible()){//proper side
															putBar(2,false);//false == vertical
														}else rightButton.click();
												}else if(ctx.widgets.get(509,36).isVisible()){//proper side
														putBar(2,true);//false == vertical
													}else rightButton.click();
											}else if(ctx.widgets.get(509,36).isVisible()){//proper side
													putBar(2,true);//false == vertical
												}else rightButton.click();
											
										}else if(ctx.widgets.get(509,50).isVisible()){//proper side
												putBar(3,false);//false == vertical
											}else rightButton.click();
									}else if(ctx.widgets.get(509,49).isVisible()){//proper side
											putBar(2,false);//false == vertical
										}else rightButton.click();
								}else if(ctx.widgets.get(509,48).isVisible()){//proper side
										putBar(4,true);//false == vertical
									}else rightButton.click();
								
							}else if(ctx.widgets.get(509,45).isVisible()){//proper side
									putBar(2,true);//false == vertical
								}else rightButton.click();
						}else if(ctx.widgets.get(509,45).isVisible()){//proper side
								putBar(2,false);//false == vertical
							}else rightButton.click();
					}else
					if(ctx.widgets.get(509,45).isVisible()){//next side
						putBar(4,false);//false == vertical
					}else rightButton.click();
				}else putBar(3,true);//false == vertical
			}else putBar(2,true);//false == vertical
		}else putBar(2,false);//false == vertical
		
	}

	private void putBar(int lengthText, boolean hori) {
		Component horizontal = ctx.widgets.get(509,19);
		Component vertical = ctx.widgets.get(509,21);
		Component plus = ctx.widgets.get(509,27);
		Component minus = ctx.widgets.get(509,25);
		Component placeBar = ctx.widgets.get(509,13);
		Component rightButton = ctx.widgets.get(509,11);
		Component leftButton = ctx.widgets.get(509,9);
		
		Component length = ctx.widgets.get(509,30);
		
		if(hori){
			if(ctx.widgets.get(509,54).isVisible()){//is horizontal visible
				if(Integer.parseInt(length.getText())==lengthText){
					Method.state("Placing bar");
					placeBar.click();
				}else if(Integer.parseInt(length.getText())<lengthText){
					Method.state("Increasing size");
					plus.click();
					//ctx.game.sleep(1200);
				}else {
					Method.state("Decreasing size");
					minus.click();
					//ctx.game.sleep(1200);
				}
			}else {
				Method.state("Clicking horizontal");
				horizontal.click();
			}
		}else
		if(ctx.widgets.get(509,55).isVisible()){//is vertical visible
			if(Integer.parseInt(length.getText())==lengthText){
				Method.state("Placing bar");
				placeBar.click();
			}else if(Integer.parseInt(length.getText())<lengthText){
				Method.state("Increasing size");
				plus.click();
				//ctx.game.sleep(1200);
			}else {
				Method.state("Decreasing size");
				minus.click();
				//ctx.game.sleep(1200);
			}
		}else {
			Method.state("Clicking vertical");
			vertical.click();
		}
		
	}

	private void cs5() {
		Tile local = ctx.players.local().getLocation();
		final String opt[] = {"Yes"};
		if(hasParts2){
			if(ctx.game.floor()==2){
				if(!Method.findOption(opt))
				if(getToObject(21943)){//machine
					if(ctx.widgets.get(1186,0).isVisible()){
						Method.pressContinue();
					}else solvePuzzle2();
					
				}
			}else if(ctx.game.floor()==1){
				 if(Method.objIsNotNull(21871)){//stairs
					  if(Method.getObject(21871).getLocation().distanceTo(local)<6){
						  Method.interactO(21871, "Climb-up", "Stairs");
					  }else Method.clickOnMap(Method.getObject(21871).getLocation());
				  }
			}else cs4();//get to first floor
		}else if(ctx.game.floor()==1){//if upstairs, climb downstairs
			if(Method.objIsNotNull(21872)){//stairs
				if(Method.getObject(21872).getLocation().distanceTo(local)<6){
					Method.interactO(21872, "Climb-down", "Stairs");
				}else Method.clickOnMap(Method.getObject(21872).getLocation());//stair loc
			}
		}else
		if(new Tile(2646,3220,0).getMatrix(ctx).isReachable()){//base tower
			
			if(Method.inventoryStackSize(10866)>=6&&//rivets
				 Method.inventoryGetCount(10871)>=4&&//pipes
					Method.inventoryGetCount(10872)>=5){//rings
				hasParts2 =true;
			}else
			if(Method.inventoryStackSize(10866)<6){//rivets
				gatherPart(21911);//box of rivets
			}else if(Method.inventoryGetCount(10871)<4){//metal pips
				gatherPart(21909);//box of pipes
			}else if(Method.inventoryGetCount(10872)<5){//metal rings
				gatherPart(21910);//box of rings
			}
			
		}else if(new Tile(2649,3226,0).distanceTo(ctx.players.local().getLocation())<7){
			Method.interactO(21814, "Open", "Door");//tower door outside
		}else cs2();//get to loc
	}

	private void solvePuzzle2() {
		int xDist;
		int yDist;
		Component base = ctx.widgets.get(511,2);
		Component downButton = ctx.widgets.get(511,35);//down butt
		Component upButton = ctx.widgets.get(511,33);//up butt
		Component leftButton = ctx.widgets.get(511,29);//left butt
		Component rightButton = ctx.widgets.get(511,31);//right butt
		Component rotateButton = ctx.widgets.get(511,9);
		
		Component smallBentPipe = ctx.widgets.get(511,8);
		Component largeBentPipe = ctx.widgets.get(511,7);
		Component straightPipe = ctx.widgets.get(511,5);
		Component oneHoleOnSidePipe = ctx.widgets.get(511,6);
		Component largestPipe = ctx.widgets.get(511,4);
		
		if((ctx.varpbits.varpbit(2190)>>7&0x1)==1){//after putting parts in machine
			Method.state("Solving Puzzle");
			if(ctx.widgets.get(511,0).isVisible()){//puzzle interface
				System.out.println("Y position: " + (base.getBoundingRect().y - smallBentPipe.getBoundingRect().y));
				System.out.println("X position: " + (base.getBoundingRect().x - smallBentPipe.getBoundingRect().x));

				
				
				if(largestPipe.getModelId()==22538){//LargestPipe fitted
					if(oneHoleOnSidePipe.getAbsoluteLocation().x==395&&
							oneHoleOnSidePipe.getAbsoluteLocation().y==194){//oneholeonside fitted
						if(largeBentPipe.getAbsoluteLocation().x==377&&
								largeBentPipe.getAbsoluteLocation().y==286){//large bent pipe fitted
						
							if(straightPipe.getAbsoluteLocation().x==227&&
									straightPipe.getAbsoluteLocation().y==195){//straight pipe fitted
							
								if((ctx.varpbits.varpbit(2190)>>12&0x1)==1){//small bent pipe selected
									 yDist = base.getBoundingRect().y - smallBentPipe.getBoundingRect().y;
									 xDist = base.getBoundingRect().x - smallBentPipe.getBoundingRect().x;
									setY(-99,-100,yDist,xDist);//the the straight pipe
								}else {
									smallBentPipe.click();
									//ctx.game.sleep(200,500);
								}
								
							}else if((ctx.varpbits.varpbit(2190)>>11&0x1)==1){//two-hole pipe on side selected
								 yDist = base.getBoundingRect().y - straightPipe.getBoundingRect().y;
								 xDist = base.getBoundingRect().x - straightPipe.getBoundingRect().x;
								setY(-96,-43,yDist,xDist);//the the straight pipe
							}else {
								straightPipe.click();
								//ctx.game.sleep(200,500);
							}
							
						}else
						
						if((ctx.varpbits.varpbit(2190)>>15&0x1)==1){//selected bent pipe
							 yDist = base.getBoundingRect().y - largeBentPipe.getBoundingRect().y;
							 xDist = base.getBoundingRect().x - largeBentPipe.getBoundingRect().x;
							setY(-187,-193,yDist,xDist);//the the large bent pipe
						}else {
							largeBentPipe.click();
							//ctx.game.sleep(200,500);
						}
					}else
					if((ctx.varpbits.varpbit(2190)>>14&0x1)==1){//oneholeonesidepipe selected
						 yDist = base.getBoundingRect().y - oneHoleOnSidePipe.getBoundingRect().y;
						 xDist = base.getBoundingRect().x - oneHoleOnSidePipe.getBoundingRect().x;
						setY(-97,-211,yDist,xDist);//the the largestPipe
					}else {
						oneHoleOnSidePipe.click();
						//ctx.game.sleep(200,500);
					}
				}else//set the largestPipe
				if((ctx.varpbits.varpbit(2190)>>13&0x1)==1){//selected largestPipe
					 yDist = base.getBoundingRect().y - largestPipe.getBoundingRect().y;
					 xDist = base.getBoundingRect().x - largestPipe.getBoundingRect().x;
					if(largestPipe.getModelId()==22547){//for rotation of the pipe
					setY(-101,-89, yDist,xDist);//the the largestPipe
				  }else{
					  rotateButton.click();
					  //ctx.game.sleep(300,500);
				  }
					 }else {
					largestPipe.click();
					//ctx.game.sleep(1200,1400);
				}
				
			}else Method.interactO(21943, "Fix", "Machine");//opens up the screen
			
		}else Method.interactO(21943, "Fix", "Machine");
		
	}

	private boolean setY(int i, int x, int yDist, int xDist) {
		Component base = ctx.widgets.get(511,2);
		Component downButton = ctx.widgets.get(511,35);//down butt
		Component upButton = ctx.widgets.get(511,33);//up butt
		Component leftButton = ctx.widgets.get(511,29);//left butt
		Component rightButton = ctx.widgets.get(511,31);//right butt
		
		Component smallBentPipe = ctx.widgets.get(511,8);
		Component largeBentPipe = ctx.widgets.get(511,7);
		Component straightPipe = ctx.widgets.get(511,5);
		Component oneHoleOnSidePipe = ctx.widgets.get(511,6);
		Component largestPipe = ctx.widgets.get(511,4);
		
		if(leftButton.isValid()&&upButton.isValid()&&rightButton.isValid()&&downButton.isValid())
		if(yDist<i+8&& yDist>i-8){
			if(xDist==x){
				return true;
			}else
			if(xDist<x){
				leftButton.click();
			}else{
				rightButton.click();
			}
		}else if(yDist<i){
			upButton.click();
		}else{
			downButton.click();
		}
		return false;
	}

	private boolean getToObject(int i) {
	    if(Method.objIsNotNull(i)){
	    	if(Method.getObject(i).getLocation().distanceTo(ctx.players.local().getLocation())<7){
	    		return true;
	    	}else Method.clickOnMap(Method.getObject(i).getLocation());
	    }
		return false;
	}

	private void cs4() {//
		Tile local = ctx.players.local().getLocation();
		final String opt[] = {"Yes"};
		if(hasParts1){
			if(ctx.game.floor()==1){
				if((ctx.varpbits.varpbit(2190)&0x3F)==40){//if put parts in machine..
					solvePuzzle1();
				}else	//put the parts in the machine..
				  if(Method.objIsNotNull(21873)){//machine
					  if(!Method.findOption(opt))
					 if(ctx.widgets.get(1186,0).isVisible()){
						 Method.pressContinue();
					 }else
					  if(!Method.isChatting("Game"))
					  if(Method.getObject(21873).getLocation().distanceTo(local)<6){
						  Method.interactO(21873, "Fix", "Machine");
					  }else Method.clickOnMap(Method.getObject(21873).getLocation());
				  }
				
			}else if(new Tile(2649,3222,0).getMatrix(ctx).isReachable()){//inside tower
			  if(Method.objIsNotNull(21871)){//stairs
				  if(Method.getObject(21871).getLocation().distanceTo(local)<6){
					  Method.interactO(21871, "Climb-up", "Stairs");
				  }else Method.clickOnMap(Method.getObject(21871).getLocation());
			  }
			}else if(new Tile(2649,3226,0).distanceTo(ctx.players.local().getLocation())<7){
				Method.interactO(21814, "Open", "Door");//tower door outside
			}else cs2();//get to loc
		}else
		if(new Tile(2649,3222,0).getMatrix(ctx).isReachable()){//inside tower
		
			if(Method.inventoryGetCount(10874)>=4&&//coloured balls
					Method.inventoryGetCount(10873)>=3&&//metal sheets
					Method.inventoryGetCount(10875)>=4){//valve wheel
				hasParts1 =true;
			}else if(Method.inventoryGetCount(10874)<4){//coloured balls
				gatherPart(21914);
			}else if(Method.inventoryGetCount(10873)<3){//metal sheets
				gatherPart(21913);
			}else if(Method.inventoryGetCount(10875)<4){//valve wheel
				gatherPart(21912);
			}
		}else if(new Tile(2649,3226,0).distanceTo(ctx.players.local().getLocation())<7){
			Method.interactO(21814, "Open", "Door");//tower door outside
		}else cs2();//get to loc
		
	}

	private void solvePuzzle1() {
		 if(ctx.widgets.get(1186,0).isVisible()){
			 Method.pressContinue();
		 }else
		if(ctx.widgets.get(510,1).isVisible()){//puzzle screen
			if((ctx.varpbits.varpbit(2190)>>18&0x1)==1){//plugged first hole
				if((ctx.varpbits.varpbit(2190)>>19&0x1)==1){//plugged second hole
					if((ctx.varpbits.varpbit(2190)>>20&0x1)==1){//plugged third hole
						if((ctx.varpbits.varpbit(2190)>>21&0x1)==1){//plugged fourth hole
							if((ctx.varpbits.varpbit(2190)>>28&0x7)==5){//fixed some pipes
								if((ctx.varpbits.varpbit(2190)>>25&0x7)==5){
									if((ctx.varpbits.varpbit(2191)>>3&0x1)==0){//left lever is up
										Method.state("Performing last step");
										ctx.widgets.get(510,83).click();//first wheel right
										//ctx.game.sleep(1200,1600);
									}else {
										ctx.widgets.get(510,160).click();//flick left lever up
									}
							
								}else {
									ctx.widgets.get(510,85).click();//second wheel right
									//ctx.game.sleep(1200,1600);
								}
							}else
							 if((ctx.varpbits.varpbit(2191)&0x7)==5){//fixed 4th pipe
								 System.out.println("Here5");
								if((ctx.varpbits.varpbit(2191)>>4&0x1)==0){//right lever is up
									ctx.widgets.get(510,87).click();//third wheel right
									//ctx.game.sleep(1200,1600);
								}else ctx.widgets.get(510,161).click();//flick right lever up
							}else {
								ctx.widgets.get(510,88).click();//fourth wheel right
								//ctx.game.sleep(1200,1600);
							}
							 
						}else if((ctx.varpbits.varpbit(2191)&0x7)==4){//ready to turn left
							ctx.widgets.get(510,89).click();//fourth wheel left
							//ctx.game.sleep(1200,1600);
						}else if((ctx.varpbits.varpbit(2191)>>4&0x1)==1){//right lever down
							ctx.widgets.get(510,88).click();//fourth wheel right
							//ctx.game.sleep(1200,1600);
						}else ctx.widgets.get(510,119).click();//flick right-lever down
					}else//plug third hole 
						if((ctx.varpbits.varpbit(2190)>>28&0x3)==3){//ready to turn wheel left
						ctx.widgets.get(510,86).click();//third wheel left
						//ctx.game.sleep(1200,1400);
					}else {
						ctx.widgets.get(510,87).click();//third wheel right
						//ctx.game.sleep(1200,1400);
					}
				}else//plug second hole
			    if((ctx.varpbits.varpbit(2191)>>3&0x1)==1){//if left lever down.
				  if((ctx.varpbits.varpbit(2191)>>4&0x1)==0){//right lever up
					  ctx.widgets.get(510,84).click();//second wheel left
						//ctx.game.sleep(1200,1500);
				  }else ctx.widgets.get(510,161).click();//flick right lever up
			  }else ctx.widgets.get(510,118).click();//flick left lever down
			
			}else//plug first hole
			if((ctx.varpbits.varpbit(2191)>>4&0x1)==1){//right lever down.
				ctx.widgets.get(510,82).click();//first wheel left
				//ctx.game.sleep(1200,1500);
			}else ctx.widgets.get(510,119).click();//flick the right lever down
		}else Method.interactO(21873, "Fix", "Machine");
		
	}

	private void gatherPart(int id) {//finds stuff in crates
		Tile local = ctx.players.local().getLocation();
		if(Method.objIsNotNull(id)){
			if(Method.getObject(id).getLocation().distanceTo(local)<6){
				Method.state("Searching crate..");
				if(ctx.players.local().isIdle()){
					Method.interactO(id, "Search","Crate");
				}
			}else Method.clickOnMap(Method.getObject(id).getLocation());
		}
		
	}

	private void cs3() {//gather all the cloth
		String[] opt = {"10 clay pieces","Torn curtains","Three"};//no option yet
		Tile local = ctx.players.local().getLocation();
		final int[] itemList = {10862,10863,10864,10865};
		final Tile bushTile = new Tile(2656,3213,0);
	
		if(items[2]==1){
			while(Method.inventoryContains(10865)){//boots
				Method.interactInventory(10865, "Wear", "Boots");
			}
			if(items[3]==1){//if you have the trousers, located in bush
				while(Method.inventoryContains(10864)){//trousers
					Method.interactInventory(10864, "Wear", "Trousers");
				}
				cs2();//speak to bonafido again with all the clothes
			}else if(Method.inventoryContains(10864)){//trouser ID
				items[3]=1;//if you have the trousers
			}else if(bushTile.distanceTo(local)<7){//bush area
				
				MobileIdNameQuery<GameObject> findBush =ctx.objects.select().select(new Filter<GameObject>() {
					public boolean accept(GameObject g) {
						return g.getLocation().distanceTo(bushTile)<=1&& g.getId()==21924;
					}
			         });
				
					for(GameObject bush: findBush){
					   if(ctx.players.local().isIdle())
						bush.interact("Search");
					}
			}else if(new Tile(2657,3213,0).distanceTo(local)<20){//bush loc
				ctx.movement.findPath(new Tile(2657,3213,0)).traverse();//bush loc
			}else cs2();//get to location
		}else
		if(new Tile(2649,3227, 0).distanceTo(ctx.players.local().getLocation())<7){//tower area
			if(items[0]==1){//has helmet
				while(Method.inventoryContains(10862)){//helmet
					Method.interactInventory(10862, "Wear", "Helmet");
				}
				if(items[1]==1){//has shirt
					while(Method.inventoryContains(10863)){//shirt
						Method.interactInventory(10863, "Wear", "Shirt");
					}
					if(items[2]==1){//has boots
					//err..ingore this part..taken care of up there ^
					}else if(Method.inventoryContains(10865)){//boots ID
						items[2]=1;//if you have the boots
					}else if((ctx.varpbits.varpbit(2191)>>26&0x1)==1){//occurs after speaking to him initially
						Method.npcInteract(5590, "Pickpocket");
					}else if(!Method.findOption(opt))//speak to him initially
						if(!Method.isChatting("No fingers")){
							Method.npcInteract(5590, "Talk-to");
						}
				}else if(Method.inventoryContains(10863)){//shirt ID
					items[1]=1;//if you have the shirt
				}else if(!Method.findOption(opt))//get the shirt
					if(!Method.isChatting("Pop-eye")){
						Method.npcInteract(11253, "Talk-to");
					}
			}else if(Method.inventoryContains(10862)){//helmet ID
				items[0]=1;//if you have the helmet
			}else if(!Method.findOption(opt))//get the helmet
					if(!Method.isChatting("Black-eye")){
						Method.npcInteract(5589, "Talk-to");
					}
			
		}else if(new Tile(2649,3227, 0).distanceTo(ctx.players.local().getLocation())<25){
			ctx.movement.findPath(new Tile(2649,3227, 0)).traverse();
		}else cs2();//get to location
		
		
	}

	private void cs2() {//speak to bonafido
		String[] opt = {"Carry on","Your legs","Whistle for","Tea"};//no option yet
		
		if(new Tile(2649,3227, 0).distanceTo(ctx.players.local().getLocation())<7){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
			if(!Method.isChatting("Bonafido")){
				Method.npcInteract(5580, "Talk-to");//Bonafido
			}
		}else if(new Tile(2649,3227,0).distanceTo(ctx.players.local().getLocation())<20){
			ctx.movement.findPath(new Tile(2649,3227,0)).traverse();//to bonafido location
		}else if(Vars.DYNAMICV){
			Method.walking(pathToEffigy, "Walking to Bonafido", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(ctx.players.local().getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
	}
	private void cs1() {//speak to effigy
		String[] opt = {"Sure"};
		
		if(new Tile(2641, 3219, 0).distanceTo(ctx.players.local().getLocation())<7){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt))
			if(!Method.isChatting("Effigy")){
				Method.npcInteract(5598, "Talk-to");//effigy
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToEffigy, "Walking to Effigy", false);
		}else if(TeleportLode.ARDOUGNE.getTile().distanceTo(ctx.players.local().getLocation())<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(TeleportType.ARDOUGNE.getTeleport(), "Ardougne");
		
	}

}
*/