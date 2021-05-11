package com.jens.automation2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.jens.automation2.Action.Action_Enum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivityManageActionStartActivity extends Activity
{
	ListView lvIntentPairs;
	EditText etParameterName, etParameterValue, etPackageName, etActivityOrActionPath;
	Button bSelectApp, bAddIntentPair, bSaveActionStartOtherActivity;
	Spinner spinnerParameterType;
	boolean edit = false;
	ProgressDialog progressDialog = null;
	RadioButton rbStartAppSelectByActivity, rbStartAppSelectByAction;
	
	private class CustomPackageInfo extends PackageInfo implements Comparable<CustomPackageInfo>
	{
		@Override
		public int compareTo(CustomPackageInfo another)
		{		
			String name1 = "";
			String name2 = "";
			
			ApplicationInfo aInfo1 = this.applicationInfo;
			if (aInfo1 != null)
			{
				name1 = (String) ActivityManageActionStartActivity.this.getPackageManager().getApplicationLabel(aInfo1);
			}
			ApplicationInfo aInfo2 = another.applicationInfo;
			if (aInfo2 != null)
			{
				name2 = (String) ActivityManageActionStartActivity.this.getPackageManager().getApplicationLabel(aInfo2);
			}
					
			return name1.compareTo(name2);
		}
		
	}
	
	private static List<PackageInfo> pInfos = null;
	public static Action resultingAction;

	private static final String[] supportedIntentTypes = { "boolean", "byte", "char", "double", "float", "int", "long", "short", "String", "Uri" };
	private ArrayList<String> intentPairList = new ArrayList<String>();

	ArrayAdapter<String> intentTypeSpinnerAdapter, intentPairAdapter;
	
	public static void getActivityList(final Context context)
	{
		if(pInfos == null)
		{
			pInfos = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
			Collections.sort(pInfos, new Comparator<PackageInfo>()
			{
			    public int compare(PackageInfo obj1, PackageInfo obj2)
			    {
			    	String name1 = "";
					String name2 = "";
					
					ApplicationInfo aInfo1 = obj1.applicationInfo;
					if (aInfo1 != null)
					{
						name1 = (String) context.getPackageManager().getApplicationLabel(aInfo1);
					}
					ApplicationInfo aInfo2 = obj2.applicationInfo;
					if (aInfo2 != null)
					{
						name2 = (String) context.getPackageManager().getApplicationLabel(aInfo2);
					}
							
					return name1.compareTo(name2);
			    }
			});
		}
	}
 
	public static String[] getApplicationNameListString(Context myContext)
	{
		// Generate the actual list
		getActivityList(myContext);
		
		ArrayList<String> returnList = new ArrayList<String>();
		
		for (PackageInfo pInfo : pInfos)
		{
			ApplicationInfo aInfo = pInfo.applicationInfo;
			if (aInfo != null)
			{
				String aLabel;
	
				aLabel = (String) myContext.getPackageManager().getApplicationLabel(aInfo); 

				ActivityInfo[] aInfos = pInfo.activities;
				if (aInfos != null && aInfos.length > 0)		// Only put Applications into the list that have packages.
				{
					if(!returnList.contains(aLabel))
						returnList.add(aLabel);
				}
			}
		}
		
		return returnList.toArray(new String[returnList.size()]);
	}

	public static String[] getPackageListString(Context myContext, String applicationLabel)
	{
		// Generate the actual list
		getActivityList(myContext);
		
		ArrayList<String> returnList = new ArrayList<String>();
		
		for (PackageInfo pInfo : pInfos)
		{
			if(myContext.getPackageManager().getApplicationLabel(pInfo.applicationInfo).equals(applicationLabel))
			{
				ActivityInfo[] aInfos = pInfo.activities;
				if (aInfos != null && aInfos.length > 0)
				{
					returnList.add(pInfo.packageName);
				}
			}
		}
		
		return returnList.toArray(new String[returnList.size()]);
	}

	public static String[] getPackageListString(Context myContext)
	{
		// Generate the actual list
		getActivityList(myContext);
		
		ArrayList<String> returnList = new ArrayList<String>();
		
		for (PackageInfo pInfo : pInfos)
		{
			ActivityInfo[] aInfos = pInfo.activities;
			if (aInfos != null && aInfos.length > 0)
			{
				returnList.add(pInfo.packageName);
			}
			else
				Miscellaneous.logEvent("w", "Empty Application", "Application " + myContext.getPackageManager().getApplicationLabel(pInfo.applicationInfo) + " doesn\'t have packages.", 5);
		}
		
		return returnList.toArray(new String[returnList.size()]);
	}
	
	public static String[] getActivityListForPackageName(String packageName)
	{
		ArrayList<String> returnList = new ArrayList<String>();
		
		for (PackageInfo pInfo : pInfos)
		{
			if(pInfo.packageName.equals(packageName))
			{
				ActivityInfo[] aInfos = pInfo.activities;
				if (aInfos != null)
				{
					for (ActivityInfo activityInfo : aInfos)
					{
						returnList.add(activityInfo.name);
					}
				}
			}
		}
		
		return returnList.toArray(new String[returnList.size()]);
	}
	
	public static ActivityInfo getActivityInfoForPackageNameAndActivityName(String packageName, String activityName)
	{
		for (PackageInfo pInfo : pInfos)
		{
			if(pInfo.packageName.equals(packageName))
			{
				ActivityInfo[] aInfos = pInfo.activities;
				if (aInfos != null)
				{
					for (ActivityInfo activityInfo : aInfos)
					{
						if(activityInfo.name.equals(activityName))
							return activityInfo;
					}
				}
			}
		}
		
		return null;
	}
	
	private AlertDialog getActionStartActivityDialog1()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(getResources().getString(R.string.selectApplication));
		final String[] applicationArray = ActivityManageActionStartActivity.getApplicationNameListString(this);
		alertDialogBuilder.setItems(applicationArray, new DialogInterface.OnClickListener()
		{			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				getActionStartActivityDialog2(applicationArray[which]).show();
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		return alertDialog;
	}
	private AlertDialog getActionStartActivityDialog2(String applicationName)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(getResources().getString(R.string.selectPackageOfApplication));
		final String[] packageArray = ActivityManageActionStartActivity.getPackageListString(this, applicationName);
		alertDialogBuilder.setItems(packageArray, new DialogInterface.OnClickListener()
		{			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				getActionStartActivityDialog3(packageArray[which]).show();
				Miscellaneous.messageBox(getResources().getString(R.string.hint), getResources().getString(R.string.chooseActivityHint), ActivityManageActionStartActivity.this).show();

			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		return alertDialog;
	}
	private AlertDialog getActionStartActivityDialog3(final String packageName)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(getResources().getString(R.string.selectActivityToBeStarted));
		final String activityArray[] = ActivityManageActionStartActivity.getActivityListForPackageName(packageName);
		alertDialogBuilder.setItems(activityArray, new DialogInterface.OnClickListener()
		{			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				ActivityInfo ai = ActivityManageActionStartActivity.getActivityInfoForPackageNameAndActivityName(packageName, activityArray[which]);
				etActivityOrActionPath.setText(ai.packageName + ";" + ai.name);
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		return alertDialog;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_action_start_activity);
		
		lvIntentPairs = (ListView)findViewById(R.id.lvIntentPairs);
		etParameterName = (EditText)findViewById(R.id.etParameterName);
		etParameterValue = (EditText)findViewById(R.id.etParameterValue);
		bSelectApp = (Button)findViewById(R.id.bSelectApp);
		bAddIntentPair = (Button)findViewById(R.id.bAddIntentPair);
		bSaveActionStartOtherActivity = (Button)findViewById(R.id.bSaveActionStartOtherActivity);
		spinnerParameterType = (Spinner)findViewById(R.id.spinnerParameterType);
		etPackageName = (EditText) findViewById(R.id.etPackageName);
		etActivityOrActionPath = (EditText) findViewById(R.id.etActivityOrActionPath);
		rbStartAppSelectByActivity = (RadioButton)findViewById(R.id.rbStartAppSelectByActivity);
		rbStartAppSelectByAction = (RadioButton)findViewById(R.id.rbStartAppSelectByAction);
		
		intentTypeSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.text_view_for_poi_listview_mediumtextsize, ActivityManageActionStartActivity.supportedIntentTypes);
		spinnerParameterType.setAdapter(intentTypeSpinnerAdapter);
		intentTypeSpinnerAdapter.notifyDataSetChanged();
		
		intentPairAdapter = new ArrayAdapter<String>(this, R.layout.text_view_for_poi_listview_mediumtextsize, intentPairList);
		
		bSelectApp.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				GetActivityListTask getActivityListTask = new GetActivityListTask();
				getActivityListTask.execute();
				progressDialog = ProgressDialog.show(ActivityManageActionStartActivity.this, "", ActivityManageActionStartActivity.this.getResources().getString(R.string.gettingListOfInstalledApplications));
			}
		});
		
		bAddIntentPair.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				// type;name;value
				if(spinnerParameterType.getSelectedItem().toString().length() == 0)
				{
					Toast.makeText(ActivityManageActionStartActivity.this, getResources().getString(R.string.selectTypeOfIntentPair), Toast.LENGTH_LONG).show();
					return;
				}
					
				if(etParameterName.getText().toString().length() == 0)
				{
					Toast.makeText(ActivityManageActionStartActivity.this, getResources().getString(R.string.enterNameForIntentPair), Toast.LENGTH_LONG).show();
					return;
				}
				else if(etParameterName.getText().toString().contains(Action.intentPairSeperator))
				{
					Toast.makeText(ActivityManageActionStartActivity.this, String.format(getResources().getString(R.string.stringNotAllowed), Action.intentPairSeperator), Toast.LENGTH_LONG).show();
					return;
				}
				else if(etParameterName.getText().toString().contains(";"))
				{
					Toast.makeText(ActivityManageActionStartActivity.this, String.format(getResources().getString(R.string.stringNotAllowed), ";"), Toast.LENGTH_LONG).show();
					return;
				}
				
				if(etParameterValue.getText().toString().length() == 0)
				{
					Toast.makeText(ActivityManageActionStartActivity.this, getResources().getString(R.string.enterValueForIntentPair), Toast.LENGTH_LONG).show();
					return;
				}
				else if(etParameterValue.getText().toString().contains(Action.intentPairSeperator))
				{
					Toast.makeText(ActivityManageActionStartActivity.this, String.format(getResources().getString(R.string.stringNotAllowed), Action.intentPairSeperator), Toast.LENGTH_LONG).show();
					return;
				}
				else if(etParameterValue.getText().toString().contains(";"))
				{
					Toast.makeText(ActivityManageActionStartActivity.this, String.format(getResources().getString(R.string.stringNotAllowed), ";"), Toast.LENGTH_LONG).show();
					return;
				}
				
				String param = supportedIntentTypes[spinnerParameterType.getSelectedItemPosition()] + Action.intentPairSeperator + etParameterName.getText().toString() + Action.intentPairSeperator + etParameterValue.getText().toString();
				intentPairList.add(param);
				
				spinnerParameterType.setSelection(0);
				etParameterName.setText("");
				etParameterValue.setText("");
				
				updateIntentPairList();

				if(lvIntentPairs.getVisibility() != View.VISIBLE)
					lvIntentPairs.setVisibility(View.VISIBLE);
			}
		});
		
		lvIntentPairs.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				getIntentPairDialog(arg2).show();
				return false;
			}
		});
		
		bSaveActionStartOtherActivity.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v)
			{
				if(saveAction())
				{
					ActivityManageActionStartActivity.this.setResult(RESULT_OK);
					finish();
				}
			}
		});

		lvIntentPairs.setOnTouchListener(new OnTouchListener()
		{			
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
		
		spinnerParameterType.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				if(supportedIntentTypes[arg2].equals("double") | supportedIntentTypes[arg2].equals("float") | supportedIntentTypes[arg2].equals("int") | supportedIntentTypes[arg2].equals("long") | supportedIntentTypes[arg2].equals("short"))
					ActivityManageActionStartActivity.this.etParameterValue.setInputType(InputType.TYPE_CLASS_NUMBER);
				else
					ActivityManageActionStartActivity.this.etParameterValue.setInputType(InputType.TYPE_CLASS_TEXT);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});

		rbStartAppSelectByActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
					bSelectApp.setEnabled(isChecked);
			}
		});

		rbStartAppSelectByAction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
					bSelectApp.setEnabled(!isChecked);
			}
		});
		
		Intent i = getIntent();
		if(i.getBooleanExtra("edit", false) == true)
		{
			edit = true;
			loadValuesIntoGui();
		}
	}
	
	private void loadValuesIntoGui()
	{
		boolean selectionByAction = resultingAction.getParameter1();
		rbStartAppSelectByActivity.setChecked(!selectionByAction);
		rbStartAppSelectByAction.setChecked(selectionByAction);

		String[] params = resultingAction.getParameter2().split(";");

		int startIndex = -1;

		if(!selectionByAction)
		{
			etPackageName.setText(params[0]);
			if(params.length > 1)	// should not occur, have fault tollerance
			{
				etActivityOrActionPath.setText(params[1]);

				if (params.length >= 2)
					startIndex = 2;
			}
		}
		else
		{
			if(params.length > 1)	// should not occur, have fault tollerance
			{
				if(params[1].contains(Action.intentPairSeperator))
				{
						etActivityOrActionPath.setText(params[0]);
						startIndex = 1;
				}
				else
				{
					etPackageName.setText(params[0]);
					etActivityOrActionPath.setText(params[1]);
					startIndex = 2;
				}
			}
			else
			{
				etActivityOrActionPath.setText(params[0]);
				startIndex = 1;
			}
		}

		if(startIndex > -1 && params.length > startIndex)
		{
			intentPairList.clear();

			for(int i=startIndex; i<params.length; i++)
			{
				if(lvIntentPairs.getVisibility() != View.VISIBLE)
					lvIntentPairs.setVisibility(View.VISIBLE);

				intentPairList.add(params[i]);
			}

			updateIntentPairList();
		}
	}

	private void updateIntentPairList()
	{		
		if(lvIntentPairs.getAdapter() == null)
			lvIntentPairs.setAdapter(intentPairAdapter);
		
		intentPairAdapter.notifyDataSetChanged();
	}
	
	private boolean saveAction()
	{
		if(rbStartAppSelectByActivity.isChecked())
		{
			if (etPackageName.getText().toString().length() == 0)
			{
				Toast.makeText(ActivityManageActionStartActivity.this, getResources().getString(R.string.enterPackageName), Toast.LENGTH_LONG).show();
				return false;
			}
			else if (etActivityOrActionPath.getText().toString().length() == 0)
			{
				Toast.makeText(ActivityManageActionStartActivity.this, getResources().getString(R.string.selectApplication), Toast.LENGTH_LONG).show();
				return false;
			}
		}
		else
		{
			if(etActivityOrActionPath.getText().toString().contains(";"))
			{
				Toast.makeText(this, getResources().getString(R.string.enterValidAction), Toast.LENGTH_LONG).show();
				return false;
			}
		}
		
		if(resultingAction == null)
			resultingAction = new Action();

		resultingAction.setParameter1(rbStartAppSelectByAction.isChecked());
		
		resultingAction.setAction(Action_Enum.startOtherActivity);
		
		String parameter2;

		if(rbStartAppSelectByActivity.isChecked())
			parameter2 = etPackageName.getText().toString() + ";" + etActivityOrActionPath.getText().toString();
		else
		{
			if(etPackageName.getText().toString() != null && etPackageName.getText().toString().length() > 0)
				parameter2 = etPackageName.getText().toString() + ";" + etActivityOrActionPath.getText().toString();
			else
				parameter2 = etActivityOrActionPath.getText().toString();
		}

		for(String s : intentPairList)
			parameter2 += ";" + s;
		
		resultingAction.setParameter2(parameter2);
		
		return true;
	}
	
	private AlertDialog getIntentPairDialog(final int itemPosition)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityManageActionStartActivity.this);
		alertDialogBuilder.setTitle(getResources().getString(R.string.whatToDoWithIntentPair));
		alertDialogBuilder.setItems(new String[]{getResources().getString(R.string.delete)}, new DialogInterface.OnClickListener()
		{			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// Only 1 choice at the moment, no need to check
				ActivityManageActionStartActivity.this.intentPairList.remove(itemPosition);
				updateIntentPairList();
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		return alertDialog;
	}
	
	private class GetActivityListTask extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected Void doInBackground(Void... params)
		{
			getActivityList(ActivityManageActionStartActivity.this);
			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			progressDialog.dismiss();
			getActionStartActivityDialog1().show();
		}
		
		
	}
}
