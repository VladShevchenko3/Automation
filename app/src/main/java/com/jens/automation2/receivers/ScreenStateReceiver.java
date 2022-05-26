package com.jens.automation2.receivers;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.jens.automation2.ActivityPermissions;
import com.jens.automation2.AutomationService;
import com.jens.automation2.Miscellaneous;
import com.jens.automation2.Rule;
import com.jens.automation2.Trigger.Trigger_Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenStateReceiver extends BroadcastReceiver implements AutomationListenerInterface
{
	static int screenState = -1;    // initialize with a better value than this
	public static AutomationService automationServiceRef = null;

	private static boolean screenStateReceiverActive = false;
	private static IntentFilter screenStateIntentFilter = null;
	private static Intent screenStatusIntent = null;
	private static BroadcastReceiver screenStateReceiverInstance = null;

	public final static String broadcastScreenLocked = "automation.system.screen_locked";

	public static BroadcastReceiver getScreenStateReceiverInstance()
	{
		if (screenStateReceiverInstance == null)
			screenStateReceiverInstance = new ScreenStateReceiver();

		return screenStateReceiverInstance;
	}

	public static void startScreenStateReceiver(final AutomationService automationServiceRef)
	{
		if (!screenStateReceiverActive)
		{
			ScreenStateReceiver.automationServiceRef = automationServiceRef;

			if (screenStateReceiverInstance == null)
				screenStateReceiverInstance = new ScreenStateReceiver();

			if (screenStateIntentFilter == null)
			{
				screenStateIntentFilter = new IntentFilter();
				screenStateIntentFilter.addAction(Intent.ACTION_SCREEN_OFF);
				screenStateIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
				screenStateIntentFilter.addAction(Intent.ACTION_USER_PRESENT);    // also fired when device is unlocked
				screenStateIntentFilter.addAction(broadcastScreenLocked);
//				Intent.ACTION_USER_UNLOCKED
			}

			screenStatusIntent = automationServiceRef.registerReceiver(screenStateReceiverInstance, screenStateIntentFilter);

			screenStateReceiverActive = true;
		}
	}

	public static void stopScreenStateReceiver()
	{
		if (screenStateReceiverActive)
		{
			if (screenStateReceiverInstance != null)
			{
				automationServiceRef.unregisterReceiver(screenStateReceiverInstance);
				screenStateReceiverInstance = null;
			}

			screenStateReceiverActive = false;
		}
	}

	public static boolean isScreenStateReceiverActive()
	{
		return screenStateReceiverActive;
	}

	public static int getScreenState()
	{
		return screenState;
	}

	private static int currentChargingState = 0; //0=unknown, 1=no, 2=yes

	public static int getCurrentChargingState()
	{
		return currentChargingState;
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent == null)
			return;
		if (context == null)
			return;

		Miscellaneous.logEvent("e", "ScreenStateReceiver", "Received: " + intent.getAction(), 3);

		try
		{
			if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
			{
				ScreenStateReceiver.screenState = 0;

//				Method 2
//				PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
				KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//				Miscellaneous.logEvent("i", "ScreenStateReceiver", "Method 2: " + String.valueOf(pm.isInteractive() && pm.isScreenOn() && keyguardManager.isKeyguardLocked() && keyguardManager.isDeviceLocked()), 4);
//				if (pm.isInteractive() && pm.isScreenOn() && keyguardManager.isKeyguardLocked() && keyguardManager.isDeviceLocked())
//				{
//
//				}

//				Miscellaneous.logEvent("i", "ScreenStateReceiver", "pm.isInteractive(): " + String.valueOf(pm.isInteractive()), 4);
//				Miscellaneous.logEvent("i", "ScreenStateReceiver", "pm.isScreenOn(): " + String.valueOf(pm.isScreenOn()), 4);
				Miscellaneous.logEvent("i", "ScreenStateReceiver", "keyguardManager.isKeyguardLocked(): " + String.valueOf(keyguardManager.isKeyguardLocked()), 4);
				Miscellaneous.logEvent("i", "ScreenStateReceiver", "keyguardManager.isDeviceLocked(): " + String.valueOf(keyguardManager.isDeviceLocked()), 4);

				boolean locked = keyguardManager.isKeyguardLocked() && keyguardManager.isDeviceLocked();
				if (locked)
				{
					sendLockBroadcast(context);
				}
				else
				{
					// Lock may be activated delayed, not at power button press
					ScreenLockMonitor mon = new ScreenLockMonitor();
					mon.startMonitor();
				}
			}
			else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
			{
				ScreenStateReceiver.screenState = 1;
			}
			else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))
			{
				ScreenStateReceiver.screenState = 2;
			}
			else if (intent.getAction().equals(broadcastScreenLocked))
			{
				ScreenStateReceiver.screenState = 3;
			}
			else
			{
				Miscellaneous.logEvent("e", "ScreenStateReceiver", "Unknown state received: " + intent.getAction(), 3);
			}
		}
		catch (Exception e)
		{
			Miscellaneous.logEvent("e", "ScreenStateReceiver", "Error receiving screen state: " + e.getMessage(), 3);
		}

		ArrayList<Rule> ruleCandidates = Rule.findRuleCandidates(Trigger_Enum.screenState);
		for (int i = 0; i < ruleCandidates.size(); i++)
		{
			if (ruleCandidates.get(i).getsGreenLight(context))
				ruleCandidates.get(i).activate(automationServiceRef, false);
		}
	}

	@Override
	public void startListener(AutomationService automationService)
	{
		ScreenStateReceiver.startScreenStateReceiver(automationService);
	}

	@Override
	public void stopListener(AutomationService automationService)
	{
		ScreenStateReceiver.stopScreenStateReceiver();
	}

	public static boolean haveAllPermission()
	{
		return ActivityPermissions.havePermission(Manifest.permission.READ_PHONE_STATE, Miscellaneous.getAnyContext()) &&
				ActivityPermissions.havePermission(Manifest.permission.BATTERY_STATS, Miscellaneous.getAnyContext());
	}

	@Override
	public boolean isListenerRunning()
	{
		return ScreenStateReceiver.isScreenStateReceiverActive();
	}

	@Override
	public Trigger_Enum[] getMonitoredTrigger()
	{
		return new Trigger_Enum[]{Trigger_Enum.screenState};
	}

	class ScreenLockMonitor
	{
		long runs = 0;
		final long maxRuns = 20;
		final long interval = 1000;

		Timer timer = new Timer();

		TimerTask task = new TimerTask()
		{
			@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
			@Override
			public void run()
			{
				KeyguardManager keyguardManager = (KeyguardManager) Miscellaneous.getAnyContext().getSystemService(Context.KEYGUARD_SERVICE);

				boolean locked = keyguardManager.isKeyguardLocked() && keyguardManager.isDeviceLocked();
				if (locked)
				{
					sendLockBroadcast(Miscellaneous.getAnyContext());
					timer.purge();
					timer.cancel();
				}
				else
				{
					if (runs++ > maxRuns)
					{
						Miscellaneous.logEvent("w", "ScreenStateReceiver->ScreenLockMonitor", "Lock never came.", 4);
						timer.purge();
						timer.cancel();
					}
				}
			}
		};

		public void startMonitor()
		{
			ContentResolver mResolver = Miscellaneous.getAnyContext().getContentResolver();
			long lockscreen_timeout = 0;

			if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1)
				lockscreen_timeout = Settings.System.getInt(mResolver, "lock_screen_lock_after_timeout", 0);
			else
				lockscreen_timeout = Settings.Secure.getInt(mResolver, "lock_screen_lock_after_timeout", 0);

			if(lockscreen_timeout > 0)
				timer.schedule(task, lockscreen_timeout);
			else
				timer.scheduleAtFixedRate(task, 0, interval);
		}
	}

	static void sendLockBroadcast(Context context)
	{
		Intent lockedBroadcastIntent = new Intent();
		lockedBroadcastIntent.setAction(broadcastScreenLocked);
		context.sendBroadcast(lockedBroadcastIntent);
	}
}