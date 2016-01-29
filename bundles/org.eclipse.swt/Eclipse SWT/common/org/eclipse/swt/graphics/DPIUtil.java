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

import org.eclipse.swt.*;

/**
 * This class hold common constants and utility functions w.r.t. to SWT high DPI
 * functionality.
 *
 * @since 3.105
 */
public class DPIUtil {

	/* DPI Constants */
	static final int DPI_ZOOM_200 = 192;
	static final int DPI_ZOOM_150 = 144;

	private static boolean autoScale = true;

/**
 * Auto-scale down ImageData
 */
public static ImageData autoScaleDown (ImageData imageData, Device device) {
	if (!getAutoScale () || imageData == null || device == null) return imageData;
	float scaleFactor = getScalingFactor (device);
	return scaleFactor == 1 ? imageData
			: imageData.scaledTo ((int)((float)imageData.width / scaleFactor), (int)((float)imageData.height / scaleFactor));
}

/**
 * Auto-scale down int dimensions.
 */
public static int autoScaleDown (int size, Device device) {
	if (!getAutoScale () || device == null) return size;
	float scaleFactor = getScalingFactor (device);
	return (int)(scaleFactor ==1 ? size : size / scaleFactor);
}

/**
 * Returns a new scaled down Point.
 */
public static Point autoScaleDown (Point point, Device device) {
	if (!getAutoScale () || point == null || device == null) return point;
	float scaleFactor = getScalingFactor (device);
	if (scaleFactor == 1) return point;
	Point scaledPoint = new Point (0,0);
	scaledPoint.x = (int) (point.x / scaleFactor);
	scaledPoint.y = (int) (point.y / scaleFactor);
	return scaledPoint;
}

/**
 * Returns a new scaled down Rectangle.
 */
public static Rectangle autoScaleDown (Rectangle rect, Device device) {
	if (!getAutoScale () || rect == null || device == null) return rect;
	float scaleFactor = getScalingFactor (device);
	if (scaleFactor == 1) return rect;
	Rectangle scaledRect = new Rectangle (0,0,0,0);
	scaledRect.x = (int) (rect.x / scaleFactor);
	scaledRect.y = (int) (rect.y / scaleFactor);
	scaledRect.width = (int) (rect.width / scaleFactor);
	scaledRect.height = (int) (rect.height / scaleFactor);
	return scaledRect;
}

/**
 * Auto-scale image with ImageData
 */
private static ImageData autoScaleImageData (ImageData imageData, int targetZoom, int currentZoom) {
	if (!getAutoScale () || imageData == null || targetZoom == currentZoom) return imageData;
	float scaleFactor = ((float) targetZoom)/((float) currentZoom);
	return imageData.scaledTo ((int)((float)imageData.width * scaleFactor), (int)((float)imageData.height * scaleFactor));
}

/**
 * Auto-scale up ImageData
 */
public static ImageData autoScaleUp (ImageData imageData, Device device) {
	if (!getAutoScale () || imageData == null || device == null) return imageData;
	float scaleFactor = getScalingFactor (device);
	return scaleFactor == 1 ? imageData
			: imageData.scaledTo ((int)((float)imageData.width * scaleFactor), (int)((float)imageData.height * scaleFactor));
}

/**
 * Auto-scale up int dimensions.
 */
public static int autoScaleUp (int size, Device device) {
	if (!getAutoScale () || device == null) return size;
	float scaleFactor = getScalingFactor (device);
	return (int)(scaleFactor == 1 ? size : size * scaleFactor);
}

/**
 * Returns a new scaled up Point.
 */
public static Point autoScaleUp (Point point, Device device) {
	if (!getAutoScale () || point == null || device == null) return point;
	float scaleFactor = getScalingFactor (device);
	if (scaleFactor == 1) return point;
	Point scaledPoint = new Point (0,0);
	scaledPoint.x = (int) (point.x * scaleFactor);
	scaledPoint.y = (int) (point.y * scaleFactor);
	return scaledPoint;
}

/**
 * Returns a new scaled up Rectangle.
 */
public static Rectangle autoScaleUp (Rectangle rect, Device device) {
	if (!getAutoScale () || rect == null || device == null) return rect;
	float scaleFactor = getScalingFactor (device);
	if (scaleFactor == 1) return rect;
	Rectangle scaledRect = new Rectangle (0,0,0,0);
	scaledRect.x = (int) (rect.x * scaleFactor);
	scaledRect.y = (int) (rect.y * scaleFactor);
	scaledRect.width = (int) (rect.width * scaleFactor);
	scaledRect.height = (int) (rect.height * scaleFactor);
	return scaledRect;
}
public static boolean getAutoScale () {
	return autoScale;
}

/**
 * Returns an <code>ImageData</code> for specified zoom.
 */
static ImageData getImageData (Image image, int zoom) {
	if (image.currentDeviceZoom == zoom) return image._getImageData ();
	ImageData imageData = null;
	if (image.imageDataProvider != null) {
		boolean[] found = new boolean[1];
		imageData = DPIUtil.validateAndGetImageDataAtZoom (image.imageDataProvider, zoom, found);
		if (!found[0]) {
			imageData = DPIUtil.autoScaleImageData (imageData, zoom, 100);
		}
	} else if (image.imageFileNameProvider != null) {
		boolean[] found = new boolean[1];
		String filename = DPIUtil.validateAndGetImagePathAtZoom (image.imageFileNameProvider, zoom, found);
		if (!found[0]) {
			imageData = DPIUtil.autoScaleImageData (new ImageData(filename), zoom, 100);
		} else {
			imageData = new ImageData (filename);
		}
	} else {
		/* Get ImageData at currentZoom and scale it to specified zoom. */
		imageData = image._getImageData ();
		imageData = DPIUtil.autoScaleImageData (imageData, zoom, image.currentDeviceZoom);
	}
	return imageData;
}

/**
 * Gets scaling factor for the current Image
 */
public static float getscalingFactor (Image image) {
	return ((float)image.getDevice ().getDeviceZoom ()) / 100f;
}

/**
 * Returns Scaling factor from the display
 * @return float scaling factor
 */
public static float getScalingFactor (Device device) {
	float scalingFactor = 1;
	if (getAutoScale ()) {
		scalingFactor = (device.getDeviceZoom ()/100f);
	}
	return scalingFactor;
}

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
 * Returns a new rectangle as per the scaleFactor.
 */
static Rectangle scale (Rectangle rect, int targetZoom, int currentZoom) {
	if (rect == null || targetZoom == currentZoom) return rect;
	float scaleFactor = ((float)targetZoom) / (float)currentZoom;
	Rectangle returnRect = new Rectangle (0,0,0,0);
	returnRect.x = (int) (rect.x * scaleFactor);
	returnRect.y = (int) (rect.y * scaleFactor);
	returnRect.width = (int) (rect.width * scaleFactor);
	returnRect.height = (int) (rect.height * scaleFactor);
	return returnRect;
}

/**
 * Gets Image data at specified zoom level, if image is missing then
 * fall-back to 100% image. If provider or fall-back image is not available,
 * throw error.
 */
static ImageData validateAndGetImageDataAtZoom (ImageDataProvider provider, int zoom, boolean[] found) {
	if (provider == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
	ImageData data = provider.getImageData (zoom);
	found [0] = (data != null);
	/* If image is null when (zoom != 100%), fall-back to image at 100% zoom */
	if (zoom != 100 && !found [0]) data = provider.getImageData (100);
	if (data == null) SWT.error (SWT.ERROR_INVALID_ARGUMENT);
	return data;
}

/**
 * Gets Image file path at specified zoom level, if image is missing then
 * fall-back to 100% image. If provider or fall-back image is not available,
 * throw error.
 */
static String validateAndGetImagePathAtZoom (ImageFileNameProvider provider, int zoom, boolean[] found) {
	if (provider == null) SWT.error (SWT.ERROR_NULL_ARGUMENT);
	String filename = provider.getImagePath (zoom);
	found [0] = (filename != null);
	/* If image is null when (zoom != 100%), fall-back to image at 100% zoom */
	if (zoom != 100 && !found [0]) filename = provider.getImagePath (100);
	if (filename == null) SWT.error (SWT.ERROR_INVALID_ARGUMENT);
	return filename;
}

}
