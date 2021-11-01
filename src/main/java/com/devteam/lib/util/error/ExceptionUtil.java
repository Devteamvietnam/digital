package com.devteam.lib.util.error;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author ddthien
 * @version 1.0
 * @apiNote https://www.geeksforgeeks.org/throwable-getstacktrace-method-in-java-with-examples/
 */
public class ExceptionUtil {

    static public String getStackTrace(Throwable t) {
        if(t == null) return null;
        StringWriter swriter = new StringWriter();
        PrintWriter pwriter = new PrintWriter(swriter);
        t.printStackTrace(pwriter);
        pwriter.close();
        return swriter.toString();
    }

    static public String toString(StackTraceElement[] elements) {
        String result = "";
        for(StackTraceElement element : elements) {
            result += "  " + element.toString() + "\n";
        }
        return result;
    }
}
