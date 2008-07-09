/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *******************************************************************************/

#include "swt.h"
#include "os_stats.h"

#ifdef NATIVE_STATS

int OS_nativeFunctionCount = 337;
int OS_nativeFunctionCallCount[337];
char * OS_nativeFunctionNames[] = {
	"CGWarpMouseCursorPosition",
	"CloseRgn",
	"CopyRgn",
	"DeleteGlobalRef",
	"DiffRgn",
	"DisposeRgn",
	"EmptyRgn",
	"Gestalt",
	"GetCurrentProcess",
	"GetRegionBounds",
	"JNIGetObject",
	"LineTo",
	"MoveTo",
	"NSAccessibilityPositionAttribute",
	"NSAccessibilitySizeAttribute",
	"NSBackgroundColorAttributeName",
	"NSBaselineOffsetAttributeName",
	"NSBitsPerPixelFromDepth",
	"NSDefaultRunLoopMode",
	"NSDeviceRGBColorSpace",
	"NSDeviceResolution",
	"NSErrorFailingURLStringKey",
	"NSFileTypeForHFSTypeCode",
	"NSFilenamesPboardType",
	"NSFontAttributeName",
	"NSForegroundColorAttributeName",
	"NSIntersectionRect",
	"NSLinkAttributeName",
	"NSParagraphStyleAttributeName",
	"NSRTFPboardType",
	"NSStrikethroughColorAttributeName",
	"NSStrikethroughStyleAttributeName",
	"NSStringPboardType",
	"NSTIFFPboardType",
	"NSURLPboardType",
	"NSUnderlineColorAttributeName",
	"NSUnderlineStyleAttributeName",
	"NewGlobalRef",
	"NewRgn",
	"OffsetRgn",
	"OpenRgn",
	"PtInRgn",
	"QDRegionToRects",
	"RectInRgn",
	"RectRgn",
	"SectRgn",
	"SetFrontProcess",
	"SetRect",
	"TransformProcessType",
	"UnionRgn",
	"class_1addIvar",
	"class_1addMethod",
	"drawRect_1CALLBACK",
	"hitTest_1CALLBACK",
	"memmove__ILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"memmove__Lorg_eclipse_swt_internal_cocoa_NSPoint_2II",
	"memmove__Lorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"memmove__Lorg_eclipse_swt_internal_cocoa_NSRect_2II",
	"memmove__Lorg_eclipse_swt_internal_cocoa_NSSize_2II",
	"objc_1allocateClassPair",
	"objc_1getClass",
	"objc_1lookUpClass",
	"objc_1msgSend__II",
	"objc_1msgSend__IIB",
	"objc_1msgSend__IID",
	"objc_1msgSend__IIDD",
	"objc_1msgSend__IIDI",
	"objc_1msgSend__IIDIIIZ",
	"objc_1msgSend__IIDIZ",
	"objc_1msgSend__IIF",
	"objc_1msgSend__IIFF",
	"objc_1msgSend__IIFFFF",
	"objc_1msgSend__IIFFFFF",
	"objc_1msgSend__IIFI",
	"objc_1msgSend__IIFIF",
	"objc_1msgSend__IIFII",
	"objc_1msgSend__IIFIII",
	"objc_1msgSend__III",
	"objc_1msgSend__IIIDIIIZ",
	"objc_1msgSend__IIIF",
	"objc_1msgSend__IIIFFF",
	"objc_1msgSend__IIIFI",
	"objc_1msgSend__IIIFILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIII",
	"objc_1msgSend__IIIID",
	"objc_1msgSend__IIIIDI",
	"objc_1msgSend__IIIIF",
	"objc_1msgSend__IIIIFII",
	"objc_1msgSend__IIIII",
	"objc_1msgSend__IIIIIF",
	"objc_1msgSend__IIIIII",
	"objc_1msgSend__IIIIIII",
	"objc_1msgSend__IIIIIIII",
	"objc_1msgSend__IIIIIIIII",
	"objc_1msgSend__IIIIIIILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIIIIIILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IIIIIIIZZIII",
	"objc_1msgSend__IIIIIIIZZIIII",
	"objc_1msgSend__IIIIIILorg_eclipse_swt_internal_cocoa_NSRect_2FFF",
	"objc_1msgSend__IIIIILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIIIILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIIIILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IIIIISI",
	"objc_1msgSend__IIIIIZ",
	"objc_1msgSend__IIIIIZI",
	"objc_1msgSend__IIIIJ",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRange_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2IILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend__IIIILorg_eclipse_swt_internal_cocoa_NSRect_2II",
	"objc_1msgSend__IIIIS",
	"objc_1msgSend__IIIIZ",
	"objc_1msgSend__IIIIZI",
	"objc_1msgSend__IIIIZII",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2IDIIIIF",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2IDIIIII",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2IDIIIIZS",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2IDIISII",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2II",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSSize_2IIIZ",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSPoint_2Z",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRange_2IZ",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2II",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2IIZ",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2IZ",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2IZI",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSRect_2ZI",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend__IIILorg_eclipse_swt_internal_cocoa_NSSize_2Z",
	"objc_1msgSend__IIIS",
	"objc_1msgSend__IIISZZZZ",
	"objc_1msgSend__IIIZ",
	"objc_1msgSend__IIIZI",
	"objc_1msgSend__IIIZII",
	"objc_1msgSend__IIIZIII",
	"objc_1msgSend__IIIZZ",
	"objc_1msgSend__IIIZZII",
	"objc_1msgSend__IIJ",
	"objc_1msgSend__IIJI",
	"objc_1msgSend__IIJSZ",
	"objc_1msgSend__IILjava_lang_String_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSAffineTransformStruct_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSDecimal_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2F",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2FFF",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2FFFZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2FLorg_eclipse_swt_internal_cocoa_NSPoint_2FI",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2IF",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2II",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2I_3F",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2F",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2IZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRect_2F",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRect_2IF",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2IFLorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2III",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2IIII",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2IIIII",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2ILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2IZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRange_2ZI",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2F",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2FF",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2II",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2III",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2IIII",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2IIIII",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2IIZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2IIZI",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2IZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2IZI",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSRect_2F",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRect_2IF",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2Z",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSRect_2ZZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSSize_2I",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSSize_2IZZ",
	"objc_1msgSend__IILorg_eclipse_swt_internal_cocoa_NSSize_2Lorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIS",
	"objc_1msgSend__IISI",
	"objc_1msgSend__IIZ",
	"objc_1msgSend__IIZI",
	"objc_1msgSend__IIZII",
	"objc_1msgSend__IIZLorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__IIZLorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend__IIZLorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend__IIZZ",
	"objc_1msgSend__II_3B",
	"objc_1msgSend__II_3BI",
	"objc_1msgSend__II_3C",
	"objc_1msgSend__II_3CI",
	"objc_1msgSend__II_3CLorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend__II_3F",
	"objc_1msgSend__II_3FIF",
	"objc_1msgSend__II_3I",
	"objc_1msgSend__II_3III",
	"objc_1msgSendSuper__Lorg_eclipse_swt_internal_cocoa_objc_1super_2I",
	"objc_1msgSendSuper__Lorg_eclipse_swt_internal_cocoa_objc_1super_2II",
	"objc_1msgSendSuper__Lorg_eclipse_swt_internal_cocoa_objc_1super_2IIIII",
	"objc_1msgSendSuper__Lorg_eclipse_swt_internal_cocoa_objc_1super_2ILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSendSuper__Lorg_eclipse_swt_internal_cocoa_objc_1super_2ILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSendSuper__Lorg_eclipse_swt_internal_cocoa_objc_1super_2ILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend_1fpret__II",
	"objc_1msgSend_1fpret__IID",
	"objc_1msgSend_1fpret__IIF",
	"objc_1msgSend_1fpret__III",
	"objc_1msgSend_1fpret__IIIF",
	"objc_1msgSend_1fpret__IIIFI",
	"objc_1msgSend_1fpret__IIII",
	"objc_1msgSend_1fpret__IIIIF",
	"objc_1msgSend_1fpret__IIIIZF",
	"objc_1msgSend_1fpret__IIILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1fpret__IILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend_1fpret__IILorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend_1fpret__IILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1fpret__IILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSAffineTransformStruct_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSDecimal_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2III",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IIIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IIIIIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IIILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IIISLorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IILorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2III",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIIIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIIIIIZ",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIIIIZII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIIILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIIILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IIILorg_eclipse_swt_internal_cocoa_NSRange_2Lorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2III",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIIILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIIILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIIIZ",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSPoint_2Lorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSRect_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSRect_2III",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSRect_2Lorg_eclipse_swt_internal_cocoa_NSRect_2ILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSSize_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IILorg_eclipse_swt_internal_cocoa_NSSize_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSRect_2IIZ",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IIF",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2III",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IIII",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IIILorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IIILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IILorg_eclipse_swt_internal_cocoa_NSRect_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IILorg_eclipse_swt_internal_cocoa_NSSize_2",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IILorg_eclipse_swt_internal_cocoa_NSSize_2I",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IILorg_eclipse_swt_internal_cocoa_NSSize_2ZZI",
	"objc_1msgSend_1stret__Lorg_eclipse_swt_internal_cocoa_NSSize_2IIZ",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSPoint_2II",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSPoint_2III",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IILorg_eclipse_swt_internal_cocoa_NSPoint_2",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSPoint_2IILorg_eclipse_swt_internal_cocoa_NSPoint_2I",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSRange_2II",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSRange_2III",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRange_2",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSRange_2IILorg_eclipse_swt_internal_cocoa_NSRange_2I",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSSize_2II",
	"objc_1msgSend_1struct__Lorg_eclipse_swt_internal_cocoa_NSSize_2IILorg_eclipse_swt_internal_cocoa_NSSize_2ZZI",
	"objc_1registerClassPair",
	"object_1getClassName",
	"object_1getInstanceVariable",
	"object_1setInstanceVariable",
	"sel_1registerName",
	"setFrameOrigin_1CALLBACK",
	"setFrameSize_1CALLBACK",
	"setFrame_1CALLBACK",
	"webView_1setFrame_1CALLBACK",
};

#define STATS_NATIVE(func) Java_org_eclipse_swt_tools_internal_NativeStats_##func

JNIEXPORT jint JNICALL STATS_NATIVE(OS_1GetFunctionCount)
	(JNIEnv *env, jclass that)
{
	return OS_nativeFunctionCount;
}

JNIEXPORT jstring JNICALL STATS_NATIVE(OS_1GetFunctionName)
	(JNIEnv *env, jclass that, jint index)
{
	return (*env)->NewStringUTF(env, OS_nativeFunctionNames[index]);
}

JNIEXPORT jint JNICALL STATS_NATIVE(OS_1GetFunctionCallCount)
	(JNIEnv *env, jclass that, jint index)
{
	return OS_nativeFunctionCallCount[index];
}

#endif
