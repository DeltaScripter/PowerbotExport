package quests;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
 
/**
* Author: Aadil Farouk
* Date: 6/17/13
* Time: 3:57 PM
*/
 
public abstract class Job extends MethodProvider {
public Job(MethodContext ctx) {
super(ctx);
}
 
/* override this to extend the sleep time */
public int delay() {
return 250;
}
 
/* returns the priority of the job. higher priority = executed first */
public int priority() {
return 0;
}
 
public abstract boolean activate();
 
public abstract void execute();
}