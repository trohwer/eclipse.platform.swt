/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
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
class DPIUtil {

	/* DPI Constants */
	static final int DPI_ZOOM_200 = 192;
	static final int DPI_ZOOM_150 = 144;

	/**
	 * Compute the zoom value based on the DPI value.
	 * 
	 * @return zoom
	 */
	static int mapDPIToZoom (int dpi) {
		int zoom;
		if (dpi >= DPI_ZOOM_200) {
			zoom = 200;
		} else if (dpi >= DPI_ZOOM_150) {
			zoom = 150;
		} else {
			zoom = 100;
		}
		return zoom;
	}
	
	/**
	 * Refreshes the zoom level of the image if required.
	 * 
	 * @param image to be refreshed
	 * @return true if zoom level is refreshed
	 */
	static boolean refreshImageZoomLevel (Image image) {
		if (image == null) return false;
		int zoom = image.getZoom();
		if (zoom != image.currentZoomLevel) {
			if (image.fileNameImageProvider != null) {
				String filename = image.fileNameImageProvider.getImagePath(zoom);
				image.initNative(filename);
			} else if (image.imageDataProvider != null) {
				ImageData data = image.imageDataProvider.getImageData(zoom);
				image.init(data);
			} else {
				return false;
			}
			image.currentZoomLevel = zoom;
			return true;
		}
		return false;
	}
}
