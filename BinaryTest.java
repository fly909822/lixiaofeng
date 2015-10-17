package com.test;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class BinaryTest {

	public static void main(String[] args) throws IOException {
		test("/Users/lixf/competImgtest/1_80.jpg","/Users/lixf/competImgtest/1_80_1.jpg");
	}
	
	public static void test(String readimg,String writeimg) throws IOException{
		File file = new File(readimg);
		BufferedImage bufferedImage = ImageIO.read(file);
		int h = bufferedImage.getHeight();
		int w = bufferedImage.getWidth();
		
		// 灰度化
	
		
		int[][] gray = new int[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				
				/**
				 * 
				 *  我们知道通过bufferedimage对象的getRGB（x，y）方法可以返回指定坐标的颜色int值 他可以通过
				 *  int R =(rgb & 0xff0000 ) >> 16 ;
				 *  int G= (rgb & 0xff00 ) >> 8 ;
				 *  int B= (rgb & 0xff );
				 *  转换成三个颜色分量
				 */
				int argb = bufferedImage.getRGB(x, y);
				int r = (argb >> 16) & 0xFF;
				int g = (argb >> 8) & 0xFF;
				int b = (argb >> 0) & 0xFF;
			
				
				int grayPixel = (r+g+b)/3;	//平均值算法
//				int grayPixel = (int) ((b * 29 + g * 150 + r * 77 + 128) >> 8);
				
				/**
				 * 像素的定义方式
				 * int 32位，每8个位标示一个数据
				 * 表示透明度 红色色阶 绿色色阶  蓝色色阶
				 * 32位颜色值一般这样分配：X8位，R8位，G8位，B8位或A8位，R8位，G8位，B8位。
				 */
				int pixel = 255 << 24 | grayPixel << 16 | grayPixel << 8 | grayPixel;
				gray[x][y] = pixel;
				bufferedImage.setRGB(x, y, pixel);
			}
		}
		
		ImageIO.write(bufferedImage, "jpg", new File( writeimg));
		
	}
	 
}