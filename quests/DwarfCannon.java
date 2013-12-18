/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.ChatOptions;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class DwarfCannon extends Node {

	public int stepX2;
	public boolean eStepX = true;
	public boolean eStepX2 = false;
	public boolean doSpecial = false;
	public boolean hBOG1 = false;
	public boolean w1 = false;
	public boolean u1 = false;
	public boolean oC1 = false;
	public boolean bL1 = false;
	public boolean hBAL = false;
	public boolean cALode = false;
	public int railNum = 1;
	public final int CAPTAIN_ID = 208;
	public final int FRIENDREMAINS_ID = 0;
	public final int WATCHTOWERLADDERBASE_ID = 1747;
	public final int[] RAIL = {15595,15594,15593,15592,15591,15590};
	public final int R5 = 15591;
	public final int R3 = 15593;
	public final int R2 = 15594;
	public final int R4 = 15592;
	public final int R6 = 15590;

	public final Area captainLawGofArea = new Area(new Tile[] {
			new Tile(2556, 3454, 0), new Tile(2579, 3454, 0),
			new Tile(2577, 3478, 0), new Tile(2552, 3485, 0) });

	public final Area burthorpeArea = new Area(new Tile[] {
			new Tile(2884, 3540, 0), new Tile(2904, 3540, 0),
			new Tile(2904, 3549, 0), new Tile(2894, 3549, 0) });

	public final Area r1Area = new Area(new Tile[] {
			new Tile(2574, 3455, 0), new Tile(2578, 3455, 0),
			new Tile(2578, 3461, 0), new Tile(2572, 3459, 0) });

	public final Area r2Area = new Area(new Tile[] {
			new Tile(2571, 3454, 0), new Tile(2575, 3454, 0),
			new Tile(2575, 3459, 0), new Tile(2571, 3460, 0) });

	public final Area r3Area = new Area(new Tile[] {
			new Tile(2561, 3456, 0), new Tile(2565, 3455, 0),
			new Tile(2565, 3461, 0), new Tile(2561, 3459, 0) });

	public final Area r4Area = new Area(new Tile[] {
			new Tile(2556, 3456, 0), new Tile(2560, 3456, 0),
			new Tile(2561, 3460, 0), new Tile(2556, 3461, 0) });

	public final Area r5Area = new Area(new Tile[] {
			new Tile(2555, 3467, 0), new Tile(2560, 3466, 0),
			new Tile(2561, 3470, 0), new Tile(2555, 3470, 0) });

	public final Area r6Area = new Area(new Tile[] {
			new Tile(2551, 3477, 0), new Tile(2558, 3477, 0),
			new Tile(2558, 3481, 0), new Tile(2551, 3481, 0) });

	public final Area captainIArea = new Area(new Tile[] {
			new Tile(2564, 3456, 0), new Tile(2571, 3455, 0),
			new Tile(2573, 3465, 0), new Tile(2564, 3464, 0) });

	public final Area watchTowerBaseArea = new Area(new Tile[] {
			new Tile(2566, 3438, 0), new Tile(2574, 3438, 0),
			new Tile(2573, 3446, 0), new Tile(2565, 3446, 0) });

	public final Area watchTowerMiddleArea = new Area(new Tile[] {
			new Tile(2566, 3438, 1), new Tile(2574, 3438, 1),
			new Tile(2573, 3446, 1), new Tile(2565, 3446, 1) });

	public final Area watchTowerTopArea = new Area(new Tile[] {
			new Tile(2566, 3438, 2), new Tile(2574, 3438, 2),
			new Tile(2573, 3446, 2), new Tile(2565, 3446, 2) });

	public final Area outsideGateArea = new Area(new Tile[] {
			new Tile(2564, 3449, 0), new Tile(2572, 3450, 0),
			new Tile(2572, 3456, 0), new Tile(2563, 3456, 0) });

	public final Area crateArea = new Area(new Tile[] {
			new Tile(2565, 9844, 0), new Tile(2572, 9844, 0),
			new Tile(2572, 9852, 0), new Tile(2565, 9852, 0) });

	public final Area outsideCaveArea = new Area(new Tile[] {
			new Tile(2623, 3388, 0), new Tile(2629, 3388, 0),
			new Tile(2631, 3392, 0), new Tile(2625, 3392, 0) });

	public final Area u1Area = new Area(new Tile[] {
			new Tile(2612, 9795, 0), new Tile(2627, 9795, 0),
			new Tile(2627, 9800, 0), new Tile(2613, 9798, 0) });

	public final Area lodeAArea = new Area(new Tile[] {
			new Tile(2630, 3345, 0), new Tile(2639, 3347, 0),
			new Tile(2638, 3354, 0), new Tile(2630, 3354, 0) });

	public final Area dwarfArea = new Area(new Tile[] {
			new Tile(3008, 3446, 0), new Tile(3018, 3446, 0),
			new Tile(3018, 3455, 0), new Tile(3008, 3455, 0) });

	public final Area dwarfIArea = new Area(new Tile[] {
			new Tile(3015, 3451, 0), new Tile(3015, 3455, 0),
			new Tile(3007, 3454, 0), new Tile(3007, 3449, 0) });

	public final Tile[] toCapFromBank = new Tile[] {
			new Tile(2808, 3440, 0), new Tile(2796, 3435, 0),
			new Tile(2785, 3434, 0), new Tile(2773, 3433, 0),
			new Tile(2762, 3428, 0), new Tile(2750, 3424, 0),
			new Tile(2738, 3421, 0), new Tile(2724, 3419, 0),
			new Tile(2712, 3416, 0), new Tile(2700, 3416, 0),
			new Tile(2688, 3411, 0), new Tile(2678, 3411, 0),
			new Tile(266, 3405, 0), new Tile(2654, 3399, 0),
			new Tile(2643, 3395, 0), new Tile(2632, 3392, 0),
			new Tile(2624, 3382, 0), new Tile(2612, 3382, 0),
			new Tile(2600, 3384, 0), new Tile(2589, 3387, 0),
			new Tile(2581, 3392, 0), new Tile(2575, 3401, 0),
			new Tile(2572, 3413, 0), new Tile(2569, 3424, 0),
			new Tile(2570, 3434, 0), new Tile(2572, 3446, 0),
			new Tile(2567, 3453, 0) };

	public final Tile[] pathToCrates = new Tile[] {
			new Tile(2617, 9796, 0), new Tile(2616, 9801, 0),
			new Tile(2609, 9804, 0), new Tile(2599, 9802, 0),
			new Tile(2589, 9800, 0), new Tile(2584, 9804, 0),
			new Tile(2581, 9804, 0), new Tile(2579, 9810, 0),
			new Tile(2576, 9814, 0), new Tile(2578, 9822, 0),
			new Tile(2582, 9828, 0), new Tile(2582, 9835, 0),
			new Tile(2579, 9840, 0), new Tile(2575, 9842, 0),
			new Tile(2571, 9844, 0), new Tile(2569, 9849, 0), };

	public final Tile[] pathToCave = new Tile[] { new Tile(2568, 3455, 0),
			new Tile(2569, 3446, 0), new Tile(2571, 3434, 0),
			new Tile(2573, 3422, 0), new Tile(2574, 3410, 0),
			new Tile(2576, 3401, 0), new Tile(2583, 3391, 0),
			new Tile(2591, 3382, 0), new Tile(2603, 3380, 0),
			new Tile(2616, 3379, 0), new Tile(2622, 3385, 0),
			new Tile(2627, 3391, 0) };

	public final Tile[] pathToLodeA = new Tile[] { new Tile(2568, 3454, 0),
			new Tile(2570, 3445, 0), new Tile(2573, 3434, 0),
			new Tile(2571, 3422, 0), new Tile(2571, 3410, 0),
			new Tile(2572, 3400, 0), new Tile(2577, 3391, 0),
			new Tile(2583, 3383, 0), new Tile(2595, 3378, 0),
			new Tile(2607, 3376, 0), new Tile(2621, 3376, 0),
			new Tile(2632, 3375, 0), new Tile(2636, 3361, 0),
			new Tile(2635, 3351, 0) };

	public final Tile[] pathToDwarf = new Tile[] {
			new Tile(2899, 3538, 0),
			new Tile(2899, 3528, 0),
			new Tile(2898, 3519, 0),
			new Tile(2899, 3511, 0),
			new Tile(2900, 3503, 0),
			new Tile(2908, 3492, 0),
			new Tile(2916, 3483, 0),
			new Tile(2922, 3472, 0),
			new Tile(2922, 3464, 0),
			new Tile(2921, 3453, 0),
			new Tile(2924, 3441, 0),
			new Tile(2933, 3440, 0),// first node is the near gate--below
			new Tile(2941, 3439, 0), new Tile(2947, 3436, 0),
			new Tile(2949, 3428, 0), new Tile(2961, 3423, 0),
			new Tile(2967, 3419, 0), new Tile(2978, 3416, 0),
			new Tile(2984, 3420, 0), new Tile(2991, 3431, 0),
			new Tile(3000, 3433, 0), new Tile(3009, 3433, 0),
			new Tile(3020, 3434, 0), new Tile(3028, 3441, 0),
			new Tile(3033, 3449, 0), new Tile(3037, 3457, 0),
			new Tile(3040, 3466, 0), new Tile(3032, 3469, 0),
			new Tile(3025, 3461, 0), new Tile(3019, 3456, 0),
			new Tile(3015, 3450, 0) };

	public final Tile[] pathToR1 = new Tile[] { new Tile(2576, 3457, 0) };
	public final Tile[] pathToR2 = new Tile[] { new Tile(2573, 3458, 0) };
	public final Tile[] pathToR3 = new Tile[] { new Tile(2563, 3457, 0) };
	public final Tile[] pathToR4 = new Tile[] { new Tile(2559, 3458, 0) };
	public final Tile[] pathToR5 = new Tile[] { new Tile(2557, 3468, 0) };
	public final Tile[] pathToR6 = new Tile[] { new Tile(2555, 3479, 0) };

	public final Tile[] pathToCaptainI = new Tile[] {
			new Tile(2562, 3472, 0), new Tile(2566, 3465, 0),
			new Tile(2567, 3459, 0) };

	public final Tile[] pathToWatchTower = new Tile[] {
			new Tile(2588, 3455, 0), new Tile(2568, 3449, 0),
			new Tile(2569, 3443, 0), new Tile(2570, 3440, 0) };
	
	public boolean init = false;

	public void execute() {
			getSetting();
			DeltaQuester.numSteps = 12;
			Quests.getSettings(2732);//was 0
			Quests.foodSupport();
			if (Widgets.get(1191).validate()) {
				if (Widgets.get(1191).getChild(17).getText()
						.contains("not going")) {
					eStepX = false;
					doSpecial = true;
				}
			}
			if (doSpecial) {
				cSSpecial();
			}
			if(!init){
				if (!Quests.thisArea(captainLawGofArea)) {
					Method.state("Must start by the dwarf, skipping quest");
					Task.sleep(2000);
					DeltaQuester.e = true;
				}else init = true;
			}else
			if (eStepX) {
				Quests.checkForGates();
				switch (Quests.stepX) {

				case 0:
					DeltaQuester.progress = 1;
					cs0();
					break;
				case 1:// step 1
					DeltaQuester.progress = 2;
					cs1();// in this step we fix railings.
					break;

				case 2:
					DeltaQuester.progress = 3;
					cS3();// In this step we must check the watch tower.
					break;

				case 3:// In this step we must return remains to the
						// captain.
					DeltaQuester.progress = 4;
					cS4();
					break;
				case 4:
					DeltaQuester.progress = 5;
					cS5();// In this step we must locate the cave.
					break;

				case 5:
					DeltaQuester.progress = 6;
					DeltaQuester.state ="Now going to find the child.";
					cS6();
					break;

				case 6:
					DeltaQuester.progress = 7;
					cS7();
					break;

				case 7:
					DeltaQuester.progress = 8;
					cS8();
					break;

				case 8:
					DeltaQuester.progress = 9;
					cS9();
					break;

				case 9:
					DeltaQuester.progress = 10;
					cS10();
					break;

				case 10:
					DeltaQuester.progress = 11;
					cS11();

					break;

				case 11:
					DeltaQuester.progress = 12;
					DeltaQuester.state ="The Dwarf Cannon quest has been completed.";
					Task.sleep(2000);
					DeltaQuester.e = true;
					break;
				}
			}
			if (eStepX2) {
				switch (stepX2) {

				case 2016:// step 2
					cS2();
					break;

				}

			}
	}

	private void cS11() {
		if (captainIArea.contains(Players.getLocal().getLocation())) {
			if (Quests.tActive()) {
				if (Quests.chat(1184,13,"Hello","Aah,","Hah!", "I'll tell", "He won't")){
					ChatOptions.getContinueOption().select(true);
				}else{
					if (Quests.chat(1191,17,"Hi","Yes,","He gave","You could", "Hmmm")){
						ChatOptions.getContinueOption().select(true);
					}
				}
			} else {
				Quests.speakTo(CAPTAIN_ID);
			}
		} else {
			if (outsideGateArea.contains(Players.getLocal().getLocation())) {
				SceneObject gate = SceneEntities.getNearest(15604);// gate
																	// id
				if (gate != null) {
					if (gate.isOnScreen()) {
						DeltaQuester.state ="Opening gate.";
						gate.interact("Open");
						Task.sleep(3200, 3500);
					} else
						Camera.turnTo(gate);
				}
			} else {
				if (hBAL) {// has been in lodestone area.
					DeltaQuester.state ="Walking to captain.";
					Walking.newTilePath(pathToLodeA).reverse().traverse();
					Task.sleep(1200, 1300);
				} else {
					if (lodeAArea
							.contains(Players.getLocal().getLocation())) {
						hBAL = true;
					} else {
						Method.teleportTo(41);
					}
				}
			}
		}

	}


	private void cS10() {
		if(cALode){
			if (bL1) {
				DeltaQuester.state ="Now in Burthorpe.";
				if (dwarfArea.contains(Players.getLocal().getLocation())) {
					if (dwarfIArea.contains(Players.getLocal()
							.getLocation())) {
						if (Widgets.get(1191).validate()
								|| Widgets.get(1184).validate()) {
							Task.sleep(400, 500);
							DeltaQuester.state ="Now speaking.";
							Widgets.get(1191).getChild(20).click(true);
							Task.sleep(1400, 1700);
							Widgets.get(1184).getChild(20).click(true);
							Task.sleep(1400, 1700);
							Widgets.get(1191).getChild(20).click(true);
							Task.sleep(1400, 1700);
							Widgets.get(1184).getChild(20).click(true);
							Task.sleep(1400, 1700);
							Widgets.get(1191).getChild(20).click(true);
							Task.sleep(1400, 1700);
							DeltaQuester.state ="Finishing conversation.";
							Widgets.get(1184).getChild(20).click(true);
							Task.sleep(1400, 1700);
						} else {
							NPC dwarf = NPCs.getNearest(209);
							if (dwarf != null) {
								if (dwarf.isOnScreen()) {
									DeltaQuester.state ="Clicking on dwarf";
									dwarf.interact("Talk-to");
									Task.sleep(1300, 1400);
								} else
									Camera.turnTo(dwarf);
							}
						}
					} else {
						SceneObject dr = SceneEntities.getNearest(3);
						if (dr != null) {
							if (dr.isOnScreen()) {
								dr.interact("Open");
								Task.sleep(1300, 1650);
							} else
								Camera.turnTo(dr);
						}
					}
				} else {
					SceneObject door = SceneEntities.getNearest(28690);
					if (door != null) {
						if (door.isOnScreen()) {
							door.interact("Open");
							Task.sleep(2300, 2400);
						} else
							Camera.turnTo(door);
						Quests.walking(pathToDwarf, "Walking to captain's friend",false);
						//Walking.newTilePath(pathToDwarf).traverse();
						//Task.sleep(1200, 1400);
					} else {
						Quests.walking(pathToDwarf, "Walking to captain's friend",false);
						//Walking.newTilePath(pathToDwarf).traverse();
						//Task.sleep(1200, 1400);
					}
				}
			} else {
				if (burthorpeArea
						.contains(Players.getLocal().getLocation())) {
					bL1 = true;
				} else {
					Method.teleportTo(42);
				}
			}
		}else{
			DeltaQuester.state ="Now checking lodestone: Ardougne.";
			activateLodestone();
		}
	}

	private void activateLodestone() {
		if (lodeAArea.contains(Players.getLocal().getLocation())) {
			DeltaQuester.state ="Ready to check stone.";
			SceneObject aLode = SceneEntities.getNearest(69830);
			if (aLode != null) {
				if (aLode.isOnScreen()) {
					aLode.hover();
					if (Menu.contains("Activate")) {
						aLode.interact("Activate");
						Task.sleep(1200, 1300);
					} else {
						cALode=true;
					}

				} else
					Camera.turnTo(aLode);
			}
		} else {
			if (hBOG1) {
				Quests.walking(pathToLodeA, "Walking to activate Ardougne lodestone.",false);
			
			} else {
				if (outsideGateArea.contains(Players.getLocal()
						.getLocation())) {
					hBOG1 = true;
				} else {
					SceneObject gate = SceneEntities.getNearest(15604);// gate
																		// id
					if (gate != null) {
						if (gate.isOnScreen()) {
							DeltaQuester.state ="Opening gate.";
							gate.interact("Open");
							Task.sleep(3200, 3500);
						} else
							Camera.turnTo(gate);
					}
				}
			}
		}

	}

	

	private void cS9() {
		if (Quests.tActive()) {
			hBOG1 = false;
			if (Quests.chat(1184,13,"Hello","Well", "Not bad","The Black","I can't", "That's great,", "You'll need")){
				ChatOptions.getContinueOption().select(true);
			}else{
				if (Quests.chat(1191,17,"Hello", "Well,", "Not bad","What now?", "Okay then", "Okay,")){
					ChatOptions.getContinueOption().select(true);
				}else{
					if (Quests.option(1184,13,"That's")){
						ChatOptions.getContinueOption().select(true);
						DeltaQuester.state ="Entering temp-sleep.";
						Task.sleep(4500,4700);
						DeltaQuester.state ="Resuming.";
					}else{
						if (Widgets.get(1188).validate()){
							Widgets.get(1188).getChild(13).click(true);
						}
					}
				}
			}
		} else {
			Quests.speakTo(CAPTAIN_ID);
		}
	}

	private void cS8() {
		/*
		 * setting 1 altered from 2016 to 2020 upon selecting the hooked
		 * tool. setting 1 altered to 2018 upon selecting he pliers. setting
		 * 1 altered to 2017 upon selecting the gear like hook. upon using
		 * the hooked tool setting 1 altered to 2036 upon selecting pliers
		 * after use of hooked tool thee setting altered to 2034. using
		 * pliers on safety pin after use of hook altered setting to 2042.
		 * selecting gearhook after use of pliers altered setting to 2041
		 * use of gearhook on small gear altered setting 0 to 8
		 
		if (Widgets.get(409).validate()) {

			if (stepX2 == 2041) {
				DeltaQuester.state ="Using gear-tool on gear.";
				Widgets.get(409).getChild(9).click(true);
				Task.sleep(1200, 1500);
			} else {

				if (stepX2 == 2042) {
					// must select gearhook from here.
					DeltaQuester.state ="Selecting gear-hook tool.";
					Widgets.get(409).getChild(1).click(true);
					Task.sleep(1200, 1500);
				} else {
					if (stepX2 == 2034) {
						DeltaQuester.state ="Using plier tool on safety pin";
						Widgets.get(409).getChild(7).click(true);
						Task.sleep(1200, 1500);
					} else {
						if (stepX2 == 2036) {
							DeltaQuester.state ="Selecting plier tool.";
							Widgets.get(409).getChild(2).click(true);
							Task.sleep(1200, 1500);
						} else {
							if (stepX2 == 2020) {
								DeltaQuester.state ="Using hook tool on spring.";
								Widgets.get(409).getChild(8).click(true);
								Task.sleep(1200, 1500);
							} else {
								if (stepX2 == 2016) {
									DeltaQuester.state ="Selecting hook tool.";
									Widgets.get(409).getChild(3)
											.click(true);
									Task.sleep(1200, 1500);
								}
							}
						}
					}
				}
			}

		} else {
			if (Inventory.getItem(1) != null) {
				Item toolK = Inventory.getItem(1);
				DeltaQuester.state ="Selecting the toolkit.";
				toolK.getWidgetChild().interact("Use");
				Task.sleep(1200, 1400);
				if (Inventory.getSelectedItem().getId() != (1)) {
				} else {
					SceneObject can = SceneEntities.getNearest(15597);
					if (can != null) {
						if (can.isOnScreen()) {
							DeltaQuester.state ="Using toolkit on cannon.";
							can.interact("Use");
							Task.sleep(2200, 2500);
						} else
							Camera.turnTo(can);
					}
				}
			}
		}

	}

	private void cS7() {
		if (captainIArea.contains(Players.getLocal().getLocation())) {
			if (Quests.tActive()) {
				DeltaQuester.state ="Now speaking to captain.";
				if (Quests.chat(1184,13,"He has,", "In that case", "When the", "please?", "Thank you,", "Take")){
					ChatOptions.getContinueOption().select(true);
				}else{
					if (Quests.chat(1191,17,"Hello,", "Always a pleasure", "Okay, I'll")){
						ChatOptions.getContinueOption().select(true);
					}else{
						if (Widgets.get(1188).validate()){
							Widgets.get(1188).getChild(11).click(true);
							Task.sleep(1200, 1400);
						}
					}
				}
			} else {
				Quests.speakTo(CAPTAIN_ID);
			}
		} else {
			if (outsideGateArea.contains(Players.getLocal().getLocation())) {
				SceneObject gate = SceneEntities.getNearest(15604);// gate
																	// id
				if (gate != null) {
					if (gate.isOnScreen()) {
						DeltaQuester.state ="Opening gate.";
						gate.interact("Open");
						Task.sleep(3200, 3500);
					} else
						Camera.turnTo(gate);
				}
			} else {
				if (oC1) {
					DeltaQuester.state ="Walking back to captain.";
					Walking.newTilePath(pathToCave).reverse().traverse();
					Task.sleep(1200, 1400);
				} else {
					if (outsideCaveArea.contains(Players.getLocal()
							.getLocation())) {
						oC1 = true;
					} else {
						if (u1Area.contains(Players.getLocal()
								.getLocation())) {
							SceneObject e = SceneEntities.getNearest(13);
							if (e != null) {
								if (e.isOnScreen()) {
									DeltaQuester.state ="Exiting cave.";
									e.interact("Climb");
									Task.sleep(1200, 1400);
								} else
									Camera.turnTo(e);
							}
						} else {
							DeltaQuester.state ="Walking to cave exit.";
							Walking.newTilePath(pathToCrates).reverse()
									.traverse();
							Task.sleep(1400, 1500);
						}
					}
				}
			}
		}

	}

	private void cS6() {
		if (crateArea.contains(Players.getLocal().getLocation())) {
			SceneObject c = SceneEntities.getNearest(1);
			if (c != null) {
				if (c.isOnScreen()) {
					c.interact("Search");
					Task.sleep(2200, 2400);
				} else
					Camera.turnTo(c);
			}
		} else {
			if (u1) {
				DeltaQuester.state ="Now walking to crates.";
				Walking.newTilePath(pathToCrates).traverse();
				Task.sleep(1200, 1400);
			} else {
				if (u1Area.contains(Players.getLocal().getLocation())) {
					u1 = true;
				} else {
					if (outsideCaveArea.contains(Players.getLocal()
							.getLocation())) {
						DeltaQuester.state ="Now outside cave.";
						SceneObject cave = SceneEntities.getNearest(2);
						if (cave != null) {
							if (cave.isOnScreen()) {
								DeltaQuester.state ="Entering cave.";
								cave.interact("Enter");
								Task.sleep(1200, 1400);
							} else
								Camera.turnTo(cave);
						}
					} else {
						if (hBOG1) {
							DeltaQuester.state ="Now walking to cave.";
							Walking.newTilePath(pathToCave).traverse();
							Task.sleep(1300, 1500);
						} else {
							if (outsideGateArea.contains(Players.getLocal()
									.getLocation())) {
								hBOG1 = true;
							} else {
								SceneObject gate = SceneEntities
										.getNearest(15604);// gate id
								if (gate != null) {
									if (gate.isOnScreen()) {
										DeltaQuester.state ="Opening gate.";
										gate.interact("Open");
										Task.sleep(3200, 3500);
									} else
										Camera.turnTo(gate);
								}
							}
						}
					}
				}
			}

		}

	}

	private void cS5() {
		DeltaQuester.state ="Now we must find goblin base.";

		if (u1Area.contains(Players.getLocal().getLocation())) {
		} else {
			if (outsideCaveArea.contains(Players.getLocal().getLocation())) {
				DeltaQuester.state ="Now outside cave.";
				SceneObject cave = SceneEntities.getNearest(2);
				if (cave != null) {
					if (cave.isOnScreen()) {
						DeltaQuester.state ="Entering cave.";
						cave.interact("Enter");
						Task.sleep(1200, 1400);
					} else
						Camera.turnTo(cave);
				}
			} else {
				if (hBOG1) {
					DeltaQuester.state ="Now walking to cave.";
					Walking.newTilePath(pathToCave).traverse();
					Task.sleep(1300, 1500);
				} else {
					if (outsideGateArea.contains(Players.getLocal()
							.getLocation())) {
						hBOG1 = true;
					} else {
						SceneObject gate = SceneEntities.getNearest(15604);// gate
																			// id
						if (gate != null) {
							if (gate.isOnScreen()) {
								DeltaQuester.state ="Opening gate.";
								gate.interact("Open");
								Task.sleep(3200, 3500);
							} else
								Camera.turnTo(gate);
						}
					}

				}
			}
		}

	}

	private void cS4() {
		Item r = Inventory.getItem(FRIENDREMAINS_ID);
		if (r != null) {
			if (captainIArea.contains(Players.getLocal().getLocation())) {
				if (Quests.tActive()) {
					DeltaQuester.state ="Now speaking to captain.";
					if (Quests.chat(1191,17,"Hello", "I have some")){
						ChatOptions.getContinueOption().select(true);
					}else{
						if (Quests.chat(1184,13,"Have you")){
							ChatOptions.getContinueOption().select(true);
						}else{
							if (Widgets.get(1189).validate()){
								ChatOptions.getContinueOption().select(true);
							}
						}
					}
					hBOG1 = false;
					Widgets.get(1189).getChild(13).click(true);
					Task.sleep(1200, 1400);
				} else {
					Quests.speakTo(CAPTAIN_ID);
				}
			} else {
				if (outsideGateArea.contains(Players.getLocal()
						.getLocation())) {
					SceneObject gate = SceneEntities.getNearest(15604);// gate
																		// id
					if (gate != null) {
						if (gate.isOnScreen()) {
							DeltaQuester.state ="Opening gate.";
							gate.interact("Open");
							Task.sleep(3200, 3500);
						} else
							Camera.turnTo(gate);
					}
				} else {
					if (w1) {
						DeltaQuester.state ="Walking to gate.";
						Walking.newTilePath(pathToWatchTower).reverse()
								.traverse();
						Task.sleep(1600, 1900);
					} else {
						if (watchTowerBaseArea.contains(Players.getLocal()
								.getLocation())) {
							w1 = true;
						} else {
							if (watchTowerMiddleArea.contains(Players
									.getLocal().getLocation())) {
								SceneObject lt = SceneEntities
										.getNearest(1746);
								if (lt != null) {
									if (lt.isOnScreen()) {
										DeltaQuester.state ="Climbing down.";
										lt.interact("Climb");
										Task.sleep(1200, 1400);
									} else
										Camera.turnTo(lt);
								}
							} else {
								if (watchTowerTopArea.contains(Players
										.getLocal().getLocation())) {
									SceneObject lt = SceneEntities
											.getNearest(1746);
									if (lt != null) {
										if (lt.isOnScreen()) {
											DeltaQuester.state ="Climbing down.";
											lt.interact("Climb");
											Task.sleep(1200, 1400);
										} else
											Camera.turnTo(lt);
									}
								} else
									DeltaQuester.state ="Where r u bro?!";
							}
						}
					}
				}
			}

		} else {// METHOD BELOW FINDS AND GRABS REMAINS.
			if (watchTowerTopArea
					.contains(Players.getLocal().getLocation())) {
				if (Widgets.get(1191).validate()) {
					DeltaQuester.state ="Clicking continue.";
					Widgets.get(1191).getChild(20).click(true);
					Task.sleep(1200, 1400);
				} else {
					DeltaQuester.state ="Now going to grab remains from floor.";
					SceneObject f = SceneEntities
							.getNearest(FRIENDREMAINS_ID);
					if (f != null) {
						if (f.isOnScreen()) {
							DeltaQuester.state ="Picking up remains.";
							f.interact("");
							Task.sleep(1200, 1400);
						} else
							Camera.turnTo(f);
					}
				}
			} else {
				if (watchTowerMiddleArea.contains(Players.getLocal()
						.getLocation())) {
					SceneObject l2 = SceneEntities.getNearest(11);
					if (l2 != null) {
						if (l2.isOnScreen()) {
							DeltaQuester.state ="Climbing up second ladder.";
							l2.interact("Climb-up");
							Task.sleep(1200, 1400);
						} else
							Camera.turnTo(l2);
					}
				} else {
					if (watchTowerBaseArea.contains(Players.getLocal()
							.getLocation())) {
						SceneObject l = SceneEntities
								.getNearest(WATCHTOWERLADDERBASE_ID);
						if (l != null) {
							if (l.isOnScreen()) {
								DeltaQuester.state ="Climbing ladder.";
								l.interact("Climb");
								Task.sleep(1300, 1600);
							} else
								Camera.turnTo(l);
						}
					} else {
						if (hBOG1) {
							DeltaQuester.state ="You have now been outside the gate.";
							Walking.newTilePath(pathToWatchTower)
									.traverse();
							Task.sleep(1200, 1400);
						} else {
							if (outsideGateArea.contains(Players.getLocal()
									.getLocation())) {
								DeltaQuester.state ="Setting to true.";
								hBOG1 = true;
							} else {
								SceneObject gate = SceneEntities
										.getNearest(15604);// gate id
								if (gate != null) {
									if (gate.isOnScreen()) {
										DeltaQuester.state ="Opening gate.";
										gate.interact("Open");
										Task.sleep(3200, 3500);
									} else
										Camera.turnTo(gate);
								}
							}
						}
					}
				}
			}
		}

	}

	private void cSSpecial() {
		if (captainIArea.contains(Players.getLocal().getLocation())) {
			if (Inventory.getItem(14)==null){
			if (Quests.tActive()) {
				DeltaQuester.state ="Now speaking.";
				if (Quests.chat(1184,13,"Hello,", "The goblins", "That's okay,")){
					ChatOptions.getContinueOption().select(true);
				}else{
					if (Quests.chat(1191,17,"Hello.", "I'm getting", "But I'm")){
						ChatOptions.getContinueOption().select(true);
					}else{
						if (option(1184,13,"That's okay,")){
							ChatOptions.getContinueOption().select(true);
						}
					}
				}
			} else {
				Quests.speakTo(CAPTAIN_ID);
			}
			}else{
				eStepX = true;
			doSpecial = false;
			}
		} else {
			DeltaQuester.state ="Walking back to captain.";
			Walking.newTilePath(pathToCaptainI).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void cS3() {
		DeltaQuester.state ="We must gather remains.";
		if (watchTowerTopArea.contains(Players.getLocal().getLocation())) {
			DeltaQuester.state ="Now within loc.";
		} else {
			if (watchTowerMiddleArea.contains(Players.getLocal()
					.getLocation())) {
				SceneObject l2 = SceneEntities.getNearest(11);
				if (l2 != null) {
					if (l2.isOnScreen()) {
						DeltaQuester.state ="Climbing up second ladder.";
						l2.interact("Climb-up");
					} else
						Camera.turnTo(l2);
				}
			} else {
				if (watchTowerBaseArea.contains(Players.getLocal()
						.getLocation())) {
					SceneObject l = SceneEntities
							.getNearest(WATCHTOWERLADDERBASE_ID);
					if (l != null) {
						if (l.isOnScreen()) {
							DeltaQuester.state ="Climbing ladder.";
							l.interact("Climb");
							Task.sleep(1300, 1600);
						} else
							Camera.turnTo(l);
					}
				} else {
					if (hBOG1) {
						DeltaQuester.state ="You have now been outside the gate.";
						Walking.newTilePath(pathToWatchTower).traverse();
						Task.sleep(1200, 1400);
					} else {
						if (outsideGateArea.contains(Players.getLocal()
								.getLocation())) {
							DeltaQuester.state ="Setting to true.";
							hBOG1 = true;
						} else {
							SceneObject gate = SceneEntities
									.getNearest(15604);// gate id
							if (gate != null) {
								if (gate.isOnScreen()) {
									DeltaQuester.state ="Opening gate.";
									gate.interact("Open");
									Task.sleep(3200, 3500);
								} else
									Camera.turnTo(gate);
							}
						}
					}
				}
			}
		}

	}

	private void cS2() {
		if (captainIArea.contains(Players.getLocal().getLocation())) {
			if (Quests.tActive()) {
				if (Quests.chat(1184,13,"Well done,", "What? I'll", "Besides, I have", "operation into", "They should")){
					ChatOptions.getContinueOption().select(true);
				}else{
					if (Quests.chat(1191,17,"Great, I'll","Okay,")){
						ChatOptions.getContinueOption().select(true);
					}else{
						if (option(1184,13,"Excellent!")){
							ChatOptions.getContinueOption().select(true);
							eStepX = true;
							eStepX2 = false;
						}
					}
				}
			} else {
				Quests.speakTo(CAPTAIN_ID);
			}
		} else {
			DeltaQuester.state ="Walking back to captain.";
			Walking.newTilePath(pathToCaptainI).traverse();
			Task.sleep(1200, 1400);
		}

	}
	private boolean option(int p, int c, String text) {
		if (Widgets.get(p).getChild(c).isOnScreen()
				&& Widgets.get(p).getChild(c).getText().contains(text)) {
			return true;
		}
		return false;
	}
	private void cs0() {
		 
		if (Quests.thisArea(captainLawGofArea)) {

			if (Quests.tActive()) {
				if (Quests.chatting()) {
					Quests.clickContinue();
				} else if (Widgets.get(1188).validate()) {
					Widgets.get(1188).getChild(23).click(true);
				}
			} else
				Quests.speakTo(CAPTAIN_ID);

		}

	}

	private void cs1() {
		if (captainLawGofArea.contains(Players.getLocal().getLocation())) {

			switch (railNum) {

			case 1:
				fixR1();
				break;
			case 2:
				fixR2();
				break;
			case 3:
				fixR3();
				break;
			case 4:
				fixR4();
				break;
			case 5:
				fixR5();
				break;
			case 6:
				fixR6();
				break;

			}

		} else {
			DeltaQuester.state ="Unknown area.";
		}

	}

	private void fixR6() {
		DeltaQuester.state ="On our way to fix rail 6";
		if (r6Area.contains(Players.getLocal().getLocation())) {
			if (Widgets.get(1186).validate()) {
				if (Players.getLocal().getAnimation() != 4190) {
					Task.sleep(1200, 1400);
					DeltaQuester.state ="Clicking continue.";
					Widgets.get(1186).getChild(10).click(true);
					DeltaQuester.state ="Now sleeping.";
					Task.sleep(5000, 5500);
					DeltaQuester.state ="Resuming.";
				}
			} else {
				if (Widgets.get(1191).validate()) {
					eStepX2 = true;
					eStepX = false;
				} else {
					SceneObject rail = SceneEntities.getNearest(R6);
					if (rail != null) {
						if (rail.isOnScreen()) {
							DeltaQuester.state ="Clicking rail 6.";
							rail.interact("Inspect");
							Task.sleep(1400, 1700);
						} else
							Camera.turnTo(rail);
					}
				}
			}
		} else {
			DeltaQuester.state ="Relocating to broken rail #6";
			Walking.newTilePath(pathToR6).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void fixR5() {
		DeltaQuester.state ="We must fix rail 5";
		if (r5Area.contains(Players.getLocal().getLocation())) {
			if (Widgets.get(1186).validate()) {
				if (Players.getLocal().getAnimation() != 4190) {
					Task.sleep(1200, 1400);
					DeltaQuester.state ="Clicking continue.";
					Widgets.get(1186).getChild(10).click(true);
					DeltaQuester.state ="Now sleeping.";
					Task.sleep(5000, 5500);
					DeltaQuester.state ="Resuming.";
				}
			} else {
				if (Widgets.get(1191).validate()) {
					railNum = 6;
				} else {
					SceneObject rail = SceneEntities.getNearest(R5);
					if (rail != null) {
						if (rail.isOnScreen()) {
							DeltaQuester.state ="Clicking rail 5.";
							rail.interact("Inspect");
							Task.sleep(1400, 1700);
						} else
							Camera.turnTo(rail);
					}
				}
			}
		} else {
			DeltaQuester.state ="Relocating to broken rail #5";
			Walking.newTilePath(pathToR5).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void fixR4() {
		DeltaQuester.state ="We must fix rail # 4!";
		if (r4Area.contains(Players.getLocal().getLocation())) {
			if (Widgets.get(1186).validate()) {
				if (Players.getLocal().getAnimation() != 4190) {
					Task.sleep(1200, 1400);
					DeltaQuester.state ="Clicking continue.";
					Widgets.get(1186).getChild(10).click(true);
					DeltaQuester.state = "Now sleeping.";
					Task.sleep(5000, 5500);
					DeltaQuester.state ="Resuming.";
				}
			} else {
				if (Widgets.get(1191).validate()) {
					railNum = 5;
				} else {
					SceneObject rail = SceneEntities.getNearest(R4);
					if (rail != null) {
						if (rail.isOnScreen()) {
							DeltaQuester.state ="Clicking rail 4.";
							rail.interact("Inspect");
							Task.sleep(1400, 1700);
						} else
							Camera.turnTo(rail);
					}
				}
			}
		} else {
			DeltaQuester.state ="Relocating to broken rail #4";
			Walking.newTilePath(pathToR4).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void fixR3() {
		if (r3Area.contains(Players.getLocal().getLocation())) {
			if (Widgets.get(1186).validate()) {
				if (Players.getLocal().getAnimation() != 4190) {
					Task.sleep(1200, 1400);
					DeltaQuester.state ="Clicking continue.";
				    Quests.clickContinue();
					DeltaQuester.state ="Now sleeping.";
					Task.sleep(5000, 5500);
					Quests.state("Resuming.");
				}
			} else {
				if (Widgets.get(1191).validate()) {
					railNum = 4;
				} else {
					SceneObject rail = SceneEntities.getNearest(R3);
					if (rail != null) {
						if (rail.isOnScreen()) {
							DeltaQuester.state = "Clicking rail 3.";
							rail.interact("Inspect");
							Task.sleep(1400, 1700);
						} else
							Camera.turnTo(rail);
					}
				}
			}
		} else {
			DeltaQuester.state ="Relocating to broken rail #3";
			Walking.newTilePath(pathToR3).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void fixR2() {
		DeltaQuester.state ="Now we must fix R2.";
		if (r2Area.contains(Players.getLocal().getLocation())) {
			if (Widgets.get(1186).validate()) {
				if (Players.getLocal().getAnimation() != 4190) {
					Task.sleep(1200, 1400);
					Widgets.get(1186).getChild(10).click(true);
					DeltaQuester.state ="Now sleeping.";
					Task.sleep(5000, 5500);
					DeltaQuester.state ="Resuming.";
				}
			} else {
				if (Widgets.get(1191).validate()) {
					railNum = 3;
				} else {
					SceneObject rail = SceneEntities.getNearest(R2);
					if (rail != null) {
						if (rail.isOnScreen()) {
							DeltaQuester.state ="Clicking rail 2.";
							rail.interact("Inspect");
							Task.sleep(1400, 1700);
						} else
							Camera.turnTo(rail);
					}
				}
			}
		} else {
			Walking.newTilePath(pathToR2).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void fixR1() {
		if (r1Area.contains(Players.getLocal().getLocation())) {
			if (Widgets.get(1186).validate()) {
				if (Players.getLocal().getAnimation() != 4190) {
					Task.sleep(1200, 1400);
					Widgets.get(1186).getChild(10).click(true);
					DeltaQuester.state ="Now sleeping.";
					Task.sleep(5000, 5500);
					DeltaQuester.state ="Resuming.";
				}
			} else {
				if (Widgets.get(1191).validate()) {
					railNum = 2;
				} else {
					Quests.interactO(RAIL[0], "Inspect");
					
				}
			}
			DeltaQuester.state ="Now prepared to fix rail 1";
		} else {
			Walking.newTilePath(pathToR1).traverse();
			Task.sleep(1200, 1400);
		}

	}

	private void getSetting() {
		stepX2 = Settings.get(2733);//0
		return;
	}
	public boolean activate() {
		return (DeltaQuester.scriptToStart == 3);
	}
}*/