package com.luzko.libraryapp.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class FooterTag extends TagSupport {
    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<hr style=\"height: 2px; margin: 0px; background-color: darkred;\"/>" +
                    "<section class=\"contact-section bg-black\" id=\"contact\" style=\"padding: 15px;\">\n" +
                    "    <div class=\"container\">\n" +
                    "\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col-md-4 mb-3 mb-md-0\">\n" +
                    "                <div class=\"card py-4 h-100\">\n" +
                    "                    <div class=\"card-body text-center\">\n" +
                    "                        <h4 class=\"text-uppercase m-0\">address</h4>\n" +
                    "                        <hr class=\"my-4\">\n" +
                    "                        <div>Minsk, st. Kuprevich, 1</div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"col-md-4 mb-3 mb-md-0\">\n" +
                    "                <div class=\"card py-4 h-100\">\n" +
                    "                    <div class=\"card-body text-center\">\n" +
                    "                        <h4 class=\"text-uppercase m-0\">email</h4>\n" +
                    "                        <hr class=\"my-4\">\n" +
                    "                        <div>\n" +
                    "                            libraryapp.app@gmail.com\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"col-md-4 mb-3 mb-md-0\">\n" +
                    "                <div class=\"card py-4 h-100\">\n" +
                    "                    <div class=\"card-body text-center\">\n" +
                    "                        <h4 class=\"text-uppercase m-0\">phone</h4>\n" +
                    "                        <hr class=\"my-4\">\n" +
                    "                        <div>\n" +
                    "                            +375333333333\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</section>" +
                    "<hr style=\"height: 2px; margin: 0px; background-color: darkred;\"/>"
            );
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}

