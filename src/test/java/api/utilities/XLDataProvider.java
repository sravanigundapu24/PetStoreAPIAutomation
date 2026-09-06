package api.utilities;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;

public class XLDataProvider {
	
	@DataProvider (name="UserData")
	public String[][] getAllUserData() throws IOException
	{
		String filePath = Paths.get(System.getProperty("user.dir"),
				"testdata","Petstore_API_Test_Data.xlsx").toString();
		
		XLUtility xlutil = new XLUtility(filePath);
		String sheetName = "User";
		int rowCount = xlutil.getRowCount(sheetName);
		
		int cellCount  =xlutil.getCellCount(sheetName,1);
		String[][] data = new String[rowCount][cellCount];
		for(int i=1;i<=rowCount;i++)
		{
			for(int j=0;j<cellCount;j++)
			{
				data[i-1][j]=xlutil.getCellData(sheetName, i, j);
			}
		}
		
		return data;
		
	}
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {

		String path = Paths.get(System.getProperty("user.dir"),
				"testdata","Petstore_API_Test_Data.xlsx").toString();;

		XLUtility xl = new XLUtility(path);
		String sheetName = "User";
		int rownum = xl.getRowCount(sheetName);

		String apidata[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) {
			apidata[i - 1] = xl.getCellData(sheetName, i, 1);
		}

		return apidata;
	}
	
	//pet
	 @DataProvider(name = "PetData")
	    public String[][] getAllPetData() throws IOException {

	        String filePath = Paths.get(
	                System.getProperty("user.dir"),
	                "testdata",
	                "Petstore_API_Test_Data.xlsx"
	        ).toString();

	        XLUtility xlutil = new XLUtility(filePath);

	        String sheetName = "Pet";

	        int rowCount = xlutil.getRowCount(sheetName);
	        int cellCount = xlutil.getCellCount(sheetName, 1);

	        String[][] data = new String[rowCount][cellCount];

	        for (int i = 0; i < rowCount; i++) {
	            for (int j = 0; j < cellCount; j++) {
	                data[i][j] = xlutil.getCellData(sheetName, i, j);
	            }
	        }

	        return data;
	    }


	    @DataProvider(name = "PetNames")
	    public String[] getPetNames() throws IOException {

	        String path = Paths.get(
	                System.getProperty("user.dir"),
	                "testdata",
	                "Petstore_API_Test_Data.xlsx"
	        ).toString();

	        XLUtility xl = new XLUtility(path);

	        String sheetName = "Pet";

	        int rownum = xl.getRowCount(sheetName);

	        String[] petdata = new String[rownum];

	        for (int i = 1; i <= rownum; i++) {
	            petdata[i - 1] = xl.getCellData(sheetName, i, 3);
	        }

	        return petdata;
	    }


	    // ================= STORE =================

	    @DataProvider(name = "StoreData")
	    public String[][] getAllStoreData() throws IOException {

	        String filePath = Paths.get(
	                System.getProperty("user.dir"),
	                "testdata",
	                "Petstore_API_Test_Data.xlsx"
	        ).toString();

	        XLUtility xlutil = new XLUtility(filePath);

	        String sheetName = "Store";

	        int rowCount = xlutil.getRowCount(sheetName);
	        int cellCount = xlutil.getCellCount(sheetName, 1);

	        String[][] data = new String[rowCount][cellCount];

	        for (int i = 0; i < rowCount; i++) {
	            for (int j = 0; j < cellCount; j++) {
	                data[i][j] = xlutil.getCellData(sheetName, i, j);
	            }
	        }

	        return data;
	    }


	    @DataProvider(name = "StoreDataById")
	    public String[] getStoreIds() throws IOException {

	        String path = Paths.get(
	                System.getProperty("user.dir"),
	                "testdata",
	                "Petstore_API_Test_Data.xlsx"
	        ).toString();

	        XLUtility xl = new XLUtility(path);

	        String sheetName = "Store";

	        int rownum = xl.getRowCount(sheetName);

	        String[] storedata = new String[rownum];

	        for (int i = 1; i <= rownum; i++) {
	            storedata[i - 1] = xl.getCellData(sheetName, i, 0);
	        }

	        return storedata;
	    }
}
