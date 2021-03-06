package piedishmaker;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Player;




public class PieDishAntipattern extends PieDishNode{

	public PieDishAntipattern(ClientContext ctx) {
		super(ctx);
	}

	PieDishMethod m = new PieDishMethod(ctx);
	@Override
	public boolean activate() {
		return PieDishBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,25), Random.nextInt(1, 20));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		//System.out.println("Antipattern");
		switch(number){
		
		case 1:
			
			PieDishBody.state = st;
			if(m.inventoryContains(526)){
				m.interactInventory(526, "Bury", "Bones");
			}
			//ctx.game.sleep(100,400);
			PieDishBody.antiPattern = false;
			break;
			
		case 2:
			PieDishBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
			PieDishBody.antiPattern = false;
			break;
			
		case 3:
			PieDishBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			PieDishBody.antiPattern = false;
			break;
			
		case 4:
			PieDishBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(7, 20));
				i.hover();
				ctx.mouse.scroll(true);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				PieDishBody.antiPattern = false;
			
			break;
			
		case 5:
			PieDishBody.state = st;
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			PieDishBody.antiPattern = false;
			break;
			
		case 6:
			
			PieDishBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			PieDishBody.antiPattern = false;
			break;
			
		case 7:
			PieDishBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			PieDishBody.antiPattern = false;
			break;
			
		case 8:
			PieDishBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			PieDishBody.antiPattern = false;
			break;
			
		case 9:
			PieDishBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			PieDishBody.antiPattern = false;
			break;
			
		case 10:
			PieDishBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			PieDishBody.antiPattern = false;
			break;
			
		case 11:
			PieDishBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(200,2000));
				ctx.mouse.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				PieDishBody.antiPattern = false;
				break;
				
		case 12:
			PieDishBody.state = st;
			  if(m.objIsNotNull(66946)&&m.getObject(66946).inViewport()){
				  m.interactO(66946, "Attack", "Attack snow");
				  m.sleep(Random.nextInt(2300, 4000));
			  }
				PieDishBody.antiPattern = false;
				break;
				
		case 13:
			PieDishBody.state = st;
		   
				PieDishBody.antiPattern = false;
				break;
				
		case 14:
			PieDishBody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			PieDishBody.antiPattern = false;
			break;
			
		case 15:
			PieDishBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			PieDishBody.antiPattern = false;
			break;
			
		case 16:
			PieDishBody.state = st;
			ctx.mouse.scroll(true);
			PieDishBody.antiPattern = false;
			break;
			
		case 17:
			PieDishBody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
			}
			PieDishBody.antiPattern = false;
			break;
		case 18:
			PieDishBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
			}
			PieDishBody.antiPattern = false;
			break;
		case 19:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(1000, 4000));
			}
			break;
			
		case 20:
			PieDishBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}
			}
			PieDishBody.antiPattern = false;
			break;
			
			default:
				PieDishBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.component(1401,36).visible()){
			PieDishBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.component(1401,36).click();//close button
		}
	}
	
	
}
