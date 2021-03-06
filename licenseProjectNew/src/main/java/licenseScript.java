    
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.time.Duration;
    
    import org.apache.poi.ss.usermodel.CellType;
    import org.apache.poi.xssf.usermodel.XSSFSheet;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    
    
    public class licenseScript {
        public static void main(String[] args) throws IOException {
    
    
            String str1 = System.getProperty("user.home");
            //System.setProperty("webdriver.chrome.driver", str1 + "/Downloads/chromedriver");
            System.setProperty("webdriver.chrome.driver", str1 + "\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
    
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
    
            //File src = new File(str1 + "/Documents/fancentral.xlsx");
            File src = new File(str1 + "\\Documents\\fancentral.xlsx");
    
            FileInputStream fls = new FileInputStream(src);
            XSSFWorkbook wb = new XSSFWorkbook(fls);
            XSSFSheet sheet1 = wb.getSheetAt(0);// accessing sheet1 using "0" as
            // index via getSheetAt
            int rowcount = sheet1.getLastRowNum();
            String tcin;
            String brand,licProp,licPersn;
            for(int i=1;i<=rowcount;i++)
            
            {
                sheet1.getRow(i).getCell(0).setCellType(CellType.STRING);
                tcin=sheet1.getRow(i).getCell(0).getStringCellValue();
                driver.get("https://rover2.prod.target.com/#/?environment=prod&layer=core_items&tcin="+tcin);
                //wait(driver,"//div/span[contains(text(),'tcin')]");
                wait(driver,"/html/body/div/div/div/div[2]/div[1]/div/div[2]/div[2]/div/div/ul/li[1]/div/div/div/span[2]");
                //brand=driver.findElement(By.xpath("//div/span[text()='product_brand:']/parent::div/ul/li/div/div/div/span[contains(text(),'brand: ')]/following-sibling::span[@class='json-scalar-value']")).getText();
                brand=driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div[2]/div[2]/div/div/ul/li[38]/div/div/ul/li[1]/div/div/div/ul/li[2]/div/div/div/span[2]")).getText();
                //System.out.println(brand);
                //licProp = driver.findElement(By.xpath("//div/span[text()='product_brand:']/parent::div/ul/li[7]/div/div/div/span[@class='json-scalar-value']")).getText();
                licProp = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div[2]/div[2]/div/div/ul/li[38]/div/div/ul/li[2]/div/div/div/ul/li[2]/div/div/div/span[2]")).getText();
                //System.out.println(licProp);
                //licPersn = driver.findElement(By.xpath("//div/span[text()='product_brand:']/parent::div/ul/li[9]/div/div/div/span[@class='json-scalar-value']")).getText();
                licPersn = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[1]/div/div[2]/div[2]/div/div/ul/li[38]/div/div/ul/li[2]/div/div/div/ul/li[2]/div/div/div/span[2]")).getText();
                //System.out.println(licPersn);
    
    
    
                sheet1.getRow(i).createCell(1).setCellValue(brand);
                sheet1.getRow(i).createCell(2).setCellValue(licProp);
                sheet1.getRow(i).createCell(3).setCellValue(licPersn);
                //FileOutputStream fos = new FileOutputStream(str1 + "/Documents/fancentral.xlsx");
                FileOutputStream fos = new FileOutputStream(str1 + "\\Documents\\fancentral.xlsx");
    
    
                wb.write(fos);
                fos.close();
                brand="";licPersn="";licProp="";
                System.out.println("/////////////////////////////");
            }
    
        }
    
        private static void wait(WebDriver driver, String locator) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        }
    
    
    }
