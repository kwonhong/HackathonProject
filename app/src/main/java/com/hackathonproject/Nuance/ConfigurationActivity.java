package com.hackathonproject.Nuance;

import android.os.Bundle;
import android.widget.TextView;

import com.hackathonproject.R;

import java.util.Arrays;

/**
 * Created by james on 15-11-07.
 */
public class ConfigurationActivity extends DetailActivity {

    private TextView contextTag;
    private TextView serverHost;
    private TextView serverPort;
    private TextView appKey;
    private TextView appId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        contextTag = (TextView) findViewById(R.id.context_tag);
        serverHost = (TextView) findViewById(R.id.server_host);
        serverPort = (TextView) findViewById(R.id.server_port);
        appKey = (TextView) findViewById(R.id.app_key);
        appId = (TextView) findViewById(R.id.app_id);

        contextTag.setText(Configuration.CONTEXT_TAG);
        serverHost.setText(Configuration.SERVER_HOST);
        serverPort.setText(Configuration.SERVER_PORT);
        appId.setText(Configuration.APP_ID);

        appKey.setText(bytesToHex(Configuration.APP_KEY));
    }

    final private static char[] hexArray = "0123456789abcdef".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        String[] hexChars = new String[bytes.length];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j] = "0x" + hexArray[v >>> 4] + hexArray[v & 0x0F];
        }
        return Arrays.toString(hexChars);
    }
}


