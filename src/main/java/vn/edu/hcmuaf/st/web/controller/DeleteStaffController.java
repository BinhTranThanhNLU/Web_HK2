package vn.edu.hcmuaf.st.web.controller;

import vn.edu.hcmuaf.st.web.service.AccountService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteStaffController", urlPatterns = "/admin/delete-staff")
public class DeleteStaffController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        accountService.deleteUserById(id);
        response.sendRedirect(request.getContextPath() + "/admin/staff-list");
    }
}