package com.adinfi.seven.business.domain;

import java.util.List;

public class TemplateSegmentVO {
	  int segmentId=0;
	  int numElements=0;
	  String type=null;	  
	  short width=0;
      public String getSegName() {
		return segName;
	}
	public void setSegName(String segName) {
		this.segName = segName;
	}
	int idx=0;
      String segName=null;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public List<TemplateSegmentVO> getChildSegments() {
		return childSegments;
	}
	public void setChildSegments(List<TemplateSegmentVO> childSegments) {
		this.childSegments = childSegments;
	}
	List<TemplateSegmentVO> childSegments=null;	 	  
	  public int getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}
	public int getNumElements() {
		return numElements;
	}
	public void setNumElements(int numElements) {
		this.numElements = numElements;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public short getWidth() {
		return width;
	}
	public void setWidth(short width) {
		this.width = width;
	}
	public short getHeight() {
		return height;
	}
	public boolean isChildsEqual() {
		return childsEqual;
	}
	public boolean getChildsEqual() {
		return childsEqual;
	}
	public void setChildsEqual(boolean childsEqual) {
		this.childsEqual = childsEqual;
	}
	public void setHeight(short height) {
		this.height = height;
	}
	public int getParentSegment() {
		return parentSegment;
	}
	public void setParentSegment(int parentSegment) {
		this.parentSegment = parentSegment;
	}
	public boolean isPercentage() {
		return isPercentage;
	}
	public void setPercentage(boolean isPercentage) {
		this.isPercentage = isPercentage;
	}
	short height=0;
	  int parentSegment=0;
	  boolean isPercentage=false;
	  boolean childsEqual=false;
	  int templateChild=0;
	public int getTemplateChild() {
		return templateChild;
	}
	public void setTemplateChild(int templateChild) {
		this.templateChild = templateChild;
	}
}
