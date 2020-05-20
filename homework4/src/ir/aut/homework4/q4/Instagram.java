package ir.aut.homework4.q4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Instagram {
    private String imageName;
    private String convertedImageName;
    private BufferedImage inputFile = null;
    int pixel;

    public Instagram(String imageName, String convertedImageName) {
        this.imageName = imageName;
        this.convertedImageName = convertedImageName;
    }

    public void openImage() {
        try {
            inputFile = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void blackWhite() {
        for (int i = 0; i < inputFile.getWidth(); i++) {
            for (int j = 0; j < inputFile.getHeight(); j++) {
                pixel = inputFile.getRGB(i, j);
                int r = (pixel) & 0xff;
                int g = (pixel) & 0xff;
                int b = (pixel) & 0xff;
                Color color = new Color((r + g + b) / 3, (r + g + b) / 3, (r + g + b) / 3);
                inputFile.setRGB(i, j, color.getRGB());
            }
        }
    }

    public void filterRedColor() {
        for (int i = 0; i < inputFile.getWidth(); i++) {
            for (int j = 0; j < inputFile.getHeight(); j++) {
                pixel = inputFile.getRGB(i, j);
                int g = (pixel) & 0xff;
                int b = (pixel) & 0xff;
                Color color = new Color(0, g, b);
                inputFile.setRGB(i, j, color.getRGB());
            }
        }
    }

    public void filterBlueColor() {
        for (int i = 0; i < inputFile.getWidth(); i++) {
            for (int j = 0; j < inputFile.getHeight(); j++) {
                pixel = inputFile.getRGB(i, j);
                int g = pixel & 0xff;
                int r = pixel & 0xff;
                Color color = new Color(r, g, 0);
                inputFile.setRGB(i, j, color.getRGB());
            }
        }
    }

    public void filterGreenColor() {
        for (int i = 0; i < inputFile.getWidth(); i++) {
            for (int j = 0; j < inputFile.getHeight(); j++) {
                pixel = inputFile.getRGB(i, j);
                int r = (pixel) & 0xff;
                int b = pixel & 0xff;
                Color color = new Color(r, 0, b);
                inputFile.setRGB(i, j, color.getRGB());
            }
        }
    }

    public void randomImage() {
        int width = 200;
        int height = 200;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int a = (int) (Math.random() * 256);
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);
                int p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(i, j, p);
            }
        }
        try {
            File randomFile = new File("random.jpg");
            ImageIO.write(img, "jpg", randomFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createImage() {
        try {
            File outputFile = new File(convertedImageName);
            ImageIO.write(inputFile, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void duplicateImage() throws Exception {
        BufferedImage duplicatedImage = new BufferedImage(inputFile.getWidth(), inputFile.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
        //int width = duplicatedImage.getWidth();
        //int height = duplicatedImage.getHeight();
        for (int i = 0; i < inputFile.getWidth(); i++) {
            for (int j = 0; j < inputFile.getHeight(); j++) {
                duplicatedImage.setRGB(i, j, inputFile.getRGB(i, j));
                duplicatedImage.setRGB(i, j + inputFile.getHeight(), inputFile.getRGB(i, j));
            }
        }
        File output = new File(convertedImageName);
        ImageIO.write(duplicatedImage, "jpg", output);
    }

    public void negativeImage() throws Exception {
        BufferedImage negativeImage = new BufferedImage(inputFile.getWidth() + 2, inputFile.getHeight() + 2, BufferedImage.TYPE_INT_ARGB);
        int[][] pixels = new int[inputFile.getWidth() + 2][inputFile.getHeight() + 2];
        for (int i = 1; i < inputFile.getWidth() - 1; i++) {
            for (int j = 1; j < inputFile.getHeight() - 1; j++) {
                negativeImage.setRGB(i, 0, 0);
                negativeImage.setRGB(0, j, 0);
                negativeImage.setRGB(i, inputFile.getHeight(), 0);
                negativeImage.setRGB(inputFile.getWidth(), j, 0);
            }
        }
        for (int i = 1; i < inputFile.getWidth() + 1; i++) {
            for (int j = 1; j < inputFile.getHeight() + 1; j++) {
                pixels[i][j] = inputFile.getRGB(i - 1, j - 1);
            }
        }
        for (int k = 1; k < inputFile.getWidth() + 1; k++) {
            for (int j = 1; j < inputFile.getHeight() + 1; j++) {
                pixels[k][j] = ((pixels[k - 1][j - 1] +
                        pixels[k - 1][j] +
                        pixels[k - 1][j + 1] +
                        pixels[k][j - 1] +
                        pixels[k][j] +
                        pixels[k][j + 1] +
                        pixels[k + 1][j - 1] +
                        pixels[k + 1][j] +
                        pixels[k + 1][j + 1]) / 360);
                negativeImage.setRGB(k - 1, j - 1, pixels[k][j]);
            }
        }
        File output = new File(convertedImageName);
        ImageIO.write(negativeImage, "png", output);
    }

    public void motionBlurImage() {
        try {
            inputFile = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage motionBluredImage = new BufferedImage(inputFile.getWidth(), inputFile.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //int pixelMask[][] = new int[5][5];
        int pix;
        double rNew = 0, gNew = 0, bNew = 0, aNew = 0;
        for (int i = 2; i < inputFile.getWidth() - 3; i++) {
            for (int j = 2; j < inputFile.getHeight() - 3; j++) {
                for (int k = 0; k < 5; k++) {
                    //pix = inputFile.getRGB(i + k - 2, j + k - 2);
                    int rgb = inputFile.getRGB(i + k - 2, j + k - 2);
                    Color color = new Color(rgb);

                    int b = color.getBlue();
                    int g = color.getGreen();
                    int r = color.getRed();
                    int a = color.getAlpha();
                    rNew += (double) r / 5;
                    gNew += (double) g / 5;
                    bNew += (double) b / 5;
                    aNew += (double) a / 5;
                }
                int newPix = (((int)aNew << 24) | ((int)rNew << 16) | ((int)gNew << 8) | (int)bNew);
                //pixSum += pixelMask[k][k] / 25;
                motionBluredImage.setRGB(i, j, newPix);
            }
        }
        try{

            File output = new File(convertedImageName);
            ImageIO.write(motionBluredImage, "jpg", output);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws Exception {
        Instagram instagram = new Instagram("image.jpeg", "invertedpic");
        //instagram.openImage();
        instagram.motionBlurImage();
        //instagram.createImage();
    }
}