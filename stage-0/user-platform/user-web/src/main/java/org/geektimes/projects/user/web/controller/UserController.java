package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/user")
public class UserController implements PageController {

    @Resource(name = "/bean/UserService")
    private UserService userService;

    @POST
    @Path("/register")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        // user object
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        request.getServletContext().log(user.toString());

        try {
            userService.register(user);
        }
        catch (Throwable t) {
            return "fail.jsp";
        }
        return "success.jsp";
    }
}
