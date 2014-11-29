package oldschooltutorial;

import java.awt.Point;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Player;





public class TutorialAntipattern extends TutorialNode{

	public TutorialAntipattern(ClientContext ctx) {
		super(ctx);
	}

	TutorialMethod m = new TutorialMethod(ctx);
	@Override
	public boolean activate() {
		return TutorialBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,40), Random.nextInt(1, 40));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		System.out.println("Antipattern");
		switch(number){
		
		case 1:
			
			TutorialBody.state = st;
			if(m.inventoryContains(526)){
				m.interactInventory(526, "Bury", "Bones");
			}
			//ctx.game.sleep(100,400);
			TutorialBody.antiPattern = false;
			break;
			
		case 2:
			TutorialBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.mouse.click(false);
			}
			TutorialBody.antiPattern = false;
			break;
			
		case 3:
			TutorialBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			TutorialBody.antiPattern = false;
			break;
			
		case 4:
		
			break;
			
		case 5:
			TutorialBody.state = st;
			ctx.mouse.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			TutorialBody.antiPattern = false;
			break;
			
		case 6:
			
			TutorialBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			TutorialBody.antiPattern = false;
			break;
			
		case 7:
			TutorialBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			TutorialBody.antiPattern = false;
			break;
			
		case 8:
			TutorialBody.state = st;
			ctx.mouse.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			TutorialBody.antiPattern = false;
			break;
			
		case 9:
			TutorialBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			TutorialBody.antiPattern = false;
			break;
			
		case 10:
			TutorialBody.state = st;
			ctx.mouse.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.mouse.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			TutorialBody.antiPattern = false;
			break;
			
	
				
		case 12:
			TutorialBody.state = st;
			  if(m.objIsNotNull(66946)&&m.getObject(66946).inViewport()){
				  m.interactO(66946, "Attack", "Attack snow");
				  m.sleep(Random.nextInt(2300, 4000));
			  }
				TutorialBody.antiPattern = false;
				break;
				
		case 13:
			TutorialBody.state = st;
		   
				TutorialBody.antiPattern = false;
				break;
				
		case 14:
			TutorialBody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			TutorialBody.antiPattern = false;
			break;
			
		case 15:
			TutorialBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			TutorialBody.antiPattern = false;
			break;
			
		case 16:
			TutorialBody.state = st;
			ctx.mouse.scroll(true);
			TutorialBody.antiPattern = false;
			break;
			
		case 17:
			TutorialBody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
				}
			}
			TutorialBody.antiPattern = false;
			break;
		case 18:
			TutorialBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.mouse.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
			}
			TutorialBody.antiPattern = false;
			break;
		case 19:
			
			break;
			
		case 20:
			TutorialBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.mouse.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.mouse.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}
			}
			TutorialBody.antiPattern = false;
			break;
			
			default:
				TutorialBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		
	}
	
	
}
