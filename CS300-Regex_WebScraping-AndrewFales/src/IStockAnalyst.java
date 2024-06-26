import java.util.*;

/**
 * IStockAnalyst
 */
public interface IStockAnalyst {

    String WEB_URL = " https://stockanalysis.com/list/";

    String getUrlText(final String url);


    List<String> getStocksListCategories(final String urlText);
    /**
    * Method to get the list of stocks within a stock list category
    * @param urlText the text of the page that has the stock list and their
    categories
    * @param stockCategoryName the stock list category name
    * @return map that contains stock lists and their URLs. Key is stock
    list name, and value is the URL
    * Example of a map entry <“Mega-Cap”,
    https://stockanalysis.com/list/mega-cap-stocks/>
    */
    Map<String, String> getStocksListsInListCategory(final String urlText,
    final String stockCategoryName);
    // Ignore change percentages that are not numbers (e.g. "-")
    /**
    * Method to get top companies by change rate
    * @param urlText the text of the page that has the stock list
    * @param topCount how many companies to return
    * @return map that has the top companies and their change rate. Key is
    the change rate and value is the company name
    */
    TreeMap<Double, String> getTopCompaniesByChangeRate(final String urlText,
    int topCount);
}
