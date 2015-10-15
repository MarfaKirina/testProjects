package ua.dotsenko.testogles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends Activity {

    private GLSurfaceView glView;

    /**
     * Create for adding renderer
     */
    class MySurfaceView extends GLSurfaceView {
        private final TryRenderer renderer;
        public MySurfaceView(Context context){
            super(context);
            //2 is meant ogles 2.0
            setEGLContextClientVersion(2);
            renderer = new TryRenderer();
            //prevents error of not chosen config
            super.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
            setRenderer(renderer);
            //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glView = new MySurfaceView(this);
        setContentView(glView);
    }

}
