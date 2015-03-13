/*******************************************************************************
 * * Copyright (c) 2000, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.snippets.hiDpiWork;

/*
 * Create a CTabFolder with min and max buttons, as well as close button and 
 * image only on selected tab.
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.0
 */
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Snippet1 {

public static void main (String [] args) {
	final Display display = new Display ();
	final ImageFileNameProvider defaultImageProvider = new ImageFileNameProvider() {
		
		@Override
		public String getImagePath(int zoom) {
			switch (zoom) {
			case 100: 
				return "collapseall.png";
			case 150: 
				return "collapseall@1.5x.png";
			case 200: 
				return "collapseall@2x.png";
			}
			return null;
		}
	};
	final ImageDataProvider imageDataProvider = new ImageDataProvider() {
		
		@Override
		public ImageData getImageData(int zoom) {
			switch (zoom) {
			case 100: 
				return new ImageData("collapseall.png");
			case 150: 
				return new ImageData("collapseall@1.5x.png");
			case 200: 
				return new ImageData("collapseall@2x.png");
			}
			return null;
		}
	};
	final Image image = new Image(display, defaultImageProvider);
	final Shell shell = new Shell (display);
	shell.setLayout(new GridLayout());
	final CTabFolder folder = new CTabFolder(shell, SWT.BORDER);
	folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	folder.setSimple(false);
	folder.setUnselectedImageVisible(false);
	folder.setUnselectedCloseVisible(false);
	for (int i = 0; i < 8; i++) {
		CTabItem item = new CTabItem(folder, SWT.CLOSE);
		item.setText("Item "+i);
		item.setImage(image);
		Text text = new Text(folder, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		text.setText("Switch\nTabs:\n Image\nZooms...\n");
		item.setControl(text);
	}
	folder.setMinimizeVisible(true);
	folder.setMaximizeVisible(true);
	folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
		@Override
		public void minimize(CTabFolderEvent event) {
			folder.setMinimized(true);
			folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			shell.layout(true);
		}
		@Override
		public void maximize(CTabFolderEvent event) {
			folder.setMaximized(true);
			folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			shell.layout(true);
		}
		@Override
		public void restore(CTabFolderEvent event) {
			folder.setMinimized(false);
			folder.setMaximized(false);
			folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			shell.layout(true);
		}
	});
	folder.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			super.widgetSelected(e);
			int index = 0;
			CTabItem selectedItem = null;
			for (CTabItem item : folder.getItems()) {
				if (item == e.item) {
					selectedItem = item;
					break;
				}
				index++;
			}
			index = index % 3;
			switch (index) {
			case 2:
				selectedItem.setImage(new Image(display, defaultImageProvider.getImagePath(200)));
				((Text)selectedItem.getControl()).setText("Zoom: 200");
				break;
			case 1:
				selectedItem.setImage(new Image(display, imageDataProvider.getImageData(150)));
				((Text)selectedItem.getControl()).setText("Zoom: 150");
				break;
			case 0:
				selectedItem.setImage(new Image(display, defaultImageProvider.getImagePath(100)));
				((Text)selectedItem.getControl()).setText("Zoom: 100");
				break;
			}
		}
	});
	shell.setSize(300, 300);
	shell.open ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	image.dispose();
	display.dispose ();
}
}

