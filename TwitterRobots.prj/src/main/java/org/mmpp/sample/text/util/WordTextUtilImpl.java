package org.mmpp.sample.text.util;

public class WordTextUtilImpl implements WordTextUtil {

	public String getText(){
		
		StringBuffer buf = new StringBuffer();
		
		// 格言表記 //
		
		buf.append("( by ");
		buf.append(getSayPerson());
		buf.append(" ). ");
		return buf.toString();
	}
	public String getSayPerson(){
		return getSayPersons().get((int)(getSayPersons().size()*Math.random()));
	}
	private java.util.List<String> _sayPersons = null;
	
	public java.util.List<String> getSayPersons(){
		if(_sayPersons==null){
			_sayPersons = new java.util.LinkedList<String>();
			_sayPersons.add("A");
			_sayPersons.add("B");
			_sayPersons.add("C");
			_sayPersons.add("D");
			_sayPersons.add("E");
		}
		
		return _sayPersons;
	}

}
