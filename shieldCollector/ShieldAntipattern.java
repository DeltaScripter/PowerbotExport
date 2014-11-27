package shieldCollector;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Player;




public class ShieldAntipattern extends ShieldNode{

	public ShieldAntipattern(ClientContext ctx) {
		super(ctx);
	}

	ShieldMethod m = new ShieldMethod(ctx);
	@Override
	public boolean activate() {
		return ShieldMainBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		int number = Random.nextInt(Random.nextInt(1,38), Random.nextInt(1, 38));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		System.out.println("Antipattern : " + number);
		switch(number){
		
		case 1:
			ShieldMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(20, 25));
			i.hover();
			m.sleep(Random.nextInt(200,1000));
			ctx.mouse.scroll(false);
			ShieldMainBody.antiPattern = false;
			break;
			
		case 2:
			ShieldMainBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 3:
			ShieldMainBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 4:
			ShieldMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(7, 20));
				i.hover();
				ctx.mouse.scroll(true);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				ShieldMainBody.antiPattern = false;
			
			break;
			
		case 5:
			ShieldMainBody.state = st;
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 6:
			
			ShieldMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 7:
			ShieldMainBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 8:
			ShieldMainBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 9:
			ShieldMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 10:
			ShieldMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			ShieldMainBody.antiPattern = false;
			break;
			
			
			
		case 11:
			ShieldMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				ShieldMainBody.antiPattern = false;
				break;
				
		case 12:
			ShieldMainBody.state = st;
			for(GameObject g : ctx.objects.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(Random.nextInt(300, 700), Random.nextInt(1100, 1400)));
					break;
				}else {
					ctx.camera.turnTo(g);
					m.sleep(Random.nextInt(Random.nextInt(1300, 1700), Random.nextInt(2100, 2400)));
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
				
				break;
			}
				ShieldMainBody.antiPattern = false;
				break;
				
		case 13:
			ShieldMainBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
				
				break;
			}
				ShieldMainBody.antiPattern = false;
				break;
				
		case 14:
			ShieldMainBody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 15:
			ShieldMainBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 16:
			ShieldMainBody.state = st;
			ctx.mouse.scroll(true);
			ShieldMainBody.antiPattern = false;
			break;
			
		case 17:
			ShieldMainBody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					break;
				}
			}
			ShieldMainBody.antiPattern = false;
			break;
		case 18:
			ShieldMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
				break;
			}
			ShieldMainBody.antiPattern = false;
			break;
		case 19:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(1000, 4000));
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 20:
			ShieldMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}
				break;
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 21:
			ShieldMainBody.state = st;
			for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
					break;
				}else ctx.camera.turnTo(rock);
				break;
			}
			ShieldMainBody.antiPattern = false;
			break;
			
			
			
		case 22:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(2000, 3500));
				ctx.mouse.move(f.x-Random.nextInt(50, 400),f.y+Random.nextInt(33, 330));
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 23:
			ShieldMainBody.state = st;
			for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
					break;
				}else ctx.camera.turnTo(rock);
				break;
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 24:
			ShieldMainBody.state = st;
			   for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
					break;
				}else ctx.camera.turnTo(rock);
				break;
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 25:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(3000, 5500));
				ctx.mouse.move(f.x-Random.nextInt(-40, -600),f.y+Random.nextInt(-43, -230));
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 26:
			ShieldMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(4, 10));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(600,1300));
				ctx.mouse.move(f.x-Random.nextInt(-50, -100),f.y+Random.nextInt(400, -650));
				m.sleep(Random.nextInt(300,500));
				ShieldMainBody.antiPattern = false;
				break;
				
		case 27:
			ShieldMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(900,1700));
				ctx.mouse.move(f.x-Random.nextInt(+450, +600),f.y+Random.nextInt(-400, 350));
				ShieldMainBody.antiPattern = false;
				break;
				
		case 28:
			ShieldMainBody.state = st;
			
			if(ctx.bank.opened()){
				i = ctx.bank.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(500,2700));
				ctx.mouse.move(f.x-Random.nextInt(-450, -600),f.y+Random.nextInt(-500, 150));
			}

				ShieldMainBody.antiPattern = false;
				break;
				
		case 29:
			ShieldMainBody.state = st;
			
			if(ctx.bank.opened()){
				i = ctx.bank.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(500,2700));
				ctx.mouse.move(f.x-Random.nextInt(-450, -600),f.y+Random.nextInt(-500, 150));
			}else ctx.bank.open();

				ShieldMainBody.antiPattern = false;
				break;
				
		case 30:
			for(GroundItem rock : ctx.groundItems.select().id(2150,2162).first()){//random ground items on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
				}else ctx.camera.turnTo(rock);
				break;
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 31:
			if(ctx.hud.open(Window.CLAN_CHAT)){
				m.sleep(Random.nextInt(1000, 2400));
				ctx.mouse.move(f.x-Random.nextInt(100, 200),f.y+Random.nextInt(430, 330));
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 32:
			ShieldMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(400, 700));
					ctx.mouse.move(f.x-Random.nextInt(0300, 110),f.y+Random.nextInt(230, -170));
				}else ctx.camera.turnTo(g.tile().derive(2, 4));
			}
			ShieldMainBody.antiPattern = false;
			break;
			
		case 33:
			ShieldMainBody.state = st;
			ctx.camera.pitch(Random.nextInt(70, 90));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 34:
			ShieldMainBody.state = st;
			ctx.camera.pitch(Random.nextInt(50, 60));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 35:
			ShieldMainBody.state = st;
			ctx.camera.pitch(Random.nextInt(76, 85));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 36:
			ShieldMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(10, -200),f.y+Random.nextInt(-200, -250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			ShieldMainBody.antiPattern = false;
			break;
			
		case 37:
		    ctx.mouse.click(false);
			ShieldMainBody.antiPattern = false;
			break;
			
			
		case 38:
		    ctx.mouse.click(true);
			ShieldMainBody.antiPattern = false;
			break;
			
				
			default:
				ShieldMainBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.component(1401,36).visible()){
			ShieldMainBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.component(1401,36).click();//close button
		}
	}
	
	
}
