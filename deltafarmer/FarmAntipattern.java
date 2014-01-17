package deltafarmer;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Item;

public class FarmAntipattern extends FarmNode{

	public FarmAntipattern(MethodContext ctx) {
		super(ctx);
	}

		private Random rand = new Random();
		FarmMethod m = new FarmMethod(ctx);
	@Override
	public boolean activate() {
		return FarmBody.antiPattern;
	}

	@Override
	public void execute() {
		
		
		int number = rand.nextInt(0, 30);
		String st = "Performing antipattern technique";
		switch(number){
		
		case 1:
			FarmBody.state = st;
			ctx.camera.setAngle(60);
			ctx.game.sleep(200,400);
			FarmBody.antiPattern = false;
			break;
			
		case 2:
			FarmBody.state = st;
			ctx.camera.setAngle(90);
			ctx.game.sleep(600,900);
			FarmBody.antiPattern = false;
			break;
			
		case 3:
			FarmBody.state = st;
			ctx.camera.setAngle(10);
			ctx.game.sleep(700,1000);
			FarmBody.antiPattern = false;
			break;
			
		case 4:
			FarmBody.state = st;
			for(Item i: ctx.backpack.getAllItems()){
				i.hover();
				i.click(false);
				ctx.game.sleep(1700,2000);
				ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(300, 500));
				ctx.game.sleep(100,200);
				FarmBody.antiPattern = false;
				break;
			}
			break;
			
		case 5:
			FarmBody.state = st;
			ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(100, 300));
			ctx.game.sleep(700,1000);
			FarmBody.antiPattern = false;
			break;
			
			default:
				FarmBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.get(1401,36).isVisible()){
			FarmBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.get(1401,36).click();//close button
		}
	}
	
	
}
