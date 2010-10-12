package com.ccmedia;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ccmedia.parse.model.basicXMLEntry;
import com.ccmedia.parse.youTube.model.entity.Author;
import com.ccmedia.parse.youTube.model.entity.Category;
import com.ccmedia.parse.youTube.model.entity.Entry;
import com.ccmedia.parse.youTube.model.entity.Feed;
import com.ccmedia.parse.youTube.model.entity.GdComments;
import com.ccmedia.parse.youTube.model.entity.GdFeedLink;
import com.ccmedia.parse.youTube.model.entity.GdRating;
import com.ccmedia.parse.youTube.model.entity.Generator;
import com.ccmedia.parse.youTube.model.entity.Link;
import com.ccmedia.parse.youTube.model.entity.MediaCategory;
import com.ccmedia.parse.youTube.model.entity.MediaContent;
import com.ccmedia.parse.youTube.model.entity.MediaDescription;
import com.ccmedia.parse.youTube.model.entity.MediaGroup;
import com.ccmedia.parse.youTube.model.entity.MediaPlayer;
import com.ccmedia.parse.youTube.model.entity.MediaRestriction;
import com.ccmedia.parse.youTube.model.entity.MediaThumbnail;
import com.ccmedia.parse.youTube.model.entity.Title;
import com.ccmedia.parse.youTube.model.entity.YtDuration;
import com.ccmedia.parse.youTube.model.entity.YtStatistics;

