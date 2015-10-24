package ua.dotsenko.testogles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ua.dotsenko.testogles.shapes.Triangle;
import ua.dotsenko.testogles.shapes.Square;

/**
 * Created by Марфа on 11.10.15.
 */
public class TryRenderer implements GLSurfaceView.Renderer{

    private Triangle triangle;
    private Square square;
    // mMVPMatrix is an abbreviation for "Model View Projecrtion Matrix"
    private final float[] mvpMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];

    private final float[] rotationMatrix = new float[16];
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0,0,23,1);
        triangle = new Triangle();
        square = new Square();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(255,0,0,255);
        float[] scratch = new float[16];
        Matrix.setLookAtM(viewMatrix, 0, 0, -5f, -3f, 0f, 0f, 0f, 0f, 1f, 0f);
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
        long time = SystemClock.uptimeMillis() % 4000L;
        float angle = 0.090f * time;
        Matrix.setRotateM(rotationMatrix, 0, angle, 0, 0, -1f);
        Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, rotationMatrix, 0);
        square.draw(mvpMatrix);
        triangle.draw(scratch);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width/height;
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
