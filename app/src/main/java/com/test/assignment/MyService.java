package com.test.assignment;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

public class MyService extends AccessibilityService {
	private static final String WHATSAPP_PACKAGE_NAME = "com.whatsapp";
	private boolean isWhatsAppOpened = false;
	@Override
	public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
		int eventType = accessibilityEvent.getEventType();

		if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && !isWhatsAppOpened) {
			AccessibilityNodeInfo source = accessibilityEvent.getSource();
			if (source != null && source.getPackageName().equals(WHATSAPP_PACKAGE_NAME)) {
				Toast.makeText(this, "WhatsApp Launched", Toast.LENGTH_SHORT).show();
				isWhatsAppOpened = true;
			}
		}
	}
	@Override
	protected void onServiceConnected() {
		super.onServiceConnected();
		AccessibilityServiceInfo serviceInfo = getServiceInfo();
		serviceInfo.flags |= AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS;
		setServiceInfo(serviceInfo);
	}
	@Override
	public void onInterrupt() {

	}

	@Override
	protected boolean onKeyEvent(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.KEYCODE_ALL_APPS && isWhatsAppOpened) {
			isWhatsAppOpened = false;
		}
			return super.onKeyEvent(event);
	}
}
