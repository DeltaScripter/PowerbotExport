package kebbithunter;

import java.awt.Point;

import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.GroundItem;
import org.powerbot.script.rt6.MobileIdNameQuery;
import org.powerbot.script.rt6.Hud.Window;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.Player;




public class KebAntipattern extends KebNode{

	public KebAntipattern(ClientContext ctx) {
		super(ctx);
	}

	KebMethod m = new KebMethod(ctx);
	@Override
	public boolean activate() {
		return KebBody.antiPattern;
	}

	@Override
	public void execute() {
		Point f = ctx.input.getLocation();
		
		int number = Random.nextInt(Random.nextInt(1,30), Random.nextInt(1, 30));
		//System.out.println(number);
		Item i;
		String st = "Performing antipattern technique";
		//System.out.println("Antipattern");
		switch(number){
		
		case 1:
			
			KebBody.state = st;
			if(m.inventoryContains(526)){
				m.interactInventory(526, "Bury", "Bones");
			}
			//ctx.game.sleep(100,400);
			KebBody.antiPattern = false;
			break;
			
		case 2:
			KebBody.state = st;
			for(GroundItem g : ctx.groundItems.select()){
				if(g.inViewport()){
					ctx.input.move(g.centerPoint());
				}
				if(!ctx.menu.opened())
					ctx.input.click(false);
			}
			KebBody.antiPattern = false;
			break;
			
		case 3:
			KebBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 50));
			m.sleep(Random.nextInt(700,1000));
			KebBody.antiPattern = false;
			break;
			
