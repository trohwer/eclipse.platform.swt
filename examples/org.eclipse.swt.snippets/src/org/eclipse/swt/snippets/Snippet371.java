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
public class Snippet371 {
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
		Shell shell = new Shell(display);
		shell.setText("Dynamic DPI Test");
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		shell.setSize(400, 300);
		Label label = new Label (shell, SWT.NONE);
		label.setText("PerMonitorV2 value before:after:Error");
		Text text = new Text(shell, SWT.BORDER);
		text.setText(DPIUtil.BEFORE + ":" + DPIUtil.AFTER + ":" + DPIUtil.RESULT);
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		Label label1 = new Label (composite, SWT.NONE);
		final Image eclipse = new Image(display, filenameProvider);
		label1.setImage (eclipse);

		Button createDialog = new Button(composite, SWT.PUSH);
		createDialog.setText("Open image in a new child dialog");
		createDialog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				final Shell dialog = new Shell (shell, SWT.DIALOG_TRIM | SWT.RESIZE);
				dialog.setText("Child Dialog");
				RowLayout rowLayout = new RowLayout (SWT.VERTICAL);
				dialog.setLayout (rowLayout);
				Label label = new Label (dialog, SWT.NONE);
				label.setImage(eclipse);
				Point location = shell.getLocation();
				dialog.setLocation(location.x + 250, location.y + 50);
				dialog.pack ();
				dialog.open ();
			}
		});

		Button button = new Button(shell, SWT.PUSH);
		button.setText("Refresh-Current Monitor [Handle : Zoom] ");
		Text text1 = new Text(shell, SWT.BORDER);
		Monitor monitor = button.getMonitor();
		text1.setText(monitor.handle + " : " + monitor.getZoom());
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Monitor monitor = button.getMonitor();
				text1.setText(monitor.handle + " : " + monitor.getZoom());
			}
		});
		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("Refresh-Both Monitors [Handle : Zoom]");
		Text text2 = new Text(shell, SWT.BORDER);
		Monitor[] monitors = display.getMonitors();
		text2.setText(monitors[0].handle + " : " + monitors[0].getZoom() + " - " + monitors[1].handle + " : " + monitors[1].getZoom());
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Monitor[] monitors = display.getMonitors();
				text2.setText(monitors[0].handle + " : " + monitors[0].getZoom() + " - " + monitors[1].handle + " : " + monitors[1].getZoom());
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
