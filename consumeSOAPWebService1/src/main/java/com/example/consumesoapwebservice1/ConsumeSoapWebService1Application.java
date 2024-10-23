package com.example.consumesoapwebservice1;

import com.example.consumesoapwebservice1.waselclient.WaselClient;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



@Configuration
@SpringBootApplication
@EnableFeignClients("com.example.consumesoapwebservice1.feignClients")
public class ConsumeSoapWebService1Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeSoapWebService1Application.class, args);

        //D:\springApplication\consumeSOAPWebService1\consumeSOAPWebService1\src\main\resources\employeesNotResigtered.xls
//        String filePath="D:\\springApplication\\consumeSOAPWebService1\\consumeSOAPWebService1\\src\\main\\resources\\employeesNotResigtered.xls";
//        readExcel(filePath);
    }

    @Autowired
    WaselClient client;

    @Bean
    public Jaxb2Marshaller marshaller()  {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.consumesoapwebservice1.stubs");
        return marshaller;
    }
    @Bean
    public WaselClient soapConnector(Jaxb2Marshaller marshaller) {
//        WaselClient client = new WaselClient();
        client.setDefaultUri("https://apiservices.balady.gov.sa/v1/gsb/wasel-address-service");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    private static void readExcel(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            System.out.println("File>> " +file.getName());
            XSSFWorkbook employeeWorkBook = new XSSFWorkbook(inputStream);
            XSSFSheet sheetAt = employeeWorkBook.getSheetAt(0);
            int firstRowNum = sheetAt.getFirstRowNum();
            int lastRowNum = sheetAt.getLastRowNum();
            for (int index = firstRowNum + 1; index <= lastRowNum; index++) {
                XSSFRow row = sheetAt.getRow(index);
                for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                    XSSFCell cell = row.getCell(cellIndex);
                    printCellValue(cell);
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printCellValue(XSSFCell cell) {
        String stringCellValue = cell.getStringCellValue();
        System.out.println("Cell Value >> "+ stringCellValue);
    }

}