		case 4:
			KebBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(7, 20));
				i.hover();
				ctx.input.scroll(true);
				m.sleep(Random.nextInt(200,2000));
				ctx.input.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				KebBody.antiPattern = false;
			
			break;
			
		case 5:
			KebBody.state = st;
			ctx.input.move(Random.nextInt(100, 300),Random.nextInt(100, 300));
			m.sleep(Random.nextInt(700,1000));
			KebBody.antiPattern = false;
			break;
			
		case 6:
			
			KebBody.state = st;
			ctx.input.move(f.x+Random.nextInt(40, 70),f.y-Random.nextInt(20, 70));
			m.sleep(Random.nextInt(300, 1900));
			KebBody.antiPattern = false;
			break;
			
		case 7:
			KebBody.state = st;
			ctx.input.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(1300, 2900));
			KebBody.antiPattern = false;
			break;
			
		case 8:
			KebBody.state = st;
			ctx.input.move(f.x-Random.nextInt(20, 100),f.y+Random.nextInt(40, 200));
			m.sleep(Random.nextInt(100, 900));
			KebBody.antiPattern = false;
			break;
			
		case 9:
			KebBody.state = st;
			ctx.input.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(100, 300));
			m.sleep(Random.nextInt(100, 900));
			KebBody.antiPattern = false;
			break;
			
		case 10:
			KebBody.state = st;
			ctx.input.move(f.x+Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
			ctx.input.scroll(true);
			m.sleep(Random.nextInt(100, 900));
			KebBody.antiPattern = false;
			break;
			
		case 11:
			KebBody.state = st;
			i = ctx.backpack.itemAt(Random.nextInt(2, 25));
				i.hover();
				i.click(false);
				m.sleep(Random.nextInt(200,2000));
				ctx.input.move(f.x-Random.nextInt(0, 200),f.y+Random.nextInt(200, 250));
				m.sleep(Random.nextInt(1956,3200));
				KebBody.antiPattern = false;
				break;
				
		case 12:
			KebBody.state = st;
			  if(m.objIsNotNull(66946)&&m.getObject(66946).inViewport()){
				  m.interactO(66946, "Attack", "Attack snow");
				  m.sleep(Random.nextInt(2300, 4000));
			  }
				KebBody.antiPattern = false;
				break;
				
		case 13:
			KebBody.state = st;
		   System.out.println("Antipattern, not filled");
				KebBody.antiPattern = false;
				break;
				
		case 14:
			KebBody.state = st;
			ctx.camera.angle(Random.nextInt(30, 60));
			m.sleep(Random.nextInt(100,400));
			if(Random.nextInt(1, 30)>15){
			ctx.camera.pitch(40);
			m.sleep(Random.nextInt(1000,2400));
			}
			KebBody.antiPattern = false;
			break;
			
		case 15:
			KebBody.state = st;
			ctx.camera.angle(Random.nextInt(10, 60));
			m.sleep(Random.nextInt(200,600));
			KebBody.antiPattern = false;
			break;
			
		case 16:
			KebBody.state = st;
			ctx.input.scroll(true);
			KebBody.antiPattern = false;
			break;
			
		case 17:
			KebBody.state = st;
			for(GameObject g : ctx.objects.select().first()){
				if(g.inViewport()){
					ctx.input.move(g.centerPoint());
				}
			}
			KebBody.antiPattern = false;
			break;
		case 18:
			KebBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.input.move(g.centerPoint());
					if(!ctx.menu.opened())
					ctx.input.click(false);
					m.sleep(Random.nextInt(1200, 1600));
					ctx.input.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(0, 170));
				}
			}
			KebBody.antiPattern = false;
			break;
		case 19:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(1000, 4000));
			}
			KebBody.antiPattern = false;
			break;
			
		case 20:
			KebBody.state = st;
			for(Player g : ctx.players.select().first()){
				if(g.inViewport()){
					ctx.input.move(g.centerPoint());
					m.sleep(Random.nextInt(200, 600));
					ctx.input.move(f.x-Random.nextInt(300, 10),f.y+Random.nextInt(154, 23));
				}break;
			}
			KebBody.antiPattern = false;
			break;
			
		case 21:
			KebBody.state = st;
			for(GameObject rock : ctx.objects.select().id(66472,66496).first()){
				if(rock.inViewport()){
					ctx.input.move(rock.centerPoint());
					m.sleep(Random.nextInt(100, 340));
					ctx.input.move(f.x-Random.nextInt(100, 300),f.y+Random.nextInt(23, 230));
				}else ctx.camera.turnTo(rock);
			}
			KebBody.antiPattern = false;
			break;
			
		case 22:
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(2000, 3500));
				ctx.input.move(f.x-Random.nextInt(50, 400),f.y+Random.nextInt(33, 330));
			}
			KebBody.antiPattern = false;
			break;
			
		case 23:
			KebBody.state = st;
			ctx.input.click(true);
			KebBody.antiPattern = false;
			break;
			
		case 24:
			KebBody.state = st;
			ctx.input.click(true);
			m.sleep(Random.nextInt(200, 600));
			KebBody.antiPattern = false;
			break;
			
			
		case 25:
			KebBody.state = st;
			if(ctx.hud.open(Window.SKILLS)){
				m.sleep(Random.nextInt(100, 2000));
			}
			ctx.input.move(f.x-Random.nextInt(-50, -400),f.y+Random.nextInt(-33, -330));
			KebBody.antiPattern = false;
			break;
			
			
			
		case 26:
			
			KebBody.state = st;
			m.sleep(Random.nextInt(Random.nextInt(1200, 1300), Random.nextInt(1400, 1700)));
			KebBody.antiPattern = false;
			break;
			
			
	case 27:
			
			KebBody.state = st;
			for(Player g : ctx.players.select().first()){
				g.hover();
				g.click(false);
			}
			KebBody.antiPattern = false;
			break;
	case 28:
		
		KebBody.state = st;
		checkRock(new Tile(2872,3479,0),66471);
		KebBody.antiPattern = false;
		break;
		
	case 29:
		
		KebBody.state = st;
		for(GameObject rock : ctx.objects.select().id(66471,66470,66472,66473,66474).nearest().first()){
			rock.hover();
			rock.click();
			break;
		}
		KebBody.antiPattern = false;
		break;
		
	case 30:
		
		KebBody.state = st;
		for(GameObject rock : ctx.objects.select().id(66472,66473,66474).nearest().first()){
			rock.hover();
			rock.click(true);
			break;
		}
		KebBody.antiPattern = false;
		break;
			
			
			
			default:
				KebBody.antiPattern = false;
				break;
		
		}
	}//checkRock(new Tile(2872,3479,0),66471);
	private void checkRock(final Tile rockTile, final int id) {
		Tile local =ctx.players.local().tile();
		
		MobileIdNameQuery<GameObject> findRock =ctx.objects.select().select(new Filter<GameObject>() {
			public boolean accept(GameObject g) {
			return g.tile().distanceTo(rockTile)<5 && g.id()==id;
				
			}
	         });
		
		for (GameObject rock : findRock) {
			if (rock.tile().distanceTo(local) < 10 || rock.inViewport()) {
				
				if(!ctx.players.local().inMotion()
						&&ctx.players.local().animation()==-1)
				if (rock.inViewport()&&rock.interact("Inspect")) {
					//	System.out.println("Clicking on rock: " + ctx.players.local().animation());
				} else{
					//System.out.println("Turning camera rock");
					ctx.camera.turnTo(rock.tile().derive(1, 3));
				}
			} else {
				ctx.movement.step(rock.tile().derive(1, 4));
			}
		}
		
	}
	
	public void closeInteruptions(){
		//Reach Divination level cap; become a member dialogue
		while(ctx.widgets.component(1401,36).visible()){
			KebBody.state = "Closing 'Become a member!' advertisement";
			ctx.widgets.component(1401,36).click();//close button
		}
	}
	
	
}
