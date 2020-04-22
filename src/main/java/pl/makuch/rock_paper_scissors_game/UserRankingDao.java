package pl.makuch.rock_paper_scissors_game;

import org.apache.poi.ss.usermodel.*;;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;

@Service
public class UserRankingDao {

    private final String PATH_ROOT = System.getProperty("user.dir");
    private final String PATH_EXCEL = "/src/main/resources/templates/results.xlsx";
    private final String FILE_NAME = PATH_ROOT + PATH_EXCEL;

    private List<User> usersResults;
    private List<String> headers;

    public void readRankingFile() throws IOException {

        usersResults = new ArrayList<>();
        headers = new ArrayList<>();

        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();

        while (iterator.hasNext()) {
            if(headers.size()==0){
                setHeadersFromFile(iterator);
            }
            else {
                usersResults.add(getUserResultsFromFile(iterator));
            }
        }
    }

    private User getUserResultsFromFile(Iterator<Row> iterator) {

        Object[] tempTable = new Object[3];
        int numTab =0;
        Row currentRow = iterator.next();
        Iterator<Cell> cellIterator = currentRow.iterator();

        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            if (currentCell.getCellTypeEnum() == CellType.STRING) {
                tempTable[numTab++]=(currentCell.getStringCellValue());
            } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                tempTable[numTab++]=((int)currentCell.getNumericCellValue());
            }
        }
        return new User((String)tempTable[0],(Integer) tempTable[1], (Integer) tempTable[2]);
    }

    private void setHeadersFromFile(Iterator<Row> iterator) {
        Row currentRow = iterator.next();
        Iterator<Cell> cellIterator = currentRow.iterator();
        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            headers.add(currentCell.getStringCellValue());
        }
    }

    public List<User> getUsersResults() {
        Collections.sort(usersResults, (o1, o2) -> o2.getRank()-o1.getRank());
        return usersResults;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void saveResult(User user) throws IOException {

        FileInputStream fis = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Row row = datatypeSheet.createRow(datatypeSheet.getLastRowNum() + 1);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue((String) user.getUsername());

        Cell cell1 = row.createCell(1);
        cell1.setCellValue((Integer) user.getUserPoints());

        Cell cell2 = row.createCell(2);
        cell2.setCellValue((Integer) user.getCpuPoints());

        fis.close();

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
