package divination;

import java.awt.Point;

import org.powerbot.script.lang.BasicNamedQuery;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;

public class DivineAntipattern extends DivineNode{

	public DivineAntipattern(MethodContext ctx) {
		super(ctx);
	}

		private Random rand = new Random();
		DivineMethod m = new DivineMethod(ctx);
	@Override
	public boolean activate() {
		return DivineBody.antiPattern;
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
			DivineBody.state = st;
			ctx.camera.setAngle(60);
			ctx.game.sleep(200,400);
			DivineBody.antiPattern = false;
			break;
			
		case 2:
			DivineBody.state = st;
			ctx.camera.setAngle(90);
			ctx.game.sleep(600,900);
			DivineBody.antiPattern = false;
			break;
			
		case 3:
			DivineBody.state = st;
			ctx.camera.setAngle(10);
			ctx.game.sleep(700,1000);
			DivineBody.antiPattern = false;
			break;
			
		case 4:
			DivineBody.state = st;
			i = ctx.backpack.getItemAt(Random.nextInt(16, 20));
				i.hover();
				ctx.mouse.scroll(true);
				ctx.game.sleep(200,2000);
				ctx.mouse.move(f.x-rand.nextInt(0, 200),f.y+rand.nextInt(200, 250));
				ctx.game.sleep(1956,3200);
				DivineBody.antiPattern = false;
			
			break;
			
		case 5:
			DivineBody.state = st;
			ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(100, 300));
			ctx.game.sleep(700,1000);
			DivineBody.antiPattern = false;
			break;
			
		case 6:
			
			DivineBody.state = st;
			ctx.mouse.move(f.x+rand.nextInt(40, 70),f.y-rand.nextInt(20, 70));
			ctx.game.sleep(Random.nextInt(300, 1900));
			DivineBody.antiPattern = false;
			break;
			
		case 7:
			DivineBody.state = st;
			ctx.mouse.move(f.x-rand.nextInt(20, 100),f.y+rand.nextInt(40, 200));
			ctx.game.sleep(Random.nextInt(1300, 2900));
			DivineBody.antiPattern = false;
			break;
			
		case 8:
			DivineBody.state = st;
			ctx.mouse.move(f.x-rand.nextInt(20, 100),f.y+rand.nextInt(40, 200));
			ctx.game.sleep(Random.nextInt(100, 900));
			DivineBody.antiPattern = false;
			break;
			
		case 9:
			DivineBody.state = st;
			ctx.mouse.move(f.x+rand.nextInt(0, 200),f.y+rand.nextInt(100, 300));
			ctx.game.sleep(Random.nextInt(100, 900));
			DivineBody.antiPattern = false;
			break;
			
		case 10:
			DivineBody.state = st;
			ctx.mouse.move(f.x+rand.nextInt(0, 200),f.y+rand.nextInt(200, 250));
			ctx.mouse.scroll(true);
			ctx.game.sleep(Random.nextInt(100, 900));
			DivineBody.antiPattern = false;
			break;
			
		case 11:
			DivineBody.state = st;
			i = ctx.backpack.getItemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				ctx.game.sleep(200,2000);
				ctx.mouse.move(f.x-rand.nextInt(0, 200),f.y+rand.nextInt(200, 250));
				ctx.game.sleep(1956,3200);
				DivineBody.antiPattern = false;
				break;
				
		case 12:
			DivineBody.state = st;
			if(!ctx.widgets.get(131,DivineBody.convertType).isValid()){
				if(ctx.hud.view(Window.SKILLS)){
					ctx.mouse.move(skills.x+Random.nextInt(1, 2),skills.y-Random.nextInt(1, 2));
					ctx.mouse.scroll(false);
					ctx.game.sleep(1200,1800);
					ctx.mouse.move(skills.x+Random.nextInt(1, 2),skills.y+Random.nextInt(1, 2));
					ctx.game.sleep(Random.nextGaussian(400, 1000, 500));
				}
				ctx.hud.view(Window.BACKPACK);
			}
				DivineBody.antiPattern = false;
				break;
				
		case 13:
			DivineBody.state = st;
		    if(!ctx.widgets.get(131,DivineBody.convertType).isValid()){
				if(ctx.hud.view(Window.FRIENDS)){
					ctx.mouse.move(skills.x+Random.nextInt(1, 4),skills.y-Random.nextInt(3, 10));
					ctx.mouse.scroll(false);
					ctx.mouse.move(skills.x-Random.nextInt(4, 14),skills.y-Random.nextInt(0, 3));
					ctx.game.sleep(Random.nextGaussian(2200, 2600, 1500));
				}
				ctx.hud.view(Window.BACKPACK);
				ctx.mouse.move(f.x+Random.nextInt(-3, 4),f.y+Random.nextInt(-2, 3));
			}
				DivineBody.antiPattern = false;
				break;
				
		case 14:
			DivineBody.state = st;
			ctx.camera.setAngle(Random.nextInt(30, 60));
			ctx.game.sleep(100,400);
			ctx.camera.setPitch(65);
			ctx.game.sleep(1800,2400);
			DivineBody.antiPattern = false;
			break;
			
		case 15:
			DivineBody.state = st;
			ctx.camera.setAngle(Random.nextInt(30, 60));
			ctx.game.sleep(200,600);
			ctx.camera.setPitch(Random.nextInt(70, 80));
			ctx.game.sleep(1400,2600);
			DivineBody.antiPattern = false;
			break;
			
		case 16:
			DivineBody.state = st;
			ctx.mouse.scroll(false);
			DivineBody.antiPattern = false;
			break;
			
		case 17:
			DivineBody.state = st;
			for(GameObject g : ctx.objects.select().name("").first()){
				if(g.isOnScreen()){
					ctx.mouse.move(g);
				}
			}
			DivineBody.antiPattern = false;
			break;
			
			default:
				DivineBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.get(1401,36).isVisible()){
			DivineBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.get(1401,36).click();//close button
		}
	}
	
	
}
