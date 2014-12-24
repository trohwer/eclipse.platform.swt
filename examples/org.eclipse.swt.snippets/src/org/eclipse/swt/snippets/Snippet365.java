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
	static Image newImage;

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Transparent Background");
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		// Standard color background for Shell
//		shell.setBackground(display.getSystemColor(SWT.COLOR_CYAN));

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
				if (transparent){
					composite.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));
					buttonCheckBox.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));
					bar.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));
					label.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));
					radio.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));
					check.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));
				}
				else {
					composite.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
					buttonCheckBox.setBackground(null);
					bar.setBackground(null);
					label.setBackground(null);
					radio.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
					check.setBackgroundImage(getBackgroundImage(display));
				}
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
		radio.setSelection(true);
		radio.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		check = new Button(composite, SWT.CHECK);
		check.setText("Check box Button");
		check.setSelection(true);
		// Image
		check.setBackgroundImage(getBackgroundImage(display));

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

	private static Image getBackgroundImage(final Display display) {
		if (newImage == null) {
			Rectangle rect = new Rectangle(0, 0, 115, 5);
			newImage = new Image(display, Math.max(1, rect.width), 1);
			GC gc = new GC(newImage);
			gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
			gc.fillGradientRectangle(rect.x, rect.y, rect.width, 1, false);
			gc.dispose();
		}
		return newImage;
	}

}
