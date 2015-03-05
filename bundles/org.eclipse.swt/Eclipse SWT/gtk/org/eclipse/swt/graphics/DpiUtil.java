/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.graphics;

/**
 * This class hold common constants and utility functions w.r.t. to SWT high DPI
 * functionality.
 * 
 * @since 3.104
 */
public class DpiUtil {
	public static final char FILE_EXTENSION_SEPARATOR = '.';
	public static final String FILE_ONE_HALF_IDENTIFIER = "@1.5x";
	public static final String FILE_DOUBLE_IDENTIFIER = "@2x";
	public static final int SIZE = 3;

	/**
	 * Prepare an array of Image filenames for various DPI levels.
	 * 
	 * @return String[] image filenames
	 */
	public static String[] getImageNames (String fileName) {
		if (fileName == null || fileName.trim().length() == 0)
			return new String[0];

		String[] fileNames = new String[SIZE];
		int separatorLoc = fileName.lastIndexOf (FILE_EXTENSION_SEPARATOR);
		if (separatorLoc != -1) {
			fileNames[0] = fileName;
			fileNames[1] = fileName.substring (0, separatorLoc) + FILE_ONE_HALF_IDENTIFIER + FILE_EXTENSION_SEPARATOR
					+ fileName.substring(separatorLoc + 1);
			fileNames[2] = fileName.substring (0, separatorLoc) + FILE_DOUBLE_IDENTIFIER + FILE_EXTENSION_SEPARATOR
					+ fileName.substring (separatorLoc + 1);
		}
		return fileNames;
	}

	/**
	 * Compute the imageSelector index based on the DPI value.
	 * 
	 * @return imageSelector index
	 */
	public static int mapDpiToImageSelectorIndex (int dpi) {
		int imageSelectorIndex;
		if (dpi >= 192) {
			imageSelectorIndex = 2;
		} else if (dpi >= 144) {
			imageSelectorIndex = 1;
		} else {
			imageSelectorIndex = 0;
		}
		return imageSelectorIndex;
	}

	/**
	 * Compute the imageSelector index based on the zoom value.
	 * 
	 * @return imageSelector index
	 */
	public static int mapZoomToImageSelectorIndex (int zoom) {
		int imageSelectorIndex = 0;
		switch (zoom) {
		case 200:
			imageSelectorIndex = 2;
			break;
		case 150:
			imageSelectorIndex = 1;
			break;
		case 100:
		default:
			imageSelectorIndex = 0;
			break;
		}
		return imageSelectorIndex;
	}
}
