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
 * This interface needs to be implemented when you need Images as per client's
 * DPI level. This interface provides a callback mechanism to the client code to
 * provide absolute file path based on the OS as per the zoom level. This method
 * give a change to SWT client to have their own file naming convention for
 * various zoom level as well as loading the image files on demand.
 * 
 * @since 3.104
 */
public interface ImageDataProvider {

	/**
	 * This method give a change to SWT client to have their own file naming
	 * convention for various zoom level as well as loading the image files on
	 * demand.
	 * 
	 * @param zoom
	 *            value will be 100, 150 and 200.
	 * @return the ImageData of the image 
	 * @since 3.104
	 */
	public ImageData getImageData (int zoom);

}
