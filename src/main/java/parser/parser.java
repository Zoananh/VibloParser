package parser;
import entity.infoEntity;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parser {

	public List<infoEntity> parse(List<String> keywords) throws IOException {
		String webPage = "http://viblo.asia";
		List<infoEntity> entList = new ArrayList<infoEntity>();
		Document doc = Jsoup.connect(webPage).get();
	        
		Elements postFeeds = doc.getElementsByClass("post-feed-item");
		String regex = buildRegex(keywords);
		
		for(Element el:postFeeds) {
			infoEntity ent = new infoEntity();
			String postTitle = el.getElementsByClass("post-title--inline").first().getElementsByTag("H3").text();
			if(filter(regex, postTitle)) {			
			 Elements auth = el.getElementsByClass("user--inline");
			 Elements thread = el.getElementsByClass("post-title--inline");
			 Elements stats = el.getElementsByClass("stats");
			 Elements points = el.getElementsByClass("points");
			 
			 ent.setAuthor(auth.first().getElementsByTag("a").text());
			 ent.setThread(thread.first().getElementsByTag("H3").text());
			 
			 String url = thread.first().getElementsByTag("H3").first().getElementsByTag("a").attr("abs:href");
			 ent.setUrl(url);
			 if(!stats.isEmpty()) {
				 ent.setViews(Short.parseShort(stats.first().getElementsByAttributeValue("title", "views").text()));
				 ent.setClips(Short.parseShort(stats.first().getElementsByAttributeValue("title", "clips").text()));
				 ent.setComments(Short.parseShort(stats.first().getElementsByAttributeValue("title", "comments").text()));
				 
				 ent.setPoints(Short.parseShort(points.first().getElementsByTag("span").text()));
			 }
			 ent.setDate(new Timestamp(System.currentTimeMillis()));
			 
			 ent.setId(ent.hashCode());
			 entList.add(ent);
			}
		}
		
		return entList;
	}
	
	public String buildRegex(List<String> keywords) {
		String regex ="";
		if(keywords.size()>1) {
			StringBuilder builder = new StringBuilder();
			builder.append("(?s).*\\b("+keywords.get(0));
			for(int i=1;i<keywords.size();i++) {
				builder.append("|"+keywords.get(i));
			}
			builder.append(")\\b.*");
			regex = builder.toString();
		}
		else {
			regex = "(?s).*\\b"+keywords.get(0)+"\\b.*";
		}
		return regex;
	}
	
	public boolean filter(String regex,String source) {
		if(source.toLowerCase().matches(regex)) {
			
			return true;
		}
		else {
			return false;
		}
	}
}
