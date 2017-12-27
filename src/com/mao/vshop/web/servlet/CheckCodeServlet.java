package com.mao.vshop.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckCodeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取输入的验证码
		String code = request.getParameter("code");
		HttpSession session = request.getSession(false);
		// 获取图片上的验证码
		String validateCode="";
		if (session!=null) {
			validateCode = (String) session.getAttribute("validateCode");
			// 清除保存验证码session
			session.invalidate();
		}
		// 对比验证码
		if (code.toLowerCase().equals(validateCode.toLowerCase())) {
			response.getWriter().write("true");
		} else {
			response.getWriter().write("false");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
