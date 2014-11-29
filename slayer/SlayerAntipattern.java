package slayer;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Npc;
import org.powerbot.script.rt6.Player;




public class SlayerAntipattern extends SlayerNode{

	public SlayerAntipattern(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return slayerbody.antiPattern;
	}

	SMethod m = new SMethod(ctx);
	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,28), Random.nextInt(1, 28));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		System.out.println("Antipattern");
		switch(number){
		
		case 1:
			
			slayerbody.state = st;
			if(m.inventoryContains(slayerbody.FOODID) && ctx.players.local().healthPercent()<60){
				m.interactInventory(slayerbody.FOODID, "Eat", "Food");
			}
			m.sleep(Random.nextInt(20, 600));
			slayerbody.antiPattern = false;
			break;
			
		case 2:
			slayerbody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					break;
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
				
			}
			slayerbody.antiPattern = false;
			break;
			
		case 3:
			slayerbody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			slayerbody.antiPattern = false;
			break;
			
		case 4:
			slayerbody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(7, 20));
				i.hover();
				ctx.mouse.scroll(true);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				slayerbody.antiPattern = false;
			
			break;
			
		case 5:
			slayerbody.state = st;
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			slayerbody.antiPattern = false;
			break;
			
		case 6:
			
			slayerbody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			slayerbody.antiPattern = false;
			break;
			
		case 7:
			slayerbody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			slayerbody.antiPattern = false;
			break;
			
		case 8:
			slayerbody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			slayerbody.antiPattern = false;
			break;
			
		case 9:
			slayerbody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			slayerbody.antiPattern = false;
			break;
			
		case 10:
			slayerbody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			slayerbody.antiPattern = false;
			break;
			
		case 11:
			slayerbody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				slayerbody.antiPattern = false;
				break;
				
		case 12:
			slayerbody.state = st;//bones
			 if(m.inventoryContains(526)){
				 m.interactInventory(526, "Bury", "Bones");
				 m.sleep(Random.nextInt(250,400));
			 }
				slayerbody.antiPattern = false;
				break;
				
		case 13:
			slayerbody.state = st;
		   if(!m.inCombat()&&m.ctx.hud.opened(Window.SKILLS)){
			   ctx.mouse.move(500,800);
			   m.sleep(Random.nextInt(300, 1200));
			   ctx.mouse.move(20,120);
			   m.sleep(Random.nextInt(20, 160));
		   }
				slayerbody.antiPattern = false;
				break;
				
		case 14:
			slayerbody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			slayerbody.antiPattern = false;
			break;
			
		case 15:
			slayerbody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			slayerbody.antiPattern = false;
			break;
			
		case 16:
			slayerbody.state = st;
			ctx.mouse.scroll(true);
			slayerbody.antiPattern = false;
			break;
			
		case 17:
			slayerbody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
			}
			slayerbody.antiPattern = false;
			break;
		case 18:
			slayerbody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
			}
			slayerbody.antiPattern = false;
			break;
		case 19:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(1000,1400));
			}
			slayerbody.antiPattern = false;
			break;
			
		case 20:
			slayerbody.state = st;
			for(Player g : ctx.players.select().shuffle().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}
			}
			slayerbody.antiPattern = false;
			break;
			
		case 21:
			slayerbody.state = st;
			for(GameObject g : ctx.objects.select().nearest().shuffle().first()){
				
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					ctx.mouse.click(true);
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(340, 110),f.y+Random.nextInt(133, 123));
					m.sleep(Random.nextInt(200, 600));
					break;
				}else ctx.camera.turnTo(g.tile().derive(1, 3));
			}
			slayerbody.antiPattern = false;
			break;
			
		case 22:
			slayerbody.state = st;
			
			for(Item g : ctx.backpack.select().shuffle()){
				ctx.mouse.click(g.centerPoint(),false);
				m.sleep(Random.nextInt(20, 300));
				break;
			}
			
			slayerbody.antiPattern = false;
			break;
			
		case 23:
			slayerbody.state = st;
			m.interactInventory(4155, "Kills-left", "Enchanted gem");
			slayerbody.antiPattern = false;
			break;
			
		case 24:
			slayerbody.state = st;
			ctx.camera.angle(Random.nextInt(40, 90));
			m.sleep(Random.nextInt(10,100));
			if(Random.nextInt(1, 30)>10){
			ctx.camera.pitch(65);
			m.sleep(Random.nextInt(100,200));
			}
			slayerbody.antiPattern = false;
			break;
			
		case 25:
			if(ctx.hud.open(Window.WORN_EQUIPMENT)){
				m.sleep(Random.nextInt(230,400));
			}
			break;
			
		case 26:
			if(ctx.combatBar.actionAt(Random.nextInt(0, 7)).select(true)){
				m.sleep(Random.nextInt(50, 200));
			}
			break;
			
		case 27:
			if(ctx.combatBar.actionAt(Random.nextInt(3, 4)).select(true)){
				m.sleep(Random.nextInt(100, 600));
			}
			break;
			
		case 28:
			slayerbody.state = st;
			for(Npc g : ctx.npcs.select().nearest().shuffle().first()){
				
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					ctx.mouse.click(true);
					m.sleep(Random.nextInt(20, 60));
					ctx.mouse.move(f.x-Random.nextInt(340,100),f.y+Random.nextInt(123,456));
					m.sleep(Random.nextInt(123,456));
					break;
				}else ctx.camera.turnTo(g.tile().derive(1, 2));
			}
			slayerbody.antiPattern = false;
			break;
			
			default:
				slayerbody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.component(1401,36).visible()){
			slayerbody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.component(1401,36).click();//close button
		}
	}
	
	
}
