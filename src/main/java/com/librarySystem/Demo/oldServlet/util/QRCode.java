package com.librarySystem.Demo.oldServlet.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 二维码工具类
 * 实现输入字符串(文本，网址)生成对应的二维码二维码实质是01代码，具有4个等级的容错能力
 * QRCodeUtils
 */
public class QRCode {

	final String imgPath = "D:\\";
	public class EncodeMode{
		/** 
		 * N代表的是数字
		 */
		public final static char N = 'N';
		/** 
		 * A代表的是a-z
		 */
		public final static char A= 'A';
		/** 
		 * B代表的是其他字符
		 */
		public final static char B = 'B';
	}
	
	/**
	 * 二维码的容错能力等级
	 * 二维码容错率用字母表示，容错能力等级分为：L、M、Q、H四级
	 * 二维码具有容错功能，当二维码图片被遮挡一部分后，仍可以扫描出来。
	 * 容错的原理是二维码在编码过程中进行了冗余，就像是123被编码成123123，这样只要扫描到一部分二维码图片，
	 * 二维码内容还是可以被全部读到。
	 * 二维码容错率即是指二维码图标被遮挡多少后，仍可以被扫描出来的能力。容错率越高，则二维码图片能被遮挡的部分越多。
	 * ErrorCorrect
	 */
	public class ErrorCorrect{
		/**
		 * 低,最大 7% 的错误能够被纠正
		 */
		public final static char L = 'L';
		/**
		 * 中，最大 15% 的错误能够被纠正
		 */
		public final static char M = 'M';
		/**
		 * 中上，最大 25% 的错误能够被纠正
		 */
		public final static char Q = 'Q';
		/**
		 * 高，最大 30% 的错误能够被纠正
		 */
		public final static char H = 'H';
	}
	
