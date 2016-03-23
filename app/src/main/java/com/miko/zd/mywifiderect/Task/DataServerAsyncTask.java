package com.miko.zd.mywifiderect.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.miko.zd.mywifiderect.Activity.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple server socket that accepts connection and writes some data on
 * the stream.
 */
public class DataServerAsyncTask extends
        AsyncTask<Void, Void, String> {

    private TextView statusText;
    private MainActivity activity;

    /**
     * @param statusText
     */
    public DataServerAsyncTask(MainActivity activity, View statusText) {
        this.statusText = (TextView) statusText;
        this.activity=activity;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            Log.i("xyz", "data doinback");
            ServerSocket serverSocket = new ServerSocket(8888);

            Log.i("xyz","串口创建完成");
            Socket client = serverSocket.accept();
            Log.i("xyz","阻塞已取消");
            InputStream inputstream = client.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while ((i = inputstream.read()) != -1) {
                baos.write(i);
            }

            String str = baos.toString();
            serverSocket.close();
            return str;

        } catch (IOException e) {
            Log.e("xyz", e.toString());
            return null;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(String result) {

        Log.i("xyz", "data onpost");

        Toast.makeText(activity, "result"+result, Toast.LENGTH_SHORT).show();

        if (result != null) {
            statusText.setText("Data-String is " + result);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {

    }

}