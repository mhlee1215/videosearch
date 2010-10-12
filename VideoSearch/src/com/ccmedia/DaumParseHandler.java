package com.ccmedia;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ccmedia.parse.daum.model.entity.Channel;
import com.ccmedia.parse.daum.model.entity.Item;
import com.ccmedia.parse.daum.model.entity.Rss;
import com.ccmedia.parse.daum.model.entity.Tag;
import com.ccmedia.parse.daum.model.entity.Thumb;
import com.ccmedia.parse.daum.model.entity.Time;
import com.ccmedia.parse.model.basicXMLEntry;

public class DaumParseHandler extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================

	private Object CurrentParsingObject = null;
	private Stack<Object> hirarchyStack = null;

	private boolean in_outertag = false;
	private boolean in_innertag = false;
	private boolean in_mytag = false;

	private ParsedDaumDataSet myParsedExampleDataSet = new ParsedDaumDataSet();

	public DaumParseHandler(){
		hirarchyStack = new Stack<Object>();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ParsedDaumDataSet getParsedData() {
		return this.myParsedExampleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedExampleDataSet = new ParsedDaumDataSet();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
		System.out.println(myParsedExampleDataSet);
	}

	/**
	 * Gets be called on opening tags like: <tag> Can provide attribute(s), when
	 * xml was like: <tag attribute="attributeValue">
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		//System.out.println("startElement..."+namespaceURI+", " + localName + ", " + qName + ", "
		//		+ atts);
		
		if (localName.equals("rss")) {
			hirarchyStack.push(myParsedExampleDataSet.getRss());
			System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("channel")) {
			CurrentParsingObject = ((Rss) hirarchyStack.lastElement()).getChannel();
			hirarchyStack.push(CurrentParsingObject);
			System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("title")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getTitle();
			} else if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getTitle();
			}
		} else if (localName.equals("link")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getLink();
			} else if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getLink();
			}
		} else if (localName.equals("description")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getDescription();
			} else if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getDescription();
			}
		} else if (localName.equals("lastBuildDate")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getLastBuildDate();
			}
		} else if (localName.equals("generator")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getGenerator();
			}
		} else if (localName.equals("totalCount")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getTotalCount();
			}
		} else if (localName.equals("result")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getResult();
			}
		} else if (localName.equals("sort")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getSort();
			}
		} else if (localName.equals("q")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getQ();
			}
		} else if (localName.equals("tagsearch")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getTagsearch();
			}
		} else if (localName.equals("pageno")) {
			if(hirarchyStack.lastElement() instanceof Channel){
				CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getPageno();
			}
		} else if (localName.equals("item")) {
			CurrentParsingObject = ((Channel) hirarchyStack.lastElement()).getItem();
			hirarchyStack.push(CurrentParsingObject);
			System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("tag")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getTag();
			}
		} else if (localName.equals("cpname")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getCpname();
			}
		} else if (localName.equals("author")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getAuthor();
			}
		} else if (localName.equals("player_url")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getPlayer_url();
			}
		} else if (localName.equals("playcnt")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getPlaycnt();
			}
		} else if (localName.equals("score")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getScore();
			}
		} else if (localName.equals("recommend")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getRecommend();
			}
		} else if (localName.equals("pubDate")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getPubDate();
			}
		} else if (localName.equals("bitrate")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getBitrate();
			}
		} else if (localName.startsWith("time_")) {
			if(hirarchyStack.lastElement() instanceof Item){
				Time time = new Time();
				CurrentParsingObject = time;
				((Item) hirarchyStack.lastElement()).getTime().add(time);
			}
		} else if (localName.equals("thumbnail")) {
			if(hirarchyStack.lastElement() instanceof Item){
				CurrentParsingObject = ((Item) hirarchyStack.lastElement()).getThumbnail();
			}
		} else if (localName.startsWith("thumb_")) {
			if(hirarchyStack.lastElement() instanceof Item){
				Thumb thumb = new Thumb();
				CurrentParsingObject = thumb;
				((Item) hirarchyStack.lastElement()).getThumb().add(thumb);
			}
		} 
			
		// else if (localName.equals("mytag")) {
		// this.in_mytag = true;
		// }else if (localName.equals("tagwithnumber")) {
		// // Extract an Attribute
		// String attrValue = atts.getValue("thenumber");
		// int i = Integer.parseInt(attrValue);
		// myParsedExampleDataSet.setExtractedInt(i);
		// }
	}

	/**
	 * Gets be called on closing tags like: </tag>
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

		if (localName.equals("item")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("channel")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("rss")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} 
	}

	/**
	 * Gets be called on the following structure: <tag>characters</tag>
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		System.out.println("char..." + new String(ch, start, length));
		if(CurrentParsingObject instanceof basicXMLEntry){
			((basicXMLEntry)CurrentParsingObject).setInnerText(new String(ch, start, length));
		}else{
			System.out.println("!!!!!!!!!!!!");
		}
//		if (this.in_mytag) {
//
//			// myParsedExampleDataSet.setExtractedString(new String(ch, start,
//			// length));
//		}
	}
}