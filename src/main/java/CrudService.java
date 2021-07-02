import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudService {
    BufferedReader br;

    public void readData(DQLService dql) {
        List<Map<String, Object>> resultList = dql.selectAll();
        dql.printMapList(resultList);
    }

    public HashMap<String, Object> createData() {
        int num;
        String name;
        String gender;
        String major;
        String studentNum;
        String regDate;

        final HashMap<String, Object> dataMap = new HashMap<String, Object>();

        try {
            System.out.print("팀원 이름 : ");
            br = new BufferedReader(new InputStreamReader(System.in));
            name = br.readLine();
            dataMap.put("NAME"   , name);

            System.out.print("성별 : ");
            gender = br.readLine();
            dataMap.put("GENDER"   , gender);

            System.out.print("학부 : ");
            major = br.readLine();
            dataMap.put("MAJOR"   , major);

            System.out.print("학번 : ");
            studentNum = br.readLine();
            dataMap.put("STUDENT_NUM"   , studentNum);

        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        regDate = date.format(formatter);
        dataMap.put("REG_DATE" , regDate);

        return dataMap;
    }

    public HashMap<String, Object> updateData() {
        int num;
        String name;
        String gender;
        String major;
        String studentNum;
        String regDate;

        final HashMap<String, Object> updateMap = new HashMap<String, Object>();

        try {
            System.out.print(">>> 수정할 팀원 번호 : ");
            br = new BufferedReader(new InputStreamReader(System.in));

            num = Integer.parseInt(br.readLine());
            updateMap.put("updateID" , num);

            System.out.print("팀원 이름 : ");
            name = br.readLine();
            updateMap.put("NAME"   , name);

            System.out.print("성별 : ");
            gender = br.readLine();
            updateMap.put("GENDER"   , gender);

            System.out.print("학부 : ");
            major = br.readLine();
            updateMap.put("MAJOR"   , major);

            System.out.print("학번 : ");
            studentNum = br.readLine();
            updateMap.put("STUDENT_NUM"   , studentNum);

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            regDate = date.format(formatter);
            updateMap.put("REG_DATE" , regDate);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return updateMap;
    }

    public int deleteData() {

        int num = 0;

        try {
            System.out.print(">>> 삭제할 팀원 번호 : ");
            br = new BufferedReader(new InputStreamReader(System.in));
            num = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return num;
    }
}