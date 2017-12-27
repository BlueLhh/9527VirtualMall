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
		// ��ȡ�������֤��
		String code = request.getParameter("code");
		HttpSession session = request.getSession(false);
		// ��ȡͼƬ�ϵ���֤��
		String validateCode="";
		if (session!=null) {
			validateCode = (String) session.getAttribute("validateCode");
			// ���������֤��session
			session.invalidate();
		}
		// �Ա���֤��
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
