/**
 * gles_texcombiner_commandAlpha_type.java
 *
 * This file was generated by XMLSpy 2007sp2 Enterprise Edition.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the XMLSpy Documentation for further details.
 * http://www.altova.com/xmlspy
 */


package com.jmex.model.collada.schema;

import com.jmex.xml.types.SchemaFloat;

public class gles_texcombiner_commandAlpha_type extends com.jmex.xml.xml.Node {

	public gles_texcombiner_commandAlpha_type(gles_texcombiner_commandAlpha_type node) {
		super(node);
	}

	public gles_texcombiner_commandAlpha_type(org.w3c.dom.Node node) {
		super(node);
	}

	public gles_texcombiner_commandAlpha_type(org.w3c.dom.Document doc) {
		super(doc);
	}

	public gles_texcombiner_commandAlpha_type(com.jmex.xml.xml.Document doc, String namespaceURI, String prefix, String name) {
		super(doc, namespaceURI, prefix, name);
	}
	
	public void adjustPrefix() {
		for (	org.w3c.dom.Node tmpNode = getDomFirstChild( Attribute, null, "operator" );
				tmpNode != null;
				tmpNode = getDomNextChild( Attribute, null, "operator", tmpNode )
			) {
			internalAdjustPrefix(tmpNode, false);
		}
		for (	org.w3c.dom.Node tmpNode = getDomFirstChild( Attribute, null, "scale" );
				tmpNode != null;
				tmpNode = getDomNextChild( Attribute, null, "scale", tmpNode )
			) {
			internalAdjustPrefix(tmpNode, false);
		}
		for (	org.w3c.dom.Node tmpNode = getDomFirstChild( Element, "http://www.collada.org/2005/11/COLLADASchema", "argument" );
				tmpNode != null;
				tmpNode = getDomNextChild( Element, "http://www.collada.org/2005/11/COLLADASchema", "argument", tmpNode )
			) {
			internalAdjustPrefix(tmpNode, true);
			new gles_texcombiner_argumentAlpha_type(tmpNode).adjustPrefix();
		}
	}
	public void setXsiType() {
 		org.w3c.dom.Element el = (org.w3c.dom.Element) domNode;
		el.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type", "gles_texcombiner_commandAlpha_type");
	}

	public static int getoperatorMinCount() {
		return 0;
	}

	public static int getoperatorMaxCount() {
		return 1;
	}

	public int getoperatorCount() {
		return getDomChildCount(Attribute, null, "operator");
	}

	public boolean hasoperator() {
		return hasDomChild(Attribute, null, "operator");
	}

	public gles_texcombiner_operatorAlpha_enums newoperator() {
		return new gles_texcombiner_operatorAlpha_enums();
	}

	public gles_texcombiner_operatorAlpha_enums getoperatorAt(int index) throws Exception {
		return new gles_texcombiner_operatorAlpha_enums(getDomNodeValue(getDomChildAt(Attribute, null, "operator", index)));
	}

	public org.w3c.dom.Node getStartingoperatorCursor() throws Exception {
		return getDomFirstChild(Attribute, null, "operator" );
	}

	public org.w3c.dom.Node getAdvancedoperatorCursor( org.w3c.dom.Node curNode ) throws Exception {
		return getDomNextChild( Attribute, null, "operator", curNode );
	}

	public gles_texcombiner_operatorAlpha_enums getoperatorValueAtCursor( org.w3c.dom.Node curNode ) throws Exception {
		if( curNode == null )
			throw new com.jmex.xml.xml.XmlException("Out of range");
		else
			return new gles_texcombiner_operatorAlpha_enums(getDomNodeValue(curNode));
	}

	public gles_texcombiner_operatorAlpha_enums getoperator() throws Exception 
 {
		return getoperatorAt(0);
	}

	public void removeoperatorAt(int index) {
		removeDomChildAt(Attribute, null, "operator", index);
	}

	public void removeoperator() {
		removeoperatorAt(0);
	}

	public org.w3c.dom.Node addoperator(gles_texcombiner_operatorAlpha_enums value) {
		if( value.isNull() )
			return null;

		return  appendDomChild(Attribute, null, "operator", value.toString());
	}

	public org.w3c.dom.Node addoperator(String value) throws Exception {
		return addoperator(new gles_texcombiner_operatorAlpha_enums(value));
	}

	public void insertoperatorAt(gles_texcombiner_operatorAlpha_enums value, int index) {
		insertDomChildAt(Attribute, null, "operator", index, value.toString());
	}

	public void insertoperatorAt(String value, int index) throws Exception {
		insertoperatorAt(new gles_texcombiner_operatorAlpha_enums(value), index);
	}

	public void replaceoperatorAt(gles_texcombiner_operatorAlpha_enums value, int index) {
		replaceDomChildAt(Attribute, null, "operator", index, value.toString());
	}

	public void replaceoperatorAt(String value, int index) throws Exception {
		replaceoperatorAt(new gles_texcombiner_operatorAlpha_enums(value), index);
	}

