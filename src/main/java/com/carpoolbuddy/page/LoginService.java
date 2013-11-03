package com.carpoolbuddy.page;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: alexus
 * Date: 11/3/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/login")
public class LoginService {

    @GET
    @Produces ({MediaType.TEXT_HTML})
    public String getDynamicPage() {
        return mockHtml();
    }

    private String mockHtml() {
        String mockHTMLPage = "<html><head>Mock Page</head><body>Welcome, this is the mock login page. \nThis will grab a login.html and present it.</body></html>";
        return mockHTMLPage;
    }
}
