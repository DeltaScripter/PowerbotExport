package questdevelopment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Tile;



@org.powerbot.script.Manifest(authors = { "Delta Scripter" }, name = "Quest data collect", 
description = "In development", hidden = true,
website = "",topic =1131685, version = 1)
public class gatherBody extends PollingScript implements PaintListener{

	public gatherBody(){
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				   addNode(new Observe(ctx));
				   initialSetting  = ctx.settings.get(setting);
				   System.out.println("Init setting as: "+initialSetting);
				   
			}
		});
	}
	
	private final List<gatherNode> nodeList = Collections.synchronizedList(new ArrayList<gatherNode>());
	private String state;
	public int boneType;
	public String boneName;
	private int setting = 3936;
	private int initialSetting;
	
	@Override
	public int poll() {
		
		for(gatherNode node: nodeList){
			if(node.activate()){
				node.execute();
			}
		}
		
		return 400;
	}
	private void shutdown() {
		System.out.println("Now shutting down");
		getController().stop();
	}	

	
	   private void addNode(final gatherNode...nodes) {
		   
	        for(gatherNode node : nodes) {
	            if(!this.nodeList.contains(node)) {
	                this.nodeList.add(node);
	            }
	        }
	    }

		class Observe extends gatherNode{

			public Observe(MethodContext ctx) {
				super(ctx);
			}

			@Override
			public boolean activate() {
				return true;
			}
			ArrayList<Tile> path = new ArrayList<Tile>();
			ArrayList<String> talk = new ArrayList<String>();
			ArrayList<String> teleport = new ArrayList<String>();
			ArrayList<String> optionTalk = new ArrayList<String>();
			@Override
			public void execute() {
				state = "Observing your actions, master";
				if(ctx.movement.getDestination()!=null){
					if(!path.contains(ctx.movement.getDestination())){
						path.add(ctx.movement.getDestination());
					System.out.println(""  +ctx.movement.getDestination());
					}
				}
				if(ctx.menu.isOpen()){
					for(String i: ctx.menu.getItems()){
					System.out.println("Menu items: " + i);
					}
				}
				if((ctx.settings.get(setting)!=initialSetting)){
					System.out.println("Setting changed: " + ctx.settings.get(setting));
					initialSetting = ctx.settings.get(setting);
				}if(ctx.widgets.get(1500).isValid()){
					System.out.println("Accept quest screen open!");
					ctx.game.sleep(1200);
				}
				if(ctx.widgets.get(1186,3).isVisible() || ctx.widgets.get(1189,4).isVisible()){
					System.out.println("Option dialogue open, click space!");
					ctx.game.sleep(1200);
				}
				if(ctx.settings.get(1113)!=0){
					System.out.println("Cutscene occurring!");
				}
				if(ctx.widgets.get(1092,47).isVisible()){
					if(!teleport.contains("Teleporting")){
						System.out.println("Teleporting!");
						talk.add("Teleporting");
					}
				}else teleport.clear();
				
				if(ctx.widgets.get(1188).isValid()){
					int opt[] = {12,18,23,28};
					for(Component i: ctx.widgets.get(1188).getComponents()){
						for(int index : opt){
							if(i.getIndex()==index){
								if(i.getTextColor()!=13158600 && !optionTalk.contains(i.getText())){
								System.out.println("Text: " + i.getText());
								optionTalk.add(i.getText());
							}
						}
					}
				}
				}else optionTalk.clear();
				if(ctx.widgets.get(1184).isValid() || ctx.widgets.get(1191).isValid()){
					if(!talk.contains("Now speaking to: " + ctx.widgets.get(1184,10).getText())){
						talk.add("Now speaking to: " + ctx.widgets.get(1184,10).getText());
						System.out.println("Now speaking to: " + ctx.widgets.get(1184,10).getText());
					}
				}else talk.clear();
			}

			
		}
	private Font myFont = new Font("Consolas",Font.BOLD,14);
	@Override
	public void repaint(Graphics g) {
		g.setFont(myFont);
		g.setColor(Color.green);
		g.drawString("State: "+state, 20, 130);
		g.drawString("Bone type: "+boneName, 20, 150);
	}
}
