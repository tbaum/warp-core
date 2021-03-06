package com.wideplay.warp.components.core;

import com.wideplay.warp.annotations.Component;
import com.wideplay.warp.components.AttributesInjectable;
import com.wideplay.warp.module.componentry.Renderable;
import com.wideplay.warp.module.ioc.el.Expressions;
import com.wideplay.warp.rendering.ComponentHandler;
import com.wideplay.warp.rendering.HtmlWriter;
import com.wideplay.warp.rendering.RenderingContext;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * On: 24/03/2007
 *
 * @author Dhanji R. Prasanna (dhanji at gmail com)
 * @since 1.0
 */
@Component
public class TextField implements Renderable, AttributesInjectable {
    private String bind;
    private Map<String, Object> injectableAttributes;

    public void render(RenderingContext context, List<? extends ComponentHandler> nestedComponents) {
        HtmlWriter writer = context.getWriter();

        //read the bound getValue
        Object text = Expressions.evaluate(bind, context.getContextVars());

        String id = writer.makeIdFor(this);
        writer.registerInputBinding(id);

        //find css class from injectables
        String cssClass;
        Object[] attrs = (Object[]) injectableAttributes.get(RawText.WARP_RAW_TEXT_PROP_ATTRS);
        cssClass = ComponentSupport.discoverCssAttribute(attrs);

        if (null == cssClass)
            cssClass = "wText";

        writer.element("input",
                "id", id,
                "type", "text",
                "name", bind,
                "value", text,
                "class", cssClass);
        writer.end("input");
    }


    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }


    public void setAttributeNameValuePairs(Map<String, Object> attribs) {
        this.injectableAttributes = attribs;
    }

    public Map<String, Object> getAttributeNameValuePairs() {
        return injectableAttributes;
    }
}
