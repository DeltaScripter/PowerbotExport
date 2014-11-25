package toadCollector;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Player;




public class toadAntipattern extends toadNode{

	public toadAntipattern(ClientContext ctx) {
		super(ctx);
	}

	toadMethod m = new toadMethod(ctx);
	@Override
	public boolean activate() {
		return ToadMainBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,32), Random.nextInt(1, 32));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		//System.out.println("Antipattern");
		switch(number){
		
		case 1:
			ToadMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(20, 25));
			i.hover();
			m.sleep(Random.nextInt(200,1000));
			ctx.mouse.scroll(false);
			ToadMainBody.antiPattern = false;
			break;
			
		case 2:
			ToadMainBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 3:
			ToadMainBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			ToadMainBody.antiPattern = false;
			break;
			
		case 4:
			ToadMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(7, 20));
				i.hover();
				ctx.mouse.scroll(true);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				ToadMainBody.antiPattern = false;
			
			break;
			
		case 5:
			ToadMainBody.state = st;
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			ToadMainBody.antiPattern = false;
			break;
			
		case 6:
			
			ToadMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			ToadMainBody.antiPattern = false;
			break;
			
		case 7:
			ToadMainBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			ToadMainBody.antiPattern = false;
			break;
			
		case 8:
			ToadMainBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			ToadMainBody.antiPattern = false;
			break;
			
		case 9:
			ToadMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			ToadMainBody.antiPattern = false;
			break;
			
		case 10:
			ToadMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			ToadMainBody.antiPattern = false;
			break;
			
		case 11:
			ToadMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				ToadMainBody.antiPattern = false;
				break;
				
		case 12:
			ToadMainBody.state = st;
			for(GameObject g : ctx.objects.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(Random.nextInt(300, 700), Random.nextInt(1100, 1400)));
				}else {
					ctx.camera.turnTo(g);
					m.sleep(Random.nextInt(Random.nextInt(1300, 1700), Random.nextInt(2100, 2400)));
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
				ToadMainBody.antiPattern = false;
				break;
				
		case 13:
			ToadMainBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
				ToadMainBody.antiPattern = false;
				break;
				
		case 14:
			ToadMainBody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 15:
			ToadMainBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			ToadMainBody.antiPattern = false;
			break;
			
		case 16:
			ToadMainBody.state = st;
			ctx.mouse.scroll(true);
			ToadMainBody.antiPattern = false;
			break;
			
		case 17:
			ToadMainBody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
			}
			ToadMainBody.antiPattern = false;
			break;
		case 18:
			ToadMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
			}
			ToadMainBody.antiPattern = false;
			break;
		case 19:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(1000, 4000));
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 20:
			ToadMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 21:
			ToadMainBody.state = st;
			for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
					break;
				}else ctx.camera.turnTo(rock);
			}
			ToadMainBody.antiPattern = false;
			break;
			
			
			
		case 22:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(2000, 3500));
				ctx.mouse.move(f.x-Random.nextInt(50, 400),f.y+Random.nextInt(33, 330));
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 23:
			ToadMainBody.state = st;
			for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
					break;
				}else ctx.camera.turnTo(rock);
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 24:
			ToadMainBody.state = st;
			   for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
					break;
				}else ctx.camera.turnTo(rock);
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 25:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(3000, 5500));
				ctx.mouse.move(f.x-Random.nextInt(-40, -600),f.y+Random.nextInt(-43, -230));
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 26:
			ToadMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(4, 10));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(600,1300));
				ctx.mouse.move(f.x-Random.nextInt(-50, -100),f.y+Random.nextInt(400, -650));
				m.sleep(Random.nextInt(300,500));
				ToadMainBody.antiPattern = false;
				break;
				
		case 27:
			ToadMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(900,1700));
				ctx.mouse.move(f.x-Random.nextInt(+450, +600),f.y+Random.nextInt(-400, 350));
				ToadMainBody.antiPattern = false;
				break;
				
		case 28:
			ToadMainBody.state = st;
			
			if(ctx.bank.opened()){
				i = ctx.bank.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(500,2700));
				ctx.mouse.move(f.x-Random.nextInt(-450, -600),f.y+Random.nextInt(-500, 150));
			}

				ToadMainBody.antiPattern = false;
				break;
				
		case 29:
			ToadMainBody.state = st;
			
			if(ctx.bank.opened()){
				i = ctx.bank.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(500,2700));
				ctx.mouse.move(f.x-Random.nextInt(-450, -600),f.y+Random.nextInt(-500, 150));
			}else ctx.bank.open();

				ToadMainBody.antiPattern = false;
				break;
				
		case 30:
			for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
				}else ctx.camera.turnTo(rock);
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 31:
			if(ctx.hud.open(Window.CLAN_CHAT)){
				m.sleep(Random.nextInt(1000, 2400));
				ctx.mouse.move(f.x-Random.nextInt(100, 200),f.y+Random.nextInt(430, 330));
			}
			ToadMainBody.antiPattern = false;
			break;
			
		case 32:
			ToadMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(400, 700));
					ctx.mouse.move(f.x-Random.nextInt(0300, 110),f.y+Random.nextInt(230, -170));
				}else ctx.camera.turnTo(g.tile().derive(2, 4));
			}
			ToadMainBody.antiPattern = false;
			break;
			
				
			default:
				ToadMainBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.component(1401,36).visible()){
			ToadMainBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.component(1401,36).click();//close button
		}
	}
	
	
}
