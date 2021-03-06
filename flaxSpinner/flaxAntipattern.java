package flaxSpinner;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Player;




public class flaxAntipattern extends FlaxNode{

	public flaxAntipattern(ClientContext ctx) {
		super(ctx);
	}

	FlaxMethod m = new FlaxMethod(ctx);
	@Override
	public boolean activate() {
		return FlaxMainBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,33), Random.nextInt(1, 33));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		//System.out.println("Antipattern");
		switch(number){
		
		case 1:
			
			FlaxMainBody.state = st;
			if(m.inventoryContains(FlaxStuff.FLAX)){
				m.interactInventory(FlaxStuff.FLAX, "Examine", "Flax");
			}
			m.sleep(Random.nextInt(1300, 1600));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 2:
			FlaxMainBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 3:
			FlaxMainBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 4:
			FlaxMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(7, 20));
				i.hover();
				//ctx.mouse.scroll(true);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				FlaxMainBody.antiPattern = false;
			
			break;
			
		case 5:
			FlaxMainBody.state = st;
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 6:
			
			FlaxMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 7:
			FlaxMainBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 8:
			FlaxMainBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 9:
			FlaxMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 10:
			FlaxMainBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			//ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 11:
			FlaxMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				FlaxMainBody.antiPattern = false;
				break;
				
		case 12:
			FlaxMainBody.state = st;
			  if(m.objIsNotNull(FlaxStuff.STAIRMIDDLE)&&m.getObject(FlaxStuff.STAIRMIDDLE).inViewport()){
				  m.interactO(FlaxStuff.STAIRMIDDLE, "Examine", "Middle stairs");
				  m.sleep(Random.nextInt(2300, 4000));
			  }
				FlaxMainBody.antiPattern = false;
				break;
				
		case 13:
			FlaxMainBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
				FlaxMainBody.antiPattern = false;
				break;
				
		case 14:
			FlaxMainBody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 15:
			FlaxMainBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			FlaxMainBody.antiPattern = false;
			break;
			
		case 16:
			FlaxMainBody.state = st;
			//ctx.mouse.scroll(true);
			FlaxMainBody.antiPattern = false;
			break;
			
		case 17:
			FlaxMainBody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
			}
			FlaxMainBody.antiPattern = false;
			break;
		case 18:
			FlaxMainBody.state = st;
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
			FlaxMainBody.antiPattern = false;
			break;
		case 19:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(1000, 4000));
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 20:
			FlaxMainBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}break;
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 21:
			FlaxMainBody.state = st;
			for(GameObject rock : ctx.objects.select().id(6910,36970,36774,36830,36846,84).first()){//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
				}else ctx.camera.turnTo(rock);break;
			}
			FlaxMainBody.antiPattern = false;
			break;
			
			
			
		case 22:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(2000, 3500));
				ctx.mouse.move(f.x-Random.nextInt(50, 400),f.y+Random.nextInt(33, 330));
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 23:
			FlaxMainBody.state = st;
			for(GameObject rock : ctx.objects.select().id(36786,45287,36775,6910,36970,36774,36830,36846,84).first()){//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
				}else ctx.camera.turnTo(rock);break;
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 24:
			FlaxMainBody.state = st;
			for(GameObject rock : ctx.objects.select().id(36786,45287,36775,6910,36970,36774,36830,36846,84).first()){//random objs on ground
				if(rock.inViewport()){
					ctx.mouse.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.mouse.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
				}else ctx.camera.turnTo(rock);break;
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 25:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(3000, 5500));
				ctx.mouse.move(f.x-Random.nextInt(-40, -600),f.y+Random.nextInt(-43, -230));
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 26:
			FlaxMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(4, 10));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(600,1300));
				ctx.mouse.move(f.x-Random.nextInt(-50, -100),f.y+Random.nextInt(400, -650));
				m.sleep(Random.nextInt(300,500));
				FlaxMainBody.antiPattern = false;
				break;
				
		case 27:
			FlaxMainBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(900,1700));
				ctx.mouse.move(f.x-Random.nextInt(+450, +600),f.y+Random.nextInt(-400, 350));
				FlaxMainBody.antiPattern = false;
				break;
				
		case 28:
			FlaxMainBody.state = st;
			
			if(ctx.bank.opened()){
				i = ctx.bank.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(500,2700));
				ctx.mouse.move(f.x-Random.nextInt(-450, -600),f.y+Random.nextInt(-500, 150));
			}

				FlaxMainBody.antiPattern = false;
				break;
				
		case 29:
			FlaxMainBody.state = st;
			
			if(ctx.bank.opened()){
				i = ctx.bank.itemAt(Random.nextInt(10, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(500,2700));
				ctx.mouse.move(f.x-Random.nextInt(-450, -600),f.y+Random.nextInt(-500, 150));
			}else ctx.bank.open();

				FlaxMainBody.antiPattern = false;
				break;
				
		case 30:
			if(ctx.hud.open(Window.WORN_EQUIPMENT)){
				m.sleep(Random.nextInt(100, 400));
				ctx.mouse.move(f.x-Random.nextInt(10, 20),f.y+Random.nextInt(43, 33));
			}
			FlaxMainBody.antiPattern = false;
			break;
			
		case 31:
			if(ctx.hud.open(Window.CLAN_CHAT)){
				m.sleep(Random.nextInt(1000, 2400));
				ctx.mouse.move(f.x-Random.nextInt(100, 200),f.y+Random.nextInt(430, 330));
			}
			FlaxMainBody.antiPattern = false;
			break;
			
				
			default:
				FlaxMainBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.component(1401,36).visible()){
			FlaxMainBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.component(1401,36).click();//close button
		}
	}
	
	
}
