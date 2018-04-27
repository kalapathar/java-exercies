package spider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Downloads web page content starting with a starting url.
 * If the spider encounters links in the content, it downloads
 * those as well.
 * 
 * Steps:
 * 1. Complete the processPage method.  One TestSpider unit tests should pass.
 * 2. Complete the crawl() method.  Both TestSpider unit tests should pass.
 *  
 * @author shilad
 *
 */
public class Spider {
	/**
	 * Urls waiting to be scraped.  The "work" left to do.
	 */
	private Queue<String> work = new LinkedList<String>();
	
	/**
	 * Keeps track of counts for each url.
	 */
	private AllWordsCounter urlCounter = new AllWordsCounter();
	
	/**
	 * Maximum number of urls that should be scraped.
	 */
	private int maxUrls = 100;
	
	/**
	 * URLs that have already been retrieved.
	 */
	private List<String> finished = new ArrayList<String>();
	
	/**
	 * Helps download and parse the web pages.
	 */
	private HttpHelper helper = new HttpHelper();
	
	/**
	 * Creates a new spider that will crawl at most maxUrls.
	 * @param maxUrls Maximum number of URLs to crawl.
	 */
	public Spider(int maxUrls) {
		this.maxUrls = maxUrls;
	}
	
	/**
	 * Crawls at most maxUrls starting with beginningUrl.
	 * @param beginningUrl Starting URL, indicating a web page that 
	   potentially contains other URLs.
	 */
	public void crawl(String beginningUrl) {
		work.add(beginningUrl);
		while (work.size()>0 && finished.size()<=maxUrls){
			String lado=work.peek();
			processPage(lado);
			finished.add(lado);
			work.remove();
		}
		
		// TODO: While there is remaining work and we haven't
		// reach the maximum # of finished urls, process
		// the next unfinshed url.  After processing, mark
		// it as finished.
	}
	
	/**
	 * Retrieves content from a url and processes that content. 
	 * @param url A URL to process.
	 */
	public void processPage(String url) {
		String html = helper.retrieve(url);
		List<String> links=helper.extractLinks(url,html); 
		String[] linksarr=links.toArray(new String[0]); //converts the arraylist to string[] to process URLS
		//finished=helper.extractLinks(url,html);
		for (int i=0;i<linksarr.length;i++){
			if (!helper.isImage(linksarr[i])){
				urlCounter.countWord(linksarr[i]);
				work.add(linksarr[i]);
			}
	} 
		
		
		
		// TODO: extract all the links from the url
		// For each link that isn't an image, increment the
		// count for that link and queue up the link for future scraping.
		// HINT: Take a look at the helper class
	}
	
	/**
	 * Returns the number of times the spider encountered
	 * links to each url.  The url are returned in increasing
	 * frequency order.
	 * 
	 * @return Number of URLs encountered.
	 */
	public WordCount[] getUrlCounts() {
		return urlCounter.getCounts();
	}
	
	/** Getter only to be used for testing.
	    @return The state variable <code>work</code>
	 */
	Queue<String> getWork() { return work; }
	/** Getter only to be used for testing.
	    @return The state variable <code>finished</code>
	 */
	List<String> getFinished() { return finished; }
}