public class YouTubeParseHandler extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================

	private Object CurrentParsingObject = null;
	private Stack<Object> hirarchyStack = null;

	private boolean in_outertag = false;
	private boolean in_innertag = false;
	private boolean in_mytag = false;

	private ParsedYouTubeDataSet myParsedExampleDataSet = new ParsedYouTubeDataSet();

	public YouTubeParseHandler(){
		hirarchyStack = new Stack<Object>();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ParsedYouTubeDataSet getParsedData() {
		return this.myParsedExampleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedExampleDataSet = new ParsedYouTubeDataSet();
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
		if (localName.equals("feed")) {
			hirarchyStack.push(myParsedExampleDataSet.getFeed());
			System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("id")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getId();
			} else if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getId();
			}
		} else if (localName.equals("updated")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getUpdated();
			} else if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getId();
			}
		} else if (localName.equals("category")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getCategory();
				
				String schemeValue = atts.getValue("scheme");
				String termValue = atts.getValue("term");
				((Category)CurrentParsingObject).setScheme(schemeValue);
				((Category)CurrentParsingObject).setTerm(termValue);
			}
		} else if (localName.equals("title")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getTitle();
				String typeValue = atts.getValue("type");
				((Title)CurrentParsingObject).setType(typeValue);
			} else if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getTitle();
				String typeValue = atts.getValue("type");
				((Title)CurrentParsingObject).setType(typeValue);
			}
		} else if (localName.equals("logo")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getLogo();
			}
		} else if (localName.equals("link")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getLink();
				
				String relValue = atts.getValue("rel");
				String typeValue = atts.getValue("type");
				String hrefValue = atts.getValue("href");
				
				Link link = new Link();
				link.setRel(relValue);
				link.setType(typeValue);
				link.setHref(hrefValue);
				
				((Feed) hirarchyStack.lastElement()).getLink().add(link);
			}
		} else if (localName.equals("author")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getAuthor();
				hirarchyStack.push(CurrentParsingObject);
				System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
			} else if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getAuthor();
				hirarchyStack.push(CurrentParsingObject);
				System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
			}
		} else if (localName.equals("name")) {
			if(hirarchyStack.lastElement() instanceof Author){
				CurrentParsingObject = ((Author) hirarchyStack.lastElement()).getName();
			}
		} else if (localName.equals("uri")) {
			if(hirarchyStack.lastElement() instanceof Author){
				CurrentParsingObject = ((Author) hirarchyStack.lastElement()).getUri();
			}
		} else if (localName.equals("generator")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getGenerator();
				
				String versionValue = atts.getValue("version");
				String uriValue = atts.getValue("uri");
				((Generator)CurrentParsingObject).setVersion(versionValue);
				((Generator)CurrentParsingObject).setUri(uriValue);
			}
		} else if (localName.equals("totalResults")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getTotalResult();
			}
		} else if (localName.equals("startIndex")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getStartIndex();
			}
		} else if (localName.equals("itemsPerPage")) {
			if(hirarchyStack.lastElement() instanceof Feed){
				CurrentParsingObject = ((Feed) hirarchyStack.lastElement()).getItemsPerPage();
			}
		} else if (localName.equals("entry")) {
			System.out.println("stack size: "+hirarchyStack.size());
			if(hirarchyStack.lastElement() instanceof Feed){
				Entry entry = new Entry();
				((Feed) hirarchyStack.lastElement()).getEntry().add(entry);
				hirarchyStack.push(entry);
				System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
				CurrentParsingObject = entry;
			}
		} else if (localName.equals("comments")) {
			if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getGdComments();
				hirarchyStack.push(CurrentParsingObject);
				System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
			}
		} else if (localName.equals("feedLink")) {
			if(hirarchyStack.lastElement() instanceof GdComments){
				CurrentParsingObject = ((GdComments) hirarchyStack.lastElement()).getGdGeedLink();
				
				String hrefValue = atts.getValue("href");
				String countHintValue = atts.getValue("countHint");
				int countHintValueInt = 0;
				try{
					countHintValueInt = Integer.parseInt(countHintValue);
				}catch(Exception e){
					
				}
				((GdFeedLink)CurrentParsingObject).setHref(hrefValue);
				((GdFeedLink)CurrentParsingObject).setCountHint(countHintValueInt);
			}
		} else if (localName.equals("group")) {
			if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getMediaGroup();
				hirarchyStack.push(CurrentParsingObject);
				System.out.println("push ==> "+localName+", size: "+hirarchyStack.size());
			}
		} else if (localName.equals("category")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getMediaCategory();
				
				String labelValue = atts.getValue("label");
				String schemeValue = atts.getValue("scheme");
				
				((MediaCategory)CurrentParsingObject).setLabel(labelValue);
				((MediaCategory)CurrentParsingObject).setScheme(schemeValue);
			}
		} else if (localName.equals("content")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				String urlValue = atts.getValue("url");
				String typeValue = atts.getValue("type");
				String mediumValue = atts.getValue("medium");
				String expressionValue = atts.getValue("expression");
				String durationValue = atts.getValue("duration");
				String formatValue = atts.getValue("format");
				
				MediaContent mediaContent = new MediaContent();
				
				mediaContent.setUrl(urlValue);
				mediaContent.setType(typeValue);
				mediaContent.setMedium(mediumValue);
				mediaContent.setExpression(expressionValue);
				mediaContent.setDuration(durationValue);
				mediaContent.setFormat(formatValue);
				
				((MediaGroup) hirarchyStack.lastElement()).getMediaContent().add(mediaContent);
				
				CurrentParsingObject = mediaContent;
			}
		} else if (localName.equals("description")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getMediaDescription();
				
				String typeValue = atts.getValue("type");
				
				((MediaDescription)CurrentParsingObject).setType(typeValue);
			}
		} else if (localName.equals("keywords")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getMediaKeywords();
			}
		} else if (localName.equals("player")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getMediaPlayer();
				
				String urlValue = atts.getValue("url");
				
				((MediaPlayer)CurrentParsingObject).setUrl(urlValue);
			}
		} else if (localName.equals("restriction")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getMediaRestriction();
				
				String typeValue = atts.getValue("type");
				String relationshipValue = atts.getValue("relationship");
				
				((MediaRestriction)CurrentParsingObject).setType(typeValue);
				((MediaRestriction)CurrentParsingObject).setRelationship(relationshipValue);
			}
		} else if (localName.equals("thumbnail")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				String urlValue = atts.getValue("url");
				String heightValue = atts.getValue("height");
				String widthValue = atts.getValue("width");
				String timeValue = atts.getValue("time");
				
				int heightIntValue = 0;
				int widthIntValue = 0;
				
				try{
					heightIntValue = Integer.parseInt(heightValue);
					widthIntValue = Integer.parseInt(widthValue);
				}catch(Exception e){
					
				}
				
				MediaThumbnail mediaThumbnail = new MediaThumbnail();
				mediaThumbnail.setUrl(urlValue);
				mediaThumbnail.setHeight(heightIntValue);
				mediaThumbnail.setWidth(widthIntValue);
				mediaThumbnail.setTime(timeValue);
				
				CurrentParsingObject = mediaThumbnail;
				
				((MediaGroup) hirarchyStack.lastElement()).getMediaThumbnail().add(mediaThumbnail);
			}
		} else if (localName.equals("title")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getTitle();
				
				String typeValue = atts.getValue("type");
				
				((Title)CurrentParsingObject).setType(typeValue);
			}
		} else if (localName.equals("duration")) {
			if(hirarchyStack.lastElement() instanceof MediaGroup){
				CurrentParsingObject = ((MediaGroup) hirarchyStack.lastElement()).getYtDuration();
				
				String secondsValue = atts.getValue("seconds");
				
				int secondsIntValue = 0;
				
				try{
					secondsIntValue = Integer.parseInt(secondsValue);
				}catch(Exception e){
					
				}
				((YtDuration)CurrentParsingObject).setSeconds(secondsIntValue);
			}
		} else if (localName.equals("noembed")) {
			if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getYtNoembed();
			}
		} else if (localName.equals("rating")) {
			if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getGdRating();
				
				String averageValue = atts.getValue("average");
				String maxValue = atts.getValue("max");
				String minValue = atts.getValue("min");
				String numRatersValue = atts.getValue("numRaters");
				String relValue = atts.getValue("rel");
				
				float averageFloatValue = 0;
				int maxIntValue = 0;
				int minIntValue = 0;
				int numRatersIntValue = 0;
				
				try{
					averageFloatValue = Float.parseFloat(averageValue);
					maxIntValue = Integer.parseInt(maxValue);
					minIntValue = Integer.parseInt(minValue);
					numRatersIntValue = Integer.parseInt(numRatersValue);
				}catch(Exception e){
					
				}
				
				((GdRating)CurrentParsingObject).setAverage(averageFloatValue);
				((GdRating)CurrentParsingObject).setMax(maxIntValue);
				((GdRating)CurrentParsingObject).setMin(minIntValue);
				((GdRating)CurrentParsingObject).setNumRaters(numRatersIntValue);
				((GdRating)CurrentParsingObject).setRel(relValue);
			}
		} else if (localName.equals("recorded")) {
			if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getYtRecorded();
			}
		} else if (localName.equals("statistics")) {
			if(hirarchyStack.lastElement() instanceof Entry){
				CurrentParsingObject = ((Entry) hirarchyStack.lastElement()).getYtStatistics();
				
				String favoriteCountValue = atts.getValue("favoriteCount");
				String viewCountValue = atts.getValue("viewCount");
				
				int favoriteCountIntValue = 0;
				int viewCountIntValue = 0;
				
				try{
					favoriteCountIntValue = Integer.parseInt(favoriteCountValue);
					viewCountIntValue = Integer.parseInt(viewCountValue);
				}catch(Exception e){
					
				}
				
				((YtStatistics)CurrentParsingObject).setFavoriteCount(favoriteCountIntValue);
				((YtStatistics)CurrentParsingObject).setViewCount(viewCountIntValue);
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

		if (localName.equals("author")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("feed")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("entry")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("comments")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} else if (localName.equals("group")) {
			hirarchyStack.pop();
			System.out.println("pop ==> "+localName+", size: "+hirarchyStack.size());
		} 
		//else if (localName.equals("mytag")) {
//			this.in_mytag = false;
//		} else if (localName.equals("tagwithnumber")) {
//			// Nothing to do here
//		}
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