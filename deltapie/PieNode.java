package deltapie;



import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

/**
* Created For Educational Purposes, please do not use maliciously.
* User: StubbornBurritos
* Date: 17/06/13
* Time: 16:36
* To change this template use File | Settings | File Templates.
*/

    public abstract class PieNode extends MethodProvider {

    protected MethodContext ctx;

    public PieNode(MethodContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    public abstract boolean activate();

    public abstract void execute();

}