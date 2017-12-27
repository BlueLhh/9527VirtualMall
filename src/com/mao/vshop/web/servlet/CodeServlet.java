package com.mao.vshop.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���������֤��
 * 
 * @author Administrator
 *
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 120; // ���ɵ�ͼƬ�Ŀ��
	public static final int HEIGHT = 35; // ���ɵ�ͼƬ�ĸ߶�

	public CodeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ڴ��д���һ��ͼƬ
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// �õ�ͼƬ
		Graphics g = image.getGraphics();

		// 1.����ͼƬ�ı���ɫ
		setBackGround(g);

		// 2.����ͼƬ�ı߿�
		setBorder(g);

		// 3.��ͼƬ�ϻ�������
		drawRandomLine(g);

		// 4.��ͼƬ��д��֤��
		String code = drawRandomNum((Graphics2D) g);
		request.getSession().setAttribute("validateCode", code); // �����������session��

		/*
		 * 5.ͼ��д�������
		 */
		response.setContentType("image/jpeg");
		// ����Ӧͷ�����������Ҫ����ͼƬ
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		ImageIO.write(image, "jpg", response.getOutputStream());

	}

	/*
	 * ��ͼƬ��д�����
	 */
	private String drawRandomNum(Graphics2D g) {
		// ������ɫ
		g.setColor(Color.RED);
		// ��������
		g.setFont(new Font("����", Font.BOLD, 20));
		// �������ֺʹ�д��ĸ������֤��
		String base = "1234567890QWERTYUIOPALKJHGFDSZXCVBNM";
		StringBuffer sb = new StringBuffer();
		int x = 5;
		// ��������
		for (int i = 0; i < 4; i++) {

			int degree = new Random().nextInt() % 30; // ����-30-30��Χ�������

			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			sb.append(ch);
			// д����֮ǰ�����ú���ת
			g.rotate(degree * Math.PI / 180, x, 20); // ����������ת�Ƕ�
			g.drawString(ch, x, 20);
			// �����ת����Ӱ����һ�ε���ת������Ҫ����һ�ε���ת�����ת��ȥ
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		return sb.toString();
	}

	/*
	 * ����ͼƬ�ı���ɫ
	 */
	private void setBackGround(Graphics g) {
		// ������ɫ
		g.setColor(Color.WHITE);
		// �������
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	/*
	 * ����ͼƬ�ı߿�
	 */
	private void setBorder(Graphics g) {
		// ���ñ߿���ɫ
		g.setColor(Color.BLUE);
		// �߿�����
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	/*
	 * ��ͼƬ�ϻ�������
	 */
	private void drawRandomLine(Graphics g) {
		// ������ɫ
		g.setColor(Color.GREEN);
		// ������������������
		for (int i = 0; i < 5; i++) {
			// ���ɸ������������ʼ����
			int x1 = new Random().nextInt(WIDTH); // ����0~WIDTH(������WIDTH)�������
			int y1 = new Random().nextInt(HEIGHT);

			// ���ɸ���������Ľ�������
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);

			g.drawLine(x1, y1, x2, y2);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
