package com.hunterdavis.easyunitconversion;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class EasyUnitConversion extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());

		// set up the units spinner
		Spinner spinner = (Spinner) findViewById(R.id.conversions);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.conversions,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new MyUnitsOnItemSelectedListener());

		// name listener
		EditText nameText = (EditText) findViewById(R.id.invalue);
		nameText.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// here we call the text changed update sql function
				updateOutputText();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

	}

	// set up the listener class for spinner
	class MyUnitsOnItemSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			updateOutputText();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// 

		}
	}

	public void updateOutputText() {
		// get units to convert
		Spinner spinner = (Spinner) findViewById(R.id.conversions);
		int selectedpos = spinner.getSelectedItemPosition();
		Resources res = getResources();
		String[] unitsarray = res.getStringArray(R.array.conversions);

		// get to and from text
		String fullString = unitsarray[selectedpos];
		String[] splitString = fullString.split(" ");

		// get conversion ratio
		float conversionRatio = getConversionRatio(selectedpos);

		// get text to convert
		EditText mylocalEdit = (EditText) findViewById(R.id.invalue);
		String textToConvert = mylocalEdit.getText().toString();
		Float valueToConvert = Float.valueOf(textToConvert);

		// convert text
		Float convertedUnits = (float) 0;

		if ((selectedpos > 19) && (selectedpos < 26)) {
			switch (selectedpos) {
			case 20:
				convertedUnits = (float) (valueToConvert + 273.15);
				break;
			case 21:
				convertedUnits = (float)((float)(1.8) * (float)valueToConvert) + 32;
				break;
			case 22:
				convertedUnits = (float)(.55555555555555555) * (valueToConvert - 32);
				break;
			case 23:
				convertedUnits = (float) (((float)(.5555555555555555555) * (float)(valueToConvert - 32)) + 273.15);
				break;
			case 24:
				convertedUnits = (float) (valueToConvert - 273.15);
				break;
			case 25:
				convertedUnits = (float) (valueToConvert - 273.15);
				convertedUnits = ((float)(1.8) * convertedUnits) + 32;
				break;

			default:
				break;
			}
		} else {
			convertedUnits = conversionRatio * valueToConvert;
		}
		// add converted text to nice pretty print
		String outputText = textToConvert + " " + splitString[0] + " is "
				+ convertedUnits + " " + splitString[2];

		// set output text
		TextView myLocalTextView = (TextView) findViewById(R.id.conversiontext);
		myLocalTextView.setText(outputText);
	}

	public float getConversionRatio(int selectedpos) {
		switch (selectedpos) {
		case 0:
			return (float) 3.28083;
		case 1:
			return (float) 1.09361;
		case 2:
			return (float) 39.369923;
		case 3:
			return (float) 0.000621368;
		case 4:
			return (float) 0.3048;
		case 5:
			return (float) 0.33333333;
		case 6:
			return (float) 11.99995;
		case 7:
			return (float) 0.00018939323;
		case 8:
			return (float) 0.9144;
		case 9:
			return (float) 3;
		case 10:
			return (float) 35.99985826827454;
		case 11:
			return (float) 0.000568179699;
		case 12:
			return (float) 0.0254001;
		case 13:
			return (float) 0.08333366141732282;
		case 14:
			return (float) 0.02777788713910761;
		case 15:
			return (float) 0.00001578283157796626;
		case 16:
			return (float) 5280.0196850393695;
		case 17:
			return (float) 1760.00656167979;
		case 18:
			return (float) 1609.35;
		case 19:
			return (float) 63359.986771705626;
		case 26:
			return (float) 2.1133785314555262;
		case 27:
			return (float) 1.05668814;
		case 28:
			return (float) 0.2641721;
		case 29:
			return (float) 0.473176;
		case 30:
			return (float) 0.49999947;
		case 31:
			return (float) 0.1249999;
		case 32:
			return (float) 0.946353;
		case 33:
			return (float) 2.0000021133785313;
		case 34:
			return (float) 0.2500001320;
		case 35:
			return (float) 3.7854099999999997;
		case 36:
			return (float) 8.000004226757062;
		case 37:
			return (float) 3.9999978866237016;
		case 38:
			return (float) 1000;
		case 39:
			return (float) 0.001;
		case 40:
			return (float) 2.2046244201837775;
		case 41:
			return (float) 35.27399072294044;
		case 42:
			return (float) 0.001;
		case 43:
			return (float) 0.000001;
		case 44:
			return (float) 0.0022046244;
		case 45:
			return (float) 0.03527399072;
		case 46:
			return (float) 1000;
		case 47:
			return (float) 1000000;
		case 48:
			return (float) 2204.6244201837;
		case 49:
			return (float) 35273.9907229;
		case 50:
			return (float) 0.453592;
		case 51:
			return (float) 453.592;
		case 52:
			return (float) 0.000453592;
		case 53:
			return (float) 16;
		case 54:
			return (float) 0.0283495;
		case 55:
			return (float) 28.3495;
		case 56:
			return (float) 0.0000283495;
		case 57:
			return (float) 0.0625;

		default:
			return (float) 0.001;
		}
	}

}