package com.wideplay.warp.components.core;

import com.wideplay.warp.annotations.Component;
import com.wideplay.warp.module.componentry.Renderable;
import com.wideplay.warp.rendering.ComponentHandler;
import com.wideplay.warp.rendering.PageRenderException;
import com.wideplay.warp.rendering.RenderingContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * On: 24/03/2007
 *
 * Projects enclosed components across a collection of items (akin to map function in lisp).
 *
 * @author Dhanji R. Prasanna
 * @since 1.0
 */
@Component
public class Repeater implements Renderable {
    private Object items;
    private String var;

    public void render(RenderingContext context, List<? extends ComponentHandler> nestedComponents) {

        //obtain the bound object
        Object itemsObject = items;

        //see if it is an iterable
        if (itemsObject instanceof Iterable)
            for (Object item : (Iterable) itemsObject) {
                context.getContextVars().put(var, item);
                ComponentSupport.renderMultiple(context, nestedComponents);
            }

        else if (itemsObject instanceof Object[])
            //must be an array
            for (Object item : (Object[])itemsObject) {
                context.getContextVars().put(var, item);
                ComponentSupport.renderMultiple(context, nestedComponents);
            }
        else
            throw new PageRenderException("Repeater can only repeat over instances of Iterables or arrays: " + items);

        //clear the repeater context var
        context.getContextVars().remove(var);
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
