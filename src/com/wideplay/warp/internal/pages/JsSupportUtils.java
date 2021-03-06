package com.wideplay.warp.internal.pages;

import com.wideplay.warp.rendering.HtmlWriter;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * On: 24/03/2007
 *
 * @author Dhanji R. Prasanna (dhanji at gmail com)
 * @since 1.0
 */
class JsSupportUtils {
    static String wrapOnFrameLoadFn(StringBuilder content) {
        //insert content in reverse order at index 0
        content.insert(0, "{");
        content.insert(0, HtmlWriter.ON_FRAME_LOAD_FUNCTION);
        content.insert(0, "function ");

        //close func
        content.append("};");

        //append support functions to onFrameLoad
//        content.append(FN_PAGE_EVENT_PUBLISH);

        return content.toString();
    }
    
    static String wrapLinkedScripts(Set<String> linkedScripts, String contextPath) {
        StringBuilder builder = new StringBuilder();
        for (String script : linkedScripts) {
            builder.append("<script type=\"text/javascript\" src=\"");

            //add context path as necessary
            builder.append(contextPath);
            if (!script.startsWith("/"))
                builder.append("/");

            builder.append(script);
            builder.append("\"></script>");
        }

        return builder.toString();
    }

}
