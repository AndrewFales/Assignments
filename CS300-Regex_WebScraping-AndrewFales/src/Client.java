import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StockAnalyst s = new StockAnalyst();
        String WEB_URL = "https://stockanalysis.com/list/"; 


        //1. List main categories
        System.out.println("Main category options:");
        List<String> categories = s.getStocksListCategories(WEB_URL);
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        System.out.println("");

        //2. Let user pick a category by number
        System.out.println("Enter the number of the category you're interested in:");
        int categoryChoice = scanner.nextInt();
        String chosenCategory = categories.get(categoryChoice - 1);
        System.out.println("You chose: " + chosenCategory);

        System.out.println("");

        //3. Display subcategories for the chosen category
        System.out.println("Fetching subcategories for " + chosenCategory);
        Map<String, String> subCategoriesMap = s.getStocksListsInListCategory(WEB_URL, chosenCategory);
        List<String> subCategories = new ArrayList<>(subCategoriesMap.keySet()); //Grab all the subcategories
        for (int i = 0; i < subCategories.size(); i++) {
            System.out.println((i + 1) + ". " + subCategories.get(i));
        }

        //4. Let user pick a subcategory by number
        System.out.println("Enter the number of the subcategory you're interested in:");
        int subCategoryChoice = scanner.nextInt();
        String subCategoryName = subCategories.get(subCategoryChoice - 1);
        String subCategoryUrl = subCategoriesMap.get(subCategoryName) + "/";
        
        System.out.println("Fetching top 4 companies from " + subCategoryName + "...");
        TreeMap<Double, String> topCompanies = s.getTopCompaniesByChangeRate(subCategoryUrl, 4);

        System.out.println("");

        //5. Display top 4 companies from the chosen subcategory
        System.out.println("Top 4 companies in " + subCategoryName + ":");
        topCompanies.forEach((changeRate, companyName) -> System.out.println(companyName + " - Change rate: " + changeRate + "%"));

    }
}
