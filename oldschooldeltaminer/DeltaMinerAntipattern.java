package oldschooldeltaminer;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Player;





public class DeltaMinerAntipattern extends DeltaMinerNode{

	public DeltaMinerAntipattern(ClientContext ctx) {
		super(ctx);
	}

	DeltaMinerMethod m = new DeltaMinerMethod(ctx);
	@Override
	public boolean activate() {
		return DeltaMinerBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,40), Random.nextInt(1, 35));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		System.out.println("Antipattern");
		switch(number){
		
		case 1:
			
			DeltaMinerBody.state = "Performing antipattern technique: Examine player";
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(800, 400),f.y+Random.nextInt(170, 0));
				}
			}
			
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 2:
			DeltaMinerBody.state = "Performing antipattern technique: Examine ground item";
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 3:
			DeltaMinerBody.state = "Performing antipattern technique: Move camera # 1";
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 4:
			DeltaMinerBody.state ="Performing antipattern technique: Examine ground item #2";
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 600));
				}
			}
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 5:
			DeltaMinerBody.state = "Performing antipattern technique: Move mouse #1";
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 6:
			
			DeltaMinerBody.state = "Performing antipattern technique: Move mouse #2";
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 7:
			DeltaMinerBody.state = "Performing antipattern technique: Move mouse #3";
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 8:
			DeltaMinerBody.state = "Performing antipattern technique: Moove mouse #4";
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 9:
			DeltaMinerBody.state = "Performing antipattern technique: Move mouse #5";
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 10:
			DeltaMinerBody.state = "Performing antipattern technique: Move mouse and scroll";
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			DeltaMinerBody.antiPattern = false;
			break;
			
	
				
		case 12:
			DeltaMinerBody.state = "Performing antipattern technique: Nothing";
			 
				DeltaMinerBody.antiPattern = false;
				break;
				
		case 13:
			DeltaMinerBody.state = "Performing antipattern technique: Nothing";
		         
				DeltaMinerBody.antiPattern = false;
				break;
				
		case 14:
			DeltaMinerBody.state = "Performing antipattern technique: Camera angle change";
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 15:
			DeltaMinerBody.state = "Performing antipattern technique: Camera angle change #2";
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 16:
			DeltaMinerBody.state = "Performing antipattern technique: Scroll";
			ctx.mouse.scroll(true);
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 17:
			DeltaMinerBody.state = "Performing antipattern technique: Hover mouse on ground item";
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
			}
			DeltaMinerBody.antiPattern = false;
			break;
		case 18:
			DeltaMinerBody.state = "Performing antipattern technique: Examine player #2";
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
			}
			DeltaMinerBody.antiPattern = false;
			break;
		case 19:
			DeltaMinerBody.state ="Performing antipattern technique: Examine inventory item";
			for(Item g : ctx.inventory.select().first()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(100, 400));
					if(ctx.menu.opened())
					for(String n: ctx.menu.items()){
						if(n.contains("Examine")){
							g.interact("Examine");
							break;
						}
					}
					ctx.mouse.move(f.x-Random.nextInt(400, 650),f.y+Random.nextInt(17, 210));
				
			}
			DeltaMinerBody.antiPattern = false;
			break;
			
		case 20:
			DeltaMinerBody.state = "Performing antipattern technique: Examine player #3";
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}
			}
			DeltaMinerBody.antiPattern = false;
			break;
			
			default:
				DeltaMinerBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		
	}
	
	
}
