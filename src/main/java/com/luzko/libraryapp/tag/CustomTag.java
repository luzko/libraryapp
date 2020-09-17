package com.luzko.libraryapp.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class CustomTag extends TagSupport {
    private String login;
    private boolean status;
    private String value;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
           out.write("<section class=\"contact-section bg-black\" id=\"contact\">\n" +
                   "    <div class=\"container\">\n" +
                   "\n" +
                   "        <div class=\"row\">\n" +
                   "            <div class=\"col-md-4 mb-3 mb-md-0\">\n" +
                   "                <div class=\"card py-4 h-100\">\n" +
                   "                    <div class=\"card-body text-center\">\n" +
                   "                        <i class=\"fas fa-map-marked-alt text-primary mb-2\"></i>\n" +
                   "                        <h4 class=\"text-uppercase m-0\">Address</h4>\n" +
                   "                        <hr class=\"my-4\">\n" +
                   "                        <div class=\"small text-black-50\">32 Vasnetsova, Minsk </div>\n" +
                   "                    </div>\n" +
                   "                </div>\n" +
                   "            </div>\n" +
                   "\n" +
                   "            <div class=\"col-md-4 mb-3 mb-md-0\">\n" +
                   "                <div class=\"card py-4 h-100\">\n" +
                   "                    <div class=\"card-body text-center\">\n" +
                   "                        <i class=\"fas fa-envelope text-primary mb-2\"></i>\n" +
                   "                        <h4 class=\"text-uppercase m-0\">Email</h4>\n" +
                   "                        <hr class=\"my-4\">\n" +
                   "                        <div class=\"small text-black-50\">\n" +
                   "                            <a href=\"#\">y.menya.ee.nety@gmail.com</a>\n" +
                   "                        </div>\n" +
                   "                    </div>\n" +
                   "                </div>\n" +
                   "            </div>\n" +
                   "\n" +
                   "            <div class=\"col-md-4 mb-3 mb-md-0\">\n" +
                   "                <div class=\"card py-4 h-100\">\n" +
                   "                    <div class=\"card-body text-center\">\n" +
                   "                        <i class=\"fas fa-mobile-alt text-primary mb-2\"></i>\n" +
                   "                        <h4 class=\"text-uppercase m-0\">Phone</h4>\n" +
                   "                        <hr class=\"my-4\">\n" +
                   "                        <div class=\"small text-black-50\">+375 (25) 951-85-76</div>\n" +
                   "                    </div>\n" +
                   "                </div>\n" +
                   "            </div>\n" +
                   "        </div>\n" +
                   "    </div>\n" +
                   "</section>");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
