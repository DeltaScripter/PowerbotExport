package kebbithunter;

import java.awt.Point;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;

public class KebAntipattern extends KebNode{

	public KebAntipattern(MethodContext ctx) {
		super(ctx);
	}

		private Random rand = new Random();
		KebMethod m = new KebMethod(ctx);
	@Override
	public boolean activate() {
		return KebBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.mouse.getLocation();
	     Point skills = ctx.widgets.get(Window.SKILLS.getWidget(),0).getCenterPoint();
		
		int number = rand.nextInt(0, 45);
		Item i;
		String st = "Performing antipattern technique";
		switch(number){
		
		case 1:
			KebBody.state = st;
			ctx.camera.setAngle(60);
			ctx.game.sleep(200,400);
			KebBody.antiPattern = false;
			break;
			
		case 2:
			KebBody.state = st;
			ctx.camera.setAngle(90);
			ctx.game.sleep(600,900);
			KebBody.antiPattern = false;
			break;
			
		case 3:
			KebBody.state = st;
			ctx.camera.setAngle(10);
			ctx.game.sleep(700,1000);
			KebBody.antiPattern = false;
			break;
			
		case 4:
			KebBody.state = st;
			i = ctx.backpack.getItemAt(Random.nextInt(16, 20));
				i.hover();
				ctx.mouse.scroll(true);
				ctx.game.sleep(200,2000);
				ctx.mouse.move(f.x-rand.nextInt(0, 200),f.y+rand.nextInt(200, 250));
				ctx.game.sleep(1956,3200);
				KebBody.antiPattern = false;
			
			break;
			
		case 5:
			KebBody.state = st;
			ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(100, 300));
			ctx.game.sleep(700,1000);
			KebBody.antiPattern = false;
			break;
			
		case 6:
			
			KebBody.state = st;
			ctx.mouse.move(f.x+rand.nextInt(40, 70),f.y-rand.nextInt(20, 70));
			ctx.game.sleep(Random.nextInt(300, 1900));
			KebBody.antiPattern = false;
			break;
			
		case 7:
			KebBody.state = st;
			ctx.mouse.move(f.x-rand.nextInt(20, 100),f.y+rand.nextInt(40, 200));
			ctx.game.sleep(Random.nextInt(1300, 2900));
			KebBody.antiPattern = false;
			break;
			
		case 8:
			KebBody.state = st;
			ctx.mouse.move(f.x-rand.nextInt(20, 100),f.y+rand.nextInt(40, 200));
			ctx.game.sleep(Random.nextInt(100, 900));
			KebBody.antiPattern = false;
			break;
			
		case 9:
			KebBody.state = st;
			ctx.mouse.move(f.x+rand.nextInt(0, 200),f.y+rand.nextInt(100, 300));
			ctx.game.sleep(Random.nextInt(100, 900));
			KebBody.antiPattern = false;
			break;
			
		case 10:
			KebBody.state = st;
			ctx.mouse.move(f.x+rand.nextInt(0, 200),f.y+rand.nextInt(200, 250));
			ctx.mouse.scroll(true);
			ctx.game.sleep(Random.nextInt(100, 900));
			KebBody.antiPattern = false;
			break;
			
		case 11:
			KebBody.state = st;
			i = ctx.backpack.getItemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				ctx.game.sleep(200,2000);
				ctx.mouse.move(f.x-rand.nextInt(0, 200),f.y+rand.nextInt(200, 250));
				ctx.game.sleep(1956,3200);
				KebBody.antiPattern = false;
				break;
				
		case 12:
			KebBody.state = st;
			
				KebBody.antiPattern = false;
				break;
				
		case 13:
			KebBody.state = st;
		   
				KebBody.antiPattern = false;
				break;
				
		case 14:
			KebBody.state = st;
			ctx.camera.setAngle(Random.nextInt(30, 60));
			ctx.game.sleep(100,400);
			ctx.camera.setPitch(65);
			ctx.game.sleep(1800,2400);
			KebBody.antiPattern = false;
			break;
			
		case 15:
			KebBody.state = st;
			ctx.camera.setAngle(Random.nextInt(30, 60));
			ctx.game.sleep(200,600);
			ctx.camera.setPitch(Random.nextInt(70, 80));
			ctx.game.sleep(1400,2600);
			KebBody.antiPattern = false;
			break;
			
		case 16:
			KebBody.state = st;
			ctx.mouse.scroll(true);
			KebBody.antiPattern = false;
			break;
			
		case 17:
			KebBody.state = st;
			for(GameObject g : ctx.objects.select().name("").first()){
				if(g.isOnScreen()){
					ctx.mouse.move(g);
				}
			}
			KebBody.antiPattern = false;
			break;
			
			default:
				KebBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.get(1401,36).isVisible()){
			KebBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.get(1401,36).click();//close button
		}
	}
	
	
}
