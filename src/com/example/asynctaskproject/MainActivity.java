package com.example.asynctaskproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView currentTxt;

	ProgressBar progressBar;

	Button buttonStartProgress;

	Button buttonCancelProgress;

	private HelloAsync helloSync = null;

	private static final String TAG = "INSLOG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// get widget

		currentTxt = (TextView) findViewById(R.id.progress_notification);

		buttonStartProgress = (Button) findViewById(R.id.startprogress);

		buttonCancelProgress = (Button) findViewById(R.id.cancelprogress);

		progressBar = (ProgressBar) findViewById(R.id.progressbar_Horizontal);

		//progressBar.setProgress(0);

		buttonStartProgress.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				buttonCancelProgress.setEnabled(true);

				// create AsyncTask

				/* HelloAsync */

				Log.d(TAG, "Start is clicked");

				helloSync = new HelloAsync();
				helloSync.execute();

			}
		});

		buttonCancelProgress.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.d(TAG, "Cancel is clicked");

				buttonStartProgress.setEnabled(true);

				buttonCancelProgress.setEnabled(false);

				currentTxt.setText("Progress is cancelled");

				helloSync.cancel(true);

			}
		});

	}
	
	class HelloAsync extends AsyncTask<Void, Integer, Void> {
		private static final String TAG = "INSLOG";
		private int myProgress;
 
		@Override
		protected Void doInBackground(Void... params) {

			// TODO Auto-generated method stub

			while (myProgress < 100) {

			boolean cancelled = isCancelled();

				if (!cancelled) {

					myProgress++;

					publishProgress(myProgress);
					Log.e(TAG, "myProgerss = " + myProgress);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					break;

				}

			}

			return null;

		}

		@Override
		protected void onCancelled() {

			// TODO Auto-generated method stub

			Log.d(TAG, "OnCancelled");
			
			//publishProgress(0);


			// super.onCancelled();

		}

		@Override
		protected void onCancelled(Void result) {

			// TODO Auto-generated method stub

			Log.d(TAG, "OnCancelled with result");

			// super.onCancelled(result);

		}

		@Override
		protected void onPostExecute(Void result) {

			// TODO Auto-generated method stub

			Log.d(TAG, "Post Execute");

			buttonStartProgress.setEnabled(true);

			buttonCancelProgress.setEnabled(false);

			currentTxt.setText("Progress is completed");

		}

		@Override
		protected void onPreExecute() {

			// TODO Auto-generated method stub

			buttonStartProgress.setEnabled(false);

			currentTxt.setText("Progress ongoing...");

		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			// TODO Auto-generated method stub

			progressBar.setProgress(values[0]);

		}
	}

}