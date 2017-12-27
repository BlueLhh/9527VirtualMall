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
 * 生成随机验证码
 * 
 * @author Administrator
 *
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 120; // 生成的图片的宽度
	public static final int HEIGHT = 35; // 生成的图片的高度

	public CodeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 在内存中创建一张图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 得到图片
		Graphics g = image.getGraphics();

		// 1.设置图片的背景色
		setBackGround(g);

		// 2.设置图片的边框
		setBorder(g);

		// 3.在图片上画干扰线
		drawRandomLine(g);

		// 4.在图片上写验证码
		String code = drawRandomNum((Graphics2D) g);
		request.getSession().setAttribute("validateCode", code); // 将随机数存在session中

		/*
		 * 5.图形写给浏览器
		 */
		response.setContentType("image/jpeg");
		// 发响应头控制浏览器不要缓存图片
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		ImageIO.write(image, "jpg", response.getOutputStream());

	}

	/*
	 * 在图片上写随机数
	 */
	private String drawRandomNum(Graphics2D g) {
		// 设置颜色
		g.setColor(Color.RED);
		// 设置字体
		g.setFont(new Font("宋体", Font.BOLD, 20));
		// 根据数字和大写字母生成验证码
		String base = "1234567890QWERTYUIOPALKJHGFDSZXCVBNM";
		StringBuffer sb = new StringBuffer();
		int x = 5;
		// 控制字数
		for (int i = 0; i < 4; i++) {

			int degree = new Random().nextInt() % 30; // 生成-30-30范围的随机数

			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			sb.append(ch);
			// 写入字之前，设置好旋转
			g.rotate(degree * Math.PI / 180, x, 20); // 设置字体旋转角度
			g.drawString(ch, x, 20);
			// 这次旋转不能影响下一次的旋转，所以要将上一次的旋转清掉，转回去
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		return sb.toString();
	}

	/*
	 * 设置图片的背景色
	 */
	private void setBackGround(Graphics g) {
		// 设置颜色
		g.setColor(Color.WHITE);
		// 填充区域
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	/*
	 * 设置图片的边框
	 */
	private void setBorder(Graphics g) {
		// 设置边框颜色
		g.setColor(Color.BLUE);
		// 边框区域
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	/*
	 * 在图片上画干扰线
	 */
	private void drawRandomLine(Graphics g) {
		// 设置颜色
		g.setColor(Color.GREEN);
		// 设置线条个数并画线
		for (int i = 0; i < 5; i++) {
			// 生成干扰线随机的起始坐标
			int x1 = new Random().nextInt(WIDTH); // 生成0~WIDTH(不包括WIDTH)的随机数
			int y1 = new Random().nextInt(HEIGHT);

			// 生成干扰线随机的结束坐标
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
