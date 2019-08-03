package duke.logic;

import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

import static org.bytedeco.opencv.global.opencv_core.cvGet2D;
import static org.bytedeco.opencv.global.opencv_imgproc.cvResize;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;

/**
 * Image Processor for Model https://storage.googleapis.com/download.tensorflow.org/models/inception5h.zip
 */
public class ImageProcessor {
    /* Constants specific to trained model
     * All RGB values are converted to normalized float values
     * using this formula (value - mean) / scale
     * */
    private final static int height = 224;
    private final static int width = 224;
    private final static float mean = 117f;
    private final static float scale = 1f;

    public static float[][][][] loadAndNormalizeImages(String... path) {
        //First dimension of the result is a number of images, because we may accept multiple paths
        float[][][][] result = new float[path.length][height][width][3];
        for (int i = 0; i < path.length; i++) {
            IplImage origImg = cvLoadImage(path[i]);
            //Creating image placeholder to put resized image data
            IplImage resizedImg = IplImage.create(width, height, origImg.depth(), origImg.nChannels());
            cvResize(origImg, resizedImg);
            result[i] = getRGBArray(resizedImg);
        }
        return result;
    }

    private static float[][][] getRGBArray(IplImage image) {
        float[][][] result = new float[image.height()][image.width()][3];
        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                CvScalar pixel = cvGet2D(image, i, j);
                result[i][j][0] = (float)(pixel.val(2) - mean) / scale; //R
                result[i][j][1] = (float)(pixel.val(1) - mean) / scale; //G
                result[i][j][2] = (float)(pixel.val(0) - mean) / scale; //B
            }
        }
        return result;
    }
}

