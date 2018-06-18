package org.eclipse.swt.snippets;

/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
/*
 * Snippet to display/refresh Monitor DPI dynamically if supported by OS.
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 *
 * @since 3.0
 */
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Dynamic DPI example snippet: Display current monitor handle/zoom and option
 * to refresh these values.
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
public class Snippet373 {
	private static final String IMAGE_100 = "eclipse16.png";
	private static final String IMAGE_150 = "eclipse24.png";
	private static final String IMAGE_200 = "eclipse32.png";

	private static final String IMAGES_ROOT = "bin/org/eclipse/swt/snippets/";

	private static final String IMAGE_PATH_100 = IMAGES_ROOT + IMAGE_100;
	private static final String IMAGE_PATH_150 = IMAGES_ROOT + IMAGE_150;
	private static final String IMAGE_PATH_200 = IMAGES_ROOT + IMAGE_200;
	static final ImageFileNameProvider filenameProvider = zoom -> {
		String path = null;
		switch (zoom) {
		case 150:
			path = IMAGE_PATH_150;
			break;
		case 200:
			path = IMAGE_PATH_200;
			break;
		default:
			path = IMAGE_PATH_100;
		}
		System.out.println("ImageFileNameProvider: " + zoom + " : " + path);
		return path;
	};

	public static void main(String[] args) {
		System.setProperty("swt.autoScale", "quarter");
		Display display = new Display();
		final Image eclipse = new Image(display, filenameProvider);

		Shell shell = new Shell(display);
		shell.setImage(eclipse);
		shell.setText("DynamicDPI @ " + DPIUtil.getDeviceZoom() );
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		shell.setLocation(100, 200);
		shell.setSize(400, 400);

		// Menu
		Menu bar = new Menu (shell, SWT.BAR);
		shell.setMenuBar (bar);
		MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
		fileItem.setText ("&File");
		fileItem.setImage(eclipse);
		Menu submenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu (submenu);
		MenuItem subItem = new MenuItem (submenu, SWT.PUSH);
		subItem.addListener (SWT.Selection, e -> System.out.println ("Select All"));
		subItem.setText ("Select &All\tCtrl+A");
		subItem.setAccelerator (SWT.MOD1 + 'A');
		subItem.setImage(eclipse);

		System.out.println(bar.getItems());

		// CTabFolder
		CTabFolder folder = new CTabFolder(shell, SWT.BORDER);
		for (int i = 0; i < 2; i++) {
			CTabItem cTabItem = new CTabItem(folder, i % 2 == 0 ? SWT.CLOSE : SWT.NONE);
			cTabItem.setText("Item "+i);
			Text textMsg = new Text(folder, SWT.MULTI);
			textMsg.setText("Content for Item "+i);
			cTabItem.setControl(textMsg);
			cTabItem.setImage(eclipse);
		}

		// PerMonitorV2 setting
//		Label label = new Label (shell, SWT.BORDER);
//		label.setText("PerMonitorV2 value before:after:Error");
//		Text text = new Text(shell, SWT.BORDER);
//		text.setText(DPIUtil.BEFORE + ":" + DPIUtil.AFTER + ":" + DPIUtil.RESULT);

		// Composite for Label, Button, Tool-bar
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));

		// Label with Image
		Label label1 = new Label (composite, SWT.BORDER);
		label1.setImage (eclipse);

		// Label with text only
		Label label2 = new Label (composite, SWT.BORDER);
		label2.setText ("No Image");

		// Button with text + Old Image Constructor
		Button oldButton1 = new Button(composite, SWT.PUSH);
		oldButton1.setText("Old Img");
		oldButton1.setImage(new Image(display, IMAGE_PATH_100));

		// Button with Old Image Constructor
//		Button oldButton2 = new Button(composite, SWT.PUSH);
//		oldButton2.setImage(new Image(display, filenameProvider.getImagePath(100)));

		// Button with Image
		Button createDialog = new Button(composite, SWT.PUSH);
		createDialog.setText("Child Dialog");
		createDialog.setImage(eclipse);
		createDialog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				final Shell dialog = new Shell (shell, SWT.DIALOG_TRIM | SWT.RESIZE);
				dialog.setText("Child Dialog");
				RowLayout rowLayout = new RowLayout (SWT.VERTICAL);
				dialog.setLayout (rowLayout);
				Label label = new Label (dialog, SWT.BORDER);
				label.setImage(eclipse);
				Point location = shell.getLocation();
				dialog.setLocation(location.x + 250, location.y + 50);
				dialog.pack ();
				dialog.open ();
			}
		});

		// Toolbar with Image
		ToolBar toolBar = new ToolBar (composite, SWT.FLAT | SWT.BORDER );
		Rectangle clientArea = shell.getClientArea ();
		toolBar.setLocation (clientArea.x, clientArea.y);
		for (int i=0; i<2; i++) {
			int style = i % 2 == 1 ? SWT.DROP_DOWN : SWT.PUSH;
			ToolItem toolItem = new ToolItem (toolBar, style);
			toolItem.setImage (eclipse);
		}
		toolBar.pack ();

		Button button = new Button(shell, SWT.PUSH);
		button.setText("Refresh-Current Monitor [Handle : Zoom] ");
		Text text1 = new Text(shell, SWT.BORDER);
		Monitor monitor = button.getMonitor();
		text1.setText("" + monitor.getZoom());
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Monitor monitor = button.getMonitor();
				text1.setText("" + monitor.getZoom());
			}
		});
		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("Refresh-Both Monitors [Handle : Zoom]");
		Text text2 = new Text(shell, SWT.BORDER);
		Monitor[] monitors = display.getMonitors();
		StringBuffer text2String = new StringBuffer();
		for (int i = 0; i < monitors.length; i++) {
			text2String.append(monitors[i].getZoom()  + (i < (monitors.length -1) ? " - " : ""));
		}
		text2.setText(text2String.toString());
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Monitor[] monitors = display.getMonitors();
				StringBuffer text2String = new StringBuffer();
				for (int i = 0; i < monitors.length; i++) {
					text2String.append(monitors[i].getZoom()  + (i < (monitors.length -1) ? " - " : ""));
				}
				text2.setText(text2String.toString());
			}
		});

		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