	public static int getscaleMinCount() {
		return 0;
	}

	public static int getscaleMaxCount() {
		return 1;
	}

	public int getscaleCount() {
		return getDomChildCount(Attribute, null, "scale");
	}

	public boolean hasscale() {
		return hasDomChild(Attribute, null, "scale");
	}

	public SchemaFloat newscale() {
		return new SchemaFloat();
	}

	public SchemaFloat getscaleAt(int index) throws Exception {
		return new SchemaFloat(getDomNodeValue(getDomChildAt(Attribute, null, "scale", index)));
	}

	public org.w3c.dom.Node getStartingscaleCursor() throws Exception {
		return getDomFirstChild(Attribute, null, "scale" );
	}

	public org.w3c.dom.Node getAdvancedscaleCursor( org.w3c.dom.Node curNode ) throws Exception {
		return getDomNextChild( Attribute, null, "scale", curNode );
	}

	public SchemaFloat getscaleValueAtCursor( org.w3c.dom.Node curNode ) throws Exception {
		if( curNode == null )
			throw new com.jmex.xml.xml.XmlException("Out of range");
		else
			return new SchemaFloat(getDomNodeValue(curNode));
	}

	public SchemaFloat getscale() throws Exception 
 {
		return getscaleAt(0);
	}

	public void removescaleAt(int index) {
		removeDomChildAt(Attribute, null, "scale", index);
	}

	public void removescale() {
		removescaleAt(0);
	}

	public org.w3c.dom.Node addscale(SchemaFloat value) {
		if( value.isNull() )
			return null;

		return  appendDomChild(Attribute, null, "scale", value.toString());
	}

	public org.w3c.dom.Node addscale(String value) throws Exception {
		return addscale(new SchemaFloat(value));
	}

	public void insertscaleAt(SchemaFloat value, int index) {
		insertDomChildAt(Attribute, null, "scale", index, value.toString());
	}

	public void insertscaleAt(String value, int index) throws Exception {
		insertscaleAt(new SchemaFloat(value), index);
	}

	public void replacescaleAt(SchemaFloat value, int index) {
		replaceDomChildAt(Attribute, null, "scale", index, value.toString());
	}

	public void replacescaleAt(String value, int index) throws Exception {
		replacescaleAt(new SchemaFloat(value), index);
	}

	public static int getargumentMinCount() {
		return 1;
	}

	public static int getargumentMaxCount() {
		return 3;
	}

	public int getargumentCount() {
		return getDomChildCount(Element, "http://www.collada.org/2005/11/COLLADASchema", "argument");
	}

	public boolean hasargument() {
		return hasDomChild(Element, "http://www.collada.org/2005/11/COLLADASchema", "argument");
	}

	public gles_texcombiner_argumentAlpha_type newargument() {
		return new gles_texcombiner_argumentAlpha_type(domNode.getOwnerDocument().createElementNS("http://www.collada.org/2005/11/COLLADASchema", "argument"));
	}

	public gles_texcombiner_argumentAlpha_type getargumentAt(int index) throws Exception {
		return new gles_texcombiner_argumentAlpha_type(getDomChildAt(Element, "http://www.collada.org/2005/11/COLLADASchema", "argument", index));
	}

	public org.w3c.dom.Node getStartingargumentCursor() throws Exception {
		return getDomFirstChild(Element, "http://www.collada.org/2005/11/COLLADASchema", "argument" );
	}

	public org.w3c.dom.Node getAdvancedargumentCursor( org.w3c.dom.Node curNode ) throws Exception {
		return getDomNextChild( Element, "http://www.collada.org/2005/11/COLLADASchema", "argument", curNode );
	}

	public gles_texcombiner_argumentAlpha_type getargumentValueAtCursor( org.w3c.dom.Node curNode ) throws Exception {
		if( curNode == null )
			throw new com.jmex.xml.xml.XmlException("Out of range");
		else
			return new gles_texcombiner_argumentAlpha_type(curNode);
	}

	public gles_texcombiner_argumentAlpha_type getargument() throws Exception 
 {
		return getargumentAt(0);
	}

	public void removeargumentAt(int index) {
		removeDomChildAt(Element, "http://www.collada.org/2005/11/COLLADASchema", "argument", index);
	}

	public void removeargument() {
		while (hasargument())
			removeargumentAt(0);
	}

	public org.w3c.dom.Node addargument(gles_texcombiner_argumentAlpha_type value) {
		return appendDomElement("http://www.collada.org/2005/11/COLLADASchema", "argument", value);
	}

	public void insertargumentAt(gles_texcombiner_argumentAlpha_type value, int index) {
		insertDomElementAt("http://www.collada.org/2005/11/COLLADASchema", "argument", index, value);
	}

	public void replaceargumentAt(gles_texcombiner_argumentAlpha_type value, int index) {
		replaceDomElementAt("http://www.collada.org/2005/11/COLLADASchema", "argument", index, value);
	}

}
