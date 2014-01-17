package deltaartisan;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Item;

public class ArtisanAntipattern extends ArtisanNode{

	public ArtisanAntipattern(MethodContext ctx) {
		super(ctx);
	}

		private Random rand = new Random();
		ArtisanMethod m = new ArtisanMethod(ctx);
	@Override
	public boolean activate() {
		return ArtisanBody.antiPattern;
	}

	@Override
	public void execute() {
		
		
		int number = rand.nextInt(0, 65);
		String st = "Performing antipattern technique";
		switch(number){
		
		case 1:
			ArtisanBody.state = st;
			ctx.camera.setAngle(60);
			ctx.game.sleep(200,400);
			ArtisanBody.antiPattern = false;
			break;
			
		case 2:
			ArtisanBody.state = st;
			ctx.camera.setAngle(90);
			ctx.game.sleep(600,900);
			ArtisanBody.antiPattern = false;
			break;
			
		case 3:
			ArtisanBody.state = st;
			ctx.camera.setAngle(10);
			ctx.game.sleep(700,1000);
			ArtisanBody.antiPattern = false;
			break;
			
		case 4:
			ArtisanBody.state = st;
			for(Item i: ctx.backpack.getAllItems()){
				i.hover();
				i.click(false);
				ctx.game.sleep(1700,2000);
				ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(300, 500));
				ctx.game.sleep(100,200);
				ArtisanBody.antiPattern = false;
				break;
			}
			break;
			
		case 5:
			ArtisanBody.state = st;
			ctx.mouse.move(rand.nextInt(100, 300),rand.nextInt(100, 300));
			ctx.game.sleep(700,1000);
			ArtisanBody.antiPattern = false;
			break;
			
			default:
				ArtisanBody.antiPattern = false;
				break;
		
		}
	}
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.get(1401,36).isVisible()){
			ArtisanBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.get(1401,36).click();//close button
		}
	}
	
	
}
