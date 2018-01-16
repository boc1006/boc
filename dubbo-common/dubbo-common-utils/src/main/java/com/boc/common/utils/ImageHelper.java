package com.boc.common.utils;

import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * <p>@Title ImageHelper</p>
 * <p>@Description com.boc.common.utils</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author <a href="mailto:wangshilong@dgg.net">王仕龙</a></p>
 * <p>@date 2017-01-09 15:11</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class ImageHelper {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ImageHelper.class);

    /**
     * 压缩图片尺寸
     *
     * @param inputStream 图片输入流
     * @param width       压缩后的宽度
     * @param height      压缩后的高度
     * @return 返回图片输出流
     */
    public static  ByteArrayOutputStream resizeImage(InputStream inputStream, int width, int height) {
        Assert.notNull(inputStream);
        try {
            BufferedImage outputImage = scaleImageWidthHeight(ImageIO.read(inputStream), width, height, false);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(outputImage, null, outputStream);

            return outputStream;
        } catch (IOException e) {
            throw new RuntimeException("图片裁剪异常", e);
        }
    }

    /**
     * 压缩图片尺寸
     *
     * @param inputImage 图片对象
     * @param width       压缩后的宽度
     * @param height      压缩后的高度
     * @return 返回图片对象
     */
    public static  BufferedImage resizeImage(BufferedImage inputImage, int width, int height) {
        Assert.notNull(inputImage);
        return scaleImageWidthHeight(inputImage, width, height, false);
    }

    /**
     * <b>function:</b> 通过指定的比例和图片对象，返回一个放大或缩小的宽度、高度
     * @param scale 缩放比例
     * @param image 图片对象
     * @return 返回宽度、高度
     */
    public static int[] getSize(float scale, Image image) {
        int targetWidth = image.getWidth(null);
        int targetHeight = image.getHeight(null);
        long standardWidth = Math.round(targetWidth * scale);
        long standardHeight = Math.round(targetHeight * scale);
        return new int[] { Integer.parseInt(Long.toString(standardWidth)), Integer.parseInt(String.valueOf(standardHeight)) };
    }

    public static int[] getSize(int width, Image image) {
        int targetWidth = image.getWidth(null);
        int targetHeight = image.getHeight(null);
        if (targetWidth > width) {
            long height = Math.round((targetHeight * width) / (targetWidth * 1.00f));
            return new int[]{width, Integer.parseInt(String.valueOf(height))};
        } else {
            return null;
        }
    }

    /***
     * 按指定的比例缩放图片
     *
     * @param sourceImagePath
     *      源地址
     * @param destinationPath
     *      改变大小后图片的地址
     * @param scale
     *      缩放比例，如1.2
     * @param format
     *      图片输出格式
     */
    public static void scaleImage(String sourceImagePath, String destinationPath, double scale, String format) {

        File file = new File(sourceImagePath);
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            BufferedImage outputImage = scaleImage(bufferedImage, scale);
            ImageIO.write(outputImage, format, new File(destinationPath));
        } catch (IOException e) {
            logger.error("scaleImage方法压缩图片时出错了", e);
        }

    }

    /***
     * 按指定的比例缩放图片
     * <p>@author <a href="mailto:wangshilong@dgg.net">王仕龙</a></p>
     * <p>@date 2017-01-09 17:05</p>
     *
     * @param inputImage
     *      图片对象
     * @param scale
     *      缩放比例，如1.2
     * @return 返回图片对象
     */
    public static BufferedImage scaleImage(BufferedImage inputImage, double scale) {
        Assert.notNull(inputImage);

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        width = parseDoubleToInt(width * scale);
        height = parseDoubleToInt(height * scale);

        return scaleImageWidthHeight(inputImage, width, height);
    }

    /***
     * 将图片缩放到指定的高度或者宽度
     * @param sourceImagePath 图片源地址
     * @param destinationPath 压缩完图片的地址
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @param auto 是否自动保持图片的原高宽比例
     * @param format 输出图片格式 例如 jpg
     */
    public static void scaleImageWidthHeight(String sourceImagePath, String destinationPath, int width, int height, boolean auto, String format) {

        try {
            File file = new File(sourceImagePath);
            BufferedImage bufferedImage = null, outputImage = null;
            bufferedImage = ImageIO.read(file);
            outputImage = scaleImageWidthHeight(bufferedImage, width, height, auto);
            ImageIO.write(outputImage, format, new File(destinationPath));
        } catch (Exception e) {
            logger.error("scaleImageWithParams方法压缩图片时出错了", e);
        }
    }

    /**
     * 将图片转变为指定的尺寸，宽高比自适应
     * <p>@author <a href="mailto:wangshilong@dgg.net">王仕龙</a></p>
     * <p>@date 2017-01-09 17:05</p>
     *
     * @param inputImage 输入图片对象
     * @param width      宽度
     * @param height     高度
     * @param auto       自动调整宽高比
     * @return 输出图片
     */
    public static BufferedImage scaleImageWidthHeight(BufferedImage inputImage, int width, int height, boolean auto) {
        Assert.notNull(inputImage);

        if (auto) {
            Integer[] autoArray = getAutoWidthAndHeight(inputImage, width, height);
            width = autoArray[0];
            height = autoArray[1];
            if (logger.isDebugEnabled()) {
                logger.debug("自动调整比例，width=" + width + " height=" + height);
            }
        }

        return scaleImageWidthHeight(inputImage, width, height);
    }

    /**
     * 将图片转变为指定的尺寸
     * <p>@author <a href="mailto:wangshilong@dgg.net">王仕龙</a></p>
     * <p>@date 2017-01-09 17:05</p>
     *
     * @param inputImage 输入图片对象
     * @param width      宽度
     * @param height     高度
     * @return 输出图片
     */
    private static BufferedImage scaleImageWidthHeight(BufferedImage inputImage, int width, int height) {
        Assert.notNull(inputImage);

        Image image = inputImage.getScaledInstance(width, height, Image.SCALE_DEFAULT); // Image.SCALE_SMOOTH
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = outputImage.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();

        return outputImage;
    }

    /**
     * 将double类型的数据转换为int，四舍五入原则
     *
     * @param sourceDouble
     * @return
     */
    private static int parseDoubleToInt(double sourceDouble) {
        int result = 0;
        result = (int) sourceDouble;
        return result;
    }

    /***
     *
     * @param inputImage 要缩放的图片对象
     * @param width_scale 要缩放到的宽度
     * @param height_scale 要缩放到的高度
     * @return 一个集合，第一个元素为宽度，第二个元素为高度
     */
    private static Integer[] getAutoWidthAndHeight(BufferedImage inputImage, int width_scale, int height_scale) {
        Assert.notNull(inputImage);

        Integer[] autoArray = new Integer[2];

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        double scale_w = getDot2Decimal(width_scale, width);

        // System.out.println("getAutoWidthAndHeight width=" + width + "scale_w=" + scale_w);
        double scale_h = getDot2Decimal(height_scale, height);
        if (scale_w < scale_h) {
            autoArray[0] = parseDoubleToInt(scale_w * width);
            autoArray[1] = parseDoubleToInt(scale_w * height);
        } else {
            autoArray[0] = parseDoubleToInt(scale_h * width);
            autoArray[1] = parseDoubleToInt(scale_h * height);
        }
        return autoArray;

    }


    /***
     * 返回两个数a/b的小数点后三位的表示
     * @param a
     * @param b
     * @return
     */
    public static double getDot2Decimal(int a, int b) {

        BigDecimal bigDecimal_1 = new BigDecimal(a);
        BigDecimal bigDecimal_2 = new BigDecimal(b);
        BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2, new MathContext(4));
        Double double1 = new Double(bigDecimal_result.toString());

        return double1;
    }
}
