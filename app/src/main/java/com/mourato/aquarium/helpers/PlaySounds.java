
package com.mourato.aquarium.helpers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.mourato.aquarium.R;

public class PlaySounds {

    private Context context;

    private AudioManager audioManager;

    private MediaPlayer mediaPlayer;

    private MediaPlayer bubble;

    private MediaPlayer aquariumTheme;

    public PlaySounds(Context context) {
        this.context = context;
        this.audioManager = (AudioManager) this.context.getSystemService(Context.AUDIO_SERVICE);

    }

    public void play(String fileName) {
        try {

            float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            streamVolume /= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            AssetFileDescriptor afd = this.context.getAssets().openFd(fileName);
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setVolume(streamVolume, streamVolume);
            this.mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
                    afd.getLength());
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
            this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        aquariumTheme = MediaPlayer.create(context, R.raw.carefree);
        aquariumTheme.setVolume(1.0f, 1.0f);
        aquariumTheme.setLooping(true);
        aquariumTheme.start();
    }

    public void bubbles() {
        bubble = MediaPlayer.create(context, R.raw.bubbles);
        bubble.setVolume(0.1f, 0.1f);
        bubble.setLooping(true);
        bubble.start();

    }

    public void stop() {
        if (aquariumTheme != null) {
            try {
                aquariumTheme.stop();
                aquariumTheme.release();
            } catch (IllegalStateException e) {
            }
        }
        if (bubble != null) {
            try {
                bubble.stop();
                bubble.release();
                } catch (IllegalStateException e) {
            }
        }
    }

}
