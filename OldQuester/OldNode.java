package OldQuester;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;




/**
* Created For Educational Purposes, please do not use maliciously.
* User: StubbornBurritos
* Date: 17/06/13
* Time: 16:36
* To change this template use File | Settings | File Templates.
*/

    public abstract class OldNode extends ClientAccessor {

    protected ClientContext ctx;

    public OldNode(ClientContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    public abstract boolean activate();

    public abstract void execute();

}