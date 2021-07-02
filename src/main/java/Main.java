import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException{

        int result = -1;
        Connection conn = SQLiteManager.getConnection();
        List<Map<String, Object>> resultList;

        Menu m = new Menu();
        CrudService crudService = new CrudService();
        SearchService searchService = new SearchService();
        DDLService DDL = new DDLService(conn);
        DMLService DML = new DMLService(conn);
        DQLService DQL = new DQLService(conn);
        FileService fileService = new FileService();

        DDL.createTable();

        System.out.println(">>>시작<<<");

        while(true) {
            try {
                String choose = m.printMenu();
                switch(choose){
                    case "1":
                        crudService.readData(DQL);
                        break;

                    case "2":
                        result = DML.insertPerson(crudService.createData());
                        if( result >= 0 ) {
                            System.out.println(">>>추가 완료<<<\n");
                        } else {
                            System.out.println(">>>데이터 입력 실패<<<\n");
                        }

                        break;

                    case "3":
                        crudService.readData(DQL); // 데이터 출력

                        result = DML.updatePerson(crudService.updateData());
                        if( result >= 0 ) {
                            System.out.println(">>>수정 완료<<<\n");
                        } else {
                            System.out.println(">>>데이터 수정 실패<<<\n");
                        }

                        break;

                    case "4":
                        crudService.readData(DQL); // 데이터 출력

                        result = DML.deletePerson(crudService.deleteData());
                        if( result >= 0 ) {
                            System.out.println(">>>삭제 완료<<<\n");
                        } else {
                            System.out.println(">>>데이터 삭제 실패<<<\n");
                        }

                        break;

                    case "5":
                        resultList = DQL.selectByName(searchService.searchByName());
                        DQL.printMapList(resultList);
                        break;

                    case "6":
                        resultList = DQL.selectAll();
                        fileService.saveFile(resultList);
                        System.out.println(">>>파일 저장 완료<<<\n");
                        break;

                    case "0":
                        System.out.println(">>>종료<<<\n");
                        return;

                    default:
                        System.out.println(">>>잘못된 메뉴 선택<<<\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
