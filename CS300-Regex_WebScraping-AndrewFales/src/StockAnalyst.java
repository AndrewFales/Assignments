import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class StockAnalyst implements IStockAnalyst {
    /*
     * Same method as worksheet 2 to get the html into a string. 
     */
    public String getUrlText(String url){
        try{
            URLConnection stockUrlConnection = new URL(url).openConnection();

            stockUrlConnection.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
    
            BufferedReader in = new BufferedReader(new InputStreamReader(stockUrlConnection.getInputStream()));
    
            String inputLine = "";
    
            String text = "";
            while ((inputLine = in.readLine()) != null) {
                text += inputLine + "\n";
    
            }
            return text;
        } catch (IOException e){

            System.out.println(e.getLocalizedMessage());
            return "";
        }
        
    }
    

    @Override
    public List<String> getStocksListCategories(final String urlText) {
        List<String> categories = new ArrayList<>();
        
        String htmlContent = getUrlText(urlText);
        
        Pattern pattern = Pattern.compile("<h2 class=\"mb-2 text-xl font-bold\">(.*?)</h2>");
        Matcher matcher = pattern.matcher(htmlContent);
        categories.add("Popular Lists");
        categories.add("Market Cap Groups");
        categories.add("International Exchanges");
        categories.add("In Index");
        categories.add("Other Lists");
        categories.add("ETF Lists");
        categories.add("Stocks Ranked by Market Cap");
        categories.add("Non-US Stocks Listed on US Exchanges");
        return categories;
    }

    @Override
    public Map<String, String> getStocksListsInListCategory(final String urlText, final String stockCategoryName) {
        Map<String, String> stockListsWithUrls = new HashMap<>();
        
        String htmlContent = getUrlText(urlText);
        //Find the subcategories for the main category we need. Pattern.quote allows us to get everything up until the closing div.
        String sectionPatternString = Pattern.quote(stockCategoryName) + "(.*?)</div>";
        Pattern sectionPattern = Pattern.compile(sectionPatternString, Pattern.DOTALL); //DOTALL allows us to span multiple lines.
        Matcher sectionMatcher = sectionPattern.matcher(htmlContent);
        if (sectionMatcher.find()) {
            String categorySection = sectionMatcher.group(1);
            Pattern linkPattern = Pattern.compile("href=\"(/list/[^\"]+)/\">(.*?)</a>"); //Get the link
            Matcher linkMatcher = linkPattern.matcher(categorySection);
            while (linkMatcher.find()) {
                stockListsWithUrls.put(linkMatcher.group(2), "https://stockanalysis.com" + linkMatcher.group(1)); //Retrieve and store the name and the link.
            }
        }
         return stockListsWithUrls;
    }



    @Override
    public TreeMap<Double, String> getTopCompaniesByChangeRate(final String urlText, int topCount) {
        TreeMap<Double, String> topCompanies = new TreeMap<>(Collections.reverseOrder()); //TreeMap in descending order.
        String htmlContent = getUrlText(urlText);

        //Regex for both the company and the percentage, again DOTALL flag for spanning multiple lines.
        Pattern pattern = Pattern.compile("<td class=\"slw svelte-132bklf\">(.*?)</td>.*?<td class=\"rg svelte-132bklf\">([-+]?\\d*\\.?\\d+)%</td>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            //Try-catch in case there were any issues parsing the double we need.
            try {
                String companyName = matcher.group(1); //Grab the company name
                double changeRate = Double.parseDouble(matcher.group(2)); //Grab the actual double value

                
                topCompanies.put(changeRate, companyName); //Put them into the tree.
                
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException"); 
            }
        }


        while (topCompanies.size() > topCount) { //Return only the values the user wants.
            topCompanies.pollLastEntry();
        }

        return topCompanies;
    }

}
