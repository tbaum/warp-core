package com.wideplay.warp.components.core;

import com.google.inject.Injector;
import com.wideplay.warp.components.AttributesInjectable;
import com.wideplay.warp.module.componentry.Renderable;
import com.wideplay.warp.module.pages.PageClassReflection;
import com.wideplay.warp.rendering.ComponentHandler;
import com.wideplay.warp.rendering.HtmlWriter;
import com.wideplay.warp.util.beans.BeanUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dhanji
 * Date: Aug 13, 2007
 * Time: 7:43:00 PM
 *
 * A component not unlike TextField, but that outputs a textarea instead. It doesnt do any
 * fancy rich text editing, so you can still use javascript rich text (of your choice) decorators
 * on top of this component.
 *
 */
public class TextArea implements Renderable, AttributesInjectable {
    private Map<String, Object> attribs;
    private String bind;

    public void render(HtmlWriter writer, List<? extends ComponentHandler> nestedComponents, Injector injector, PageClassReflection reflection, Object page) {
        Object[] attributes = ComponentSupport.getTagAttributesExcept(attribs, "name");

        String id = writer.makeIdFor(this);

        writer.registerInputBinding(id);
        writer.elementWithAttrs("textarea", new Object[] { "id", id, "name", bind }, attributes);
        writer.writeRaw(BeanUtils.getFromPropertyExpression(bind, page).toString());
        writer.end("textarea");
    }

    public void setAttributeNameValuePairs(Map<String, Object> attribs) {
        this.attribs = attribs;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public Map<String, Object> getAttributeNameValuePairs() {
        return attribs;
    }
}
