package com.hackathonproject.Nuance;


import android.net.Uri;

/**
 * Created by james on 15-11-07.
 */
public class Configuration {

    /**
     * All Nuance Developers configuration parameters can be set here.
     *
     * Copyright (c) 2015 Nuance Communications. All rights reserved.
     */

        //All fields are required.
        //Your credentials can be found in your Nuance Developers portal, under "Manage My Apps".
        public static byte[] APP_KEY = {(byte)0x60, (byte)0x9b, (byte)0xc5, (byte)0xc3, (byte)0x65, (byte)0xdc, (byte)0xb9, (byte)0xeb, (byte)0x9c, (byte)0x20, (byte)0x36, (byte)0x8a, (byte)0x4b, (byte)0x91, (byte)0xc0, (byte)0x13, (byte)0x26, (byte)0xd8, (byte)0xb5, (byte)0x34, (byte)0x28, (byte)0x2c, (byte)0xee, (byte)0x4e, (byte)0x52, (byte)0xcc, (byte)0x36, (byte)0x6e, (byte)0xd3, (byte)0x57, (byte)0x65, (byte)0x33, (byte)0xf2, (byte)0x2b, (byte)0xf6, (byte)0x58, (byte)0x07, (byte)0xaf, (byte)0x43, (byte)0xa3, (byte)0xcf, (byte)0x79, (byte)0x2d, (byte)0x1a, (byte)0x0b, (byte)0xed, (byte)0x2e, (byte)0x31, (byte)0xc6, (byte)0xe3, (byte)0x66, (byte)0xee, (byte)0x0f, (byte)0x54, (byte)0x3c, (byte)0x61, (byte)0x20, (byte)0x72, (byte)0xcf, (byte)0xf3, (byte)0xc9, (byte)0x35, (byte)0x26, (byte)0xbc};
        public static final String APP_ID = "NMDPTRIAL_rkim43_uic_edu20151107153603";
        public static final String SERVER_HOST = "nmsp.dev.nuance.com";
        public static final String SERVER_PORT = "443";

        public static final Uri SERVER_URI = Uri.parse("nmsp://" + APP_ID + "@" + SERVER_HOST + ":" + SERVER_PORT);

        //Only needed if using NLU
        public static final String CONTEXT_TAG = "Project886_App437";


}
