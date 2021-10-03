package com.librarySystem.Demo.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {

    private static final int MAX_QRCODE_SIXE = 1500;

    private static String url = null;

    public QRCode() {
    }

    public static void CreateQRCode(String content) {
        int width = 400;
        int height = 400;
        String format = "png";// 生成的二维码图片为png类型

        HashMap hints = new HashMap();// 定义二维码内容参数
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置字符集编码格式,消除乱码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 设置容错等级，使用M级别
        hints.put(EncodeHintType.MARGIN, 2);// 设置二维码空白区域大小
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);// 优化扫描精度，增加解析成功率

        String name = content;
        String filepath = "./src/main/resources/static/res/qrcode";
        File file = new File(filepath);
        System.out.println("图片存储路径为:"+file.getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        content = "生成时间：\n"+date+"\n用户:"+name;
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path path = FileSystems.getDefault().getPath(filepath, name + ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 将时间戳转换为simpledateformat类
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    public static String readQRCode(String filePath) {
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
        try {
            File file = new File(filePath);
            BufferedImage sourceImage;

            sourceImage = ImageIO.read(file);// 读取二维码图片文件

            BufferedImage image = toGrayImage(sourceImage);// 将图片转为灰阶
            if (sourceImage.getWidth() > MAX_QRCODE_SIXE && sourceImage.getHeight() > MAX_QRCODE_SIXE) {// 如果二维码图片的宽和高大于最大宽和高
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

    /**
     * 将图片转成灰阶。
     *
     * @param image
     * @return
     */
    private static BufferedImage toGrayImage(BufferedImage image) {
        BufferedImage result = image;
        if (BufferedImage.TYPE_BYTE_GRAY != image.getType()) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY);
            newImage.getGraphics().drawImage(image, 0, 0, null);
            result = newImage;
        }
        /*
         * 黑白处理 Raster raster = result.getRaster(); DataBufferByte buffer =
         * (DataBufferByte) raster.getDataBuffer(); byte[] data = buffer.getData(); for
         * (int i = 0; i < data.length; i++) { byte value = 0; if (data[i] < 32) { value
         * = -1; } buffer.setElem(i, value); }
         */
        return result;
    }

    /**
     * 图片若过大，则缩放图片。
     *
     * @param image
     * @return
     */
    private static BufferedImage resizeToMaxSize(BufferedImage image) {
        int height = MAX_QRCODE_SIXE;
        int width = MAX_QRCODE_SIXE;
        if (image.getWidth() > image.getHeight()) {
            width = (int) (height * (((double) image.getWidth()) / image.getHeight()));
        } else {
            height = (int) (width * (((double) image.getHeight()) / image.getWidth()));
        }
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        newImage.getGraphics().drawImage(image, 0, 0, newImage.getWidth() + 1, newImage.getHeight() + 1, 0, 0,
                image.getWidth(), image.getHeight(), null);
        return newImage;
    }

    // 二维码解码
    private static String readDirectly(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);// 将在不同平台的图片对象转换成LuminanceSource对象，交给Zxing进行解析
        Binarizer binarizer = new HybridBinarizer(source);// 将图像进行二值化处理，定义出黑白的界限即一个灰度值，大于这个值就为白（0），低于这个值就为黑（1）
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);// BinaryBitmap类用来表示1位数据的核心位图类，接受二进制位图并对其进行解码
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");// 设定字符集编码，消除乱码
        try {
            return new MultiFormatReader().decode(binaryBitmap, hints).getText();// 获取解码之后的字符串
        } catch (NotFoundException e) {
            return "";
        }
    }


}
