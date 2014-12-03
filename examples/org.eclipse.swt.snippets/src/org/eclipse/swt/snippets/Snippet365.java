/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.snippets;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Transparent Background example snippet: Set transparent background of a
 * native control.
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
public class Snippet365 {
	static Image oldImage;
	static Composite composite;
	static Button buttonCheckBox;
	static ToolBar bar;
	static Label label;
	static List list;
	static Button radio;
	static Button check;
	static Button push;
	static Text text;

	public static void main1(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		CTabFolder folder = new CTabFolder(shell, SWT.BORDER);
		folder.setBackground(
				new Color[] { display.getSystemColor(SWT.COLOR_RED), display.getSystemColor(SWT.COLOR_YELLOW) },
				new int[] { 90 }, true);

		CTabItem item = new CTabItem(folder, SWT.CLOSE);
		item.setText("Item");

		Composite comp = new Composite(folder, SWT.NONE);
		comp.setLayout(new RowLayout(SWT.HORIZONTAL));
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Shell.setBackgroundMode()");
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		// Standard color background for Shell
		shell.setBackground(display.getSystemColor(SWT.COLOR_CYAN));

		// Gradient background for Shell
		shell.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Rectangle rect = shell.getClientArea();
				Image newImage = new Image(display, Math.max(1, rect.width), 1);
				GC gc = new GC(newImage);
				gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
				gc.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
				gc.fillGradientRectangle(rect.x, rect.y, rect.width, 1, false);
				gc.dispose();
				shell.setBackgroundImage(newImage);
				if (oldImage != null)
					oldImage.dispose();
				oldImage = newImage;
			}
		});
		// Create INHERIT Radio
		// createInheritRadio(shell);

		composite = new Composite(shell, SWT.BORDER);
		composite.setLayout(new RowLayout());

		// Transparent
		buttonCheckBox = new Button(composite, SWT.CHECK | SWT.None);
		buttonCheckBox.setText("Transparent");
		buttonCheckBox.setSelection(false);
		buttonCheckBox.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean transparent = ((Button) e.getSource()).getSelection();
				composite.setBackground(new Color(display, composite.getBackground().getRGB(), transparent));
				buttonCheckBox.setBackground(new Color(Display.getDefault(), buttonCheckBox.getBackground().getRGB(),
						transparent));
				bar.setBackground(new Color(display, bar.getBackground().getRGB(), transparent));
				list.setBackground(new Color(display, list.getBackground().getRGB(), transparent));
				label.setBackground(new Color(display, label.getBackground().getRGB(), transparent));
				radio.setBackground(new Color(display, radio.getBackground().getRGB(), transparent));
				check.setBackground(new Color(display, check.getBackground().getRGB(), transparent));
				push.setBackground(new Color(display, push.getBackground().getRGB(), transparent));
				text.setBackground(new Color(display, text.getBackground().getRGB(), transparent));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// Toolbar
		bar = new ToolBar(composite, SWT.FLAT);
		for (int i = 0; i < 2; i++) {
			ToolItem item2 = new ToolItem(bar, SWT.PUSH);
			item2.setText("Item " + i);
		}

		Rectangle clientArea = composite.getClientArea();
		bar.setLocation(clientArea.x, clientArea.y);
		bar.pack();

		// create children
		label = new Label(composite, SWT.NONE);
		label.setText("Label");
		radio = new Button(composite, SWT.RADIO);
		radio.setText("Radio Button");
		radio.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		check = new Button(composite, SWT.CHECK);
		check.setText("Check box Button");

		// Image
		Rectangle rect = new Rectangle(0, 0, 115, 5);
		Image newImage = new Image(display, Math.max(1, rect.width), 1);
		GC gc = new GC(newImage);
		gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
		gc.fillGradientRectangle(rect.x, rect.y, rect.width, 1, false);
		gc.dispose();
		shell.setBackgroundImage(newImage);
		check.setBackgroundImage(newImage);

		push = new Button(composite, SWT.PUSH);
		push.setText("Push Button");
		list = new List(composite, SWT.BORDER | SWT.MULTI);
		list.add("List item 1");
		list.add("List item 2");
		text = new Text(composite, SWT.BORDER);
		text.setText("Text");
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
