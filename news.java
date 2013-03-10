import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.*;

class news
{
	public static void main(String args[]) throws IOException
	{
		String url="";
		//you can give your rss url here
		url="http://news.google.com/news?pz=1&cf=all&ned=in&hl=en&output=rss";
		
		/********** if you are using internet via proxy,
		remove the below commented and state your proxy address, port,
		user & password in the code given below
		if username and password are not required, let them be in comments
		********************/
		/********************
		System.getProperties().put("http.proxyHost", "172.16.0.16");
		System.getProperties().put("http.proxyPort", "8080");
		System.getProperties().put("http.proxyUser", "someUserName");
		System.getProperties().put("http.proxyPassword", "somePassword");
		************/
		
		File rss=new File("news.xml");
		FileWriter fw=new FileWriter(rss);
		BufferedWriter bw=new BufferedWriter(fw);
		try
		{
			URL rssurl=new URL(url);
			BufferedReader in=new BufferedReader(new InputStreamReader(rssurl.openStream()));
			String line="";
			while((line=in.readLine())!=null)
			{
				bw.write(line);
			}
			bw.close();
			in.close();
		}catch(MalformedURLException ue)
		{
			System.out.println(ue);
		}catch(IOException ioe)
		{
			System.out.println(ioe);
		}
		
		try
		{
			
			File fXmlFile = new File("gnews.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("item");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
		 
				Node nNode = nList.item(temp);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
		 
					Element eElement = (Element) nNode;
		 
					System.out.println(eElement.getElementsByTagName("title").item(0).getTextContent());
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}