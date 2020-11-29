package com.cloudcrlmonitor.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.crlmonitor.entity.MonitoredUrl;

import com.cloudcrlmonitor.restclient.MonitoredUrlUtil;
import com.example.cloudcrlmonitor.R;

public class ListaActivity extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);

		MonitoredUrlUtil monitoredUrlUtil = new MonitoredUrlUtil();
		List<MonitoredUrl> monitoredUrlList = new ArrayList<MonitoredUrl>();
		try {
			monitoredUrlList = monitoredUrlUtil.getUrlStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);

		String[] values = new String[]{"lista vazia"};
		if (monitoredUrlList != null && monitoredUrlList.size() > 0) {
			values = new String[monitoredUrlList.size()];
			for (int i = 0; i < monitoredUrlList.size(); i++) {
				String item = monitoredUrlList.get(i).getUrl() + "|" + monitoredUrlList.get(i).getStatus();
				values[i] = item;
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition = position;

				// ListView Clicked item value
				String itemValue = (String) listView
						.getItemAtPosition(position);

				// Show Alert
				Toast.makeText(
						getApplicationContext(),
						"Position :" + itemPosition + "  ListItem : "
								+ itemValue, Toast.LENGTH_LONG).show();

			}

		});
	}

}