	/**
	 * 基于 QRCode 创建二维码
	 * @param content 要写入二维码的内容
	 * @param savePath 完整的保存路径
	 * @param version 版本
	 * @param logoPath 完整的logo路径，可以为：null
	 */
	public static boolean CreateQRCode(String content, String savePath, int version, String logoPath){
		// 创建生成二维码的对象
        Qrcode qrcode = new Qrcode();
        // 设置二维码的容错能力等级
        qrcode.setQrcodeErrorCorrect(ErrorCorrect.M);
        // N代表的是数字，A代表的是a-z,B代表的是其他字符
        qrcode.setQrcodeEncodeMode(EncodeMode.B);
        // 版本
        qrcode.setQrcodeVersion(version);
        
        // 设置验证码的大小
        int width = 200;//67 + 12 * (version - 1);
        int height = 200;//67 + 12 * (version - 1);
        // 定义缓冲区图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 设置画图工具
        Graphics2D gs = bufferedImage.createGraphics();
        // 设置二维码背景颜色
        gs.setBackground(Color.white);//lightGray
        // 设置颜色
        gs.setColor(Color.black);//cyan,green,red,black,pink
        // 清除画板内容
        gs.clearRect(0, 0, width, height);
        // 定义偏移量
        int pixoff = 2;

        // 填充的内容转化为字节数
        byte[] ctt;
		try {
			ctt = content.getBytes("utf-8");
			// 设置编码方式
	        if (ctt.length > 0 && ctt.length < 120) {
	            boolean[][] s = qrcode.calQrcode(ctt);
	            for (int i = 0; i < s.length; i++) {
	                for (int j = 0; j < s.length; j++) {
	                    if (s[j][i]) {
	                        // 验证码图片填充内容
	                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
	                    }
	                }
	            }
	        }
	        
	        /* 判断是否需要添加logo图片 */
            if(logoPath == null || logoPath.equals("")){
            	System.out.println("Warning: Logo图片不存在！");
                }else{
                	File icon = new File(logoPath);
                    if(icon.exists()){
                        int width_4 = width / 4;
                        int width_8 = width_4 / 2;
                        int height_4 = height / 4;
                        int height_8 = height_4 / 2;
                        Image img = ImageIO.read(icon);
                        gs.drawImage(img, width_4 + width_8, height_4 + height_8,width_4,height_4, null);   
                }
 
            }

	        // 结束写入
	        gs.dispose();
	        // 结束内存图片
	        bufferedImage.flush();
	        // 保存生成的二维码图片
	        ImageIO.write(bufferedImage, "png", new File(savePath));
	        
	        return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void getCode(String content,String savePath,String logoPatn){

		int version= 9;
		boolean result = CreateQRCode(content, "D:\\test.png", version,logoPatn);
		if (result){
			System.out.println("\n二维码图片生成成功！");
		}else{
			System.out.println("\n二维码图片生成失败！");
		}
	}



	public static void toGrayImage(BufferedImage img){
		int h = img.getHeight();
		int w = img.getWidth();

		int[] date= img.getRGB(0, 0, w, h, null, 0, w);
		for (int y = 0; y < h; y++) {
			   for (int x = 0; x < w; x++) {
				   int c = date[x + y * w];
				   int R = (c >> 16) & 0xFF;
				   int G = (c >> 8) & 0xFF;
				   int B = (c >> 0) & 0xFF;
				   date[x + y * w] = (int)(0.3f*R + 0.59f*G + 0.11f*B); //to gray
			}
		}

	}

	/*
	public static String readQRCode(String filePath) {
	 */
		/*
		 * try { File file = new File(filePath); BufferedImage bufferedImage =
		 * ImageIO.read(file); BinaryBitmap bitmap = new BinaryBitmap(new
		 * HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
		 *
		 * HashMap hints = new HashMap<>(); hints.put(EncodeHintType.CHARACTER_SET,
		 * "utf-8"); hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
		 *
		 *
		 * Result result = new MultiFormatReader().decode(bitmap, hints); return
		 * result.getText(); } catch (NotFoundException e) { e.printStackTrace(); return
		 * null; } catch (IOException e) { e.printStackTrace(); return null; }
		 */
	/*
		try {
			int MAX_QRCODE_SIZE = 1500;
			File file = new File(filePath);
			BufferedImage sourceImage;

			sourceImage = ImageIO.read(file);// 读取二维码图片文件

			BufferedImage image = toGrayImage(sourceImage);// 将图片转为灰阶


			if (sourceImage.getWidth() > MAX_QRCODE_SIZE && sourceImage.getHeight() > MAX_QRCODE_SIZE) {// 如果二维码图片的宽和高大于最大宽和高
				image = resizeToMaxSize(sourceImage);// 图片若过大，则缩放图片
			}
			String result = readDirectly(image);// 获取二维码图片解码之后得到的字符串
			if (StringUtils.isNotBlank(result)) {// 判断字符串是否不为空且长度不为0且不由空白符构成
				return result;
			}
			int minSize = 170;
			int imgSize = Math.min(image.getWidth(), image.getHeight());// 获取图片宽、高中较小的一个
			int level = 1;
			while (imgSize > minSize) {// 如果图片尺寸大于最小尺寸，就重新生成一个尺寸为原来尺寸0.9倍的图片，并重新获得字符串，重复操作直到小于最小尺寸即minSize
				BufferedImage newImage = new BufferedImage((int) (image.getWidth() * Math.pow(0.9, level)),
						(int) (image.getHeight() * Math.pow(0.9, level)), image.getType());
				newImage.getGraphics().drawImage(image, 0, 0, newImage.getWidth(), newImage.getHeight(), 0, 0,
						image.getWidth(), image.getHeight(), null);
				result = readDirectly(newImage);
				if (StringUtils.isNotBlank(result)) {
					return result;
				}
				imgSize = Math.min(newImage.getWidth(), newImage.getHeight());
				level++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "no";
	}
	*/

    /*public static void main(String[] args) {
        String content = "http://42.192.164.221:2048/";
        String savePath = "D:\\xys2048.png";
        int version = 9;
//        String logoPath ="E:\\代码\\Web\\个人主页\\Moli.png";
        String logoPath = "";
    	boolean result = CreateQRCode(content, savePath, version, logoPath);
    	if (result){
    		System.out.println("\n二维码图片生成成功！");
    	}else{
    		System.out.println("\n二维码图片生成失败！");
    	}
    }*/
}