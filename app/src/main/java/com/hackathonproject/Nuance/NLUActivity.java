//package com.hackathonproject.Nuance;
//
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import com.hackathonproject.R;
//import com.nuance.nmdp.speechkit.Audio;
//import com.nuance.nmdp.speechkit.AudioPlayer;
//import com.nuance.nmdp.speechkit.DetectionType;
//import com.nuance.nmdp.speechkit.Interpretation;
//import com.nuance.nmdp.speechkit.Language;
//import com.nuance.nmdp.speechkit.Recognition;
//import com.nuance.nmdp.speechkit.Session;
//import com.nuance.nmdp.speechkit.Transaction;
//import com.nuance.nmdp.speechkit.TransactionException;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class NLUActivity extends DetailActivity implements View.OnClickListener, AudioPlayer.Listener {
//
//    private Audio startEarcon;
//    private Audio stopEarcon;
//    private Audio errorEarcon;
//
//    private RadioGroup detectionType;
//    private EditText nluContextTag;
//    private EditText language;
//
//    private TextView logs;
//    private Button clearLogs;
//
//    private Button toggleReco;
//
//    private ProgressBar volumeBar;
//
//    private Session speechSession;
//    private Transaction recoTransaction;
//    private State state = State.IDLE;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nlu);
//
//        detectionType = (RadioGroup)findViewById(R.id.detection_type_picker );
//        nluContextTag = (EditText)findViewById(R.id.nlu_context_tag);
//        nluContextTag.setText(Configuration.CONTEXT_TAG);
//        language = (EditText)findViewById(R.id.language);
//
//        logs = (TextView)findViewById(R.id.logs);
//        clearLogs = (Button)findViewById(R.id.clear_logs);
//        clearLogs.setOnClickListener(this);
//
//        toggleReco = (Button)findViewById(R.id.toggle_reco);
//        toggleReco.setOnClickListener(this);
//
//        volumeBar = (ProgressBar)findViewById(R.id.volume_bar);
//
//        //Create a session
//        speechSession = Session.Factory.session(this, Configuration.SERVER_URI, Configuration.APP_KEY);
//
//        speechSession.getAudioPlayer().setListener(this);
//        //Tell the AudioPlayer to start playing as soon as we enqueue an Audio .
//        speechSession.getAudioPlayer().play();
//        loadEarcons();
//
//        setState(State.IDLE);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if(v == clearLogs) {
//            logs.setText("");
//        } else if(v == toggleReco) {
//            toggleReco();
//        }
//    }
//
//    /* Reco transactions */
//
//    private void toggleReco() {
//        switch (state) {
//            case IDLE:
//                speechSession.getAudioPlayer().enqueue(startEarcon);
//                break;
//            case LISTENING:
//                stopRecording();
//                break;
//            case PROCESSING:
//                cancel();
//                break;
//        }
//    }
//
//    @Override
//    public void onBeginPlaying(AudioPlayer audioPlayer, Audio audio) {
//        //No need to do anything here, but feel free to get creative and leverage this handler.
//    }
//
//    @Override
//    public void onFinishedPlaying(AudioPlayer audioPlayer, Audio audio) {
//        //We should make sure to let the start earcon finish playing before we start to listen.
//        //This will ensure that there are no earcon artifacts in the recording.
//        if(audio == startEarcon) {
//            recognize();
//        }
//    }
//
//    /**
//     * Start listening to the user and streaming their voice to the server.
//     */
//    private void recognize() {
//        //Setup our Reco transaction options.
//        Transaction.Options options = new Transaction.Options();
//        options.setDetection(resourceIDToDetectionType(detectionType.getCheckedRadioButtonId()));
//        options.setLanguage(new Language(language.getText().toString()));
//
//        //Add properties to appServerData for use with custom service. Leave empty for use with NLU.
//        JSONObject appServerData = new JSONObject();
//        //Start listening
//        recoTransaction = speechSession.recognizeWithService(nluContextTag.getText().toString(), appServerData, options, recoListener);
//    }
//
//    private Transaction.Listener recoListener = new Transaction.Listener() {
//        @Override
//        public void onStartedRecording(Transaction transaction) {
//            logs.append("\nonStartedRecording");
//
//            //We have started recording the users voice.
//            //We should update our state and start polling their volume.
//            setState(State.LISTENING);
//            startAudioLevelPoll();
//        }
//
//        @Override
//        public void onFinishedRecording(Transaction transaction) {
//            logs.append("\nonFinishedRecording");
//
//            //We have finished recording the users voice.
//            //We should update our state and stop polling their volume.
//            setState(State.PROCESSING);
//            stopAudioLevelPoll();
//
//            //We should also play an earcon reassure the user that we have stopped listening.
//            speechSession.getAudioPlayer().enqueue(stopEarcon);
//        }
//
//        @Override
//        public void onServiceResponse(Transaction transaction, org.json.JSONObject response) {
//            try {
//                // 2 spaces for tabulations.
//                logs.append("\nonServiceResponse: " + response.toString(2));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            // We have received a service response. In this case it is our NLU result.
//            // Note: this will only happen if you are doing NLU (or using a service)
//            setState(State.IDLE);
//        }
//
//        @Override
//        public void onRecognition(Transaction transaction, Recognition recognition) {
//            logs.append("\nonRecognition: " + recognition.getText());
//
//            //We have received a transcription of the users voice from the server.
//            setState(State.IDLE);
//        }
//
//        @Override
//        public void onInterpretation(Transaction transaction, Interpretation interpretation) {
//            try {
//                logs.append("\nonInterpretation: " + interpretation.getResult().toString(2));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            // We have received a service response. In this case it is our NLU result.
//            // Note: this will only happen if you are doing NLU (or using a service)
//            setState(State.IDLE);
//        }
//
//        @Override
//        public void onSuccess(Transaction transaction, String s) {
//            logs.append("\nonSuccess");
//
//            //Notification of a successful transaction. Nothing to do here.
//        }
//
//        @Override
//        public void onError(Transaction transaction, String s, TransactionException e) {
//            logs.append("\nonError: " + e.getMessage() + ". " + s);
//
//            //Something went wrong. Check Configuration.java to ensure that your settings are correct.
//            //The user could also be offline, so be sure to handle this case appropriately.
//            //We will simply reset to the idle state and play a sound to indicate a failure.
//            setState(State.IDLE);
//            speechSession.getAudioPlayer().enqueue(errorEarcon);
//        }
//    };
//
//    /**
//     * Stop recording the user
//     */
//    private void stopRecording() {
//        recoTransaction.stopRecording();
//    }
//
//    /**
//     * Cancel the Reco transaction.
//     * This will only cancel if we have not received a response from the server yet.
//     */
//    private void cancel() {
//        recoTransaction.cancel();
//    }
//
//    /* Audio Level Polling */
//
//    private Handler handler = new Handler();
//
//    /**
//     * Every 50 milliseconds we should update the volume meter in our UI.
//     */
//    private Runnable audioPoller = new Runnable() {
//        @Override
//        public void run() {
//            float level = recoTransaction.getAudioLevel();
//            volumeBar.setProgress((int)level);
//            handler.postDelayed(audioPoller, 50);
//        }
//    };
//
//    /**
//     * Start polling the users audio level.
//     */
//    private void startAudioLevelPoll() {
//        audioPoller.run();
//    }
//
//    /**
//     * Stop polling the users audio level.
//     */
//    private void stopAudioLevelPoll() {
//        handler.removeCallbacks(audioPoller);
//        volumeBar.setProgress(0);
//    }
//
//
//    /* State Logic: IDLE -> LISTENING -> PROCESSING -> repeat */
//
//    private enum State {
//        IDLE,
//        LISTENING,
//        PROCESSING
//    }
//
//    /**
//     * Set the state and update the button text.
//     */
//    private void setState(State newState) {
//        state = newState;
//        switch (newState) {
//            case IDLE:
//                toggleReco.setText(getResources().getString(R.string.recognize_with_service));
//                break;
//            case LISTENING:
//                toggleReco.setText(getResources().getString(R.string.listening));
//                break;
//            case PROCESSING:
//                toggleReco.setText(getResources().getString(R.string.processing));
//                break;
//        }
//    }
//
//    /* Earcons */
//
//    private void loadEarcons() {
//        //Load all of the earcons from disk
//        startEarcon = new Audio(this, R.raw.sk_start, null);
//        startEarcon.load();
//
//        stopEarcon = new Audio(this, R.raw.sk_stop, null);
//        stopEarcon.load();
//
//        errorEarcon = new Audio(this, R.raw.sk_error, null);
//        errorEarcon.load();
//    }
//
//    /* Helpers */
//
//    private DetectionType resourceIDToDetectionType(int id) {
//        if(id == R.id.long_endpoint) {
//            return DetectionType.Long;
//        }
//        if(id == R.id.short_endpoint) {
//            return DetectionType.Short;
//        }
//        if(id == R.id.none) {
//            return DetectionType.None;
//        }
//        return null;
//    }
//}
