package com.example.appstreaming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Camera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);



            private SurfaceView surfaceView;
            private Button startButton;
            private Camera camera;
            private MediaRecorder mediaRecorder;
            private boolean isRecording = false;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                surfaceView = findViewById(R.id.surfaceView);
                startButton = findViewById(R.id.startButton);

                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isRecording) {
                            stopRecording();
                        } else {
                            startRecording();
                        }
                    }
                });
            }

            private void startRecording() {
                camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();
                camera.setParameters(parameters);
                camera.setDisplayOrientation(90);

                mediaRecorder = new MediaRecorder();
                camera.unlock();
                mediaRecorder.setCamera(camera);

                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

                String outputFilePath = getExternalFilesDir(null).getAbsolutePath() + "/output.mp4";
                mediaRecorder.setOutputFile(outputFilePath);

                mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());

                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    startButton.setText("Stop");
                    isRecording = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void stopRecording() {
                mediaRecorder.stop();
                mediaRecorder.release();
                camera.lock();
                camera.release();
                startButton.setText("Start");
                isRecording = false;
            }






    }


}