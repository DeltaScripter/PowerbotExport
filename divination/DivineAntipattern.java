package divination;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
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
		
		
		int number = rand.nextInt(0, 23);
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
			for(Item i: ctx.backpack.getAllItems()){
				i.hover();
				i.click(false);
				ctx.game.sleep(1700,2000);
				ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(300, 500));
				ctx.game.sleep(100,200);
				DivineBody.antiPattern = false;
				break;
			}
			break;
			
		case 5:
			DivineBody.state = st;
			ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(100, 300));
			ctx.game.sleep(700,1000);
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
